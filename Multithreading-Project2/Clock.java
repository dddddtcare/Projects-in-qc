import java.util.concurrent.Semaphore;

public class Clock extends Thread {
	/*
	 * 1.movie1 is to make sure that visitors can watch movie 
	 *   after clock signal that movie already starts.
	 * 2.movie2 is same as movie that make sure visitors can 
	 *   watch movie after clock signal that movie already starts.
	 * 3.semaphore inform is to make sure speaker inform one of the visitor
	 *   in line that no more movie session after visitor fill in theater
	 * 4.semaphore finishInform is to make sure the second presentation
	 *   can starts after all the visitor know that no more session today
	 */
	public final static long startTime = System.currentTimeMillis();
	static Semaphore movie1=new Semaphore(0);
	static Semaphore movie2=new Semaphore(0);
	static Semaphore inform=new Semaphore(0);
	static Semaphore finishInform=new Semaphore(0);
	 
	
	 
	
	 
	public Clock( ){
		 super("clock"); //constructor
	}
	
	 
	public void run(){
		 
		
		
	    message("visitors can check session 1 seats now");
	    Visitor.getSeats.release(6);//let only 6 visitors can get seats
	    try {
			sleep(30);
		} catch (InterruptedException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
	    if(Visitor.count==6){
	    	Speaker.presentation1.release(); // speaker starts presentation after visitors
	    	                                 // fill in theater
		}
	    
	    
			  
		message("speaker enters room");
		 
		 
		
		
		try {                                 //after speaker signal that presentation ends,
			                                  //movie one can starts
			Speaker.endPre1.acquire();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		message("movie one starts...");
		 Clock.movie1.release(6);      //movie1 allows only 6 visitor to watch  movie.
		 
		

		
		try {
			sleep(1000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		message("movie one finished...");
		Visitor.goTomuseum1.release(6);  //allow only 6 visitor go to museum 
		  
		message("break 15 min...");
		try {
			sleep(500);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
      		
		message("visitors can check session 2 seats now");
		Visitor.getSeats.release(6);
		try {
			sleep(50);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		message("speaker enters room and find no more available seats");
		inform.release();
		
		
		
  
 		 
		
		
		try {
			Speaker.endPre2.acquire();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
 		message("movie two starts...");        
 		movie2.release(6);
 		
 		try {
			sleep(1000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		message("movie two finished...");
		Visitor.goTomuseum2.release(6);
		
		
        try {
			sleep(10);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
 	    message("museum is closing,please leave...");
 	    
 	    /*
 	     * use semaphore speakerBefore to lock 
 	     * the day ends, in other words, speaker need
 	     * to leave before day ends
 	     */
 
        try {
			Speaker.speakerBefore.acquire();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
 	    message("the day ends");
 	 
		 
		 
		 
		 
		 
		 
	}
	 
	public  void message (String m) {
		System.out.println ("[" + Clock.age() + "] " + getName() + " : " + m);
	}
	public static long age() {
		return System.currentTimeMillis() - startTime;
	}
}