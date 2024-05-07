package application.multithreading;

public class Object2 {
    private Object1 object1;

    public Object2(Object1 object1) {
        this.object1 = object1;
    }

    public void setFlag1() {
        object1.setFlag1();
    }

    public void setFlag2() {
        object1.setFlag2();
    }
}