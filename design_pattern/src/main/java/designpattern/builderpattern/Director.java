package designpattern.builderpattern;

public class Director {
    public Product constructCar(Builder builder){
        builder.buildBody();
        builder.buildEngine();
        builder.buildTire();
        return builder.getResult();
    }
}

class client{
    public static void main(String[] args) {
        Director director = new Director();
        ConcreteBuilder concreteBuilder = new ConcreteBuilder();
        Product product = director.constructCar(concreteBuilder);
        System.out.println(product);
    }
}