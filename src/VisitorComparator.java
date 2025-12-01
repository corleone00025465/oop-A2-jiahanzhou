// 5. VisitorComparator.java   ← 现在创建！前面四个类已经存在了，不会再红！
import java.util.Comparator;

public class VisitorComparator implements Comparator<Visitor> {
    @Override
    public int compare(Visitor v1, Visitor v2) {
        int ageDiff = Integer.compare(v1.getAge(), v2.getAge());
        if (ageDiff != 0) return ageDiff;
        return v1.getName().compareToIgnoreCase(v2.getName());
    }
}