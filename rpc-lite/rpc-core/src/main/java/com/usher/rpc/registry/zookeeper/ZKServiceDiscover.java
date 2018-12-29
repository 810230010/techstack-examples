package com.usher.rpc.registry.zookeeper;

import com.usher.rpc.common.Environment;
import com.usher.rpc.registry.AbstractServiceDiscover;
import org.apache.zookeeper.KeeperException;
import org.apache.zookeeper.WatchedEvent;
import org.apache.zookeeper.Watcher;
import org.apache.zookeeper.ZooKeeper;
import org.springframework.util.CollectionUtils;

import java.io.IOException;
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class ZKServiceDiscover extends AbstractServiceDiscover {
    private static Executor threadPool = Executors.newCachedThreadPool();
    public ZKServiceDiscover(String zkAddress){
        super(zkAddress);
        threadPool.execute(new Runnable() {
            @Override
            public void run() {
                while(true){
                    freshService();
                    try {
                        TimeUnit.SECONDS.sleep(10L);
                        System.out.println(">>>>>>>   刷新服务.......");
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        });
    }

    private static ZooKeeper zooKeeper = null;
    private static final Lock lock = new ReentrantLock();

    private ZooKeeper getInstance(){

        if(zooKeeper == null){
            try {
                if(lock.tryLock(2, TimeUnit.SECONDS)){
                    try {
                            zooKeeper = new ZooKeeper(address, 30000, (WatchedEvent event)->{
                            if(event.getState() == Watcher.Event.KeeperState.Expired){
                                try {
                                    zooKeeper.close();
                                    zooKeeper = null;
                                } catch (InterruptedException e) {
                                    e.printStackTrace();
                                }
                            }
                            String path = event.getPath();
                            if(path != null){
                                try {
                                    zooKeeper.exists(path, true);
                                } catch (KeeperException e) {
                                    e.printStackTrace();
                                } catch (InterruptedException e) {
                                    e.printStackTrace();
                                }
                            }
                            if(event.getType() == Watcher.Event.EventType.NodeChildrenChanged){
                                freshService();
                            }
                        });
                    } catch (IOException e) {
                        e.printStackTrace();
                    }finally {
                        lock.unlock();
                    }
                }

            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        return zooKeeper;
    }

    /**
     * /rpc-lite/com.usher.iface.UserService/localhost:2181
     * key: interfaceName   value: Set<String> address
     */
    private Map<String, Set<String>> serviceAddressMap = new ConcurrentHashMap<>();
     //刷新服务
    private void freshService(){
        String serviceParentPath = Environment.RPC_ZK_PATH;
        Map<String, Set<String>> serviceAddressTemp = new ConcurrentHashMap<>();
        try {
            List<String> interfaceNames = getInstance().getChildren(serviceParentPath, true);
            if(CollectionUtils.isEmpty(interfaceNames)){
                for(String interfaceName : interfaceNames){
                    String servicePath = serviceParentPath.concat("/").concat(interfaceName);
                    List<String> serviceAddress = getInstance().getChildren(servicePath, true);
                    Set addressSet = new LinkedHashSet(serviceAddress);
                    serviceAddressTemp.put(interfaceName, addressSet);
                }
            }
            serviceAddressMap = serviceAddressTemp;
        } catch (KeeperException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    @Override
    public String getService(String interfaceName) {
        freshService();
        Set<String> address = serviceAddressMap.get(interfaceName);
        if(address.size() == 1){
            return (String)address.toArray()[0];
        }else{
            return (String) address.toArray()[new Random().nextInt(address.size())];
        }
    }
}
