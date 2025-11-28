// VisitorComparator.java
import java.util.Comparator;

/**
 * Part 4B: Comparator 排序类。
 * 实现了 Comparator 接口，用于对 Visitor 集合进行自定义排序。
 * 高分要求: compare 方法必须使用至少两个实例变量进行比较 (TicketType 和 Age)。
 */
public class VisitorComparator implements Comparator<Visitor> {

    @Override
    public int compare(Visitor v1, Visitor v2) {
        // 1. 主要排序标准: 按 Ticket Type 排序 (VIP 在前)
        // 使用 String.compareTo() 进行自然排序
        int ticketCompare = v1.getTicketType().compareTo(v2.getTicketType());

        if (ticketCompare != 0) {
            return ticketCompare;
        }

        // 2. 次要排序标准: 按 Age 排序 (从大到小 - 降序)
        // 使用 Integer.compare(v2, v1) 实现降序排序
        return Integer.compare(v2.getAge(), v1.getAge());
    }
}