package org.kolpakoveethendray;

import org.kolpakoveethendray.threads.Restaurant;
import org.kolpakoveethendray.tools.DataReader;
import org.kolpakoveethendray.tools.Logger;

import java.io.File;
import java.io.IOException;

public class Main {
    public static void main(String[] args) throws IOException, InterruptedException {
        DataReader dataReader = new DataReader();

        String resourcePath = args[0];

        dataReader.readRestaurantData(resourcePath);

        Logger.setFile(new File("logs.txt"));

        Restaurant restaurant = new Restaurant();
        restaurant.start();
    }
}
