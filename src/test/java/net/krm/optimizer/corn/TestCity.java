package net.krm.optimizer.corn;


import org.junit.Test;

import static org.junit.Assert.*;


public class TestCity {

    @Test
    public void testDistance() {
        City sratov        = new City(51.528594f, 46.027980f, "Саратов");
        City krasnoarmeysk = new City(51.017050f, 45.698992f, "Красноармейск");

        assertEquals(61000, sratov.distance(krasnoarmeysk), City.DELTA_M);
        System.out.println("sratov.distance(kalininsk) = " + sratov.distance(krasnoarmeysk));
    }
}
