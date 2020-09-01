package net.krm.optimizer.figures.v3;

import net.krm.optimizer.colors.ColorException;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;

public class TestPoint2D {

    @Test
    public void testPoint2D1() throws ColorException {
        Point2D p = new Point2D(10, 20);
        assertEquals(10, p.getX());
        assertEquals(20, p.getY());
    }

    @Test
    public void testPoint2D2() throws ColorException {
        Point2D p = new Point2D();
        assertEquals(0, p.getX());
        assertEquals(0, p.getY());
    }

    @Test
    public void testMoveTo() throws ColorException {
        Point2D p = new Point2D(10, 10);
        p.moveTo(20, 30);
        assertEquals(20, p.getX());
        assertEquals(30, p.getY());
    }

    @Test
    public void testEquals() throws ColorException {
        Point2D p1 = new Point2D(20, 30);
        Point2D p2 = new Point2D(20, 30);
        Point2D p3 = new Point2D(400, 30);
        assertEquals(p1, p2);
        assertNotEquals(p1, p3);
    }


}
