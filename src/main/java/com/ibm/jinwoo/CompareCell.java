// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   CompareCell.java

package com.ibm.jinwoo;


// Referenced classes of package com.ibm.jinwoo.thread:
//            ThreadDump

public class CompareCell
{

    public CompareCell()
    {
    }

    public CompareCell(ThreadDump td[], int idx, int thread, Long ti)
    {
        threadDump = td;
        index = idx;
        threadIndex = thread;
        tid = ti;
    }

    public String toString()
    {
        return threadDump[index].getCurrentMethod(threadIndex);
    }

    ThreadDump threadDump[];
    int index;
    int threadIndex;
    Long tid;
}
