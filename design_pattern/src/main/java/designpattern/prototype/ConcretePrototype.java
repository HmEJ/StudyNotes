package designpattern.prototype;

import lombok.Data;

@Data
public class ConcretePrototype implements Prototype<ConcretePrototype>{
    private String name;
    public ConcretePrototype(String name){
        this.name = name;
    }

    @Override
    public ConcretePrototype clone() {
        return new ConcretePrototype(this.name);
    }
}
