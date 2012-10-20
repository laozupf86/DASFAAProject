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
	//private String clusterFilePath = "C:\\myUQ\\expData\\Dasfaa\\";
	private int model;
	private ArrayList<Point> refPoint;
	int count = 0;
	private long startTime, endTime;
	
	/**
	 * @param args
	 */
	
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		//Test for number of reference point
		
		System.out.println("Test Ref Point");
		int[] numRefPoint = {25, 50, 100, 200, 400, 600, 800, 1000};
		for (int i = 0; i < numRefPoint.length; i++){
			System.out.println("Current refence point is " + numRefPoint[i]);
			System.out.println("------------SUM function---------------");
			
			
			LinearSearch ls = new LinearSearch(new Query(7).getQueryPoint(), 10, numRefPoint[i], 1);
			
			
			System.out.println("Time is " + ls.getTime());
			System.out.println("------------Min function---------------");
			
			ls = new LinearSearch(new Query(7).getQueryPoint(), 10, numRefPoint[i], 2);
			
			System.out.println("Time is " + ls.getTime());
			System.out.println("######END############");
		}
		
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
		this.startTime = System.currentTimeMillis();
		for(String f : list){ 
			//System.out.println("current grid is " + f);
			if (getAggDistance(f) > bestSoFar){
				count++;
				deepSearch(f);
			}else{
				//deepSearch(f);
			}
			
		}
		this.endTime = System.currentTimeMillis();
		/*
		for (int i = 0; i < k; i++){
			PQElement r = heap.poll();
			System.out.println(r.id + " , " + r.distance);
		}
		*/
		System.out.println("IO is " + count);
		
	}
	
	public long getTime(){
		return this.endTime - this.startTime;
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
		//String best = "";
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
