package space.harbour.java.hw9;

public class BankDepartment implements AtmObserver {
    @Override
    public void update(AtmDispenser atmDispenser) {
        System.out.println("BANK DEPARTMENT: ATM with code "
                + atmDispenser.hashCode() + " is EMPTY");
    }
}
