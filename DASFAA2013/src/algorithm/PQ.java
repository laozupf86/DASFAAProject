package algorithm;

import java.util.Comparator;
import java.util.PriorityQueue;

public class PQ extends PriorityQueue<Double>{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public PQ(){  
        super(1, new Comparator<Double>(){

			@Override
			public int compare(Double arg0, Double arg1) {
				// TODO Auto-generated method stub
				if( arg1 > arg0)  
                    return 1 ;  
                else if(arg1 < arg0){  
                    return -1;  
                }else{  
                    return 0;  
                }  
			}
        	
        });  
	}

}
