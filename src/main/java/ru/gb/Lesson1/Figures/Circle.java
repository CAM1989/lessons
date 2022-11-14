package ru.gb.Lesson1.Figures;

public class Circle implements Figure{
    private final float radius;

    public Circle(float radius) {
        this.radius = radius;
    }

    @Override
    public void draw() {
        System.out.println("Круг с радиусом " + radius);
    }

    @Override
    public void area() {
        System.out.println("Площадь круга = " + 3.14f * radius * radius);
    }
}