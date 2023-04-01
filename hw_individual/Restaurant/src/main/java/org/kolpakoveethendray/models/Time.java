package org.kolpakoveethendray.models;

import org.kolpakoveethendray.config.RestaurantData;

public class Time {
    public static final long startTime = System.currentTimeMillis();
    public static final long endTime = startTime + 3600L * RestaurantData.getWorkHours();
    public static String getTimeStamp() {
        long elapsedTime = System.currentTimeMillis() - startTime;
        int hours = (int) (elapsedTime / 3600);
        int minutes = (int) (elapsedTime % 3600) / 60;
        return String.format("%02d:%02d", hours + 8, minutes);
    }
}
