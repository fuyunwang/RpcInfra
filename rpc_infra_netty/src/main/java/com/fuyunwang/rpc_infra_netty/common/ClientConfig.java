package com.fuyunwang.rpc_infra_netty.common;

/**
 * @Title ClientConfig
 * @Author fyw
 * @Date 2022/4/24 14:23
 * @Description:
 */
public class ClientConfig {
    private Integer port;

    private String serverAddr;

    public String getServerAddr() {
        return serverAddr;
    }

    public void setServerAddr(String serverAddr) {
        this.serverAddr = serverAddr;
    }

    public Integer getPort() {
        return port;
    }

    public void setPort(Integer port) {
        this.port = port;
    }
}
