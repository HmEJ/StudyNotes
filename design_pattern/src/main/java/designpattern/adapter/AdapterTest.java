package designpattern.adapter;

public class AdapterTest {
    public static void main(String[] args) {
        Adaptee adaptee = new Adaptee();

        Target target = new Adapter(adaptee);

        target.output5V();
    }
}

class Adaptee{
    public int output220v(){
        return 220;
    }
}

interface Target{
    int output5V();
}

class Adapter implements Target{
    private Adaptee adaptee;

    public Adapter(Adaptee adaptee){
        this.adaptee = adaptee;
    }

    @Override
    public int output5V() {
        int i = adaptee.output220v();
        System.out.println("原始电压:"+i+" 输出电压:5");
        return 5;
    }
}