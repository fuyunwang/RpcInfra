package com.fuyunwang.rpc_infra_netty.test;

import java.util.List;

/**
 * @Author fyw
 * @Date 2022/4/24 14:04
 * @Description: 基于反射模拟远程过程调用
 */
public interface DataService {
    /**
     * 发送数据
     *
     * @param body
     */
    String sendData(String body);

    /**
     * 获取数据
     *
     * @return
     */
    List<String> getList();
}
