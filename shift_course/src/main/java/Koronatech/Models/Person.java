package Koronatech.Models;

public abstract class Person {
    protected String position;
    protected int id;
    protected String name;
    protected double salary;

    public Person (String position, int id, String name, double salary) {
        this.position = position;
        this.id = id;
        this.name = name;
        this.salary = salary;
    }

    public String getPosition() { return this.position; }

    public int getId() { return this.id; }

    public String getName() { return this.name; }

    public double getSalary() { return this.salary; }

    @Override
    public boolean equals(Object object) {
        if (object ==null || this.getClass()!=object.getClass()) return false;
        Person person = (Person) object;
        return person.id == this.id;
    }
}
