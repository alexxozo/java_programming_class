package space.harbour.java.hw9;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class AtmDispenser implements Cloneable, Observable {

    private final DenominationContainer denominationContainer;
    private final Map<Integer, Integer> bills;
    private List<BankDepartment> observers;

    public AtmDispenser(DenominationContainer denominationContainer) {
        this.denominationContainer = denominationContainer;
        this.bills = new HashMap<>();
        this.observers = new ArrayList<>();
    }

    @Override
    public String toString() {

        return "AtmDispenser{"
                + "denominationContainer=" + denominationContainer
                + ", bills=" + bills
                + '}';
    }

    public Map<Integer, Integer> getBills() {
        return bills;
    }

    public int balance() {
        DenominationContainer currentContainer = denominationContainer;
        int countBalance = currentContainer.getDenomination() * currentContainer.getCount();
        while (currentContainer.hasNext()) {
            currentContainer = (DenominationContainer) currentContainer.next();
            countBalance += currentContainer.getDenomination() * currentContainer.getCount();
        }
        return countBalance;
    }

    public void giveMeMoney(int amount) {
        int balance = balance();
        System.out.print("ATM (" + this.hashCode() + "): ");
        if (amount > balance) {
            System.out.println("Sorry not enough money :(");
        } else {
            denominationContainer.handle(amount, bills);
            if (balance() == 0) {
                notifyAllObservers();
            }
            bills.clear();
        }
    }

    @Override
    protected AtmDispenser clone() throws CloneNotSupportedException {
        DenominationContainer clonedDenominationContainerChain = denominationContainer.clone();
        AtmDispenser clonedAtmDispenser = new AtmDispenser(clonedDenominationContainerChain);
        for (BankDepartment department : observers) {
            clonedAtmDispenser.addObserver(department);
        }
        return new AtmDispenser(clonedDenominationContainerChain);
    }

    @Override
    public void addObserver(AtmObserver observer) {
        observers.add((BankDepartment) observer);
    }

    @Override
    public void removeObserver(AtmObserver observer) {
        observers.remove(observer);
    }

    @Override
    public void notifyAllObservers() {
        for (BankDepartment department : observers) {
            department.update(this);
        }
    }
}
