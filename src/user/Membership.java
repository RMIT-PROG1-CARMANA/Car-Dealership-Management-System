package user;

public class Membership {
    public enum MembershipType {
        SILVER, GOLD, PLATINUM
    }

    private MembershipType membershipType;
    private double totalSpending;

    public Membership(MembershipType membershipType, double totalSpending) {
        this.membershipType = membershipType;
        this.totalSpending = totalSpending;
    }

    // Getters and Setters
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
}
