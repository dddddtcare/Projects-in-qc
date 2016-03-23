import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.List;

/**
*
*/
public abstract class FileUtil {

  /**
*
* @param fileName
* @param persontree
*/
  public void read(String fileName, List<Person> persontree) {
    File file = new File(fileName);
    BufferedReader reader = null;
    try {
      reader = new BufferedReader(new FileReader(file));
      String tempString = null;
      int linenum = 0;
      while ((tempString = reader.readLine()) != null) {
        // first line is format, not really data.
        linenum += 1;
        if (tempString.length() < 1 || linenum == 1)
          continue;
        this.callbackTask(tempString, persontree); // The sub class will implement this.
      }
      reader.close();
    } catch (IOException e) {
      e.printStackTrace();
    } finally {
      if (reader != null) {
        try {
          reader.close();
        } catch (IOException e1) {
        }
      }
    }
  }
  
  abstract void callbackTask(String tempString, List<Person> persontree);
}