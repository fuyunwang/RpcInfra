package com.fuyunwang.rpc_infra_netty.codec;

import com.fuyunwang.rpc_infra_netty.protocol.Packet;
import com.fuyunwang.rpc_infra_netty.protocol.PacketCodec;
import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.MessageToByteEncoder;

/**
 * @Title PacketEncoder
 * @Author fyw
 * @Date 2022/4/24 12:52
 * @Description: 编码器直接按照协议封装发送数据即可，不存在粘包问题
 */
public class PacketEncoder extends MessageToByteEncoder<Packet> {
    @Override
    protected void encode(ChannelHandlerContext ctx, Packet msg, ByteBuf out) throws Exception {
        PacketCodec.newInstance().encode(out,msg);
    }
}
