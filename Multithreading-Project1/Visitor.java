/*
 * this class realize the visitor threads
 */
public class Visitor extends Thread{
	private Object obj;
	private String name;
	static byte count = 0; 
	public Visitor(String name, Object obj){
		this.name = name;
		this.obj = obj;
	}
	public String getName2() {
		return name;
	}
	 
	public synchronized void run(){
		count++;  //a counter that count for the number of people
		          //who arrive the island
		message("arrives Ellis Island and waits in the lobby."+ count);
		/*
		 * the while loop realize busy waiting, if the session1 is
		 * not ready , it will be in the while loop forever
		 */
		while((Clock.firstSessionReady != true)){	}
		
		if((Clock.group1Ready != true)&&(Clock.group2Ready != true)){
			grouping1(obj);
			//group1 is ready then do presentation and watching movie
			while(Clock.group1Ready !=true)	{ }
			if (Clock.group1Ready == true) {
				Clock.startPresentation = true;
			}
			while(Clock.doneFor1Movie!=true){}
			
			leftTheatre(obj);
		}
		else if((Clock.group1Ready ==true)&&(Clock.group2Ready != true)){
			//group2 is ready then do presentation and watching movie
			// setPriority(getPriority()+1);    
			
			setPriority(getPriority()+1);
			grouping2(obj);
			setPriority(getPriority()+1);
			
			while(Clock.group2Ready !=true)	{ }
			if (Clock.group2Ready == true) {
				Clock.startPresentation = true;
			}
			while(Clock.doneFor2Movie!=true){}
			leftTheatre(obj);
		}
		else if((Clock.group1Ready ==true)&&(Clock.group2Ready == true)){
			//no more movie available for the rest just have a visiting
			message("no more movie available just have a visiting");
			if(count==EllisIsland.numVisitor){
				Clock.groupingDone = true;
			}
			yield();  //if people who did not see movie today ,give up
		}
			
	}
	/*
	 * the method that realize the people who finish movie and leaving 
	 * the theater
	 */
	public  void leftTheatre(Object obj){
		synchronized(obj){
			if(EllisIsland.numSeats1<=EllisIsland.size){
				EllisIsland.numSeats1++;
				if(EllisIsland.numSeats1==EllisIsland.size)
					Clock.allLeftTheatre = true;
			}
			message("having movie done and left for visiting Museum");
		}
	}
	/*
	 * the method that make people who can count to group 2 and see movie
	 */
	public void grouping2(Object obj){
		synchronized(obj){
			if(EllisIsland.numSeats2>0){
				message("grouping for Second group to watch movie: "+ EllisIsland.numSeats2);
				EllisIsland.numSeats2--;
				if(EllisIsland.numSeats2==0){
					Clock.group2Ready = true; //set the group2 ready to see movie
				}
			}
		}
	}
	/*
	 * the method that make people who can count to group 1 and see movie together
	 */
	public void grouping1(Object obj){
		synchronized(obj){
			if(EllisIsland.numSeats1>0){
				message("grouping for First group to watch movie: "+ EllisIsland.numSeats1);
				EllisIsland.numSeats1--;
				if(EllisIsland.numSeats1==0){
					Clock.group1Ready = true;// set the group 1 ready to see movie
				}
			}
		}
	}
	
	public  void message (String m) {
		System.out.println ("[" + Clock.age() + "] " + getName2() + " : " + m);
	}
	public static long age() {
		return System.currentTimeMillis() - Clock.startTime;
	}
}