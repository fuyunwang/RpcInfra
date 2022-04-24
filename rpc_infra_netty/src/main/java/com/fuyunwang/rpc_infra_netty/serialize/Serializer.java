package com.fuyunwang.rpc_infra_netty.serialize;

import com.fuyunwang.rpc_infra_netty.serialize.impl.JsonSerializer;

/**
 * @Title Serializer
 * @Author fyw
 * @Date 2022/4/24 11:15
 * @Description:
 */
public interface Serializer {
    // 定义默认的序列化手段
    Serializer DEFAULT=new JsonSerializer();

    // 当前序列化算法的编号
    byte getSerializerAlgorithm();
    // 序列化对象成二进制
    byte[] serialize(Object data);
    // 二进制数据反序列化成对象
    <T> T deSerialize(Class<T> clazz,byte[] data);
}
