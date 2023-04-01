package org.kolpakoveethendray.tools;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.kolpakoveethendray.config.RestaurantData;
import org.kolpakoveethendray.jsonModels.RestaurantDataModel;

import java.io.IOException;

//TODO: вынести objectMapper
//TODO: в ресурсах должны быть константы (лучше на вход в аргументах путь к файлу)
public class DataReader {
    public void readRestaurantData() throws IOException {
        var resourceStream = getClass().getClassLoader().getResourceAsStream("data/restaurant_data.txt");

        ObjectMapper objectMapper = new ObjectMapper();
        RestaurantDataModel restaurantDataModel = objectMapper.readValue(resourceStream, RestaurantDataModel.class);

        RestaurantData.setCountOfChefs(restaurantDataModel.countOfChefs());
        RestaurantData.setCountOfSits(restaurantDataModel.countOfSits());
        RestaurantData.setCountOfVisitors(restaurantDataModel.countOfVisitors());
        RestaurantData.setWorkHours(restaurantDataModel.workHours());
        RestaurantData.setStock(restaurantDataModel.stock());
        RestaurantData.setMainMenu(restaurantDataModel.mainMenu());
    }
}
