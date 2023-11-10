package designpattern.decorator;

public class DecoratorTest {
    public static void main(String[] args) {
        Component component = new function(new ConcreteComponent());
        component.operation();
    }
}

interface Component{
    void operation();
}

class ConcreteComponent implements Component{

    @Override
    public void operation() {
        System.out.println("拍照");
    }
}

abstract class Decorator implements Component{

    Component component;

    public Decorator(Component component){
        this.component = component;
    }
}
/* 添加美颜功能 */
class function extends Decorator{

    public function(Component component) {
        super(component);
    }

    @Override
    public void operation() {
        System.out.println("添加美颜");
        component.operation();
    }
}