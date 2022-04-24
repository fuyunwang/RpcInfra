package com.fuyunwang.rpc_infra_netty.proxy;

/**
 * @Author fyw
 * @Date 2022/4/24 15:19
 * @Description:
 */
public interface ProxyFactory {
    <T> T getProxy(Class<?> clazz) throws Throwable;
}
