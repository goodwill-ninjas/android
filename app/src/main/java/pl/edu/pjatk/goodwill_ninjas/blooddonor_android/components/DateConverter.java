package pl.edu.pjatk.goodwill_ninjas.blooddonor_android.components;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;

public class DateConverter {

    public DateConverter(long milliSeconds) {
        this.milliSeconds = milliSeconds;
        this.dateFormat = "dd/MM/yyyy";
    }

    public long getMilliSeconds() {
        return milliSeconds;
    }

    public String getDateFormat() {
        return dateFormat;
    }

    public void setMilliSeconds(long milliSeconds) {
        this.milliSeconds = milliSeconds;
    }

    public void setDateFormat(String dateFormat) {
        this.dateFormat = "dd/MM/yyyy";
    }

    long milliSeconds;
    String dateFormat;

    public static String getDate(long milliSeconds, String dateFormat) {

        SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");


        Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(milliSeconds);
        return formatter.format(calendar.getTime());
    }
}
