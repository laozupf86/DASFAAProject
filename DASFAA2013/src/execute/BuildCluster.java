package execute;

import hcl.Cluster;
import hcl.ClusterAnalysis;
import hcl.DataPoint;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class BuildCluster {

	/**
	 * @param args
	 */
	
	private ArrayList<DataPoint> dpoints;
	private String poiFilePath = "";
	//private String clusterFilePath = "C:\\Users\\WANG Haozhou\\Documents\\myUQ\\expData\\Dasfaa\\";
	private String clusterFilePath = "C:\\myUQ\\expData\\Dasfaa\\";
	
	
	public BuildCluster(){
		
		
	}
	
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		int[] numofClusters = {10,5,4,2};
		System.out.println("start reading");
		BuildCluster bc = new BuildCluster();
		System.out.println("reading finish");
	    ClusterAnalysis HCL =new ClusterAnalysis();
	    System.out.println("reading clustering");
	    for (int i = 0; i < numofClusters.length; i++){
	    	List<Cluster> clusters = HCL.startAnalysis(bc.getPoints(), numofClusters[i]); 
		    System.out.println("clustering finish, write to file");
		    bc.writeToFile(clusters, numofClusters[i]);
	    }
	    System.out.println("all done!");
	}
	
	public ArrayList<DataPoint> getPoints(){
		return this.dpoints;
	}
	
	public void writeToFile(List<Cluster> clusters, int num){
		String foldPath = "c" + num;
		File fold = new File(clusterFilePath + foldPath);
		if (!fold.exists()){
			fold.mkdir();
		}
		
		
		for(Cluster cl:clusters){
			String filename = "l-" + num + "_c_" + cl.getClusterName();
			try {
				FileWriter fw = new FileWriter(clusterFilePath + foldPath + "\\" + filename);
				BufferedWriter bw = new BufferedWriter(fw);
				List<DataPoint> tempDps = cl.getDataPoints();
				for(DataPoint tempdp : tempDps){
					bw.write(tempdp.getDataPointName() + "," + tempdp.getDimensioin()[0] + "," +tempdp.getDimensioin()[1]);
					bw.newLine();
				}
				bw.flush();
				bw.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	           
	    }
	 
		
	}
	
	
	
	
	
	
	
	

}
