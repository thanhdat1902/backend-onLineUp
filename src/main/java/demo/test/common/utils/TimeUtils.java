package demo.test.common.utils;

import java.util.Date;

public class TimeUtils {

    public static long getCurrentTimestamp() {
        Date date = new Date();
        return date.getTime();
    }

}
