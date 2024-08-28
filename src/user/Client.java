package user;

import java.io.File;
import java.util.*;

public abstract class Client extends User {
    private String clientID;
    private MembershipType membershipType;
    private double totalSpending;

    public enum MembershipType {
        REGULAR, SILVER, GOLD, PLATINUM
    }

    public Client(String userID, String fullName, Date dateOfBirth, String address, String phoneNumber, String email, boolean status, String password, String clientID, MembershipType membershipType, double totalSpending) {
        super(userID, fullName, dateOfBirth, address, phoneNumber, email, UserType.CLIENT, status, password);
        this.clientID = clientID;
        this.membershipType = membershipType;
        this.totalSpending = totalSpending;
    }

    public void upgradeMembership() {
        if (totalSpending > 250000000) {
            membershipType = MembershipType.PLATINUM;
        } else if (totalSpending > 100000000) {
            membershipType = MembershipType.GOLD;
        } else if (totalSpending > 30000000) {
            membershipType = MembershipType.SILVER;
        } else {
            membershipType = MembershipType.REGULAR;
        }
    }
}
