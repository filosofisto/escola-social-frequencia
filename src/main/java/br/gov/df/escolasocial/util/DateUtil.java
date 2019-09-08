package br.gov.df.escolasocial.util;

import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.*;

public final class DateUtil {

    public static final int SUNDAY = 0;

    private DateUtil() {

    }

    public static int actualYear() {
        Calendar calendar = Calendar.getInstance();
        return calendar.get(Calendar.YEAR);
    }

    public static Date firstDateFromYear(int year) {
        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.YEAR, year);
        calendar.set(Calendar.MONTH, Calendar.JANUARY);
        calendar.set(Calendar.DAY_OF_MONTH, 1);

        return calendar.getTime();
    }

    public static int month(Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);

        return calendar.get(Calendar.MONTH);
    }

    public static MonthYear monthYear(Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);

        return new MonthYear(calendar.get(Calendar.MONTH), calendar.get(Calendar.YEAR), false);
    }

    public static boolean equalsDate(Date d1, Date d2) {
        Calendar c1 = Calendar.getInstance();
        c1.setTime(d1);

        Calendar c2 = Calendar.getInstance();
        c2.setTime(d2);

        return c1.get(Calendar.DAY_OF_MONTH) == c2.get(Calendar.DAY_OF_MONTH)
                && c1.get(Calendar.MONTH) == c2.get(Calendar.MONTH)
                && c1.get(Calendar.YEAR) == c2.get(Calendar.YEAR);
    }

    public static Date yesterday() {
        LocalDate ontem = LocalDate.now();
        ontem = ontem.minusDays(1);
        Date yesterday = Date.from(ontem.atStartOfDay(ZoneId.systemDefault()).toInstant());

        return yesterday;
    }

    public static YearMonth yearAndMonthFrom(Date data) {
        Calendar c = Calendar.getInstance();
        c.setTime(data);

        return new YearMonth(c.get(Calendar.YEAR), c.get(Calendar.MONTH)+1);
    }

    public static Date firstDayFrom(YearMonth yearMonth) {
        Calendar c = Calendar.getInstance();
        c.set(Calendar.DAY_OF_MONTH, 1);
        c.set(Calendar.MONTH, yearMonth.getMonth()-1);
        c.set(Calendar.YEAR, yearMonth.getYear());

        return c.getTime();
    }

    public static Date lastDayFrom(YearMonth yearMonth) {
        Calendar c = Calendar.getInstance();
        c.set(Calendar.DAY_OF_MONTH, 1);
        c.set(Calendar.MONTH, yearMonth.getMonth());
        c.set(Calendar.YEAR, yearMonth.getYear());

        c.add(Calendar.DAY_OF_MONTH, -1);

        return c.getTime();
    }

    public static class MonthYear {
        public static final int SATURDAY = 7;
        private int month;
        private int year;
        private List<Date> datesOfMonth;

        public MonthYear(int month, int year, boolean calculateDates) {
            this.month = month;
            this.year = year;

            if (calculateDates) {
                calculateDatesOfMonth();
            }
        }

        public MonthYear(MonthYear other) {
            this(other.getMonth(), other.getYear(), true);
        }

        private void calculateDatesOfMonth() {
            datesOfMonth = new ArrayList<>();

            LocalDate actualDay = LocalDate.of(year, month+1, 1);
            ZoneId zoneId = ZoneId.systemDefault();

            do {
                Instant instant = actualDay.atStartOfDay(zoneId).toInstant();
                LocalDate localDate = instant.atZone(zoneId).toLocalDate();

                if (isWorkDay(localDate)) {
                    datesOfMonth.add(Date.from(instant));
                }

                actualDay = actualDay.plusDays(1);
            } while (isInsideMonth(actualDay));
        }

        private boolean isWorkDay(LocalDate localDate) {
            return localDate.getDayOfWeek().getValue() != SATURDAY && localDate.getDayOfWeek().getValue() != SUNDAY;
        }

        private boolean isInsideMonth(LocalDate actualDay) {
            return actualDay.getMonth().getValue() == (month + 1)
                    && actualDay.getYear() == year;
        }

        public int getMonth() {
            return month;
        }

        public int getYear() {
            return year;
        }

        public List<Date> getDatesOfMonth() {
            return datesOfMonth;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            MonthYear monthYear = (MonthYear) o;
            return month == monthYear.month &&
                    year == monthYear.year;
        }

        @Override
        public int hashCode() {
            return Objects.hash(month, year);
        }
    }
}
