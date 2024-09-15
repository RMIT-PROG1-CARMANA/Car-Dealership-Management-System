package user;

import crudhandlers.SalesTransactionCRUD;


import java.io.Serializable;
import java.util.Date;


public class Client extends User implements Serializable {
    private static final long serialVersionUID = 7922721516029818164L;

    private Membership membership;
    private double totalSpending;

    public void setMembership(Membership membership) {
        this.membership = membership;
    }

    public double getTotalSpending() {
        return totalSpending;
    }

    public Client(String userID, String fullName, Date dateOfBirth, String address, long phoneNumber, String email, boolean status, String password, String username, double totalSpending) {
        super(userID, fullName, dateOfBirth, address, phoneNumber, email, UserType.CLIENT, status, password, username);
        this.totalSpending = totalSpending;
        updateMembership();
    }

    // Get the current membership level
    public Membership getMembership() {
        return membership;
    }
    private void updateMembership() {
        this.membership = new Membership(totalSpending);
    }

    public void setTotalSpending(double totalSpending) {
        this.totalSpending = totalSpending;
        updateMembership();  // Recalculate membership based on updated total spending
    }

    @Override
    public String toString() {
        return super.toString() + membership + ", TotalSpending: " + totalSpending;
    }
}
