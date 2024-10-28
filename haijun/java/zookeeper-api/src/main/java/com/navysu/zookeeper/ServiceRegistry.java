package com.navysu.zookeeper;

import java.util.Collections;
import java.util.List;

import org.apache.zookeeper.CreateMode;
import org.apache.zookeeper.KeeperException;
import org.apache.zookeeper.WatchedEvent;
import org.apache.zookeeper.Watcher;
import org.apache.zookeeper.ZooDefs;
import org.apache.zookeeper.ZooKeeper;
import org.apache.zookeeper.data.Stat;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class ServiceRegistry implements Watcher {

    public static final String REGISTRY_ZNODE = "/service_registry";

    private final ZooKeeper zooKeeper;

    private String currentZnode = null;

    private List<String> allServiceAddress = null;

    public ServiceRegistry(ZooKeeper zooKeeper) {
        this.zooKeeper = zooKeeper;
        createServiceRegistryZnode();
    }

    public void registerToCluster(String serviceAddress) {

        try {
            if (currentZnode == null) {
                String znode = REGISTRY_ZNODE + "/n_";
                if (zooKeeper.exists(znode, false) == null) {
                    this.currentZnode = zooKeeper.create(znode, serviceAddress.getBytes(), ZooDefs.Ids.OPEN_ACL_UNSAFE,
                            CreateMode.EPHEMERAL_SEQUENTIAL);
                    log.info("Registered to cluster: {}", this.currentZnode);
                }
            }
        } catch (KeeperException | InterruptedException e) {
            log.error("Error registering to cluster", e);
        }
    }

    private void createServiceRegistryZnode() {

        try {
            if (zooKeeper.exists(REGISTRY_ZNODE, false) == null) {
                zooKeeper.create(REGISTRY_ZNODE, new byte[] {}, ZooDefs.Ids.OPEN_ACL_UNSAFE, CreateMode.PERSISTENT);
            }
        } catch (KeeperException | InterruptedException e) {
            log.error("Error checking/creating service registry znode", e);
        }
    }

    private synchronized void updateAddresses() throws KeeperException, InterruptedException {
        List<String> workerZnodes = zooKeeper.getChildren(REGISTRY_ZNODE, false);
        List<String> workerAddresses = workerZnodes.stream().map(znode -> {
            String workerZnodeFullPath = REGISTRY_ZNODE + "/" + znode;
            try {
                Stat stat = zooKeeper.exists(workerZnodeFullPath, false);
                if (stat != null) {
                    byte[] data = zooKeeper.getData(workerZnodeFullPath, false, stat);
                    return new String(data);
                } else {
                    return null;
                }
            } catch (KeeperException | InterruptedException e) {
                log.error("Error updating service addresses", e);
                return null;
            }
        }).filter(address -> address != null).toList();
        this.allServiceAddress = Collections.unmodifiableList(workerAddresses);
        log.info("Updated service addresses: {}", this.allServiceAddress);
    }

    public void registerForUpdates() {
        try {
            updateAddresses();
        } catch (KeeperException | InterruptedException e) {
            log.error("Error registering for updates", e);
        }
    }

    public void deregisterFromCluster() {
        try {
            if (this.currentZnode != null && zooKeeper.exists(this.currentZnode, false) != null) {
                zooKeeper.delete(this.currentZnode, -1);
                this.currentZnode = null;
                log.info("Deregistered from cluster");
            }
        } catch (KeeperException | InterruptedException e) {
            log.error("Error deregistering from cluster", e);
        }
    }

    public synchronized List<String> getAllServiceAddress() {
        if (allServiceAddress == null) {
            try {
                updateAddresses();
            } catch (KeeperException | InterruptedException e) {
                log.error("Error updating service addresses", e);
            }
        }
        return this.allServiceAddress;
    }

    @Override
    public void process(WatchedEvent event) {
        try {
            updateAddresses();
        } catch (KeeperException | InterruptedException e) {
            log.error("Error updating service addresses", e);
        }
    }

}
