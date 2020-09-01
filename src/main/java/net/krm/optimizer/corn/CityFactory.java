package net.krm.optimizer.corn;


import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

public class CityFactory {

    public static Map<Float, City> cities = new HashMap<>();

    /**
     * Создает {@link City} по долготе, широте и названию
     * */
    public static City createCircle(Float latitude, Float longitude, String name) {
        City city = new City(latitude, longitude, name);
        cities.put(city.getKey(), city);
        return city;
    }

    /**
     * Возвращает количество Circle, созданных с помощью метода createCircle.
     * */
    public static int getCircleCount(){
        return cities.size();
    }

    /**
     *  Устанавливает количество Circle, созданных с помощью метода createCircle, равным 0
     * */
    public static void clear(){
        cities.clear();
    }
}
