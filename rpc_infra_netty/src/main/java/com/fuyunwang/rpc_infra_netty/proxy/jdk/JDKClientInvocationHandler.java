package com.fuyunwang.rpc_infra_netty.proxy.jdk;

import com.fuyunwang.rpc_infra_netty.entity.RpcInvocationPacket;
import com.fuyunwang.rpc_infra_netty.test.ProviderClass;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.util.UUID;

/**
 * @Title JDKClientInvocationHandler
 * @Author fyw
 * @Date 2022/4/24 15:19
 * @Description: 代理对象实现从客户端到服务端的首次数据发送
 */
public class JDKClientInvocationHandler implements InvocationHandler {

    private static final Object OBJECT=new Object();

    private Class<?> clazz;

    public JDKClientInvocationHandler(Class<?> clazz) {
        this.clazz = clazz;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        RpcInvocationPacket invocationPacket=new RpcInvocationPacket();
        invocationPacket.setArgs(args);
        invocationPacket.setTargetMethod(method.getName());
        invocationPacket.setTargetServiceName(clazz.getName());
        invocationPacket.setUuid(UUID.randomUUID().toString());
        ProviderClass.getInstance().RESP_MAP.put(invocationPacket.getUuid(),OBJECT);
        ProviderClass.getInstance().SEND_QUEUE.add(invocationPacket);

        long beginTime=System.currentTimeMillis();
        while (System.currentTimeMillis()-beginTime<3*1000){    // 主程序中有单独的IO线程发送数据到服务端，所以这里只需要监听超时时长
            final Object o = ProviderClass.getInstance().RESP_MAP.get(invocationPacket.getUuid());
            if (o instanceof RpcInvocationPacket){
                return ((RpcInvocationPacket)o).getResponse();
            }
        }
        throw new RuntimeException("服务端响应超时");
    }
}
