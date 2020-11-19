package space.harbour.java.hw9;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import org.junit.Before;
import org.junit.Test;

public class AtmDispenserTest {

    BankDepartment bankDepartment = new BankDepartment();
    ArrayList<AtmDispenser> atms = new ArrayList<>();

    @Before
    public void setup() throws CloneNotSupportedException {
        DenominationContainer firstDenominationContainer = new DenominationContainer(100, 1);
        DenominationContainer secondDenominationContainer = new DenominationContainer(100, 1);
        DenominationContainer thirdDenominationContainer = new DenominationContainer(10, 4);

        firstDenominationContainer.setNextContainer(secondDenominationContainer);
        secondDenominationContainer.setNextContainer(thirdDenominationContainer);

        AtmDispenser atmPrototype = new AtmDispenser(firstDenominationContainer);

        atms.add(atmPrototype.clone());
        atms.add(atmPrototype.clone());
        atms.add(atmPrototype.clone());


        atms.get(0).addObserver(bankDepartment);
        atms.get(1).addObserver(bankDepartment);
        atms.get(2).addObserver(bankDepartment);
    }

    @Test
    public void testOne() {
        atms.get(0).giveMeMoney(2000);
        assertEquals(240, atms.get(0).balance());
    }

    @Test
    public void testTwo() {
        atms.get(1).giveMeMoney(100);
        assertEquals(140, atms.get(1).balance());
    }

    @Test
    public void testThree() {
        atms.get(2).giveMeMoney(240);
        assertEquals(0, atms.get(2).balance());
    }

}