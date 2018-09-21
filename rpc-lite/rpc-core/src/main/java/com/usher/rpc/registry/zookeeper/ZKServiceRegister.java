package com.usher.rpc.registry.zookeeper;

import com.usher.rpc.common.Environment;
import com.usher.rpc.registry.IServiceRegister;
import lombok.Data;
import org.apache.zookeeper.*;
import org.apache.zookeeper.data.Stat;

import java.io.IOException;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.text.MessageFormat;
import java.util.Set;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.ReentrantLock;

public class ZKServiceRegister extends IServiceRegister {
    private static ZooKeeper zooKeeperInstance;
    private static ReentrantLock reentrantLock = new ReentrantLock(true);
    public ZKServiceRegister(String zkAddress, int servicePort){
        super(zkAddress, servicePort);
    }
    public ZooKeeper getInstance(){
        if(zooKeeperInstance == null){
            try {
                if(reentrantLock.tryLock(3, TimeUnit.SECONDS)){
                    try {
                        zooKeeperInstance = new ZooKeeper(address, 30000, watchedEvent -> {
                            // session expire, close old and create new
                            if(watchedEvent.getState() == Watcher.Event.KeeperState.Expired){
                                try {
                                    zooKeeperInstance.close();
                                    zooKeeperInstance = null;
                                } catch (InterruptedException e) {
                                    e.printStackTrace();
                                }
                            }
                            // add One-time trigger, ZooKeeper的Watcher是一次性的，用过了需要再注册
                            String zpath = watchedEvent.getPath();
                            try {
                                zooKeeperInstance.exists(zpath, true);
                            } catch (KeeperException e) {
                                e.printStackTrace();
                            } catch (InterruptedException e) {
                                e.printStackTrace();
                            }
                        });
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }finally {
                reentrantLock.unlock();
            }
        }
        if (zooKeeperInstance == null) {
            throw new NullPointerException(">>>>>>>>>>>zookeeper connect fail.");
        }
        return zooKeeperInstance;
    }

    //注册格式 /rpc-lite/interface/address
    @Override
    public void registerService(Set<String> services) {
        String localAddress = null;
        try {
            localAddress = InetAddress.getLocalHost().getHostAddress();
        } catch (UnknownHostException e) {
            e.printStackTrace();
        }
        String serviceAdress = localAddress.concat(":").concat(port + "");
        try {
            //rpc-lite 服务注册根路径
            Stat stat = getInstance().exists(Environment.RPC_ZK_PATH, true);
            if(stat == null){
                getInstance().create(Environment.RPC_ZK_PATH, new byte[]{}, ZooDefs.Ids.OPEN_ACL_UNSAFE, CreateMode.PERSISTENT);
            }
            for(String serviceName : services){
                String servicePath = MessageFormat.format("{0}/{1}", Environment.RPC_ZK_PATH, serviceName);
                String serviceAddress = MessageFormat.format("{0}/{1}/{2}", Environment.RPC_ZK_PATH, serviceName, serviceAdress);

                Stat servicePathStat = getInstance().exists(servicePath, true);
                if(servicePathStat == null){
                    getInstance().create(servicePath, new byte[]{}, ZooDefs.Ids.OPEN_ACL_UNSAFE, CreateMode.PERSISTENT);
                }
                Stat serviceAddressStat = getInstance().exists(serviceAddress, true);
                if(serviceAddressStat != null){
                    getInstance().delete(serviceAddress, -1);
                }
                getInstance().create(serviceAddress, serviceAddress.getBytes(), ZooDefs.Ids.OPEN_ACL_UNSAFE, CreateMode.EPHEMERAL);
            }
        } catch (KeeperException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public void stop(){
        try {
            zooKeeperInstance.close();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
