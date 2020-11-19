package space.harbour.java.hw9;

import java.util.HashMap;
import java.util.Map;
import java.util.Observable;
import java.util.Observer;

public class AtmDispenser implements Cloneable {

    private final DenominationContainer denominationContainer;
    private final Map<Integer, Integer> bills;

    public AtmDispenser(DenominationContainer denominationContainer) {
        this.denominationContainer = denominationContainer;
        this.bills = new HashMap<>();
    }

    @Override
    public String toString() {

        return "AtmDispenser{" +
                "denominationContainer=" + denominationContainer +
                ", bills=" + bills +
                '}';
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

    @Override
    protected AtmDispenser clone() throws CloneNotSupportedException {
        DenominationContainer clonedDenominationContainerChain = denominationContainer.clone();
        return new AtmDispenser(clonedDenominationContainerChain);
    }
}
