package com.geniy.client.service;

import com.geniy.system.common.Message;
import com.geniy.system.common.MessageType;
import com.geniy.system.common.User;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.InetAddress;
import java.net.Socket;

/**
 * @author Geniy
 * @date 2021/12/3 15:40
 * 用户登录验证和用户注册等
 */
public class UserClientService {

    /**
     * 其他地方会用到user和socket信息，因此做成成员数学
     */
    private User user = new User();
    private Socket socket;

    /**
     * 用户登录
     */
    public boolean login(String userId, String password) {

        boolean isLoginSuccess = false;
        user.setUserId(userId);
        user.setPassword(password);
        // 连接到服务器，发送User对象
        try {
            socket = new Socket(InetAddress.getByName("127.0.0.1"), 9999);
            // 得到输出流
            ObjectOutputStream oos = new ObjectOutputStream(socket.getOutputStream());
            // 发送
            oos.writeObject(user);

            // 读取从无服务器回复的Message对象
            ObjectInputStream ois = new ObjectInputStream(socket.getInputStream());
            Message message = (Message) ois.readObject();

            // 判断账号登录情况
            if (message.getMessageType().equals(MessageType.MESSAGE_LOGIN_SUCCEED)) {
                // 登录成功
                // 创建一个和服务器保持通信的线程
                ClientConnectServerThread thread = new ClientConnectServerThread(socket);
                thread.start();
                ManageClientConnectServerThread.addClientConnectServerThread(userId, thread);

                isLoginSuccess = true;
            } else {
                // 登录失败
                // 不能创建和服务器通信的线程，关闭isocket
                socket.close();
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return isLoginSuccess;
    }










}
