package com.navysu.java.basic.algorithm;

/*
 *  Cracking the Coding Interview 3.4
 *
 * Towers of Hanoi
 */

import java.util.*;
public class TowersHanoi {
    public static void main(String args[]) {
       int n = 5;
       Tower[] towers = new Tower[3];
       for (int i = 0; i < 3; ++i) towers[i] = new Tower(i);
       for (int i = n - 1; i >=0; --i) towers[0].add(i);
       towers[0].moveDisks(n, towers[2], towers[1]);
    }
}

class Tower {
    private Stack<Integer> disks;
    private int index;
    public Tower(int i) {
        this.index = i;
        this.disks = new Stack<Integer>();
    }

    public int index() {
        return index;
    }

    public void add(int d) {
        if (!disks.isEmpty() && disks.peek() <= d) {
            System.out.println("Error placing disk " + d);
        } else {
            disks.push(d);
        }
    }

    public void moveTopTo(Tower t) {
        int top = disks.pop();
        t.add(top);
        System.out.println("Move disk " + top + " from " + index() + " to " + t.index());
    }

    public void print() {
        System.out.println("Contents of Tower " + index());
        for (int i = disks.size() - 1; i >= 0; --i) {
            System.out.println( "    " + disks.get(i));
        }
    }

    public void moveDisks(int n, Tower destination, Tower buffer) {
        if (n > 0) {
            moveDisks(n - 1, buffer, destination);
            moveTopTo(destination);
            buffer.moveDisks(n - 1, destination, this);
        }
    }

}
