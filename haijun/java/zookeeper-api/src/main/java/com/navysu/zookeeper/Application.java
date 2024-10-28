package com.navysu.zookeeper;

import java.io.IOException;
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
public class Application implements Watcher {

    private static final String ZOOKEEPER_CONNECT = "127.0.0.1:2181";
    private static final String ELECTION_NAMESPACE = "/election";
    private static final int ZOOKEEPER_SESSION_TIMEOUT = 3000;
    private static final int DEFAULT_PORT = 8080;

    private ZooKeeper zooKeeper;
    private String currentZnodeName;
    private OnElectionAction onElectionCallback;

    public static void main(String[] args) {
        log.info("start Application");
        int currentServerPort = args.length > 0 ? Integer.parseInt(args[0]) : DEFAULT_PORT;
        Application application = new Application();
        try {
            application.connectToZooKeeper();
            ServiceRegistry serviceRegistry = new ServiceRegistry(application.zooKeeper);
            application.setOnElectionCallback(new OnElectionAction(serviceRegistry, currentServerPort));
            application.volunteerForElection();
            application.reelectLeader();
            application.run();
            application.close();
            log.info("end Application");
        } catch (IOException | InterruptedException | KeeperException e) {
            log.error("Error connecting to ZooKeeper", e);
        }

    }

    public void volunteerForElection() throws KeeperException, InterruptedException {
        String znodePrefix = ELECTION_NAMESPACE + "/c_";
        String zondeFullPath = this.zooKeeper.create(ELECTION_NAMESPACE, new byte[] {}, ZooDefs.Ids.OPEN_ACL_UNSAFE,
                CreateMode.EPHEMERAL_SEQUENTIAL);
        log.info("Created election znode: {}", zondeFullPath);
        this.currentZnodeName = zondeFullPath.replace(znodePrefix, "");
    }

    public void reelectLeader() throws KeeperException, InterruptedException {
        Stat precessorStat = null;
        String precessorZondeName = "";
        while (precessorStat == null) {
            List<String> children = this.zooKeeper.getChildren(ELECTION_NAMESPACE, false);
            Collections.sort(children);
            String smallestChild = children.get(0);

            if (smallestChild.equals(this.currentZnodeName)) {
                // first child is the leader.
                log.info("I am the leader");
                onElectionCallback.onElectedToBeLeader();
                return;
            }

            log.info("I am not the leader, {} is the leader", smallestChild);
            int precessorStatIndex = Collections.binarySearch(children, this.currentZnodeName) - 1;
            precessorZondeName = children.get(precessorStatIndex);
            precessorStat = this.zooKeeper.exists(ELECTION_NAMESPACE + "/" + precessorZondeName, false);
        }

        onElectionCallback.onWorker();
        log.info("I am the worker. Watching znode: {}", precessorZondeName);

    }

    public ZooKeeper connectToZooKeeper() throws IOException {
        this.zooKeeper = new ZooKeeper(ZOOKEEPER_CONNECT, ZOOKEEPER_SESSION_TIMEOUT, this);
        return this.zooKeeper;
    }

    private void run() throws InterruptedException {
        synchronized (this.zooKeeper) {
            this.zooKeeper.wait();
        }
    }

    private void close() throws InterruptedException {
        this.zooKeeper.close();
    }

    public void watchTargetZnode() throws KeeperException, InterruptedException {
        String service_zone = ServiceRegistry.REGISTRY_ZNODE;

        Stat stat = this.zooKeeper.exists(service_zone, false);
        if (stat == null) {
            return;
        }
        byte[] data = this.zooKeeper.getData(service_zone, false, stat);
        List<String> children = this.zooKeeper.getChildren(service_zone, false);
        log.info("Current target znode: {}, children: {}", new String(data), children);
    }

    @Override
    public void process(WatchedEvent event) {
        switch (event.getType()) {
            case None:
                if (event.getState() == Event.KeeperState.SyncConnected) {
                    log.info("Connected to ZooKeeper");
                } else {
                    synchronized (this.zooKeeper) {
                        log.info("Disconnected from ZooKeeper");
                        this.zooKeeper.notifyAll();
                    }
                }

                break;

            case NodeChildrenChanged:
                log.info("NodeChildrenChanged: {}", event.getPath());
                break;

            case NodeCreated:
                log.info("NodeCreated: {}", event.getPath());
                break;

            case NodeDeleted:
                log.info("NodeDeleted: {}", event.getPath());
                if (event.getPath().startsWith(ELECTION_NAMESPACE)) {
                    try {
                        reelectLeader();
                    } catch (KeeperException | InterruptedException e) {
                        log.error("Error reelecting leader", e);
                    }

                }
                break;

            case NodeDataChanged:
                log.info("NodeDataChanged: {}", event.getPath());
                break;

            default:
                log.info("Default???: {}", event.getPath());
                break;
        }

        try {
            watchTargetZnode();
        } catch (KeeperException | InterruptedException e) {
            log.error("Error watching target znode", e);
        }
    }

    public void setOnElectionCallback(OnElectionAction onElectionAction) {
        this.onElectionCallback = onElectionAction;
    }
}
