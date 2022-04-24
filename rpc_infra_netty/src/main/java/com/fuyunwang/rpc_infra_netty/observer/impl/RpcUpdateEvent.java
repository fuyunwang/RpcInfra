package com.fuyunwang.rpc_infra_netty.observer.impl;

import com.fuyunwang.rpc_infra_netty.observer.RpcEvent;

/**
 * @Title RpcUpdateEvent
 * @Author fyw
 * @Date 2022/4/25 0:43
 * @Description: 当某个zk的节点发生数据变更时，发送变更事件，由对应的监听器捕获数据并处理。具体来说，对于变更事件，客户端需要更新本地缓存服务列表
 */
public class RpcUpdateEvent implements RpcEvent {
    private Object data;

    public RpcUpdateEvent(Object data) {
        this.data = data;
    }

    @Override
    public Object getData() {
        return data;
    }

    @Override
    public RpcEvent setData(Object data) {
        this.data = data;
        return this;
    }
}
