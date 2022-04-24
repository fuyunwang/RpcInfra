package com.fuyunwang.rpc_infra_netty.entity;

import com.fuyunwang.rpc_infra_netty.protocol.Packet;
import com.fuyunwang.rpc_infra_netty.protocol.command.Command;
import lombok.Data;

import static com.fuyunwang.rpc_infra_netty.protocol.command.Command.LOGIN_REQUEST;

/**
 * @Title LoginRequestPacket
 * @Author fyw
 * @Date 2022/4/24 11:25
 * @Description:
 */
@Data
public class LoginRequestPacket extends Packet {
    private String username;
    private String password;
    @Override
    public Byte getCommand() {
        return LOGIN_REQUEST;
    }
}
