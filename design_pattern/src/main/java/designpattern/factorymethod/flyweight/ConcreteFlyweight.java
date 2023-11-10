package designpattern.factorymethod.flyweight;

public class ConcreteFlyweight implements Flyweight{
    private final String internalState;

    public ConcreteFlyweight(String internalState) {
        this.internalState = internalState;
    }

    @Override
    public void operation(String externalState) {
        System.out.println("ConcreteFlyweight-internalState:"+internalState +" externalState:"+externalState);
    }
}
