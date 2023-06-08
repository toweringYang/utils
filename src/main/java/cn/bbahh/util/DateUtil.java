package cn.bbahh.util;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.ZoneOffset;
import java.time.temporal.ChronoField;
import java.time.temporal.TemporalAdjusters;
import java.time.temporal.WeekFields;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

public class DateUtil {
    public static LocalDate[] getWeekStartAndEnd(int year, int weekNum) {
        LocalDate week = LocalDate.now().plusYears(year-LocalDate.now().getYear()).with(ChronoField.ALIGNED_WEEK_OF_YEAR, weekNum);
        LocalDate start = week.with(TemporalAdjusters.previousOrSame(DayOfWeek.MONDAY));
        LocalDate end = week.with(TemporalAdjusters.nextOrSame(DayOfWeek.SUNDAY));
        return new LocalDate[]{start, end};
    }

    public static int getNowWeekNum() {
        return LocalDate.now().get(WeekFields.ISO.weekOfWeekBasedYear());
    }

    public static int getSumWeekNum(int year){
        Calendar cal = Calendar.getInstance();
        cal.set(Calendar.YEAR, year);
        cal.set(Calendar.MONTH, Calendar.JANUARY);
        cal.set(Calendar.DAY_OF_MONTH, 1);
        GregorianCalendar gregorianCalendar = new GregorianCalendar();

        int weekDay = cal.get(Calendar.DAY_OF_WEEK) - 1;
        if (gregorianCalendar.isLeapYear(2020)) {
            if (weekDay == Calendar.THURSDAY || weekDay == Calendar.WEDNESDAY)
                return 53;
            else
                return 52;
        } else {
            if (weekDay == Calendar.THURSDAY)
                return 53;
            else
                return 52;
        }
    }

    /**
     * Date转LocalDate
     *
     * @param date
     * @return
     */
    public static LocalDate date2LocalDate(Date date) {
        return date.toInstant().atZone(ZoneOffset.ofHours(8)).toLocalDate();
    }


}
