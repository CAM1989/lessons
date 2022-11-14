package ru.gb.Lesson1.Figures;

public class Square extends Figure{
    private final float length;

    public Square(float length) {
        this.length = length;
    }

    @Override
    public void draw() {
        System.out.println("Квадрат со сторонами " + length);
    }

    @Override
    public void area() {
        System.out.println("Площадь квадрата = " + length * length);
    }
}
