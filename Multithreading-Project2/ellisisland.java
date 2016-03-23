/*
 * this class realize all the threads run normally
 */
public class ellisisland {
	public static int Num_seats= 6;
	public static int SeatsTook=0;
	public static int Num_session_day=2;
	public static int Num_visitors=15;
	public static int GroupSize=4;
    
	
	
	 public static void main(String args[]){
		 Clock c=new Clock();
		  
		  for(int i=0;i<15;i++){
			  new Visitor(i).start();
		  }
		  
		  Speaker s=new Speaker();
		  
		  c.start();
		  
		  s.start();
		  
		  
		   
		  
		 
	 
	 
}
}