package soding.com.taskmanagementsystem;

import java.text.SimpleDateFormat;
import java.util.Calendar;

/**
 * Created by PT on 8/31/2017.
 */

public class Utlis {

    public static String getCurrentDate() {
        String formattedDate = getTimeByFormat("dd-MMM-yyyy");
        return formattedDate ;
    }
    private static String getTimeByFormat(String format){
        Calendar c = Calendar.getInstance();
        SimpleDateFormat df = new SimpleDateFormat(format);
        String formattedDate = df.format(c.getTime());
        return formattedDate ;
    }


}
