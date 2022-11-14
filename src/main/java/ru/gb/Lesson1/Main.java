package ru.gb.Lesson1;

import ru.gb.Lesson1.Figures.Circle;
import ru.gb.Lesson1.Figures.Figure;
import ru.gb.Lesson1.Figures.Square;
import ru.gb.Lesson1.Figures.Triangle;

public class Main {
    public static void main(String[] args) {
        Person person = new Person.Builder()
                .withFirstName("Ivan")
                .withLastName("Ivanov")
                .withMiddleName("Ivanovich")
                .withCountry("Russia")
                .withPhone("+79876543210")
                .withAddress("Freedom Street, 1")
                .withAge(30)
                .withGender("male")
                .build();
        System.out.println(person.toString());

        Figure circle = new Circle(4);
        Figure square = new Square(3);
        Figure triangle = new Triangle(2, 2,3);

        Figure[] figures = {circle, square, triangle};

        for (Figure figure : figures) {
            figure.draw();
            figure.area();
        }
    }
}
