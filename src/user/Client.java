package user;

import java.io.Serializable;
import java.util.Date;

public class Client extends User implements Serializable {
    private MembershipType membershipType;
    private double totalSpending;

    public enum MembershipType {
        REGULAR, SILVER, GOLD, PLATINUM
    }

    public Client(String userID, String fullName, Date dateOfBirth, String address, long phoneNumber, String email, boolean status, String password, String username, MembershipType membershipType, double totalSpending) {
        super(userID, fullName, dateOfBirth, address, phoneNumber, email, UserType.CLIENT, status, password, username);
        this.membershipType = membershipType;
        this.totalSpending = totalSpending;
    }

    public MembershipType getMembershipType() {
        return membershipType;
    }

    public void setMembershipType(MembershipType membershipType) {
        this.membershipType = membershipType;
    }

    public double getTotalSpending() {
        return totalSpending;
    }

    public void setTotalSpending(double totalSpending) {
        this.totalSpending = totalSpending;
    }



    @Override
    public String toString() {
        return super.toString() + ", MembershipType: " + membershipType + ", TotalSpending: " + totalSpending;
    }
}
