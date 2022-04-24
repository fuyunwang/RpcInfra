package com.fuyunwang.rpc_infra_netty.registry.zookeeper;

import com.fuyunwang.rpc_infra_netty.registry.RegistryService;
import com.fuyunwang.rpc_infra_netty.registry.URL;

/**
 * @Title AbstractRegister
 * @Author fyw
 * @Date 2022/4/25 0:15
 * @Description: 作为一些基础的注册数据的统一处理
 */
public abstract class AbstractRegister implements RegistryService {
    @Override
    public void register(URL url) {

    }

    @Override
    public void unRegister(URL url) {

    }

    @Override
    public void subscribe(URL url) {

    }

    @Override
    public void doUnSubscribe(URL url) {

    }
}
