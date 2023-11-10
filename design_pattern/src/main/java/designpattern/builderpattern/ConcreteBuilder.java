package designpattern.builderpattern;

public class ConcreteBuilder implements Builder{
    private Product car;
    public ConcreteBuilder(){
        this.car = new Product();
    }

    @Override
    public void buildBody() {
        car.setCarBody("车身");
    }

    @Override
    public void buildEngine() {
        car.setCarEngine("引擎");
    }

    @Override
    public void buildTire() {
        car.setCarTire("轮胎");
    }

    @Override
    public Product getResult() {
        return car;
    }
}
