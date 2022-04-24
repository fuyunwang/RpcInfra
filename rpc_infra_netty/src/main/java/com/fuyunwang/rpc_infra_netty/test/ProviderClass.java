package com.fuyunwang.rpc_infra_netty.test;

import com.fuyunwang.rpc_infra_netty.entity.RpcInvocationPacket;
import com.fuyunwang.rpc_infra_netty.test.impl.DataServiceImpl;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @Title ProviderClass
 * @Author fyw
 * @Date 2022/4/24 14:07
 * @Description:
 */
public class ProviderClass {
    private volatile static ProviderClass INSTANCE=null;
    public final Map<String,Object> PROVIDER_CLASS_MAP;
    public final BlockingQueue<RpcInvocationPacket> SEND_QUEUE;
    public final Map<String,Object> RESP_MAP;
    private ProviderClass(){
        PROVIDER_CLASS_MAP=new HashMap<>();
        SEND_QUEUE = new ArrayBlockingQueue(100);
        RESP_MAP= new ConcurrentHashMap<>();
        DataService serviceBean=new DataServiceImpl();
        if(serviceBean.getClass().getInterfaces().length==0){
            throw new RuntimeException("service must had interfaces!");
        }
        Class[] classes = serviceBean.getClass().getInterfaces();
        if(classes.length>1){
            throw new RuntimeException("service must only had one interfaces!");
        }
        Class interfaceClass = classes[0];
        PROVIDER_CLASS_MAP.put(interfaceClass.getName(), serviceBean);
    }
    public static ProviderClass getInstance(){
        if (INSTANCE==null){
            synchronized (ProviderClass.class){
                if (INSTANCE==null){
                    INSTANCE=new ProviderClass();
                }
            }
        }
        return INSTANCE;
    }
    public Map<String, Object> getProviderMap() {
        return PROVIDER_CLASS_MAP;
    }

    public BlockingQueue<RpcInvocationPacket> getSEND_QUEUE() {
        return SEND_QUEUE;
    }

    public Map<String, Object> getRESP_MAP() {
        return RESP_MAP;
    }
}
