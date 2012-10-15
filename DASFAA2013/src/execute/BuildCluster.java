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
	private String clusterFilePath = "C:\\Users\\WANG Haozhou\\Documents\\myUQ\\expData\\Dasfaa\\";
	
	
	public BuildCluster(){
		dpoints = new ArrayList<DataPoint>();
		   double[] a={2,3};
	       double[] b={2,4};
	       double[] c={1,4};
	       double[] d={1,3};
	       double[] e={2,2};
	       double[] f={3,2};

	       double[] g={8,7};
	       double[] h={8,6};
	       double[] i={7,7};
	       double[] j={7,6};
	       double[] k={8,5};

//	       double[] l={100,2};//¹ÂÁ¢µã


	       double[] m={8,20};
	       double[] n={8,19};
	       double[] o={7,18};
	       double[] p={7,17};
	       double[] q={8,20};

	       dpoints.add(new DataPoint(a,"a"));
	       dpoints.add(new DataPoint(b,"b"));
	       dpoints.add(new DataPoint(c,"c"));
	       dpoints.add(new DataPoint(d,"d"));
	       dpoints.add(new DataPoint(e,"e"));
	       dpoints.add(new DataPoint(f,"f"));

	       dpoints.add(new DataPoint(g,"g"));
	       dpoints.add(new DataPoint(h,"h"));
	       dpoints.add(new DataPoint(i,"i"));
	       dpoints.add(new DataPoint(j,"j"));
	       dpoints.add(new DataPoint(k,"k"));

//	       dataPoints.add(new DataPoint(l,"l"));

	       dpoints.add(new DataPoint(m,"m"));
	       dpoints.add(new DataPoint(n,"n"));
	       dpoints.add(new DataPoint(o,"o"));
	       dpoints.add(new DataPoint(p,"p"));
	       dpoints.add(new DataPoint(q,"q"));
		
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
