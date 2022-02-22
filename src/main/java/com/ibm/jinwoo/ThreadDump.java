// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   ThreadDump.java

package com.ibm.jinwoo;

import sun.tools.jar.CommandLine;

import javax.swing.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Hashtable;
import java.util.List;

// Referenced classes of package com.ibm.jinwoo.thread:
//            Arrays2, MonitorDump

public class ThreadDump {

    public ThreadDump() {
        nativeMemoryTree = null;
        homeDir = null;
        dllDir = null;
        sysCP = null;
        osLevel = null;
        hypervisor = null;
        architecture = null;
        numberOfCPU = null;
        userArgs = "";
        gcHistory = null;
        threadHash = null;
        threadHash2 = null;
    }

    public String getAggregationSummary() {
        long emptyMethod = 0L;
        String summary = "";
        List methodList = new ArrayList();
        for (int i = 0; i < name.length; i++) {
            String methodName = getName(i);
            if (methodName != null)
                if (methodName.length() != 0) {
                    int result = getCommonThreadName(methodName);
                    if (result >= 0 && !methodList.contains(AGGREGATION[result]))
                        methodList.add(AGGREGATION[result]);
                } else if (methodName.length() == 0)
                    emptyMethod++;
        }

        String methodArray[] = (String[]) methodList.toArray(new String[methodList.size()]);
        sortedMethod = new long[2][methodArray.length];
        methodList.clear();
        for (int i = 0; i < methodArray.length; i++) {
            sortedMethod[0][i] = 0L;
            sortedMethod[1][i] = i;
        }

        int j = 0;
        int value[] = new int[AGGREGATION.length];
        for (int i = 0; i < value.length; i++)
            value[i] = 0;

        for (int i = 0; i < name.length; i++) {
            int result = getCommonThreadName(getName(i));
            if (result >= 0) {
                for (int k = 0; k < methodArray.length; k++)
                    if (methodArray[k].compareTo(AGGREGATION[result]) == 0)
                        sortedMethod[0][k]++;

            }
        }

        Arrays2.sort(sortedMethod);
        summary = (new StringBuilder("<UL><LI>Thread Aggregation Analysis<BR><table border=\"1\"><tr><th>Thread Type</th><th>Number of Threads : ")).append(getTotalThread()).append("</th><th>Percentage</th></tr>").toString();
        for (int i = methodArray.length - 1; i >= 0; i--)
            summary = (new StringBuilder(String.valueOf(summary))).append("<tr><td>").append(methodArray[(int) sortedMethod[1][i]]).append("</td><td align=\"right\">").append(sortedMethod[0][i]).append("</td><td align=\"right\">").append(Math.round(((float) sortedMethod[0][i] * 100F) / (float) name.length)).append(" (%)</td></tr>").toString();

        if (emptyMethod != 0L)
            summary = (new StringBuilder(String.valueOf(summary))).append("<tr><td>Unknown method : ").append(emptyMethod).append("<tr><td align=\"right\">").append(Math.round(((float) emptyMethod * 100F) / (float) name.length)).append(" (%))</td></tr>").toString();
        else if (summary.length() != 0)
            summary = (new StringBuilder(String.valueOf(summary))).append("<BR>").toString();
        return (new StringBuilder(String.valueOf(summary))).append("</table></UL>").toString();
    }

    public long getBlocked() {
        long count = 0L;
        for (int i = 0; i < state.length; i++)
            if (state[i] == 5)
                count++;

        return count;
    }

    public int getCommonThreadName(String key) {
        if (key == null)
            return -1;
        if (key.length() == 0)
            return -1;
        for (int i = 0; i < AGGREGATION.length; i++)
            if (key.indexOf(AGGREGATION[i]) >= 0)
                return i;

        return -1;
    }

    public String getCurrentMethod(int r) {
        if (javaStack[r] == null || javaStack[r].length() == 0)
            return "NO JAVA STACK";
        if (pattern[r] != null)
            return pattern[r];
        if (macro[r] == 1)
            return "IDLE";
        if (macro[r] == 2)
            return "KEEP-ALIVE";
        if (macro[r] == 3)
            return "LISTEN";
        if (macro[r] == 4)
            return "READ REQUEST";
        int i = javaStack[r].indexOf("<BR>");
        if (i != -1) {
            int j = javaStack[r].indexOf("at ");
            if (j != -1)
                return javaStack[r].substring(j + 3, i);
        }
        return "NO JAVA STACK";
    }

    public long getDeadlock() {
        long count = 0L;
        for (int i = 0; i < isDeadlock.length; i++)
            if (isDeadlock[i])
                count++;

        return count;
    }

    public int getIndexFromName(String threadName) {
        for (int i = 0; i < name.length; i++)
            if (threadName.compareTo(name[i]) == 0)
                return i;

        return -1;
    }

    public int getIndexFromSysThread(long id) {
        int idx = Arrays.binarySearch(sortedArray[0], id);
        if (idx < 0)
            return -1;
        else
            return (int) sortedArray[1][idx];
    }

    public String getJavaStack(int idx) {
        return javaStack[idx];
    }

    public String getMethodSummary() {
        long emptyMethod = 0L;
        String summary = "";
        List methodList = new ArrayList();
        for (int i = 0; i < name.length; i++) {
            String methodName = getSortedCurrentMethod(i);
            if (methodName.length() != 0 && !methodList.contains(methodName))
                methodList.add(methodName);
            else if (methodName.length() == 0)
                emptyMethod++;
        }

        methodArray = (String[]) methodList.toArray(new String[methodList.size()]);
        sortedMethod = new long[2][methodArray.length];
        methodList.clear();
        for (int i = 0; i < methodArray.length; i++) {
            sortedMethod[0][i] = 1L;
            sortedMethod[1][i] = i;
        }

        int j = 0;
        for (int i = 0; i < name.length; i++)
            if (i < name.length - 1 && getSortedCurrentMethod(i).length() != 0 && getSortedCurrentMethod(i + 1).length() != 0)
                if (getSortedCurrentMethod(i).compareTo(getSortedCurrentMethod(i + 1)) == 0)
                    sortedMethod[0][j]++;
                else
                    j++;

        Arrays2.sort(sortedMethod);
        summary = (new StringBuilder("<UL><LI>Thread Method Analysis<BR><table border=\"1\"><tr><th>Method Name</th><th>Number of Threads : ")).append(getTotalThread()).append("</th><th>Percentage</th></tr>").toString();
        for (int i = methodArray.length - 1; i >= 0; i--)
            summary = (new StringBuilder(String.valueOf(summary))).append("<tr><td>").append(methodArray[(int) sortedMethod[1][i]]).append("</td><td align=\"right\">").append(sortedMethod[0][i]).append("</td><td align=\"right\">").append(Math.round(((float) sortedMethod[0][i] * 100F) / (float) name.length)).append(" (%)</td></tr>").toString();

        if (emptyMethod != 0L)
            summary = (new StringBuilder(String.valueOf(summary))).append("<tr><td>Unknown method : ").append(emptyMethod).append("<tr><td align=\"right\">").append(Math.round(((float) emptyMethod * 100F) / (float) name.length)).append(" (%))</td></tr>").toString();
        else if (summary.length() != 0)
            summary = (new StringBuilder(String.valueOf(summary))).append("<BR>").toString();
        return (new StringBuilder(String.valueOf(summary))).append("</table></UL>").toString();
    }

    public String getMethodSummary(int sn) {
        long emptyMethod = 0L;
        String summary = "";
        List methodList = new ArrayList();
        for (int i = 0; i < name.length; i++) {
            String methodName = getSortedCurrentMethod(i);
            if (methodName.length() != 0 && !methodList.contains(methodName))
                methodList.add(methodName);
            else if (methodName.length() == 0)
                emptyMethod++;
        }

        String methodArray[] = (String[]) methodList.toArray(new String[methodList.size()]);
        sortedMethod = new long[2][methodArray.length];
        methodList.clear();
        for (int i = 0; i < methodArray.length; i++) {
            sortedMethod[0][i] = 1L;
            sortedMethod[1][i] = i;
        }

        int j = 0;
        for (int i = 0; i < name.length; i++)
            if (i < name.length - 1 && getSortedCurrentMethod(i).length() != 0 && getSortedCurrentMethod(i + 1).length() != 0)
                if (getSortedCurrentMethod(i).compareTo(getSortedCurrentMethod(i + 1)) == 0)
                    sortedMethod[0][j]++;
                else
                    j++;

        Arrays2.sort(sortedMethod);
        summary = (new StringBuilder("<UL><LI><a name=\"TM")).append(sn).append("\">Thread Method Analysis</a><BR><table border=\"1\"><tr><th>Method Name</th><th>Number of Threads : ").append(getTotalThread()).append("</th><th>Percentage</th></tr>").toString();
        for (int i = methodArray.length - 1; i >= 0; i--)
            summary = (new StringBuilder(String.valueOf(summary))).append("<tr><td>").append(methodArray[(int) sortedMethod[1][i]]).append("</td><td align=\"right\">").append(sortedMethod[0][i]).append("</td><td align=\"right\">").append(Math.round(((float) sortedMethod[0][i] * 100F) / (float) name.length)).append(" (%)</td></tr>").toString();

        if (emptyMethod != 0L)
            summary = (new StringBuilder(String.valueOf(summary))).append("<tr><td>Unknown method : ").append(emptyMethod).append("<tr><td align=\"right\">").append(Math.round(((float) emptyMethod * 100F) / (float) name.length)).append(" (%))</td></tr>").toString();
        else if (summary.length() != 0)
            summary = (new StringBuilder(String.valueOf(summary))).append("<BR>").toString();
        return (new StringBuilder(String.valueOf(summary))).append("</table></UL>").toString();
    }

    public String getName(int idx) {
        return name[idx];
    }

    public Long getNativeID(int idx) {
        return new Long(nid[idx]);
    }

    public String getNativeStack(int idx) {
        return nativeStack[idx];
    }

    public long getOWait() {
        long count = 0L;
        for (int i = 0; i < state.length; i++)
            if (state[i] == 4)
                count++;

        return count;
    }

    public String getOwningMonitor(int idx) {
        if (mdump == null)
            return null;
        for (int i = 0; i < mdump.length; i++)
            if (mdump[i].owner == sys_thread[idx])
                return mdump[i].objectName;

        return null;
    }

    public MonitorDump getOwningMonitorDump(int idx) {
        if (mdump == null)
            return null;
        for (int i = 0; i < mdump.length; i++)
            if (mdump[i].owner == sys_thread[idx])
                return mdump[i];

        return null;
    }

    public String getOwningMonitors(int idx) {
        boolean isFirst = true;
        String monitors = null;
        if (mdump == null)
            return null;
        for (int i = 0; i < mdump.length; i++)
            if (mdump[i].owner == sys_thread[idx])
                if (isFirst) {
                    isFirst = false;
                    monitors = new String(mdump[i].objectName);
                } else {
                    monitors = (new StringBuilder(String.valueOf(monitors))).append(" , ").append(mdump[i].objectName).toString();
                }

        return monitors;
    }

    public long getRunnable() {
        long count = 0L;
        for (int i = 0; i < state.length; i++)
            if (state[i] == 0)
                count++;

        return count;
    }

    public String getSortedCurrentMethod(int idx) {
        return getCurrentMethod(currentMethodArray[idx]);
    }

    public String getSortedName(int idx) {
        return name[nameArray[idx]];
    }

    public int getStackTraceDepth(int selectedRow) {
        return javaStackDepth[selectedRow];
    }

    public String getStackTrace(int selectedRow) {
        String stackTrace = (new StringBuilder("Thread Name : ")).append(getName(selectedRow)).append("<BR><BR>State : ").append(getState(selectedRow)).append("<BR><BR>").toString();
        String m1 = getOwningMonitors(selectedRow);
        if (m1 != null)
            stackTrace = (new StringBuilder(String.valueOf(stackTrace))).append("Owns Monitor Lock on ").append(m1).append("<BR><BR>").toString();
        m1 = getWaitingMonitors(selectedRow);
        if (m1 != null)
            stackTrace = (new StringBuilder(String.valueOf(stackTrace))).append("Waiting for Monitor Lock on ").append(m1).append("<BR><BR>").toString();
        if (javaStack[selectedRow] != null)
            stackTrace = (new StringBuilder(String.valueOf(stackTrace))).append("Java Stack :<BR>").append(javaStack[selectedRow]).toString();
        else
            stackTrace = (new StringBuilder(String.valueOf(stackTrace))).append("No Java Stack Trace<BR>").toString();
        if (nativeStack[selectedRow] != null)
            stackTrace = (new StringBuilder(String.valueOf(stackTrace))).append("<BR>Native Stack :<BR>").append(nativeStack[selectedRow]).toString();
        else
            stackTrace = (new StringBuilder(String.valueOf(stackTrace))).append("<BR>No Native Stack Trace<BR>").toString();
        if (stackTrace.length() == 0)
            stackTrace = "No stack trace available";
        return stackTrace;
    }

    public String getState(int idx) {
        String deadlock;
        if (isDeadlock[idx])
            deadlock = "Deadlock/";
        else
            deadlock = "";
        switch (state[idx]) {
            case 0: // '\0'
                return (new StringBuilder(String.valueOf(deadlock))).append("Runnable").toString();

            case 1: // '\001'
                return (new StringBuilder(String.valueOf(deadlock))).append("Waiting on condition").toString();

            case 2: // '\002'
                return (new StringBuilder(String.valueOf(deadlock))).append("Waiting on monitor").toString();

            case 3: // '\003'
                return (new StringBuilder(String.valueOf(deadlock))).append("Suspended").toString();

            case 4: // '\004'
                return (new StringBuilder(String.valueOf(deadlock))).append("in Object.wait()").toString();

            case 5: // '\005'
                return (new StringBuilder(String.valueOf(deadlock))).append("Blocked").toString();

            case 6: // '\006'
                return (new StringBuilder(String.valueOf(deadlock))).append("Parked").toString();
        }
        return "";
    }

    public String getStateColor(int idx) {
        if (isDeadlock[idx])
            return "bgcolor=\"# \"";
        String deadlock = "";
        switch (state[idx]) {
            case 0: // '\0'
                return (new StringBuilder(String.valueOf(deadlock))).append("Runnable").toString();

            case 1: // '\001'
                return (new StringBuilder(String.valueOf(deadlock))).append("Waiting on condition").toString();

            case 2: // '\002'
                return (new StringBuilder(String.valueOf(deadlock))).append("Waiting on monitor").toString();

            case 3: // '\003'
                return (new StringBuilder(String.valueOf(deadlock))).append("Suspended").toString();

            case 4: // '\004'
                return (new StringBuilder(String.valueOf(deadlock))).append("in Object.wait()").toString();

            case 5: // '\005'
                return (new StringBuilder(String.valueOf(deadlock))).append("Blocked").toString();

            case 6: // '\006'
                return (new StringBuilder(String.valueOf(deadlock))).append("Parked").toString();
        }
        return null;
    }

    public long getSuspended() {
        long count = 0L;
        for (int i = 0; i < state.length; i++)
            if (state[i] == 3)
                count++;

        return count;
    }

    public long getSys_ThreadFromTID(long tid) {
        for (int i = 0; i < sys_thread.length; i++)
            if (tid == this.tid[i])
                if (sys_thread[i] == 0L)
                    throw new RuntimeException((new StringBuilder("Could not find sys thread ID for TID ")).append(tid).toString());
                else
                    return sys_thread[i];

        return -1L;
    }

    public long getTotalThread() {
        return (long) state.length;
    }

    public String getWaitingMonitor(int idx) {
        if (mdump == null)
            return null;
        for (int i = 0; i < mdump.length; i++)
            if (mdump[i] != null && mdump[i].waiting != null && mdump[i].waiting.size() != 0) {
                for (int j = 0; j < mdump[i].waiting.size(); j++)
                    if (((Long) mdump[i].waiting.get(j)).longValue() == sys_thread[idx])
                        return mdump[i].objectName;

            }

        return null;
    }

    public String getWaitingMonitors(int idx) {
        String monitors = null;
        boolean isFirst = true;
        if (mdump == null)
            return null;
        for (int i = 0; i < mdump.length; i++)
            if (mdump[i] != null && mdump[i].waiting != null && mdump[i].waiting.size() != 0) {
                for (int j = 0; j < mdump[i].waiting.size(); j++)
                    if (((Long) mdump[i].waiting.get(j)).longValue() == sys_thread[idx])
                        if (isFirst) {
                            monitors = new String(mdump[i].objectName);
                            isFirst = false;
                        } else {
                            monitors = (new StringBuilder(String.valueOf(monitors))).append(" , ").append(mdump[i].objectName).toString();
                        }

            }

        return monitors;
    }

    public long getWCondition() {
        long count = 0L;
        for (int i = 0; i < state.length; i++)
            if (state[i] == 1)
                count++;

        return count;
    }

    public long getWMonitor() {
        long count = 0L;
        for (int i = 0; i < state.length; i++)
            if (state[i] == 2)
                count++;

        return count;
    }

    public long getParked() {
        long count = 0L;
        for (int i = 0; i < state.length; i++)
            if (state[i] == 6)
                count++;

        return count;
    }

    public JTree nativeMemoryTree;
    public String homeDir;
    public String dllDir;
    public String sysCP;
    public String osLevel;
    public String hypervisor;
    public String architecture;
    public String numberOfCPU;
    public String userArgs;
    static final String AGGREGATION[] = {
            "ThreadManager.JobsProcessorThread.InternalThread", "WLMMonitorSleeper", "Deferrable Alarm", "HAManager.thread.pool", "WebContainer", "Servlet.Engine.Transports :", "ServerSocket", "ORB.thread.pool", "SoapConnectorThreadPool", "Alarm",
            "Thread"
    };
    long xmx;
    public boolean isNewFormat;
    public String fileName;
    public boolean isIBM;
    public boolean isJ9;
    public String gcHistory;
    public String name[];
    public long tid[];
    public int priority[];
    public long sys_thread[];
    public long nid[];
    public int macro[];
    public int state[];
    public String javaStack[];
    public float cpu[][];
    public int javaStackDepth[];
    public String nativeStack[];
    public Hashtable threadHash;
    public Hashtable threadHash2;
    public MonitorDump mdump[];
    public String summary;
    public List deadlock;
    public int javaHeap;
    public int numberOfRunnable;
    public String warning;
    public long sortedArray[][];
    public long sortedMethod[][];
    public String methodArray[];
    public int nameArray[];
    public int currentMethodArray[];
    public int sortedDepthArray[];
    public int stateArray[];
    public int idArray[];
    public long af;
    public long gc;
    public long free;
    public long allocated;
    public long timeStamp;
    public long pid;
    public long currentTid;
    public int currentThreadIndex;
    public boolean isDeadlock[];
    public String pattern[];
    public long inuse;
    public boolean oome;
    public String javaVersion;
    public CommonTable ulimitTable;
    public CommonTable eVariables;
    public CommonTable memorySegment;
    public CommonTable sharedClassCacheTable;
    public CommonTable commandLineTable;


}
