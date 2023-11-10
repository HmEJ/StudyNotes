package designpattern.singleton.hungrysingleton;

public class Test {

    public static void main(String[] args) {
        Hungry instance1 = Hungry.getInstance();
        Hungry instance2 = Hungry.getInstance();
        System.out.println(instance1==instance2);
    }
}
