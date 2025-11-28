// RideInterface.java
/**
 * Part 2: 接口 (Interface) - 定义 Ride 类必须实现的所有行为。
 * 接口确保了 Ride 类符合 Part 3, 4, 5 的所有功能要求。
 */
public interface RideInterface {

    // --- Part 3: Waiting Queue (Queue) 方法 ---
    /**
     * 将访客添加到乘骑的等待队列中 (FIFO)。
     */
    void addVisitorToQueue(Visitor visitor);

    /**
     * 从等待队列中移除并返回队首的访客。
     */
    Visitor removeVisitorFromQueue();

    /**
     * 打印当前等待队列中的所有访客。
     */
    void printQueue();

    // --- Part 4: Ride History (LinkedList) 方法 ---
    /**
     * 将已乘骑的访客添加到乘骑历史记录中。
     */
    void addVisitorToHistory(Visitor visitor);

    /**
     * 检查某个访客是否在乘骑历史记录中。
     */
    boolean checkVisitorFromHistory(Visitor visitor);

    /**
     * 返回乘骑历史记录中的访客总数。
     */
    int numberOfVisitors();

    /**
     * 使用 Iterator 打印乘骑历史记录中的所有访客。
     */
    void printRideHistory();

    // --- Part 5: Run Cycle 方法 ---
    /**
     * 模拟乘骑运行一个周期，将访客从队列移动到历史记录。
     */
    void runOneCycle();
}