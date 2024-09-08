
package sales;
import part.AutoPart;
import user.Membership;
import vehicle.Car;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class SalesTransaction  implements Serializable {
    private String transactionID;
    private String transactionDate;
    private String clientID;
    private String salespersonID;
    private List<PurchasedItem> purchaseItems;
    // discount will base on the client membership type
    private double discount;
    private double totalAmount;
    private String notes;
    private boolean deleted;

    public SalesTransaction() {
        this.transactionID = "t-000";//t-XXX formatted
        this.transactionDate = "1970-01-01";
        this.clientID = "cl-000"; //cl-XXX formatted
        this.salespersonID = "sp-000";
        this.purchaseItems = new ArrayList<>();
        this.discount = 0.0;
        this.totalAmount = 0.0;
        this.notes = "No additional notes";
        this.deleted = true;
    }

    public SalesTransaction(String transactionID, String transactionDate, String clientID,
                            String salespersonID,List<PurchasedItem> purchaseItems,
                            double discount, double totalAmount, String notes) {
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

    public String getTransactionDate() {
        return transactionDate;
    }

    public void setTransactionDate(String transactionDate) {
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

    // Additional Methods
    public void addPurchasedItem(Object item) {
        this.purchaseItems.add((PurchasedItem) item);
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
        System.out.println("Purchased Items: " + purchaseItems.size() + " items");
        System.out.println("Discount: " + discount);
        System.out.println("Total Amount: " + totalAmount);
        System.out.println("Notes: " + notes);
        System.out.println("Deleted Status: " + deleted);
    }

    private double totalAmountCalculation(Membership membership) {
        double sum = purchaseItems.stream()
                .mapToDouble(PurchasedItem::getItemPrice)
                .sum();
        this.discount = membership.getDiscount();
        this.totalAmount = sum * (1 - discount);  // Apply discount and set totalAmount
        return totalAmount;  // Return the calculated total amount
    }

    @Override
    public String toString() {
        return "SalesTransaction{" +
                "transactionID='" + transactionID + '\'' +
                ", transactionDate='" + transactionDate + '\'' +
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
