package net.krm.optimizer.figures.v3;

import net.krm.optimizer.colors.Color;
import net.krm.optimizer.colors.ColorException;
import net.krm.optimizer.colors.Colored;
import net.krm.optimizer.figures.v3.Helper.VectorAlgebra;

public class Circle extends Figure implements Colored{

    protected static final int COORDINATE_SHIFT_X = 0;
    protected static final int COORDINATE_SHIFT_Y = 0;
    protected static final int RADIUS_DEFAULT = 1;
    protected static final int SQUARE_DEGREE = 2;

    private Point2D center;
    private int radius;

    /**
     * Создает Circle по координатам центра, значению радиуса, окрашеным в цвет соответствующив enum Color.
     */
    public Circle(Point2D center, int radius, Color color) throws ColorException {
        super(color);
        this.center = center;
        this.radius = radius;
    }

    /**
     * Создает Circle по координатам центра, значению радиуса, окрашеным в цвет соответствующив enum Color.
     */
    public Circle(Point2D center, int radius, String colorString) throws ColorException {
        super(colorString);
        this.center = center;
        this.radius = radius;
    }

    /**
     * Создает Circle по координатам центра, значению радиуса, окрашеным в цвет соответствующив enum Color
     */
    public Circle(int xCenter, int yCenter, int radius, Color color) throws ColorException {
        super(color);
        this.center = new Point2D(xCenter, yCenter);
        this.radius = radius;
    }

    /**
     * Создает Circle по координатам центра, значению радиуса, окрашеным в цвет соответствующив enum Color
     */
    public Circle(int xCenter, int yCenter, int radius, String colorString) throws ColorException {
        super(colorString);
        this.center = new Point2D(xCenter, yCenter);
        this.radius = radius;
    }

    /**
     * Создает Circle с центром в точке (0,0) указанного радиуса, окрашеным в цвет соответствующив enum Color.
     */
    public Circle(int radius, Color color) throws ColorException {
        this(Circle.COORDINATE_SHIFT_X, Circle.COORDINATE_SHIFT_Y,
                radius, color);
    }

    /**
     * Создает Circle с центром в точке (0,0) указанного радиуса, окрашеным в цвет соответствующив enum Color.
     */
    public Circle(int radius, String colorString) throws ColorException {
        this(Circle.COORDINATE_SHIFT_X, Circle.COORDINATE_SHIFT_Y,
                radius, colorString);
    }

    /**
     * Создает Circle с центром в точке (0,0) с радиусом 1, окрашеным в цвет соответствующив enum Color.
     */
    public Circle(Color color) throws ColorException {
        this(Circle.COORDINATE_SHIFT_X, Circle.COORDINATE_SHIFT_Y,
                Circle.RADIUS_DEFAULT, color);
    }

    /**
     * Создает Circle с центром в точке (0,0) с радиусом 1, окрашеным в цвет соответствующив enum Color.
     */
    public Circle(String colorString) throws ColorException {
        this(Circle.COORDINATE_SHIFT_X, Circle.COORDINATE_SHIFT_Y,
                Circle.RADIUS_DEFAULT, colorString);
    }

    /**
     * Устанавливает центр Circle.
     */
    public void setCenter(Point2D center) {
        this.center = center;
    }

    /**
     * Устанавливает радиус Circle.
     */
    public void setRadius(int radius) {
        this.radius = radius;
    }

    /**
     * Возвращает центр Circle.
     */
    public Point2D getCenter() {
        return center;
    }

    /**
     * Возвращает радиус Circle.
     */
    public int getRadius() {
        return radius;
    }

    /**
     * Передвигает Circle на (dx, dy).
     */
    public void moveRel(int dx, int dy) {
        // REVU не пишите this., если нет необходимости
        center.moveRel(dx, dy);
    }

    /**
     * Увеличивает радиус Circle в n раз, не изменяя центра.
     */
    public void enlarge(int n) {
        radius *= n;
    }

    /**
     * Возвращает площадь круга.
     */
    public double getArea() {
        return Math.PI * Math.pow(radius, SQUARE_DEGREE);
    }

    /**
     * озвращает периметр окружности.
     */
    public double getPerimeter() {
        return Math.PI * 2 * this.radius;
    }

    /**
     * Определяет, лежит ли точка (x, y) внутри Circle. Если точка лежит на окружности, считается, что она лежит внутри.
     */
    public boolean isInside(int x, int y) {
        double s = VectorAlgebra.distance(center.getX(), center.getY(), x, y);
        return s <= radius;
    }

    /**
     * Определяет, лежит ли точка point внутри Circle. Если точка лежит на окружности, считается, что она лежит внутри.
     */
    public boolean isInside(Point2D point) {
        return isInside(point.getX(), point.getY());
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Circle circle = (Circle) o;

        if (radius != circle.getRadius()) {
            return false;
        }
        if (getColor() != circle.getColor()) {
            return false;
        }
        return center.equals(circle.getCenter());
    }

    @Override
    public int hashCode() {
        int result = center.hashCode();
        result = 31 * result + radius;
        return result;
    }
}
