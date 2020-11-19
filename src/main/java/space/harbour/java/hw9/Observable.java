package space.harbour.java.hw9;

public interface Observable {

    void addObserver(AtmObserver observer);
    void removeObserver(AtmObserver observer);
    void notifyAllObservers();
}
