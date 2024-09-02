package user;

import java.util.Date;

public class Client extends User {
    private Membership membership;

    public Client(String userID, String fullName, Date dateOfBirth, String address, long phoneNumber, String email, boolean status, String password, String username, Membership.MembershipType membershipType, double totalSpending) {
        super(userID, fullName, dateOfBirth, address, phoneNumber, email, UserType.CLIENT, status, password, username);
        this.membership = new Membership(membershipType, totalSpending);
    }

    // Getters and Setters
    public Membership getMembership() {
        return membership;
    }

    public void setMembership(Membership membership) {
        this.membership = membership;
    }
}
