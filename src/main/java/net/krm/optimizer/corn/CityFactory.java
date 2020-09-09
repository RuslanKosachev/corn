package net.krm.optimizer.corn;


import java.util.*;

/**
 * Фабрика для работы с городами
 * */
public class CityFactory implements Comparator<City> {

    /***
     * Критерий установки соседнего ВСЦ regarding the main
     */
    public static final Integer  RELATIVE_TO_NEIGHORING = 70;

    /***
     * Критерий установки ВСЦ относительно ГСЦ
     */
    public static final Integer RELATIVE_TO_MAIN = 140;

    /**
     * Города области
     * */
    public  Set<City> cities = new TreeSet<>(this);

    /**
     * Центр области
     * */
    public  City centralCity = null;

    /**
     * Необходимое кол-во впомогательных сервисных центров в данно области
     * */
    public  int countOfSubsidiary = 0;

    public static CityFactory getInstance(String value) {
        if (instance == null) {
            instance = new CityFactory(value);
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
     *  Устанавливает количество {@link City} равным 0
     * */
    public void clear(){
        cities.clear();
    }

    public int getCountOfSubsidiary() {
        return countOfSubsidiary;
    }

    public void setCountOfSubsidiary(int countOfSubsidiary) {
        this.countOfSubsidiary = countOfSubsidiary;
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

        if (o1.getPlacementFactor() == null) {
            return -1;
        } else if (o2.getPlacementFactor() == null) {
            return 1;
        }

        return o1.getPlacementFactor().compareTo(o2.getPlacementFactor());
    }

    private static CityFactory instance;

    private CityFactory(String value) {}
}
