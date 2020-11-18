package space.harbour.java.hw8;

public class AtmDemo {

    public static void main(String[] args) {
        DenominationContainer firstDenominationContainer = new DenominationContainer(20, 1);
        DenominationContainer secondDenominationContainer = new DenominationContainer(20, 1);
        DenominationContainer thirdDenominationContainer = new DenominationContainer(5, 4);

        firstDenominationContainer.setNextContainer(secondDenominationContainer);
        secondDenominationContainer.setNextContainer(thirdDenominationContainer);


        AtmDispenser atmDispenser = new AtmDispenser(firstDenominationContainer);

        System.out.println("Current balance is: " + atmDispenser.balance());
        atmDispenser.giveMeMoney(60);
        System.out.println("Current balance is: " + atmDispenser.balance());

    }

}
