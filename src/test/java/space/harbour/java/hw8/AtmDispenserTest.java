package space.harbour.java.hw8;

import java.util.HashMap;
import org.junit.Assert;
import org.junit.Test;

public class AtmDispenserTest {

    @Test
    public void testOne() {
        DenominationContainer firstDenominationContainer = new DenominationContainer(20, 1);
        DenominationContainer secondDenominationContainer = new DenominationContainer(20, 1);
        DenominationContainer thirdDenominationContainer = new DenominationContainer(20, 4);
        firstDenominationContainer.setNextContainer(secondDenominationContainer);
        secondDenominationContainer.setNextContainer(thirdDenominationContainer);

        AtmDispenser atmDispenser = new AtmDispenser(firstDenominationContainer);

        Assert.assertEquals(120, atmDispenser.balance());
        atmDispenser.giveMeMoney(80);
        Assert.assertEquals(40, atmDispenser.balance());

        HashMap<Integer, Integer> testingMap = new HashMap<>();
        testingMap.put(20, 4);
        Assert.assertEquals(testingMap, atmDispenser.getBills());
    }

    @Test
    public void testTwo() {
        DenominationContainer firstDenominationContainer = new DenominationContainer(50, 1);
        DenominationContainer secondDenominationContainer = new DenominationContainer(10, 1);
        DenominationContainer thirdDenominationContainer = new DenominationContainer(5, 4);
        firstDenominationContainer.setNextContainer(secondDenominationContainer);
        secondDenominationContainer.setNextContainer(thirdDenominationContainer);

        AtmDispenser atmDispenser = new AtmDispenser(firstDenominationContainer);

        Assert.assertEquals(80, atmDispenser.balance());
        atmDispenser.giveMeMoney(80);
        Assert.assertEquals(0, atmDispenser.balance());

        HashMap<Integer, Integer> testingMap = new HashMap<>();
        System.out.println(atmDispenser.getBills());
        testingMap.put(50, 1);
        testingMap.put(10, 1);
        testingMap.put(5, 4);
        Assert.assertEquals(testingMap, atmDispenser.getBills());
    }
}