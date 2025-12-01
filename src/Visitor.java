// 3. Visitor.java   ← 重点！一定要有 getAge() 和 getName()
public class Visitor extends Person {
    private String ticketId;
    private String membershipType;

    public Visitor() {}

    public Visitor(String name, int age, String phone, String ticketId, String membershipType) {
        super(name, age, phone);
        this.ticketId = ticketId;
        this.membershipType = membershipType;
    }

    public String getTicketId() { return ticketId; }
    public void setTicketId(String ticketId) { this.ticketId = ticketId; }
    public String getMembershipType() { return membershipType; }
    public void setMembershipType(String membershipType) { this.membershipType = membershipType; }

    // 必须重写这两个方法，否则 Comparator 找不到！
    @Override
    public String getName() { return super.getName(); }
    @Override
    public int getAge() { return super.getAge(); }

    @Override
    public String toString() {
        return String.format("Visitor → %-12s │ Age:%3d │ Phone:%11s │ Ticket:%-8s │ Member:%s",
                getName(), getAge(), super.getPhone(), ticketId, membershipType);
    }

    public String toCSV() {
        return getName() + "," + getAge() + "," + super.getPhone() + "," + ticketId + "," + membershipType;
    }
}