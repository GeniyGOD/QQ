package com.geniy.client.view;

import com.geniy.client.service.UserClientService;
import com.geniy.system.utils.Utility;

/**
 * @author Geniy
 * @date 2021/12/3 14:56
 * 客户端的菜单界面
 */
@SuppressWarnings({"all"})
public class ClientView {

    /**
     * 控制是否显示菜单
     */
    private boolean loop = true;

    /**
     * 接收用户的键盘输入
     */
    private String key = "";

    /**
     * 用于登录服务器等
     */
    private UserClientService userClientService = new UserClientService();

    public static void main(String[] args) {
        new ClientView().clientMainMenu();
    }

    private void clientMainMenu() {
        while (loop) {
            System.out.println("=====欢迎登录网络通信系统=====");
            System.out.println("\t\t 1 登录系统");
            System.out.println("\t\t 9 退出系统");
            System.out.println("请输入你的选择：");
            key = Utility.readString(1);

            //根据用户的输入，处理不同的逻辑
            switch (key) {
                case "1":
                    System.out.println("登录系统");
                    System.out.println("请输入你的账号：");
                    String userId = Utility.readString(50);
                    System.out.println("请输入你的密码：");
                    String password = Utility.readString(50);

                    // 得到账号密码后去验证合法性
                    if (userClientService.login(userId, password)) {
                        // 账号登录成功
                        System.out.println("=====欢迎（用户" + userId + "）=====");

                        // 进入二级菜单
                        while (loop) {
                            System.out.println("=====网络通信系统二级菜单（用户" + userId + "）=====");
                            System.out.println("\t\t 1 显示在线用户列表");
                            System.out.println("\t\t 2 群发消息");
                            System.out.println("\t\t 3 私发消息");
                            System.out.println("\t\t 4 发送文件");
                            System.out.println("\t\t 9 退出系统");
                            System.out.println("请输入你的选择：");
                            key = Utility.readString(1);
                            //根据用户的输入，处理不同的逻辑
                            switch (key) {
                                case "1":
                                    System.out.println("显示在线用户列表");
                                    break;
                                case "2":
                                    System.out.println("群发消息");
                                    break;
                                case "3":
                                    System.out.println("私发消息");
                                    break;
                                case "4":
                                    System.out.println("发送文件");
                                    break;
                                case "9":
                                    loop = false;
                                    break;
                            }
                        }
                    } else {
                        // 账号登录失败
                        System.out.println("登录失败...");
                    }
                    break;
                case "9":
                    loop = false;
                    break;
            }
        }
    }

}
