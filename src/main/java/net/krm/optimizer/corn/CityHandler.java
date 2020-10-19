package net.krm.optimizer.corn;


import java.util.*;
import java.util.HashSet;

/**
 * Класс - Фабрика для работы с городами
 * */
public class CityHandler implements Comparator<City> {

    /***
     * «Зона обслуживания» в км
     */
    public static final int RELATIVE_TO_NEIGHORING = 70;

    /***
     * Критерий установки РДЦ относительно ОДЦ  в км
     */
    public static final int RELATIVE_TO_MAIN = 140;

    /**
     * Города области районного значения, рассматриваются как потенциальные места для размещения РДЦ
     * */
    public  List<City> cities = new LinkedList<>();

    /**
     * Города области районного значения, рассматриваются как потенциальные места для размещения РДЦ
     * */
    public  Set<City>  citiesPossible = new TreeSet<>(this);

    /**
     * Центр области
     * */
    public  City centralCity = null;

    private CityHandler() {}

    public static CityHandler getInstance() {
        if (instance == null) {
            instance = new CityHandler();
        }
        return instance;
    }

    /**
     * Возвращает количество городов
     * */
    public int getCount(){
        return cities.size();
    }


    /**
     * Создает {@link City} по долготе, широте, названию
     * и добавляет в фабрику
     * */
    public City add(Float latitude, Float longitude, String name) {
        City city = new City(latitude, longitude, name);
        add(city);
        return city;
    }

    /**
     * Добавляет {@link City}
     * */
    public boolean add(City city) {
        return cities.add(city);
    }

    /**
     * Устанавливает ссылку на обл. центр
     *
     *  @param centralCity центр области
     * */
    public void setCentralCity(City centralCity) {
        for (City city : cities) {
            city.setCentralCity(centralCity);
        }
        this.centralCity = centralCity;
    }

    public Set<City> centralCityFilter() {
        for (City city : cities) {
            if (city.distance(city.getCentralCity()) >= RELATIVE_TO_MAIN) {
                cities.remove(city);
            }
        }
        return cities;
    }

    /**
     *  Возвращает список вариантов городов где возможно необходимо разместить впомогательные СЦ
     * */
    // todo перенести в отдельный класс
    public List<List<City>> getOptions(Set<City> cities, Integer countOfSubsidiary){
        if (Objects.isNull(countOfSubsidiary) || getCountOfSubsidiary() <= 0) {
            return null;
        }
        if (Objects.isNull(cities) || cities.isEmpty()) {
            return null;
        }

        Queue<City> possibleСities = new ArrayDeque<>(cities);
        List<List<City>> possiblePlacements = new LinkedList<>();
        List<City> possiblePlacement = new ArrayList<>(getCountOfSubsidiary());

        while (possibleСities.peek() != null) {
            if (!possiblePlacement.isEmpty()) {
                for (City city : possiblePlacement) {
                    if (city.distance(possibleСities.peek()) > RELATIVE_TO_NEIGHORING) {
                        break;
                    }
                    possiblePlacement.add(possibleСities.poll());
                }
            } else {
                // первый элемент добавляем бе проверки
                possiblePlacement.add(possibleСities.poll());
            }

            if (possiblePlacement.size() >= getCountOfSubsidiary()) {
                possiblePlacements.add(possiblePlacement);
                possiblePlacement = new ArrayList<>(getCountOfSubsidiary());
            }
        }


        return  possiblePlacements;
    }

    // todo перенести в City
    @Override
    public int compare(City o1, City o2) {
        //Objects.equals(o1.getPlacementFactor(), o2.getPlacementFactor())
        if (o1 == null && o2 == null) {
            return 0;
        } else if (o1 == null) {
            return -1;
        } else if (o2 == null) {
            return 1;
        }


        if (o1.getCoefficientPlacement() < o2.getCoefficientPlacement()) {
            return 1;
        } else if (o1.getCoefficientPlacement() > o2.getCoefficientPlacement()) {
            return -1;
        } else {
            return 0;
        }
    }

    private static CityHandler instance;

}
