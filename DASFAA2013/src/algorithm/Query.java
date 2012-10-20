package algorithm;

import java.util.ArrayList;

import refPoint.Point;

public class Query {
	
	private ArrayList<Point> query;
	
	public Query(int num){
		ArrayList<Point> q = new ArrayList<Point>();
		
		Point p = new Point("1", new double[]{115.628,40.87866666666666});
		q.add(p);
		p =  new Point("2", new double[]{115.836,40.422666666666665});
		q.add(p);
		p =  new Point("3", new double[]{116.148,40.827999999999996});
		q.add(p);
		p =  new Point("4", new double[]{115.836,39.96666666666667});
		q.add(p);
		p =  new Point("5", new double[]{116.252,39.96666666666667});
		q.add(p);
		p = new Point("6", new double[]{116.46000000000001,39.86533333333333});
		q.add(p);
		p =  new Point("7", new double[]{116.168,39.916});
		q.add(p);
		p =  new Point("8", new double[]{117.005610955059,39.8850970061053});
		q.add(p);
		p =  new Point("9", new double[]{117.00265201254,40.202183011384});
		q.add(p);
		p =  new Point("10", new double[]{116.203557038151,39.96977019899});
		q.add(p);
		p = new Point("11", new double[]{115.90475198923,39.9950970061053});
		q.add(p);
		p =  new Point("12", new double[]{115.908992981429,40.102183011384});
		q.add(p);
		p =  new Point("13", new double[]{116.95610955059,39.8350970061053});
		q.add(p);
		p =  new Point("14", new double[]{116.95265201254,40.22183011384});
		q.add(p);
		p =  new Point("15", new double[]{116.513557038151,39.866977019899});
		q.add(p);
		p = new Point("16", new double[]{115.57475198923,39.6350970061053});
		q.add(p);
		p =  new Point("17", new double[]{115.588992981429,40.632183011384});
		q.add(p);
		p =  new Point("18", new double[]{117.165610955059,39.6350970061053});
		q.add(p);
		p =  new Point("19", new double[]{117.38265201254,40.632183011384});
		q.add(p);
		p =  new Point("20", new double[]{116.513557038151,39.866977019899});
		q.add(p);
		
		this.query = new ArrayList<Point>();
		for (int i = 0; i < num; i++){
			query.add(q.get(i));
		}
		
	}
	
	public ArrayList<Point> getQueryPoint(){
		return this.query;
	}

}
