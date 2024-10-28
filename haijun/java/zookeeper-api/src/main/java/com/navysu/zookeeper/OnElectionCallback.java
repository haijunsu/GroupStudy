package com.navysu.zookeeper;

public interface OnElectionCallback {

    void onElectedToBeLeader();

    void onWorker();
}
