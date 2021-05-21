package Work.srp;

public class Employee1 {
    Waiter waiter = new Waiter();
    Cook cook = new Cook();
    void cook() {
        cook.cook();
    }
    void take() {
        waiter.take();
    }
}
