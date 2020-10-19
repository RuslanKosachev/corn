package net.krm.optimizer.corn.utils;


import net.krm.optimizer.corn.City;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.math.MathContext;
import java.math.RoundingMode;
import java.util.*;

/**
 * Калькулятор коэффициентов
 */
public class CoefficientCalc {

    public static void servicedEquipment(List<City> cities) {
        if (cities == null || cities.isEmpty()) {
            return;
        }

        int max;
        Collections.sort(cities,
                new Comparator<City>() {
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

                        if (o1.getCountServicedEquipment() < o2.getCountServicedEquipment()) {
                            return 1;
                        } else if (o1.getCountServicedEquipment() > o2.getCountServicedEquipment()) {
                            return -1;
                        } else {
                            return 0;
                        }
                    }
                }
        );

        max = cities.get(cities.size() - 1).getCountServicedEquipment();

        cities.forEach((city) -> {
            BigInteger.valueOf(city.getCountServicedEquipment());
            city.setCoefficientServicedEquipment(
                    BigDecimal.valueOf(city.getCountServicedEquipment())
                            .divide(BigDecimal.valueOf(max),
                                    new MathContext(3, RoundingMode.HALF_UP))
                            .floatValue()
            );
        });
    }
}
