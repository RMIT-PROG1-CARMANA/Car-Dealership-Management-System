package sales;
import java.io.Serializable;
import part.AutoPart;
import vehicle.Car;

public class PurchasedItem implements Serializable {
    private Car car;
    private AutoPart part;

    // Constructor, getters, setters

    public PurchasedItem(Car car, AutoPart part) {
        this.car = car;
        this.part = part;
    }

    public Car getCar() {
        return car;
    }

    public void setCar(Car car) {
        this.car = car;
    }

    public AutoPart getPart() {
        return part;
    }

    public void setPart(AutoPart part) {
        this.part = part;
    }

    public double getItemPrice() {
        double carPrice = (car != null) ? car.getPrice() : 0.0;
        double partPrice = (part != null) ? part.getCost() : 0.0;
        return carPrice + partPrice;
    }

    @Override
    public String toString() {
        return "PurchasedItem {" +
                "car=" + (car != null ? car.toString() : "No car purchased") +
                ", part=" + (part != null ? part.toString() : "No part purchased") +
                '}';
    }
}
