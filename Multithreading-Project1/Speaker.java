/*
 * this class realize the speaker threads
 */
public class Speaker extends Thread {

	private String name;
	public String getName2() {
		return name;
	}
	
	public Speaker(String name) {
		this.name = name;
	}
 /*
  * (non-Javadoc)
  * @see java.lang.Thread#run()
  * make speaker sleep enough time ,and use the time to 
  * interrupt it to give presentation.
  */
	public  void run(){
		try {
			Thread.sleep(1000000);
		} catch (InterruptedException e) {
			message("Speaker provides a presentation");
			try {
				Thread.sleep(500);
			} catch (InterruptedException e1) {
				e1.printStackTrace();
			}
			message("showing time: let's seeing the movie");
			//if the movie 1 is done, set the flag to true
			Clock.doneFor1Movie = true;
			
		}
 
		try {
			Thread.sleep(1000000);
		} catch (InterruptedException e) {
			message("have a 15 minutes' break between the movie");
		 
			message("Speaker provides a presentation");
			try {
				Thread.sleep(500);
			} catch (InterruptedException e1) {
				e1.printStackTrace();
			}
			message("showing time: let's watch the movie");
			//if the movie 2 is done,set the flag to true
			Clock.doneFor2Movie = true;
		}
	}
	
	public  void message (String m) {
		System.out.println ("[" + Clock.age() + "] " + getName2() + " : " + m);
	}
	public static long age() {
		return System.currentTimeMillis() - Clock.startTime;
	}
}
