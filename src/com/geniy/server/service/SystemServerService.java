package com.geniy.server.service;

import com.geniy.system.common.Message;
import com.geniy.system.common.MessageType;
import com.geniy.system.common.User;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * @author Geniy
 * @date 2021/12/3 16:18
 * 服务器，监听9999，等待客服端链接，并保持通信
 */
public class SystemServerService {

    private ServerSocket serverSocket = null;

    public SystemServerService() {

        try {
            System.out.println("服务器在9999端口监听...");
            serverSocket = new ServerSocket(9999);

            while (true) {
                // 当和摸个客户端连接后，会继续监听
                Socket socket = serverSocket.accept();
                ObjectInputStream ois = new ObjectInputStream(socket.getInputStream());
                ObjectOutputStream oos = new ObjectOutputStream(socket.getOutputStream());
                User user = (User) ois.readObject();
                Message message = new Message();
                // 检验账号密码合法性
                if("100".equals(user.getUserId()) && "123456".equals(user.getPassword())) {
                    // 账号正确
                    message.setMessageType(MessageType.MESSAGE_LOGIN_SUCCEED);
                    oos.writeObject(message);
                    ServerConnectClientThread thread = new ServerConnectClientThread(socket, user.getUserId());
                    thread.start();
                    ManageServerConnectClientThread.addServerConnectClientThread(user.getUserId(), thread);

                } else {
                    // 账号错误
                    message.setMessageType(MessageType.MESSAGE_LOGIN_FAIL);
                    oos.writeObject(message);
                    socket.close();
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                serverSocket.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
