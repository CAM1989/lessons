package ru.gb.Lesson1.Code;

class Lorry extends Car implements Moveable, Stopable {

    public Lorry(Engine engine, String color, String name) {
        super(engine, color, name);
    }

    @Override
    public void move(){
        System.out.println("Car is moving");
    }
    @Override
    public void stop(){
        System.out.println("Car is stop");
    }

    @Override
    void open() {
        System.out.println("Car is open");
    }
}

//class Lorry extends Car, Moveable, Stopable {
//    public void move(){
//        System.out.println("Car is moving");
//    }
//    public void stop(){
//        System.out.println("Car is stop");
//    }
//}