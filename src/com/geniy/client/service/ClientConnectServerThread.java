package com.geniy.client.service;

import com.geniy.system.common.Message;

import java.io.ObjectInputStream;
import java.net.Socket;

/**
 * @author Geniy
 * @date 2021/12/3 15:53
 */
public class ClientConnectServerThread extends Thread{

    /**
     * 该线程需要持有一个socket对象
     */
    private Socket socket;

    /**
     * 构造器接收一个socket对象
     */
    public ClientConnectServerThread(Socket socket) {
        this.socket = socket;
    }

    @Override
    public void run() {

        while (true) {
            try {
                System.out.println("客户端线程，等待读取从服务器发送的消息");
                ObjectInputStream ois = new ObjectInputStream(socket.getInputStream());
                // 如果服务器没有发送Message对象，线程会阻塞在这
                Message message = (Message) ois.readObject();




            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    public Socket getSocket() {
        return socket;
    }
}
