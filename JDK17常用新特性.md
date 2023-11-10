## 1. 性能提升

### ZGC垃圾回收器

大大减少STW (stop the world) 的时间。STW就是垃圾回收器在进行垃圾回收时，会暂停所有的线程，这个过程非常耗时，就容易使用户感觉到卡顿。但是ZGC垃圾回收器减少了STW时间，能做到用户无感，让垃圾回收更加流畅，不卡顿。

## 2. 语法糖

### 1. switch语句增强

JDK17之前：

```java
String name = "hang";
String alias;
switch(name){
    case "hang":
    case "san":
        alias = "b";
        break;
    case "wang":
    case "jun":
        alias = "c";
        break
    default:
        alias = "unknown";
        break;
}
```

JDK17：

```java
String name = "hang";
String alias = switch(name){
        case "hang","san" -> "b";
        case "wang","jun" -> "c";
        default -> "unknown";
}
```

### 2. 字符串拼接

JDK17之前：

```java
String html = "<html>\n"+
    " <body> \n " +
    " <h1>hello world!</h1> \n "+
    " </body>\n"+
    " </html> \n";
```

JDK17:

```java
String html = """
    <html> \s
    	<body>
    		<h1> %s </h1>   // %s 是占位符
    	</body>
    </html>
    """;
System.out.println(String.format(html,"hello world"));
```

> JDK17提供两个转义字符来处理字符串连接问题：
>
> 1. \  : 置于行尾，用来将两行连接为一行
> 2. \s ：单个空白字符

### 3. instanceof增强

JDK17之前：

```java
Object o = 1;
if(o instanceof Integer){
    Integer i = (Interger) o;
    System.out.println(i.intValue());
}else if(o instanceof String){
    String s = (String) o;
    System.out.println(s.charAt(0));
}
```

JDK17:

```java
Object o = 1;
if(o instanceof Integer i){
    System.out.println(i.intValue());
}else if(o instanceof String s){
    System.out.println(s.charAt(0));
}
```

## 3.密封类(Sealed Classes) 

更愿意把他叫做 *继承限制类* ，主要功能就是用来限制继承的

优点就是可以提高代码的安全性和可控性。

1. 具体使用的话就是对父类使用`sealed`关键字来修饰，然后使用 `permits`关键字来限制能够继承的子类

```java
public abstract sealed class Animals permits Dog,Cat{}
```

2. 对于子类的话，使用`final`关键字或`non-sealed`关键字来修饰。`final`关键字表示该子类**不可以再被继承**。`non-sealed`关键字表示该子类**可以被再次继承**。

```java
//cat类 = 该类使用final关键字修饰，不可以被继承
public final class Cat extends Animal{ }

//dog类 = 该类使用non-sealed关键字修饰，可以被继承
public non-sealed class Dog extends Animal{ }
```

> 注意，使用密封类的代码，子类和父类**必须在同一个包中**。

## 4. Record类

类似lombok的 **属性只读 **对象

1. 具体的使用方法就是使用record关键字修饰一个类，这个类就是`Record`类

```java
public record UserRecord(Long userId,String userName){ }
```

> 这样定义的类，JVM会在编译时自动为我们生成**全参构造器**以及**这些属性的get方法**（只读）

2. 使用该类

```java
public class demo{
    public static void main(String[] args){
        UserRecord u = new UserRecord(20L,"ahang");
    }
}
```

