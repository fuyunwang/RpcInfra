package com.fuyunwang.rpc_infra_netty.handler;

import com.fuyunwang.rpc_infra_netty.entity.RpcInvocationPacket;
import com.fuyunwang.rpc_infra_netty.test.ProviderClass;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.util.ReferenceCountUtil;

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
        ProviderClass.getInstance().RESP_MAP.put(msg.getUuid(),msg);  // 初始放入的是Object，经过服务器响应之后存入处理结果
        ReferenceCountUtil.release(msg);
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        super.exceptionCaught(ctx, cause);
        if (ctx.channel().isActive()){
            ctx.close();
        }
    }
}
