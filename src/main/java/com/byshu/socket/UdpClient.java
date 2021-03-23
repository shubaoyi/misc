package com.byshu.socket;

import java.io.IOException;
import java.net.*;

/**
 * @author byshu
 * @desc
 */
public class UdpClient {
    private String sendStr = "hello";
    private String netAddress = "127.0.0.1";
    private final int PORT = 5060;

    private DatagramSocket datagramSocket;
    private DatagramPacket datagramPacket;

    public UdpClient() {
        try {
            datagramSocket = new DatagramSocket(9090);
            byte[] buf = sendStr.getBytes();
            InetAddress address = InetAddress.getByName(netAddress);
            datagramPacket = new DatagramPacket(buf, buf.length, address, PORT);
            datagramSocket.send(datagramPacket);

            byte[] receBuf = new byte[1024];
            DatagramPacket recePacket = new DatagramPacket(receBuf, receBuf.length);
            datagramSocket.receive(recePacket);

            String receStr = new String(recePacket.getData(), 0, recePacket.getLength());
            System.out.println(receStr);
            String serverIp = recePacket.getAddress() + " : " + recePacket.getPort();
            System.err.println(serverIp);
            String sIp = recePacket.getSocketAddress().toString();
            System.err.println("sIp " + sIp);
        } catch (SocketException e) {
            e.printStackTrace();
        } catch (UnknownHostException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            // 关闭socket
            if (datagramSocket != null) {
                datagramSocket.close();
            }
        }
    }

    public static void main(String[] args) {
        for (int i = 0; i < 1; i++) {
            new Thread(new Runnable() {
                @Override
                public void run() {
                    UdpClient udpClient = new UdpClient();
                }
            }).start();
        }
    }
}
