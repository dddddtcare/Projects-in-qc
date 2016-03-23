
import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.net.URL;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

 
public class DownloadImage {
    
    public static void getImage(String s, String ad_id){
           BufferedImage image =null;
     try{
 
            URL url =new URL(s);
            image = ImageIO.read(url);
            String subs = s.substring((s.length()-12),s.length());
          if(subs.contains("600x450.jpg"))
        {   File outputImageFile = new File("C:\\Craigslist\\Ad"+ad_id+"\\image_"+s.substring((s.length()-24),s.length()-4)+".jpg");       
	    ImageIO.write(image, "jpg", outputImageFile);
        }
 
        }catch(IOException e){
            e.printStackTrace();
    }
    
}
}
