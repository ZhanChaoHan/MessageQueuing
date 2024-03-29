package com.jachs.rabbitmq_se_provider;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;
 
import java.io.IOException;
import java.util.concurrent.TimeoutException;

import org.junit.Test;

/**
 * @author zhanchaohan
 * 
 */
public class Demo1 {
    
    @Test
    public void t1() {
        ConnectionFactory factory=new ConnectionFactory();
        factory.setHost("192.168.2.150");
        factory.setPort(5672);
        factory.setUsername("jachs");
        factory.setPassword("123456");
        
        factory.setVirtualHost ( "jachsHost" );
        //定义连接
        Connection connection=null;
        //定义通道
        Channel channel=null;
        try {
            connection=factory.newConnection();//获取连接
            System.out.println ( connection.isOpen () );
            
            channel=connection.createChannel();//获取通道
            /**
             * 声明一个队列
             * 参数1：为队列名取任意值
             * 参数2：是否为持久化队列
             * 参数3：是否排外，如果排外则这个队列只允许一个消费者监听
             * 参数4：是否自动删除，如果为true则表示当前队列中没有消息，也没有消费者连接时就会自动删除这个队列。
             * 参数5：为队列的一些属性设置通常为null即可
             */
            channel.queueDeclare("myQueue",true,false,false,null);
            //定义消息
            String message="我的RabbitMQ的测试消息";
            /**
             * 发送消息
             * 参数1：为交换机名称，这里为空字符串表示不使用交换机
             * 参数2：为队列名或RoutingKey,当指定了交换机名称以后这个值就是RoutingKey
             * 参数3：为消息的属性信息，通常为空即可
             * 参数4：为具体的消息数据的字节数组
             */
            channel.basicPublish("","myQueue",null,message.getBytes("utf-8"));
            System.out.println("消息发送成功！");
        } catch (IOException e) {
            e.printStackTrace();
        } catch (TimeoutException e) {
            e.printStackTrace();
        }finally {
            if (channel!=null){
                try {
                    channel.close();
                } catch (IOException e) {
                    e.printStackTrace();
                } catch (TimeoutException e) {
                    e.printStackTrace();
                }
            }
            if (connection!=null){
                try {
                    connection.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
