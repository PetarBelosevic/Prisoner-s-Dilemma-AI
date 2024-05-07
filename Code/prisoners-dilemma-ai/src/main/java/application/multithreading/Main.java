package application.multithreading;

public class Main {
    public static void main(String[] args) {
        Object1 object1 = new Object1();
        Object2 object2 = new Object2(object1);

        // Start a thread to call setFlag1 or setFlag2 after some delay
        new Thread(() -> {
            try {
                Thread.sleep(2000); // Simulate delay
                object2.setFlag1(); // Set flag1 after delay
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }).start();

        // Start another thread to call setFlag2 after some delay
        new Thread(() -> {
            try {
                Thread.sleep(3000); // Simulate delay
                object2.setFlag2(); // Set flag2 after delay
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }).start();

        // Main thread calls whichFlag and prints the result
        try {
            int result = object1.whichFlag();
            System.out.println("Flag " + result + " was set.");
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}