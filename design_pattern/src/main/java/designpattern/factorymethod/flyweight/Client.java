package designpattern.factorymethod.flyweight;

public class Client {
    public static void main(String[] args) {
        FlyweightFactory factory = new FlyweightFactory();

        Flyweight fw1 = factory.getFlyweight("A");
        fw1.operation("1");

        Flyweight fw2 = factory.getFlyweight("B");
        fw1.operation("2");

        Flyweight fw3 = factory.getFlyweight("A");
        fw1.operation("3");

        System.out.println(fw1==fw3);
    }
}
