package net.krm.optimizer.figures.v3.Helper;

import net.krm.optimizer.colors.ColorException;
import org.junit.Test;
import static org.junit.Assert.*;

import net.krm.optimizer.figures.v3.Point2D;

public class TestVectorAlgebra {

    private static final double DOUBLE_EPS = 1E-6;

    @Test
    public void testDistanceFxFySxSy() {
        assertEquals(new Double(10), (Double) VectorAlgebra.distance(10, 20, 16, 28));
        assertEquals(new Double(35), (Double) VectorAlgebra.distance(10, 10, 45, 10));
        assertEquals(new Double(75), (Double) VectorAlgebra.distance(10, 20, 10, 95));
    }

    @Test
    public void testDistancePoint2D() {
        assertEquals(10, VectorAlgebra.distance(new Point2D(10, 20), new Point2D(16, 28)), DOUBLE_EPS);
        assertEquals(35, VectorAlgebra.distance(new Point2D(10, 10), new Point2D(45, 10)), DOUBLE_EPS);
        assertEquals(75, VectorAlgebra.distance(new Point2D(10, 20), new Point2D(10, 95)), DOUBLE_EPS);
    }

    @Test
    public void testIntersectLineD() {
        assertTrue(VectorAlgebra.intersectLine(20, 40, 35, 40));
        assertTrue(VectorAlgebra.intersectLine(20, 40, 40, 45));
        assertTrue(VectorAlgebra.intersectLine(20, 40, -5, 20));
        assertFalse(VectorAlgebra.intersectLine(20, 40, 10, 15));
        assertFalse(VectorAlgebra.intersectLine(20, 40, 41, 45));
    }

    @Test
    public void testGetAreaOfTriangle() throws ColorException {
        Point2D point1 = new Point2D(2, 0);
        Point2D point2 = new Point2D(-1, 0);
        Point2D point3 = new Point2D(0, 2);
        assertEquals(3.0, VectorAlgebra.getAreaOfTriangle(point1, point2, point3), DOUBLE_EPS);
    }

    @Test
    public void testGetPerimeterOfTriangle() throws ColorException {
        Point2D point1 = new Point2D(2, 0);
        Point2D point2 = new Point2D(-1, 0);
        Point2D point3 = new Point2D(0, 2);
        assertEquals(8.06449510, VectorAlgebra.getPerimeterOfTriangle(point1, point2, point3), DOUBLE_EPS);
    }
}
