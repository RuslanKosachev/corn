package net.krm.optimizer.corn;

public interface DistanceI {

    /**
     * Расстояние между текущей координатой и переданой в аргумент точками
     *
     * @param  last координата {@link Сoordinates}
     * @return расстояние в км
     *
     */
    public double distance(Сoordinates last);
}
