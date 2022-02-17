// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   MonitorDump.java

package com.ibm.jinwoo;

import java.util.List;

public class MonitorDump
{

    public MonitorDump()
    {
    }

    public MonitorDump(String name, long owner)
    {
        objectName = name;
        this.owner = owner;
    }

    public MonitorDump(String name, long owner, boolean heapLock)
    {
        objectName = name;
        this.owner = owner;
        isHeapLock = heapLock;
    }

    public boolean isHeapLock()
    {
        return isHeapLock;
    }

    public boolean waitingHeapLock()
    {
        return waitingHeapLock;
    }

    public String objectName;
    public String threadName;
    public long owner;
    public List waiting;
    public boolean isHeapLock;
    public boolean waitingHeapLock;
}
