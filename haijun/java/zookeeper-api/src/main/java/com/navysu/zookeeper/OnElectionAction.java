package com.navysu.zookeeper;

import java.net.InetAddress;
import java.net.UnknownHostException;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class OnElectionAction implements OnElectionCallback {

    private final ServiceRegistry serviceRegistry;
    private final int port;

    public OnElectionAction(ServiceRegistry serviceRegistry, int port) {
        this.serviceRegistry = serviceRegistry;
        this.port = port;
    }

    @Override
    public void onElectedToBeLeader() {
        try {
            serviceRegistry.deregisterFromCluster();
            serviceRegistry.registerForUpdates();
        } catch (Exception e) {
            log.error("Error registering to cluster", e);
        }
    }

    @Override
    public void onWorker() {
        try {
            String currentServiceAddress = String.format("https://%s:%d",
                    InetAddress.getLocalHost().getCanonicalHostName(), port);
            serviceRegistry.registerToCluster(currentServiceAddress);
        } catch (UnknownHostException e) {
            log.error("Error registering to cluster", e);
        }
    }

}
