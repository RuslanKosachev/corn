package net.krm.optimizer.corn;

import java.io.Serializable;
import java.util.*;

/**
 * Класс реализующий сущность - Город
 * */
public class City extends Сoordinates implements Serializable, Comparable<City> {

    /***
     * «Зона обслуживания», м
     */
    public static final int SERVICE_ZONE_M = 70000;

    /***
     * Критерий установки РДЦ относительно ОДЦ, м
     */
    public static final int RELATIVE_TO_CENTRAL_CITY_M = 140000;

    /**
     *  Допустимая разница между расстояниями "зоны обслуживания" и 2-мя городами, м
     * */
    public static final double DELTA_M = 500;

    /**
     * Название
     * */
    private  String name;

    /**
     * Количество обслуживаемой техники в «Зоне обслуживания» условного РДЦ,
     * принимается равным кол-ву техники работающий в ссответствующем районе шт
     * */
    private int countServicedEquipment;

    /**
     *  Кт - условный "коэффициент размещения"
     *
     *  Kт – коэффициент, зависящий от количества обслуживаемой техники
     *  в «Зоне обслуживания» условного РДЦ
     * */
    private float coefficientPlacement;

    /**
     * место установки РДЦ
     * */
    private boolean install = false;


    public City(float latitude, float longitude, String name) {
        super(latitude, longitude);

        this.name = name;
    }

    public City(float latitude, float longitude, int countServicedEquipment, String name) {
        super(latitude, longitude);

        setCountServiceEquipment(countServicedEquipment);
        setName(name);
    }

    public String getName() {
        if (name == null || name.trim().isEmpty()) {
            return "noName";
        }
        return name;
    }

    public void setName(String name) {
        Objects.requireNonNull(name);
        this.name = name.trim();
    }

    public int getCountServiceEquipment() {
        return countServicedEquipment;
    }

    public void setCountServiceEquipment(int countServicedEquipment) {
        this.countServicedEquipment = countServicedEquipment;
    }

    public float getCoefficientPlacement() {
        return coefficientPlacement;
    }

    public void setCoefficientPlacement(float coefficientPlacement) {
        this.coefficientPlacement = coefficientPlacement;
    }

    public boolean isInstall() {
        return install;
    }

    public void setInstall(boolean install) {
        this.install = install;
    }

    @Override
    public int compareTo(City o) {
        if (o == null) {
            return -1;
        }

        return getName().compareTo(o.getName());
    }

    @Override
    public String toString() {
        return String.format("г.%s:{equipment=%s coefficient=%s}\n", getName(), getCountServiceEquipment(), getCoefficientPlacement());
    }
}