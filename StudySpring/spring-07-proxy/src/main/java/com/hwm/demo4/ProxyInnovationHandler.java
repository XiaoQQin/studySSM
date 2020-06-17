package com.hwm.demo4;

import com.hwm.demo3.Rent;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

//会用这个类，自动生成代理类
public class ProxyInnovationHandler implements InvocationHandler {


    //代理谁
    private Object target;

    public void setTarget(Object target) {
        this.target = target;
    }

    //生成得到代理类
    public Object getProxy(){
        return Proxy.newProxyInstance(this.getClass().getClassLoader(),target.getClass().getInterfaces(),this);
    }



    //处理代理实例，并且返回结果
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {

        //动态代理的本质使用反射类

        log(method.getName());
        Object result = method.invoke(target, args);

        return result;
    }


    private void log(String msg){
        System.out.println("执行了"+msg+"方法");
    }

}
