package com.byshu.rabbitmq;

import com.rabbitmq.client.*;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

public class Producer {

    public static void main(String[] args) throws IOException, TimeoutException, InterruptedException {
        //创建连接工厂
        ConnectionFactory factory = new ConnectionFactory();
        factory.setUsername("admin");
        factory.setPassword("admin");

        //设置 RabbitMQ 地址
        factory.setHost("localhost");
        //建立到代理服务器到连接
        Connection conn = factory.newConnection();
        //获得信道
        Channel channel = conn.createChannel();
        //声明交换器
        String exchangeName = "hello-exchange";
        channel.exchangeDeclare(exchangeName, "direct", true);

        String routingKey = "hola";

        // 设置消息持久化
        AMQP.BasicProperties.Builder properties = new AMQP.BasicProperties().builder();
        properties.deliveryMode(2);  // 设置消息是否持久化，1： 非持久化 2：持久化
        AMQP.BasicProperties basicProperties = properties.build();
        //发布消息
        byte[] messageBodyBytes = "no ack".getBytes();


        // 设置channel的confirm模式
        channel.confirmSelect();
        channel.addConfirmListener(new ConfirmListener() {
            @Override
            public void handleAck(long deliveryTag, boolean multiple) throws IOException {
                System.err.println("handleAck : " + deliveryTag + ", " + multiple);
            }

            @Override
            public void handleNack(long deliveryTag, boolean multiple) throws IOException {
                System.err.println("handleNack : " + deliveryTag + ", " + multiple);

            }
        });
        for (int i = 0; i < 10; i++) {
            channel.basicPublish(exchangeName, routingKey, basicProperties, messageBodyBytes);
        }

        channel.waitForConfirms();
        channel.close();
        conn.close();
    }

}
