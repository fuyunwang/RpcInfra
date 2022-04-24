package com.fuyunwang.rpc_infra_netty.observer;

/**
 * @Author fyw
 * @Date 2022/4/25 0:44
 * @Description:
 */
public interface RpcListener<T> {
    void callBack(Object t);
}
