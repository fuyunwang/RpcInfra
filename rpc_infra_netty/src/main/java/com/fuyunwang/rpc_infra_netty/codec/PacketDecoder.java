package com.fuyunwang.rpc_infra_netty.codec;

import com.fuyunwang.rpc_infra_netty.common.RpcConstants;
import com.fuyunwang.rpc_infra_netty.protocol.Packet;
import com.fuyunwang.rpc_infra_netty.protocol.PacketCodec;
import com.fuyunwang.rpc_infra_netty.serialize.Serializer;
import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.ByteToMessageDecoder;

import java.util.List;

/**
 * @Title PacketDecoder
 * @Author fyw
 * @Date 2022/4/24 13:05
 * @Description: 解码器，服务器将不断收到的客户端的字节流数据放到ByteBuf这个字节数组容器中
 *
 * 考虑到粘包（多个包一起发送）和半包（一个包多次发送）的情况，需要记录初始readerIndex，如果后面解析不出一个完整的数据包则将指针归位，
 * 否则读取一个完整的数据包，readerIndex后移不断的收取数据进而截取下一个数据包
 *
 */
public class PacketDecoder extends ByteToMessageDecoder {
    @Override
    protected void decode(ChannelHandlerContext ctx, ByteBuf in, List<Object> out) throws Exception {
        if (in.readableBytes()>= RpcConstants.BASE_LENGTH){ // 此时说明有数据可读
            int readerIndex;    // 记录当前读指针，用于在数据包不完整的情况下恢复读取位置
            while (true){   // 检查是否是按照协议组装的数据包
                readerIndex=in.readerIndex();
                if (in.readInt()==RpcConstants.MAGIC_NUMBER){
                    break;
                }else{
                    ctx.close();
                    return;
                }
            }

            // 跳过版本号
            in.skipBytes(1);
            // 得到序列化算法
            byte serializeAlgorithm=in.readByte();
            // 得到指令即操作
            byte command=in.readByte();
            // 得到数据长度
            int length=in.readInt();

            if (in.readableBytes()<length){ // 数据包不完整
                in.readerIndex(readerIndex);
                return;
            }

            // 真正读取数据
            byte[] bytes=new byte[length];
            in.readBytes(bytes);
            // 根据指令字段来操作数据流，即对应方式序列化
            final Class<? extends Packet> classType = PacketCodec.newInstance().getOperationMap().get(command);
            final Serializer serializer = PacketCodec.newInstance().getSerializerMap().get(serializeAlgorithm);
            final Packet packet = serializer.deSerialize(classType, bytes);
            out.add(packet);
        }
    }
}
