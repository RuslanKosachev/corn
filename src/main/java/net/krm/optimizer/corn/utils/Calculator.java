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

    /**
     * расчет "коэффициента размещения"
     *
     * City.coefficientPlacement[i] = City.countServiceEquipment[i] / countServiceEquipmentMax
     *
     * где: countServiceEquipment[i] – количество обслуживаемой техники в районе условного РДЦ i элемента cities, шт.
     *      countServiceEquipmentMax – максимальное количество обслуживаемой техники в районе из ряда рассматриваемых
     *      районных городов (cities) в условной области, шт.
     *
     * @param cities - ряд городав районного значения для РДЦ
     */
    public static void calculationCoefficientPlacement(List<City> cities) {
        System.out.println("calculationCoefficientPlacement().....");
        if (cities == null || cities.isEmpty()) {
            return;
        }

        cities.sort(new ComparatorCityEquipment());
        //System.out.println("calculationCoefficientPlacement cities=" + cities);

        int countServiceEquipmentMax = cities.get(cities.size() - 1).getCountServiceEquipment();
        if (0 >= countServiceEquipmentMax) {
            return;
        }

        System.out.println("countServiceEquipmentMax   :" + countServiceEquipmentMax);

        cities.forEach((city) -> {
            if (0 < city.getCountServiceEquipment()) {
                city.setCoefficientPlacement(
                        BigDecimal.valueOf(city.getCountServiceEquipment())
                                .divide(BigDecimal.valueOf(countServiceEquipmentMax),
                                        new MathContext(3, RoundingMode.HALF_UP))
                                .floatValue()
                );
            }
            System.out.println(String.format("city г.%-20s:{countEquipment=%-2s coefficient=%-4s}", city.getName(), city.getCountServiceEquipment(), city.getCoefficientPlacement()));
        });
        System.out.println("------------------------------------------------------------------------------------------------");
    }
}
