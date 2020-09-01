package net.krm.optimizer.figures.v3;

import net.krm.optimizer.colors.Color;
import net.krm.optimizer.colors.ColorException;
import net.krm.optimizer.colors.Colored;
import net.krm.optimizer.figures.v3.Helper.VectorAlgebra;

/**
 * Прямоугольник с горизонтальными и вертикальными сторонами, координаты вершин целые.
 * Предполагается, что будут передаваться только правильные значения координат, то есть левая верхняя
 * точка всегда будет левее и выше правой нижней.
 */
public class Rectangle extends Figure implements Colored {

    protected static final int COORDINATE_SHIFT_X = 0;
    protected static final int COORDINATE_SHIFT_Y = 0;
    protected static final int LENGTH_DEFAULT = 1;
    protected static final int WIDTH_DEFAULT = 1;
    protected static final int SAME_HEIGHT_SIDES = 2;
    protected static final int SAME_WIDTH_SIDES = 2;

    private Point2D leftTop;
    private Point2D rightBottom;

    public static String DEFAULT_COLOR = "RED";

    /**
     * Создает Rectangle по координатам углов - левого верхнего и правого нижнего,
     * и окрашеным в цвет соответствующив enum Color
     */
    public Rectangle(Point2D leftTop, Point2D rightBottom, Color color) throws ColorException {
        super(color);
        this.leftTop =leftTop;
        this.rightBottom = rightBottom;
    }

    /**
     * Создает Rectangle по координатам углов - левого верхнего и правого нижнего,
     * и окрашеным в цвет соответствующив enum Color
     */
    public Rectangle(Point2D leftTop, Point2D rightBottom, String colorString) throws ColorException {
        super(colorString);
        this.leftTop =leftTop;
        this.rightBottom = rightBottom;
    }

    /**
     * Создает Rectangle по координатам углов - левого верхнего, правого нижнего,
     * и окрашеным в цвет соответствующив enum Color
     */
    public Rectangle(int xLeft, int yTop, int xRight, int yBottom, Color color) throws ColorException {
        super(color);
        this.leftTop = new Point2D(xLeft, yTop);
        this.rightBottom = new Point2D(xRight, yBottom);
    }

    /**
     * Создает Rectangle по координатам углов - левого верхнего и правого нижнего, и окрашеным в цвет соответствующив enum Color
     */
    public Rectangle(int xLeft, int yTop, int xRight, int yBottom, String colorString) throws ColorException {
        super(colorString);
        this.leftTop = new Point2D(xLeft, yTop);
        this.rightBottom = new Point2D(xRight, yBottom);
    }

    /**
     * Создает Rectangle окрашеным в цвет соответствующив enum Color, левый нижний угол которого находится в начале координат,
     * а  длина (по оси X) и ширина (по оси Y) задаются.
     */
    public Rectangle(int length, int width, Color color) throws ColorException {
        this(COORDINATE_SHIFT_X, COORDINATE_SHIFT_Y - width,
                length, COORDINATE_SHIFT_Y,
                color);
    }

    /**
     * Создает Rectangle окрашеным в цвет соответствующив enum Color, левый нижний угол которого находится в начале координат,
     * а  длина (по оси X) и ширина (по оси Y) задаются.
     */
    public Rectangle(int length, int width, String colorString) throws ColorException {
        this(COORDINATE_SHIFT_X, COORDINATE_SHIFT_Y - width,
                length, COORDINATE_SHIFT_Y,
                colorString);
    }

    /**
     * Создает Rectangle с размерами (1,1), левый нижний угол которого находится в начале координат. И окрашеным в цвет соответствующив enum Color
     */
    public Rectangle(Color color) throws ColorException {
        this(COORDINATE_SHIFT_X, COORDINATE_SHIFT_Y - WIDTH_DEFAULT,
                LENGTH_DEFAULT, COORDINATE_SHIFT_Y,
                color);
    }

    /**
     * Создает Rectangle с размерами (1,1), левый нижний угол которого находится в начале координат. И окрашеным в цвет соответствующив enum Color
     */
    public Rectangle(String colorString) throws ColorException {
        this(COORDINATE_SHIFT_X, COORDINATE_SHIFT_Y - WIDTH_DEFAULT,
                LENGTH_DEFAULT, COORDINATE_SHIFT_Y,
                colorString);
    }

    /**
     * Возвращает левую верхнюю точку Rectangle.
     */
    public Point2D getTopLeft() {
        return leftTop;
    }

    /**
     * Возвращает правую нижнюю точку Rectangle.
     */
    public Point2D getBottomRight() {
        return rightBottom;
    }

    /**
     * Устанавливает левую верхнюю точку Rectangle.
     */
    public void setTopLeft(Point2D topLeft) {
        this.leftTop = topLeft;
    }

    /**
     * Устанавливает правую нижнюю точку Rectangle.
     */
    public void setBottomRight(Point2D bottomRight) {
        this.rightBottom = bottomRight;
    }

    /**
     * Возвращает длину прямоугольника.
     */
    public int getLength() {
        return rightBottom.getX() - leftTop.getX();
    }

    /**
     * Возвращает ширину прямоугольника.
     */
    public int getWidth() {
        return rightBottom.getY() - leftTop.getY();
    }

    /**
     * Передвигает Rectangle на (dx, dy).
     */
    public void moveRel(int dx, int dy) {
        leftTop.moveRel(dx, dy);
        rightBottom.moveRel(dx, dy);
    }

    /**
     * Увеличивает стороны Rectangle в (nx, ny) раз при сохранении координат левой верхней вершины.
     */
    public void enlarge(int nx, int ny) {
        rightBottom.setX(getTopLeft().getX() + (getLength() * nx));
        rightBottom.setY(getTopLeft().getY() + (getWidth() * ny));
    }

    /**
     * Возвращает площадь прямоугольника.
     */
    public double getArea() {
        return getLength() * getWidth();
    }

    /**
     * Возвращает периметр прямоугольника.
     */
    public double getPerimeter() {
        return (getLength() * Rectangle.SAME_HEIGHT_SIDES) + (getWidth() * Rectangle.SAME_WIDTH_SIDES);
    }

    /**
     * Определяет, лежит ли точка (x, y) внутри Rectangle. Если точка лежит на стороне, считается, что она лежит внутри.
     */
    public boolean isInside(int x, int y) {
        if ((leftTop.getX() <= x && x <= rightBottom.getX())
                && (leftTop.getY() <= y && y <= rightBottom.getY())) {
            return true;
        }
        return false;
    }

    /**
     * Определяет, лежит ли точка point внутри Rectangle. Если точка лежит на стороне, считается, что она лежит внутри.
     */
    public boolean isInside(Point2D point) {
        return isInside(point.getX(), point.getY());
    }

    /**
     * Определяет, пересекается  ли Rectangle с другим Rectangle.
     * Считается, что прямоугольники пересекаются, если у них есть хоть одна общая точка.
     */
    public boolean isIntersects(Rectangle rectangle) {
        if (VectorAlgebra.intersectLine(getTopLeft().getX(), getBottomRight().getX(),
                rectangle.getTopLeft().getX(), rectangle.getBottomRight().getX())
                && VectorAlgebra.intersectLine(getTopLeft().getY(), getBottomRight().getY(),
                rectangle.getTopLeft().getY(), rectangle.getBottomRight().getY())) {
            return true;
        }
        return false;
    }


    /**
     * Определяет, лежит ли rectangle целиком внутри текущего Rectangle.
     */
    public boolean isInside(Rectangle rectangle) {
        if (isInside(rectangle.getTopLeft()) && isInside(rectangle.getBottomRight())) {
            return true;
        }
        return false;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Rectangle rectangle = (Rectangle) o;

        if (getColor() != rectangle.getColor()) {
            return false;
        }
        if (!leftTop.equals(rectangle.leftTop)) {
            return false;
        }
        return rightBottom.equals(rectangle.rightBottom);
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;

        result = prime * result + leftTop.hashCode();
        result = prime * result + rightBottom.hashCode();

        return result;
    }
}
