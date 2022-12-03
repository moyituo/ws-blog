package work.xiaoying.utils;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.Date;

/**
 * 日期跑龙套
 *
 * @author 小樱
 * @date 2022/12/04
 */
public class DateUtil {

    private static final Long ONE_MINUTE_TO_SECOND=60L;
    private static final Long ONE_HOUR_TO_SECOND=ONE_MINUTE_TO_SECOND*60;
    private static final Long ONE_DAY_TO_SECOND=ONE_HOUR_TO_SECOND*24;

    private static final DateTimeFormatter DATE_TIME_FORMATTER1=DateTimeFormatter.ofPattern("yyyy-MM-dd");
    private static final DateTimeFormatter DATE_TIME_FORMATTER2=DateTimeFormatter.ofPattern("MM-dd");

    public static String getTimeDiff(Date now,Date targetDate){
        String timeDescription = "";
        if (targetDate != null) {
            // 5. 年内判断
            if (targetDate.getYear() == now.getYear()) {
                // 获取秒数差
                long betweenSeconds = (now.getTime() - targetDate.getTime()) / 1000;
                if (betweenSeconds < ONE_MINUTE_TO_SECOND) {
                    // 1. 1分钟内：刚刚
                    timeDescription = "刚刚";
                } else if (betweenSeconds < ONE_HOUR_TO_SECOND) {
                    // 2. 60分钟内
                    timeDescription = betweenSeconds / ONE_MINUTE_TO_SECOND + "分钟前";
                } else if (betweenSeconds < ONE_DAY_TO_SECOND) {
                    // 3. 24小时内：x小时前
                    timeDescription = betweenSeconds / ONE_HOUR_TO_SECOND + "小时前";
                } else {
                    // 4. >24小时：x月x日  08-1
                    timeDescription = dateToLocalDateTime(targetDate).format(DATE_TIME_FORMATTER2);
                }
            } else {
                timeDescription = dateToLocalDateTime(targetDate).format(DATE_TIME_FORMATTER1);
            }
        }
        return timeDescription;
    }

    public static LocalDateTime dateToLocalDateTime(Date date) {
        return date.toInstant().atZone(ZoneId.systemDefault()).toLocalDateTime();
    }

}
