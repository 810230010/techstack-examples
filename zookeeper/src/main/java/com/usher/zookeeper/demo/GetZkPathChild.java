package com.usher.zookeeper.demo;

import org.apache.zookeeper.CreateMode;
import org.apache.zookeeper.KeeperException;
import org.apache.zookeeper.ZooKeeper;
import org.apache.zookeeper.data.Stat;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class GetZkPathChild {
    private static ZooKeeper zooKeeper = null;
    public static final String hostPort = "localhost:2181";
    public static final String parentPath = "/";
    static {
        try {
            zooKeeper = new ZooKeeper(hostPort, 2000, null);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public static List<String> getChildPath(){
        List<String> result = new ArrayList<>();
        if(zooKeeper != null){
            try {
                result = zooKeeper.getChildren(parentPath, Boolean.FALSE);
            } catch (KeeperException e) {
                e.printStackTrace();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        return result;
    }
    public static void main(String[] args) {
        System.out.println(getChildPath());
    }

}
