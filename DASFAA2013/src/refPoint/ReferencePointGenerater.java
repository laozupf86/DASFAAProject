package refPoint;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

public class ReferencePointGenerater {
	
	
	private double maxOfX = 117.5;
	private double maxOfY = 40.98;
	private double minOfX = 115.42;
	private double minOfY = 39.46;
	private String clusterFilePath = "C:\\Users\\WANG Haozhou\\Documents\\myUQ\\expData\\Dasfaa\\";
	//private String clusterFilePath = "C:\\myUQ\\expData\\Dasfaa\\";
	//private String clusterFilePath = "C:\\myUQ\\expData\\Dasfaa\\";
	private int numOfRefPoint;
	private double xRange, yRange;
	private double x, y;
	
	public ReferencePointGenerater(int x, int y){
		this.numOfRefPoint = x * y;
		this.xRange = (this.maxOfX - this.minOfX) / x;
		this.yRange = (this.maxOfY - this.minOfY) / y;
		this.x = x;
		this.y = y;
		writeToFile(numOfRefPoint);
		
	}
	
	
	public void writeToFile(int num){
		FileWriter outfile;
		double x = this.minOfX;
		double y = this.minOfY;
		try {
			outfile = new FileWriter(clusterFilePath + "refPoints\\refPoint" + num);
			BufferedWriter bw = new BufferedWriter(outfile);
			int c = 0;
			for (int i = 0; i < this.x; i++){
				for (int j = 0; j < this.y;j++ ){
					bw.write(c + "," + (x+this.xRange*i) + "," + (y+this.yRange*j));
		        	bw.newLine();
		        	c++;
				}
			}
				
	        	
			
			bw.flush();
			bw.close();
			outfile.close();
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	
	public static void main(String[] args) {
		ReferencePointGenerater rpg = new ReferencePointGenerater(5, 5);
		rpg = new ReferencePointGenerater(5, 10);
		rpg = new ReferencePointGenerater(5, 20);
		rpg = new ReferencePointGenerater(10, 15);
		rpg = new ReferencePointGenerater(10, 20);
		//rpg = new ReferencePointGenerater(25, 10);
		rpg = new ReferencePointGenerater(20, 20);
		rpg = new ReferencePointGenerater(20, 30);
		rpg = new ReferencePointGenerater(20, 40);
		rpg = new ReferencePointGenerater(20, 50);
		
		
		
		
		
	}

}
