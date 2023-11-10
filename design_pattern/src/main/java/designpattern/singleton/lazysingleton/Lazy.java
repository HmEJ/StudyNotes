package designpattern.singleton.lazysingleton;

public class Lazy {
    /*防止重排序*/
    private volatile static Lazy instance;

    private Lazy(){}

    public static Lazy getInstance(){

        if (instance==null){
            synchronized (Lazy.class){
                if (instance==null){
                    instance=new Lazy();
                    /*
                    * 字节码层：
                    * 1. 分配空间
                    * 2. 引用赋值
                    * 3. 初始化
                    * */

                }
            }
        }

        return instance;
    }
}
