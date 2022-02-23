//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package com.ibm.jinwoo;

import com.ibm.jinwoo.Arrays2;
import com.ibm.jinwoo.MonitorDump;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Hashtable;
import java.util.List;
import javax.swing.JTable;
import javax.swing.JTree;

public class ThreadDump {
    public JTree nativeMemoryTree = null;
    public String homeDir = null;
    public String dllDir = null;
    public String sysCP = null;
    public String osLevel = null;
    public String hypervisor = null;
    public String architecture = null;
    public String numberOfCPU = null;
    public String userArgs = "";
    static final String[] AGGREGATION = new String[]{"ThreadManager.JobsProcessorThread.InternalThread", "WLMMonitorSleeper", "Deferrable Alarm", "HAManager.thread.pool", "WebContainer", "Servlet.Engine.Transports :", "ServerSocket", "ORB.thread.pool", "SoapConnectorThreadPool", "Alarm", "Thread"};
    long xmx;
    public boolean isNewFormat;
    public String fileName;
    public boolean isIBM;
    public boolean isJ9;
    public String gcHistory = null;
    public String[] name;
    public long[] tid;
    public int[] priority;
    public long[] sys_thread;
    public long[] nid;
    public int[] macro;
    public int[] state;
    public String[] javaStack;
    public float[][] cpu;
    public int[] javaStackDepth;
    public String[] nativeStack;
    public Hashtable threadHash = null;
    public Hashtable threadHash2 = null;
    public MonitorDump[] mdump;
    public String summary;
    public List deadlock;
    public int javaHeap;
    public int numberOfRunnable;
    public String warning;
    //    public long[][] sortedArray;
    public long[][] sortedMethod;
    //    public String[] methodArray;
//    public int[] nameArray;
//    public int[] currentMethodArray;
//    public int[] sortedDepthArray;
//    public int[] stateArray;
//    public int[] idArray;
    public long af;
    public long gc;
    public long free;
    public long allocated;
    public long timeStamp;
    public long pid;
    public long currentTid;
    public int currentThreadIndex;
    public boolean[] isDeadlock;
    public String[] pattern;
    public long inuse;
    public boolean oome;
    public String javaVersion;
    public CommonTable ulimitTable;
    public CommonTable eVariables;
    public CommonTable memorySegment;
    public CommonTable sharedClassCacheTable;
    public CommonTable commandLineTable;

    public ThreadDump() {
    }

    public String getAggregationSummary() {
        long emptyMethod = 0L;
        String summary = "";
        List methodList = new ArrayList();

        for (int i = 0; i < this.name.length; ++i) {
            String methodName = this.getName(i);
            if (methodName != null) {
                if (methodName.length() != 0) {
                    int result = this.getCommonThreadName(methodName);
                    if (result >= 0 && !methodList.contains(AGGREGATION[result])) {
                        methodList.add(AGGREGATION[result]);
                    }
                } else if (methodName.length() == 0) {
                    ++emptyMethod;
                }
            }
        }

        String[] methodArray = (String[]) methodList.toArray(new String[methodList.size()]);
        this.sortedMethod = new long[2][methodArray.length];
        methodList.clear();

        for (int i = 0; i < methodArray.length; ++i) {
            this.sortedMethod[0][i] = 0L;
            this.sortedMethod[1][i] = i;
        }

        int j = 0;
        int[] value = new int[AGGREGATION.length];

        int i;
        for (i = 0; i < value.length; ++i) {
            value[i] = 0;
        }

        for (i = 0; i < this.name.length; ++i) {
            int result = this.getCommonThreadName(this.getName(i));
            if (result >= 0) {
                for (int k = 0; k < methodArray.length; ++k) {
                    if (methodArray[k].compareTo(AGGREGATION[result]) == 0) {
                        this.sortedMethod[0][k]++;
                    }
                }
            }
        }

        Arrays2.sort(this.sortedMethod);
        summary = "<UL><LI>Thread Aggregation Analysis<BR><table border=\"1\"><tr><th>Thread Type</th><th>Number of Threads : " + this.getTotalThread() + "</th><th>Percentage</th></tr>";

        for (i = methodArray.length - 1; i >= 0; --i) {
            summary = summary + "<tr><td>" + methodArray[(int) this.sortedMethod[1][i]] + "</td><td align=\"right\">" + this.sortedMethod[0][i] + "</td><td align=\"right\">" + Math.round((float) this.sortedMethod[0][i] * 100.0F / (float) this.name.length) + " (%)</td></tr>";
        }

        if (emptyMethod != 0L) {
            summary = summary + "<tr><td>Unknown method : " + emptyMethod + "<tr><td align=\"right\">" + Math.round((float) emptyMethod * 100.0F / (float) this.name.length) + " (%))</td></tr>";
        } else if (summary.length() != 0) {
            summary = summary + "<BR>";
        }

        return summary + "</table></UL>";
    }

    public long getBlocked() {
        long count = 0L;

        for (int i = 0; i < this.state.length; ++i) {
            if (this.state[i] == 5) {
                ++count;
            }
        }

        return count;
    }

    public int getCommonThreadName(String key) {
        if (key == null) {
            return -1;
        } else if (key.length() == 0) {
            return -1;
        } else {
            for (int i = 0; i < AGGREGATION.length; ++i) {
                if (key.indexOf(AGGREGATION[i]) >= 0) {
                    return i;
                }
            }

            return -1;
        }
    }

    public String getCurrentMethod(int r) {
        if (this.javaStack[r] != null && this.javaStack[r].length() != 0) {
            if (this.pattern[r] != null) {
                return this.pattern[r];
            } else if (this.macro[r] == 1) {
                return "IDLE";
            } else if (this.macro[r] == 2) {
                return "KEEP-ALIVE";
            } else if (this.macro[r] == 3) {
                return "LISTEN";
            } else if (this.macro[r] == 4) {
                return "READ REQUEST";
            } else {
                int i = this.javaStack[r].indexOf("<BR>");
                if (i != -1) {
                    int j = this.javaStack[r].indexOf("at ");
                    if (j != -1) {
                        return this.javaStack[r].substring(j + 3, i);
                    }
                }

                return "NO JAVA STACK";
            }
        } else {
            return "NO JAVA STACK";
        }
    }

//    public long getDeadlock() {
//        long count = 0L;
//
//        for(int i = 0; i < this.isDeadlock.length; ++i) {
//            if (this.isDeadlock[i]) {
//                ++count;
//            }
//        }
//
//        return count;
//    }
//
//    public int getIndexFromName(String threadName) {
//        for(int i = 0; i < this.name.length; ++i) {
//            if (threadName.compareTo(this.name[i]) == 0) {
//                return i;
//            }
//        }
//
//        return -1;
//    }

    //    public int getIndexFromSysThread(long id) {
//        int idx = Arrays.binarySearch(this.sortedArray[0], id);
//        return idx < 0 ? -1 : (int)this.sortedArray[1][idx];
//    }
//
//    public String getJavaStack(int idx) {
//        return this.javaStack[idx];
//    }
//
//    public String getMethodSummary() {
//        long emptyMethod = 0L;
//        String summary = "";
//        List methodList = new ArrayList();
//
//        int j;
//        for(j = 0; j < this.name.length; ++j) {
//            String methodName = this.getSortedCurrentMethod(j);
//            if (methodName.length() != 0 && !methodList.contains(methodName)) {
//                methodList.add(methodName);
//            } else if (methodName.length() == 0) {
//                ++emptyMethod;
//            }
//        }
//
//        this.methodArray = (String[])methodList.toArray(new String[methodList.size()]);
//        this.sortedMethod = new long[2][this.methodArray.length];
//        methodList.clear();
//
//        for(j = 0; j < this.methodArray.length; ++j) {
//            this.sortedMethod[0][j] = 1L;
//            this.sortedMethod[1][j] = (long)j;
//        }
//
//        j = 0;
//
//        int i;
//        for(i = 0; i < this.name.length; ++i) {
//            if (i < this.name.length - 1 && this.getSortedCurrentMethod(i).length() != 0 && this.getSortedCurrentMethod(i + 1).length() != 0) {
//                if (this.getSortedCurrentMethod(i).compareTo(this.getSortedCurrentMethod(i + 1)) == 0) {
//                    this.sortedMethod[0][j]++;
//                } else {
//                    ++j;
//                }
//            }
//        }
//
//        Arrays2.sort(this.sortedMethod);
//        summary = "<UL><LI>Thread Method Analysis<BR><table border=\"1\"><tr><th>Method Name</th><th>Number of Threads : " + this.getTotalThread() + "</th><th>Percentage</th></tr>";
//
//        for(i = this.methodArray.length - 1; i >= 0; --i) {
//            summary = summary + "<tr><td>" + this.methodArray[(int)this.sortedMethod[1][i]] + "</td><td align=\"right\">" + this.sortedMethod[0][i] + "</td><td align=\"right\">" + Math.round((float)this.sortedMethod[0][i] * 100.0F / (float)this.name.length) + " (%)</td></tr>";
//        }
//
//        if (emptyMethod != 0L) {
//            summary = summary + "<tr><td>Unknown method : " + emptyMethod + "<tr><td align=\"right\">" + Math.round((float)emptyMethod * 100.0F / (float)this.name.length) + " (%))</td></tr>";
//        } else if (summary.length() != 0) {
//            summary = summary + "<BR>";
//        }
//
//        return summary + "</table></UL>";
//    }
//
//    public String getMethodSummary(int sn) {
//        long emptyMethod = 0L;
//        String summary = "";
//        List methodList = new ArrayList();
//
//        for(int i = 0; i < this.name.length; ++i) {
//            String methodName = this.getSortedCurrentMethod(i);
//            if (methodName.length() != 0 && !methodList.contains(methodName)) {
//                methodList.add(methodName);
//            } else if (methodName.length() == 0) {
//                ++emptyMethod;
//            }
//        }
//
//        String[] methodArray = (String[])methodList.toArray(new String[methodList.size()]);
//        this.sortedMethod = new long[2][methodArray.length];
//        methodList.clear();
//
//        for(int i = 0; i < methodArray.length; ++i) {
//            this.sortedMethod[0][i] = 1L;
//            this.sortedMethod[1][i] = i;
//        }
//
//        int j = 0;
//
//        for(int i = 0; i < this.name.length; ++i) {
//            if (i < this.name.length - 1 && this.getSortedCurrentMethod(i).length() != 0 && this.getSortedCurrentMethod(i + 1).length() != 0) {
//                if (this.getSortedCurrentMethod(i).compareTo(this.getSortedCurrentMethod(i + 1)) == 0) {
//                    this.sortedMethod[0][j]++;
//                } else {
//                    ++j;
//                }
//            }
//        }
//
//        Arrays2.sort(this.sortedMethod);
//        summary = "<UL><LI><a name=\"TM" + sn + "\">Thread Method Analysis</a><BR><table border=\"1\"><tr><th>Method Name</th><th>Number of Threads : " + this.getTotalThread() + "</th><th>Percentage</th></tr>";
//
//        for(int i = methodArray.length - 1; i >= 0; --i) {
//            summary = summary + "<tr><td>" + methodArray[(int)this.sortedMethod[1][i]] + "</td><td align=\"right\">" + this.sortedMethod[0][i] + "</td><td align=\"right\">" + Math.round((float)this.sortedMethod[0][i] * 100.0F / (float)this.name.length) + " (%)</td></tr>";
//        }
//
//        if (emptyMethod != 0L) {
//            summary = summary + "<tr><td>Unknown method : " + emptyMethod + "<tr><td align=\"right\">" + Math.round((float)emptyMethod * 100.0F / (float)this.name.length) + " (%))</td></tr>";
//        } else if (summary.length() != 0) {
//            summary = summary + "<BR>";
//        }
//
//        return summary + "</table></UL>";
//    }
//
    public String getName(int idx) {
        return this.name[idx];
    }

    //
//    public Long getNativeID(int idx) {
//        return new Long(this.nid[idx]);
//    }
//
//    public String getNativeStack(int idx) {
//        return this.nativeStack[idx];
//    }
//
//    public long getOWait() {
//        long count = 0L;
//
//        for(int i = 0; i < this.state.length; ++i) {
//            if (this.state[i] == 4) {
//                ++count;
//            }
//        }
//
//        return count;
//    }
//
//    public String getOwningMonitor(int idx) {
//        if (this.mdump == null) {
//            return null;
//        } else {
//            for(int i = 0; i < this.mdump.length; ++i) {
//                if (this.mdump[i].owner == this.sys_thread[idx]) {
//                    return this.mdump[i].objectName;
//                }
//            }
//
//            return null;
//        }
//    }
//
//    public MonitorDump getOwningMonitorDump(int idx) {
//        if (this.mdump == null) {
//            return null;
//        } else {
//            for(int i = 0; i < this.mdump.length; ++i) {
//                if (this.mdump[i].owner == this.sys_thread[idx]) {
//                    return this.mdump[i];
//                }
//            }
//
//            return null;
//        }
//    }
//
    public String getOwningMonitors(int idx) {
        boolean isFirst = true;
        String monitors = null;
        if (this.mdump == null) {
            return null;
        } else {
            for(int i = 0; i < this.mdump.length; ++i) {
                if (this.mdump[i].owner == this.sys_thread[idx]) {
                    if (isFirst) {
                        isFirst = false;
                        monitors = new String(this.mdump[i].objectName);
                    } else {
                        monitors = monitors + " , " + this.mdump[i].objectName;
                    }
                }
            }

            return monitors;
        }
    }
//
//    public long getRunnable() {
//        long count = 0L;
//
//        for(int i = 0; i < this.state.length; ++i) {
//            if (this.state[i] == 0) {
//                ++count;
//            }
//        }
//
//        return count;
//    }
//
//    public String getSortedCurrentMethod(int idx) {
//        return this.getCurrentMethod(this.currentMethodArray[idx]);
//    }
//
//    public String getSortedName(int idx) {
//        return this.name[this.nameArray[idx]];
//    }
//
//    public int getStackTraceDepth(int selectedRow) {
//        return this.javaStackDepth[selectedRow];
//    }
//
//    public String getStackTrace(int selectedRow) {
//        String stackTrace = "Thread Name : " + this.getName(selectedRow) + "<BR><BR>State : " + this.getState(selectedRow) + "<BR><BR>";
//        String m1 = this.getOwningMonitors(selectedRow);
//        if (m1 != null) {
//            stackTrace = stackTrace + "Owns Monitor Lock on " + m1 + "<BR><BR>";
//        }
//
//        m1 = this.getWaitingMonitors(selectedRow);
//        if (m1 != null) {
//            stackTrace = stackTrace + "Waiting for Monitor Lock on " + m1 + "<BR><BR>";
//        }
//
//        if (this.javaStack[selectedRow] != null) {
//            stackTrace = stackTrace + "Java Stack :<BR>" + this.javaStack[selectedRow];
//        } else {
//            stackTrace = stackTrace + "No Java Stack Trace<BR>";
//        }
//
//        if (this.nativeStack[selectedRow] != null) {
//            stackTrace = stackTrace + "<BR>Native Stack :<BR>" + this.nativeStack[selectedRow];
//        } else {
//            stackTrace = stackTrace + "<BR>No Native Stack Trace<BR>";
//        }
//
//        if (stackTrace.length() == 0) {
//            stackTrace = "No stack trace available";
//        }
//
//        return stackTrace;
//    }
//
    public String getState(int idx) {
        String deadlock;
        if (this.isDeadlock[idx]) {
            deadlock = "Deadlock/";
        } else {
            deadlock = "";
        }

        switch(this.state[idx]) {
            case 0:
                return deadlock + "Runnable";
            case 1:
                return deadlock + "Waiting on condition";
            case 2:
                return deadlock + "Waiting on monitor";
            case 3:
                return deadlock + "Suspended";
            case 4:
                return deadlock + "in Object.wait()";
            case 5:
                return deadlock + "Blocked";
            case 6:
                return deadlock + "Parked";
            default:
                return "";
        }
    }
//
//    public String getStateColor(int idx) {
//        if (this.isDeadlock[idx]) {
//            return "bgcolor=\"# \"";
//        } else {
//            String deadlock = "";
//            switch(this.state[idx]) {
//                case 0:
//                    return deadlock + "Runnable";
//                case 1:
//                    return deadlock + "Waiting on condition";
//                case 2:
//                    return deadlock + "Waiting on monitor";
//                case 3:
//                    return deadlock + "Suspended";
//                case 4:
//                    return deadlock + "in Object.wait()";
//                case 5:
//                    return deadlock + "Blocked";
//                case 6:
//                    return deadlock + "Parked";
//                default:
//                    return null;
//            }
//        }
//    }
//
//    public long getSuspended() {
//        long count = 0L;
//
//        for(int i = 0; i < this.state.length; ++i) {
//            if (this.state[i] == 3) {
//                ++count;
//            }
//        }
//
//        return count;
//    }
//
    public long getSys_ThreadFromTID(long tid) {
        for(int i = 0; i < this.sys_thread.length; ++i) {
            if (tid == this.tid[i]) {
                if (this.sys_thread[i] == 0L) {
                    throw new RuntimeException("Could not find sys thread ID for TID " + tid);
                }

                return this.sys_thread[i];
            }
        }

        return -1L;
    }
//
    public long getTotalThread() {
        return (long) this.state.length;
    }
//
//    public String getWaitingMonitor(int idx) {
//        if (this.mdump == null) {
//            return null;
//        } else {
//            for(int i = 0; i < this.mdump.length; ++i) {
//                if (this.mdump[i] != null && this.mdump[i].waiting != null && this.mdump[i].waiting.size() != 0) {
//                    for(int j = 0; j < this.mdump[i].waiting.size(); ++j) {
//                        if ((Long)this.mdump[i].waiting.get(j) == this.sys_thread[idx]) {
//                            return this.mdump[i].objectName;
//                        }
//                    }
//                }
//            }
//
//            return null;
//        }
//    }
//
    public String getWaitingMonitors(int idx) {
        String monitors = null;
        boolean isFirst = true;
        if (this.mdump == null) {
            return null;
        } else {
            for(int i = 0; i < this.mdump.length; ++i) {
                if (this.mdump[i] != null && this.mdump[i].waiting != null && this.mdump[i].waiting.size() != 0) {
                    for(int j = 0; j < this.mdump[i].waiting.size(); ++j) {
                        if ((Long)this.mdump[i].waiting.get(j) == this.sys_thread[idx]) {
                            if (isFirst) {
                                monitors = new String(this.mdump[i].objectName);
                                isFirst = false;
                            } else {
                                monitors = monitors + " , " + this.mdump[i].objectName;
                            }
                        }
                    }
                }
            }

            return monitors;
        }
    }
//
//    public long getWCondition() {
//        long count = 0L;
//
//        for(int i = 0; i < this.state.length; ++i) {
//            if (this.state[i] == 1) {
//                ++count;
//            }
//        }
//
//        return count;
//    }
//
//    public long getWMonitor() {
//        long count = 0L;
//
//        for(int i = 0; i < this.state.length; ++i) {
//            if (this.state[i] == 2) {
//                ++count;
//            }
//        }
//
//        return count;
//    }
//
//    public long getParked() {
//        long count = 0L;
//
//        for(int i = 0; i < this.state.length; ++i) {
//            if (this.state[i] == 6) {
//                ++count;
//            }
//        }
//
//        return count;
//    }
}
