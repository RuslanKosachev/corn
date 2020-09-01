package net.krm.optimizer.figures.v3;

import net.krm.optimizer.colors.Color;
import net.krm.optimizer.colors.ColorErrorCode;
import net.krm.optimizer.colors.ColorException;
import net.krm.optimizer.colors.Colored;

import java.util.Objects;

public abstract class Figure  implements Colored {
	
	// REVU все фигуры имеют цвет, поэтому Color надо перенести сюда
	// REVU в классе должен быть конструктор по Color (а может, и по String colorString)
	// и наследники должны его вызывать через this(color)

    private Color color;

    public Figure(Color color) throws ColorException {
        if (Objects.isNull(color)) {
            throw new ColorException(ColorErrorCode.NULL_COLOR);
        }
        this.color = color;
    }
    public Figure(String colorString) throws ColorException {
        this.color = Color.colorFromString(colorString);
    }

    public abstract void moveRel(int dx, int dy);

    public abstract double getArea();

    public abstract double getPerimeter();

    public abstract boolean isInside(int x, int y);

    public abstract boolean isInside(Point2D point);

    public void setColor(Color color) throws ColorException {
        if (Objects.isNull(color)) {
            throw new ColorException(ColorErrorCode.NULL_COLOR);
        }
        this.color = color;
    }

    public void setColor(String colorString) throws ColorException {
        this.color = Color.colorFromString(colorString);
    }

    public Color getColor() {
        return this.color;
    }
}
