package algorithm;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

import refPoint.Point;

public class BuildIndex {
	
	private HashMap<String, Double> index;
	//private String clusterFilePath = "C:\\Users\\WANG Haozhou\\Documents\\myUQ\\expData\\Dasfaa\\";
	private String clusterFilePath = "C:\\myUQ\\expData\\Dasfaa\\";
	
	public BuildIndex(ArrayList<Point> refPoint, int model, int num){
		this.index = new HashMap<String, Double>();
		if (model == 1){
			buildForGrid(refPoint, num);
		}else if (model == 2){
			
		}else{
			
		}
		 
	}
	
	
	private void buildForGrid(ArrayList<Point> refPoint, int num){
		//File flist = new File(clusterFilePath + "grid\\");
		//String[] list = flist.list();
		BufferedReader reader = null;
		String lineWord;
		HashMap<String, Integer> rp = new HashMap<String, Integer>();
		for (Point tp : refPoint){
			rp.put(tp.id, 1);
		}
		try {
			reader = new BufferedReader(new FileReader(clusterFilePath + "griddistance//refPoint" + num));
			while ((lineWord = reader.readLine()) != null){
				 String[] tr = lineWord.split(",");
				 if (rp.containsKey(tr[0])){
					 index.put(tr[0] + "," + tr[1], Double.parseDouble(tr[2]));
				 }
				 
			}
		} catch (NumberFormatException | IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	
	private void buildForHCL(ArrayList<Point> refPoint, int num){
		BufferedReader reader = null;
		String lineWord;
		HashMap<String, Integer> rp = new HashMap<String, Integer>();
		for (Point tp : refPoint){
			rp.put(tp.id, 1);
		}
		try {
			reader = new BufferedReader(new FileReader(clusterFilePath + "hcldistance//refPoint" + num));
			while ((lineWord = reader.readLine()) != null){
				 String[] tr = lineWord.split(",");
				 if (rp.containsKey(tr[0])){
					 index.put(tr[0] + "," + tr[1], Double.parseDouble(tr[2]));
				 }
				 
			}
		} catch (NumberFormatException | IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public Double getDistance(String key){
		if (index.containsKey(key)){
			return index.get(key);
		}else{
			System.out.println("invailed key");
			return -1.0;
		}
	}
	

}
