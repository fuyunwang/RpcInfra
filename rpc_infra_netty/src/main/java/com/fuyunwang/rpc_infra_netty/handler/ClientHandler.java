package com.fuyunwang.rpc_infra_netty.handler;

import com.fuyunwang.rpc_infra_netty.entity.RpcInvocationPacket;
import com.fuyunwang.rpc_infra_netty.test.ProviderClass;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;

/**
 * @Title ClientHandler
 * @Author fyw
 * @Date 2022/4/24 15:13
 * @Description:
 */
public class ClientHandler extends SimpleChannelInboundHandler<RpcInvocationPacket> {
    @Override
    protected void channelRead0(ChannelHandlerContext ctx, RpcInvocationPacket msg) throws Exception {
        if (!ProviderClass.getInstance().RESP_MAP.containsKey(msg.getUuid())){
            throw new RuntimeException("服务端发送了错误的消息");
        }
    }
}
