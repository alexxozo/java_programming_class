package space.harbour.java.hw8;

import java.util.Map;

public class DenominationContainer {

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

    public void handle(int amount, Map<Integer, Integer> bills) {
        int billsToGive = 0, remainingAmount = 0;
        if (amount > 0 && amount >= denomination) {
            billsToGive = Math.min(count, amount / denomination);
            remainingAmount = amount - (billsToGive * denomination);

            if (bills.containsKey(denomination)) {
                bills.put(denomination, bills.get(denomination) + billsToGive);
            } else {
                bills.put(denomination, billsToGive);
            }
        }
        if (nextContainer != null) {
            nextContainer.handle(remainingAmount, bills);
        } else {
            if (remainingAmount > 0) {
                System.out.println("Sorry not enough money for this!");
            } else {
                System.out.println("Succesful transaction!");
                System.out.println(bills);
            }
        }
    }
}
