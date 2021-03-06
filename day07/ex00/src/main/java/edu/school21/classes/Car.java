package edu.school21.classes;
import java.util.StringJoiner;

public class Car {
    private final String model;
    private final String color;
    private int mileage;

    public Car() {
        this.model = "default model";
        this.color = "default color";
        this.mileage = 0;
    }

    public Car(String model, String color, int mileage) {
        this.model = model;
        this.color = color;
        this.mileage = mileage;
    }

    public int increaseMileage(int value) {
        this.mileage += value;
        return mileage;
    }

    @Override
    public String toString() {
        return new StringJoiner(", ", Car.class.getSimpleName() + "[", "]")
                .add("model='" + model + "'")
                .add("color='" + color + "'")
                .add("mileage=" + mileage)
                .toString();
    }
}
