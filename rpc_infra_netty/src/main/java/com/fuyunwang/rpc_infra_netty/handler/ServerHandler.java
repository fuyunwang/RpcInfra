package com.fuyunwang.rpc_infra_netty.handler;

import com.fuyunwang.rpc_infra_netty.entity.RpcInvocationPacket;
import com.fuyunwang.rpc_infra_netty.protocol.PacketCodec;
import com.fuyunwang.rpc_infra_netty.test.ProviderClass;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;

import java.lang.reflect.Method;

/**
 * @Title ServerHandler
 * @Author fyw
 * @Date 2022/4/24 13:49
 * @Description: 服务端接受数据并处理
 */
public class ServerHandler extends SimpleChannelInboundHandler<RpcInvocationPacket> {
    @Override
    protected void channelRead0(ChannelHandlerContext channelHandlerContext,
                                RpcInvocationPacket rpcInvocationPacket) throws Exception {
        final String targetServiceName = rpcInvocationPacket.getTargetServiceName();
        final Object o = ProviderClass.getInstance().getProviderMap().get(targetServiceName);   // 找到目标对象
        Object result=null;
        final Method[] declaredMethods = o.getClass().getDeclaredMethods(); // 反射到目标对象的方法
        for (Method method:declaredMethods){
            if (method.getName().equals(rpcInvocationPacket.getTargetMethod())){
                // 判断是否与真正调用的方法一致
                if (method.getReturnType().equals(Void.TYPE)){
                    method.invoke(o,rpcInvocationPacket.getArgs());
                }else{
                    result=method.invoke(o,rpcInvocationPacket.getArgs());
                }
            }
        }

        // 设置服务端处理结果并返回客户端相应
        rpcInvocationPacket.setResponse(result);
        channelHandlerContext.channel().writeAndFlush(rpcInvocationPacket);
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        cause.printStackTrace();
        if (ctx.channel().isActive()){
            ctx.close();
        }
    }
}
