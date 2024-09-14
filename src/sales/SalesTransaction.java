package sales;

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
