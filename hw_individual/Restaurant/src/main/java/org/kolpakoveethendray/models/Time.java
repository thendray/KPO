package org.kolpakoveethendray.models;

import org.kolpakoveethendray.config.RestaurantData;

/**
 * Класс-модель времени
 */
public class Time {
    public static final long startTime = System.currentTimeMillis();
    public static final long endTime = startTime + 3600L * RestaurantData.getWorkHours();

    /**
     * Получить актуальное время в формате ЧАСЫ:МИНУТЫ
     * @return - строковое представление текущего времени
     */
    public static String getTimeStamp() {
        long elapsedTime = System.currentTimeMillis() - startTime;
        int hours = (int) (elapsedTime / 3600);
        int minutes = (int) (elapsedTime % 3600) / 60;
        return String.format("%02d:%02d", hours + 8, minutes);
    }
}
