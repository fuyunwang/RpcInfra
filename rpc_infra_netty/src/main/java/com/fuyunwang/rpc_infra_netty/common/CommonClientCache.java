package com.fuyunwang.rpc_infra_netty.common;

import com.fuyunwang.rpc_infra_netty.entity.RpcInvocationPacket;
import com.fuyunwang.rpc_infra_netty.registry.ChannelFutureWrapper;
import com.fuyunwang.rpc_infra_netty.registry.URL;

import java.util.*;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @Title CommonClientCache
 * @Author fyw
 * @Date 2022/4/25 0:17
 * @Description: 客户端的缓存服务
 */
public class CommonClientCache {
    public static BlockingQueue<RpcInvocationPacket> SEND_QUEUE = new ArrayBlockingQueue(100);
    public static Map<String,Object> RESP_MAP = new ConcurrentHashMap<>();
    public static ClientConfig CLIENT_CONFIG;
    // 作为服务提供者名称与集群服务URL的映射
    public static List<String> SUBSCRIBE_SERVICE_LIST = new ArrayList<>();
    public static Map<String, List<URL>> URL_MAP = new ConcurrentHashMap<>();
    public static Set<String> SERVER_ADDRESS = new HashSet<>();
    // 每次进行远程调用的时候都是从这里面去选择服务提供者
    public static Map<String, List<ChannelFutureWrapper>> CONNECT_MAP = new ConcurrentHashMap<>();
}
