/* 
Represents a time in hour:minute. For example, 8:52 is 8:52 AM and 15:4 is 3:04 PM.
*/

@javax.jdo.annotations.PersistenceCapable

public class Time
{

	int hour; // 0 -- 23
	int minute; // 0 -- 59

	public int differenceFrom(Time earlierTime)

	/* Returns the time difference in minutes between "earlierTime"
           and the target time object, with "earlierTime" always regarded as 
	   the earlier time. Note that the time interval may include midnight.
           For example, suppose the target object represents 1:30. Then:

           if earlierTime = 23:30, the function will return 120 (minutes);
           if earlierTime = 0:30, the function will return 60 (minutes);
	   if earlierTime = 9:30, the function will return 960 (minutes), etc. */

	{
		  int timeDifference = 0;
		  
		  if((this.hour>earlierTime.hour)&&(this.minute>earlierTime.minute)){
			  timeDifference=(this.hour-earlierTime.hour)*60+(this.minute-earlierTime.minute);
		  }else if((this.hour>earlierTime.hour)&&(this.minute<earlierTime.minute)){
			  timeDifference=(this.hour-earlierTime.hour)*60-(earlierTime.minute-this.minute);
		  }else if((this.hour<earlierTime.hour)&&(this.minute>earlierTime.minute)){
			  timeDifference=(24-earlierTime.hour+this.hour)*60+(this.minute-earlierTime.minute);
		  }else if((this.hour<earlierTime.hour)&&(this.minute<earlierTime.minute)){
			  timeDifference=(24-earlierTime.hour+this.hour)*60-(earlierTime.minute-this.minute);
		  }else if((this.hour==earlierTime.hour)&&(this.minute>earlierTime.minute))
			  timeDifference=this.minute-earlierTime.minute;
		   
		  
		  return timeDifference;
		 

	}

	public boolean isInInterval(int h1, int m1, int h2, int m2)

	/* Checks to see if the target time object is in the time interval
	   from h1:m1 to h2:m2, inclusive, with h1:m1 always regarded as 
	   earlier than h2:m2. Note that the interval may include midnight. */

	{
		if((h2>h1)&&(this.hour==h1)&&(this.minute>m1)||(h2>h1)&&(this.hour==h2)&&(this.minute<m2)
				||(h2>h1)&&(this.hour>h1)&&(this.hour<h2)||(h2<h1)&&(this.hour==h1)&&(this.minute>m1)||
				(h2<h1)&&(this.hour==h2)&&(this.minute<m2)||((h2<h1)&&((this.hour<24)&&(this.hour>h1)||(this.hour<h2)&&(this.hour>=0)))){
			return true;
		}else 
			return false;
		 
		 
	}
}