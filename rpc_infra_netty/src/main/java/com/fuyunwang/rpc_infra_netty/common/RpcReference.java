package com.fuyunwang.rpc_infra_netty.common;

import com.fuyunwang.rpc_infra_netty.proxy.ProxyFactory;

/**
 * @Title RpcReference
 * @Author fyw
 * @Date 2022/4/24 15:34
 * @Description:
 */
public class RpcReference {
    private ProxyFactory proxyFactory;

    public RpcReference(ProxyFactory proxyFactory) {
        this.proxyFactory = proxyFactory;
    }

    // 根据类型获得对应代理对象
    public <T> T getProxy(Class<T> clazz) throws Throwable{
        return proxyFactory.getProxy(clazz);
    }
}
