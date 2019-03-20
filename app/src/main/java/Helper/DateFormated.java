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

    public  static String setTgl(String oldDate){
        Date date= null;
        try {
            date = new SimpleDateFormat("yyyy-MM-dd").parse(oldDate);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return new SimpleDateFormat("dd MMMM yyyy").format(date);
    }

    public static String dateValidation(int value){
        if (value<10){
            return "0"+value;
        }
        return String.valueOf(value);
    }

    public  static String getMonthName(String month){
        Date date= null;
        try {
            date = new SimpleDateFormat("MM").parse(month);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return new SimpleDateFormat("MMMM").format(date);
    }

}
