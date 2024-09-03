package user;

import sales.SalesTransaction;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

public class Client extends User implements Serializable {
    private Membership membership;
    private List<SalesTransaction> transactions;

    public enum MembershipType {
        REGULAR, SILVER, GOLD, PLATINUM
    }

    public Client(String userID, String fullName, Date dateOfBirth, String address, long phoneNumber, String email, boolean status, String password, String username, List<SalesTransaction> transactions, double v) {
        super(userID, fullName, dateOfBirth, address, phoneNumber, email, UserType.CLIENT, status, password, username);
        this.transactions = transactions;
        updateMembership();
    }

    public double calculateTotalSpending() {
        return transactions.stream().mapToDouble(SalesTransaction::getTotalAmount).sum();
    }

    private void updateMembership() {
        double totalSpending = calculateTotalSpending();
        this.membership = new Membership(totalSpending);
    }

    public Membership getMembership() {
        return membership;
    }

    public void addTransaction(SalesTransaction transaction) {
        transactions.add(transaction);
        updateMembership(); // Recalculate membership level after adding a transaction
    }

    @Override
    public String toString() {
        return super.toString() + ", Membership: " + membership + ", TotalSpending: " + calculateTotalSpending();
    }
}
