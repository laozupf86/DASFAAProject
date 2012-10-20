package refPoint;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

public class CalculateDistanceForHCL {
	//private static String clusterFilePath = "C:\\Users\\WANG Haozhou\\Documents\\myUQ\\expData\\Dasfaa\\";
	private static String clusterFilePath = "C:\\myUQ\\expData\\Dasfaa\\";
	
	
	public CalculateDistanceForHCL(){
		
	}
	
	
	public static void main(String[] args) {
		File f = new File(clusterFilePath + "refPoints\\");
		//File pf = new File(clusterFilePath + "grid\\");
		String[] list = f.list();
		for (int i = 1; i < 7; i++){
			File pf = new File(clusterFilePath + "HCL\\L" + i);
			String[] pointList = pf.list();
			CalculateDistanceForHCL cd; 
			for (String s : list){
				
				cd = new CalculateDistanceForHCL();
				cd.readFile(s, pointList, i);
				
				
			}
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
	
	public void readFile(String refPointFileName, String[] pointFileName, int level){
		BufferedReader poiReader = null;
		BufferedReader refPointReader = null;
		String lineWord;
		String refPoint;
		ArrayList<Point> poi = new ArrayList<Point>();
		ArrayList<Point> tempPoint; 
		try {
			
			refPointReader = new BufferedReader(new FileReader(clusterFilePath + "refPoints\\" + refPointFileName));
			while (( refPoint = refPointReader.readLine()) != null){
				 String[] tr = refPoint.split(",");
				 double[] temp = new double[2];
				 double tempX = Double.parseDouble(tr[1]);
				 double tempY = Double.parseDouble(tr[2]);
				 temp[0] = tempX;
				 temp[1] = tempY;
				 //int id = Integer.parseInt(tr[0]);
				 
				 Point p = new Point(tr[0], temp);
				 poi.add(p);
				 
				 
				 
			}
			FileWriter outfile;
			outfile = new FileWriter(clusterFilePath + "HCLdistance\\" + refPointFileName, true);
			BufferedWriter bw = new BufferedWriter(outfile);
			
			for (String name : pointFileName){
				//poiReader = new BufferedReader(new FileReader(clusterFilePath + "grid\\" + name));
				poiReader = new BufferedReader(new FileReader(clusterFilePath + "HCL\\L" + level + "\\" + name));
				tempPoint = new ArrayList<Point>();
				while((lineWord = poiReader.readLine()) != null){
					 String[] p = lineWord.split(",");
					 double rx = Double.parseDouble(p[1]); 
					 double ry = Double.parseDouble(p[2]);
					 double[] rp = new double[2];
					 rp[0] = rx;
					 rp[1] = ry;
					 Point po = new Point(p[0], rp);
					 tempPoint.add(po);
					 
					
					 
				}
				
				
				 for (Point point : poi){
					 
					 double max = 0;				 
					 for (Point pp : tempPoint){
						 double distance = getDistance(point.p, pp.p);
						 if (distance > max){
							 max = distance;
						 }
					 }				 
					 bw.write(point.id + ",L" + level + "\\" + name + "," + max);
					 bw.newLine(); 
				}
				
				
			}
			
			bw.flush();
			bw.close();
			System.out.println("calculation finish");
			
		} catch (NumberFormatException | IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
}
