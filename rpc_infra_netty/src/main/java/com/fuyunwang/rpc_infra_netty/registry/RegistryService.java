package com.fuyunwang.rpc_infra_netty.registry;

/**
 * @Author fyw
 * @Date 2022/4/24 21:47
 * @Description:
 */
public interface RegistryService {
    void register(URL url); // 被调用方注册服务
    void unRegister(URL url);   // 被调用方下线，主动将注册信息从zk指定节点上删除
    void subscribe(URL url);    // 调用方主动从注册中心中提取现有的服务提供者地址，实现服务订阅
    void doUnSubscribe(URL url);    // 调用方不再需要调用服务，主动取消订阅并将注册信息从zk指定节点上删除
}
