package algorithm;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

import refPoint.Point;;

public class FindNearestPoint {
	
	//double[] refPoint = {25,50,100,250,}
	private String clusterFilePath = "C:\\Users\\WANG Haozhou\\Documents\\myUQ\\expData\\Dasfaa\\";
	//private String clusterFilePath = "C:\\myUQ\\expData\\Dasfaa\\";
	private ArrayList<Point> query;
	private int refPointNum;
	private ArrayList<Point> refPoint;
	
	public FindNearestPoint(ArrayList<Point> query, int refPointNum){
		this.query = query;
		this.refPointNum = refPointNum;
		this.refPoint = new ArrayList<Point>();
		readFile("refPoints\\refPoint" + refPointNum);
	}
	
	public ArrayList<Point> getPoint(){
		ArrayList<Point> result = new ArrayList<Point>();
		for (Point q : query){
			double minDistance = Double.MAX_VALUE;
			Point r = null;
			for (Point p : refPoint){
				double tempDistance = getDistance(q.p, p.p);
				if (tempDistance < minDistance){
					minDistance = tempDistance;
					r = p;
				}
			}
			result.add(r);
		}
		
		
		
		return result;
	}
	
	
	public void readFile(String fileName){
		BufferedReader reader = null;
		
		String lineWord;
		try {
			reader = new BufferedReader(new FileReader(clusterFilePath + fileName));
			while ((lineWord = reader.readLine()) != null){
				 String[] tr = lineWord.split(",");
				 double[] temp = new double[2];
				 double tempX = Double.parseDouble(tr[1]);
				 double tempY = Double.parseDouble(tr[2]);
				 temp[0] = tempX;
				 temp[1] = tempY;
				 //int id = Integer.parseInt(tr[0]); 
				 Point p = new Point(tr[0], temp);
				 refPoint.add(p);
				 
			}
		} catch (NumberFormatException | IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
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
