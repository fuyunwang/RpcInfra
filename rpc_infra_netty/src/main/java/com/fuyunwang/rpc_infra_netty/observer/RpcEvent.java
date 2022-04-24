package com.fuyunwang.rpc_infra_netty.observer;

/**
 * @Author fyw
 * @Date 2022/4/25 0:42
 * @Description: 定义抽象事件用于装载需要传递的数据信息
 */
public interface RpcEvent {
    Object getData();

    RpcEvent setData(Object data);
}
