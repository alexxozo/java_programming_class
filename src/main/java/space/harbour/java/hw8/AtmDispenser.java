package space.harbour.java.hw8;

import java.util.HashMap;
import java.util.Map;

public class AtmDispenser {

    private final DenominationContainer denominationContainer;
    private final Map<Integer, Integer> bills;

    public AtmDispenser(DenominationContainer denominationContainer) {
        this.denominationContainer = denominationContainer;
        this.bills = new HashMap<>();
    }

    public Map<Integer, Integer> getBills() {
        return bills;
    }

    public void giveMeMoney(int amount) {
        denominationContainer.handle(amount, bills);
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

}
