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

    public void giveMeMoney(int amount) {
        denominationContainer.handle(amount, bills);
    }

    public int balance() {
        // TODO: iterator

        return 0;
    }

}
