package com.fuyunwang.rpc_infra_netty.registry.zookeeper;

/**
 * @Title ProviderNodeInfo
 * @Author fyw
 * @Date 2022/4/25 0:05
 * @Description:
 */
public class ProviderNodeInfo {
    private String serviceName;

    private String address;

    public String getServiceName() {
        return serviceName;
    }

    public void setServiceName(String serviceName) {
        this.serviceName = serviceName;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    @Override
    public String toString() {
        return "ProviderNodeInfo{" +
                "serviceName='" + serviceName + '\'' +
                ", address='" + address + '\'' +
                '}';
    }
}
