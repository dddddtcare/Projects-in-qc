import java.util.concurrent.Semaphore;

public class Speaker extends Thread {
	/*
	 * 1.presentation1 is a binary Semaphore that makes sure the first presentation starts after 
	 *   visitors fill in the theater.
	 * 2.endPre1 is a binary Semaphore that makes sure the first movie starts after first presentation ends.
	 * 3.endPre2 is same as endPre2,it makes sure the second movie starts after second presentation ends.
	 * 4.speakerLeave is a binary Semaphore that makes sure speaker leave island after all the visitors leave.
	 * 5.speakerBefore is to make sure that speaker leave before the day ends.
	 */
       static Semaphore presentation1 =new Semaphore(0);
       static Semaphore endPre1=new Semaphore(0);
       static Semaphore endPre2=new Semaphore(0);
       static Semaphore speakerleave=new Semaphore(0);
       static Semaphore speakerBefore=new Semaphore(0);
        
	 
	 
	
	public Speaker( ) {   //constructor
		 super("speaker");
	}

	public  void run(){
		
		//use semaphore presentation1 lock the the first presentation 
		     try {
				presentation1.acquire();
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		     
		     
			 message("starts first presentation");
			 
		 //let visitors who attend first movie session listening to 
		 // first presentation 
			 Visitor.listenTopre1.release(6);
			 try {
				sleep(50);
			} catch (InterruptedException e2) {
				// TODO Auto-generated catch block
				e2.printStackTrace();
			}
			 
			  
			 
			 
			 
			 message("finish first presentation");
			 endPre1.release(); //use endPre1 mark that the first presentation ends,
			                    //and movie can starts now
			
			 
			 try {
				sleep(50);
			} catch (InterruptedException e2) {
				// TODO Auto-generated catch block
				e2.printStackTrace();
			}
			 /*
			  * speaker should inform the people who is in line that
			  * there is no more movie session after visitors fill in
			  * the second session theater
			  */
			 try {
				Clock.inform.acquire();
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			 
			
    		 message("there is no more movie session today");
    		 Visitor.getSeats.release();//inform a visitor in line
    		  
    		 
    		 
    		 /*
    		  * speaker start second presentation after 
    		  * he get signal that the people in line 
    		  * already all get information that no 
    		  * more movie session today.
    		  */
			 try {
				Clock.finishInform.acquire();
			} catch (InterruptedException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			 message("speaker starts second presentation");
		     
			 /*
			  * same as listenTopre1, here to make sure 
			  * visitors listen to second presentation 
			  * after presentation starts
			  */
			 Visitor.listenTopre2.release(6);
			 try {
				sleep(50);
			} catch (InterruptedException e2) {
				// TODO Auto-generated catch block
				e2.printStackTrace();
			}
			 
			 
			 message("speaker finish second presentation");
			 endPre2.release();
			 try {
				sleep(10);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			 message("watching movie...");//speaker also watch movie after presentation ends
			 
			 /*
			  * speaker leave island after 15 visitor leave
			  */
			 try {
				speakerleave.acquire(15);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			 message("leaving island");
			 speakerBefore.release(); //make sure speaker leave before day ends
		    
		 
		}
	 
	public  void message (String m) {
		System.out.println ("[" + Clock.age() + "] " + getName() + " : " + m);
	}
	public static long age() {
		return System.currentTimeMillis() - Clock.startTime;
	}
}
