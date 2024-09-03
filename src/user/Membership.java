package user;

import java.io.Serializable;

public class Membership implements Serializable {
    private MembershipType membershipType;
    private static final double SILVER_THRESHOLD = 30_000_000;
    private static final double GOLD_THRESHOLD = 100_000_000;
    private static final double PLATINUM_THRESHOLD = 250_000_000;

    public enum MembershipType {
        REGULAR, SILVER, GOLD, PLATINUM
    }

    public Membership(double totalSpending) {
        this.membershipType = determineMembershipType(totalSpending);
    }

    public MembershipType getMembershipType() {
        return membershipType;
    }

    public void setMembershipType(MembershipType membershipType) {
        this.membershipType = membershipType;
    }

    private MembershipType determineMembershipType(double totalSpending) {
        if (totalSpending >= PLATINUM_THRESHOLD) {
            return MembershipType.PLATINUM;
        } else if (totalSpending >= GOLD_THRESHOLD) {
            return MembershipType.GOLD;
        } else if (totalSpending >= SILVER_THRESHOLD) {
            return MembershipType.SILVER;
        } else {
            return MembershipType.REGULAR;
        }
    }

    public double getDiscount() {
        return switch (membershipType) {
            case SILVER -> 0.05;
            case GOLD -> 0.10;
            case PLATINUM -> 0.15;
            default -> 0.00;
        };
    }

    @Override
    public String toString() {
        return "MembershipType: " + membershipType;
    }
}
