// 6. Ride.java
import java.io.*;
import java.util.*;

public class Ride implements RideInterface {
    private String rideName;
    private int maxRider;
    private Employee operator;
    private int numOfCycles = 0;
    private Queue<Visitor> waitingQueue = new LinkedList<>();
    private LinkedList<Visitor> rideHistory = new LinkedList<>();

    public Ride() {}
    public Ride(String rideName, int maxRider) {
        this.rideName = rideName;
        this.maxRider = maxRider;
    }

    // getters & setters
    public String getRideName() { return rideName; }
    public void setRideName(String rideName) { this.rideName = rideName; }
    public int getMaxRider() { return maxRider; }
    public void setMaxRider(int maxRider) { this.maxRider = maxRider; }
    public Employee getOperator() { return operator; }
    public void setOperator(Employee operator) { this.operator = operator; }

    @Override public void addVisitorToQueue(Visitor v) { waitingQueue.offer(v); }
    @Override public void removeVisitorFromQueue() { waitingQueue.poll(); }

    @Override
    public void printQueue() {
        System.out.println("\n" + "=".repeat(100));
        System.out.println(" " + rideName + " 排队队列 (" + waitingQueue.size() + "人)");
        System.out.println("=".repeat(100));
        int i = 1;
        for (Visitor v : waitingQueue) {
            System.out.printf("%4d │ %s%n", i++, v);
        }
    }

    @Override public void addVisitorToHistory(Visitor v) { rideHistory.add(v); }
    @Override public boolean checkVisitorFromHistory(Visitor v) { return rideHistory.contains(v); }
    @Override public int numberOfVisitors() { return rideHistory.size(); }

    @Override
    public void printRideHistory() {
        System.out.println("\n" + "=".repeat(120));
        System.out.println(" " + rideName + " 乘车记录 (" + rideHistory.size() + "人次)");
        System.out.println("=".repeat(120));
        int i = 1;
        for (Visitor v : rideHistory) {
            System.out.printf("%4d │ %s%n", i++, v);
        }
    }

    @Override
    public void sortRideHistory() {
        Collections.sort(rideHistory, new VisitorComparator());
        System.out.println("排序完成：按年龄升序，同龄按姓名字母排序");
    }

    @Override
    public void runOneCycle() {
        if (operator == null) {
            System.out.println("无操作员，无法运行！");
            return;
        }
        int count = 0;
        while (count < maxRider && !waitingQueue.isEmpty()) {
            Visitor v = waitingQueue.poll();
            rideHistory.add(v);
            count++;
            System.out.println("上车：" + v.getName());
        }
        numOfCycles++;
        System.out.println("本轮结束，共 " + count + " 人乘车，今日已运行 " + numOfCycles + " 轮");
    }

    @Override
    public void exportRideHistory(String filename) {
        try (PrintWriter pw = new PrintWriter(new FileWriter(filename))) {
            pw.println("Name,Age,Phone,TicketID,Membership");
            for (Visitor v : rideHistory) pw.println(v.toCSV());
            System.out.println("导出成功 → " + filename);
        } catch (IOException e) { System.out.println("导出失败"); }
    }

    @Override
    public void importRideHistory(String filename) {
        rideHistory.clear();
        try (BufferedReader br = new BufferedReader(new FileReader(filename))) {
            br.readLine(); // 跳过表头
            String line;
            while ((line = br.readLine()) != null) {
                String[] p = line.split(",");
                Visitor v = new Visitor(p[0], Integer.parseInt(p[1]), p[2], p[3], p[4]);
                rideHistory.add(v);
            }
            System.out.println("导入成功，共 " + rideHistory.size() + " 条记录");
        } catch (Exception e) { System.out.println("导入失败"); }
    }
}
