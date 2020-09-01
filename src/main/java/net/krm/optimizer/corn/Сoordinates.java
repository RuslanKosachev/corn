package net.krm.optimizer.corn;


import net.krm.optimizer.corn.utils.Geography;

import java.io.Serializable;
import java.util.Objects;

/**
 * Координаты
 */
public class Сoordinates implements СoordinatesI, DistanceI, Serializable {


    /**
     * широта
     */
    private Float latitude;

    /**
     * долгота
     */
    private Float Longitude;

    /**
     * высота
     */
    private Float seaLevel;


    public Float getLatitude() {
        return latitude;
    }

    public void setLatitude(Float latitude) {
        Objects.requireNonNull(latitude);
        this.latitude = latitude;
    }

    public Float getLongitude() {
        return Longitude;
    }

    public void setLongitude(Float longitude) {
        Objects.requireNonNull(longitude);
        Longitude = longitude;
    }

    public Float getSeaLevel() {
        return seaLevel;
    }

    public void setSeaLevel(Float seaLevel) {
        this.seaLevel = seaLevel;
    }

    public Float getKey() {
        return (latitude + Longitude);
    }

    /**
     * расстояние между 2-мя координатами
     */
    public double distance(Сoordinates last) {
        return Geography.distance(this.getLatitude(), this.getLongitude(), last.getLatitude(), last.getLongitude());
    }


    public Сoordinates(Float latitude, Float longitude) {
        setLatitude(latitude);
        setLongitude(longitude);
    }

    public Сoordinates(Float latitude, Float longitude, Float seaLevel) {
        this(latitude, longitude);
        this.seaLevel = seaLevel;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Сoordinates)) return false;
        Сoordinates that = (Сoordinates) o;
        return Objects.equals(getLatitude(), that.getLatitude()) &&
                Objects.equals(getLongitude(), that.getLongitude());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getLatitude(), getLongitude());
    }
}
