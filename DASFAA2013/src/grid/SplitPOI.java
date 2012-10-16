package grid;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

public class SplitPOI {
	
	
	private String clusterFilePath = "C:\\Users\\WANG Haozhou\\Documents\\myUQ\\expData\\Dasfaa\\";
	private double maxOfX = 117.5;
	private double maxOfY = 40.98;
	private double minOfX = 115.42;
	private double minOfY = 39.46;
	private double offsetX, offsetY;
	private HashMap<String, ArrayList<String>> grid;
	private int num;
	
	
	public SplitPOI(int num){
		this.grid = new HashMap<String, ArrayList<String>>();
		this.offsetX = (maxOfX - minOfX)/num;
		this.offsetY = (maxOfY - minOfY)/num;
		for (int i = 0; i < num; i++){
			for (int j = 0; j < num; j++){
				grid.put(i + "," + j, new ArrayList<String>());
			}
		}
		this.num = num;
		
	}
	
	public void wirteFile(String path){
		
		for (int i = 0; i < num; i++){
			for (int j = 0; j < num; j++){
				FileWriter outfile;
				ArrayList<String> out = this.grid.get(i + "," + j); 
				try {
					outfile = new FileWriter(clusterFilePath + path + "grid" + i + "-" + j);
					BufferedWriter bw = new BufferedWriter(outfile);
					for (String line : out){
						bw.write(line);
			        	bw.newLine();
					}
					
					bw.flush();
					bw.close();
					outfile.close();
					
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
		
		
		
	}
	
	public void readFile(String filename){
		
		BufferedReader reader = null;
		
		String lineWord;
		try {
			reader = new BufferedReader(new FileReader(clusterFilePath + filename));
			while ((lineWord = reader.readLine()) != null){
				 String[] tr = lineWord.split(",");
				 int tempX = (int) ((Double.parseDouble(tr[1]) - this.minOfX)/this.offsetX);
				 int tempY = (int) ((Double.parseDouble(tr[2]) - this.minOfY)/this.offsetY);
				 if (this.grid.containsKey(tempX + "," + tempY)){
					 this.grid.get(tempX + "," + tempY).add(lineWord);
				 }else{
					 System.out.println("Error, x is " + tempX + " y is  " + tempY);
				 }
				 
			}
		} catch (NumberFormatException | IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	

	/**
	 * @param args
	 */
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		SplitPOI sp = new SplitPOI(10);
		sp.readFile("points");
		sp.wirteFile("grid\\");
	}

}
