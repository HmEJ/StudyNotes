package designpattern.singleton.lazysingleton;

public class Test {
    public static void main(String[] args) {
        Lazy instance1 = Lazy.getInstance();
        Lazy instance2 = Lazy.getInstance();
        System.out.println(instance1==instance2);
    }
}
