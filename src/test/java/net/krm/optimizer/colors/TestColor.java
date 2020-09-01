package net.krm.optimizer.colors;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;

import net.krm.optimizer.cars.Car;
import org.junit.Test;

import net.krm.optimizer.figures.v3.Circle;
import net.krm.optimizer.figures.v3.Figure;
import net.krm.optimizer.figures.v3.Point2D;
import net.krm.optimizer.figures.v3.Rectangle;
import net.krm.optimizer.figures.v3.Triangle;

public class TestColor {

    @Test
    public void testCompareColors() throws ColorException {
        Figure rect = new Rectangle(10, 20, 30, 40, Color.BLUE);
        Figure circle = new Circle(10, 20, 10, Color.BLUE);
        assertEquals(rect.getColor(), circle.getColor());
        circle.setColor(Color.GREEN);
        assertNotEquals(rect.getColor(), circle.getColor());
    }

    @Test
    public void testArrayOfColored() throws ColorException {
        Colored[] coloredObjects = new Colored[4];
        coloredObjects[0] = new Circle(10, 20, 10, Color.BLUE);
        coloredObjects[1] = new Rectangle(10, 20, 30, 40, Color.BLUE);
        coloredObjects[2] = new Triangle(new Point2D(2, 0), new Point2D(-1, 0), new Point2D(0, 2), Color.BLUE);
        coloredObjects[3] = new Car("Tesla", 1500, 400, Color.BLUE);

        for (Colored colored : coloredObjects)
            colored.setColor(Color.GREEN);
        for (Colored colored : coloredObjects)
            assertEquals(Color.GREEN, colored.getColor());
    }

    @Test
    public void testColors() {
        Color[] colors = Color.values();
        assertEquals(3, colors.length);
        assertEquals(0, Color.RED.ordinal());
        assertEquals(1, Color.GREEN.ordinal());
        assertEquals(2, Color.BLUE.ordinal());
    }

}
