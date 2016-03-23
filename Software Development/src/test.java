
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;


/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
 
public class test {

    public static void main(String[] args) 
        throws IOException {
    /*    
        //SearchAPP.GetLatitude("11373");
       // SearchAPP.GetLongitude("11373");
        SearchAPP.GetDistance(40.7143528,-74.0059731,36.778261,-119.4179324 );
        SearchAPP.GetDistanceFromZip("11355", "11354");
      ArrayList<String> ZC=new ArrayList<String>();
      ArrayList<String> web=new ArrayList<String>();
      ArrayList<String> search=new ArrayList<String>();
      ZC=SearchAPP.GetZipCodes("11355", 150);
      web=SearchAPP.GetSearchWebPage(ZC);
      System.out.println("----------------zip codes---------------Toatal matchs is "+ZC.size());
        if(ZC.size()==0)
            JOptionPane.showMessageDialog(null, "OPPS!!! NO RESULT!!! Please increase your radial distance");
        for(int i=0; i<ZC.size(); i++){
            System.out.println(ZC.get(i));
        } 
     // System.out.println("----------------zip codes---------------Toatal matchs is "+ZC.size());
      
        System.out.println("----------------Web Page--------------- Number of Page: "+web.size());
        if(web.size()==0)
            System.out.println("NO RESULTS ");
        for(int i=0; i<web.size(); i++){
            System.out.println(web.get(i));
        } 
  System.out.println("-----------------------Area ID and SubAreaID------------------------- ");
        try {
             for(int i=0; i<web.size(); i++)
            System.out.println("areID is "+webpageReader.GetAreaID(web.get(i))+" subAreaID is "+webpageReader.GetSubAreaID(web.get(i)));
        } catch (Exception ex) {
            Logger.getLogger(test.class.getName()).log(Level.SEVERE, null, ex);
        }
 System.out.println("-----------------------Search WebPage----------------------------- ");
               
              search=SearchAPP.SearchURL("good find", web);
              for(int i=0; i<search.size(); i++)
              System.out.println("Search WebPage is "+search.get(i));*/
/*System.out.println("-----------------------Search Reresult----------------------------- ");
        try {
        // SearchAPP.SearchFunction("car seat","11355",150);
           // System.out.println(webpageReader.GetTittle("http://harrisonburg.craigslist.org/fod/4428972426.html"));
           // System.out.println(webpageReader.GetAdType("http://harrisonburg.craigslist.org/fod/4428972426.html"));
           // System.out.println(webpageReader.GetDate("http://harrisonburg.craigslist.org/fod/4428972426.html"));
           // System.out.println(webpageReader.GetLocation("http://hartford.craigslist.org/mob/4413924186.html"));
          //  webpageReader.GetAdsUrl("http://hartford.craigslist.org/search/?sort=rel&areaID=44&subAreaID=&query=car fuck  seat&catAbb=sss");
           // webpageReader.GetAdsUrl("http://hartford.craigslist.org/search/?sort=rel&areaID=44&subAreaID=&query=car%20seat&catAbb=sss");
            ArrayList<String> image= new ArrayList<String>();
            image=webpageReader.FindImage("http://allentown.craigslist.org/cto/4437254417.html");
            for(int i=0; i<image.size();i++)
            System.out.println(image.get(i));
            GetURLImage.saveImageFromURL("http://images.craigslist.org/00707_8W7Z4pIAXze_600x450.jpg", "22");
           // new File("C:\\Craigslist").mkdirs();
              
        } catch (Exception ex) {
            Logger.getLogger(test.class.getName()).log(Level.SEVERE, null, ex);
        }*/   
        System.out.println(SearchGUI.CheckDataBase());
    }
    
}


