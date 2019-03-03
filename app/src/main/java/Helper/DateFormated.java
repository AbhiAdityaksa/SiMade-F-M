package Helper;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class DateFormated {

    public  static String setTglHistory(String oldDate){
        Date date= null;
        try {
            date = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(oldDate);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return new SimpleDateFormat("dd MMMM yyyy HH:mm").format(date);
    }

}
