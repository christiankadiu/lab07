package it.unibo.nestedenum;

import java.util.Comparator;

/**
 * Implementation of {@link MonthSorter}.
 */
public final class MonthSorterNested implements MonthSorter {
    private static final Comparator<String> DAYS = new SortByDays();
    private static final Comparator<String> ORDER = new SortByMonthOrder();

    @Override
    public Comparator<String> sortByDays() {
        return DAYS;
    }

    @Override
    public Comparator<String> sortByOrder() {
        return ORDER;
    }

    public enum Month {
        JANUARY(31),
        FEBRUARY(28),
        MARCH(31),
        APRIL(30),
        MAY(31),
        JUNE(30),
        JULY(31),
        AUGUST(31),
        SEPTEMBER(30),
        OCTOBER(31),
        NOVEMBER(30),
        DECEMBER(31);

        private final int days;

        Month(int days) {
            this.days = days;
        }

        public static Month fromString(String name) {
            Month mese = null;
            name = name.toUpperCase();
            int i = 0;
            for (final Month month : Month.values()) {
                if (month.toString().startsWith(name)) {
                    i++;
                    if (i > 1) {
                        throw new IllegalArgumentException();
                    }
                    mese = month;
                }
            }
            if (i != 0) {
                return mese;
            } else {
                throw new IllegalArgumentException();
            }
        }
    }

    private static class SortByDays implements Comparator<String> {

        @Override
        public int compare(String o1, String o2) {
            final Month o1Days = Month.fromString(o1);
            final Month o2Days = Month.fromString(o2);
            return Integer.compare(o1Days.days, o2Days.days);
        }
    }

    private static class SortByMonthOrder implements Comparator<String> {
        @Override
        public int compare(String o1, String o2) {
            return Month.fromString(o1).compareTo(Month.fromString(o2));
        }
    }

}
