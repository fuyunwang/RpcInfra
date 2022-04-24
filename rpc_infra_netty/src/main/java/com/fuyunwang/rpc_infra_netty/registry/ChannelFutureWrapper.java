package com.fuyunwang.rpc_infra_netty.registry;

import io.netty.channel.ChannelFuture;

/**
 * @Title ChannelFutureWrapper
 * @Author fyw
 * @Date 2022/4/25 0:20
 * @Description:
 */
public class ChannelFutureWrapper {
    private ChannelFuture channelFuture;

    private String host;

    private Integer port;

    public ChannelFuture getChannelFuture() {
        return channelFuture;
    }

    public void setChannelFuture(ChannelFuture channelFuture) {
        this.channelFuture = channelFuture;
    }

    public String getHost() {
        return host;
    }

    public void setHost(String host) {
        this.host = host;
    }

    public Integer getPort() {
        return port;
    }

    public void setPort(Integer port) {
        this.port = port;
    }
}
