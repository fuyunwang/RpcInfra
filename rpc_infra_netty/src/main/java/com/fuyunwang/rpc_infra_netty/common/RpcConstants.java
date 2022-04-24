package com.fuyunwang.rpc_infra_netty.common;

/**
 * @Title RpcConstants
 * @Author fyw
 * @Date 2022/4/24 13:26
 * @Description:
 */
public class RpcConstants {
    // 定义魔数
    public static final int MAGIC_NUMBER=0x12345678;
    // 定义协议的前5个部分所占基本长度
    public static final int BASE_LENGTH=4+1+1+1+4;

}
