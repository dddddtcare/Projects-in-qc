import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
*
*/
public class DataUtil {

  public static Date getDate(String data) {
    if (data.length() < 4) {
      return null;
    } else {
      try {
        if (data.length() == 4) {
          return new SimpleDateFormat("yyyy").parse(data);
        } else {
          return new SimpleDateFormat("yyyy-MMM-dd").parse(data);
        }
      } catch (ParseException e) {
        e.printStackTrace();
        return null;
      }
    }
  }

}