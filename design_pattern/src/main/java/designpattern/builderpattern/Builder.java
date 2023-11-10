package designpattern.builderpattern;

public interface Builder {
    void buildBody();
    void buildEngine();
    void buildTire();
    Product getResult();
}
