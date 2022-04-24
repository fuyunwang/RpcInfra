package com.fuyunwang.rpc_infra_netty;

import com.alibaba.fastjson.JSON;
import com.fuyunwang.rpc_infra_netty.common.ClientConfig;
import com.fuyunwang.rpc_infra_netty.common.RpcReference;
import com.fuyunwang.rpc_infra_netty.entity.RpcInvocationPacket;
import com.fuyunwang.rpc_infra_netty.proxy.jdk.JDKProxyFactory;
import com.fuyunwang.rpc_infra_netty.test.DataService;
import com.fuyunwang.rpc_infra_netty.test.ProviderClass;
import io.netty.bootstrap.Bootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;

/**
 * @Title Client
 * @Author fyw
 * @Date 2022/4/24 14:57
 * @Description:
 */
public class Client {
    private void beginSendMsg() throws InterruptedException {
        ClientConfig clientConfig=new ClientConfig();
        clientConfig.setPort(9000);
        clientConfig.setServerAddr("localhost");

        NioEventLoopGroup workerGroup=new NioEventLoopGroup();
        Bootstrap bootstrap=new Bootstrap();
        bootstrap.group(workerGroup)
                .channel(NioSocketChannel.class)
                .handler(new ChannelInitializer<SocketChannel>() {
                    @Override
                    protected void initChannel(SocketChannel socketChannel) throws Exception {

                    }
                });
        final ChannelFuture future = bootstrap.connect(clientConfig.getServerAddr(), clientConfig.getPort()).sync();
        Thread thread=new Thread(new AsyncSendJob(future));
        thread.start();
    }
    public static void main(String[] args) throws Throwable {
        Client client=new Client();
        client.beginSendMsg();
        // 注入代理工厂，通过代理对象将数据放入发送队列
        RpcReference rpcReference=new RpcReference(new JDKProxyFactory());
        final DataService dataService = rpcReference.getProxy(DataService.class);
        for(int i=0;i<100;i++){
            String result = dataService.sendData("test");
            System.out.println(result);
        }
        // 异步线程将队列中的数据依次发送给服务端，等待服务端响应数据结果。使用单独的IO线程发送数据，实现异步化提升性能
    }

    // 定义异步发送信息的任务
    class AsyncSendJob implements Runnable{
        private ChannelFuture channelFuture;

        public AsyncSendJob(ChannelFuture channelFuture) {
            this.channelFuture = channelFuture;
        }

        @Override
        public void run() {
            while (true) {
                try {
                    //阻塞模式
                    RpcInvocationPacket data = ProviderClass.getInstance().SEND_QUEUE.take();
                    //netty的通道负责发送数据给服务端
                    channelFuture.channel().writeAndFlush(data);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
