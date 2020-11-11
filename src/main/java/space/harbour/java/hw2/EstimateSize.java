package space.harbour.java.hw2;

public class EstimateSize {

    public static float estimateSizeOfInt() throws InterruptedException {
        System.gc();
        Thread.sleep(10);
        final int N = 10_000_000;
        int[] arr = new int[N];
        for (int i = 0; i < N; i++) {
            arr[i] = i;
        }
        long occupiedMemory = Runtime.getRuntime().totalMemory()
                - Runtime.getRuntime().freeMemory();
        return occupiedMemory / Float.valueOf(N);
    }

    public static float estimateSizeOfObject() throws InterruptedException {
        System.gc();
        Thread.sleep(10);
        final int N = 10_000_000;
        Object[] arr = new Object[N];
        for (int i = 0; i < N; i++) {
            arr[i] = new Object();
        }
        long occupiedMemory = Runtime.getRuntime().totalMemory()
                - Runtime.getRuntime().freeMemory();
        return occupiedMemory / Float.valueOf(N);
    }

    public static float estimateSizeOfReference() throws InterruptedException {
        System.gc();
        Thread.sleep(10);
        final int N = 10_000_000;
        Object[] arr = new Object[N];
        for (int i = 0; i < N; i++) {
            arr[i] = null;
        }
        long occupiedMemory = Runtime.getRuntime().totalMemory()
                - Runtime.getRuntime().freeMemory();
        return occupiedMemory / Float.valueOf(N);
    }

    public static float estimateSizeOfString() throws InterruptedException {
        System.gc();
        Thread.sleep(10);
        final int N = 10_000_000;
        String[] arr = new String[N];
        for (int i = 0; i < N; i++) {
            arr[i] = "Test";
        }
        long occupiedMemory = Runtime.getRuntime().totalMemory()
                - Runtime.getRuntime().freeMemory();
        return occupiedMemory / Float.valueOf(N);
    }


    public static void main(String[] args) throws InterruptedException {
        System.out.println("Estimate of int: " + estimateSizeOfInt());
        System.out.println("Estimate of object: " + estimateSizeOfObject());
        System.out.println("Estimate of reference: " + estimateSizeOfReference());
        System.out.println("Estimate of string: " + estimateSizeOfString());
    }


}
