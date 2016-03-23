import java.util.concurrent.Semaphore;

public class Visitor extends Thread{
     
	/*
	 * 1.mutex to protect count, to make sure there are just one visitor can control count,namely cs
	 * 2.getSeats to make sure there is limit number of visitors can get seats
	 * 3.listentopre1 and listentopre2 make sure visitors listen to presentation after presentation start
	 * 4.goTomuseum1 and goTomuseum2 to make sure visitors go to museum after movie 1 finished
	 * 5.count counts the number of seats that visitor took
	 * 6.mutex1 and mutex2 to make groups for visitors
	 * 7.groupCount is to count the proper number of visitors in a group 
	 * 8.group_NO is group1 ,group2,group3,group4
	 * 9.grouplock is to make sure all the visitor already in the museum,then grouping starts   
	 */
	 Semaphore mutex =new Semaphore(1);
	 static Semaphore getSeats=new Semaphore(0);
	 static Semaphore listenTopre1=new Semaphore(0);
	 static Semaphore listenTopre2=new Semaphore(0);
	 static Semaphore goTomuseum1=new Semaphore(0);
	 static Semaphore goTomuseum2=new Semaphore(0);
	 public static int count = 0;
	 static Semaphore mutex1=new Semaphore(1);
	 static Semaphore mutex2=new Semaphore(1);
	 static int groupCount;
	 static int group_NO=1;
	 static Semaphore grouplock=new Semaphore(0);
	 
 
	
	
	public Visitor(int id){
		 
		super("Visitor-"+id);
	}
	 
	
	public void run(){
		
		
		
		message("enter island and wait in the lobby");
		
		//wait in the lobby;
		
		 
		
		try {
		getSeats.acquire();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		/*
		 * use getQueueLength to divide visitors to different situations,here the visitors 
		 * who are in the first movie session
		 */	
		if(Visitor.getSeats.getQueueLength()<16&&Visitor.getSeats.getQueueLength()>8){
		try {
			mutex.acquire();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
 
		 
		count++;
		 
	    message("   took a  seat");  
	    mutex.release();
	    
	    
	    try {
			listenTopre1.acquire();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	    message("listening to presentation..");
	     
	    
	    
	    
	   try {
		Clock.movie1.acquire();
	} catch (InterruptedException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	} 
	   
	   
	   message("watching movie");
	    
	   try {
		goTomuseum1.acquire();
	} catch (InterruptedException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	} 
	   message(" go to musueum");
	    
	    
	    
	    
	    
		}
		/*
		 * 
		 * the visitors who are in the second movie session
		 */
		else if(Visitor.getSeats.getQueueLength()<9&&Visitor.getSeats.getQueueLength()>2){
			
			
			try {
				mutex.acquire();
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	 
			 
			count++;
			 
		    message("   took a  seat");  
		    mutex.release();
		
		    
		    try {
				listenTopre2.acquire();
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		    message("listening to presentation..");
		     
		    
		    
		    
		   try {
			Clock.movie2.acquire();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
		   
		   
		   message("watching movie");
		    
		   try {
			goTomuseum2.acquire();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
		   message(" go to musueum");
		   try {
			sleep(30);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		   grouplock.release(16);
	 
		    
		   
			
			
 		}
		/*
		 * visitor who is in the first position standing in the waiting line
		 */
		
		
		else if(Visitor.getSeats.getQueueLength()<3&&Visitor.getSeats.getQueueLength()>1){
 			message("tell next one no more movie seesion today.");
 			Visitor.getSeats.release();
 			message("go to museum");
 		}
		/*
		 * visitor who is in the second position standing in the waiting line
		 */
 		else if(Visitor.getSeats.getQueueLength()<2&&Visitor.getSeats.getQueueLength()>0){
 			message("tell next one no more movie seesion today.");
 			Visitor.getSeats.release();
 			message("go to museum");
 			 
 		}
 		/*
 		 * visitor who is in the last position standing in the waiting line
 		 */
 		else
 		{   
 			message("go to museum");
 			 
 			Visitor.getSeats.release();
 			Clock.finishInform.release();  //speaker can begin presentation
 		}
		
		
		
       try {
		grouplock.acquire();
	} catch (InterruptedException e1) {
		// TODO Auto-generated catch block
		e1.printStackTrace();
	}
       
       /*
        * grouping 
        */
       
 	    try {
			Visitor.mutex1.acquire();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
 	    groupCount++;
 	    
 	    if(groupCount%ellisisland.GroupSize!=0&&groupCount<15){
 	    	
			int group=group_NO;
 	    	Visitor.mutex1.release();
 	    	try {
				Visitor.mutex2.acquire();
				 
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
 	    	message("leaving with group"+group);
 	    	
 	    }else
 	    {
 	    	message("leavnig with group"+group_NO);
 	    	group_NO++;
 	    	Visitor.mutex1.release();
 	    	for(int i=1;i<ellisisland.GroupSize;i++){
 	    		Visitor.mutex2.release();
 	    		
 	    	}
 	    }
 	    
 
		Speaker.speakerleave.release();
		
		
		
		
		
		
		
		
		
		
     	 
     
 		 
 	    
 	   
     }
		
		
 
	 
	
	
	
	public void message(String m){
		System.out.println("["+Clock.age()+"]"+getName()+":"+m);
		
	}
	
	
	
	 
	 
		
	}
	 
 