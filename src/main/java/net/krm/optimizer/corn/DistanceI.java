package net.krm.optimizer.corn;

import net.krm.optimizer.corn.utils.Geography;

public interface DistanceI {

    /**
     * Расстояние между текущей координатой и переданой в аргумент точками
     *
     * http://gis-lab.info/qa/great-circles.html
     *
     * @param  last координата {@link Сoordinates}
     * @return расстояние в км
     *
     */
    public double distance(Сoordinates last);
}
