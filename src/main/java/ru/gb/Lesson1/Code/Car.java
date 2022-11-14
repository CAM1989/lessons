package ru.gb.Lesson1.Code;

abstract class Car implements Engine {
    private Engine engine;
    private String color;
    private String name;

    void start() {
        System.out.println("Car starting");
    }

    abstract void open();

    public Engine getEngine() {
        return engine;
    }

    public void setEngine(Engine engine) {
        this.engine = engine;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Car(Engine engine, String color, String name) {
        this.engine = engine;
        this.color = color;
        this.name = name;
    }
}
//abstract class Car {
//    public Engine engine;
//    private String color;
//    private String name;
//    protected void start() {
//        System.out.println("Car starting");
//    }
//    abstract void open();
//    public Engine getEngine() {
//        return engine;
//    }
//    public void setEngine(Engine engine) {
//        this.engine = engine;
//    }
//    public String getColor() {
//        return color;
//    }
//    public void setColor(String color) {
//        this.color = color;
//    }
//    public String getName() {
//        return name;
//    }
//    public void setName(String name) {
//        this.name = name;
//    }
//}
