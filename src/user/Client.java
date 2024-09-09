package user;

import sales.SalesTransaction;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Client extends User implements Serializable {
    private Membership membership;
    private final List<SalesTransaction> transactions;

    public Client(String userID, String fullName, Date dateOfBirth, String address, long phoneNumber, String email, boolean status, String password, String username, List<SalesTransaction> transactions) {
        super(userID, fullName, dateOfBirth, address, phoneNumber, email, UserType.CLIENT, status, password, username);
        this.transactions = (transactions != null) ? transactions : new ArrayList<>();
        updateMembership();  // Initialize membership based on existing transactions
    }

    // Calculate the total spending from all transactions
    public double calculateTotalSpending() {
        return transactions.stream().mapToDouble(SalesTransaction::getTotalAmount).sum();
    }

    // Update membership based on total spending
    private void updateMembership() {
        double totalSpending = calculateTotalSpending();
        this.membership = new Membership(totalSpending);
    }

    // Get the current membership level
    public Membership getMembership() {
        return membership;
    }

    // Add a new transaction and update membership accordingly
    public void addTransaction(SalesTransaction transaction) {
        transactions.add(transaction);
        updateMembership();  // Recalculate membership level after adding a transaction
    }

    @Override
    public String toString() {
        return super.toString() + ", Membership: " + membership + ", TotalSpending: " + calculateTotalSpending();
    }
}
