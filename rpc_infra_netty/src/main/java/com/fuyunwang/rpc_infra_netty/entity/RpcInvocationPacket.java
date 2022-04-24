package com.fuyunwang.rpc_infra_netty.entity;

import com.fuyunwang.rpc_infra_netty.protocol.Packet;

import java.util.Arrays;

import static com.fuyunwang.rpc_infra_netty.protocol.command.Command.RPC_INVOCATION;

/**
 * @Title RpcInvocationPacket
 * @Author fyw
 * @Date 2022/4/24 11:25
 * @Description: 核心的传输数据
 */
public class RpcInvocationPacket extends Packet {

    private String targetMethod;

    private String targetServiceName;

    private Object[] args;

    private String uuid;    // 记录请求和响应，用于匹配请求线程并返回给调用线程

    private Object response;

    public Object getResponse() {
        return response;
    }

    public void setResponse(Object response) {
        this.response = response;
    }

    public String getUuid() {
        return uuid;
    }

    public void setUuid(String uuid) {
        this.uuid = uuid;
    }

    public String getTargetMethod() {
        return targetMethod;
    }

    public void setTargetMethod(String targetMethod) {
        this.targetMethod = targetMethod;
    }

    public String getTargetServiceName() {
        return targetServiceName;
    }

    public void setTargetServiceName(String targetServiceName) {
        this.targetServiceName = targetServiceName;
    }

    public Object[] getArgs() {
        return args;
    }

    public void setArgs(Object[] args) {
        this.args = args;
    }


    @Override
    public String toString() {
        return "RpcInvocation{" +
                "targetMethod='" + targetMethod + '\'' +
                ", targetServiceName='" + targetServiceName + '\'' +
                ", args=" + Arrays.toString(args) +
                ", uuid='" + uuid + '\'' +
                ", response=" + response +
                '}';
    }

    @Override
    public Byte getCommand() {
        return RPC_INVOCATION;
    }
}
