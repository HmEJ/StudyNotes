package designpattern.factorymethod.flyweight;

import java.util.HashMap;
import java.util.Map;

public class FlyweightFactory {
    private final Map<String,Flyweight> flyweights = new HashMap<>();

    public Flyweight getFlyweight(String internalState){
        if (flyweights.containsKey(internalState)){
            return flyweights.get(internalState);
        }else {
            Flyweight flyweight = new ConcreteFlyweight(internalState);
            flyweights.put(internalState, flyweight);
            return flyweight;
        }
    }
}
