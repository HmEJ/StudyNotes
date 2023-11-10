package designpattern.factorymethod;

import java.lang.reflect.InvocationTargetException;

public class ConcreteFactory extends Factory{
    @Override
    public <T> T createProduct(Class<T> clazz) {
        Product product = null;

        try {
            product = (Product) Class.forName(clazz.getName()).getConstructor().newInstance();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return (T)product;
    }
}
