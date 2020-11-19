package space.harbour.java.hw9;

public class BankAtmDemo {

    public static void main(String[] args) throws CloneNotSupportedException {
        DenominationContainer firstDenominationContainer = new DenominationContainer(100, 1);
        DenominationContainer secondDenominationContainer = new DenominationContainer(100, 1);
        DenominationContainer thirdDenominationContainer = new DenominationContainer(10, 4);

        firstDenominationContainer.setNextContainer(secondDenominationContainer);
        secondDenominationContainer.setNextContainer(thirdDenominationContainer);


        AtmDispenser atmDispenser = new AtmDispenser(firstDenominationContainer);
        AtmDispenser clonedAtmDispenser = atmDispenser.clone();

//        System.out.println(atmDispenser + "\n" + clonedAtmDispenser + "\n" + atmDispenser.equals(clonedAtmDispenser));

        BankDepartment bankDepartment = new BankDepartment();
        atmDispenser.addObserver(bankDepartment);

        atmDispenser.giveMeMoney(2000);



    }
}
