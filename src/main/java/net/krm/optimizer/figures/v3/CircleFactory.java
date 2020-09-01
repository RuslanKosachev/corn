package net.krm.optimizer.figures.v3;

import net.krm.optimizer.colors.Color;
import net.krm.optimizer.colors.ColorException;

import java.util.ArrayList;
import java.util.Collection;

public class CircleFactory {

    /** Создает Circle по координатам центра и значению радиуса.  */
    public static Collection<Circle> col = new ArrayList<>();

    public static Circle createCircle(Point2D center, int radius, Color color) throws ColorException {
        Circle circle = new Circle(center, radius, color);
        col.add(circle);
        return circle;
    }

    public static Circle createCircle(Point2D center, int radius, String colorString) throws ColorException {
        return createCircle(center, radius, Color.colorFromString(colorString));
    }

    /** Возвращает количество Circle, созданных с помощью метода createCircle. */
    public static int getCircleCount(){
        return col.size();
    }

    /** Устанавливает количество Circle, созданных с помощью метода createCircle, равным 0 (иными словами, реинициализирует фабрику). */
    public static void reset(){
        col.clear();
    }
}
