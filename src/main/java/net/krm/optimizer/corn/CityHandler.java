package net.krm.optimizer.corn;


import net.krm.optimizer.corn.utils.Calculator;

import java.util.*;

/**
 * Класс - Фабрика для работы с городами
 * */
public class CityHandler {

    /**
     * Заданные города области условной области над которыми бужет произволится обработка
     * */
    public List<City> cities = new LinkedList<>();

    /**
     * Города области районного значения, рассматриваются как потенциальные места для размещения РДЦ
     *
     * должны быть только города находящиеся на равном либо большем расстоянии
     * соответсвующему критерию удаленность от ОДЦ {@link City#RELATIVE_TO_CENTRAL_CITY_M}
     *
     * */
    public Set<City> citiesPossible = new HashSet<>();

    /**
     * Центр области условной области
     * */
    public City centralCity = null;

    /**
     * Создает {@link City} по долготе, широте, названию
     * и добавляет в фабрику
     * */
    public City addCity(Float latitude, Float longitude, String name) {
        City city = new City(latitude, longitude, name);
        addCity(city);
        return city;
    }

    /**
     * Добавляет {@link City}
     * */
    public boolean addCity(City city) {
        return cities.add(city);
    }

    /**
     * Возвращает количество заданных городов
     * */
    public int getCountCity(){
        return cities.size();
    }

    public List<City> getCities() {
        if (cities == null) {
            cities = new LinkedList<City>();
        }
        return cities;
    }

    /**
     * Устанавливает ссылку на обл. центр
     *
     *  @param centralCity центр области
     * */
    public void setCentralCity(City centralCity) {
        this.centralCity = centralCity;
    }

    public City getCentralCity() {
        return centralCity;
    }

    public Set<City> getCitiesPossible() {
        if (citiesPossible == null) {
            citiesPossible = new HashSet<City>();
        }

        return citiesPossible;
    }

    public void filterRelativeCentralCity() {
        System.out.println("filterRelativeCentralCity()... ");
        if (getCentralCity() == null) {
            System.out.println("не задан центр области");
            return;
        }

        for (City city : cities) {
            if (City.RELATIVE_TO_CENTRAL_CITY_M + City.DELTA_M <= city.distance(getCentralCity())) {
                getCitiesPossible().add(city);
            }
        }
    }

    public void calcCoefficientPlacement() {
        Calculator.calcCoefficientPlacement(cities);
    }


    /**
     * Грода попадающие в зону обслуживани друг друга {@link City#SERVICE_ZONE_M}
     * */
    public Set<Set<City>> aggregatedByServiceArea = new HashSet<>();

    public Set<Set<City>> getAggregatedByServiceArea() {
        if (aggregatedByServiceArea == null) {
            aggregatedByServiceArea = new HashSet<>();
        }
        return aggregatedByServiceArea;
    }

    /**
     * агрегируем города по "зоне обслуживания" {@link City#SERVICE_ZONE_M}
     */
    public void aggregatedByServiceArea() {
        System.out.println("aggregatedByServiceArea()...  ");

        if (getCitiesPossible().isEmpty()) {
            System.out.println("aggregatedByServiceArea() getCitiesPossible().isEmpty()=" + getCitiesPossible().isEmpty());
            return;
        }


        Set<Set<City>> aggregatedByServiceArea = new HashSet<>();
        Set<City> citiesAggNew;
        boolean addInSet = false; // флаг добавлния нового города
        boolean addInSingleSet = false; // флаг добавлния нового города в новое множество
        for (City cityNew : getCitiesPossible()) {
            System.out.println("aggregatedByServiceArea()  cityNew=" + cityNew);

            addInSingleSet = true;
            // итерация по агрегированным множествам
            for (Set<City> citiesAgg : getAggregatedByServiceArea()) {
                addInSet = true;
                citiesAggNew = new TreeSet<City>(new ComparatorCityPlacement());
                // итерация по агрегированному множеству
                for (City cityAgg : citiesAgg) {
                    System.out.println("aggregatedByServiceArea() cityAgg(" + cityAgg.getName() + ")<->cityNew(" + cityNew.getName() +  ") distance=" + cityAgg.distance(cityNew));
                    if ((City.SERVICE_ZONE_M + City.DELTA_M >= cityAgg.distance(cityNew))) {
                        citiesAggNew.add(cityAgg);
                    } else {
                        addInSet = false;
                    }
                }

                if (addInSet) {
                    citiesAgg.add(cityNew);
                    addInSingleSet = false;
                } else if (!citiesAggNew.isEmpty()) {
                    citiesAggNew.add(cityNew);
                    aggregatedByServiceArea.add(citiesAggNew);
                    addInSingleSet = false;
                }
            }

            // если город никуда не добавленн он одиночка и входит в новое множество
            if (addInSingleSet) {
                citiesAggNew = new TreeSet<City>(new ComparatorCityPlacement());
                citiesAggNew.add(cityNew);
                getAggregatedByServiceArea().add(citiesAggNew);
                System.out.println("aggregatedByServiceArea()  n=" + getAggregatedByServiceArea().size());
            }

            if (!aggregatedByServiceArea.isEmpty()) {
                getAggregatedByServiceArea().addAll(aggregatedByServiceArea);
                aggregatedByServiceArea.clear();
            }
        }
    }

    /**
     *  Возвращает список вариантов городов где возможно необходимо разместить впомогательные СЦ
     * */
    // todo перенести в отдельный класс
    /*public List<List<City>> getOptions(Set<City> cities, Integer countOfSubsidiary){
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
    }*/

}
