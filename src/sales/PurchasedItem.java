package sales;
import java.io.Serializable;
import part.AutoPart;
import vehicle.Car;

public class PurchasedItem implements Serializable {
    private Car car;
    private AutoPart part;
    private Integer carQuantity;
    private Integer partQuantity;

    public PurchasedItem(Car car, AutoPart part, Integer carQuatity, Integer partQuatity) {
        this.car = car;
        this.part = part;
        this.carQuantity = carQuatity;
        this.partQuantity = partQuatity;
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

    public Integer getCarQuantity() {
        return carQuantity;
    }

    public void setCarQuantity(Integer carQuantity) {
        this.carQuantity = carQuantity;
    }

    public Integer getPartQuantity() {
        return partQuantity;
    }

    public void setPartQuantity(Integer partQuantity) {
        this.partQuantity = partQuantity;
    }

    public double getItemPrice() {
        double carPrice = (car != null) ? car.getPrice() : 0.0;
        double partPrice = (part != null) ? part.getPrice() : 0.0;
        return (carPrice * (carQuantity != null ? carQuantity : 0)) + (partPrice * (partQuantity != null ? partQuantity : 0));
    }

    @Override
    public String toString() {
        return "PurchasedItem {" +
                "car=" + (car != null ? car.toString() : "No car purchased") +
                ", part=" + (part != null ? part.toString() : "No part purchased") +
                ", carQuality=" + carQuantity +
                ", partQuality=" + partQuantity +
                '}';
    }

}
