### 1. 什么是序列化流, 什么是反序列化流
```
ObjectOutputStream 类（序列化流） ： 把内存中的Java对象转换成平台无关的二进制数据，从而允许把这种二进制数据持久地保存在磁盘上，或通过网络将这种二进制数据传输到另一个网络节点。
ObjectInputStream类 （反序列化流）： 当其它程序获取了这种二进制数据，就可以恢复成原来的Java对象。

```
### 2. 用IO流读取城市id.txt文件的内容, 将内容存入map集合中, 城市名称作为key, id作为值
```java
import java.io.*;
import java.util.HashMap;

class Test {
    public static void main(String[] args) throws IOException {
        HashMap<String, String> map = new HashMap<>();
        File file = new File("D:\\桌面\\城市id.txt");
        //使用BufferedReader读取数据（效率更高）   
        // 存入StringBuilder中
        BufferedReader reader = new BufferedReader(new FileReader(file));
        StringBuilder sb = new StringBuilder();
        String s = reader.readLine();
        while (s!=null){
            sb.append(s);
            s=reader.readLine();
        }
        //将StringBuilder中数据取出
        String ss = sb.toString();
        //根据逗号分割， 返回87个元素的数组 每个元素形式：  "北京"=>"101010100"
        String[] split = ss.split(",");
        //遍历该数组
        for (int i = 0; i < split.length; i++) {
            //取出每个元素
            String sss = split[i];
            //去除=>符号
            String[] split1 = sss.split("=>");
            //存入map中
            map.put(split1[0],split1[1]);
        }
        System.out.println(map);
    }
}

```
### 3. TCP和UDP的区别, 请分点回答
```
1. TCP安全 UDP不安全
2. TCP是面向连接协议，也就是说二者在收发信息之前必须建立有效，可靠的连接，有点类似于生活中的打电话。而UDP是非连接协议，在收发信息之前，源端和目的端不建立连接，只是将数据尽可能地丢到网络上。类似于生活中的微信，QQ。
3. TCP传输是可靠的 UDP传输不是可靠的
4. TCP对系统资源要求高 UDP要求少
5. TCP保证数据顺序 UDP不保证
```

### 4. 编写一段客户端登录注册程序, 注册程序利用用户类, 将用户的密码, 账户名封装并且序列化进本地的User.txt文件里, 登录程序将Usr.txt文件进行反序列, 进行登录的密码用户名验证
```java
/**
 * 用户类
 */
public class User implements Serializable {
    private static final long serialVersionUID = 5815801903108783216L;
    private String username;
    private String password;

    public User() {
    }

    public User(String username, String password) {
        this.username = username;
        this.password = password;
    }

    /**
     * 获取
     * @return username
     */
    public String getUsername() {
        return username;
    }

    /**
     * 设置
     * @param username
     */
    public void setUsername(String username) {
        this.username = username;
    }

    /**
     * 获取
     * @return password
     */
    public String getPassword() {
        return password;
    }

    /**
     * 设置
     * @param password
     */
    public void setPassword(String password) {
        this.password = password;
    }

    public String toString() {
        return "User{username = " + username + ", password = " + password + "}";
    }
}
```

```java
static void register(User user) throws InterruptedException, IOException {
    Scanner in = new Scanner(System.in);
    System.out.println("===========注册程序=========");
    System.out.println("输入用户名：");
    String username = in.next();
    System.out.println("输入密码：");
    String password = in.next();
    Thread.sleep(300);
    System.out.println("正在注册中...");

    user.setUsername(username);
    user.setPassword(password);

    Thread.sleep(500);

    File file = new File("D:\\桌面\\User.txt");
    ObjectOutputStream objos = new ObjectOutputStream(new FileOutputStream(file));
    objos.writeObject(user);
    System.out.println("正在注入文件...");
    Thread.sleep(500);
    objos.close();
    System.out.println("注册成功！");
}
```
```java
    static void login(User user) throws IOException, ClassNotFoundException {
        ObjectInputStream objis = new ObjectInputStream(new FileInputStream(new File("D:\\桌面\\User.txt")));
        user = (User) objis.readObject();
        String pass = user.getPassword();
        String userName = user.getUsername();

        Scanner in = new Scanner(System.in);
        System.out.println("输入用户名：");
        String in1 = in.next();
        if (userName.equals(in1)){
            System.out.println("请输入密码：");
            String in2 = in.next();
            if (pass.equals(in2)){
                System.out.println("登陆成功！\n 您的用户信息：");
                System.out.println(user);
            }else {
                System.out.println("密码错误！");
            }
        }else {
            System.out.println("用户名不存在！");
        }
    }
```
### 5. TCP三次握手, 四次挥手的过程
```
三次握手：
1. a向b发送连接请求数据包
2. b向a发送同意请求ACK和要求同步的数据包SYN
3. a向b发出确认同步数据包
四次挥手:
1. A发送关闭连接请求，将FIN置1
2. B收到FIN确认信息，将ACK置1
3. B发送关闭连接应答，将FIN置1
4. A确认请求，将ACK置1. 连接关闭.
```

### 6. 利用IO流和File类实现文本文件的剪切, 讲一个文件夹里的至少两个文件剪切到另外一个文件夹里
```java
import java.io.*;
/**
 * 前提：
 * File对象传入的路径参数得是文件的具体路径，而不能是文件的上级目录。
 */
class Test {
    public static void main(String[] args) throws IOException {
        String srcName = "D:\\Atest\\";
        String destName = "D:\\桌面\\";
        StringBuilder srcSB = new StringBuilder();
        StringBuilder destSB = new StringBuilder();
        srcSB.append(srcName);  
        destSB.append(destName);
        File srcFile = new File(srcSB.toString());

        String[] list = srcFile.list();
        //list[0] = t1.txt  list[1] = t2.txt
        //list[i].toString() 获取文件名 拼接
        for (int i = 0; i < list.length; i++) {   //循环 文件数 次
            //在文件夹目录下拼接上文件的具体名字
            String srcFileName = String.valueOf(srcSB.append(list[i])); 
            String destFileName = String.valueOf(destSB.append(list[i]));
            //读
            BufferedReader reader = new BufferedReader(new FileReader(srcFileName));
            //写
            BufferedWriter writer = new BufferedWriter(new FileWriter(destFileName));
            String s = reader.readLine();
            //将内容写入
            while (s !=null){
                writer.write(s);
                writer.newLine();
                s= reader.readLine();
            }
            writer.close();
            reader.close();
            //重置文件名为当前完整文件名
            File file = new File(srcFileName);
            //删除文件
            file.delete();
            //重置StringBuilder内容为文件夹目录 。否则会导致无法找到正确目录，读取失败
            srcSB.replace(0, srcSB.capacity(), srcName);
            destSB.replace(0, destSB.capacity(), destName);
        }
    }
}

```
