package algorithm;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

import refPoint.Point;

public class LinearSearch {
	
	
	private RPQ heap;
	private ArrayList<Point> query;
	private int k;
	private BuildIndex index;
	private double bestSoFar = 0;
	private String clusterFilePath = "C:\\Users\\WANG Haozhou\\Documents\\myUQ\\expData\\Dasfaa\\";
	private int model;
	private ArrayList<Point> refPoint;
	
	/**
	 * @param args
	 */
	
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		ArrayList<Point> q = new ArrayList<Point>();
		Point p = new Point("1", new double[]{116.427898004571,39.8975208179019});
		q.add(p);
		p =  new Point("2", new double[]{116.478003964994,40.0026050122136});
		q.add(p);
		LinearSearch ls = new LinearSearch(q, 3, 100, 1);

	}
	
	
	
	public LinearSearch(ArrayList<Point> query, int k, int num, int model){
		this.query = query;
		this.heap = new RPQ();
		this.k = k;
		this.refPoint = new FindNearestPoint(query, num).getPoint();
		this.index = new BuildIndex(refPoint, 1, num);
		this.model = model;
		
		File flist = new File(clusterFilePath + "grid\\");
		String[] list = flist.list();
		for(String f : list){
			deepSearch(f);
		}
		
		for (int i = 0; i < k; i++){
			System.out.println(heap.poll().id);
		}
		
	}
	
	private double getAggDistance(String name){
		ArrayList<Double> refDistance = new ArrayList<Double>();
		for (Point p : refPoint){
			String key = p.id + "," + name;
			refDistance.add(index.getDistance(key));
			
		}
		
		
		return new AggregateFunction(this.model, refDistance).getDistance();
	}
	
	private void pushToHeap(String id, double distance){
		PQElement element = new PQElement(id, distance);
		heap.add(element);
		if (heap.size() > k){
			heap.poll();
		}
	}
	
	private void deepSearch(String name){
		BufferedReader reader = null;
		String lineWord;
		String best = "";
		try {
			reader = new BufferedReader(new FileReader(clusterFilePath + "grid\\" + name));
			while ((lineWord = reader.readLine()) != null){
				String[] tr = lineWord.split(",");
				double[] p = new double[2];
				p[0] = Double.parseDouble(tr[1]);
				p[1] = Double.parseDouble(tr[2]);
				double distance = getAggDistance(p);
				if (bestSoFar < distance){
					pushToHeap(tr[0], distance);
					bestSoFar = heap.peek().distance;
					//best = tr[0];
				}
				
				
			}
		} catch (NumberFormatException | IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	private double getAggDistance(double[] p){
		ArrayList<Double> tempd = new ArrayList<Double>();
		for (Point q : this.query){
			tempd.add(getDistance(q.p, p));
		}
		
		return new AggregateFunction(this.model, tempd).getDistance();
	}
	
	
	private double getDistance(double[] x, double[] y){
		double distance = 0;
		for (int i = 0; i < 2; i++) {
            double temp=Math.pow((x[i]-y[i]),2);
            distance=distance+temp;
       }	
		return Math.pow(distance, 0.5);
		
	}

}
