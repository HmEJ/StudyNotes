package designpattern.factorymethod;

public abstract class Factory {
    public abstract <T>T createProduct(Class<T> clazz);
}
