package space.harbour.java.hw9;

public class BankAtmDemo {

    public static void main(String[] args) throws CloneNotSupportedException {
        DenominationContainer firstDenominationContainer = new DenominationContainer(100, 1);
        DenominationContainer secondDenominationContainer = new DenominationContainer(50, 1);
        DenominationContainer thirdDenominationContainer = new DenominationContainer(10, 4);

        firstDenominationContainer.setNextContainer(secondDenominationContainer);
        secondDenominationContainer.setNextContainer(thirdDenominationContainer);


        AtmDispenser atmDispenser = new AtmDispenser(firstDenominationContainer);

        AtmDispenser cloned = atmDispenser.clone();

        System.out.println(atmDispenser + "\n" + cloned);

    }
}
