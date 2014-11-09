

import java.sql.*;
import java.util.Calendar;
import java.util.List;
import java.util.concurrent.atomic.AtomicLong;
 
/**
 * A Java MySQL PreparedStatement INSERT example.
 * Demonstrates the use of a SQL INSERT statement against a
 * MySQL database, called from a Java program, using a
 * Java PreparedStatement.
 * 
 * Created by Alvin Alexander, <a href="http://devdaily.com" title="http://devdaily.com">http://devdaily.com</a>
 */
public class DBInsert 
{
	private static final AtomicLong LAST_TIME_MS = new AtomicLong();
  public void  insert(List<Review> reviewList)
  {
	  database db=new database();
	  db.connect();
    try
    {
    	String id3=Long.toString(uniqueCurrentTimeMS()) ;
    	db.add_business( id3, City.city);
    	String resturant1=null;
    	String address1=null;
    	String id=null;
      for(int i=0; i<reviewList.size();i++)
      {
     
    	  String resturant=reviewList.get(i).getResturant();
    	  String address = reviewList.get(i).getAddress();
    	  if(i==0||!resturant.equalsIgnoreCase(resturant1)&&!address.equalsIgnoreCase(address1))
    	  {
    		 resturant1=reviewList.get(i).getResturant();
    	  address1= reviewList.get(i).getAddress();
    	  id=Long.toString(uniqueCurrentTimeMS()) ;
    	  db.add_restaurant(id,reviewList.get(i).getResturant(),reviewList.get(i).getAddress(),id3);
      }
    	  String id2=Long.toString(uniqueCurrentTimeMS()) ;
    	  db.add_review(id2,id,reviewList.get(i).getReview() );
      }
    }
    catch (Exception e)
    {
      System.err.println("Got an exception!");
      System.err.println(e.getMessage());
    }
  }
  
  
  public static long uniqueCurrentTimeMS() {
	    long now = System.currentTimeMillis();
	    while(true) {
	        long lastTime = LAST_TIME_MS.get();
	        if (lastTime >= now)
	            now = lastTime+1;
	        if (LAST_TIME_MS.compareAndSet(lastTime, now))
	            return now;
	    }
	    
  }
}