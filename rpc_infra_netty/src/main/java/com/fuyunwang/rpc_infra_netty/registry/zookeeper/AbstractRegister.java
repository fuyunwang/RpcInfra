package com.fuyunwang.rpc_infra_netty.registry.zookeeper;

import com.fuyunwang.rpc_infra_netty.registry.RegistryService;
import com.fuyunwang.rpc_infra_netty.registry.URL;

import java.util.List;

import static com.fuyunwang.rpc_infra_netty.common.CommonClientCache.SUBSCRIBE_SERVICE_LIST;
import static com.fuyunwang.rpc_infra_netty.common.CommonServerCache.PROVIDER_URL_SET;

/**
 * @Title AbstractRegister
 * @Author fyw
 * @Date 2022/4/25 0:15
 * @Description: 作为一些基础的注册数据的统一处理
 */
public abstract class AbstractRegister implements RegistryService {
    @Override
    public void register(URL url) {
        PROVIDER_URL_SET.add(url);
    }

    @Override
    public void unRegister(URL url) {
        PROVIDER_URL_SET.remove(url);
    }

    @Override
    public void subscribe(URL url) {
        SUBSCRIBE_SERVICE_LIST.add(url.getServiceName());
    }

    /**
     * 留给子类扩展
     *
     * @param url
     */
    public abstract void doAfterSubscribe(URL url);

    /**
     * 留给子类扩展
     *
     * @param url
     */
    public abstract void doBeforeSubscribe(URL url);

    /**
     * 留给子类扩展
     *
     * @param serviceName
     * @return
     */
    public abstract List<String> getProviderIps(String serviceName);


    @Override
    public void doUnSubscribe(URL url) {
        SUBSCRIBE_SERVICE_LIST.remove(url.getServiceName());
    }
}
