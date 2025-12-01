// AssignmentTwo.java

/**
 * PROG2004 Object Oriented Programming - Assessment 2
 * 主题公园乘骑访客管理系统 (PRVMS)
 * 主类：包含 main 方法和所有 Part 3-7 的演示方法。
 * 负责实例化对象并调用 Ride 类中的核心逻辑。
 */
public class AssignmentTwo {

    // 定义 Part 6/7 要使用的文件名
    private static final String HISTORY_FILE = "ride_history_backup.csv";

    /**
     * main 方法：程序的入口点。
     */
    public static void main(String[] args) {
        // 实例化 AssignmentTwo 类，以便调用非静态的演示方法
        AssignmentTwo assignment = new AssignmentTwo();

        System.out.println("--- PROG2004 A2 演示开始 ---");

        // 依次调用 Part 3 到 Part 7 的演示方法，这是展示成果的流程

        System.out.println("\n==================== Part 3 演示: 等待队列 (Queue) ====================");
        assignment.partThree();

        System.out.println("\n==================== Part 4A 演示: 乘骑历史 (LinkedList) ====================");
        assignment.partFourA();

        System.out.println("\n==================== Part 4B 演示: 历史记录排序 (Comparator) ====================");
        assignment.partFourB();

        System.out.println("\n==================== Part 5 演示: 运行乘骑周期 (runOneCycle) ====================");
        assignment.partFive();

        System.out.println("\n==================== Part 6 演示: 写入文件 (Export) ====================");
        assignment.partSix();

        System.out.println("\n==================== Part 7 演示: 从文件读取 (Import) ====================");
        assignment.partSeven();

        System.out.println("\n--- PROG2004 A2 演示结束 ---");
    }

    /**
     * Part 3 演示: Waiting line (Queue) [cite: 564]
     * 演示 Queue 的添加、移除和打印操作 [cite: 567, 568, 569]。
     */
    public void partThree() {
        // 创建 Ride 对象和 Employee 操作员 [cite: 565]
        Employee operator = new Employee("Jane Smith", 35, "E100", "Supervisor", 35.0);
        Ride rollerCoaster = new Ride("G-Force Coaster", 140, operator, 4);

        // 创建至少 5 个访客 [cite: 567]
        Visitor v1 = new Visitor("Alice", 25, "V001", "Standard", 0);
        Visitor v2 = new Visitor("Bob", 15, "V002", "VIP", 0);
        Visitor v3 = new Visitor("Charlie", 30, "V003", "Standard", 0);
        Visitor v4 = new Visitor("David", 45, "V004", "VIP", 0);
        Visitor v5 = new Visitor("Eve", 18, "V005", "Standard", 0);

        // 添加访客到 Queue
        rollerCoaster.addVisitorToQueue(v1);
        rollerCoaster.addVisitorToQueue(v2);
        rollerCoaster.addVisitorToQueue(v3);
        rollerCoaster.addVisitorToQueue(v4);
        rollerCoaster.addVisitorToQueue(v5);

        // 打印所有 Visitors 在 Queue 中的状态 [cite: 569]
        rollerCoaster.printQueue();

        // 移除一个 Visitor (FIFO 原则) [cite: 568]
        rollerCoaster.removeVisitorFromQueue();

        // 再次打印 Queue 中的 Visitors，确认移除成功
        rollerCoaster.printQueue();
    }

    /**
     * Part 4A 演示: Ride history (LinkedList) [cite: 586]
     * 演示 LinkedList 的添加、检查、计数和使用 Iterator 打印 [cite: 589, 590, 591, 582]。
     */
    public void partFourA() {
        // 创建 Ride 对象
        Ride waterRide = new Ride("River Rapids", 110, null, 6);

        // 创建至少 5 个访客 [cite: 589]
        Visitor v6 = new Visitor("Fiona", 22, "V006", "Standard", 2);
        Visitor v7 = new Visitor("George", 12, "V007", "VIP", 5);
        Visitor v8 = new Visitor("Hannah", 33, "V008", "Standard", 1);
        Visitor v9 = new Visitor("Igor", 40, "V009", "VIP", 8);
        Visitor v10 = new Visitor("Jack", 28, "V010", "Standard", 3);

        // 添加访客到 History [cite: 589]
        waterRide.addVisitorToHistory(v6);
        waterRide.addVisitorToHistory(v7);
        waterRide.addVisitorToHistory(v8);
        waterRide.addVisitorToHistory(v9);
        waterRide.addVisitorToHistory(v10);

        // 检查 Visitor 是否在 History 中 [cite: 590]
        waterRide.checkVisitorFromHistory(v8);
        Visitor nonExistent = new Visitor("Zoe", 20, "V999", "Standard", 0);
        waterRide.checkVisitorFromHistory(nonExistent);

        // 打印 Visitor 总数 [cite: 591]
        waterRide.numberOfVisitors();

        // 打印所有 Visitors 在 History 中的状态 (使用 Iterator) [cite: 582, 591]
        waterRide.printRideHistory();
    }

    /**
     * Part 4B 演示: Sorting the ride history (Comparator) [cite: 602]
     * 演示使用自定义 Comparator 对 LinkedList 进行排序 [cite: 607]。
     */
    public void partFourB() {
        // 创建 Ride 对象
        Ride dropTower = new Ride("Sky Drop", 130, null, 2);

        // 创建不同 ticketType 和 age 的访客，用于测试排序
        Visitor s1 = new Visitor("Owen", 30, "S001", "Standard", 0);
        Visitor s2 = new Visitor("Mark", 20, "S002", "Standard", 0);
        Visitor v1 = new Visitor("Nancy", 40, "V001", "VIP", 0);
        Visitor v2 = new Visitor("Pat", 50, "V002", "VIP", 0);
        Visitor s3 = new Visitor("Quinn", 25, "S003", "Standard", 0);

        // 添加至少 5 个访客到 History [cite: 605]
        dropTower.addVisitorToHistory(s1);
        dropTower.addVisitorToHistory(s2);
        dropTower.addVisitorToHistory(v1);
        dropTower.addVisitorToHistory(v2);
        dropTower.addVisitorToHistory(s3);

        System.out.println("\n--- 排序前 ---");
        dropTower.printRideHistory(); // 打印排序前状态 [cite: 606]

        // 排序 History，使用 VisitorComparator [cite: 594, 596]
        dropTower.sortRideHistory(new VisitorComparator());

        System.out.println("\n--- 排序后 (VIP优先，年龄降序) ---");
        dropTower.printRideHistory(); // 打印排序后状态 [cite: 608]
    }

    /**
     * Part 5 演示: Run a ride cycle [cite: 624]
     * 演示 runOneCycle() 如何将访客从 Queue 移动到 History [cite: 629]。
     */
    public void partFive() {
        // 创建 Ride 对象，设置 maxRider 为 3 [cite: 625]
        Employee op = new Employee("Rex Ryan", 45, "E101", "Operator", 25.0);
        Ride bumperCars = new Ride("Bumper Blasters", 100, op, 3);

        // 添加 10 个访客到 Queue [cite: 627]
        for (int i = 1; i <= 10; i++) {
            bumperCars.addVisitorToQueue(new Visitor("Rider" + i, 20 + i, "B10" + i, "Standard", 0));
        }

        System.out.println("\n*** 初始状态 ***");
        bumperCars.printQueue(); // 打印初始队列 [cite: 628]

        // 运行第一个周期 (带走 maxRider=3 人) [cite: 629]
        bumperCars.runOneCycle();

        System.out.println("\n*** 第 1 周期后: 队列状态 ***");
        bumperCars.printQueue(); // 打印队列 (剩 7 人) [cite: 630]

        System.out.println("\n*** 第 1 周期后: 历史状态 ***");
        bumperCars.printRideHistory(); // 打印历史记录 (3 人) [cite: 631]

        // 运行第二个周期 (再带走 3 人)
        bumperCars.runOneCycle();
        System.out.println("\n*** 第 2 周期后: 历史状态 (总计 6 人) ***");
        bumperCars.printRideHistory();
    }

    /**
     * Part 6 演示: Writing to a file (Export) [cite: 657]
     * 演示 I/O 机制将 LinkedList 导出为 CSV 文件 [cite: 652, 659]。
     */
    public void partSix() {
        // 创建 Ride 对象
        Ride hauntedHouse = new Ride("Haunted Hotel", 120, null, 1);

        // 创建 5 个访客用于 History
        Visitor h1 = new Visitor("Scarlet", 22, "H001", "Standard", 1);
        Visitor h2 = new Visitor("Tom", 40, "H002", "VIP", 5);
        Visitor h3 = new Visitor("Uma", 33, "H003", "Standard", 0);
        Visitor h4 = new Visitor("Victor", 45, "H004", "VIP", 8);
        Visitor h5 = new Visitor("Wendy", 28, "H005", "Standard", 3);

        // 添加至少 5 个访客到 Ride History [cite: 659]
        hauntedHouse.addVisitorToHistory(h1);
        hauntedHouse.addVisitorToHistory(h2);
        hauntedHouse.addVisitorToHistory(h3);
        hauntedHouse.addVisitorToHistory(h4);hauntedHouse.addVisitorToHistory(h5);

        // 导出到文件 [cite: 659]
        hauntedHouse.exportRideHistory(HISTORY_FILE);
    }

    /**
     * Part 7 演示: Reading from a file (Import) [cite: 671]
     * 演示 I/O 机制从 CSV 文件导入数据到 LinkedList [cite: 663]。
     */
    public void partSeven() {
        // 创建新的 Ride 对象，测试导入功能
        Ride importTestRide = new Ride("Import Test Ride", 100, null, 5);

        System.out.println("\n*** 导入前状态 ***");
        // 确认 History 当前是空的
        importTestRide.numberOfVisitors();

        // 从 Part 6 创建的文件导入数据 [cite: 673]
        importTestRide.importRideHistory(HISTORY_FILE);

        System.out.println("\n*** 导入后状态 ***");
        // 打印数量，确认导入了正确的访客数量 [cite: 674]
        importTestRide.numberOfVisitors();
        // 打印 History，确认访客详情正确 [cite: 675]
        importTestRide.printRideHistory();
    }
}
