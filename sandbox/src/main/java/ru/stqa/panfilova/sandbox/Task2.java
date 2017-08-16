package ru.stqa.panfilova.sandbox;

public class Task2 {

    public static void main(String[] args) {
        Point p1 = new Point(10,5);
        Point p2 = new Point(1,4);
        System.out.println("Расстояние между точкой (" + p1.x + "," + p1.y + ") и точкой (" + p2.x + "," + p2.y + ") = " + p1.distance(p2));
    }
}