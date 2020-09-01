package net.krm.optimizer.corn;

import java.io.Serializable;
import java.util.Objects;

public class City extends Сoordinates implements KeyI, Serializable {

    /**
     * Название
     * */
    private  String name;

    /**
     * фактор размещения станции
     * */
    private Float placementFactor;

    /**
     * является областным центром или районным
     * если районный центр то есть ссылка а областной
     * */
    private City centralCity;


    public City(Float latitude, Float longitude, String name) {
        super(latitude, longitude);

        Objects.requireNonNull(name);
        this.name = name;
    }

    public City(Float latitude, Float longitude, Float seaLevel, String name) {
        super(latitude, longitude, seaLevel);

        Objects.requireNonNull(name);
        this.name = name;
    }

    @Override
    public Float getKey() {
        return getLongitude() + getLatitude();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        Objects.requireNonNull(name);
        this.name = name;
    }

    public Float getPlacementFactor() {
        return placementFactor;
    }

    public void setPlacementFactor(Float placementFactor) {
        this.placementFactor = placementFactor;
    }

    public City getCentralCity() {
        return centralCity;
    }

    public void setCentralCity(City centralCity) {
        this.centralCity = centralCity;
    }
}