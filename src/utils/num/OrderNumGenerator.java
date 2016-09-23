package utils.num;

import javafx.scene.input.DataFormat;

import java.text.DateFormat;
import java.text.SimpleDateFormat;


/**
 * Created by RZ on 7/8/16.
 */
public class OrderNumGenerator {
    private static Integer serNum = 1;
    private static final byte[] zeros = {48,48,48,48,48,48,48,48};
    private static final Integer len = 8;

    public static String generator() {
        DateFormat df = new SimpleDateFormat("yyyyMMdd");
        String now = df.format(System.currentTimeMillis());
        int l = serNum++;
        String z = new String(zeros,0,len-(l+"").length());
        return Long.toHexString(new Long(now+z+l)).toUpperCase();
    }

}
