package utils.format;

import java.text.DateFormat;
import java.text.SimpleDateFormat;

/**
 * Created by RZ on 6/30/16.
 */
public class FormatUtil {
    public static final String formatDate(Long time) {
        if(time==null) return null;
        DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
        return df.format(time);
    }
    public static final String formatTime(Long time) {
        if(time==null) return null;
        DateFormat df = new SimpleDateFormat("HH:mm:ss");
        return df.format(time);
    }
    public static final String formatDateTime(Long time) {
        if(time==null) return null;
        DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        return df.format(time);
    }

    public static final String formatPrice(Double price) {
        return String.format("%.2f",price);
    }

    public static void main(String[] args) {
        System.out.println(formatPrice(0.236432432d));
    }
}
