package com.navysu.java.basic.algorithm;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

/**
 * https://leetcode.com/discuss/interview-question/system-design/431023/Google-or-Onsite-or-Get-all-logs-between-times
 *
 * Example:
 *
 *
 * ServiceManager m = new ServiceManager();
 * m.addService("A");
 * m.addService("B");
 * m.addServiceCall("A", 1, "abc");
 * m.addServiceCall("A", 5, "abec");
 * m.addServiceCall("B", 3, "ac");
 * m.getAllServiceCallsBetweenTimes(1, 4); // ["abc", "ac"]
 */
public class FindAllServiceInRange {

    public static void main(String[] args) {
        ServiceManager m = new ServiceManager();
        m.addService("A");
        m.addService("B");
        m.addServiceCall("A", 1, "abc");
        m.addServiceCall("A", 5, "abec");
        m.addServiceCall("B", 3, "ac");
        System.out.println(m.getAllServiceCallsBetweenTimes(1, 4)); // ["abc", "ac"]
    }
}

class ServiceManager {

    private Map<String, TreeMap<Long, String>> serviceCalls = new HashMap<>();
    private TreeMap<Long, String> serviceCallTimes = new TreeMap<>();

    public void addService(String service) {
        if (serviceCalls.containsKey(service))
            return;
        serviceCalls.put(service, new TreeMap<>());
    }

    public void addServiceCall(String service, long time, String payload) {
        serviceCalls.getOrDefault(service, new TreeMap<>()).put(time, payload);
    }

    public List<String> getAllServiceCallsBetweenTimes(long time1, long time2) {
        List<String> payloads = new ArrayList<>();
        for (Map.Entry<String, TreeMap<Long, String>> entry : serviceCalls.entrySet()) {
            System.out.println("Service: " + entry.getKey());
            for (Map.Entry<Long, String> call : entry.getValue().subMap(time1, time2).entrySet()) {
                System.out.println(call);
                payloads.add(call.getValue());
            }
        }
        return payloads;
    }
}