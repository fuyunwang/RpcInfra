package com.fuyunwang.rpc_infra_netty.observer;

import com.fuyunwang.rpc_infra_netty.observer.impl.ServiceUpdateListener;
import com.fuyunwang.rpc_infra_netty.utils.CommonUtil;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;


/**
 * @Title RpcListenerLoader
 * @Author fyw
 * @Date 2022/4/25 0:45
 * @Description: 事件发送器
 */
public class RpcListenerLoader {
    private static List<RpcListener> iRpcListenerList = new ArrayList<>();

    private static ExecutorService eventThreadPool = Executors.newFixedThreadPool(2);

    public static void registerListener(RpcListener iRpcListener) {
        iRpcListenerList.add(iRpcListener);
    }

    public void init() {
        registerListener(new ServiceUpdateListener());
    }

    /**
     * 获取接口上的泛型T
     *
     * @param o     接口
     */
    public static Class<?> getInterfaceT(Object o) {
        Type[] types = o.getClass().getGenericInterfaces();
        ParameterizedType parameterizedType = (ParameterizedType) types[0];
        Type type = parameterizedType.getActualTypeArguments()[0];
        if (type instanceof Class<?>) {
            return (Class<?>) type;
        }
        return null;
    }

    public static void sendEvent(RpcEvent iRpcEvent) {
        if(CommonUtil.isEmptyList(iRpcListenerList)){
            return;
        }
        for (RpcListener<?> iRpcListener : iRpcListenerList) {
            Class<?> type = getInterfaceT(iRpcListener);
            if(type.equals(iRpcEvent.getClass())){
                eventThreadPool.execute(new Runnable() {
                    @Override
                    public void run() {
                        try {
                            iRpcListener.callBack(iRpcEvent.getData());
                        }catch (Exception e){
                            e.printStackTrace();
                        }
                    }
                });
            }
        }
    }

}
