import java.util.List;

public class SalesTransaction {
    private String transactionID; // format t-number
    private String transactionDate;
    private String clientID;
    private String salespersonID;
    private List<Object> purchasedItems; // Cars/Parts
    private double discount;
    private double totalAmount;
    private String notes;

    public SalesTransaction(String transactionID, String transactionDate, String clientID, String salespersonID, List<Object> purchasedItems, double discount, double totalAmount, String notes) {
        this.transactionID = transactionID;
        this.transactionDate = transactionDate;
        this.clientID = clientID;
        this.salespersonID = salespersonID;
        this.purchasedItems = purchasedItems;
        this.discount = discount;
        this.totalAmount = totalAmount;
        this.notes = notes;
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

    public List<Object> getPurchasedItems() {
        return purchasedItems;
    }

    public void setPurchasedItems(List<Object> purchasedItems) {
        this.purchasedItems = purchasedItems;
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
        this.purchasedItems.add(item);
    }

    public void displayTransactionDetails() {
        System.out.println("Transaction ID: " + transactionID);
        System.out.println("Transaction Date: " + transactionDate);
        System.out.println("Client ID: " + clientID);
        System.out.println("Salesperson ID: " + salespersonID);
        System.out.println("Purchased Items: " + purchasedItems.size() + " items");
        System.out.println("Discount: " + discount);
        System.out.println("Total Amount: " + totalAmount);
        System.out.println("Notes: " + notes);
    }
}
