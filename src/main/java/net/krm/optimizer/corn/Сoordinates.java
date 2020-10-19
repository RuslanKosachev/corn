package net.krm.optimizer.corn;


import net.krm.optimizer.corn.utils.Geography;

import java.io.Serializable;
import java.util.Objects;

/**
 * Координаты
 */
public class Сoordinates implements СoordinatesI, DistanceI, KeyI, Serializable {


    /**
     * широта
     */
    private float latitude;

    /**
     * долгота
     */
    private float Longitude;

    public float getLatitude() {
        return latitude;
    }

    public void setLatitude(Float latitude) {
        Objects.requireNonNull(latitude);
        this.latitude = latitude;
    }

    public float getLongitude() {
        return Longitude;
    }

    public void setLongitude(Float longitude) {
        Objects.requireNonNull(longitude);
        Longitude = longitude;
    }

    @Override
    public float getKey() {
        return (latitude + Longitude);
    }

    /**
     * расстояние между 2-мя координатами
     */
    @Override
    public double distance(Сoordinates last) {
        return Geography.distance(this.getLatitude(), this.getLongitude(), last.getLatitude(), last.getLongitude());
    }

    public Сoordinates(float latitude, float longitude) {
        setLatitude(latitude);
        setLongitude(longitude);
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
