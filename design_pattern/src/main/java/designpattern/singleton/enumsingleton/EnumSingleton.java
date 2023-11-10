package designpattern.singleton.enumsingleton;

public enum EnumSingleton {
    INSTANCE;

    public void print(){
        System.out.println(INSTANCE.hashCode());
    }
}
