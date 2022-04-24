package com.fuyunwang.rpc_infra_netty.common;

import com.fuyunwang.rpc_infra_netty.registry.URL;

import java.util.HashSet;
import java.util.Set;

/**
 * @Title CommonServerCache
 * @Author fyw
 * @Date 2022/4/25 0:17
 * @Description: 服务端的缓存服务
 */
public class CommonServerCache {
    public static final Set<URL> PROVIDER_URL_SET = new HashSet<>();
}
