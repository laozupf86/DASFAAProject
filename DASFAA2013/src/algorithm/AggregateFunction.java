package algorithm;

import java.util.ArrayList;

public class AggregateFunction {
	
	private int model = 0;
	private double distance = -1;
	ArrayList<Double> point;
	
	
	public AggregateFunction(int model, ArrayList<Double> points){
		this.model = model;
		this.point = points;
		if (this.model == 1){
			distance = getSum();
		}else{
			distance = getMin();
		}
		
	}
	
	
	public double getDistance(){
		return this.distance;
	}
	
	private double getSum(){
		double max = 0;
		for(double d : this.point){
			max = max + d;
		}
		
		return max;
		
	}
	
	private double getMin(){
		double max = Double.MAX_VALUE;
		for(double d : this.point){
			if (d < max ){
				max = d;
			}
		}
		
		return max; 
	}
	
	

}
