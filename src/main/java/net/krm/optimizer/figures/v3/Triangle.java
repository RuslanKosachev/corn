package net.krm.optimizer.figures.v3;

import net.krm.optimizer.colors.Color;
import net.krm.optimizer.colors.ColorException;
import net.krm.optimizer.colors.Colored;
import net.krm.optimizer.figures.v3.Helper.VectorAlgebra;

/**
 * Треугольник.
 * Координаты углов целые.
 * Предполагается, что будут передаваться только допустимые значения координат,
 * то есть три точки не лежат на одной прямой
 */
public class Triangle extends Figure implements Colored {

    private Point2D point1;
    private Point2D point2;
    private Point2D point3;

    /**
     * Создает Triangle по координатам трех точек и окрашеным в цвет соответствующив enum Color.
     */
    public Triangle(Point2D point1, Point2D point2, Point2D point3, Color color) throws ColorException {
        super(color);
        this.point1 = point1;
        this.point2 = point2;
        this.point3 = point3;
    }

    /**
     * Создает Triangle по координатам трех точек и окрашеным в цвет соответствующив enum Color.
     */
    public Triangle(Point2D point1, Point2D point2, Point2D point3, String colorString) throws ColorException {
        super(colorString);
        this.point1 = point1;
        this.point2 = point2;
        this.point3 = point3;
    }

    /**
     * Возвращает точку 1 треугольника.
     */

    public Point2D getPoint1() {
        return point1;
    }

    /**
     * бВозвращает точку 2 треугольника.
     */

    public Point2D getPoint2() {
        return point2;
    }

    /**
     * Возвращает точку 3 треугольника.
     */

    public Point2D getPoint3() {
        return point3;
    }

    /**
     * Устанавливает точку 1 треугольника.
     */
    public void setPoint1(Point2D point) {
        this.point1 = point;
    }

    /**
     * Устанавливает точку 2 треугольника.
     */
    public void setPoint2(Point2D point) {
        this.point2 = point;
    }

    /**
     * Устанавливает точку 3 треугольника.
     */
    public void setPoint3(Point2D point) {
        this.point3 = point;
    }

    /**
     * Возвращает длину стороны 1-2.
     */
    public double getSide12() {
        return VectorAlgebra.distance(point1, point2);
    }

    /**
     * Возвращает длину стороны 1-3.
     */
    public double getSide13() {
        return VectorAlgebra.distance(point1, point3);
    }

    /**
     * Возвращает длину стороны 2-3.
     */
    public double getSide23() {
        return VectorAlgebra.distance(point2, point3);
    }

    /**
     * Передвигает Triangle на (dx, dy).
     */
    public void moveRel(int dx, int dy) {
        this.point1.moveRel(dx, dy);
        this.point2.moveRel(dx, dy);
        this.point3.moveRel(dx, dy);
    }

    /**
     * Возвращает площадь треугольника
     */
    public double getArea() {
        return VectorAlgebra.getAreaOfTriangle(point1, point2, point3);
    }

    /**
     * Возвращает периметр треугольника.
     */
    public double getPerimeter() {
        return  VectorAlgebra.getPerimeterOfTriangle(point1, point2, point3);
    }

    /**
     * Определяет, лежит ли точка (x, y) внутри Triangle.
     * Если точка лежит на стороне треугольника, считается, что она лежит внутри.
     */
    public boolean isInside(int x, int y) {
        double innerArea12 = VectorAlgebra.getAreaOfTriangle(point1, point2, new Point2D(x, y));
        double innerArea23 = VectorAlgebra.getAreaOfTriangle(point2, point3, new Point2D(x, y));
        double innerArea31 = VectorAlgebra.getAreaOfTriangle(point3, point1, new Point2D(x, y));

        return this.getArea() >= innerArea12 + innerArea23 + innerArea31;
    }

    /**
     * Определяет, лежит ли точка point внутри Triangle.
     * Если точка лежит на стороне треугольника, считается, что она лежит внутри.
     */
    public boolean isInside(Point2D point) {
        return  isInside(point.getX(), point.getY());
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Triangle triangle = (Triangle) o;

        if (getColor() != triangle.getColor()) {
            return false;
        }

        if (point1 != null ? !point1.equals(triangle.point1) : triangle.point1 != null) return false;
        if (point2 != null ? !point2.equals(triangle.point2) : triangle.point2 != null) return false;

        return point3 != null ? point3.equals(triangle.point3) : triangle.point3 == null;
    }

    @Override
    public int hashCode() {
        int result = point1 != null ? point1.hashCode() : 0;
        result = 31 * result + (point2 != null ? point2.hashCode() : 0);
        result = 31 * result + (point3 != null ? point3.hashCode() : 0);
        return result;
    }
}
