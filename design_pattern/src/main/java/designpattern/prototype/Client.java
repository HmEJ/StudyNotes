package designpattern.prototype;

public class Client {
    public static void main(String[] args) {
        Prototype prototype = new ConcretePrototype("jone");
        Prototype obj = (Prototype) prototype.clone();
        System.out.println(obj);
    }
}
