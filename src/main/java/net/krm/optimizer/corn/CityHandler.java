package net.krm.optimizer.corn;


import net.krm.optimizer.corn.utils.Calculator;

import java.util.*;

/**
 * Класс - Фабрика для работы с городами
 * */
public class CityHandler {

    /**
     * Заданныег города условной области районного значения над которыми будет произволится обработка
     * (исходные искомые места для размещения районных дилерских центров(РДЦ))
     * */
    public List<City> cities = new LinkedList<>();

    /**
     * Города области районного значения, рассматриваются как потенциальные места для размещения РДЦ
     *
     * должны быть только города находящиеся на равном либо большем расстоянии
     * соответсвующему критерию удаленность от ОДЦ {@link City#RELATIVE_TO_CENTRAL_CITY_M}
     * */
    public Collection<City> citiesPossible = new HashSet<>();

    /**
     * Центр условной области - основной диллерский центр(ОДЦ)
     * */
    public City centralCity = null;

    /**
     * Создает {@link City} по долготе, широте, названию
     * и добавляет в фабрику
     * */
    public boolean addCity(float latitude, float longitude, int countServicedEquipment, String name) {
        return addCity(new City(latitude, longitude, countServicedEquipment, name));
    }

    /**
     * Добавляет {@link City}
     * */
    public boolean addCity(City city) {
        return cities.add(city);
    }

    public void setCities(List<City> cities) {
        this.cities = cities;
    }

    public List<City> getCities() {
        if (cities == null) {
            cities = new LinkedList<City>();
        }
        return cities;
    }

    /**
     * Устанавливает обл. центр
     * */
    public void setCentralCity(float latitude, float longitude, String name) {
        setCentralCity(new City(latitude, longitude, name));
    }

    public void setCentralCity(City centralCity) {
        this.centralCity = centralCity;
    }

    public City getCentralCity() {
        return centralCity;
    }

    public Collection<City> getCitiesPossible() {
        if (citiesPossible == null) {
            citiesPossible = new HashSet<City>();
        }

        return citiesPossible;
    }

    /**
     * расчет "коэффициента размещения"
     */
    public void calcCoefficientPlacement() {
        Calculator.calculationCoefficientPlacement(getCities());
    }

    /**
     * Исключение городов по критерию RELATIVE_TO_CENTRAL_CITY_M
     *
     * Из коллекции {@link #cities} отбираются города которые
     * находятся на расстоянии относительно ОДЦ ({@link #centralCity})
     * большем значения критерия RELATIVE_TO_CENTRAL_CITY_M (140000м)
     */
    public Collection<City> exclusionOfCitiesRelativeToCentral() {
        System.out.println("exclusionOfCitiesRelativeToCentral()... ");

        if (getCentralCity() == null) {
            System.out.println("не задан центр области");
            getCitiesPossible().clear();
            return getCitiesPossible();
        }

        if (!getCities().isEmpty()) {
            for (City city : getCities()) {
                if (City.RELATIVE_TO_CENTRAL_CITY_M + City.DELTA_M <= city.distance(getCentralCity())) {
                    getCitiesPossible().add(city);
                }
            }
        } else {
            getCitiesPossible().clear();
        }

        System.out.println("cities         count: " + getCities().size());
        System.out.println("citiesPossible count: " + getCitiesPossible().size());
        System.out.println("------------------------------------------------------------------------------------------------");

        return getCitiesPossible();
    }

    /**
     * Грода попадающие в зону обслуживани друг друга {@link City#SERVICE_ZONE_M}
     * */
    public Collection<SortedSet<City>> aggregatedCitiesByServiceArea = new HashSet<>();

    public Collection<SortedSet<City>> getAggregatedCitiesByServiceArea() {
        if (aggregatedCitiesByServiceArea == null) {
            aggregatedCitiesByServiceArea = new HashSet<>();
        }
        return aggregatedCitiesByServiceArea;
    }

    /**
     * Агрегирует города в множества по "зоне обслуживания" {@link City#SERVICE_ZONE_M}
     *
     * @return Collection<SortedSet<City>> - коллекция коллекций (множеств) городов({@link SortedSet<City>}),
     *         которые попадают в радиус равный параметру {@link City#SERVICE_ZONE_M}
     *         друг относительно друга и отсортированны по свойству {@link City#coefficientPlacement}
     */
    public Collection<SortedSet<City>> aggregatedCitiesByServiceArea() {
        System.out.println("aggregatedCitiesByServiceArea()...  ");


        Collection<SortedSet<City>> aggregatedByServiceAreaIn = new HashSet<>();
        SortedSet<City> citiesAggNew;
        boolean addInSet = false; // флаг добавлния нового города в существующее множество
        boolean addInSingleSet = false; // флаг добавлния одного нового города в новое созданное множество
        for (City cityNew : getCitiesPossible()) {
            //System.out.println("aggregatedCitiesByServiceArea()  cityNew=" + cityNew);

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

                } else if (!citiesAggNew.isEmpty()) {
                    citiesAggNew.add(cityNew);
                    aggregatedByServiceAreaIn.add(citiesAggNew);
                    addInSingleSet = false;
                }
            }

            // если город никуда не добавленн он одиночка и входит в новое множество
            if (addInSingleSet) {
                citiesAggNew = new TreeSet<City>(new ComparatorCityPlacement());
                citiesAggNew.add(cityNew);
                getAggregatedCitiesByServiceArea().add(citiesAggNew);
            }

            if (!aggregatedByServiceAreaIn.isEmpty()) {
                getAggregatedCitiesByServiceArea().addAll(aggregatedByServiceAreaIn);
                aggregatedByServiceAreaIn.clear();
            }
        }

        return getAggregatedCitiesByServiceArea();
    }

    /**
     * Возвращает коллекцию(множество) полученных городов для размщения РДЦ
     *
     * В элементах множеств агрегированных городов({@link SortedSet<City>}),
     * свойство {@link City#install} устанавливается в значение {@code false}
     * последний элемент множествах не изменяется  (сойство {@link City#install} остается в значении {@code true})
     *
     * @return {@code Set<City>} записываются элементы из коллекции {@link #citiesPossible}
     *         в которых свойство {@link City#install} равно значению {@code true}
     */
    public Set<City> getCitiesForDealerships() {
        System.out.println("getCitiesForDealerships()...");

        Set<City> cities = new HashSet<>();
        for (SortedSet<City> citiesAgg : getAggregatedCitiesByServiceArea()) {
            install(citiesAgg);
        }

        for (City city: getCitiesPossible()) {
            if (city.isInstall()) {
                cities.add(city);
            }
        }

        printAggregatedCitiesByServiceArea(getAggregatedCitiesByServiceArea());
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

    public void printAggregatedCitiesByServiceArea(Collection<SortedSet<City>> aggregatedCities) {
            System.out.println("aggregated cities by service area count: " + aggregatedCities.size());
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
        System.out.println("cities for dealerships count: " + cities.size());
        for (City city : cities) {
            System.out.println("                         " + city);
        }

        System.out.println("------------------------------------------------------------------------------------------------");
    }
}
