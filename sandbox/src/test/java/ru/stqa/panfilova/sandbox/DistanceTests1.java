package ru.stqa.panfilova.sandbox;

import org.testng.Assert;
import org.testng.annotations.Test;

@Test
public class DistanceTests1 {
    public void testDistance() {
        Point p1 = new Point(25,9);
        Point p2 = new Point(25,4);
        Assert.assertEquals(p1.distance(p2),5.0);
    }
}
