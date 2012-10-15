package treebuild;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class TreeNode {
	
	private HashMap<String, TreeNode> children;
	private String id;
	private boolean isLeaf;
	private TreeNode parent;
	private int level;
	private List<String> data;
	private double maxDistance;
	
	public TreeNode(String id, boolean isLeaf, TreeNode parent, int level, double maxDistance){
		this.id = id;
		this.isLeaf = isLeaf;
		this.parent = parent;
		this.level = level;
		this.children = new HashMap<String, TreeNode>();
		if (isLeaf){
			this.data = new ArrayList<String>();
		}else{
			this.data = null;
		}
		this.maxDistance = maxDistance;
	}
	
	
	public void addChild(String name, TreeNode child){
		 this.children.put(name,child);
	}
	
	public HashMap<String, TreeNode> getChildren(){
		return this.children;
	}
	
	public List<TreeNode> getChildrenList(){
		List<TreeNode> children = new ArrayList<TreeNode>();
		for (Object tn : this.children.keySet().toArray()){
			children.add((TreeNode)tn);
		}
		
		return children;
	}
	
	public TreeNode getParent(){
		return this.parent;
	}
	
	public boolean checkLeaf(){
		return this.isLeaf;
	}
	
	public int getLevel(){
		return this.level;
	}
	
	public List<String> getData(){
		return this.data;
	}
	
	public double getMaxDistance(){
		return this.maxDistance;
	}
	

}
