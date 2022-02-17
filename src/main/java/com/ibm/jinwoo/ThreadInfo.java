// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   ThreadInfo.java

package com.ibm.jinwoo;

import java.io.File;
import java.io.Serializable;
import java.util.ArrayList;

public class ThreadInfo
    implements Serializable
{

    public ThreadInfo()
    {
        threadDumps = new ArrayList();
    }

    public String toString()
    {
        return super.toString();
    }

    File file;
    ArrayList threadDumps;
    long timestamp[];
    long freed[];
    long free[];
    long total[];
    long mark[];
    long sweep[];
    long compact[];
    long af[];
    long since[];
    long completed[];
    long gccompleted[];
    int ngc[];
    int naf[];
    int numberOfSet;
    int outOfHeapSpace[];
}
