// Ride.java
import java.util.Collections;
import java.util.Comparator;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.Queue;
import java.io.FileWriter; // Part 6
import java.io.IOException; // Part 6/7 异常处理
import java.io.BufferedReader; // Part 7
import java.io.FileReader; // Part 7
import java.util.NoSuchElementException;

/**
 * Part 2-7: 乘骑核心类。
 * 实现了 RideInterface 接口，并包含了 Part 3-7 的所有核心逻辑。
 * 聚合了 Employee (操作员) 和 Visitor (通过 Queue/LinkedList)。
 */
public class Ride implements RideInterface {
    private String rideName;
    private int minimumHeight;
    private Employee operator; // Part 1/2 要求：必须是 Employee 类型

    private int maxRider; // Part 5: 每周期最大乘载量
    private int numOfCycles; // Part 5: 运行周期计数

    // Part 3: 等待队列 (Queue, 使用 LinkedList 实现)
    private Queue<Visitor> waitingLine;
    // Part 4: 乘骑历史 (LinkedList)
    private LinkedList<Visitor> rideHistory;

    // 参数化构造函数
    public Ride(String rideName, int minimumHeight, Employee operator, int maxRider) {
        this.rideName = rideName;
        this.minimumHeight = minimumHeight;
        this.operator = operator;
        this.maxRider = maxRider;
        this.numOfCycles = 0;
        this.waitingLine = new LinkedList<>();
        this.rideHistory = new LinkedList<>();
    }

    // Getters and Setters (省略了大部分，只保留关键的)
    public String getRideName() { return rideName; }
    public void setOperator(Employee operator) { this.operator = operator; }
    public int getNumOfCycles() { return numOfCycles; }
    public int getWaitingSize() { return waitingLine.size(); }

    // --- Part 3 Implementations (Queue: add, remove, print) ---
    @Override
    public void addVisitorToQueue(Visitor visitor) {
        if (waitingLine.offer(visitor)) {
            System.out.println("[Queue SUCCESS] " + visitor.getName() + " 已加入 " + rideName + " 等待队列。");
        }
    }

    @Override
    public Visitor removeVisitorFromQueue() {
        Visitor removed = waitingLine.poll();
        if (removed != null) {
            System.out.println("[Queue SUCCESS] " + removed.getName() + " 已从 " + rideName + " 等待队列中移除 (FIFO)。");
            return removed;
        } else {
            // 注意: 在 Part 5 运行时，如果队列空了，不会打印此错误，因为 Part 5 自己会处理
            return null;
        }
    }

    @Override
    public void printQueue() {
        System.out.println("\n--- " + rideName + " 等待队列 (当前人数: " + waitingLine.size() + ") ---");
        if (waitingLine.isEmpty()) {
            System.out.println("队列当前为空。");
            return;
        }
        int index = 1;
        // 遍历 Queue 中的元素
        for (Visitor v : waitingLine) {
            System.out.println(index++ + ". " + v.toString());
        }
        System.out.println("------------------------------------------");
    }

    // --- Part 4A Implementations (Ride History: add, check, count, print) ---
    @Override
    public void addVisitorToHistory(Visitor visitor) {
        if (rideHistory.add(visitor)) {
            // 历史记录添加成功，此处可根据需要打印信息
            // System.out.println("[History SUCCESS] " + visitor.getName() + " 已添加到乘骑历史记录。");
        }
    }

    @Override
    public boolean checkVisitorFromHistory(Visitor visitor) {
        boolean exists = rideHistory.contains(visitor);
        String status = exists ? "在" : "不在此";
        System.out.println("[History CHECK] " + visitor.getName() + status + " " + rideName + " 的历史记录中。");
        return exists;
    }

    @Override
    public int numberOfVisitors() {
        int count = rideHistory.size();
        System.out.println("[History COUNT] " + rideName + " 历史记录中的访客总数: " + count);
        return count;
    }

    @Override
    public void printRideHistory() {
        System.out.println("\n--- " + rideName + " 乘骑历史记录 (总数: " + rideHistory.size() + ") ---");
        // Part 4A 要求: 必须使用 Iterator 遍历集合
        Iterator<Visitor> iterator = rideHistory.iterator();
        while (iterator.hasNext()) {
            System.out.println(iterator.next().toString());
        }
        System.out.println("------------------------------------------");
    }

    // --- Part 4B Implementation (Sorting) ---
    public void sortRideHistory(Comparator<Visitor> comparator) {
        // 使用 Collections.sort() 与自定义 Comparator
        Collections.sort(rideHistory, comparator);
        System.out.println("[History SORT] 乘骑历史记录已使用自定义 Comparator 排序。");
    }

    // --- Part 5 Implementation (Run Cycle) ---
    @Override
    public void runOneCycle() {
        System.out.println("\n--- 模拟运行 " + rideName + " 的一个周期 (Operator: " + (operator != null ? operator.getName() : "None") + ") ---");

        // 1. 检查操作员 (Part 5 要求)
        if (operator == null) {
            System.out.println("[CYCLE FAILURE] 错误: 未分配乘骑操作员。无法运行。");
            return;
        }
        // 2. 检查队列 (Part 5 要求)
        if (waitingLine.isEmpty()) {
            System.out.println("[CYCLE WARNING] 警告: 等待队列为空。乘骑没有客人可运行。");
            return;
        }

        // 计算本周期实际可乘载的人数
        int ridersToTake = Math.min(maxRider, waitingLine.size());
        System.out.println("[CYCLE INFO] 本周期最大乘载 " + maxRider + " 人，实际将带走队列中的 " + ridersToTake + " 名访客。");

        // 3. 将访客从 Queue 移动到 History
        for (int i = 0; i < ridersToTake; i++) {
            Visitor rider = waitingLine.poll(); // 使用 poll() 移除队首访客
            if (rider != null) {
                rider.setRideCount(rider.getRideCount() + 1); // 更新访客乘骑次数
                addVisitorToHistory(rider); // 加入历史记录 (Part 4A)
            }
        }

        numOfCycles++;
        System.out.println("[CYCLE SUCCESS] 周期完成。总共运行周期: " + numOfCycles);
    }

    // --- Part 6 Implementation (Writing to a file) ---
    // 使用 FileWriter 将 Ride History 导出为 CSV
    public void exportRideHistory(String filename) {
        // 使用 try-with-resources 语句确保 FileWriter 被自动关闭
        // 错误修正: 移除了多余的 'new' 关键字
        try (FileWriter writer = new FileWriter(filename)) {
            if (rideHistory.isEmpty()) {
                System.out.println("[EXPORT WARNING] 历史记录为空，导出的文件将是空的。");
            }

            // 写入每个 Visitor 的 CSV 格式字符串到新行
            for (Visitor v : rideHistory) {
                writer.write(v.toCsvString() + "\n");
            }
            System.out.println("[EXPORT SUCCESS] 乘骑历史记录成功导出到文件: " + filename);

        } catch (IOException e) {
            System.err.println("[EXPORT ERROR] 写入文件失败: " + filename + ". 错误信息: " + e.getMessage());
        }
    }

    // --- Part 7 Implementation (Reading from a file) ---
    // 使用 BufferedReader 从文件中导入 CSV 数据并重新创建 Visitor 对象
    public void importRideHistory(String filename) {
        // 导入前先清空当前历史记录，确保只包含导入的数据
        rideHistory.clear();
        int importedCount = 0;

        // 使用 try-with-resources 语句确保 BufferedReader/FileReader 被自动关闭
        try (BufferedReader reader = new BufferedReader(new FileReader(filename))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(","); // 按逗号分隔 CSV 值

                // 预期 CSV 格式: name, age, id, ticketType, rideCount
                if (parts.length == 5) {
                    try {
                        String name = parts[0].trim();
                        int age = Integer.parseInt(parts[1].trim());
                        String id = parts[2].trim();
                        String ticketType = parts[3].trim();
                        int rideCount = Integer.parseInt(parts[4].trim());

                        // 创建新的 Visitor 对象并添加到 LinkedList (History)
                        Visitor newVisitor = new Visitor(name, age, id, ticketType, rideCount);
                        rideHistory.add(newVisitor);
                        importedCount++;
                    } catch (NumberFormatException nfe) {
                        System.err.println("[IMPORT WARNING] 跳过格式错误的行 (数字格式错误): " + line);
                    }
                } else {
                    System.err.println("[IMPORT WARNING] 跳过格式错误的行 (字段数量不正确): " + line);
                }
            }
            System.out.println("[IMPORT SUCCESS] 成功从 " + filename + " 导入了 " + importedCount + " 条访客历史记录。");

        } catch (IOException e) {
            System.err.println("[IMPORT ERROR] 读取文件失败: " + filename + ". 错误信息: " + e.getMessage());
        }
    }
}