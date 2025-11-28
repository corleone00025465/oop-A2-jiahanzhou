// Employee.java
/**
 * Part 1: 具体类 (Concrete Class) - 继承自 Person。
 * 跟踪主题公园的员工，必须包含至少 2 个员工特有的实例变量 (position, hourlyRate)。
 * 体现了继承的 OOP 原则。
 */
public class Employee extends Person {
    private String position;
    private double hourlyRate;

    // 默认构造函数
    public Employee() {
        super(); // 调用 Person 的默认构造函数
        this.position = "Trainee";
        this.hourlyRate = 0.0;
    }

    // 参数化构造函数 (调用父类的构造函数)
    public Employee(String name, int age, String id, String position, double hourlyRate) {
        super(name, age, id);
        this.position = position;
        this.hourlyRate = hourlyRate;
    }

    // Getters and Setters for Employee 属性
    public String getPosition() { return position; }
    public void setPosition(String position) { this.position = position; }
    public double getHourlyRate() { return hourlyRate; }
    public void setHourlyRate(double hourlyRate) { this.hourlyRate = hourlyRate; }

    @Override
    public String toString() {
        return "Employee: " + super.toString() + ", Position: " + position + ", Hourly Rate: $" + hourlyRate;
    }
}
