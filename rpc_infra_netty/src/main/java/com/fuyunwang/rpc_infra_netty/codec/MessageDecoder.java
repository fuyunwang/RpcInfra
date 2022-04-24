package com.fuyunwang.rpc_infra_netty.codec;

import com.fuyunwang.rpc_infra_netty.protocol.PacketCodec;
import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.MessageToMessageDecoder;

import java.util.List;

/**
 * @Title MessageDecoder
 * @Author fyw
 * @Date 2022/4/24 12:54
 * @Description:
 */
public class MessageDecoder extends MessageToMessageDecoder<ByteBuf> {
    @Override
    protected void decode(ChannelHandlerContext ctx, ByteBuf in, List<Object> out) throws Exception {
        out.add(PacketCodec.newInstance().decode(in));
    }
}
