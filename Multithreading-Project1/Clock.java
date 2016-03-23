/*
 * this class realize that a thread that can 
 * signal the time during the whole movie session
 */
public class Clock extends Thread {
	public final static long startTime = System.currentTimeMillis();
	volatile static boolean firstSessionReady = false;
	volatile static boolean startPresentation = false;
	volatile static boolean group1Ready = false;
	volatile static boolean group2Ready = false;
	volatile static boolean groupingDone = false;
	volatile static boolean doneFor1Movie = false;
	volatile static boolean doneFor2Movie = false;
	volatile static boolean allLeftTheatre = false;
	volatile static boolean breakBetweenMovies = false;
	private String name;
	public Clock(String name){  //constructor
		this.name = name;
	}
	
	public String getName2() {
		return name;
	}
   /*
    * (non-Javadoc)
    * @see java.lang.Thread#run()
    * if the visitor satisfy 6 person , session 1 starts
    */
	public void run(){
		 while(true){
			 if(Visitor.count == EllisIsland.numVisitor){
				 message("Clock sets session 1 and notifies all visitor to enter the room");
				 Clock.firstSessionReady = true;
				 break;
			 }
		 }
	}
	 
	public  void message (String m) {
		System.out.println ("[" + Clock.age() + "] " + getName2() + " : " + m);
	}
	
	public static long age() {
		return System.currentTimeMillis() - startTime;
	}
}
