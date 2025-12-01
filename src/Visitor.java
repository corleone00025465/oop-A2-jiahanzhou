// Visitor.java
/**
 * Part 1: 具体类 (Concrete Class) - 继承自 Person。
 * 跟踪主题公园的访客，必须包含至少 2 个访客特有的实例变量 (ticketType, rideCount)。
 * 实现了 toCsvString() 方法用于 Part 6/7 的 I/O 机制。
 */
public class Visitor extends Person {
    private String ticketType;
    private int rideCount;

    // 默认构造函数
    public Visitor() {
        super();
        this.ticketType = "Standard";
        this.rideCount = 0;
    }

    // 参数化构造函数 (调用父类的构造函数)
    public Visitor(String name, int age, String id, String ticketType, int rideCount) {
        super(name, age, id);
        this.ticketType = ticketType;
        this.rideCount = rideCount;
    }

    // Getters and Setters for Visitor 属性
    public String getTicketType() { return ticketType; }
    public void setTicketType(String ticketType) { this.ticketType = ticketType; }
    public int getRideCount() { return rideCount; }
    public void setRideCount(int rideCount) { this.rideCount = rideCount; }

    // 用于 Part 6/7 I/O 机制：将访客数据格式化为 CSV 字符串
    public String toCsvString() {
        // 格式: name, age, id, ticketType, rideCount
        return getName() + "," + getAge() + "," + getId() + "," + ticketType + "," + rideCount;
    }

    @Override
    public String toString() {
        return "Visitor: " + super.toString() + ", Ticket: " + ticketType + ", Rides Taken: " + rideCount;
    }
}
