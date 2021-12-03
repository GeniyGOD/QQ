package com.geniy.server.service;

import com.geniy.system.common.Message;

import java.io.ObjectInputStream;
import java.net.Socket;

/**
 * @author Geniy
 * @date 2021/12/3 16:35
 */
public class ServerConnectClientThread extends Thread{

    private Socket socket;
    /**
     * 连接到服务端的userId
     */
    private String userId;

    public ServerConnectClientThread(Socket socket, String userId) {
        this.socket = socket;
        this.userId = userId;
    }

    @Override
    public void run() {
        // 发送或接受消息
        while (true) {
            try {
                ObjectInputStream ois = new ObjectInputStream(socket.getInputStream());
                Message message = (Message) ois.readObject();




            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}
