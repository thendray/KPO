package library.tools;

import java.time.LocalDate;

public class DateChecker {
    private final static int current_year = LocalDate.now().getYear();


    public static boolean checkData(int day, int month, int year) {

        if (year < 0 || year > current_year) {
            return false;
        }

        if (day < 0) {
            return false;
        }

        switch (month) {

            case 1, 3, 5, 7, 8, 10, 12:
                if (day > 31) {
                    return false;
                }
                break;

            case 2:
                if (year % 4 == 0 && year % 100 != 0 || year % 400 == 0) {
                    if (day > 29) {
                        return false;
                    }
                } else {
                    if (day > 28) {
                        return false;
                    }
                }
                break;

            case 4, 6, 9, 11:
                if (day > 30) {
                    return false;
                }
                break;

            default:
                return false;
        }

        return true;
    }
}
