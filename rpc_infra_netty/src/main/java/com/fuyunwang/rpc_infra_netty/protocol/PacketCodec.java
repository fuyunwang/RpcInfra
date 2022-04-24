package com.fuyunwang.rpc_infra_netty.protocol;

import com.fuyunwang.rpc_infra_netty.entity.LoginRequestPacket;
import com.fuyunwang.rpc_infra_netty.protocol.command.Command;
import com.fuyunwang.rpc_infra_netty.serialize.Serializer;
import com.fuyunwang.rpc_infra_netty.serialize.impl.JsonSerializer;
import io.netty.buffer.ByteBuf;

import java.util.HashMap;
import java.util.Map;

/**
 * @Title PacketCodec
 * @Author fyw
 * @Date 2022/4/24 10:54
 * @Description:
 */
public class PacketCodec {
    // 定义魔数
    public static final int MAGIC_NUMBER=0x12345678;
    private final Map<Byte,Class<? extends Packet>> operationMap;
    private final Map<Byte, Serializer> serializerMap;
    // 单例模式
    private volatile static PacketCodec INSTANCE=null;
    private PacketCodec(){
        operationMap=new HashMap<>();
        serializerMap=new HashMap<>();
        operationMap.put(Command.LOGIN_REQUEST, LoginRequestPacket.class);

        // 预处理所有序列化方式
        Serializer jsonSerializer=new JsonSerializer();
        serializerMap.put(jsonSerializer.getSerializerAlgorithm(),jsonSerializer);
    }
    public static PacketCodec newInstance(){
        if (INSTANCE==null){
            synchronized (PacketCodec.class){
                if (INSTANCE==null){
                    INSTANCE=new PacketCodec();
                }
            }
        }
        return INSTANCE;
    }

    public void encode(ByteBuf byteBuf,Packet packet){
        // 先根据序列化算法进行数据内容的序列化
        // 将序列化后的数据内容按照协议进行编码
        final byte[] bytes = Serializer.DEFAULT.serialize(packet);

        byteBuf.writeInt(MAGIC_NUMBER);
        byteBuf.writeByte(packet.getVersion());
        byteBuf.writeByte(Serializer.DEFAULT.getSerializerAlgorithm());
        byteBuf.writeByte(packet.getCommand());
        byteBuf.writeInt(bytes.length);
        byteBuf.writeBytes(bytes);
    }

    public Packet decode(ByteBuf byteBuf){
        // 按照协议进行解码

        // 跳过魔数
        byteBuf.skipBytes(4);
        // 跳过版本号
        byteBuf.skipBytes(1);
        // 得到序列化算法
        byte serializeAlgorithm=byteBuf.readByte();
        // 得到指令即操作
        byte command=byteBuf.readByte();
        // 得到数据长度
        int length=byteBuf.readInt();
        // 真正读取数据
        byte[] bytes=new byte[length];
        byteBuf.readBytes(bytes);
        // 根据指令字段来操作数据流，即对应方式序列化
        final Class<? extends Packet> classType = operationMap.get(command);
        final Serializer serializer = serializerMap.get(serializeAlgorithm);
        if (serializer!=null&&classType!=null){
            return serializer.deSerialize(classType,bytes);
        }
        return null;
    }

}
