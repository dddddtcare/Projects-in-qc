/*
 * this class contains the main method that makes all 
 * the threads runs and wake up the speaker when the movie
 * session starts.
 */
public class EllisIsland {
 
	
	 
	static int numVisitor = 15;
	static volatile int numSeats1 = 6;
	static volatile int numSeats2 = 6;
	static volatile int size = 6;
	
	public static void main(String[] args) {
		
		Object obj = new Object();
		Clock clock = new Clock("Clock");
		clock.start();
		Speaker speak = new Speaker("Speaker");
		speak.start();
		try {
			Thread.sleep(10);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		Visitor[] vs = new Visitor[numVisitor];
		for(int i = 0;i<numVisitor; i++){
			vs[i] =  new Visitor("Visitor "+i, obj);
		}
		for(int i = 0;i<numVisitor; i++){
			vs[i].start();
		}
		
		try {
			Thread.sleep(10);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		/*
		 * busy waiting
		 */
		while(Clock.groupingDone!=true){  }
		while(Clock.startPresentation!=true){  }
		/*
		 * interrupt the speaker to give presentation
		 */
		speak.interrupt();// speaker announce the first Movie
		try {
			Thread.sleep(15000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		while(Clock.allLeftTheatre!=true){}
 
		speak.interrupt();// speaker announce the second Movie
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		try {
			speak.join();
			clock.join();
			for(int i = numVisitor-1;i>0; i--){
				if(vs[i].isAlive())
				vs[i].join();
			}
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
}