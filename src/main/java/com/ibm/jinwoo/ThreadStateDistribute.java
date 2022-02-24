package com.ibm.jinwoo;

public class ThreadStateDistribute {
    long total;
    long runnable;
    long condition;
    long monitor;
    long suspended;
    long wait;
    long blocked;
    long deadlock;
    long parked;

    public ThreadStateDistribute(ThreadDump td) {
        this.total = td.getTotalThread();
        this.runnable = td.getRunnable();
        this.condition = td.getWCondition();
        this.monitor = td.getWMonitor();
        this.suspended = td.getSuspended();
        this.wait = td.getOWait();
        this.blocked = td.getBlocked();
        this.deadlock = td.getDeadlock();
        this.parked = td.getParked();
    }
}
