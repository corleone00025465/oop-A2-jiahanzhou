// 7. AssignmentTwo.java
public class AssignmentTwo {
    public static void main(String[] args) {
        System.out.println("PROG2004 Assignment 2 - Theme Park System\n");
        AssignmentTwo demo = new AssignmentTwo();
        demo.runDemo();
    }

    void runDemo() {
        // Part 3
        Ride dc = new Ride("DC Rivals", 8);
        dc.addVisitorToQueue(new Visitor("Alice", 25, "0400", "T001", "Gold"));
        dc.addVisitorToQueue(new Visitor("Bob", 30, "0401", "T002", "Silver"));
        dc.printQueue();

        // Part 4A & 4B
        Ride ww = new Ride("Wild West Falls", 6);
        ww.addVisitorToHistory(new Visitor("Tom", 20, "04", "W001", "Day"));
        ww.addVisitorToHistory(new Visitor("Anna", 20, "04", "W002", "Gold"));
        ww.printRideHistory();
        ww.sortRideHistory();
        ww.printRideHistory();

        // Part 5
        Ride gl = new Ride("Green Lantern", 4);
        gl.setOperator(new Employee("Mike", 35, "999", "E001", "Operator"));
        for (int i=1; i<=10; i++) gl.addVisitorToQueue(new Visitor("V"+i, 18+i, "04"+i, "T"+i, "Gold"));
        gl.runOneCycle();
        gl.runOneCycle();

        // Part 6 & 7
        Ride bat = new Ride("Batman", 6);
        bat.addVisitorToHistory(new Visitor("Bruce", 35, "888", "B001", "VIP"));
        bat.exportRideHistory("history.csv");
        Ride jl = new Ride("Justice League", 6);
        jl.importRideHistory("history.csv");
        jl.printRideHistory();
    }
}
