/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
import java.sql.*;
import java.util.*;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

 
public class SearchAPP {
     private static Connection conn=DatabaseConnect.ConnerDb();
     private static ResultSet rs=null;
     private static PreparedStatement pst=null;
     public static Object [][] table; //= new Object [400][5];
     public static int loadingMore=0;
     public static ArrayList<String > Results=new ArrayList<String >();
     public static ArrayList<String> URL=new ArrayList<String>();
     public static int count=-1;
     public static int done=0;
     public static boolean flag=false; 
  private static double deg2rad(double deg) {

    return (deg * Math.PI / 180.0);

}
  private static double rad2deg(double rad) {

   return (rad * 180.0 / Math.PI);

}
 public static double GetDistance(double lat1, double lng1, double lat2, double lng2){    
  
   double theta = lng1 - lng2;

   double dist = Math.sin(deg2rad(lat1)) * Math.sin(deg2rad(lat2)) + Math.cos(deg2rad(lat1)) * Math.cos(deg2rad(lat2)) * Math.cos(deg2rad(theta));

   dist = Math.acos(dist);

   dist = rad2deg(dist);

   dist = dist * 60 * 1.1515;


    //System.out.println("Distance is "+dist*1.609344+ "km");
    return dist*1.609344;  
    } 
    public static double GetDistanceFromZip(String zip1, String zip2){
    double d=0d , lat1=0d, lng1=0d, lat2=0d, lng2=0d ;
    lat1=GetLatitude(zip1); lng1=GetLongitude(zip1);
    lat2=GetLatitude(zip2); lng2=GetLongitude(zip2);
    d=GetDistance( lat1,  lng1,  lat2,  lng2);
   // System.out.println("The distance between "+zip1+" and " +zip2+ " is "+d+" KM" );
    return d;    
    }
    public static Double GetLatitude (String zip){
      double lat=0d;
          try{
        String sql = "SELECT latitude \n" +
                     "FROM `database`.zip\n" +
                      "where zip_code='"+zip+"';";
        pst = conn.prepareStatement(sql);
        rs=pst.executeQuery();
        if(rs.next()){
           lat = rs.getDouble("latitude");
        }
    }catch(Exception e){
        JOptionPane.showMessageDialog(null, e);     
    }      
   //   System.out.println(lat);  
      return lat;  
    }
    public static Double GetLongitude(String zip){
        double lng=0d;
        try{
        String sql = "SELECT longitude \n" +
                     "FROM `database`.zip\n" +
                      "where zip_code='"+zip+"';";
        pst = conn.prepareStatement(sql);
        rs=pst.executeQuery();
        if(rs.next()){
           lng = rs.getDouble("longitude");
        }
    }catch(Exception e){
        JOptionPane.showMessageDialog(null, e);
        
    }      
  //    System.out.println(lng);              
        return lng;
    }
    public static ArrayList<String> GetZipCodes(String zip , double r ){
        ArrayList<String> ZipCodes=new ArrayList<String>();
        String tem="";
        double d=0d;
        ResultSet rs1=null;
        PreparedStatement pst1=null;
        ResultSet rs2=null;
        PreparedStatement pst2=null;
         try{
        String sql = "SELECT zip_code \n" +
                     "FROM `database`.zip\n" +
                      "where zip_code='"+zip+"';";
        String sql1 = "SELECT zip_code \n" +
                     "FROM `database`.clist\n" +
                      "where zip_code='"+zip+"';";  
        String sql2 = "SELECT zip_code \n" +
                      "FROM `database`.clist";
                    
  
        pst = conn.prepareStatement(sql);
        rs=pst.executeQuery();
        pst1 = conn.prepareStatement(sql1);
        rs1=pst1.executeQuery();
        pst2 = conn.prepareStatement(sql2);
        rs2=pst2.executeQuery();
        if(!rs.next()){
            JOptionPane.showMessageDialog(null, "No results, Please enter valid query or You zip code isn't in craigslist. You can Try to increase Radius");
        }
        else if(!rs1.next()){
            // JOptionPane.showMessageDialog(null, "Your zip code is not in craigslist, we will find nearby within "+r+" km[s] radial distance");
             while(rs2.next()){
                 tem=rs2.getString("zip_code");
                d=GetDistanceFromZip(zip, tem);
                if(d<=r)
                ZipCodes.add(tem);    
             }
        }
        else{
            //JOptionPane.showMessageDialog(null, "Your zip code is in craigslist, we will find nearby within "+r+" km[s] radial distance instead");
             while(rs2.next()){
                 tem=rs2.getString("zip_code");
                d=GetDistanceFromZip(zip, tem);
                if(d<=r)
                ZipCodes.add(tem);    
             }
        }
             
    }catch(Exception e){
        JOptionPane.showMessageDialog(null, e);
        
    }      
     System.out.println("Get the nearby zip codes");    
     return ZipCodes;   
    }
  public static ArrayList<String> GetSearchWebPage(ArrayList<String> zip ){
      ArrayList<String> SearchWeb=new ArrayList<String>();
      ResultSet rs1=null;
      PreparedStatement pst1=null;
       try{
        for(int i=0; i<zip.size();i++){
            String sql = "SELECT URL \n" +
                         "FROM `database`.clist\n" +
                         "where zip_code='"+zip.get(i)+"';";
        pst1 = conn.prepareStatement(sql);
        rs1=pst1.executeQuery();
        if(rs1.next()){
           SearchWeb.add(rs1.getString("URL"));
        }
        
        }
    }catch(Exception e){
        JOptionPane.showMessageDialog(null, e);
        
    }  
     System.out.println("Get the Search page url zip");  
     return SearchWeb;
   }
  public static ArrayList<String> SearchURL( String SearchContent, ArrayList<String> webpage ){
      ArrayList<String> URL=new ArrayList<String>();
      SearchContent=SearchContent.replace(" ", "%20");
      for(int i=0; i<webpage.size();i++){
          
          try {
              URL.add(webpage.get(i)+"/search/?sort=rel&areaID="+webpageReader.GetAreaID(webpage.get(i))+"&subAreaID=&query="+SearchContent+"&catAbb=sss");
          } catch (Exception ex) {
              Logger.getLogger(SearchAPP.class.getName()).log(Level.SEVERE, null, ex);
          }
      }
      
      System.out.println("get search results page url");
      return URL;
    } 
  
 public static void SearchFunction(String SearchConten, String zip, double r)throws Exception{   

     ArrayList<String> ZipCodes=new ArrayList<String>();
     ArrayList<String> SearchWeb=new ArrayList<String>();
     
     
     ZipCodes=GetZipCodes(zip,r);
     SearchWeb=GetSearchWebPage(ZipCodes);
     URL=SearchURL(SearchConten,SearchWeb);
                for(int j=0; j<URL.size();j++)      
               System.out.println("Searching page  ----"+URL.get(j));
     System.out.println("Searching prosessing;");
    Results= webpageReader.GetSearchResults(URL);
          System.out.println("Getting Results;");
/*
 
           for(int i=loadingMore; i<loadingMore+28;i++){
               
                table[i][0]=webpageReader.GetTittle(Results.get(i));
                table[i][1]=webpageReader.GetLocation(Results.get(i));
                table[i][2]=webpageReader.GetDate(Results.get(i));
                table[i][3]=webpageReader.GetAdType(Results.get(i));
                table[i][4]=Results.get(i);
           
            //    System.out.println("Tittle  ----"+webpageReader.GetTittle(Results.get(i))+ "   ----AD NUMBER  "+i);
            //     System.out.println("Location  ----"+webpageReader.GetLocation(Results.get(i))+ "   ----AD NUMBER  "+i);
              //    System.out.println("Time  ----"+webpageReader.GetDate(Results.get(i))+ "   ----AD NUMBER  "+i);
                //   System.out.println("type  ----"+webpageReader.GetAdType(Results.get(i))+ "   ----AD NUMBER  "+i);
                   // System.out.println("AdUrl  ----"+Results.get(i)+ "  ----AD NUMBER  "+i);
                  System.out.println("Tittle  ----"+table[i][0]+ "   ----AD NUMBER  "+i);
                 System.out.println("Location  ----"+table[i][1]+ "   ----AD NUMBER  "+i);
                  System.out.println("Time  ----"+table[i][2]+ "   ----AD NUMBER  "+i);
                  System.out.println("type  ----"+table[i][3]+ "   ----AD NUMBER  "+i);
                   System.out.println("AdUrl  ----"+table[i][4]+ "  ----AD NUMBER  "+i);
          
       /*   String SQL="INSERT INTO `database`.`sr` (`ad_tittle`, `location`, `time_posted`, `ad_type`, `ad_url`) VALUES ('"+webpageReader.GetTittle(Results.get(i))+"', '"+webpageReader.GetLocation(Results.get(i))+"', '"+webpageReader.GetDate(Results.get(i))+"', '"+webpageReader.GetAdType(Results.get(i))+"', '"+Results.get(i)+"');";
           pst = conn.prepareStatement(SQL);       
           pst.execute();
           System.out.println("AD NUMBER "+i+" inserted");  
            //JOptionPane.showMessageDialog(null, "Updated");
   }*/

          
 }
 public static void SaveItintoTable()throws Exception{
                  
          
          for(int i=loadingMore; i<loadingMore+10;i++){
               
                table[i][0]=webpageReader.GetTittle(Results.get(i));
                table[i][1]=webpageReader.GetLocation(Results.get(i));
                table[i][2]=webpageReader.GetDate(Results.get(i));
                table[i][3]=webpageReader.GetAdType(Results.get(i));
                table[i][4]=Results.get(i);
                count++;
               
          }
          loadingMore+=count;
          count=0;
}
      public static class ST implements Runnable {
         public void run(){         
              try { 
                       GetIndex();
                        int temp = count;
		table[temp][0]=webpageReader.GetTittle(Results.get(temp));
                table[temp][1]=webpageReader.GetLocation(Results.get(temp));
                table[temp][2]=webpageReader.GetDate(Results.get(temp));
                table[temp][3]=webpageReader.GetAdType(Results.get(temp));
                table[temp][4]=Results.get(temp);
                      DoneSignal();
                System.out.println("Thread "+ temp+ " done");
               
        } catch (Exception ex) {
                 Logger.getLogger(SearchAPP.class.getName()).log(Level.SEVERE, null, ex);
                
                 
             }
}              
	}
    
      	protected  static synchronized void GetIndex(){
		count++;
		
		
	}
	protected  static synchronized void DoneSignal(){
                done++;
         
     }
}
