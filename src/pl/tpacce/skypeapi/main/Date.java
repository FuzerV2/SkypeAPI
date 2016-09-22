package pl.tpacce.skypeapi.main;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;

/**
 * Created by Szymon on 2015-06-24.
 */
public class Date {

    public static String getDate() {
        DateFormat df = new SimpleDateFormat("dd/MM/yyyy");
        Calendar calobj = Calendar.getInstance();
        return df.format(calobj.getTime());
    }

    public static String getTime() {
        DateFormat df = new SimpleDateFormat("HH:mm:ss");
        Calendar calobj = Calendar.getInstance();
        return df.format(calobj.getTime());
    }
}
