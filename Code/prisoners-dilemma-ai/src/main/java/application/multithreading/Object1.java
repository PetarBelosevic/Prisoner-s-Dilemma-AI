package application.multithreading;

public class Object1 {
    private boolean flag1 = false;
    private boolean flag2 = false;

    public synchronized int whichFlag() throws InterruptedException {
        while (!flag1 && !flag2) {
            wait(); // Wait until either flag1 or flag2 is set
        }

        if (flag1) {
            return 1;
        } else {
            return 2;
        }
    }

    public synchronized void setFlag1() {
        flag1 = true;
        notify(); // Notify waiting threads that flag1 has been set
    }

    public synchronized void setFlag2() {
        flag2 = true;
        notify(); // Notify waiting threads that flag2 has been set
    }
}