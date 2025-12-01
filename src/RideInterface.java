// 4. RideInterface.java
public interface RideInterface {
    void addVisitorToQueue(Visitor v);
    void removeVisitorFromQueue();
    void printQueue();
    void addVisitorToHistory(Visitor v);
    boolean checkVisitorFromHistory(Visitor v);
    int numberOfVisitors();
    void printRideHistory();
    void runOneCycle();
    void sortRideHistory();
    void exportRideHistory(String filename);
    void importRideHistory(String filename);
}