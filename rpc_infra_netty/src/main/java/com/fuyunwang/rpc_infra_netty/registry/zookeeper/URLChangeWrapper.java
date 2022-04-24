package com.fuyunwang.rpc_infra_netty.registry.zookeeper;

import java.util.List;

/**
 * @Title URLChangeWrapper
 * @Author fyw
 * @Date 2022/4/25 0:50
 * @Description: 封装节点信息
 */
public class URLChangeWrapper {
    private String serviceName;

    private List<String> providerUrl;

    public String getServiceName() {
        return serviceName;
    }

    public void setServiceName(String serviceName) {
        this.serviceName = serviceName;
    }

    public List<String> getProviderUrl() {
        return providerUrl;
    }

    public void setProviderUrl(List<String> providerUrl) {
        this.providerUrl = providerUrl;
    }

    @Override
    public String toString() {
        return "URLChangeWrapper{" +
                "serviceName='" + serviceName + '\'' +
                ", providerUrl=" + providerUrl +
                '}';
    }
}
