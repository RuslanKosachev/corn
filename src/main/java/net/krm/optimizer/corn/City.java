package net.krm.optimizer.corn;

import java.io.Serializable;
import java.util.Objects;

/**
 * Класс реализующий сущность - Город
 * */
public class City extends Сoordinates implements Serializable, Comparable<City> {

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
     *  Кд - условный коэффициент доступности (размещения) Кд
     *
     *  Kд = Kт + Kти
     * */
    private float coefficientPlacement;

    /**
     * Kт – коэффициент, зависящий от количества обслуживаемой техники в «Зоне обслуживания» условного РДЦ
     * */
    private float coefficientServicedEquipment;

    /**
     * Kти – коэффициент, зависящий от удобства транспортной инфраструк-туры района
     *
     * Если районный и областной центры соединяются напрямую автомо-бильной дорогой федерального
     * значения и имеют класс «Автомаги-страль» или «Скоростная дорога», то KТИ = 1, если класс
     * «дорога обыч-ного типа (не скоростная дорога)», то KТИ =0,9; если районный и об-ластной
     * центры соединяются напрямую автомобильной дорогой регио-нального или межмуниципального
     * значения и имеют класс «Автомаги-страль» или «Скоростная дорога», то KТИ = 0,75, если
     * класс «дорога обычного типа (не скоростная дорога)», то KТИ =0,7; если районный и областной
     * центры соединяются напрямую автомобильной дорогой местного значения, то KТИ =0,5; если
     * районный и областной центры со-единяются между собой разными типами автомобильных дорог,
     * то ко-эффициент определяется составлением и решением «Золотой пропор-ции»
     */
    private float coefficientTransportInfrastructure;

    /**
     * Измнение коэффициэнтов
     * */
    private boolean changed = true;

    /**
     * является областным центром или районным
     * если районный центр то есть ссылка а областной
     * */
    private City centralCity;


    public City(float latitude, float longitude, String name) {
        super(latitude, longitude);

        this.name = name;
    }

    public String getName() {
        if (name == null) {
            return "noName";
        }
        return name;
    }

    public void setName(String name) {
        Objects.requireNonNull(name);
        this.name = name.trim();
    }

    public int getCountServicedEquipment() {
        return countServicedEquipment;
    }

    public void setCountServicedEquipment(int countServicedEquipment) {
        this.countServicedEquipment = countServicedEquipment;
    }

    public float getCoefficientPlacement() {
        if (changed) {
            coefficientPlacement = getCoefficientServicedEquipment() + getCoefficientTransportInfrastructure();
        }

        return coefficientPlacement;
    }

    public float getCoefficientServicedEquipment() {
        return coefficientServicedEquipment;
    }

    public void setCoefficientServicedEquipment(float coefficientServicedEquipment) {
        this.coefficientServicedEquipment = coefficientServicedEquipment;
        changed = true;
    }

    public float getCoefficientTransportInfrastructure() {
        return coefficientTransportInfrastructure;
    }

    public void setCoefficientTransportInfrastructure(float coefficientTransportInfrastructure) {
        this.coefficientTransportInfrastructure = coefficientTransportInfrastructure;
        changed = true;
    }

    public City getCentralCity() {
        return centralCity;
    }

    public void setCentralCity(City centralCity) {
        this.centralCity = centralCity;
    }

    @Override
    public int compareTo(City o) {
        if (o == null) {
            return -1;
        }

        return getName().compareTo(o.getName());
    }
}