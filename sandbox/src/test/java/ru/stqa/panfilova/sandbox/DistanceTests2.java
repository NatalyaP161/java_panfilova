package ru.stqa.panfilova.sandbox;

import org.testng.Assert;
import org.testng.annotations.Test;

@Test
public class DistanceTests2 {
    public void testDistance() {
        Point p1 = new Point(10,5);
        Point p2 = new Point(1,4);
        Assert.assertEquals(p1.distance(p2.x,p2.y),Math.sqrt(Math.pow((p2.x - p1.x),2) + Math.pow((p2.y - p1.y),2)));
    }
}
