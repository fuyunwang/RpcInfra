package com.fuyunwang.rpc_infra_netty.serialize.impl;

import com.alibaba.fastjson.JSON;
import com.fuyunwang.rpc_infra_netty.serialize.Serializer;
import com.fuyunwang.rpc_infra_netty.serialize.SerializerAlgorithm;

/**
 * @Title JsonSerializer
 * @Author fyw
 * @Date 2022/4/24 11:16
 * @Description: JSON序列化方式
 */
public class JsonSerializer implements Serializer {
    @Override
    public byte getSerializerAlgorithm() {
        return SerializerAlgorithm.JSON;
    }

    @Override
    public byte[] serialize(Object data) {
        return JSON.toJSONBytes(data);
    }

    @Override
    public <T> T deSerialize(Class<T> clazz, byte[] data) {
        return JSON.parseObject(data,clazz);
    }
}
