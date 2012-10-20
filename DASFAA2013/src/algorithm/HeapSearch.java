package algorithm;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

import refPoint.Point;

public class HeapSearch {
	
	private PQ pq;
	private RPQ heap;
	private ArrayList<Point> query;
	private int k;
	private BuildIndex index;
	private double bestSoFar = 0;
	//private String clusterFilePath = "C:\\Users\\WANG Haozhou\\Documents\\myUQ\\expData\\Dasfaa\\";
	private String clusterFilePath = "C:\\myUQ\\expData\\Dasfaa\\";
	private int model;
	private ArrayList<Point> refPoint;
	int count = 0;
	
	/**
	 * @param args
	 */
	
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		ArrayList<Point> q = new ArrayList<Point>();
		Point p = new Point("1", new double[]{115.57475198923,39.6350970061053});
		q.add(p);
		p =  new Point("2", new double[]{115.588992981429,40.632183011384});
		q.add(p);
		p =  new Point("3", new double[]{117.165610955059,39.6350970061053});
		q.add(p);
		p =  new Point("4", new double[]{117.38265201254,40.632183011384});
		q.add(p);
		p =  new Point("5", new double[]{116.513557038151,39.866977019899});
		q.add(p);
		/*p =  new Point("5", new double[]{146.478003964994,38.0026050122136});
		q.add(p);
		p =  new Point("6", new double[]{106.478003964994,33.0026050122136});
		q.add(p);
		p =  new Point("7", new double[]{156.478003964994,40.0026050122136});
		q.add(p);
		p =  new Point("8", new double[]{166.478003964994,41.0026050122136});
		q.add(p);
		p =  new Point("9", new double[]{156.478003964994,42.0026050122136});
		q.add(p);
		p =  new Point("10", new double[]{116.478003964994,40.0026050122136});
		q.add(p);*/
		LinearSearch ls = new LinearSearch(q, 10, 500, 2);
		q = new ArrayList<Point>();
		p = new Point("1", new double[]{115.57475198923,39.6350970061053});
		q.add(p);
		p =  new Point("2", new double[]{115.57475198923,39.6350970061053});
		q.add(p);
		p =  new Point("3", new double[]{115.57475198923,39.6350970061053});
		q.add(p);
		p =  new Point("4", new double[]{115.57475198923,39.6350970061053});
		q.add(p);
		ls = new LinearSearch(q, 10, 1000, 1);

	}
	
	
	
	public HeapSearch(ArrayList<Point> query, int k, int num, int model){
		this.query = query;
		this.heap = new RPQ();
		this.k = k;
		this.refPoint = new FindNearestPoint(query, num).getPoint();
		this.index = new BuildIndex(refPoint, 1, num);
		this.model = model;
		
		File flist = new File(clusterFilePath + "grid\\");
		String[] list = flist.list();
		
		for(String f : list){ 
			//System.out.println("current grid is " + f);
			if (getAggDistance(f) > bestSoFar){
				deepSearch(f);
			}else{
				//deepSearch(f);
			}
			
		}
		
		for (int i = 0; i < k; i++){
			PQElement r = heap.poll();
			System.out.println(r.id + " , " + r.distance);
		}
		
		System.out.println("IO is " + count);
		
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
				count++;
				String[] tr = lineWord.split(",");
				double[] p = new double[2];
				p[0] = Double.parseDouble(tr[1]);
				p[1] = Double.parseDouble(tr[2]);
				double distance = getAggDistance(p);
				if (bestSoFar < distance){
					pushToHeap(tr[0], distance);
					bestSoFar = heap.peek().distance;
					//best = tr[0];
				}else{
					break;
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
