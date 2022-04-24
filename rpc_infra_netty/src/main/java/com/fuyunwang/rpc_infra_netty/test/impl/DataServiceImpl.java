package com.fuyunwang.rpc_infra_netty.test.impl;

import com.fuyunwang.rpc_infra_netty.test.DataService;

import java.util.ArrayList;
import java.util.List;

/**
 * @Title DataServiceImpl
 * @Author fyw
 * @Date 2022/4/24 14:06
 * @Description:
 */
public class DataServiceImpl implements DataService {
    @Override
    public String sendData(String body) {
        System.out.println("己收到的参数长度："+body.length());
        return "success";
    }

    @Override
    public List<String> getList() {
        ArrayList<String> arrayList = new ArrayList<>();
        arrayList.add("idea1");
        arrayList.add("idea2");
        arrayList.add("idea3");
        return arrayList;
    }
}
