package org.kolpakoveethendray.tools;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.kolpakoveethendray.config.RestaurantData;
import org.kolpakoveethendray.jsonModels.RestaurantDataModel;

import java.io.IOException;

public class DataReader {

    private final ObjectMapper objectMapper;

    public DataReader() {
        objectMapper = new ObjectMapper();
    }
    public void readRestaurantData(String resourcePath) throws IOException {
        var resourceStream = getClass().getClassLoader().getResourceAsStream(resourcePath);

        RestaurantDataModel restaurantDataModel = objectMapper.readValue(resourceStream, RestaurantDataModel.class);

        RestaurantData.setCountOfChefs(restaurantDataModel.countOfChefs());
        RestaurantData.setCountOfSits(restaurantDataModel.countOfSits());
        RestaurantData.setCountOfVisitors(restaurantDataModel.countOfVisitors());
        RestaurantData.setWorkHours(restaurantDataModel.workHours());
        RestaurantData.setStock(restaurantDataModel.stock());
        RestaurantData.setMainMenu(restaurantDataModel.mainMenu());
    }
}
