package hw1;

public class Task1 {

    private static int give_me_one() {
        return 1;
    }
    
    public static void main(String[] args) {
        int one = give_me_one();
        int two = one * 2;
        System.out.println(two);
    }
}