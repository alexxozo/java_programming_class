package space.harbour.java.hw8;

public class AtmDemo {

    public static void main(String[] args) {
        DenominationContainer firstDenominationContainer = new DenominationContainer(20, 2);
        DenominationContainer secondDenominationContainer = new DenominationContainer(20, 2);
        DenominationContainer thirdDenominationContainer = new DenominationContainer(5, 20);

        firstDenominationContainer.setNextContainer(secondDenominationContainer);
        secondDenominationContainer.setNextContainer(thirdDenominationContainer);

        AtmDispenser atmDispenser = new AtmDispenser(firstDenominationContainer);
        atmDispenser.giveMeMoney(100);
    }

}
