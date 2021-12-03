package com.geniy.server.service;

import java.util.HashMap;

/**
 * @author Geniy
 * @date 2021/12/3 16:35
 */
public class ManageServerConnectClientThread {

    /**
     * <userId-线程>
     */
    private static HashMap<String, ServerConnectClientThread> hm = new HashMap<>();

    public static void addServerConnectClientThread(String userId, ServerConnectClientThread thread) {
        hm.put(userId, thread);
    }

    public static ServerConnectClientThread getServerConnectClientThread(String userId) {
        return hm.get(userId);
    }
}
