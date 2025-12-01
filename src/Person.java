// Person.java
/**
 * Part 1: 抽象类 (Abstract Class) - 所有人或访客的顶级父类。
 * 必须包含至少 3 个实例变量 (name, age, id)。
 * 体现了抽象和封装的 OOP 原则。
 */
public abstract class Person {
    private String name;
    private int age;
    private String id;

    // 默认构造函数
    public Person() {
        this("Unknown", 0, "0000");
    }

    // 参数化构造函数
    public Person(String name, int age, String id) {
        this.name = name;
        this.age = age;
        this.id = id;
    }

    // Getters and Setters (体现封装)
    public String getName() { return name; }
    public void setName(String name) { this.name = name; }
    public int getAge() { return age; }
    public void setAge(int age) { this.age = age; }
    public String getId() { return id; }
    public void setId(String id) { this.id = id; }

    // 重写 toString() 方法，方便打印
    @Override
    public String toString() {
        return "Name: " + name + ", Age: " + age + ", ID: " + id;
    }
}
