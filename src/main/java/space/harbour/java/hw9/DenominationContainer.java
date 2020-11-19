package space.harbour.java.hw9;

import java.util.Iterator;
import java.util.Map;

public class DenominationContainer implements Iterator, Cloneable {

    private final int denomination;
    private int count;

    private DenominationContainer nextContainer;

    public DenominationContainer(int denomination, int count) {
        this.denomination = denomination;
        this.count = count;
    }

    public void setNextContainer(DenominationContainer nextContainer) {
        this.nextContainer = nextContainer;
    }

    @Override
    public boolean hasNext() {
        return nextContainer != null;
    }

    @Override
    public Object next() {
        return nextContainer;
    }

    @Override
    protected DenominationContainer clone() throws CloneNotSupportedException {
        DenominationContainer headOfChain = new DenominationContainer(denomination, count);
        DenominationContainer tailOfChain = headOfChain;
        DenominationContainer currentContainer = nextContainer;
        while (currentContainer != null) {
            DenominationContainer clonedContainer = new
                    DenominationContainer(currentContainer.denomination, currentContainer.count);
            tailOfChain.setNextContainer(clonedContainer);
            tailOfChain = tailOfChain.nextContainer;
            currentContainer = (DenominationContainer) currentContainer.next();
        }

        return headOfChain;
    }

    public int getDenomination() {
        return denomination;
    }

    public int getCount() {
        return count;
    }

    public void handle(int amount, Map<Integer, Integer> bills) {
        int billsToGive;
        int remainingAmount = 0;
        if (amount > 0 && amount >= denomination) {
            billsToGive = Math.min(count, amount / denomination);
            remainingAmount = amount - (billsToGive * denomination);
            count -= billsToGive;
            if (bills.containsKey(denomination)) {
                bills.put(denomination, bills.get(denomination) + billsToGive);
            } else {
                bills.put(denomination, billsToGive);
            }
        } else {
            remainingAmount = amount;
        }
        if (nextContainer != null) {
            nextContainer.handle(remainingAmount, bills);
        } else {
            if (remainingAmount > 0) {
                System.out.println("Sorry there was a problem!");
            } else {
                System.out.println("Successful transaction!");
                System.out.println(bills);
            }
        }
    }
}
