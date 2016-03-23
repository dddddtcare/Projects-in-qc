import java.net.*;

import java.util.*;

public class GetURLInfo {
    public static String CT=null;
    public static int CL;
    public static String LM=null;
    public static String EP=null;
    public static String CE=null;
    public static void printURLinfo(URLConnection uc) {
        // Display the URL address, and information about it.
        System.out.println(uc.getURL().toExternalForm() + ":");
      //   System.out.println("  Name of file: " )
        // Get the information from url and prepare to save them into database
        System.out.println("  Content Type: " + uc.getContentType());
        CT=uc.getContentType();
        System.out.println("  Content Length: " + uc.getContentLength());
        CL =uc.getContentLength();
        System.out.println("  Last Modified: " + new Date(uc.getLastModified()));
        LM=(new Date(uc.getLastModified())).toString();
        System.out.println("  Expiration: " + new Date(uc.getExpiration()));
        EP=(new Date(uc.getExpiration())).toString();
        System.out.println("  Content Encoding: " + uc.getContentEncoding());
        CE=uc.getContentEncoding();
        // Read and print out the first five lines of the URL.
     //   System.out.println("First five lines:");
      //  DataInputStream in = new DataInputStream(uc.getInputStream());
   /*     for(int i = 0; i < 5; i++) {
            String line = in.readLine();
            if (line == null) break;
            System.out.println("  " + line);
        } // for
*/
    } // printURLinfo
    
    // Create a URL from the specified address, open a connection to it,
    // and then display information about the URL.
 /*   public static void main(String[] args) 
        throws MalformedURLException, IOException
    {
	URL url = new URL("http://www.w3schools.com/html/");
	URLConnection connection = url.openConnection();
	printURLinfo(connection);
    } // main  */
} // GetURLInfo
