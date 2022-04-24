package com.fuyunwang.rpc_infra_netty.protocol;

import com.alibaba.fastjson.annotation.JSONField;
import lombok.Data;

/**
 * @Title Packet
 * @Author fyw
 * @Date 2022/4/24 10:49
 * @Description:
 */
@Data
public abstract class Packet {

    @JSONField(serialize = false,deserialize = false)
    private Byte version=1; // 版本不参与序列化和反序列化

    @JSONField(serialize = false)
    public abstract Byte getCommand();  // 定义获取指令的方法
}
