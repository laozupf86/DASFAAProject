package algorithm;

import java.util.Comparator;
import java.util.PriorityQueue;

public class RPQ extends PriorityQueue<PQElement>{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public RPQ(){  
        super(1, new Comparator<PQElement>(){

			@Override
			public int compare(PQElement arg0, PQElement arg1) {
				// TODO Auto-generated method stub
				if( arg1.distance < arg0.distance)  
                    return 1 ;  
                else if(arg1.distance > arg0.distance){  
                    return -1;  
                }else{  
                    return 0;  
                }  
			}
        	
        });  
	}

}
