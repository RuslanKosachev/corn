package net.krm.optimizer.corn.utils;


import net.krm.optimizer.corn.City;
import net.krm.optimizer.corn.ComparatorCityEquipment;

import java.math.BigDecimal;
import java.math.MathContext;
import java.math.RoundingMode;
import java.util.*;

/**
 * Калькулятор
 */
public class Calculator {

    public static void calcCoefficientPlacement(List<City> cities) {
        if (cities == null || cities.isEmpty()) {
            return;
        }

        cities.sort(new ComparatorCityEquipment());
        //System.out.println("calcCoefficientPlacement cities=" + cities);

        int max = cities.get(cities.size() - 1).getCountServiceEquipment();
        if (0 >= max) {
            return;
        }
            System.out.println("calcCoefficientPlacement max                   =" + max);


        cities.forEach((city) -> {
            System.out.println("calcCoefficientPlacement city                  =" + city.getName());
            System.out.println("calcCoefficientPlacement countServicedEquipment=" + city.getCountServiceEquipment());
            if (0 < city.getCountServiceEquipment()) {
                city.setCoefficientPlacement(
                        BigDecimal.valueOf(city.getCountServiceEquipment())
                                .divide(BigDecimal.valueOf(max),
                                        new MathContext(3, RoundingMode.HALF_UP))
                                .floatValue()
                );
            }
            System.out.println("calcCoefficientPlacement coefficientPlacement  =" + city.getCoefficientPlacement());
        });
    }
}
