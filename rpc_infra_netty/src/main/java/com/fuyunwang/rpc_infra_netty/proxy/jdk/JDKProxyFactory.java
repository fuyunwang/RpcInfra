package com.fuyunwang.rpc_infra_netty.proxy.jdk;

import com.fuyunwang.rpc_infra_netty.proxy.ProxyFactory;

import java.lang.reflect.Proxy;

/**
 * @Title JDKProxyFactory
 * @Author fyw
 * @Date 2022/4/24 15:19
 * @Description:
 */
public class JDKProxyFactory implements ProxyFactory {
    @Override
    public <T> T getProxy(Class<?> clazz) throws Throwable {
        return (T) Proxy.newProxyInstance(clazz.getClassLoader(),
                new Class[]{clazz},new JDKClientInvocationHandler(clazz));
    }
}
