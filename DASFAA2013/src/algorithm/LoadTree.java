package algorithm;

import hcl.DataPoint;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

public class LoadTree {
	
	HashMap<String, ArrayList<String>> treeindex;
	private String clusterFilePath = "C:\\Users\\WANG Haozhou\\Documents\\myUQ\\expData\\Dasfaa\\";
	//private String clusterFilePath = "C:\\myUQ\\expData\\Dasfaa\\";
	
	public LoadTree(String fileName){
		buildIndex(fileName);
	}
	
	private void buildIndex(String fileName){
		BufferedReader reader = null;		
		String lineWord;
		
		try {
			reader = new BufferedReader(new FileReader(clusterFilePath + fileName));
			while ((lineWord = reader.readLine()) != null){
				 String[] tr = lineWord.split(",");
				 if (treeindex.containsKey(tr[0])){
					 treeindex.get(tr[0]).add(tr[1]);
				 }else{
					 treeindex.put(tr[0], new ArrayList<String>());
					 treeindex.get(tr[0]).add(tr[1]);
				 }
				 			 
			}
		} catch (NumberFormatException | IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public ArrayList<String> getChildren(String key){
		if (this.treeindex.containsKey(key)){
			return this.treeindex.get(key);
		}else{
			System.out.println("invaild key");
			return null;
		}
	}

}
