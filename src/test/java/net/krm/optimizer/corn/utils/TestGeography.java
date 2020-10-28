package net.krm.optimizer.corn.utils;

import net.krm.optimizer.corn.City;
import org.junit.Assert;
import org.junit.Test;

import static org.junit.Assert.*;

public class TestGeography {

    @Test
    public void testDistance() {
        City sratov        = new City(51.528594f, 46.027980f, "Саратов");
        City krasnoarmeysk = new City(51.017050f, 45.698992f, "Красноармейск");
        City kalininsk     = new City(51.503167f, 44.478237f, "Калининск");

        Assert.assertEquals(61000, Geography.distance(sratov, krasnoarmeysk), City.DELTA_M);
        System.out.println("Саратов - Красноармейск :" + Geography.distance(sratov, krasnoarmeysk));

        assertEquals(107000, Geography.distance(sratov, kalininsk), City.DELTA_M);
        System.out.println("Саратов - Калининск     :" + Geography.distance(sratov, kalininsk));
    }
}

