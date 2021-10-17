package demo.test.util;

import java.util.Date;

public class TimeUtils {

    public static long getCurrentTimestamp() {
        Date date = new Date();
        return date.getTime();
    }

}
