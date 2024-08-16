package user;

public class AutoPart {
    private String partID;
    private String partName;
    private String manufacturer;
    private String partNumber;
    private String condition;
    private String warranty;
    private double cost;
    private String notes;

    public AutoPart(String partID, String partName, String manufacturer, String partNumber, String condition, String warranty, double cost, String notes) {
        this.partID = partID;
        this.partName = partName;
        this.manufacturer = manufacturer;
        this.partNumber = partNumber;
        this.condition = condition;
        this.warranty = warranty;
        this.cost = cost;
        this.notes = notes;
    }

    public String getPartID() {
        return partID;
    }

    public void setPartID(String partID) {
        this.partID = partID;
    }

    public String getPartName() {
        return partName;
    }

    public void setPartName(String partName) {
        this.partName = partName;
    }

    public String getManufacturer() {
        return manufacturer;
    }

    public void setManufacturer(String manufacturer) {
        this.manufacturer = manufacturer;
    }

    public String getPartNumber() {
        return partNumber;
    }

    public void setPartNumber(String partNumber) {
        this.partNumber = partNumber;
    }

    public String getCondition() {
        return condition;
    }

    public void setCondition(String condition) {
        this.condition = condition;
    }

    public String getWarranty() {
        return warranty;
    }

    public void setWarranty(String warranty) {
        this.warranty = warranty;
    }

    public double getCost() {
        return cost;
    }

    public void setCost(double cost) {
        this.cost = cost;
    }

    public String getNotes() {
        return notes;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }

    @Override
    public String toString() {
        return "AutoPart{" +
                "partID='" + partID + '\'' +
                ", partName='" + partName + '\'' +
                ", manufacturer='" + manufacturer + '\'' +
                ", partNumber='" + partNumber + '\'' +
                ", condition='" + condition + '\'' +
                ", warranty='" + warranty + '\'' +
                ", cost=" + cost +
                ", notes='" + notes + '\'' +
                '}';
    }

    public void addPart(AutoPart part) {
        // Assuming you have a list of parts somewhere in your system
        // You can add the part to that list
    }

    public void updatePart(String partName, String manufacturer, String partNumber, String condition, String warranty, double cost, String notes) {
        if (partName != null) this.partName = partName;
        if (manufacturer != null) this.manufacturer = manufacturer;
        if (partNumber != null) this.partNumber = partNumber;
        if (condition != null) this.condition = condition;
        if (warranty != null) this.warranty = warranty;
        if (cost != 0) this.cost = cost;
        if (notes != null) this.notes = notes;
    }
}
