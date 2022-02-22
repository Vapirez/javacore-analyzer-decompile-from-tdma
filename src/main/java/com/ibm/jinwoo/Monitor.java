//// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
//// Jad home page: http://www.kpdus.com/jad.html
//// Decompiler options: packimports(3)
//// Source File Name:   Monitor.java
//
//package com.ibm.jinwoo;
//
//import java.util.Vector;
//
//public class Monitor
//{
//
//    public Monitor()
//    {
//    }
//
//    public boolean isLeaf()
//    {
//        if(children == null)
//            return true;
//        return children.size() == 0;
//    }
//
//    public boolean isPop()
//    {
//        return isPop;
//    }
//
//    public String toString()
//    {
//        return (new StringBuilder(String.valueOf(threadName))).append("(").append(objectName).append(")").append(owner).toString();
//    }
//
//    public boolean dup;
//    public boolean visited;
//    public boolean isPop;
//    public int owner;
//    public String objectName;
//    public String threadName;
//    public Vector children;
//    public boolean isHeapLock;
//    public boolean waitingHeapLock;
//    boolean isDeadlock;
//}
