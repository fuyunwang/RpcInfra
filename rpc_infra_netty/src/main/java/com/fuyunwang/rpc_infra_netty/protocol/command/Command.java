package com.fuyunwang.rpc_infra_netty.protocol.command;

/**
 * @Author fyw
 * @Date 2022/4/24 11:12
 * @Description: 指令即操作，通过对指令和请求响应对象的类型进行Map映射，实现当数据到来时序列化和反序列化操作
 */
public interface Command {
    Byte LOGIN_REQUEST = 1;

    Byte LOGIN_RESPONSE = 2;

    Byte RPC_INVOCATION = 3;
}
