package sales;

import part.AutoPart;
import vehicle.Car;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class SalesTransaction implements Serializable {
    private String transactionID;
    private Date transactionDate;
    private String clientID;
    private String salespersonID;
    private List<PurchasedItem> purchaseItems;
    private double discount;
    private double totalAmount;
    private String notes;
    private boolean deleted;

    public SalesTransaction(String transactionID, Date transactionDate, String clientID, String salespersonID, List<PurchasedItem> purchaseItems, double discount, double totalAmount, String notes) {
        this.transactionID = transactionID;
        this.transactionDate = transactionDate;
        this.clientID = clientID;
        this.salespersonID = salespersonID;
        this.purchaseItems = purchaseItems != null ? new ArrayList<>(purchaseItems) : new ArrayList<>();
        this.discount = discount;
        this.totalAmount = totalAmount;
        this.notes = notes != null ? notes : "";
        this.deleted = false;
    }

    // Getters and Setters
    public String getTransactionID() {
        return transactionID;
    }

    public void setTransactionID(String transactionID) {
        this.transactionID = transactionID;
    }

    public Date getTransactionDate() {
        return transactionDate;
    }

    public void setTransactionDate(Date transactionDate) {
        this.transactionDate = transactionDate;
    }

    public String getClientID() {
        return clientID;
    }

    public void setClientID(String clientID) {
        this.clientID = clientID;
    }

    public String getSalespersonID() {
        return salespersonID;
    }

    public void setSalespersonID(String salespersonID) {
        this.salespersonID = salespersonID;
    }

    public List<PurchasedItem> getPurchaseItems() {
        return purchaseItems;
    }

    public void setPurchaseItems(List<PurchasedItem> purchaseItems) {
        this.purchaseItems = purchaseItems;
    }

    public double getDiscount() {
        return discount;
    }

    public void setDiscount(double discount) {
        this.discount = discount;
    }

    public double getTotalAmount() {
        return totalAmount;
    }

    public void setTotalAmount(double totalAmount) {
        this.totalAmount = totalAmount;
    }

    public String getNotes() {
        return notes;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }

    public boolean isDeleted() {
        return deleted;
    }

    public void setDeleted(boolean deleted) {
        this.deleted = deleted;
    }

    public void displayTransactionDetails() {
        System.out.println("Transaction ID: " + transactionID);
        System.out.println("Transaction Date: " + transactionDate);
        System.out.println("Client ID: " + clientID);
        System.out.println("Salesperson ID: " + salespersonID);

        // Print the number of purchased items
        System.out.println("Purchased Items: " + purchaseItems.size() + " items");

        // Loop through each purchased item and display details
        for (PurchasedItem item : purchaseItems) {
            AutoPart part = item.getPart();
            Car car = item.getCar();
            int qualityCar = item.getCarQuantity();
            int qualityPart = item.getPartQuantity();

            // Print the part and car details
            if (part != null) {
                System.out.println("Part Name: " + part.getPartName() + ", Part Quality: " + qualityPart);
            }
            if (car != null) {
                System.out.println("Car Model: " + car.getModel() + ", Car Quality: " + qualityCar);
            }
        }
        // Print the rest of the transaction details
        System.out.println("Discount: " + discount);
        System.out.println("Total Amount: " + totalAmount);
        System.out.println("Notes: " + notes);
        System.out.println("Deleted Status: " + deleted);
    }


    @Override
    public String toString() {
        return "SalesTransaction{" +
                "transactionID='" + transactionID + '\'' +
                ", transactionDate=" + transactionDate +
                ", clientID='" + clientID + '\'' +
                ", salespersonID='" + salespersonID + '\'' +
                ", purchasedItems=" + purchaseItems +
                ", discount=" + discount +
                ", totalAmount=" + totalAmount +
                ", notes='" + notes + '\'' +
                ", deleted=" + deleted +
                '}';
    }
}
