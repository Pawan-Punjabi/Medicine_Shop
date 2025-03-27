package models;

public abstract class Person {
    // Attributes
    private String name;
    private int age;

    // Constructor
    public Person(String name, int age) {
        this.name = name;
        this.age = age;
    }

    // Abstract method
    public abstract void displayDetails();

    // Getters for attributes
    public String getName() {
        return name;
    }

    public int getAge() {
        return age;
    }
}