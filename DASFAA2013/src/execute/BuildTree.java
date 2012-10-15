package execute;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.HashMap;

public class BuildTree {
	
	private String filePath = "C:\\Users\\WANG Haozhou\\Documents\\myUQ\\expData\\Dasfaa\\";
	private int[] clusterLevels = {2,4,5,10};
	private HashMap<String, String> clusterRelationship;
	
	
	
	
	public BuildTree(){
		for (int i = 0; i < clusterLevels.length -1; i++){
			clusterRelationship = new HashMap<String, String>();
			readCluster("c"+clusterLevels[i]);
			writeTree("", "c"+clusterLevels[i+1]);
		}
		
		
		
	}
	
	
	
	public static void main(String[] args) {
		BuildTree bt = new BuildTree();
		
	}
	
	public void readCluster(String pathName){
		BufferedReader reader = null;
		File file = new File(filePath + "\\" + pathName);
		String[] fileList = file.list();
		String lineWord;
		for (String f: fileList){
			file = new File(filePath + "\\" + pathName + "\\" + f);
			try {
				reader = new BufferedReader(new FileReader(file));
				while ((lineWord = reader.readLine()) != null){
					 String[] tr = lineWord.split(",");
					 clusterRelationship.put(tr[0], pathName +"\\" + f);
				}
			} catch (FileNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			
		}
		
		
	}
	
	public void writeTree(String fileName, String pathName){
		BufferedReader reader = null;
		File file = new File(filePath + "\\" + pathName);
		String[] fileList;
		String lineWord;
		
		try {
			fileList = file.list();
			FileWriter fw = new FileWriter(filePath + "tree", true);
			BufferedWriter bw = new BufferedWriter(fw);
			
	        for (String f : fileList){
	        	file = new File(filePath + "\\" + pathName + "\\" + f);
	        	reader = new BufferedReader(new FileReader(file));
	        	lineWord = reader.readLine();
	        	String[] tr = lineWord.split(",");
	        	String cluster = this.clusterRelationship.get(tr[0]);
	        	bw.write(cluster + "," + pathName + "\\" + f);
	        	bw.newLine();
				
	        }
	        bw.flush();
	        bw.close();
	        fw.close();
	        
	        
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
         
         
		
		
	}
	
	
	
	
	
	
	

}
