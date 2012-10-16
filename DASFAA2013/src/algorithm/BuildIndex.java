package algorithm;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

import refPoint.Point;

public class BuildIndex {
	
	private HashMap<String, HashMap<String, Double>> index;
	private String clusterFilePath = "C:\\Users\\WANG Haozhou\\Documents\\myUQ\\expData\\Dasfaa\\";
	
	public BuildIndex(ArrayList<Point> refPoint, int model){
		this.index = new HashMap<String, HashMap<String, Double>>();
		 
	}
	
	
	private void buildForGrid(ArrayList<Point> refPoint, int num){
		//File flist = new File(clusterFilePath + "grid\\");
		//String[] list = flist.list();
		BufferedReader reader = null;
		String lineWord;
		
		try {
			reader = new BufferedReader(new FileReader(clusterFilePath + "griddistance//refPoint" + num));
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
