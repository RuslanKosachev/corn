package net.krm.optimizer.figures.v3.Helper;

import net.krm.optimizer.figures.v3.Point2D;

/**
 * математические операции над геометрическими объектами
 */
public class VectorAlgebra {

    /**
     * расстояние между 2-мя точками по координатам
     */
    public static double distance(int firstX, int firstY, int secondX, int secondY) {
        int catheterX = Math.abs(firstX - secondX);
        int catheterY = Math.abs(firstY - secondY);

        return Math.sqrt((catheterX * catheterX) + (catheterY * catheterY));
    }

    /**
     * расстояние между 2-мя точками щбьектами City
     */
    public static double distance(Point2D first, Point2D second) {
        return distance(first.getX(), first.getY(), second.getX(), second.getY());
    }

    /**
     * Проверяет, пересекаются ли два отрезка лежащие на общей прямой
     *
     * @return если пересекаются, то возвращает true
     */
    public static boolean intersectLine(int a, int b, int c, int d) {
        if (a > b) {
            a = a + b - (b = a);
        }
        if (c > d) {
            a = a + b - (b = a);
        }
        return Math.max(a, c) <= Math.min(b, d);
    }

    /**
     * Возвращает площадь треугольника
     */
    public static double getAreaOfTriangle(int x1, int y1, int x2, int y2, int x3, int y3) {
        double p = getPerimeterOfTriangle(x1, y1, x2, y2, x3, y3) / 2;
        return Math.sqrt(p
                * (p - distance(x1, y1, x2, y2))
                * (p - distance(x2, y2, x3, y3))
                * (p - distance(x3, y3, x1, y1)));
    }

    /**
     * Возвращает площадь треугольника
     */
    public static double getAreaOfTriangle(Point2D p1, Point2D p2, Point2D p3) {
        return getAreaOfTriangle(p1.getX(), p1.getY(), p2.getX(), p2.getY(), p3.getX(), p3.getY());
    }

    /**
     * Возвращает периметр треугольника.
     */
    public static double getPerimeterOfTriangle(int x1, int y1, int x2, int y2, int x3, int y3) {
        return distance(x1, y1, x2, y2)
                + distance(x2, y2, x3, y3)
                + distance(x3, y3, x1, y1);
    }

    /**
     * Возвращает периметр треугольника.
     */
    public static double getPerimeterOfTriangle(Point2D p1, Point2D p2, Point2D p3) {
        return getPerimeterOfTriangle(p1.getX(), p1.getY(), p2.getX(), p2.getY(), p3.getX(), p3.getY());
    }
}
