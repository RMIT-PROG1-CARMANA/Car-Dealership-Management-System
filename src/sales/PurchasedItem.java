package sales;
import java.io.Serializable;
import part.AutoPart;
import vehicle.Car;

public class PurchasedItem implements Serializable {
    private Car car;
    private AutoPart part;
    private Integer carQuality;
    private Integer partQuality;

    public PurchasedItem(Car car, AutoPart part, Integer carQuality, Integer partQuality) {
        this.car = car;
        this.part = part;
        this.carQuality = carQuality;
        this.partQuality = partQuality;
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

    public Integer getCarQuality() {
        return carQuality;
    }

    public void setCarQuality(Integer carQuality) {
        this.carQuality = carQuality;
    }

    public Integer getPartQuality() {
        return partQuality;
    }

    public void setPartQuality(Integer partQuality) {
        this.partQuality = partQuality;
    }

    public double getItemPrice() {
        double carPrice = (car != null) ? car.getPrice() : 0.0;
        double partPrice = (part != null) ? part.getPrice() : 0.0;
        return (carPrice * (carQuality != null ? carQuality : 0)) + (partPrice * (partQuality != null ? partQuality : 0));
    }

    @Override
    public String toString() {
        return "PurchasedItem {" +
                "car=" + (car != null ? car.toString() : "No car purchased") +
                ", part=" + (part != null ? part.toString() : "No part purchased") +
                ", carQuality=" + carQuality +
                ", partQuality=" + partQuality +
                '}';
    }
}
