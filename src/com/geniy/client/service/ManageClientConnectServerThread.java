package com.geniy.client.service;

import java.util.HashMap;

/**
 * @author Geniy
 * @date 2021/12/3 16:05
 */
public class ManageClientConnectServerThread {

    /**
     * <userId-线程>
     */
    private static HashMap<String, ClientConnectServerThread> hm = new HashMap<>();

    public static void addClientConnectServerThread(String userId, ClientConnectServerThread thread) {
        hm.put(userId, thread);
    }

    public static ClientConnectServerThread getClientConnectServerThread(String userId) {
        return hm.get(userId);
    }

}
