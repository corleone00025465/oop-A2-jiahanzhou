// 2. Employee.java
public class Employee extends Person {
    private String employeeId;
    private String position;

    public Employee() {}

    public Employee(String name, int age, String phone, String employeeId, String position) {
        super(name, age, phone);
        this.employeeId = employeeId;
        this.position = position;
    }

    public String getEmployeeId() { return employeeId; }
    public void setEmployeeId(String employeeId) { this.employeeId = employeeId; }
    public String getPosition() { return position; }
    public void setPosition(String position) { this.position = position; }

    @Override
    public String toString() {
        return super.toString() + String.format(" │ ID: %-6s │ Pos: %s", employeeId, position);
    }
}