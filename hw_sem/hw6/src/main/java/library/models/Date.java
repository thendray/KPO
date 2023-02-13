package library.models;

import static library.tools.DateChecker.checkData;

public class Date {

    private final int day;
    private final int month;
    private final int year;

    public int getDay() {
        return day;
    }

    public int getMonth() {
        return month;
    }

    public int getYear() {
        return year;
    }

    public Date(int d, int m, int y) {
        if (checkData(d, m, y)) {
            this.day = d;
            this.month = m;
            this.year = y;
        } else {
            throw new IllegalArgumentException("Дата некорректна!");
        }
    }


}
