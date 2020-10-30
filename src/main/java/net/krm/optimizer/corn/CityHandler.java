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
    public boolean addCity(Float latitude, Float longitude, int countServicedEquipment, String name) {
        return addCity(new City(latitude, longitude, countServicedEquipment, name));
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
        System.out.println("------------------------------------------------------------------------------------------------");
        System.out.println("citiesPossible: " + getCitiesPossible().size());
        System.out.println("------------------------------------------------------------------------------------------------");
    }

    public void calcCoefficientPlacement() {
        Calculator.calcCoefficientPlacement(cities);
    }


    /**
     * Грода попадающие в зону обслуживани друг друга {@link City#SERVICE_ZONE_M}
     * */
    public Set<SortedSet<City>> aggregatedCitiesByServiceArea = new HashSet<>();

    public Set<SortedSet<City>> getAggregatedCitiesByServiceArea() {
        if (aggregatedCitiesByServiceArea == null) {
            aggregatedCitiesByServiceArea = new HashSet<>();
        }
        return aggregatedCitiesByServiceArea;
    }

    /**
     * агрегируем города по "зоне обслуживания" {@link City#SERVICE_ZONE_M}
     */
    public void aggregatedCitiesByServiceArea() {
        System.out.println("aggregatedCitiesByServiceArea()...  ");

        Set<SortedSet<City>> aggregatedByServiceArea = new HashSet<>();
        SortedSet<City> citiesAggNew;
        boolean addInSet = false; // флаг добавлния нового города
        boolean addInSingleSet = false; // флаг добавлния нового города в новое множество
        for (City cityNew : getCitiesPossible()) {
            System.out.println("aggregatedCitiesByServiceArea()  cityNew=" + cityNew);

            addInSingleSet = true;
            // итерация по агрегированным множествам
            for (Set<City> citiesAgg : getAggregatedCitiesByServiceArea()) {
                addInSet = true;
                citiesAggNew = new TreeSet<City>(new ComparatorCityPlacement());
                // итерация по агрегированному множеству
                for (City cityAgg : citiesAgg) {
                    //System.out.println("aggregatedCitiesByServiceArea() cityAgg(" + cityAgg.getName() + ")<->cityNew(" + cityNew.getName() +  ") distance=" + cityAgg.distance(cityNew));
                    if ((City.SERVICE_ZONE_M + City.DELTA_M >= cityAgg.distance(cityNew))) {
                        citiesAggNew.add(cityAgg);
                    } else {
                        addInSet = false;
                    }
                }

                if (addInSet) {
                    citiesAgg.add(cityNew);
                    addInSingleSet = false;

                    install(citiesAgg);
                } else if (!citiesAggNew.isEmpty()) {
                    citiesAggNew.add(cityNew);
                    aggregatedByServiceArea.add(citiesAggNew);
                    addInSingleSet = false;

                    install(citiesAggNew);
                }
            }

            // если город никуда не добавленн он одиночка и входит в новое множество
            if (addInSingleSet) {
                citiesAggNew = new TreeSet<City>(new ComparatorCityPlacement());
                citiesAggNew.add(cityNew);
                getAggregatedCitiesByServiceArea().add(citiesAggNew);
                //System.out.println("aggregatedCitiesByServiceArea()  n=" + getAggregatedCitiesByServiceArea().size());
            }

            if (!aggregatedByServiceArea.isEmpty()) {
                getAggregatedCitiesByServiceArea().addAll(aggregatedByServiceArea);
                aggregatedByServiceArea.clear();
            }
        }

        printAggregatedCitiesByServiceArea(getAggregatedCitiesByServiceArea());
    }

    /**
     * Возвращает список полученных городов для устанавки РДЦ {@link City#SERVICE_ZONE_M}
     */
    public Set<City> getCitiesForDealerships() {
        System.out.println("getCitiesForDealerships()...");

        Set<City> cities = new HashSet<>();
        for (SortedSet<City> citiesAgg : getAggregatedCitiesByServiceArea()) {
            if (citiesAgg.isEmpty()) {
                continue;
            }
            if (citiesAgg.last().isInstall()) {
                cities.add(citiesAgg.last());
            }
        }

        printCitiesForDealerships(cities);

        return cities;
    }

    private void install(Collection<City> cities) {
        int i = 0;
        for (City c : cities) {
            i++;
            //System.out.println("aggregatedCitiesByServiceArea() out n:" + cities.size() + " i:" + i + " cityAgg:" + c.getName());
            if (i < cities.size()) {
                c.setInstall(false);
            }
        }
    }

    public void printAggregatedCitiesByServiceArea(Set<SortedSet<City>> aggregatedCities) {
        System.out.println("------------------------------------------------------------------------------------------------");
            System.out.println("aggregated cities by service area n:" + aggregatedCities.size());
        for (SortedSet<City> cities : aggregatedCities) {
            System.out.print("[");
            for (City city : cities) {
                System.out.print("\n  " + city + ", ");
            }
            System.out.println("],");
        }
        System.out.println("------------------------------------------------------------------------------------------------");
    }

    public void printCitiesForDealerships(Set<City> cities) {
        System.out.println("------------------------------------------------------------------------------------------------");
        System.out.println("cities for dealerships n:" + cities.size());
        for (City city : cities) {
            System.out.println("                         " + city);
        }
    }
}
