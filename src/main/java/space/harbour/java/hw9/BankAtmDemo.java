package space.harbour.java.hw9;

import java.util.ArrayList;

public class BankAtmDemo {

    public static void main(String[] args) throws CloneNotSupportedException {
        DenominationContainer firstDenominationContainer = new DenominationContainer(100, 1);
        DenominationContainer secondDenominationContainer = new DenominationContainer(100, 1);
        DenominationContainer thirdDenominationContainer = new DenominationContainer(10, 4);

        firstDenominationContainer.setNextContainer(secondDenominationContainer);
        secondDenominationContainer.setNextContainer(thirdDenominationContainer);


        AtmDispenser atmPrototype = new AtmDispenser(firstDenominationContainer);
        ArrayList<AtmDispenser> atms = new ArrayList<>();

        atms.add(atmPrototype.clone());
        atms.add(atmPrototype.clone());
        atms.add(atmPrototype.clone());

        BankDepartment bankDepartment = new BankDepartment();
        atms.get(0).addObserver(bankDepartment);
        atms.get(1).addObserver(bankDepartment);
        atms.get(2).addObserver(bankDepartment);

        atms.get(0).giveMeMoney(2000);
        atms.get(1).giveMeMoney(100);
        atms.get(2).giveMeMoney(240);



    }
}
