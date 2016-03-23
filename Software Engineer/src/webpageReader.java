import java.io.*;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.Date;
import java.util.regex.*;
import java.util.Scanner;
import javax.swing.JOptionPane;

public class webpageReader{

        private static String webpage = null;
        public static int Ucount =0;
        public static final String USER_AGENT = "Mozilla/5.0 (Windows; U; Windows NT 5.1; en-US; rv:1.9.2) Gecko/20100115 Firefox/3.6";

        public static InputStream getURLInputStream(String sURL) throws Exception {
        URLConnection oConnection = (new URL(sURL)).openConnection();
        oConnection.setRequestProperty("User-Agent", USER_AGENT);
        return oConnection.getInputStream();
        }

        public static BufferedReader read(String url) throws Exception {
            //InputStream content = (InputStream)uc.getInputStream();
//        BufferedReader in = new BufferedReader (new InputStreamReader
//    (content));
            InputStream content = (InputStream)getURLInputStream(url);
            return new BufferedReader (new InputStreamReader(content));
        } // read

        public static BufferedReader read2(String url) throws Exception {
                return new BufferedReader(
                        new InputStreamReader(
                                new URL(url).openStream()));
        } // read

	public static String GetAreaID(String s) throws Exception{

	/*	if (s.length == 0) {
			System.out.println("No URL inputted.");
			System.exit(1);
		} // any inputs?       */
              //  PrintWriter pw = new PrintWriter( new FileWriter( "C:\\Users\\Qiang\\Desktop\\File_"+Ucount+++".txt" ) ); // Save htm and html files into desktop and convert to txt format 
		String aID="";
		//System.out.println("Contents of the following URL: "+s);                
		BufferedReader reader = read(s);
               //  Pattern AreaID = Pattern.compile(".*\\<li\\>\\<a\\shref\\=\"(.*org)\".*");
                  Pattern AreaID = Pattern.compile(".*id\\=\"areaID\"\\svalue=\"(\\d+)\">.*");
		String line = reader.readLine();

		while (line != null) {
                      Matcher ID = AreaID.matcher(line);
                        if(ID.matches())
                        {
                            aID=ID.group(1);
                            break;
                        }
			//System.out.println(line);
                      //  pw.println(line);       // save the Strings into txt file line by line
			line = reader.readLine();   
		} // while   
      //    pw.close();
                return aID;
	} // main     
  
         public static ArrayList<String> GetSearchResults(ArrayList<String> url ) throws Exception{
                  ArrayList<String > Sresults=new ArrayList<String >();
                  
                  String str="";
                  int Count=0;
	       for(int i=0; i<url.size();i++){
//                 System.out.println(url.get(i));
		 BufferedReader reader = read(url.get(i));
                  Pattern AdUrl = Pattern.compile(".*\"(\\/.*)\"\\>");
                  Matcher AU;                
                  Pattern Header = Pattern.compile("(htt.*org).*");
                  Matcher HD;
                  String header="";
                  HD=Header.matcher(url.get(i));
                  if(HD.find()){
                   header= HD.group(1);
              }
                  String line = reader.readLine();
                  String content="";

              
              while(line !=null){
                  if(line.contains("<p class=\"row\"")){
                      content=line;
                      break;
                  }
              line = reader.readLine();
                                 }
              Scanner s = new Scanner(content);
             while(s.hasNext()){
                  str=s.next();            
                  if(str.contains("html"))
                  {
                     // System.out.println(str);
                      AU=AdUrl.matcher(str);
                     if(AU.find()){
                         Sresults.add(header+AU.group(1));
                        
                     }
                  }
                  
                   
              
              
               /*   
                   AU=AdUrl.matcher(str);
                  if(AU.find()){
                     // RS[4]=(header+AU.group(1));
                     // RS[0]=GetTittle(RS[4]);
                    // RS[2]=GetDate(RS[4]);
                     // RS[3]=GetAdType(RS[4]);
                     // Count++;
                      Sresults.add(RS); 
                  }   */
                     
                     
              }
      
               }
         return Sresults;
         }

          public static String GetAdType(String s) throws Exception{

	/*	if (s.length == 0) {
			System.out.println("No URL inputted.");
			System.exit(1);
		} // any inputs?       */
              //  PrintWriter pw = new PrintWriter( new FileWriter( "C:\\Users\\Qiang\\Desktop\\File_"+Ucount+++".txt" ) ); // Save htm and html files into desktop and convert to txt format 
		String ST="";
		//System.out.println("Contents of the following URL: "+s);                
		BufferedReader reader = read(s);
               //  Pattern AreaID = Pattern.compile(".*\\<li\\>\\<a\\shref\\=\"(.*org)\".*");
                  Pattern Tittle = Pattern.compile(".*\\/\"\\>(.*)\\<\\/a\\>\\<\\/span\\>.*");
		String line = reader.readLine();

		while (line != null) {
                      Matcher TL = Tittle.matcher(line);
                        if(TL.matches())
                        {
                            ST=TL.group(1);
                            break;
                        }
			//System.out.println(line);
                      //  pw.println(line);       // save the Strings into txt file line by line
			line = reader.readLine();   
		} // while   
      //    pw.close();
                return ST;
	}  
         
    public static String GetTittle(String s) throws Exception{

	/*	if (s.length == 0) {
			System.out.println("No URL inputted.");
			System.exit(1);
		} // any inputs?       */
              //  PrintWriter pw = new PrintWriter( new FileWriter( "C:\\Users\\Qiang\\Desktop\\File_"+Ucount+++".txt" ) ); // Save htm and html files into desktop and convert to txt format 
		String tittle="";
		//System.out.println("Contents of the following URL: "+s);                
		BufferedReader reader = read(s);
               //  Pattern AreaID = Pattern.compile(".*\\<li\\>\\<a\\shref\\=\"(.*org)\".*");
                Pattern Tittle = Pattern.compile(".*\\<title\\>(.*)\\<\\/title\\>.*");
		String line = reader.readLine();

		while (line != null) {
                      Matcher TL = Tittle.matcher(line);
                        if(TL.matches())
                        {
                            tittle=TL.group(1);
                            break;
                        }
			//System.out.println(line);
                      //  pw.println(line);       // save the Strings into txt file line by line
			line = reader.readLine();   
		} // while   
      //    pw.close();
                return tittle;
	} // main  
      public static String GetDate(String s) throws Exception{

	/*	if (s.length == 0) {
			System.out.println("No URL inputted.");
			System.exit(1);
		} // any inputs?       */
              //  PrintWriter pw = new PrintWriter( new FileWriter( "C:\\Users\\Qiang\\Desktop\\File_"+Ucount+++".txt" ) ); // Save htm and html files into desktop and convert to txt format 
		String tittle="";
		//System.out.println("Contents of the following URL: "+s);                
		BufferedReader reader = read(s);
               //  Pattern AreaID = Pattern.compile(".*\\<li\\>\\<a\\shref\\=\"(.*org)\".*");
                Pattern Tittle = Pattern.compile(".*\"\\>(.*)\\<\\/time\\>.*");
		String line = reader.readLine();

		while (line != null) {
                      Matcher TL = Tittle.matcher(line);
                        if(TL.matches())
                        {
                            tittle=TL.group(1);
                            break;
                        }
			//System.out.println(line);
                      //  pw.println(line);       // save the Strings into txt file line by line
			line = reader.readLine();   
		} // while   
      //    pw.close();
                return tittle;
	} // main  
        public static String GetLocation(String s) throws Exception{

	/*	if (s.length == 0) {
			System.out.println("No URL inputted.");
			System.exit(1);
		} // any inputs?       */
              //  PrintWriter pw = new PrintWriter( new FileWriter( "C:\\Users\\Qiang\\Desktop\\File_"+Ucount+++".txt" ) ); // Save htm and html files into desktop and convert to txt format 
		String saID="";
		//System.out.println("Contents of the following URL: "+s);                
		BufferedReader reader = read(s);
               //  Pattern AreaID = Pattern.compile(".*\\<li\\>\\<a\\shref\\=\"(.*org)\".*");
                  Pattern AreaID = Pattern.compile(".*\\-\\s\\&\\#x.*\\((.*)\\).*");
		String line = reader.readLine();

		while (line != null) {
                      Matcher ID = AreaID.matcher(line);
                        if(ID.matches())
                        {
                            saID=ID.group(1);
                            break;
                        }
			//System.out.println(line);
                      //  pw.println(line);       // save the Strings into txt file line by line
			line = reader.readLine();   
		} // while   
      //    pw.close();
                return saID;
	} 
        public static void SaveAdData(String url,String ad_id) throws Exception{

	/*	if (s.length == 0) {
			System.out.println("No URL inputted.");
			System.exit(1);
		} // any inputs?       */
                new File("C:\\Craigslist\\Ad"+ad_id).mkdirs();
                PrintWriter pw = new PrintWriter( new FileWriter( "C:\\Craigslist\\Ad"+ad_id+"\\Ad_Content.txt" ) ); // Save htm and html files into desktop and convert to txt format 
		webpage = url;
		System.out.println("Contents of the following URL: "+webpage+"\n");                
		BufferedReader reader = read(webpage);
                       URL weburl = new URL(url);
                       URLConnection connection = weburl.openConnection();
                      // GetURLInfo.printURLinfo(connection);
                       pw.println("--------------------------------------------------------------------------");
                       pw.println("  Ad Tittle:   "+webpageReader.GetTittle(url));
                       pw.println("  Content Type: " + connection.getContentType());
                       pw.println("  Content Length: " + connection.getContentLength());
                       pw.println("  Last Modified: " + new Date(connection.getLastModified()));
                       pw.println ("  Expiration: " + new Date(connection.getExpiration())); 
                       System.out.println("  Content Encoding: " + connection.getContentEncoding());
		       String line = reader.readLine();
                      
                       pw.println("-------------------------------Ad Content---------------------------------");
		while (!line.contains("<section id=\"postingbody\">")) {
			//System.out.println(line);
                       // pw.println(line);       
			line = reader.readLine();   
		} // while  
                line = reader.readLine();
                while (!line.contains("<div class=\"postinginfos\">")) {
			System.out.println(line);
                          line.replaceAll("", "");
                          pw.println(line);       
			line = reader.readLine();   
		} // while 
               JOptionPane.showMessageDialog(null, "Successful, Data was Stored in  C:\\Craigslist\\Ad"+ad_id); 
                pw.println("--------------------------------------------------------------------------");
               pw.close();
	} //
        
   public static ArrayList<String> FindImage(String s) throws Exception{
                 String str1="";
	/*	if (s.length == 0) {
			System.out.println("No URL inputted.");
			System.exit(1);
		} // any inputs?       */
              //  PrintWriter pw = new PrintWriter( new FileWriter( "C:\\Users\\Qiang\\Desktop\\File_"+Ucount+++".txt" ) ); // Save htm and html files into desktop and convert to txt format 
		ArrayList<String> image= new ArrayList<String>();
		//System.out.println("Contents of the following URL: "+s);                
		BufferedReader reader = read(s);
               //  Pattern AreaID = Pattern.compile(".*\\<li\\>\\<a\\shref\\=\"(.*org)\".*");
                Pattern JPG = Pattern.compile(".*href\\=\"(http\\:.*600x450\\.jpg).*");
		String line = reader.readLine();
                while(!line.contains("id=\"thumbs\"><a href")){
                    line=reader.readLine();
                }
                
              // System.out.println(line);
               Scanner s1 = new Scanner(line);
                while(s1.hasNext()){
                  str1=s1.next();            
                  if(str1.contains("600x450"))
                  {
                     // System.out.println(str);
                    Matcher PIC=JPG.matcher(str1);
                     if(PIC.find()){
                         image.add(PIC.group(1));
                        
                     }
                  }
                }
		/*while (line != null) {
                         Matcher PIC = JPG.matcher(line);
                        if(PIC.matches())
                        {
                            image.add(PIC.group(1));                          
                        }
			//System.out.println(line);
                      //  pw.println(line);       // save the Strings into txt file line by line
			line = reader.readLine();   
		} // while   */
      //    pw.close();
                return image;
	} 

} // WebpageReaderWithAgent
