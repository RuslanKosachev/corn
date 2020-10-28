package net.krm.optimizer.corn;

import java.util.Comparator;

/**
 *  Сортировка городов по значению параметра "коэффициент размещения" {@link City#countServicedEquipment}
 */
public class ComparatorCityPlacement implements Comparator<City> {
    @Override
    public int compare(City o1, City o2) {
        if (o1 == o2) {
            return 0;
        } else if (o1 == null) {
            return -1;
        } else if (o2 == null) {
            return 1;
        }

        if (o1.getCoefficientPlacement() < o2.getCoefficientPlacement()) {
            return -1;
        } else if (o1.getCoefficientPlacement() > o2.getCoefficientPlacement()) {
            return 1;
        } else {
            return 0;
        }
    }
}
