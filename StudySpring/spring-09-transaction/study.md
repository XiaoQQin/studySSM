# spring与mybatis之间的事务管理

## 声明式事务

在spring-dao.xml中声明事务
```xml

<!--配置声明式事务-->
<bean id="transactionManager" class="org.springframework.jdbc.datasource.DataSourceTransactionManager">
    <constructor-arg ref="datasource" />
</bean>
```

结合aop插入事务  

首先配置事务通知，也就是事务
```xml

    <!--配置事务通知-->

    <tx:advice id="txAdvice" transaction-manager="transactionManager">
        <!--给哪些方法配置事务-->
        <tx:attributes>
            <tx:method name="*" propagation="REQUIRED"/>
        </tx:attributes>
    </tx:advice>
```

像AOP一样配置切入点

```xml
    <!--配置事务切入-->
    <aop:config>
        <aop:pointcut id="txPoint" expression="execution(* com.hwm.mapper.*.*(..))"/>
        <aop:advisor advice-ref="txAdvice" pointcut-ref="txPoint"/>
    </aop:config>
```

那么UserMapper中所有方法都是事务执行
## 编程式事务
在代码中使用