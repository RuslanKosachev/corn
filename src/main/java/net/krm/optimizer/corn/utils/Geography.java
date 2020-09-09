package net.krm.optimizer.corn.utils;


import net.krm.optimizer.corn.Сoordinates;

/** Класс для вычислений географических показателей */
public class Geography {

    /** Средний радиус земли */
    private static int EARTH_RADIUS = 6372795;

    /**
     * Расстояние между двумя точками
     *
     * http://gis-lab.info/qa/great-circles.html
     *
     * @param  latitudeF  широта 1-й точки
     * @param  longitudeF долгота 1-й точки
     * @param  latitudeL  широта 2-й точки
     * @param  longitudeL  долгота 2-й точки
     * @return расстояние в км
     *
     */
    public static double distance(Float latitudeF, Float longitudeF, Float latitudeL, Float longitudeL) {

        // перевести координаты в радианы
        double lat1 = Math.toRadians(latitudeF);
        double lat2 = Math.toRadians(latitudeL);
        double long1 = Math.toRadians(longitudeF);
        double long2 = Math.toRadians(longitudeL);

        // косинусы и синусы широт и разницы долгот
        double cl1 = Math.cos(lat1);
        double cl2 = Math.cos(lat2);
        double sl1 = Math.sin(lat1);
        double sl2 = Math.sin(lat2);
        double delta = long2 - long1;
        double cdelta = Math.cos(delta);
        double sdelta = Math.sin(delta);

        // вычисления длины большого круга
        double y = Math.sqrt(Math.pow(cl2 * sdelta, 2) + Math.pow(cl1 * sl2 - sl1 * cl2 * cdelta, 2));
        double x = sl1 * sl2 + cl1 * cl2 * cdelta;

        double ad = Math.atan2(y, x);
        double dist = ad * EARTH_RADIUS;

        return dist;
    }

    /**
     * расстояние между 2-мя точками обьектами {@link Сoordinates}
     */
    public static double distance(Сoordinates first, Сoordinates last) {
        return distance(first.getLatitude(), first.getLongitude(), last.getLatitude(), last.getLongitude());
    }
}
