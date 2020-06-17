# 静态代理
角色分析：  
  - 抽象角色:一般会使用接口和抽象类
  - 真实角色：被代理的角色
  - 代理角色：代理真是角色，代理真实角色后，一般会有附加操作
  - 客户：访问代理对象的人
  
  
抽象角色： rent 需要出租一个房子，方法
```java
public interface rent {

    //抽象角色，出租房子
    public void rent();
}

```


真是角色：HouseOwner 房东
```java
public class HouseOwneer implements rent {
    public void rent() {
        System.out.println("我是房东，出租房子");
    }
}
```

抽象角色：Proxy，中介，帮房东出租房子，同时附加方法
```java
public class Proxy implements rent {

    private HouseOwneer houseOwneer;


    public Proxy(HouseOwneer houseOwneer) {
        this.houseOwneer = houseOwneer;
    }

    public void rent() {
        see();
        houseOwneer.rent();

        hetong();
        fare();
    }

    //看房
    public void see(){
        System.out.println("中介带你去看房");
    }

    public void hetong(){
        System.out.println("中介和你签合同");
    }

    public void fare(){
        System.out.println("收中介费");
    }
}
```
客户 client 要租房的人,通过中介租房
```java
public class client {
    public static void main(String[] args) {
        
        //房东出租房子
        HouseOwneer houseOwneer=new HouseOwneer();
        //代理，中介帮房东出租房子，一般会有附加操作
        Proxy proxy=new Proxy(houseOwneer);
        
        //不用面对房东，直接面对中介
        proxy.rent();
    }
}
```

代理模式的好处：
- 使真实角色的操作更加纯粹，不用关注公共业务
- 公共业务交给代理角色，实现业务的分工
- 公共业务发生扩展时，便于管理

缺点：
- 一个真实角色就会产生代理角色：


# 动态代理

动态代理和静态代理的角色一样  
动态代理的代理类是动态生成的，不用我们直接写  
动态代理分为两大类：基于接口的动态代理、基于类的动态代理  

  
基于接口：原生的jdk类


需要了解两个类：Proxy：代理类 InvocationHandler：调用处理程序  


动态模式的好处：
- 使真实角色的操作更加纯粹，不用关注公共业务
- 公共业务交给代理角色，实现业务的分工
- 公共业务发生扩展时，便于管理