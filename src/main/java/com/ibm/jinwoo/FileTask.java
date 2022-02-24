//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package com.ibm.jinwoo;

import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellRenderer;
import javax.swing.tree.DefaultMutableTreeNode;
import java.awt.Component;
import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.text.ParseException;
import java.text.ParsePosition;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Date;
import java.util.HashSet;
import java.util.Hashtable;
import java.util.Iterator;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Stack;
import java.util.StringTokenizer;
import java.util.Vector;
import java.util.regex.Pattern;

public class FileTask {
    private static final String userLimit = "2CIUSERLIMIT";
    private static final String enviromentVariables = "2CIENVVAR";
    private static final String MEMUSER = "MEMUSER";
    private static final boolean SHOW_MEMORY_SEGMENT_ANALYSIS = Boolean.getBoolean("SHOW_MEMORY_SEGMENT_ANALYSIS");
    static final String BTT = "<p><a href=\"#top\">Back to top</a></p>";
    static NumberFormat nf = NumberFormat.getNumberInstance();
    int fileSequenceNumber;
    String outputHTMLFileName = null;
    boolean debug = Boolean.getBoolean("DEBUG");
    int monIndex = 0;
    List monList = new ArrayList();
    Hashtable threadIdent = new Hashtable();
    public static final int SIZE_OF_LARGE_OBJECT = 0xdbba0;
    private int lengthOfTask;
    private int current = 0;
    private int overall = 0;
    private boolean done = false;
    private boolean canceled = false;
    private String statMessage;
    static NumberFormat numberFormatter = NumberFormat.getNumberInstance();
    static SimpleDateFormat formatter = new SimpleDateFormat("yyyy'/'MM'/'dd 'at' HH:mm:ss");
    static SimpleDateFormat formatterSolaris = new SimpleDateFormat("yyyy'-'MM'-'dd HH:mm:ss");
    static SimpleDateFormat formatterHP = new SimpleDateFormat("EEE MMM dd HH:mm:ss yyyy");
    static final String homeDir = "1CIJAVAHOMEDIR Java Home Dir:";
    static final String dllDir = "1CIJAVADLLDIR  Java DLL Dir:";
    static final String sysCP = "1CISYSCP       Sys Classpath:";
    static final String userArgs0 = "1CIUSERARGS";
    static final String userArgs = "2CIUSERARG";
    static final String osLevel = "2XHOSLEVEL     OS Level         : ";
    static final String hypervisorName = "2CISYSINFO     Hypervisor name = ";
    static final String cpuArchitecture = "3XHCPUARCH       Architecture   : ";
    static final String numberOfCPU = "3XHNUMCPUS       How Many       : ";
    static final String xmxcl = "NOTE: Only for Java 5.0 Service Refresh 4 (build date:February 1st, 2007) and older. When you use delegated class loaders, the JVM can create a large number of ClassLoader objects. On IBM Java 5.0 Service Refresh 4 and older, the number of class loaders that are permitted is limited to 8192 by default and an OutOfMemoryError exception is thrown when this limit is exceeded. Use the -Xmxcl parameter to increase the number of class loaders allowed to avoid this problem, for example to 25000, by setting -Xmxcl25000, until the problem is resolved.<BR><BR>Please examine the current thread stack trace to check whether a class loader is being loaded if there is an OutOfMemoryError. For example, the following stack trace indicates that a class loader is being loaded:<BR><BR>at com/ibm/oti/vm/VM.initializeClassLoader(Native Method)<BR>at java/lang/ClassLoader.<init>(ClassLoader.java:120)";
    static final String[] symptom1 = new String[]{"java/lang/OutOfMemoryError", "-Xmxcl", "com/ibm/oti/vm/VM.initializeClassLoader"};
    static final String signatureGCHistoryHeader = "1STGCHTYPE";
    static final String signatureGCHistory = "3STHSTTYPE";
    static final String signatureAllocateError = "J9AllocateIndexableObject() returning NULL!";
    static final String memorySignature = "1STSEGTYPE     Internal Memory";
    static final String memorySignature626 = "1STHEAPTYPE    Object Memory";
    static final String memoryOMSignature = "1STSEGTYPE     Object Memory";
    static final String memoryCMSignature = "1STSEGTYPE     Class Memory";
    static final String memoryJCSignature = "1STSEGTYPE     JIT Code Cache";
    static final String memoryJDSignature = "1STSEGTYPE     JIT Data Cache";
    static final String memorySegmentSignature = "1STSEGMENT";
    static final String memorySegmentSignature626 = "1STHEAP";
    static final String IBMJ9 = "IBM J9";
    static final int LOW_FREE_HEAP = 15;
    static final String kalSSL5 = "at java.net.SocketInputStream.socketRead<BR>at java.net.SocketInputStream.read<BR>at com.ibm.sslite.s.a<BR>at com.ibm.sslite.s.b<BR>at com.ibm.sslite.s.a<BR>at com.ibm.sslite.a.read<BR>at com.ibm.jsse.a.read<BR>at com.ibm.ws.io.Stream.read<BR>at com.ibm.ws.io.ReadStream.readBuffer<BR>at com.ibm.ws.io.ReadStream.read<BR>at com.ibm.ws.http.HttpConnection.run<BR>at com.ibm.ws.util.ThreadPool$Worker.run";
    static final String kalSSL05 = "at java.net.SocketInputStream.socketRead0<BR>at java.net.SocketInputStream.read<BR>at com.ibm.sslite.s.a<BR>at com.ibm.sslite.s.b<BR>at com.ibm.sslite.s.a<BR>at com.ibm.sslite.a.read<BR>at com.ibm.jsse.a.read<BR>at com.ibm.ws.io.Stream.read<BR>at com.ibm.ws.io.ReadStream.readBuffer<BR>at com.ibm.ws.io.ReadStream.read<BR>at com.ibm.ws.http.HttpConnection.run<BR>at com.ibm.ws.util.ThreadPool$Worker.run";
    static final String kal5 = "at java.net.SocketInputStream.socketRead<BR>at java.net.SocketInputStream.read<BR>at com.ibm.ws.io.Stream.read<BR>at com.ibm.ws.io.ReadStream.readBuffer<BR>at com.ibm.ws.io.ReadStream.read<BR>at com.ibm.ws.http.HttpConnection.run<BR>at com.ibm.ws.util.ThreadPool$Worker.run";
    static final String kal4 = "at java.net.SocketInputStream.socketRead<BR>at java.net.SocketInputStream.read<BR>at com.ibm.ws.io.Stream.read<BR>at com.ibm.ws.io.ReadStream.readBuffer<BR>at com.ibm.ws.io.ReadStream.read<BR>at com.ibm.ws.http.HttpConnection.run<BR>at com.ibm.ws.util.CachedThread.run";
    static final String kal05 = "at java.net.SocketInputStream.socketRead0<BR>at java.net.SocketInputStream.read<BR>at com.ibm.ws.io.Stream.read<BR>at com.ibm.ws.io.ReadStream.readBuffer<BR>at com.ibm.ws.io.ReadStream.read<BR>at com.ibm.ws.http.HttpConnection.run<BR>at com.ibm.ws.util.ThreadPool$Worker.run";
    static final String kal04 = "at java.net.SocketInputStream.socketRead0<BR>at java.net.SocketInputStream.read<BR>at com.ibm.ws.io.Stream.read<BR>at com.ibm.ws.io.ReadStream.readBuffer<BR>at com.ibm.ws.io.ReadStream.read<BR>at com.ibm.ws.http.HttpConnection.run<BR>at com.ibm.ws.util.CachedThread.run";
    static final String idle5 = "at java.lang.Object.wait<BR>at java.lang.Object.wait<BR>at com.ibm.ws.util.BoundedBuffer.take<BR>at com.ibm.ws.util.ThreadPool.getTask<BR>at com.ibm.ws.util.ThreadPool$Worker.run";
    static final String idle4 = "at java.lang.Object.wait<BR>at java.lang.Object.wait<BR>at com.ibm.ws.util.CachedThread.waitForRunner<BR>at com.ibm.ws.util.CachedThread.run";
    static final String rr4 = "at java.net.SocketInputStream.socketRead<BR>at java.net.SocketInputStream.read<BR>at com.ibm.ws.io.Stream.read<BR>at com.ibm.ws.io.ReadStream.readBuffer<BR>at com.ibm.ws.io.ReadStream.read<BR>at com.ibm.ws.http.HttpRequest.readRequestLine<BR>at com.ibm.ws.http.HttpRequest.readRequest<BR>at com.ibm.ws.http.HttpConnection.readAndHandleRequest<BR>at com.ibm.ws.http.HttpConnection.run<BR>at com.ibm.ws.util.CachedThread.run";
    static final String rr5 = "at java.net.SocketInputStream.socketRead<BR>at java.net.SocketInputStream.read<BR>at com.ibm.ws.io.Stream.read<BR>at com.ibm.ws.io.ReadStream.readBuffer<BR>at com.ibm.ws.io.ReadStream.read<BR>at com.ibm.ws.http.HttpRequest.readRequestLine<BR>at com.ibm.ws.http.HttpRequest.readRequest<BR>at com.ibm.ws.http.HttpConnection.readAndHandleRequest<BR>at com.ibm.ws.http.HttpConnection.run<BR>at com.ibm.ws.util.ThreadPool$Worker.run";
    static final String rrSSL4 = "at java.net.SocketInputStream.socketRead<BR>at java.net.SocketInputStream.read<BR>at com.ibm.sslite.s.a<BR>at com.ibm.sslite.s.b<BR>at com.ibm.sslite.s.a<BR>at com.ibm.sslite.a.read<BR>at com.ibm.jsse.a.read<BR>at com.ibm.ws.io.Stream.read<BR>at com.ibm.ws.io.ReadStream.readBuffer<BR>at com.ibm.ws.io.ReadStream.read(ReadStream.java<BR>at com.ibm.ws.http.HttpRequest.readRequestLine<BR>at com.ibm.ws.http.HttpRequest.readRequest<BR>at com.ibm.ws.http.HttpConnection.readAndHandleRequest<BR>at com.ibm.ws.http.HttpConnection.run<BR>at com.ibm.ws.util.CachedThread.run";
    static final String rrSSL5 = "at java.net.SocketInputStream.socketRead<BR>at java.net.SocketInputStream.read<BR>at com.ibm.sslite.s.a<BR>at com.ibm.sslite.s.b<BR>at com.ibm.sslite.s.a<BR>at com.ibm.sslite.a.read<BR>at com.ibm.jsse.a.read<BR>at com.ibm.ws.io.Stream.read<BR>at com.ibm.ws.io.ReadStream.readBuffer<BR>at com.ibm.ws.io.ReadStream.read(ReadStream.java<BR>at com.ibm.ws.http.HttpRequest.readRequestLine<BR>at com.ibm.ws.http.HttpRequest.readRequest<BR>at com.ibm.ws.http.HttpConnection.readAndHandleRequest<BR>at com.ibm.ws.http.HttpConnection.run<BR>at com.ibm.ws.util.ThreadPool$Worker.run";
    static final String rr04 = "at java.net.SocketInputStream.socketRead<BR>at java.net.SocketInputStream.read<BR>at com.ibm.ws.io.Stream.read<BR>at com.ibm.ws.io.ReadStream.readBuffer<BR>at com.ibm.ws.io.ReadStream.read<BR>at com.ibm.ws.http.HttpRequest.readRequestLine<BR>at com.ibm.ws.http.HttpRequest.readRequest<BR>at com.ibm.ws.http.HttpConnection.readAndHandleRequest<BR>at com.ibm.ws.http.HttpConnection.run<BR>at com.ibm.ws.util.CachedThread.run";
    static final String rr05 = "at java.net.SocketInputStream.socketRead<BR>at java.net.SocketInputStream.read<BR>at com.ibm.ws.io.Stream.read<BR>at com.ibm.ws.io.ReadStream.readBuffer<BR>at com.ibm.ws.io.ReadStream.read<BR>at com.ibm.ws.http.HttpRequest.readRequestLine<BR>at com.ibm.ws.http.HttpRequest.readRequest<BR>at com.ibm.ws.http.HttpConnection.readAndHandleRequest<BR>at com.ibm.ws.http.HttpConnection.run<BR>at com.ibm.ws.util.ThreadPool$Worker.run";
    static final String rrSSL04 = "at java.net.SocketInputStream.socketRead<BR>at java.net.SocketInputStream.read<BR>at com.ibm.sslite.s.a<BR>at com.ibm.sslite.s.b<BR>at com.ibm.sslite.s.a<BR>at com.ibm.sslite.a.read<BR>at com.ibm.jsse.a.read<BR>at com.ibm.ws.io.Stream.read<BR>at com.ibm.ws.io.ReadStream.readBuffer<BR>at com.ibm.ws.io.ReadStream.read(ReadStream.java<BR>at com.ibm.ws.http.HttpRequest.readRequestLine<BR>at com.ibm.ws.http.HttpRequest.readRequest<BR>at com.ibm.ws.http.HttpConnection.readAndHandleRequest<BR>at com.ibm.ws.http.HttpConnection.run<BR>at com.ibm.ws.util.CachedThread.run";
    static final String rrSSL05 = "at java.net.SocketInputStream.socketRead<BR>at java.net.SocketInputStream.read<BR>at com.ibm.sslite.s.a<BR>at com.ibm.sslite.s.b<BR>at com.ibm.sslite.s.a<BR>at com.ibm.sslite.a.read<BR>at com.ibm.jsse.a.read<BR>at com.ibm.ws.io.Stream.read<BR>at com.ibm.ws.io.ReadStream.readBuffer<BR>at com.ibm.ws.io.ReadStream.read(ReadStream.java<BR>at com.ibm.ws.http.HttpRequest.readRequestLine<BR>at com.ibm.ws.http.HttpRequest.readRequest<BR>at com.ibm.ws.http.HttpConnection.readAndHandleRequest<BR>at com.ibm.ws.http.HttpConnection.run<BR>at com.ibm.ws.util.ThreadPool$Worker.run";
    static final String listen = "at java.net.PlainSocketImpl.socketAccept<BR>at java.net.PlainSocketImpl.accept<BR>at java.net.ServerSocket.implAccept<BR>at java.net.ServerSocket.accept<BR>at com.ibm.ws.http.HttpTransport.run<BR>at java.lang.Thread.run";
    static final String listenSSL = "at java.net.PlainSocketImpl.socketAccept<BR>at java.net.PlainSocketImpl.accept<BR>at java.net.ServerSocket.implAccept<BR>at java.net.ServerSocket.accept<BR>at com.ibm.jsse.br.accept<BR>at com.ibm.ws.http.HttpTransport.run<BR>at java.lang.Thread.run";
    static final String listen2SSL = "at java.net.PlainSocketImpl.socketAccept<BR>at java.net.PlainSocketImpl.accept<BR>at java.net.ServerSocket.implAccept<BR>at java.net.ServerSocket.accept<BR>at com.ibm.jsse.bc.accept<BR>at com.ibm.ws.http.HttpTransport.run<BR>at java.lang.Thread.run";
    static final String listen3SSL = "at java.net.PlainSocketImpl.socketAccept<BR>at java.net.PlainSocketImpl.accept<BR>at java.net.ServerSocket.implAccept<BR>at java.net.ServerSocket.accept<BR>at com.ibm.jsse.bu.accept<BR>at com.ibm.ws.http.HttpTransport.run<BR>at java.lang.Thread.run";
    static final String signatureAllThread = "1XMTHDINFO";
    static final String signatureFileName = "1TIFILENAME";
    static final String signatureMore = "4XEMORENOTSHOWN";
    static final String signatureClass = "3CLTEXTCLASS";
    static final String signatureClassLoader = "2CLTEXTCLLOAD  \t\tLoader ";
    static final String signatureCurrentHeapBase = "1STCURHBASE";
    static final String signatureCurrentHeapLimit = "1STCURHLIM";
    static final String signatureHeapLockWaiting = "3LKWAITERQ            Waiting to enter:";
    static final String signatureHeapLock = "2LKREGMON          Heap lock";
    static final String signatureSolarisWait = "- waiting to lock <";
    static final String signatureSolarisLock = "- locked <";
    static final String signatureSolarisWaitAfter = "- waiting to lock [";
    static final String signatureSolarisLockAfter = "- locked [";
    static final String signatureWaiting = "  waiting to lock monitor 0x";
    static final String signatureHeld = "  which is held by \"";
    static final String signatureLocked = "  which is locked by \"";
    static final String signalSignature = "1TISIGINFO";
    static final String waiterqSignature = "3LKWAITERQ";
    static final String waiterSignature = "3LKWAITER";
    static final String freeSignature = "1STHEAPFREE";
    static final String currentThreadSignature = "1XMCURTHDINFO";
    static final String allocatedSignature = "1STHEAPALLOC";
    static final String gcCounterSignature = "1STGCCTR";
    static final String afCounterSignature = "1STAFCTR";
    static final String timeStampSignature = "1TIDATETIME";
    static final String javaVersionSignature = "1CIJAVAVERSION";
    static final String vmVersionSignature = "1CIVMVERSION";
    static final String jitVersionSignature = "1CIJITVERSION";
    static final String gcVersionSignature = "1CIGCVERSION";
    static final String commandLineSignature = "1CICMDLINE";
    static final String javacoreSignature = "NULL           ------------------------------------------------------------------------";
    static final String javacoreSignature2 = "NULL            ---------------------------------------------------------------";
    static final String javacoreSignature3 = "0SECTION       TITLE subcomponent dump routine";
    static final String nullSignature = "NULL";
    static final String sys_threadSignature = ", sys_thread_t:";
    static final String sys_threadSignature60 = ", j9thread_t:";
    static final String threadHeaderAll = "3XMTHREADINFO";
    static final String CPUTIME = "3XMCPUTIME";
    static final String threadHeaderAlone = "3XMTHREADINFO ";
    static final String threadHeader1 = "3XMTHREADINFO1";
    static final String threadHeader2 = "3XMTHREADINFO2";
    static final String threadHeaderAnonymousNativeThread = "Anonymous native thread";
    static final String stackTraceHeader = "4XESTACKTRACE";
    static final String stackTraceHeaderSolaris = "\tat ";
    static final String stackTraceHeaderSolaris2 = "   at ";
    static final String stackTraceHeaderHPwaiting1 = "\t- waiting on <";
    static final String stackTraceHeaderHPwaiting1b = "   - waiting on <";
    static final String stackTraceHeaderHPwaiting2 = "\t- waiting to lock <";
    static final String stackTraceHeaderHPwaiting2b = "   - waiting to lock <";
    static final String stackTraceHeaderHPlocked = "\t- locked <";
    static final String stackTraceHeaderHPlocked2 = "   - locked <";
    static final String stackLineHeader = "3XHSTACKLINE";
    static final String stackLineHeader2 = "3HPSTACKLINE";
    static final String stackLineHeaderIBM6 = "4XENATIVESTACK";
    static final String threadDumpSignature = "Full thread dump";
    static final String threadSignature = " prio=";
    static final String threadSignature2 = " - Thread t@";
    static final String threadIDSignature = "\" (TID:";
    static final String threadIDSignature60Old = "\" TID:";
    static final String threadIDSignature60New = "\" J9VMThread:";
    static final String threadIDSignatureSolaris = " tid=";
    static final String lwp_id = "lwp_id=";
    static final String threadStateSignature = ", state:";
    static final String threadNativeIDSignature = ", native ID:";
    static final String threadNativeIDSignatureIBM6 = "(native thread ID:";
    static final String threadNativeIDSignatureSolaris = " nid=";
    static final String threadIDMonSignature = "2LKFLATMON         ident 0x";
    static final String threadIDMonIDSignature = "\" (0x";
    static final String threadIDMonIDSignature2 = "\" (";
    static final String monSignature = "3LKMONOBJECT";
    static final String monSignatureSystem = "2LKREGMON";
    static final String monSignature2 = "thread ident 0x";
    static final String monSignatureJ9 = "Flat locked by \"";
    static final String notifySignature0 = "3LKNOTIFYQ";
    static final String notifySignature = "3LKWAITNOTIFY";
    static final String deadlockSignatureSolaris131 = "FOUND A JAVA LEVEL DEADLOCK:";
    static final String deadlockSignatureSolaris142 = "Found one Java-level deadlock:";
    static final String deadlockSignature = "1LKDEADLOCK";
    static final String deadlockThreadSignature = "2LKDEADLOCKTHR  Thread \"";
    static final String errorSignature = "2LKERROR";
    static final String signatureClassloader = "2CLTEXTCLLOADER";
    static final String signatureNumberOfLoaded = "3CLNMBRLOADEDCL";
    static final String signatureLoader = "Loader ";
    static final String signatureParent = ", Parent ";
    static final String signatureNumberOfClasses = "Number of loaded classes";
    private static final String OOME = "OutOfMemoryError";
    private static final String cacheSummary = "1SCLTEXTCSUM";
    private static final String cacheMemoryStatus = "1SCLTEXTCMST";
    private static final String PROCESS_ID = "1CIPROCESSID";
    public File[] file = null;
    GCInfo gi = null;
    boolean isJavacore = false;
    static DecimalFormat decimatFormat = new DecimalFormat("###.##");

    public FileTask(File[] f) {
        this.file = f;
        this.lengthOfTask = 1000;
    }

    public String getStrippedStackTrace(String str) {
        if (str.indexOf("(") == -1) {
            return str;
        } else {
            String temp = "";
            byte s = 0;

            while (true) {
                int p = str.indexOf("(");
                if (p == -1) {
                    break;
                }

                int lock = str.substring(s, p).indexOf("- lock");
                if (lock == -1) {
                    lock = str.substring(s, p).indexOf("- wait");
                }

                if (lock != -1) {
                    str = str.substring(p + 1);
                } else {
                    temp = temp + str.substring(s, p);
                    str = str.substring(p + 1);
                }

                int n = str.indexOf("<BR>");
                if (n == -1) {
                    break;
                }

                str = str.substring(n);
                s = 0;
            }

            return temp;
        }
    }

    public void checkIdle(String st, int index, ThreadDump td) {
        if (st != null && st.length() != 0) {
            String temp = this.getStrippedStackTrace(st);
            if (temp.compareTo(idle5) != 0 && temp.compareTo(idle4) != 0) {
                if (temp.compareTo(kal4) != 0 && temp.compareTo(kal5) != 0 && temp.compareTo(kalSSL5) != 0 && temp.compareTo(kal04) != 0 && temp.compareTo(kal05) != 0 && temp.compareTo(kalSSL05) != 0) {
                    if (temp.compareTo(listen) != 0 && temp.compareTo(listenSSL) != 0 && temp.compareTo(listen2SSL) != 0 && temp.compareTo(listen3SSL) != 0) {
                        if (temp.compareTo(rr4) == 0 || temp.compareTo(rrSSL4) == 0 || temp.compareTo(rr5) == 0 || temp.compareTo(rrSSL5) == 0 || temp.compareTo(rr4) == 0 || temp.compareTo(rrSSL4) == 0 || temp.compareTo(rr5) == 0 || temp.compareTo(rrSSL5) == 0) {
                            td.macro[index] = 4;
                        }
                    } else {
                        td.macro[index] = 3;
                    }
                } else {
                    td.macro[index] = 2;
                }
            } else {
                td.macro[index] = 1;
            }
        }
    }

//    public String generateMonitorDetail(int sn, ThreadDump td) {
//        if (td == null || td.mdump == null) {
//            return null;
//        }
//
//        MonitorModel m = new MonitorModel(td);
//        String id = "_" + sn;
//        String stackArray = "var stacks" + id + " = new Array()\n";
//
//        for (int i = 0; (long) i < td.getTotalThread(); ++i) {
//            stackArray = stackArray + "stacks" + id + "[" + i + "]=\"" + td.getStackTrace(i) + "\"\n";
//        }
//
//        String highLight = "function highLight" + id + "(objref,state,row)\n{\nobjref.style.color = (0 == state) ? '#000000' : '#0000FF'; \n showStackTrace(stacks" + id + "[row]) \n if(state==0) hideStackTrace() \n }\n";
//        String tree = "var openImg = new Image(); \n openImg.src = \"open.gif\"; \n var closeImg = new Image(); \n closeImg.src = \"close.gif\"; \n function changeTree(disp,img) \n { \n var obj = document.getElementById(disp).style; \n if(obj.display == \"block\") \n  obj.display = \"none\"; \n else \n  obj.display = \"block\"; \t\n var Image = document.getElementById(img); \n if(Image.src.indexOf('deadlock.gif')>-1) ; \n else if(Image.src.indexOf('close.gif')>-1) \n  Image.src = openImg.src; \n else \n  Image.src = closeImg.src; \n } ";
//        String javaScript = "\n<script type=\"text/javascript\">\n" + stackArray + "\nfunction showMonStack" + id + "(rowNumber)   { \ndocument.getElementById(\"monStackColumn" + id + "\").innerHTML=stacks" + id + "[rowNumber];\n }\n" + highLight + " " + tree + " </script>\n";
//        String str = "<UL><LI>Monitor Detail : " + td.fileName + "<BR><BR><table border=\"1\"><col width=50%></col><col width=50%></col><caption align=\"bottom\">" + "Monitor Detail : " + td.fileName + "</caption><tr><th>Monitor Owner</th><th>Stack Trace</th></tr><tr><td valign=\"top\"><div class=\"parent\" onClick=\"changeTree('branch" + id + "_" + "1','folder" + id + "_" + "1')\"> \n \t<img src=\"close.gif\" id=\"folder" + id + "_" + "1\">[TotalSize/Size] ThreadName (ObjectName) " + m.getChildCount(m.getRoot()) + "</div>";
//        str = str + "<span class=\"children\" id=\"branch" + id + "_" + "1\">";
//        int seq = 2;
//        boolean pushed = false;
//        Monitor aNode = null;
//        Stack stack = new Stack();
//        for (int i = 0; i < m.getChildCount(m.getRoot()); i++) {
//            stack.push(m.getChild(m.getRoot(), i));
//        }
//
//        int n = -1;
//        while (!stack.empty()) {
//            aNode = (Monitor) stack.pop();
//            n = m.getThreadDumpIndex(aNode);
//            if (aNode.isPop()) {
//                str = str + "</span>\n";
//            } else if (m.isRecursive(aNode)) {
//                if (aNode.isDeadlock) {
//                    if (n >= 0) {
//                        str = str + "<img src=\"deadlock.gif\"><a  onmouseover=\"highLight" + id + "(this,1," + n + ")\" onmouseout=\"highLight" + id + "(this,0)\"  onclick=\"showMonStack" + id + "(" + n + ")\"> " + this.getTreeEntry(m, aNode) + "</a><br>\n";
//                    } else {
//                        str = str + "<img src=\"deadlock.gif\"> " + this.getTreeEntry(m, aNode) + "<br>\n";
//                    }
//                } else if (n >= 0) {
//                    str = str + "<img src=\"leaf.gif\"><a  onmouseover=\"highLight" + id + "(this,1," + n + ")\" onmouseout=\"highLight" + id + "(this,0)\" onclick=\"showMonStack" + id + "(" + n + ") ; showMonStack" + id + "(" + n + ")\" > " + this.getTreeEntry(m, aNode) + "</a><br>\n";
//                } else {
//                    str = str + "<img src=\"leaf.gif\"><a onclick=\"showMonStack" + id + "(" + n + ")\" >" + this.getTreeEntry(m, aNode) + "</a><br>\n";
//                }
//            } else {
//                if (m.getChildCount(aNode) == 0) {
//                    if (n >= 0) {
//                        str = str + "<img src=\"leaf.gif\"><a  onmouseover=\"highLight" + id + "(this,1," + n + ")\" onmouseout=\"highLight" + id + "(this,0)\" onclick=\"showMonStack" + id + "(" + n + ")\" > " + this.getTreeEntry(m, aNode) + "</a><br>\n";
//                    } else {
//                        str = str + "<img src=\"leaf.gif\"><a onclick=\"showMonStack" + id + "(" + n + ")\" >" + this.getTreeEntry(m, aNode) + "</a><br>\n";
//                    }
//                } else {
//                    if (aNode.isDeadlock) {
//                        if (n >= 0) {
//                            str = str + "<span class=\"parent\" onClick=\"changeTree('branch" + id + "_" + seq + "','folder" + id + "_" + seq + "')\">\n\t<img src=\"deadlock.gif\" id=\"folder" + id + "_" + seq + "\"><a  onmouseover=\"highLight" + id + "(this,1," + n + ")\" onmouseout=\"highLight" + id + "(this,0)\" onclick=\"showMonStack" + id + "(" + n + ")\" > " + this.getTreeEntry(m, aNode) + "</a><br>\n </span> \n <span class=\"children\" id=\"branch" + id + "_" + seq + "\"> \n";
//                        } else {
//                            str = str + "<span class=\"parent\" onClick=\"changeTree('branch" + id + "_" + seq + "','folder" + id + "_" + seq + "') ; showMonStack" + id + "(" + n + ")\" >\n\t<img src=\"deadlock.gif\" id=\"folder" + id + "_" + seq + "\"> " + this.getTreeEntry(m, aNode) + "<br>\n </span> \n <span class=\"children\" id=\"branch" + id + "_" + seq + "\"> \n";
//                        }
//                    } else if (n >= 0) {
//                        str = str + "<span class=\"parent\" onClick=\"changeTree('branch" + id + "_" + seq + "','folder" + id + "_" + seq + "') ; showMonStack" + id + "(" + n + ")\" >\n\t<img src=\"close.gif\" id=\"folder" + id + "_" + seq + "\"><a  onmouseover=\"highLight" + id + "(this,1," + n + ")\" onmouseout=\"highLight" + id + "(this,0)\" >  " + this.getTreeEntry(m, aNode) + "</a><br>\n </span> \n <span class=\"children\" id=\"branch" + id + "_" + seq + "\"> \n";
//                    } else {
//                        str = str + "<span class=\"parent\" onClick=\"changeTree('branch" + id + "_" + seq + "','folder" + id + "_" + seq + "') ; showMonStack" + id + "(" + n + ")\" >\n\t<img src=\"close.gif\" id=\"folder" + id + "_" + seq + "\"> " + this.getTreeEntry(m, aNode) + "<br>\n </span> \n <span class=\"children\" id=\"branch" + id + "_" + seq + "\"> \n";
//                    }
//
//                    ++seq;
//                    aNode.isPop = true;
//                    stack.push(aNode);
//                }
//
//                if (m.getChildCount(aNode) > 0) {
//                    for (int i = 0; i < m.getChildCount(aNode); ++i) {
//                        Monitor cNode = (Monitor) m.getChild(aNode, i);
//                        if (!cNode.visited) {
//                            cNode.visited = true;
//                            stack.push(cNode);
//                        }
//                    }
//                }
//            }
//        }
//        str = str + "</span></td><td id=\"monStackColumn" + id + "\" valign=\"top\" >Click on entries to display stack traces</td></tr></table>";
//        return javaScript + str + "</UL>";
//    }

//    public String generateReport(ThreadInfo ti) {
//        StringBuffer output = new StringBuffer();
//        output.append("<UL>");
//
//        for (int i = 0; i < ti.threadDumps.size(); ++i) {
//            output.append("<li><a href=\"#T" + i + "\">Java Thread Dump Individual Analysis : " + ((ThreadDump) ti.threadDumps.get(i)).fileName + "</a><br>");
//            output.append("<ul><li><a href=\"#TS" + i + "\">Thread Status Analysis</a><br>");
//            output.append("<li><a href=\"#TM" + i + "\">Thread Method Analysis</a><br>");
//            output.append("<li><a href=\"#TD" + i + "\">Thread Detail  Analysis</a><br><br></ul>");
//            output.append("<li><a href=\"#M" + i + "\">Java Monitor Dump Individual Analysis : " + ((ThreadDump) ti.threadDumps.get(i)).fileName + "</a><br><br>");
//        }
//
//        if (ti.threadDumps.size() > 1) {
//            output.append("<li><a href=\"#C\">Java Thread Dump Comparison Analysis</a>");
//            output.append("<UL><li><a href=\"#CS\">Thread Comparison Summary</a>");
//            output.append("<li><a href=\"#CD\">Thread Comparison Detail</a></UL>");
//            output.append("</UL><br><br>");
//        } else {
//            output.append("<li>Java Thread Dump Comparison Analysis : Not available for a single thread dump");
//            output.append("</UL><br><br>");
//        }
//
//        String temp = null;
//
//        for (int i = 0; i < ti.threadDumps.size(); ++i) {
//            output.append("<H2><a name=\"T" + i + "\">Java Thread Dump Individual Analysis</a></H2>");
//            output.append("<UL>");
//            output.append(((ThreadDump) ti.threadDumps.get(i)).warning);
//            output.append(((ThreadDump) ti.threadDumps.get(i)).summary);
//            output.append("</UL>");
//            output.append("<BR>");
//            output.append(this.generateThreadSummary(i, (ThreadDump) ti.threadDumps.get(i)));
//            output.append(BTT);
//            output.append("<BR>");
//            output.append(this.generateThreadDetail(i, (ThreadDump) ti.threadDumps.get(i)));
//            output.append(BTT);
//            output.append("<BR>");
//            output.append("<H2><a name=\"M" + i + "\">Java Monitor Dump Individual Analysis</a></H2>");
//            temp = this.generateMonitorDetail(i, (ThreadDump) ti.threadDumps.get(i));
//            if (temp == null) {
//                output.append("<BR>Monitor dump is not available in " + ((ThreadDump) ti.threadDumps.get(i)).fileName);
//            } else {
//                output.append(temp);
//            }
//
//            output.append("<BR>");
//            output.append(BTT);
//        }
//
//        if (ti.threadDumps.size() > 1) {
//            output.append("<H2><a name=\"C\">Java Thread Dump Comparison Analysis</a></H2>");
//            output.append(this.generateThreadComparison(ti));
//            output.append(BTT);
//        }
//
//        return output.toString();
//    }

//    public String generateThreadComparison(ThreadInfo ti) {
//        if (ti == null) {
//            return "";
//        } else if (ti.threadDumps == null) {
//            return "";
//        } else if (ti.threadDumps.size() < 2) {
//            return "";
//        } else {
//            String id = "comp";
//            ThreadDump[] td = new ThreadDump[ti.threadDumps.size()];
//
//            for (int i = 0; i < ti.threadDumps.size(); ++i) {
//                td[i] = (ThreadDump) ti.threadDumps.get(i);
//            }
//
//            CompareTableModel model = new CompareTableModel(td);
//            String stackArray = "var stacks" + id + " = [";
//
//            for (int i = 0; i < model.getRowCount(); ++i) {
//                for (int j = 1; j < model.getColumnCount(); ++j) {
//                    if (j == 1) {
//                        stackArray = stackArray + "[\"" + model.getStack(i, j - 1) + "\"";
//                    } else {
//                        stackArray = stackArray + ",\"" + model.getStack(i, j - 1) + "\"";
//                    }
//                }
//
//                stackArray = stackArray + "],\n";
//            }
//
//            stackArray = stackArray + "];\n";
//            String highLight = "function highLight" + id + "(objref,state,row,col)\n{\nobjref.style.color = (0 == state) ? '#000000' : '#0000FF';\n showStackTrace(stackscomp[row][col]); \n  if(state==0) hideStackTrace();  }\n";
//            String javaScript = "\n<script type=\"text/javascript\">\n" + stackArray + "\nfunction showStack" + id + "(rowNumber,columnNumber)   { \ndocument.getElementById(\"stackColumn" + id + "\").innerHTML=stacks" + id + "[rowNumber][columnNumber];\n }\n" + highLight + " </script>\n";
//            String str = "<UL><LI><a name=\"CS\">Thread Comparison Summary</a><BR><BR>" + this.generateThreadComparisonSummary(td) + this.hangSummary(model) + "</UL><BR><BR>" + "<LI><a name=\"CD\">Thread Comparison Detail</a><BR><BR><table border=\"1\"><col width=20%></col><col width=20%></col><col width=20%></col><col width=40%></col><caption align=\"bottom\">Thread Comparison Detail</caption><tr><th>Thread Name</th>";
//
//            for (int i = 0; i < td.length; ++i) {
//                str = str + "<th>" + td[i].fileName + "</th>";
//            }
//
//            str = str + "<th>Stack Trace</th></tr>";
//
//            for (int i = 0; i < model.getRowCount(); ++i) {
//                str = str + "<tr><td>" + model.getValueAt(i, 0) + "</font></a></td>";
//
//                for (int j = 0; j < td.length; ++j) {
//                    //tips-删除了颜色函数-采集数据不需要
////                    str = str + "<td " + this.getStateBGColor(model, i, j + 1) + "><a  style=\"cursor:hand\"  onmouseover=\"highLight" + id + "(this,1," + i + "," + j + ")\" onmouseout=\"highLight" + id + "(this,0," + i + "," + j + ")\" onclick=\"showStack" + id + "(" + i + "," + j + ")\"><font " + this.getStateColor(model, i, j + 1) + ">" + (model.getValueAt(i, j + 1) == null ? "NO THREAD" : model.getValueAt(i, j + 1)) + "</font></a></td>";
//                    str = str + "<td " + "><a  style=\"cursor:hand\"  onmouseover=\"highLight" + id + "(this,1," + i + "," + j + ")\" onmouseout=\"highLight" + id + "(this,0," + i + "," + j + ")\" onclick=\"showStack" + id + "(" + i + "," + j + ")\"><font " + ">" + (model.getValueAt(i, j + 1) == null ? "NO THREAD" : model.getValueAt(i, j + 1)) + "</font></a></td>";
//                }
//
//                if (i == 0) {
//                    str = str + "<td\t id=\"stackColumn" + id + "\" rowspan=\"" + model.getRowCount() + "\" valign=\"top\" >Click on threads to display stack traces</td></tr>";
//                } else {
//                    str = str + "</tr>";
//                }
//            }
//
//            return javaScript + str + "</table></UL>";
//        }
//    }

//    public String generateThreadComparisonSummary(ThreadDump[] td) {
//        String compareSummary = "<UL>";
//        if (td[0].pid != -1L) {
//            if (!this.isSamePID(td)) {
//                compareSummary = compareSummary + "<LI>WARNING!! Thread dumps are taken from different processes.Further analysis is not meaningful<BR><BR>";
//            } else {
//                compareSummary = compareSummary + "<LI>Process ID : " + td[0].pid + "<BR><BR>";
//            }
//        }
//
//        compareSummary = compareSummary + "<LI>List of files compared<UL><BR>";
//
//        int i;
//        for (i = 0; i < td.length; ++i) {
//            compareSummary = compareSummary + "<LI>" + td[i].fileName + "<BR>";
//        }
//
//        compareSummary = compareSummary + "</UL><BR>";
//        long endTime;
//        long startTime = endTime = td[0].timeStamp;
//        if (startTime != -1L) {
//            long endGC;
//            long startGC = endGC = td[0].gc;
//            long endAF;
//            long startAF = endAF = td[0].af;
//
//            for (i = 0; i < td.length; ++i) {
//                if (startTime > td[i].timeStamp) {
//                    startTime = td[i].timeStamp;
//                    startGC = td[i].gc;
//                    startAF = td[i].af;
//                }
//
//                if (endTime < td[i].timeStamp) {
//                    endTime = td[i].timeStamp;
//                    endGC = td[i].gc;
//                    endAF = td[i].af;
//                }
//            }
//
//            float min = (float) (endTime - startTime) / 60000.0F;
//            compareSummary = compareSummary + "<LI>First Dump : " + new Date(startTime) + "<BR><BR><LI>Last Dump : " + new Date(endTime) + "<BR><BR>";
//            if (startGC != -1L) {
//                if (min != 0.0F) {
//                    if (td[0].isJ9) {
//                        compareSummary = compareSummary + "<LI>Global Collections per Minute : " + (float) (endGC - startGC) / min + "<BR><BR><LI>Scavenge Collections per Minute : " + (float) (endAF - startAF) / min + "<BR><BR>";
//                    } else {
//                        compareSummary = compareSummary + "<LI>Garbage Collections per Minute : " + (float) (endGC - startGC) / min + "<BR><BR><LI>Allocation Failures per Minute : " + (float) (endAF - startAF) / min + "<BR><BR>";
//                    }
//                }
//
//                long t = (endTime - startTime) / 1000L;
//                long s = t % 60L;
//                long m = t / 60L % 60L;
//                long h = t / 60L / 60L % 24L;
//                long d = t / 60L / 60L / 24L;
//                if (t != 0L && s + m + h + d != 0L) {
//                    compareSummary = compareSummary + "<LI>Elapsed Time : " + (d == 0L ? "" : d + " Day(s) ") + (h == 0L ? "" : h + " Hour(s) ") + (m == 0L ? "" : m + " Minute(s) ") + (s == 0L ? "" : s + " Second(s)" + "<BR><BR>");
//                }
//            }
//        }
//
//        return compareSummary;
//    }

//    public String generateThreadDetail(int sn, ThreadDump td) {
//        String id = "_" + sn;
//        String stackArray = "var stacks" + id + " = new Array()\n";
//
//        for (int i = 0; (long) i < td.getTotalThread(); ++i) {
//            stackArray = stackArray + "stacks" + id + "[" + i + "]=\"" + td.getStackTrace(i) + "\"\n";
//        }
//
//        String highLight = "function highLight" + id + "(objref,state,row)\n{\nobjref.style.color = (0 == state) ? '#000000' : '#0000FF'; \n showStackTrace(stacks" + id + "[row]) \n if(state==0) hideStackTrace() \n }\n";
//        String javaScript = "\n<script type=\"text/javascript\">\n" + stackArray + "\nfunction showStack" + id + "(rowNumber)   { \ndocument.getElementById(\"stackColumn" + id + "\").innerHTML=stacks" + id + "[rowNumber];\n }\n" + highLight + " </script>\n";
//        String str = "<UL><LI><a name=\"TD" + sn + "\">Thread Detail : " + td.fileName + "</a><BR><BR><table border=\"1\"><col width=20%></col><col width=20%></col><col width=30%></col><col width=30%></col><caption align=\"bottom\">" + "Thread Detail : " + td.fileName + "</caption><tr><th>Name</th><th>State</th><th>Method</th><th>Stack Trace</th></tr>";
//        //tips-去除颜色函数
////        str = str + "<tr><td><a style=\"cursor:hand\" onmouseover=\"highLight" + id + "(this,1,0)\" onmouseout=\"highLight" + id + "(this,0)\" onclick=\"showStack" + id + "(0)\">" + td.getName(0) + "</a></td><td " + this.getStateColor(td, 0) + "<a style=\"cursor:hand\" onmouseover=\"highLight" + id + "(this,1,0)\" onmouseout=\"highLight" + id + "(this,0)\" onclick=\"showStack" + id + "(0)\">" + td.getState(0) + "</a></td><td><a style=\"cursor:hand\" onmouseover=\"highLight" + id + "(this,1,0)\" onmouseout=\"highLight" + id + "(this,0)\" onclick=\"showStack" + id + "(0)\">" + td.getCurrentMethod(0) + "</a></td><td\t id=\"stackColumn" + id + "\" rowspan=\"" + td.getTotalThread() + "\" valign=\"top\" >Click on threads to display stack traces</td></tr>";
//        str = str + "<tr><td><a style=\"cursor:hand\" onmouseover=\"highLight" + id + "(this,1,0)\" onmouseout=\"highLight" + id + "(this,0)\" onclick=\"showStack" + id + "(0)\">" + td.getName(0) + "</a></td><td " + "<a style=\"cursor:hand\" onmouseover=\"highLight" + id + "(this,1,0)\" onmouseout=\"highLight" + id + "(this,0)\" onclick=\"showStack" + id + "(0)\">" + td.getState(0) + "</a></td><td><a style=\"cursor:hand\" onmouseover=\"highLight" + id + "(this,1,0)\" onmouseout=\"highLight" + id + "(this,0)\" onclick=\"showStack" + id + "(0)\">" + td.getCurrentMethod(0) + "</a></td><td\t id=\"stackColumn" + id + "\" rowspan=\"" + td.getTotalThread() + "\" valign=\"top\" >Click on threads to display stack traces</td></tr>";
//
//        for (int i = 1; (long) i < td.getTotalThread(); ++i) {
//            //tips-去除颜色函数
////            str = str + "<tr><td><a style=\"cursor:hand\"  onmouseover=\"highLight" + id + "(this,1," + i + ")\" onmouseout=\"highLight" + id + "(this,0)\" onclick=\"showStack" + id + "(" + i + ")\">" + td.getName(i) + "</a></td><td " + this.getStateColor(td, i) + " ><a  style=\"cursor:hand\"  onmouseover=\"highLight" + id + "(this,1," + i + ")\" onmouseout=\"highLight" + id + "(this,0)\" onclick=\"showStack" + id + "(" + i + ")\">" + td.getState(i) + "</a></td><td><a style=\"cursor:hand\"  onmouseover=\"highLight" + id + "(this,1," + i + ")\" onmouseout=\"highLight" + id + "(this,0)\" onclick=\"showStack" + id + "(" + i + ")\">" + td.getCurrentMethod(i) + "</a></td></tr>";
//            str = str + "<tr><td><a style=\"cursor:hand\"  onmouseover=\"highLight" + id + "(this,1," + i + ")\" onmouseout=\"highLight" + id + "(this,0)\" onclick=\"showStack" + id + "(" + i + ")\">" + td.getName(i) + "</a></td><td " + " ><a  style=\"cursor:hand\"  onmouseover=\"highLight" + id + "(this,1," + i + ")\" onmouseout=\"highLight" + id + "(this,0)\" onclick=\"showStack" + id + "(" + i + ")\">" + td.getState(i) + "</a></td><td><a style=\"cursor:hand\"  onmouseover=\"highLight" + id + "(this,1," + i + ")\" onmouseout=\"highLight" + id + "(this,0)\" onclick=\"showStack" + id + "(" + i + ")\">" + td.getCurrentMethod(i) + "</a></td></tr>";
//        }
//
//        return javaScript + str + "</table></UL>";
//    }

//    public String generateThreadSummary(int sn, ThreadDump td) {
//        if (td != null) {
//            long total = td.getTotalThread();
//            long runnable = td.getRunnable();
//            long condition = td.getWCondition();
//            long monitor = td.getWMonitor();
//            long suspended = td.getSuspended();
//            long wait = td.getOWait();
//            long blocked = td.getBlocked();
//            long parked = td.getParked();
//            //tips-去除颜色函数
////            String summary = "<UL><LI><a name=\"TS" + sn + "\">Thread Status Analysis</a><BR><BR><table border=\"1\"><tr><th>Status</th><th>Number of Threads : " + total + "</th><th>Percentage</th></tr><tr><td bgcolor=\"#" + this.getHTMLColor(this.cfg == null ? Analyzer.runnable : Analyzer.runnable) + "\">Runnable</td><td>" + runnable + "</td><td>" + Math.round((float) runnable * 100.0F / (float) total) + " (%)</td></tr><tr><td bgcolor=\"#" + this.getHTMLColor(this.cfg == null ? Analyzer.condition : Analyzer.condition) + "\">Waiting on condition</td><td>" + condition + "</td><td>" + Math.round((float) condition * 100.0F / (float) total) + " (%)</td></tr><tr><td bgcolor=\"#" + this.getHTMLColor(this.cfg == null ? Analyzer.monitor : Analyzer.monitor) + "\">Waiting on monitor</td><td>" + monitor + "</td><td>" + Math.round((float) monitor * 100.0F / (float) total) + " (%)</td></tr><tr><td bgcolor=\"#" + this.getHTMLColor(this.cfg == null ? Analyzer.suspended : Analyzer.suspended) + "\">Suspended</td><td>" + suspended + "</td><td>" + Math.round((float) suspended * 100.0F / (float) total) + " (%)</td></tr><tr><td bgcolor=\"#" + this.getHTMLColor(this.cfg == null ? Analyzer.object : Analyzer.object) + "\">Object.wait()</td><td>" + wait + "</td><td>" + Math.round((float) wait * 100.0F / (float) total) + " (%)</td></tr><tr><td bgcolor=\"#" + this.getHTMLColor(this.cfg == null ? Analyzer.blocked : Analyzer.blocked) + "\">Blocked</td><td>" + blocked + "</td><td>" + Math.round((float) blocked * 100.0F / (float) total) + " (%)</td></tr><tr><td bgcolor=\"#" + this.getHTMLColor(this.cfg == null ? Analyzer.park : Analyzer.park) + "\">Parked</td><td>" + parked + "</td><td>" + Math.round((float) parked * 100.0F / (float) total) + " (%)</td></tr></table></UL>";
//            String summary = "<UL><LI><a name=\"TS" + sn + "\">Thread Status Analysis</a><BR><BR><table border=\"1\"><tr><th>Status</th><th>Number of Threads : " + total + "</th><th>Percentage</th></tr><tr><td bgcolor=\"#" + "\">Runnable</td><td>" + runnable + "</td><td>" + Math.round((float) runnable * 100.0F / (float) total) + " (%)</td></tr><tr><td " + ">Waiting on condition</td><td>" + condition + "</td><td>" + Math.round((float) condition * 100.0F / (float) total) + " (%)</td></tr><tr><td " + ">Waiting on monitor</td><td>" + monitor + "</td><td>" + Math.round((float) monitor * 100.0F / (float) total) + " (%)</td></tr><tr><td " + ">Suspended</td><td>" + suspended + "</td><td>" + Math.round((float) suspended * 100.0F / (float) total) + " (%)</td></tr><tr><td " + ">Object.wait()</td><td>" + wait + "</td><td>" + Math.round((float) wait * 100.0F / (float) total) + " (%)</td></tr><tr><td " + ">Blocked</td><td>" + blocked + "</td><td>" + Math.round((float) blocked * 100.0F / (float) total) + " (%)</td></tr><tr><td" + ">Parked</td><td>" + parked + "</td><td>" + Math.round((float) parked * 100.0F / (float) total) + " (%)</td></tr></table></UL>";
//            return summary + "<br>" + td.getMethodSummary(sn);
//        } else {
//            return "";
//        }
//    }

//    public String getAddress(String str) {
//        if (str != null && str.length() != 0) {
//            int open = str.indexOf("(");
//            int close = str.indexOf(")");
//            if (open != -1 && close != -1) {
//                long value = Long.decode(str.substring(open + 1, close));
//                System.out.println("value:" + value);
//                return str.substring(open + 1, close);
//            } else {
//                return null;
//            }
//        } else {
//            return null;
//        }
//    }
//
//    long getCompleted(int idx) {
//        if (this.gi.completed[idx] != 0L) {
//            return this.gi.completed[idx];
//        } else if (this.gi.gccompleted[idx] == 0L) {
//            return 0L;
//        } else {
//            for (int i = idx; i < this.gi.free.length; ++i) {
//                if (this.gi.completed[i] != 0L) {
//                    return this.gi.completed[i];
//                }
//            }
//
//            return 0L;
//        }
//    }
//
//    public int getCurrent() {
//        return this.current;
//    }
//
//    public int getLengthOfTask() {
//        return this.lengthOfTask;
//    }
//
//    public String getMessage() {
//        return this.statMessage;
//    }
//
//    public String getName(String str) {
//        if (str != null && str.length() != 0) {
//            int open = str.indexOf("(");
//            return open == -1 ? null : str.substring(0, open);
//        } else {
//            return null;
//        }
//    }
//
//    public int getNumberOfChar(String str, char c) {
//        int count = 0;
//        for (int newIndex = 0; (newIndex = str.indexOf(c)) >= 0; ) {
//            count++;
//            if (newIndex >= str.length())
//                break;
//            str = str.substring(newIndex + 1);
//        }
//
//        return count;
//    }
//
//    public int getOverall() {
//        return this.overall;
//    }

    private long getPid(String str) {
        long pid = -1L;
        int i = str.lastIndexOf("JAVADUMP");
        if (i != -1) {
            int j = str.lastIndexOf(".txt");
            if (j != -1) {
                int k = str.substring(0, j - 1).lastIndexOf(".");
                if (k != -1) {
                    try {
                        pid = Long.parseLong(str.substring(k + 1, j).trim(), 10);
                    } catch (Exception var8) {
                        //todo-ha
//                        this.ha.handleException(var8);
                    }

                    return pid;
                }
            }
        } else {
            String[] split = str.split("\\.");
            if (split.length > 3) {
                try {
                    long v = Long.parseLong(split[split.length - 3]);
                    return v;
                } catch (Exception var9) {
                }
            }

            System.out.println(split[split.length - 3]);
        }

        return -1L;
    }

//    long getRequested(int idx) {
//        if (this.gi.af[idx] != 0L) {
//            return this.gi.af[idx];
//        } else {
//            for (int i = idx; i >= 0; --i) {
//                if (this.gi.af[i] != 0L) {
//                    return this.gi.af[i];
//                }
//            }
//
//            return 0L;
//        }
//    }

//    long getSince(int idx) {
//        if (this.gi.since[idx] != 0L) {
//            return this.gi.since[idx];
//        } else {
//            for (int i = idx; i >= 0; --i) {
//                if (this.gi.since[i] != 0L && this.gi.af[i] != 0L) {
//                    return this.gi.since[i];
//                }
//            }
//
//            return 0L;
//        }
//    }

//    public String getTreeEntry(MonitorModel model, Object value) {
//        int id = ((Monitor) value).owner;
//        if (id == -1)
//            return (new StringBuilder("[TotalSize/Size] ThreadName (ObjectName) ")).append(nf.format(model.rootChildren.length)).toString();
//        if (model.child[id] != null)
//            if (model.objectName[id] == -1)
//                return (new StringBuilder("[")).append(nf.format(model.total[id])).append("/").append(nf.format(model.size[id])).append("] ").append(model.getThreadName(id)).toString();
//            else
//                return (new StringBuilder("[")).append(nf.format(model.total[id])).append("/").append(nf.format(model.size[id])).append("] ").append(model.getThreadName(id)).append(" (").append(model.objectArray[model.objectName[id]]).append(")").toString();
//        if (model.objectName[id] >= 0)
//            return (new StringBuilder(String.valueOf(model.getThreadName(id)))).append(" (").append(model.objectArray[model.objectName[id]]).append(")").toString();
//        else
//            return model.getThreadName(id);
//    }

//    String getTrend(int x, int y) {
//        String ts = "between " + formatter.format(new Date(this.gi.timestamp[x])) + " and " + formatter.format(new Date(this.gi.timestamp[y]));
//        float alpha = 0.9F;
//        float st_1 = (float) (this.gi.total[x] - this.gi.free[x]);
//        float st1 = 0.0F;
//        float st2 = 0.0F;
//        float st2_1 = (float) (this.gi.total[x] - this.gi.free[x]);
//        float mape = 0.0F;
//        long maxUsed = 0L;
//
//        for (int i = x + 1; i <= y; ++i) {
//            if (maxUsed < this.gi.total[i] - this.gi.free[i]) {
//                maxUsed = this.gi.total[i] - this.gi.free[i];
//            }
//
//            st1 = alpha * (float) (this.gi.total[i] - this.gi.free[i]) + (1.0F - alpha) * st_1;
//            st2 = alpha * st1 + (1.0F - alpha) * st2_1;
//            st_1 = st1;
//            st2_1 = st2;
//            mape += Math.abs((float) (this.gi.total[i] - this.gi.free[i]) - st2) / (float) (this.gi.total[i] - this.gi.free[i]);
//        }
//
//        float doub = 2.0F * st_1 - st2_1 + alpha / (1.0F - alpha) * (st_1 - st2_1);
//        if ((double) (mape / (float) (y - x)) > 0.5D) {
//            return "Trend model not reliable (percentage error is larger than 50%) " + ts;
//        } else {
//            float number = doub;
//            long max = 0L;
//
//            for (int i = 0; i < y - x + 1; ++i) {
//                st1 = alpha * number + (1.0F - alpha) * st_1;
//                st2 = alpha * st1 + (1.0F - alpha) * st2_1;
//                st_1 = st1;
//                st2_1 = st2;
//                number = 2.0F * st1 - st2 + alpha / (1.0F - alpha) * (st1 - st2);
//                if (number < 1.0F) {
//                    return "Trend ratio(%): 0 (Recommended max heap size of " + numberFormatter.format((double) (1.3F * (float) maxUsed)) + " or greater) with percentage error(%): " + 100.0F * mape / (float) (y - x) + " " + ts;
//                }
//
//                if (number > 4.0E9F) {
//                    return "Trend ratio(%): over 100 with percentage error(%): " + 100.0F * mape / (float) (y - x) + " " + ts;
//                }
//
//                if (number > (float) max) {
//                    max = (long) number;
//                }
//            }
//
//            if (max < this.gi.total[y] - this.gi.free[y]) {
//                return "Trend ratio(%): " + 100.0F * (float) (max - (this.gi.total[y] - this.gi.free[y])) / (float) (this.gi.total[y] - this.gi.free[y]) + " (Recommended max heap size of " + numberFormatter.format((double) (1.3F * (float) maxUsed)) + " or greater) with percentage error(%): " + 100.0F * mape / (float) (y - x) + " " + ts;
//            } else {
//                return "Trend ratio(%): " + 100.0F * (float) (max - (this.gi.total[y] - this.gi.free[y])) / (float) (this.gi.total[y] - this.gi.free[y]) + " with percentage error(%): " + 100.0F * mape / (float) (y - x) + " " + ts;
//            }
//        }
//    }
//
//    public long getXmx(String str) {
//        if (str == null) {
//            return -1L;
//        } else {
//            int g = str.indexOf(103);
//            if (g < 0) {
//                g = str.indexOf(71);
//            }
//
//            long output = 0L;
//            String num = null;
//            if (g < 0) {
//                int m = str.indexOf(109);
//                if (m < 0) {
//                    m = str.indexOf(77);
//                }
//
//                if (m < 0) {
//                    int k = str.indexOf(107);
//                    if (k < 0) {
//                        k = str.indexOf(75);
//                    }
//
//                    if (k < 0) {
//                        output = Long.parseLong(str);
//                        return output;
//                    } else {
//                        num = str.substring(0, k);
//                        if (num == null) {
//                            return -1L;
//                        } else {
//                            output = Long.parseLong(num);
//                            return output * 1024L;
//                        }
//                    }
//                } else {
//                    num = str.substring(0, m);
//                    if (num == null) {
//                        return -1L;
//                    } else {
//                        output = Long.parseLong(num);
//                        return output * 1024L * 1024L;
//                    }
//                }
//            } else {
//                num = str.substring(0, g);
//                if (num == null) {
//                    return -1L;
//                } else {
//                    output = Long.parseLong(num);
//                    return output * 1024L * 1024L * 1024L;
//                }
//            }
//        }
//    }

    public void go() {
        numberFormatter.setMaximumFractionDigits(2);
        this.processThreadDump(this.file);
    }

//    private String hangSummary(CompareTableModel cTableModel) {
//        String id = "comp";
//        String summary = "";
//        List threadNameList = Collections.synchronizedList(new ArrayList());
//        if (cTableModel != null && cTableModel.hi != null && cTableModel.hi.length != 1) {
//            long numberOfHung = 0L;
//            ThreadDump[] hi = cTableModel.hi;
//            String[] threads = new String[cTableModel.tidHash.size()];
//            boolean[] isMoving = new boolean[cTableModel.tidHash.size()];
//
//            for (int i = 0; i < cTableModel.tidHash.size(); ++i) {
//                Long tid = (Long) cTableModel.tidHash.get(new Integer(i));
//                Integer idx = (Integer) hi[0].threadHash.get(tid);
//                if (idx == null) {
//                    isMoving[i] = true;
//                } else {
//                    threads[i] = hi[0].javaStack[idx];
//                    if (threads[i] == null) {
//                        isMoving[i] = true;
//                    } else {
//                        int j = 1;
//
//                        while (j < hi.length) {
//                            idx = (Integer) hi[j].threadHash.get(tid);
//                            if (idx != null) {
//                                if (hi[j].javaStack[idx] != null && threads[i].compareTo(hi[j].javaStack[idx]) == 0) {
//                                    ++j;
//                                    continue;
//                                }
//
//                                isMoving[i] = true;
//                                break;
//                            }
//
//                            isMoving[i] = true;
//                            break;
//                        }
//
//                        idx = (Integer) hi[0].threadHash.get(tid);
//                        if (!isMoving[i] && idx != null && hi[0].macro[idx] != 1) {
//                            threadNameList.add(hi[0].name[idx]);
//                        }
//                    }
//                }
//            }
//
//            if (threadNameList.size() != 0) {
//                String[] nameString = (String[]) threadNameList.toArray(new String[threadNameList.size()]);
//                Arrays.sort(nameString);
//                String highLight = "function highLightHang" + id + "(objref,state,row,col)\n{ \n objref.style.color = (0 == state) ? '#000000' : '#0000FF';\n showStackTrace(stackscomp[row][col]); \n  if(state==0) hideStackTrace();  }\n";
//                String javaScript = "\n<script type=\"text/javascript\">\n function showStackHang" + id + "(rowNumber,columnNumber)   { \ndocument.getElementById(\"stackColumnHang" + id + "\").innerHTML=stacks" + id + "[rowNumber][columnNumber];\n }\n" + highLight + " </script>\n";
//                summary = "<LI>Number of hang suspects : " + nameString.length + "<BR><BR><LI>List of hang suspects:<BR><BR><UL><table border=\"1\"><col width=20%></col><col width=20%></col><col width=20%></col><col width=40%></col><caption align=\"bottom\">Hang suspects</caption><tr><th>Thread Name</th>";
//                summary = summary + "<th>Method</th><th>Stack Trace</th></tr>";
//
//                for (int i = 0; i < nameString.length; ++i) {
//                    for (int j = 0; j < cTableModel.getRowCount(); ++j) {
//                        if (nameString[i].compareTo((String) cTableModel.getValueAt(j, 0)) == 0) {
//                            summary = summary + "<tr><td>" + cTableModel.getValueAt(j, 0) + "</font></a></td>";
//                            //tips-去除颜色函数
//                            summary = summary + "<td " + "><a  style=\"cursor:hand\"  onmouseover=\"highLightHang" + id + "(this,1," + j + ",0)\" onmouseout=\"highLightHang" + id + "(this,0," + j + ",0)\" onclick=\"showStackHang" + id + "(" + j + ",0)\"><font " + ">" + (cTableModel.getValueAt(j, 1) == null ? "NO THREAD" : cTableModel.getValueAt(j, 1)) + "</font></a></td>";
////                            summary = summary + "<td " + this.getStateBGColor(cTableModel, j, 1) + "><a  style=\"cursor:hand\"  onmouseover=\"highLightHang" + id + "(this,1," + j + ",0)\" onmouseout=\"highLightHang" + id + "(this,0," + j + ",0)\" onclick=\"showStackHang" + id + "(" + j + ",0)\"><font " + this.getStateColor(cTableModel, j, 1) + ">" + (cTableModel.getValueAt(j, 1) == null ? "NO THREAD" : cTableModel.getValueAt(j, 1)) + "</font></a></td>";
//                            break;
//                        }
//                    }
//
//                    if (i == 0) {
//                        summary = summary + "<td id=\"stackColumnHang" + id + "\" rowspan=\"" + cTableModel.getRowCount() + "\" valign=\"top\" >Click on threads to display stack traces</td></tr>";
//                    } else {
//                        summary = summary + "</tr>";
//                    }
//                }
//
//                summary = javaScript + summary + "</table></UL>";
//            } else {
//                summary = "<li>No hang suspects found.</br>";
//            }
//        }
//
//        return summary;
//    }

//    boolean hasOOM(int x, int y) {
//        if (this.gi.outOfHeapSpace == null) {
//            return false;
//        } else {
//            for (int i = 0; i < this.gi.outOfHeapSpace.length; ++i) {
//                if (x <= this.gi.outOfHeapSpace[i] && y >= this.gi.outOfHeapSpace[i]) {
//                    return true;
//                }
//            }
//
//            return false;
//        }
//    }

    public int indexOfContent(String str) {
        int idx = -1;
        int start = str.indexOf(" ");
        if (start >= 0) {
            while (str.length() > start && str.charAt(start) == ' ') {
                ++start;
                idx = start;
            }
        }

        return idx;
    }

//    public boolean isDone() {
//        return this.done;
//    }
//
//    boolean isFragmented(int idx) {
//        if (this.gi.free[idx] < 1000000L) {
//            return false;
//        } else if ((double) ((float) this.gi.free[idx] / (float) this.gi.total[idx]) > 0.1D) {
//            return true;
//        } else {
//            return (double) ((float) this.getRequested(idx) / (float) this.gi.free[idx]) < 0.05D;
//        }
//    }
//
//    private boolean isSamePID(ThreadDump[] td) {
//        long pid = td[0].pid;
//
//        for (int i = 1; i < td.length; ++i) {
//            if (pid != td[i].pid) {
//                return false;
//            }
//        }
//
//        return true;
//    }
//
//    boolean isStartPoint(int idx) {
//        if (idx < this.gi.free.length && idx >= 0) {
//            if (idx == 0) {
//                return true;
//            } else {
//                return this.gi.ngc[idx - 1] >= this.gi.ngc[idx];
//            }
//        } else {
//            return false;
//        }
//    }
//
//    boolean isTooLarge(int idx) {
//        if (this.getRequested(idx) < SIZE_OF_LARGE_OBJECT) {
//            return false;
//        } else {
//            return (double) ((float) this.getRequested(idx) / (float) this.gi.free[idx]) > 0.1D;
//        }
//    }

    public static long jinwooDecode(String ad) throws NumberFormatException {
        if (ad == null) {
            throw new NumberFormatException();
        } else {
            int max = ad.length();
            if (max == 18) {
                int firstDigit = Character.digit(ad.charAt(2), 16);
                if (firstDigit == -1)
                    return -1L;
                if (firstDigit >= 8) {
                    int digit = 0;
                    long result = 0L;
                    int i = 2;
                    int b = 0;
                    while (i < max) {
                        digit = Character.digit(ad.charAt(i++), 16);
                        b = (byte) digit;
                        b = (byte) (b | 0xfffffff0);
                        b = (byte) (~b);
                        result <<= 4;
                        result += b;
                    }
                    result++;
                    return -1L * result;
                }
            }

            int digit = 0;
            long result = 0L;
            int i = 2;

            while (i < max) {
                digit = Character.digit(ad.charAt(i++), 16);
                if (digit == -1)
                    return -1L;
                result <<= 4;
                result += digit;
            }

            return result;
        }
    }

    public String convertToMG(long n) {
        float result = (float) n;
        String unit = "bytes";
        if (result >= 1.07374182E9F) {
            result /= 1.07374182E9F;
            unit = "GB";
        } else if (result >= 1048576.0F) {
            result /= 1048576.0F;
            unit = "MB";
        } else if (result >= 1024.0F) {
            result /= 1024.0F;
            unit = "KB";
        }

        return decimatFormat.format((double) result) + " " + unit;
    }

    private boolean isValidThread(String firstLine, ThreadDump dump) {
        if (firstLine.contains("<name unavailable>NULL")) {
            if (dump != null) {
                dump.warning = dump.warning + "<LI style=\"color:red\"><b>***WARNING*** Stack walk error: " + this.replaceEntities(firstLine) + "<BR><BR>";
            }

            return false;
        } else {
            return true;
        }
    }

    public String processThreadDump(File[] file) {
        ThreadInfo threadInfo = null;
        threadInfo = new ThreadInfo();

        Date dt = new Date();
        ParsePosition pos = new ParsePosition(0);
        String commandLine = new String();
        long tmpL = 0L;
        long numberOfClasses = 0L;
        long numberOfClassLoader = 0L;
        long heapLimit = -1L;
        long heapBase = -1L;
//        int pinIdx = 0;
//        long dosed = 0L;
//        long pinned = 0L;
//        long classes = 0L;
//        long maxClass = 0L;
//        long maxClassTS = 0L;
//        long maxPinned = 0L;
//        long maxPinnedTS = 0L;
//        long maxDosed = 0L;
//        long maxDosedTS = 0L;
//        long pCluster = 0L;
        Boolean found = null;
        long fileLocation = 0L;
        this.gi = new GCInfo();
        Vector rv = new Vector(10, 100);
//        int index = 0;
//        int totalNames = 0;
//        long totalNodes = 0L;
        String line = new String();
        Vector cv = new Vector(1);
//        int result = 0;
//        int progress = 0;
//        int p = 0;
//        int i = 0;
//        int j = 0;
//        int waitIndex = 0;
//        int k = 0;
//        int gcNumber = 0;
//        int numberOfSet = 1;
        int totalThread = 0;
        int totalThreadDump = -1;
//        int nextGcNumber = 0;
//        int outOfHeapSpace = 0;
//        HashSet threadLine;

        BufferedReaderWrapper in;
//        int parentloader;
//        int dumpIndex;
        for (int z = 0; z < file.length; ++z) {
            if (this.debug) {
                System.out.println(file[z].getAbsolutePath());
            }

            boolean canCount = false;
            fileLocation = 0L;
            this.monIndex = 0;
            numberOfClassLoader = 0L;
            numberOfClasses = 0L;
            this.monList = new ArrayList();
            this.threadIdent = new Hashtable();
//            progress = 0;
            this.overall = 0;
            this.statMessage = "Loading thread dump";
//            gcNumber = 0;
//            numberOfSet = 1;
//            nextGcNumber = 0;
//            outOfHeapSpace = 0;
            ArrayList list = new ArrayList();
            in = null;

            long fileSize;
//            String line;
            try {
                if (!file[z].exists()) {
                    //todo-记录文件不存在
//                    JOptionPane.showMessageDialog(this.ha, "File not found:" + file[z].getAbsoluteFile(), "File Open Error", 2);
                    continue;
                }

                fileSize = file[z].length();
                in = new BufferedReaderWrapper(file[z]);
                line = in.readLine();
                if (line != null) {
                    fileLocation += (long) line.length();
                    if (line.startsWith(javacoreSignature) || line.startsWith(javacoreSignature2) || line.startsWith(javacoreSignature3)) {
                        this.isJavacore = true;
                    }
                }

                if (this.isJavacore) {
                    HashSet threadLine = new HashSet();

                    while (true) {
                        if (line == null) {
                            threadLine.clear();
                            break;
                        }

                        if (!line.startsWith(currentThreadSignature) && !line.startsWith(signatureAllThread)) {
                            if (line.startsWith(threadHeaderAlone) && !line.contains(threadHeaderAnonymousNativeThread) && this.isValidThread(line, (ThreadDump) null)) {
                                if (canCount && !threadLine.contains(line)) {
                                    if (this.debug) {
                                        System.out.println("Thread: " + line);
                                    }

                                    threadLine.add(line);
                                    ++totalThread;
                                }
                            } else if (line.startsWith(signatureClassloader)) {
                                int loader = line.indexOf(signatureLoader);
                                int parentloader = line.indexOf(signatureParent);
                                int comma = line.indexOf(",");
                                if (loader != -1 && comma != -1) {
                                    line.substring(loader + signatureLoader.length(), comma);
                                    if (parentloader != -1) {
                                        line.substring(parentloader + signatureParent.length());
                                    }
                                }

                                line = in.readLine();
                                if (line != null && line.startsWith(signatureNumberOfLoaded)) {
                                    int number = line.indexOf(signatureNumberOfClasses);
                                    if (number != -1) {
                                        line.substring(number + signatureNumberOfClasses.length());
                                    }
                                }
                            } else if (line.startsWith(signatureCurrentHeapBase)) {
                                int i = line.indexOf(":");
                                if (i != -1) {
                                    int j = line.indexOf("0x");
                                    if (j == -1) {
                                        heapBase = jinwooDecode("0x" + line.substring(i + 1).trim());
                                    } else {
                                        heapBase = jinwooDecode(line.substring(j).trim());
                                    }
                                }
                            } else if (line.startsWith(signatureCurrentHeapLimit)) {
                                int i = line.indexOf(":");
                                if (i != -1) {
                                    int j = line.indexOf("0x");
                                    if (j == -1) {
                                        heapLimit = jinwooDecode("0x" + line.substring(i + 1).trim());
                                    } else {
                                        heapLimit = jinwooDecode(line.substring(j).trim());
                                    }
                                }
                            } else if (line.startsWith(signatureClass)) {
                                int i = line.lastIndexOf("(0x");
                                int j = line.lastIndexOf(")");
                                if (i != -1 && j != -1 && heapBase == -1L) {
                                    ++numberOfClasses;
                                } else if (i != -1 && j != -1 && heapBase != -1L && heapLimit != -1L) {
                                    tmpL = jinwooDecode(line.substring(i + 1, j));
                                    if (heapBase != heapLimit && tmpL >= heapBase && tmpL <= heapLimit) {
                                        ++numberOfClasses;
                                    }
                                }
                            } else if (line.startsWith(signatureClassLoader)) {
                                ++numberOfClassLoader;
                            }
                        } else {
                            canCount = true;
                        }

                        line = in.readLine();
                        if (line != null) {
                            fileLocation += (long) line.length();
                            if (line.startsWith(threadIDMonSignature)) {
                                int i = line.indexOf(" \"");
                                if (i != -1) {
                                    Long key = new Long(jinwooDecode("0x" + line.substring(threadIDMonSignature.length(), i)));
                                    i = line.lastIndexOf(threadIDMonIDSignature);
                                    Long value;
                                    if (i != -1) {
                                        int j = line.lastIndexOf(")");
                                        if (j != -1) {
                                            value = new Long(jinwooDecode("0x" + line.substring(i + threadIDMonIDSignature.length(), j)));
                                            if (this.debug) {
                                                System.out.println("Mapping monitor " + key + " = " + value);
                                            }

                                            this.threadIdent.put(key, value);
                                        }
                                    } else {
                                        i = line.lastIndexOf(threadIDMonIDSignature2);
                                        if (i != -1) {
                                            int j = line.lastIndexOf(")");
                                            if (j != -1) {
                                                value = new Long(jinwooDecode("0x" + line.substring(i + threadIDMonIDSignature2.length(), j)));
                                                if (this.debug) {
                                                    System.out.println("Mapping monitor " + key + " = " + value);
                                                }

                                                this.threadIdent.put(key, value);
                                            }
                                        }
                                    }
                                }
                            }
                        }

                        this.current = (int) (100.0F * (float) fileLocation / (float) fileSize);
                        this.overall = (int) (30.0F * (float) fileLocation / (float) fileSize);
                    }
                } else {
                    for (; line != null; this.overall = (int) (30.0F * (float) fileLocation / (float) fileSize)) {
                        if (line.indexOf(threadDumpSignature) >= 0) {
                            ++totalThreadDump;
                            list.add(totalThreadDump, new Integer(0));
                        } else {
                            int tempI;
                            if (line.startsWith("\"") && line.indexOf(threadSignature) >= 0) {
                                tempI = (Integer) list.get(totalThreadDump);
                                list.set(totalThreadDump, new Integer(tempI + 1));
                                ++totalThread;
                            } else if (line.startsWith("\"") && line.indexOf(threadSignature2) >= 0) {
                                tempI = (Integer) list.get(totalThreadDump);
                                list.set(totalThreadDump, new Integer(tempI + 1));
                                ++totalThread;
                            }
                        }

                        line = in.readLine();
                        if (line != null) {
                            fileLocation += (long) line.length();
                        }

                        this.current = (int) (100.0F * (float) fileLocation / (float) fileSize);
                    }
                }

                in.close();
            } catch (Exception var135) {
                System.out.println(in.getLastLineExceptionDiagnostics(var135));
                continue;
            }

            if (totalThread == 0) {
            } else {
                long totalLine = in.getLinesRead();
                fileLocation = 0L;
                String oldLine = null;

                try {
                    in = new BufferedReaderWrapper(file[z]);
                    line = in.readLine();
                    if (line != null) {
                        fileLocation += (long) line.length();
                    }

                    ThreadDump td = null;
                    int dumpIndex = 0;
//                    String[] var10000;
//                    int var10002;
//                    String name;
                    String[] tokens;
//                    String tempS;
                    boolean inArgs;
                    if (this.isJavacore) {
                        HashSet threadLine = new HashSet();
                        td = new ThreadDump();
                        td.xmx = -1L;
                        td.isIBM = true;
                        td.javaHeap = -1;
                        td.fileName = file[z].getName();
                        td.name = new String[totalThread];
                        td.pattern = new String[totalThread];
                        td.isDeadlock = new boolean[totalThread];
                        td.nid = new long[totalThread];
                        td.state = new int[totalThread];
                        td.priority = new int[totalThread];
                        td.javaStack = new String[totalThread];
                        td.javaStackDepth = new int[totalThread];
                        td.macro = new int[totalThread];
                        td.nativeStack = new String[totalThread];
                        td.sys_thread = new long[totalThread];
                        td.tid = new long[totalThread];
                        td.summary = "<LI>File name : " + file[z].getAbsolutePath() + "<BR><BR>";
                        td.warning = "";
                        td.javaHeap = -1;
                        totalThread = 0;
                        td.free = -1L;
                        td.allocated = -1L;
                        td.af = -1L;
                        td.gc = -1L;
                        td.pid = -1L;
                        td.currentTid = (long) (td.currentThreadIndex = -1);
//                        tempS = null;
                        inArgs = false;
                        boolean inAnonymous = false;
                        boolean currentThread = false;

                        label2596:
                        while (true) {
//                            String checkNewFormat;
//                            String stateString;
//                            int x;
//                            int end;
//                            long owningThreadId;
                            label2545:
                            while (true) {
//                                String baseStr;
//                                int index_nid;
//                                int l;
                                if (line == null) {
                                    if (td.deadlock != null && td.deadlock.size() != 0) {
                                        boolean inTID = true;
                                        td.warning = td.warning + "<LI style=\"color:red\"><b>***WARNING*** Deadlock detected in </b><br>";

                                        for (int q = 0; q < td.deadlock.size(); ++q) {
                                            if ((Long) td.deadlock.get(q) != -1L) {
                                                for (int r = 0; r < td.sys_thread.length; ++r) {
                                                    if (td.sys_thread[r] == (Long) td.deadlock.get(q)) {
                                                        td.isDeadlock[r] = true;
                                                        td.warning = td.warning + "  [" + td.name[r] + "]";
                                                        inTID = false;
                                                    }
                                                }

                                                if (inTID) {
                                                    for (int r = 0; r < td.tid.length; ++r) {
                                                        if (td.tid[r] == (Long) td.deadlock.get(q)) {
                                                            td.isDeadlock[r] = true;
                                                            td.warning = td.warning + "  [" + td.name[r] + "]";
                                                        }
                                                    }
                                                }
                                            }
                                        }

                                        td.warning = td.warning + "<BR><BR>";
                                    }

                                    if (td.free != -1L && td.allocated != -1L && td.xmx != -1L && (float) td.allocated / (float) td.xmx > 0.9F) {
                                        td.javaHeap = (int) (100L * td.free / td.xmx);
                                        if (td.javaHeap < LOW_FREE_HEAP) {
                                            td.warning = td.warning + "<LI style=\"color:red\"><b>***WARNING*** Java heap is almost exhausted : </b>" + td.javaHeap + "% free Java heap<BR>Please enable verbosegc trace and use IBM Pattern Modeling and Analysis Tool(http://www.alphaworks.ibm.com/tech/pmat) to analyze garbage collection activities.<BR>If heapdumps are generated at the same time, please use IBM HeapAnalyzer(http://www.alphaworks.ibm.com/tech/heapanalyzer) to analyze Java heap.<BR><BR>";
                                        }
                                    }

                                    if (td != null) {
                                        if (td.currentThreadIndex != -1) {
                                            td.summary = td.summary + "<LI>Current Thread : <BR><BR>" + this.threadDetailTable(td) + "<BR><BR>";
                                        }

                                        if (heapBase != heapLimit && numberOfClasses != 0L) {
                                            td.summary = td.summary + "<LI>Number of loaded classes in Java heap : " + numberFormatter.format(numberOfClasses) + "<BR><BR><LI>Recommended size of kCluster : greater than " + numberFormatter.format((long) (1.1F * (float) numberOfClasses)) + "<BR><BR>";
                                        } else if (heapBase == -1L && numberOfClasses != 0L) {
                                            td.summary = td.summary + "<LI>Number of loaded classes in Java heap : " + numberFormatter.format(numberOfClasses) + "<BR><BR>";
                                        }

                                        if (numberOfClassLoader != 0L) {
                                            td.summary = td.summary + "<LI>Number of classloaders in Java heap : " + numberFormatter.format(numberOfClassLoader) + "<BR><BR>";
                                        }

                                        if (td.isJ9 && td.javaHeap == -1 && td.gcHistory != null) {
                                            String newspaceSignature = "newspace=";
                                            String oldspaceSignature = "oldspace=";
                                            String loaspaceSignature = "loa=";
                                            long free = 0L;
                                            long total = 0L;
                                            int i = td.gcHistory.indexOf(newspaceSignature);
                                            if (i >= 0) {
                                                String newspace = td.gcHistory.substring(i + newspaceSignature.length());
                                                int j = newspace.indexOf("/");
                                                if (j >= 0) {
                                                    free = Long.parseLong(newspace.substring(0, j));
                                                    int k = newspace.indexOf(" ");
                                                    if (k >= 0) {
                                                        total += Long.parseLong(newspace.substring(j + 1, k));
                                                    }
                                                }
                                            }

                                            i = td.gcHistory.indexOf(oldspaceSignature);
                                            if (i >= 0) {
                                                String oldspace = td.gcHistory.substring(i + oldspaceSignature.length());
                                                int j = oldspace.indexOf("/");
                                                if (j >= 0) {
                                                    free += Long.parseLong(oldspace.substring(0, j));
                                                    int k = oldspace.indexOf(" ");
                                                    if (k >= 0) {
                                                        try {
                                                            total += Long.parseLong(oldspace.substring(j + 1, k));
                                                        } catch (NumberFormatException var115) {
                                                            System.out.println("Incorrect number " + oldspace.substring(j + 1, k) + " in GC History.");
                                                        }
                                                    }
                                                }
                                            }

                                            i = td.gcHistory.indexOf(loaspaceSignature);
                                            if (i >= 0) {
                                                String loaspace = td.gcHistory.substring(i + loaspaceSignature.length());
                                                int j = loaspace.indexOf("/");
                                                if (j >= 0) {
                                                    free += Long.parseLong(loaspace.substring(0, j));
                                                    int k = loaspace.indexOf(" ");
                                                    int m = loaspace.indexOf("\n");
                                                    if (k < 0) {
                                                        k = m;
                                                    } else {
                                                        int o = Math.min(k, m);
                                                        k = o;
                                                    }

                                                    if (k >= 0) {
                                                        total += Long.parseLong(loaspace.substring(j + 1, k));
                                                    }
                                                }
                                            }

                                            if (td.free == -1L) {
                                                td.free = free;
                                            }

                                            if (td.allocated == -1L) {
                                                td.allocated = total;
                                            }

                                            if (td.free != -1L && td.allocated != -1L) {
                                                td.javaHeap = (int) (100L * td.free / td.allocated);
                                            }
                                        }

                                        if (td.isJ9 && td.gcHistory != null) {
                                            if (td.gc == -1L) {
                                                String globalSignature = "globalcount=";
                                                int i = td.gcHistory.indexOf(globalSignature);
                                                if (i >= 0) {
                                                    String global = td.gcHistory.substring(i + globalSignature.length());
                                                    int j = global.indexOf(" ");
                                                    if (j >= 0) {
                                                        td.gc = Long.parseLong(global.substring(0, j));
                                                    }
                                                }
                                            }

                                            if (td.af == -1L) {
                                                String scaSignature = "scavengecount=";
                                                int i = td.gcHistory.indexOf(scaSignature);
                                                if (i >= 0) {
                                                    String sca = td.gcHistory.substring(i + scaSignature.length());
                                                    int j = sca.indexOf(" ");
                                                    int m = sca.indexOf("\n");
                                                    if (j < 0) {
                                                        j = m;
                                                    } else {
                                                        int o = Math.min(j, m);
                                                        j = o;
                                                    }

                                                    if (j >= 0) {
                                                        td.af = Long.parseLong(sca.substring(0, j));
                                                    }
                                                }
                                            }
                                        }

                                        if (td.currentTid != -1L) {
                                            int indexOfCause = td.summary.indexOf("Cause of thread dump");
                                            if (indexOfCause != -1) {
                                                int indexOfEnd = td.summary.substring(indexOfCause).indexOf("<BR><BR>");
                                                if (indexOfEnd != -1) {
                                                    int resultIndex = td.summary.substring(indexOfCause, indexOfCause + indexOfEnd).indexOf("java/lang/OutOfMemoryError");
                                                    if (resultIndex != -1) {
                                                        for (int m = 0; m < td.sys_thread.length; ++m) {
                                                            if (td.sys_thread[m] == td.currentTid) {
                                                                int firstIndex = td.javaStack[m].indexOf("<BR>");
                                                                if (firstIndex != -1) {
                                                                    int foundIndex = td.javaStack[m].substring(0, firstIndex).indexOf("com/ibm/oti/vm/VM.initializeClassLoader");
                                                                    if (foundIndex != -1)
                                                                        if (numberOfClassLoader != 0L && heapBase == heapLimit) {
                                                                            td.summary = "***ERROR*** The failure was caused because the class loader limit(-Xmxcl) was exceeded. Please set -Xmxcl to " + numberFormatter.format((long) ((int) ((double) numberOfClassLoader * 1.3D))) + " or greater<BR><BR>" + td.summary;
                                                                        } else {
                                                                            td.summary = "***ERROR*** The failure was caused because the class loader limit(-Xmxcl) was exceeded. Please set  -Xmxcl larger than the number of class loaders.<BR><BR>" + td.summary;
                                                                        }
                                                                }
                                                                break;
                                                            }
                                                        }
                                                    }
                                                }
                                            }
                                        }

                                        td.summary = td.summary + commandLine;
                                        threadInfo.threadDumps.add(td);
                                    }

                                    threadLine.clear();
                                    break label2596;
                                }

                                if (line.startsWith(signatureGCHistory)) {
                                    if (td.gcHistory == null) {
                                        td.gcHistory = new String();
                                    }

                                    td.gcHistory = td.gcHistory + line.substring(signatureGCHistory.length() + 1).trim() + " <BR>";
                                    int sig = line.indexOf(signatureAllocateError);
                                    if (sig >= 0) {
                                        String warning = line.substring(sig + signatureAllocateError.length());
                                        if (td.oome) {
                                            warning = "OutOfMemoryError caused by " + warning;
                                        }

                                        td.summary = "<LI style=\"color:red\"><b>***WARNING*** " + warning + "</b><BR><BR>" + td.summary;
                                    }

                                    if (line.contains("to fail due to excessive GC") && !td.summary.contains("OutOfMemoryError due to excessive GC")) {
                                        td.summary = "<LI style=\"color:red\"><b>***WARNING*** OutOfMemoryError due to excessive GC. See ‑Xenableexcessivegc and -Xgc:excessiveGCratio</b><BR><BR>" + td.summary;
                                    }
                                    break;
                                }

                                if (line.startsWith(currentThreadSignature)) {
                                    currentThread = true;
                                    break;
                                }

                                if (line.startsWith(timeStampSignature)) {
                                    String converted = line.replace('<', ' ');
                                    line = converted.replace('>', ' ');
                                    td.summary = td.summary + "<LI>" + line.substring(timeStampSignature.length()).trim() + "<BR><BR>";
                                    int i = line.indexOf(":");
                                    if (i != -1) {
                                        dt = null;

                                        try {
                                            dt = formatter.parse(line.substring(1 + i).trim());
                                        } catch (ParseException var128) {
                                        }

                                        if (dt != null) {
                                            td.timeStamp = dt.getTime();
                                        }
                                    }
                                    break;
                                }

                                if (line.startsWith(osLevel)) {
                                    td.osLevel = line.substring(osLevel.length());
                                    td.summary = td.summary + "<LI>Operating System : " + td.osLevel + "<BR><BR>";
                                    break;
                                }

                                if (line.startsWith(hypervisorName)) {
                                    td.hypervisor = line.substring(hypervisorName.length());
                                    td.summary = td.summary + "<LI>Hypervisor Name : " + td.hypervisor + "<BR><BR>";
                                    break;
                                }

                                if (line.startsWith(cpuArchitecture)) {
                                    td.architecture = line.substring(cpuArchitecture.length());
                                    td.summary = td.summary + "<LI>Processor Architecture : " + td.architecture + "<BR><BR>";
                                    break;
                                }

                                if (line.startsWith(numberOfCPU)) {
                                    td.numberOfCPU = line.substring(numberOfCPU.length());
                                    td.summary = td.summary + "<LI>Number of Processors : " + td.numberOfCPU + "<BR><BR>";
                                    break;
                                }

                                if (line.startsWith(PROCESS_ID)) {
                                    int s = line.indexOf(':');
                                    int e = line.indexOf('(');
                                    String pd = line.substring(s + 2, e - 1);

                                    try {
                                        td.pid = Long.parseLong(pd);
                                        td.summary = td.summary + "<LI>Process ID : " + td.pid + " (0x" + Long.toHexString(td.pid) + ")<BR><BR>";
                                    } catch (Exception var127) {
                                        var127.printStackTrace();
                                    }
                                    break;
                                }

                                if (line.startsWith(userArgs0)) {
                                    td.summary = td.summary + "<LI> Command-Line Arguments<BR><BR><table border=\"1\"><col width=100%></col><caption align=\"bottom\">Command-Line Arguments</caption><tr><th>Command-Line Arguments</th></tr>";
                                    break;
                                }

                                if (line.startsWith(userArgs)) {
                                    inArgs = true;
                                    //todo-src文件的args不是在这里定义的
                                    String args = line.substring(userArgs.length()).trim();
                                    td.userArgs = td.userArgs + args;
                                    td.summary = td.summary + "<tr><td>" + args + "</td></tr>";
                                    if (td.commandLineTable == null) {
                                        td.commandLineTable = new CommonTable();
                                        td.commandLineTable.columnLabelList = new ArrayList<>();
                                        td.commandLineTable.columnLabelList.add("Arguments");
                                        td.commandLineTable.dataList = new ArrayList<>();
                                    }

                                    td.commandLineTable.dataList.add(Arrays.asList(args));
                                    break;
                                }

                                if (line.startsWith(nullSignature)) {
                                    if (inArgs) {
                                        td.summary = td.summary + "</table><BR><BR>";
                                        inArgs = false;
                                    }
                                    break;
                                }

                                if (line.startsWith(homeDir)) {
                                    td.homeDir = line.substring(homeDir.length());
                                    td.summary = td.summary + "<LI>Java Home Directory : " + td.homeDir + "<BR><BR>";
                                    break;
                                }

                                if (line.startsWith(dllDir)) {
                                    td.dllDir = line.substring(dllDir.length());
                                    td.summary = td.summary + "<LI>Java DLL Directory : " + td.dllDir + "<BR><BR>";
                                    break;
                                }

                                if (line.startsWith(sysCP)) {
                                    td.sysCP = line.substring(sysCP.length());
                                    td.summary = td.summary + "<LI>System Classpath : " + td.sysCP + "<BR><BR>";
                                    break;
                                }

                                if (line.startsWith(freeSignature)) {
                                    int i = line.indexOf(": ");
                                    if (i == -1) {
                                        td.summary = td.summary + line.substring(freeSignature.length()).trim() + "<BR>";
                                    } else {
                                        try {
                                            td.free = jinwooDecode("0x" + line.substring(i + 2).trim());
                                        } catch (NumberFormatException var126) {
                                            td.free = -1L;
                                        }

                                        if (td.free == -1L) {
                                            td.summary = td.summary + "<LI>Free Java heap size: unknown<BR>";
                                        } else {
                                            td.summary = td.summary + "<LI>Free Java heap size: " + this.convertToMG(td.free) + "<BR><BR>";
                                        }
                                    }
                                    break;
                                }

                                if (line.startsWith(allocatedSignature)) {
                                    int i = line.indexOf(": ");
                                    if (i == -1) {
                                        td.summary = td.summary + line.substring(allocatedSignature.length()).trim() + "<BR><BR>";
                                    } else {
                                        try {
                                            td.allocated = jinwooDecode("0x" + line.substring(i + 2).trim());
                                        } catch (NumberFormatException var125) {
                                            td.allocated = -1L;
                                        }

                                        if (td.allocated == -1L) {
                                            td.summary = td.summary + "<LI>Allocated Java heap size: unknown<BR><BR>";
                                        } else {
                                            td.summary = td.summary + "<LI>Allocated Java heap size: " + this.convertToMG(td.allocated) + "<BR><BR>";
                                        }
                                    }
                                    break;
                                }

                                if (line.startsWith(afCounterSignature)) {
                                    int i = line.indexOf(": ");
                                    if (i != -1) {
                                        td.af = jinwooDecode(line.substring(i + 2).trim());
                                    }

                                    td.summary = td.summary + "<LI>" + line.substring(afCounterSignature.length()).trim() + "<BR><BR>";
                                    break;
                                }

                                if (line.startsWith(gcCounterSignature)) {
                                    int i = line.indexOf(": ");
                                    if (i != -1) {
                                        td.gc = jinwooDecode(line.substring(i + 2).trim());
                                    }

                                    td.summary = td.summary + "<LI>" + line.substring(gcCounterSignature.length()).trim() + "<BR><BR>";
                                    break;
                                }

                                if (line.startsWith("0MEMUSER")) {
                                    td.nativeMemoryTree = new CustomerTree();
                                    CustomerTreeNode[] levels = new CustomerTreeNode[20];
                                    levels[0] = new CustomerTreeNode("Native Memory : Size / Allocation");
                                    for (; line != null && line.contains(MEMUSER); line = in.readLine()) {
                                        int level = Integer.parseInt(line.substring(0, line.indexOf(MEMUSER)));
                                        int i = line.indexOf(":");
                                        if (i >= 0) {
                                            int j = line.lastIndexOf("-");
                                            if (j < 0) {
                                                j = i - 4;
                                            }

                                            CustomerTreeNode jre = new CustomerTreeNode(line.substring(j + 1));
                                            levels[level - 1].add(jre);
                                            levels[level] = jre;
                                        }
                                    }

                                    td.nativeMemoryTree = new CustomerTree(levels[0]);
                                    break;
                                }

//                                String allocStr;
                                long countIM;
                                if (line.startsWith(userLimit)) {
                                    String type = null;
                                    String soft = null;
                                    String hard = null;
                                    td.summary = td.summary + "<LI> User Process Resource Limit Analysis <BR><BR><table border=\"1\"><col width=20%></col><col width=40%></col><col width=40%></col><caption align=\"bottom\">User Process Resource Limit Analysis</caption><tr><th>Type</th><th>Soft Limit</th><th>Hard Limit</th></tr>";

                                    while (line.indexOf(userLimit) >= 0) {
                                        StringTokenizer st = new StringTokenizer(line);

                                        try {
                                            st.nextToken();
                                            type = st.nextToken();
                                            String bytes;
                                            if (!type.contains("NOFILE") && !type.contains("NPROC")) {
                                                bytes = " bytes";
                                            } else {
                                                bytes = "";
                                            }

                                            soft = st.nextToken();
                                            hard = st.nextToken();
                                            if (soft != null) {
                                                try {
                                                    countIM = Long.parseLong(soft);
                                                    soft = numberFormatter.format(countIM) + bytes;
                                                } catch (NumberFormatException var124) {
                                                }
                                            }

                                            if (hard != null) {
                                                try {
                                                    long hlimit = Long.parseLong(hard);
                                                    hard = numberFormatter.format(hlimit) + bytes;
                                                } catch (NumberFormatException var123) {
                                                }
                                            }

                                            if (hard != null && soft != null) {
                                                if (td.ulimitTable == null) {
                                                    td.ulimitTable = new CommonTable();
                                                    td.ulimitTable.columnLabelList = new ArrayList<>();
                                                    td.ulimitTable.columnLabelList.add("Type");
                                                    td.ulimitTable.columnLabelList.add("Soft Limit");
                                                    td.ulimitTable.columnLabelList.add("Hard Limit");
                                                    td.ulimitTable.dataList = new ArrayList<>();
                                                }

                                                td.summary = td.summary + "<tr><td>" + type + "</td><td align=right>" + soft + "</td><td align=right>" + hard + "</td></tr>";
                                                td.ulimitTable.dataList.add(Arrays.asList(type, soft, hard));
                                            }
                                        } catch (NoSuchElementException var132) {
                                        }

                                        line = in.readLine();
                                        if (line == null) {
                                            break;
                                        }
                                    }

                                    td.summary = td.summary + "</table><BR><BR>";
                                    break;
                                }

//                                String xmso;
                                if (line.startsWith(cacheSummary)) {
                                    td.summary = td.summary + "<LI> Shared Class Cache Analysis<BR><BR><table border=\"1\"><col width=50%></col><col width=50%></col><caption align=\"bottom\">Shared Class Cache Analysis</caption><tr><th>Property</th><th>Value</th></tr>";
                                    if (td.sharedClassCacheTable == null) {
                                        td.sharedClassCacheTable = new CommonTable();
                                        td.sharedClassCacheTable.columnLabelList = new ArrayList<>();
                                        td.sharedClassCacheTable.columnLabelList.add("Property");
                                        td.sharedClassCacheTable.columnLabelList.add("Value");
                                        td.sharedClassCacheTable.dataList = new ArrayList<>();
                                    }

                                    while ((line = in.readLine()) != null && line.indexOf("2SCLTEXT") >= 0 || line.indexOf(nullSignature) >= 0) {
                                        if (line.indexOf(nullSignature) < 0) {
                                            int firstSpace = line.indexOf(' ');
                                            if (firstSpace >= 0) {
                                                String trimmed = line.substring(firstSpace).trim();
                                                int eq = trimmed.indexOf('=');
                                                if (eq > 0) {
                                                    String vname = trimmed.substring(0, eq - 1).trim();
                                                    String vvalue = trimmed.substring(eq + 1).trim();
                                                    Long n = null;

                                                    try {
                                                        n = Long.parseLong(vvalue);
                                                    } catch (Exception var122) {
                                                    }

                                                    if (n == null) {
                                                        td.summary = td.summary + "<tr><td>" + vname + "</td><td>" + vvalue + "</td></tr>";
                                                        td.sharedClassCacheTable.dataList.add(Arrays.asList(vname, vvalue));
                                                    } else {
                                                        td.summary = td.summary + "<tr><td>" + vname + "</td><td>" + numberFormatter.format(n) + "</td></tr>";
                                                        td.sharedClassCacheTable.dataList.add(Arrays.asList(vname, numberFormatter.format(n)));
                                                    }
                                                } else {
                                                    td.summary = td.summary + "<tr><td>" + trimmed + "</td><td></td></tr>";
                                                    td.sharedClassCacheTable.dataList.add(Arrays.asList(trimmed));
                                                }

                                                if (line == null) {
                                                    break;
                                                }
                                            }
                                        }
                                    }

                                    td.summary = td.summary + "</table><BR><BR>";
                                    if (!line.startsWith(cacheMemoryStatus)) {
                                        break;
                                    }

                                    String title = line.substring(cacheMemoryStatus.length() + 1).trim();

                                    String[] values;
                                    while ((line = in.readLine()) != null && (line.indexOf("1SCLTEXTCNTD") >= 0 || line.indexOf("2SCLTEXTCMDT") >= 0 || line.indexOf(nullSignature) >= 0)) {
                                        int idx = line.indexOf("1SCLTEXTCNTD");
                                        if (idx >= 0) {
                                            String trimmed = line.substring(idx + "1SCLTEXTCNTD".length() + 1).trim();
                                            values = new String[30];

                                            int v = 0;
                                            int ds;
                                            for (; trimmed != null && (ds = trimmed.indexOf("  ")) > 0; trimmed = trimmed.substring(ds).trim()) {
                                                values[v++] = trimmed.substring(0, ds);
                                            }

                                            values[v++] = trimmed;
                                            if (v == 3) {
                                                td.summary = td.summary + "<LI> Shared Class " + trimmed + " Analysis<BR><BR><table border=\"1\"><col width=33%></col><col width=33%></col><col width=33%></col><caption align=\"bottom\">Shared Class " + trimmed + " Analysis</caption><tr><th>" + values[0] + "</th><th>" + values[1] + "</th><th>" + values[2] + "</th></tr>";
                                            }
                                        } else {
                                            idx = line.indexOf("2SCLTEXTCMDT");
                                            if (idx >= 0) {
                                                String trimmed = line.substring(idx + "2SCLTEXTCMDT".length() + 1).trim();
                                                values = new String[30];
                                                int v = 0;
                                                int ds;
                                                for (; trimmed != null && (ds = trimmed.indexOf("  ")) > 0; trimmed = trimmed.substring(ds).trim()) {
                                                    values[v++] = trimmed.substring(0, ds);
                                                }

                                                values[v++] = trimmed;
                                                if (v == 3) {
                                                    td.summary = td.summary + "<tr><td>" + values[0] + "</td><td>" + values[1] + "</td><td>" + values[2] + "</td></tr>";
                                                }
                                            }
                                        }
                                    }

                                    td.summary = td.summary + "</table><BR><BR>";
                                    if (!line.startsWith(cacheMemoryStatus)) {
                                        break;
                                    }

                                    title = line.substring(cacheMemoryStatus.length() + 1).trim();

                                    while ((line = in.readLine()) != null && (line.indexOf("1SCLTEXTCNTD") >= 0 || line.indexOf("2SCLTEXTCWRL") >= 0 || line.indexOf("2SCLTEXTCRWL") >= 0 || line.indexOf(nullSignature) >= 0)) {
                                        int idx = line.indexOf("1SCLTEXTCNTD");
                                        if (idx >= 0) {
                                            String trimmed = line.substring(idx + "1SCLTEXTCNTD".length() + 1).trim();
                                            values = new String[30];

                                            int v = 0;
                                            int ds;
                                            for (; trimmed != null && (ds = trimmed.indexOf("  ")) > 0; trimmed = trimmed.substring(ds).trim()) {
                                                values[v++] = trimmed.substring(0, ds);
                                            }

                                            values[v++] = trimmed;
                                            if (v == 3) {
                                                td.summary = td.summary + "<LI> Shared Class " + trimmed + " Analysis<BR><BR><table border=\"1\"><col width=33%></col><col width=33%></col><col width=33%></col><caption align=\"bottom\">Shared Class " + trimmed + " Analysis</caption><tr><th>" + values[0] + "</th><th>" + values[1] + "</th><th>" + values[2] + "</th></tr>";
                                            }
                                        } else {
                                            idx = line.indexOf("2SCLTEXTCWRL");
                                            if (idx < 0) {
                                                idx = line.indexOf("2SCLTEXTCRWL");
                                            }

                                            if (idx >= 0) {
                                                String trimmed = line.substring(idx + "2SCLTEXTCWRL".length() + 1).trim();
                                                values = new String[30];

                                                int v = 0;
                                                int ds;
                                                for (; trimmed != null && (ds = trimmed.indexOf("  ")) > 0; trimmed = trimmed.substring(ds).trim()) {
                                                    values[v++] = trimmed.substring(0, ds);
                                                }

                                                values[v++] = trimmed;
                                                if (v == 3) {
                                                    td.summary = td.summary + "<tr><td>" + values[0] + "</td><td>" + values[1] + "</td><td>" + values[2] + "</td></tr>";
                                                }
                                            }
                                        }
                                    }

                                    td.summary = td.summary + "</table><BR><BR>";
                                    break;
                                }

//                                String Xscminjitdata;
                                if (line.startsWith(enviromentVariables)) {
                                    td.summary = td.summary + "<LI> Environment Variables Analysis <BR><BR><table border=\"1\"><col width=50%></col><col width=50%></col><caption align=\"bottom\">Environment Variable</caption><tr><th>Environment Variable</th><th>Value</th></tr>";
                                    int l = enviromentVariables.length() + 1;

                                    while (line.indexOf(enviromentVariables) >= 0) {
                                        line = line.substring(l).trim();
                                        int s = line.indexOf("=");
                                        String vvname = "";
                                        String vvalue = "";
                                        if (s >= 0) {
                                            vvname = line.substring(0, s);
                                            vvalue = line.substring(s + 1);
                                        }

                                        td.summary = td.summary + "<tr><td>" + vvname + "</td><td>" + vvalue + "</td></tr>";
                                        final String os = td.javaVersion;
                                        if (td.eVariables == null) {
                                            td.eVariables = new CommonTable();
                                            td.eVariables.columnLabelList = new ArrayList<>();
                                            td.eVariables.columnLabelList.add("Environment Variable");
                                            td.eVariables.columnLabelList.add("Value");

                                            td.eVariables.dataList = new ArrayList<>();
                                        }

                                        td.eVariables.dataList.add(Arrays.asList(new String[]{vvname, vvalue}));
                                        if (td.javaVersion != null && vvname != null && (vvname.toLowerCase().contains("path") || vvalue != null && vvalue.length() > 100)) {
                                            String del;
                                            if (td.javaVersion.toLowerCase().contains("win")) {
                                                del = ";";
                                            } else {
                                                del = ":";
                                            }

                                            String[] splits = vvalue.split(del);
                                            if (splits.length > 1) {
                                                for (int m = 0; m < splits.length; ++m) {
                                                    String t = splits[m].trim();
                                                    if (m == 0) {
                                                        td.summary = td.summary + "<tr><td>" + vvname + " (Expanded)</td><td>" + t + del + "</td></tr>";
                                                    } else if (t.length() != 0) {
                                                        td.summary = td.summary + "<tr><td></td><td>" + t + del + "</td></tr>";
                                                    }
                                                }
                                            }
                                        }

                                        line = in.readLine();
                                        if (line == null) {
                                            break;
                                        }
                                    }

                                    td.summary = td.summary + "</table><BR><BR>";
                                    break;
                                }

//                                String Xmt;
//                                String reservedStr;
                                long reserved;
                                long totalUsed;
                                long totalReserved;
                                long sumCount;
                                long sumUsed;
                                long sumReserved;
                                DefaultTableCellRenderer rightRenderer;
//                                long value;
//                                StringTokenizer st;
//                                long base;
                                long alloc;
                                if (line.startsWith(memorySignature626)) {
                                    line = in.readLine();
                                    if (line.indexOf(nullSignature) >= 0) {
                                        line = in.readLine();
                                        if (line.indexOf(memorySegmentSignature626) >= 0) {
                                            long usedIM = 0L;
                                            long reservedIM = 0L;
                                            countIM = 0L;
//                                            Xmt = null;
                                            totalUsed = 0L;
                                            totalReserved = 0L;
                                            sumCount = 0L;
                                            sumUsed = 0L;

                                            for (sumReserved = 0L; line.indexOf(memorySegmentSignature626) >= 0; line = in.readLine()) {
                                                StringTokenizer st = new StringTokenizer(line);
                                                st.nextToken();
                                                st.nextToken();
                                                st.nextToken();
                                                st.nextToken();
                                                String baseStr = st.nextToken();
                                                long base;
                                                if (baseStr.startsWith("0x")) {
                                                    base = jinwooDecode(baseStr);
                                                } else {
                                                    base = jinwooDecode("0x" + baseStr);
                                                }

                                                if (base == -1L) {
                                                    base = 0L;
                                                }

                                                ++countIM;
                                                totalUsed += base;
                                                totalReserved += base;
                                            }

                                            if (td.memorySegment == null) {
                                                td.memorySegment = new CommonTable();
                                                td.memorySegment.columnLabelList = new ArrayList<>();

                                                td.memorySegment.columnLabelList.add("Memory Type");
                                                td.memorySegment.columnLabelList.add("# of Segments");
                                                td.memorySegment.columnLabelList.add("Used Memory(bytes)");
                                                td.memorySegment.columnLabelList.add("Used Memory(%)");
                                                td.memorySegment.columnLabelList.add("Free Memory(bytes)");
                                                td.memorySegment.columnLabelList.add("Free Memory(%)");
                                                td.memorySegment.columnLabelList.add("Total Memory(bytes)");
                                            }

                                            while (line.indexOf(memorySignature) < 0) {
                                                line = in.readLine();
                                                if (line.startsWith(freeSignature)) {
                                                    int i = line.indexOf(": ");
                                                    int j = line.indexOf("(");
                                                    if (i >= 0 && j >= 0) {
                                                        try {
                                                            td.free = Long.parseLong(line.substring(i + 2, j).trim());
                                                        } catch (NumberFormatException var131) {
                                                            td.free = -1L;
                                                        }
                                                    }
                                                } else if (line.startsWith("1STHEAPTOTAL")) {
                                                    int i = line.indexOf(": ");
                                                    int j = line.indexOf("(");
                                                    if (i >= 0 && j >= 0) {
                                                        try {
                                                            td.allocated = Long.parseLong(line.substring(i + 2, j).trim());
                                                        } catch (NumberFormatException var130) {
                                                            td.allocated = -1L;
                                                        }
                                                    }
                                                } else if (line.startsWith("1STHEAPINUSE")) {
                                                    int i = line.indexOf(": ");
                                                    int j = line.indexOf("(");
                                                    if (i >= 0 && j >= 0) {
                                                        try {
                                                            td.inuse = Long.parseLong(line.substring(i + 2, j).trim());
                                                        } catch (NumberFormatException var129) {
                                                            td.inuse = -1L;
                                                        }
                                                    }
                                                }
                                            }

                                            long heapFree = td.free == -1L ? totalReserved - totalUsed : td.free;
                                            long heapInUse = td.inuse == -1L ? totalUsed : td.inuse;
                                            sumCount += countIM;
                                            sumUsed += heapInUse;
                                            sumReserved += totalReserved;
                                            if (SHOW_MEMORY_SEGMENT_ANALYSIS) {
                                                td.summary = td.summary + "<LI> Memory Segment Analysis <BR><BR><table border=\"1\"><col width=10%></col><col width=20%></col><col width=20%></col><col width=20%></col><col width=20%></col><caption align=\"bottom\">Memory Segment Analysis</caption><tr><th>Memory Type</th><th># of Segments</th><th>Used Memory(bytes)</th><th>Used Memory(%)</th><th>Free Memory(bytes)</th><th>Free Memory(%)</th><th>Total Memory(bytes)</th></tr>";
                                                td.summary = td.summary + "<tr><td>Java Heap (Virtual)</td><td align=right>" + numberFormatter.format(countIM) + "</td><td align=right>" + numberFormatter.format(heapInUse) + "</td><td align=right>" + numberFormatter.format((double) (100.0F * (float) heapInUse / (float) totalReserved)) + "</td><td align=right>" + numberFormatter.format(heapFree) + "</td><td align=right>" + numberFormatter.format((double) (100.0F * (float) heapFree / (float) totalReserved)) + "</td><td align=right>" + numberFormatter.format(totalReserved) + "</td></tr>";
                                            }

                                            td.memorySegment.dataList.add(Arrays.asList("Java Heap (Virtual)", numberFormatter.format(countIM), numberFormatter.format(heapInUse), numberFormatter.format((double) (100.0F * (float) heapInUse / (float) totalReserved)), numberFormatter.format(heapFree), numberFormatter.format((double) (100.0F * (float) heapFree / (float) totalReserved)), numberFormatter.format(totalReserved)));
                                            line = in.readLine();
                                            if (line.indexOf(nullSignature) >= 0) {
                                                line = in.readLine();
                                                if (line.indexOf(memorySegmentSignature) >= 0) {
                                                    totalUsed = 0L;
                                                    totalReserved = 0L;

                                                    for (countIM = 0L; line.indexOf(memorySegmentSignature) >= 0; line = in.readLine()) {
                                                        StringTokenizer st = new StringTokenizer(line);
                                                        if (st.countTokens() != 7) {
                                                            break;
                                                        }

                                                        st.nextToken();
                                                        st.nextToken();
                                                        String baseStr = st.nextToken();
                                                        long base;
                                                        if (baseStr.startsWith("0x")) {
                                                            base = jinwooDecode(baseStr);
                                                        } else {
                                                            base = jinwooDecode("0x" + baseStr);
                                                        }

                                                        String allocStr = st.nextToken();
                                                        if (allocStr.startsWith("0x")) {
                                                            alloc = jinwooDecode(allocStr);
                                                        } else {
                                                            alloc = jinwooDecode("0x" + allocStr);
                                                        }

                                                        st.nextToken();
                                                        st.nextToken();
                                                        String reservedStr = st.nextToken();
                                                        if (reservedStr.startsWith("0x")) {
                                                            reserved = jinwooDecode(reservedStr);
                                                        } else {
                                                            reserved = jinwooDecode("0x" + reservedStr);
                                                        }

                                                        ++countIM;
                                                        totalUsed += alloc - base;
                                                        totalReserved += reserved;
                                                    }

                                                    if (SHOW_MEMORY_SEGMENT_ANALYSIS) {
                                                        td.summary = td.summary + "<tr><td>Internal (Virtual)</td><td align=right>" + numberFormatter.format(countIM) + "</td><td align=right>" + numberFormatter.format(totalUsed) + "</td><td align=right>" + numberFormatter.format((double) (100.0F * (float) totalUsed / (float) totalReserved)) + "</td><td align=right>" + numberFormatter.format(totalReserved - totalUsed) + "</td><td align=right>" + numberFormatter.format((double) (100.0F * (float) (totalReserved - totalUsed) / (float) totalReserved)) + "</td><td align=right>" + numberFormatter.format(totalReserved) + "</td></tr>";
                                                    }

                                                    td.memorySegment.dataList.add(Arrays.asList("Internal (Virtual)", numberFormatter.format(countIM), numberFormatter.format(totalUsed), numberFormatter.format((double) (100.0F * (float) totalUsed / (float) totalReserved)), numberFormatter.format(totalReserved - totalUsed), numberFormatter.format((double) (100.0F * (float) (totalReserved - totalUsed) / (float) totalReserved)), numberFormatter.format(totalReserved)));
                                                    sumCount += countIM;
                                                    sumUsed += totalUsed;

                                                    for (sumReserved += totalReserved; line.indexOf(memoryCMSignature) < 0; line = in.readLine()) {
                                                    }

                                                    line = in.readLine();
                                                    if (line.indexOf(nullSignature) >= 0) {
                                                        line = in.readLine();
                                                        if (line.indexOf(memorySegmentSignature) >= 0) {
                                                            totalUsed = 0L;
                                                            totalReserved = 0L;

                                                            for (countIM = 0L; line.indexOf(memorySegmentSignature) >= 0; line = in.readLine()) {
                                                                StringTokenizer st = new StringTokenizer(line);
                                                                if (st.countTokens() != 7) {
                                                                    break;
                                                                }

                                                                st.nextToken();
                                                                st.nextToken();
                                                                String baseStr = st.nextToken();
                                                                long base;
                                                                if (baseStr.startsWith("0x")) {
                                                                    base = jinwooDecode(baseStr);
                                                                } else {
                                                                    base = jinwooDecode("0x" + baseStr);
                                                                }

                                                                String allocStr = st.nextToken();
                                                                if (allocStr.startsWith("0x")) {
                                                                    alloc = jinwooDecode(allocStr);
                                                                } else {
                                                                    alloc = jinwooDecode("0x" + allocStr);
                                                                }

                                                                st.nextToken();
                                                                st.nextToken();
                                                                String reservedStr = st.nextToken();
                                                                if (reservedStr.startsWith("0x")) {
                                                                    reserved = jinwooDecode(reservedStr);
                                                                } else {
                                                                    reserved = jinwooDecode("0x" + reservedStr);
                                                                }

                                                                ++countIM;
                                                                totalUsed += alloc - base;
                                                                totalReserved += reserved;
                                                            }

                                                            if (SHOW_MEMORY_SEGMENT_ANALYSIS) {
                                                                td.summary = td.summary + "<tr><td>Class (Virtual)</td><td align=right>" + numberFormatter.format(countIM) + "</td><td align=right>" + numberFormatter.format(totalUsed) + "</td><td align=right>" + numberFormatter.format((double) (100.0F * (float) totalUsed / (float) totalReserved)) + "</td><td align=right>" + numberFormatter.format(totalReserved - totalUsed) + "</td><td align=right>" + numberFormatter.format((double) (100.0F * (float) (totalReserved - totalUsed) / (float) totalReserved)) + "</td><td align=right>" + numberFormatter.format(totalReserved) + "</td></tr>";
                                                            }

                                                            td.memorySegment.dataList.add(Arrays.asList("Class (Virtual)", numberFormatter.format(countIM), numberFormatter.format(totalUsed), numberFormatter.format((double) (100.0F * (float) totalUsed / (float) totalReserved)), numberFormatter.format(totalReserved - totalUsed), numberFormatter.format((double) (100.0F * (float) (totalReserved - totalUsed) / (float) totalReserved)), numberFormatter.format(totalReserved)));
                                                            sumCount += countIM;
                                                            sumUsed += totalUsed;

                                                            for (sumReserved += totalReserved; line.indexOf(memoryJCSignature) < 0; line = in.readLine()) {
                                                            }

                                                            line = in.readLine();
                                                            if (line.indexOf(nullSignature) >= 0) {
                                                                line = in.readLine();
                                                                if (line.indexOf(memorySegmentSignature) >= 0) {
                                                                    totalUsed = 0L;
                                                                    totalReserved = 0L;

                                                                    for (countIM = 0L; line.indexOf(memorySegmentSignature) >= 0; line = in.readLine()) {
                                                                        StringTokenizer st = new StringTokenizer(line);
                                                                        if (st.countTokens() != 7) {
                                                                            break;
                                                                        }

                                                                        st.nextToken();
                                                                        st.nextToken();
                                                                        String baseStr = st.nextToken();
                                                                        long base;
                                                                        if (baseStr.startsWith("0x")) {
                                                                            base = jinwooDecode(baseStr);
                                                                        } else {
                                                                            base = jinwooDecode("0x" + baseStr);
                                                                        }

                                                                        String allocStr = st.nextToken();
                                                                        if (allocStr.startsWith("0x")) {
                                                                            alloc = jinwooDecode(allocStr);
                                                                        } else {
                                                                            alloc = jinwooDecode("0x" + allocStr);
                                                                        }

                                                                        st.nextToken();
                                                                        st.nextToken();
                                                                        String reservedStr = st.nextToken();
                                                                        if (reservedStr.startsWith("0x")) {
                                                                            reserved = jinwooDecode(reservedStr);
                                                                        } else {
                                                                            reserved = jinwooDecode("0x" + reservedStr);
                                                                        }

                                                                        ++countIM;
                                                                        totalUsed += alloc - base;
                                                                        totalReserved += reserved;
                                                                    }

                                                                    if (SHOW_MEMORY_SEGMENT_ANALYSIS) {
                                                                        td.summary = td.summary + "<tr><td>JIT Code Cache (Virtual)</td><td align=right>" + numberFormatter.format(countIM) + "</td><td align=right>" + numberFormatter.format(totalUsed) + "</td><td align=right>" + numberFormatter.format((double) (100.0F * (float) totalUsed / (float) totalReserved)) + "</td><td align=right>" + numberFormatter.format(totalReserved - totalUsed) + "</td><td align=right>" + numberFormatter.format((double) (100.0F * (float) (totalReserved - totalUsed) / (float) totalReserved)) + "</td><td align=right>" + numberFormatter.format(totalReserved) + "</td></tr>";
                                                                    }

                                                                    td.memorySegment.dataList.add(Arrays.asList("JIT Code Cache (Virtual)", numberFormatter.format(countIM), numberFormatter.format(totalUsed), numberFormatter.format((double) (100.0F * (float) totalUsed / (float) totalReserved)), numberFormatter.format(totalReserved - totalUsed), numberFormatter.format((double) (100.0F * (float) (totalReserved - totalUsed) / (float) totalReserved)), numberFormatter.format(totalReserved)));
                                                                    sumCount += countIM;
                                                                    sumUsed += totalUsed;

                                                                    for (sumReserved += totalReserved; line.indexOf(memoryJDSignature) < 0; line = in.readLine()) {
                                                                    }

                                                                    line = in.readLine();
                                                                    if (line.indexOf(nullSignature) >= 0) {
                                                                        line = in.readLine();
                                                                        if (line.indexOf(memorySegmentSignature) >= 0) {
                                                                            totalUsed = 0L;
                                                                            totalReserved = 0L;

                                                                            for (countIM = 0L; line.indexOf(memorySegmentSignature) >= 0; line = in.readLine()) {
                                                                                StringTokenizer st = new StringTokenizer(line);
                                                                                if (st.countTokens() != 7) {
                                                                                    break;
                                                                                }

                                                                                st.nextToken();
                                                                                st.nextToken();
                                                                                String baseStr = st.nextToken();
                                                                                long base;
                                                                                if (baseStr.startsWith("0x")) {
                                                                                    base = jinwooDecode(baseStr);
                                                                                } else {
                                                                                    base = jinwooDecode("0x" + baseStr);
                                                                                }

                                                                                String allocStr = st.nextToken();
                                                                                if (allocStr.startsWith("0x")) {
                                                                                    alloc = jinwooDecode(allocStr);
                                                                                } else {
                                                                                    alloc = jinwooDecode("0x" + allocStr);
                                                                                }

                                                                                st.nextToken();
                                                                                st.nextToken();
                                                                                String reservedStr = st.nextToken();
                                                                                if (reservedStr.startsWith("0x")) {
                                                                                    reserved = jinwooDecode(reservedStr);
                                                                                } else {
                                                                                    reserved = jinwooDecode("0x" + reservedStr);
                                                                                }

                                                                                ++countIM;
                                                                                totalUsed += alloc - base;
                                                                                totalReserved += reserved;
                                                                            }

                                                                            if (SHOW_MEMORY_SEGMENT_ANALYSIS) {
                                                                                td.summary = td.summary + "<tr><td>JIT Data Cache (Virtual)</td><td align=right>" + numberFormatter.format(countIM) + "</td><td align=right>" + numberFormatter.format(totalUsed) + "</td><td align=right>" + numberFormatter.format((double) (100.0F * (float) totalUsed / (float) totalReserved)) + "</td><td align=right>" + numberFormatter.format(totalReserved - totalUsed) + "</td><td align=right>" + numberFormatter.format((double) (100.0F * (float) (totalReserved - totalUsed) / (float) totalReserved)) + "</td><td align=right>" + numberFormatter.format(totalReserved) + "</td></tr>";
                                                                            }

                                                                            td.memorySegment.dataList.add(Arrays.asList("JIT Data Cache (Virtual)", numberFormatter.format(countIM), numberFormatter.format(totalUsed), numberFormatter.format((double) (100.0F * (float) totalUsed / (float) totalReserved)), numberFormatter.format(totalReserved - totalUsed), numberFormatter.format((double) (100.0F * (float) (totalReserved - totalUsed) / (float) totalReserved)), numberFormatter.format(totalReserved)));
                                                                            sumCount += countIM;
                                                                            sumUsed += totalUsed;
                                                                            sumReserved += totalReserved;
                                                                            if (SHOW_MEMORY_SEGMENT_ANALYSIS) {
                                                                                td.summary = td.summary + "<tr><td>Overall (Virtual)</td><td align=right>" + numberFormatter.format(sumCount) + "</td><td align=right>" + numberFormatter.format(sumUsed) + "</td><td align=right>" + numberFormatter.format((double) (100.0F * (float) sumUsed / (float) sumReserved)) + "</td><td align=right>" + numberFormatter.format(sumReserved - sumUsed) + "</td><td align=right>" + numberFormatter.format((double) (100.0F * (float) (sumReserved - sumUsed) / (float) sumReserved)) + "</td><td align=right>" + numberFormatter.format(sumReserved) + "</td></tr></table><BR><BR>";
                                                                            }

                                                                            td.memorySegment.dataList.add(Arrays.asList("Overall (Virtual)", numberFormatter.format(sumCount), numberFormatter.format(sumUsed), numberFormatter.format((double) (100.0F * (float) sumUsed / (float) sumReserved)), numberFormatter.format(sumReserved - sumUsed), numberFormatter.format((double) (100.0F * (float) (sumReserved - sumUsed) / (float) sumReserved)), numberFormatter.format(sumReserved)));
                                                                            if (td.allocated == -1L) {
                                                                                td.summary = td.summary + "<LI>Total Java heap size: unknown<BR><BR>";
                                                                            } else {
                                                                                td.summary = td.summary + "<LI>Total Java heap size: " + this.convertToMG(td.allocated) + "<BR><BR>";
                                                                            }

                                                                            if (td.inuse == -1L) {
                                                                                td.summary = td.summary + "<LI>Used Java heap size: unknown<BR>";
                                                                            } else {
                                                                                td.summary = td.summary + "<LI>Used Java heap size: " + this.convertToMG(td.inuse) + "<BR><BR>";
                                                                            }

                                                                            if (td.free == -1L) {
                                                                                td.summary = td.summary + "<LI>Free Java heap size: unknown<BR>";
                                                                            } else {
                                                                                td.summary = td.summary + "<LI>Free Java heap size: " + this.convertToMG(td.free) + "<BR><BR>";
                                                                            }
                                                                            break;
                                                                        }
                                                                    }
                                                                }
                                                            }
                                                        }
                                                    }
                                                }
                                            }
                                        }
                                    }
                                } else {
                                    if (line.startsWith(memorySignature)) {
                                        line = in.readLine();
                                        if (line.indexOf(nullSignature) < 0) {
                                            continue;
                                        }

                                        line = in.readLine();
                                        if (line.indexOf(memorySegmentSignature) < 0) {
                                            continue;
                                        }

                                        totalUsed = 0L;
                                        totalReserved = 0L;
                                        countIM = 0L;
//                                        Xmt = null;
                                        totalUsed = 0L;
                                        totalReserved = 0L;
                                        sumCount = 0L;
                                        sumUsed = 0L;

                                        for (sumReserved = 0L; line.indexOf(memorySegmentSignature) >= 0; line = in.readLine()) {
                                            StringTokenizer st = new StringTokenizer(line);
                                            if (st.countTokens() != 7) {
                                                break;
                                            }

                                            st.nextToken();
                                            st.nextToken();
                                            String baseStr = st.nextToken();
                                            long base;
                                            if (baseStr.startsWith("0x")) {
                                                base = jinwooDecode(baseStr);
                                            } else {
                                                base = jinwooDecode("0x" + baseStr);
                                            }

                                            String allocStr = st.nextToken();
                                            if (allocStr.startsWith("0x")) {
                                                alloc = jinwooDecode(allocStr);
                                            } else {
                                                alloc = jinwooDecode("0x" + allocStr);
                                            }

                                            st.nextToken();
                                            st.nextToken();
                                            String reservedStr = st.nextToken();
                                            if (reservedStr.startsWith("0x")) {
                                                reserved = jinwooDecode(reservedStr);
                                            } else {
                                                reserved = jinwooDecode("0x" + reservedStr);
                                            }

                                            ++countIM;
                                            totalUsed += alloc - base;
                                            totalReserved += reserved;
                                        }

                                        if (SHOW_MEMORY_SEGMENT_ANALYSIS) {
                                            td.summary = td.summary + "<LI> Memory Segment Analysis <BR><BR><table border=\"1\"><col width=10%></col><col width=20%></col><col width=20%></col><col width=20%></col><col width=20%></col><caption align=\"bottom\">Memory Segment Analysis</caption><tr><th>Memory Type</th><th># of Segments</th><th>Used Memory(bytes)</th><th>Used Memory(%)</th><th>Free Memory(bytes)</th><th>Free Memory(%)</th><th>Total Memory(bytes)</th></tr>";
                                            td.summary = td.summary + "<tr><td>Internal (Virtual)</td><td align=right>" + numberFormatter.format(countIM) + "</td><td align=right>" + numberFormatter.format(totalUsed) + "</td><td align=right>" + numberFormatter.format((double) (100.0F * (float) totalUsed / (float) totalReserved)) + "</td><td align=right>" + numberFormatter.format(totalReserved - totalUsed) + "</td><td align=right>" + numberFormatter.format((double) (100.0F * (float) (totalReserved - totalUsed) / (float) totalReserved)) + "</td><td align=right>" + numberFormatter.format(totalReserved) + "</td></tr>";
                                        }

                                        sumCount += countIM;
                                        sumUsed += totalUsed;
                                        sumReserved += totalReserved;
                                        if (td.memorySegment == null) {
                                            td.memorySegment = new CommonTable();
                                            td.memorySegment.columnLabelList = new ArrayList<>();
                                            td.memorySegment.columnLabelList.add("Memory Type");
                                            td.memorySegment.columnLabelList.add("# of Segments");
                                            td.memorySegment.columnLabelList.add("Used Memory(bytes)");
                                            td.memorySegment.columnLabelList.add("Used Memory(%)");
                                            td.memorySegment.columnLabelList.add("Free Memory(bytes)");
                                            td.memorySegment.columnLabelList.add("Free Memory(%)");
                                            td.memorySegment.columnLabelList.add("Total Memory(bytes)");
                                            td.memorySegment.dataList = new ArrayList<>();
                                        }

                                        td.memorySegment.dataList.add(Arrays.asList("Internal (Virtual)", numberFormatter.format(countIM), numberFormatter.format(totalUsed), numberFormatter.format((double) (100.0F * (float) totalUsed / (float) totalReserved)), numberFormatter.format(totalReserved - totalUsed), numberFormatter.format((double) (100.0F * (float) (totalReserved - totalUsed) / (float) totalReserved)), numberFormatter.format(totalReserved)));
                                        if (line.indexOf(nullSignature) < 0) {
                                            continue;
                                        }

                                        line = in.readLine();
                                        if (line.indexOf(memoryOMSignature) < 0) {
                                            continue;
                                        }

                                        line = in.readLine();
                                        if (line.indexOf(nullSignature) < 0) {
                                            continue;
                                        }

                                        line = in.readLine();
                                        if (line.indexOf(memorySegmentSignature) < 0) {
                                            continue;
                                        }

                                        totalUsed = 0L;
                                        totalReserved = 0L;

                                        for (countIM = 0L; line.indexOf(memorySegmentSignature) >= 0; line = in.readLine()) {
                                            StringTokenizer st = new StringTokenizer(line);
                                            if (st.countTokens() != 7) {
                                                break;
                                            }

                                            st.nextToken();
                                            st.nextToken();
                                            String baseStr = st.nextToken();
                                            long base;
                                            if (baseStr.startsWith("0x")) {
                                                base = jinwooDecode(baseStr);
                                            } else {
                                                base = jinwooDecode("0x" + baseStr);
                                            }

                                            String allocStr = st.nextToken();
                                            if (allocStr.startsWith("0x")) {
                                                alloc = jinwooDecode(allocStr);
                                            } else {
                                                alloc = jinwooDecode("0x" + allocStr);
                                            }

                                            st.nextToken();
                                            st.nextToken();
                                            String reservedStr = st.nextToken();
                                            if (reservedStr.startsWith("0x")) {
                                                reserved = jinwooDecode(reservedStr);
                                            } else {
                                                reserved = jinwooDecode("0x" + reservedStr);
                                            }

                                            ++countIM;
                                            totalUsed += alloc - base;
                                            totalReserved += reserved;
                                        }

                                        if (SHOW_MEMORY_SEGMENT_ANALYSIS) {
                                            td.summary = td.summary + "<tr><td>Java Heap (Virtual)</td><td align=right>" + numberFormatter.format(countIM) + "</td><td align=right>" + numberFormatter.format(totalUsed) + "</td><td align=right>" + numberFormatter.format((double) (100.0F * (float) totalUsed / (float) totalReserved)) + "</td><td align=right>" + numberFormatter.format(totalReserved - totalUsed) + "</td><td align=right>" + numberFormatter.format((double) (100.0F * (float) (totalReserved - totalUsed) / (float) totalReserved)) + "</td><td align=right>" + numberFormatter.format(totalReserved) + "</td></tr>";
                                        }

                                        td.memorySegment.dataList.add(Arrays.asList("Java Heap (Virtual)", numberFormatter.format(countIM), numberFormatter.format(totalUsed), numberFormatter.format((double) (100.0F * (float) totalUsed / (float) totalReserved)), numberFormatter.format(totalReserved - totalUsed), numberFormatter.format((double) (100.0F * (float) (totalReserved - totalUsed) / (float) totalReserved)), numberFormatter.format(totalReserved)));
                                        sumCount += countIM;
                                        sumUsed += totalUsed;
                                        sumReserved += totalReserved;
                                        if (line.indexOf(nullSignature) < 0) {
                                            continue;
                                        }

                                        line = in.readLine();
                                        if (line.indexOf(memoryCMSignature) < 0) {
                                            continue;
                                        }

                                        line = in.readLine();
                                        if (line.indexOf(nullSignature) < 0) {
                                            continue;
                                        }

                                        line = in.readLine();
                                        if (line.indexOf(memorySegmentSignature) < 0) {
                                            continue;
                                        }

                                        totalUsed = 0L;
                                        totalReserved = 0L;

                                        for (countIM = 0L; line.indexOf(memorySegmentSignature) >= 0; line = in.readLine()) {
                                            StringTokenizer st = new StringTokenizer(line);
                                            if (st.countTokens() != 7) {
                                                break;
                                            }

                                            st.nextToken();
                                            st.nextToken();
                                            String baseStr = st.nextToken();
                                            long base;
                                            if (baseStr.startsWith("0x")) {
                                                base = jinwooDecode(baseStr);
                                            } else {
                                                base = jinwooDecode("0x" + baseStr);
                                            }

                                            String allocStr = st.nextToken();
                                            if (allocStr.startsWith("0x")) {
                                                alloc = jinwooDecode(allocStr);
                                            } else {
                                                alloc = jinwooDecode("0x" + allocStr);
                                            }

                                            st.nextToken();
                                            st.nextToken();
                                            String reservedStr = st.nextToken();
                                            if (reservedStr.startsWith("0x")) {
                                                reserved = jinwooDecode(reservedStr);
                                            } else {
                                                reserved = jinwooDecode("0x" + reservedStr);
                                            }

                                            ++countIM;
                                            totalUsed += alloc - base;
                                            totalReserved += reserved;
                                        }

                                        if (SHOW_MEMORY_SEGMENT_ANALYSIS) {
                                            td.summary = td.summary + "<tr><td>Class (Virtual)</td><td align=right>" + numberFormatter.format(countIM) + "</td><td align=right>" + numberFormatter.format(totalUsed) + "</td><td align=right>" + numberFormatter.format((double) (100.0F * (float) totalUsed / (float) totalReserved)) + "</td><td align=right>" + numberFormatter.format(totalReserved - totalUsed) + "</td><td align=right>" + numberFormatter.format((double) (100.0F * (float) (totalReserved - totalUsed) / (float) totalReserved)) + "</td><td align=right>" + numberFormatter.format(totalReserved) + "</td></tr>";
                                        }

                                        td.memorySegment.dataList.add(Arrays.asList("Class (Virtual)", numberFormatter.format(countIM), numberFormatter.format(totalUsed), numberFormatter.format((double) (100.0F * (float) totalUsed / (float) totalReserved)), numberFormatter.format(totalReserved - totalUsed), numberFormatter.format((double) (100.0F * (float) (totalReserved - totalUsed) / (float) totalReserved)), numberFormatter.format(totalReserved)));
                                        sumCount += countIM;
                                        sumUsed += totalUsed;
                                        sumReserved += totalReserved;
                                        if (line.indexOf(nullSignature) < 0) {
                                            continue;
                                        }

                                        line = in.readLine();
                                        if (line.indexOf(memoryJCSignature) < 0) {
                                            continue;
                                        }

                                        line = in.readLine();
                                        if (line.indexOf(nullSignature) < 0) {
                                            continue;
                                        }

                                        line = in.readLine();
                                        if (line.indexOf(memorySegmentSignature) < 0) {
                                            continue;
                                        }

                                        totalUsed = 0L;
                                        totalReserved = 0L;

                                        for (countIM = 0L; line.indexOf(memorySegmentSignature) >= 0; line = in.readLine()) {
                                            StringTokenizer st = new StringTokenizer(line);
                                            if (st.countTokens() != 7) {
                                                break;
                                            }

                                            st.nextToken();
                                            st.nextToken();
                                            String baseStr = st.nextToken();
                                            long base;
                                            if (baseStr.startsWith("0x")) {
                                                base = jinwooDecode(baseStr);
                                            } else {
                                                base = jinwooDecode("0x" + baseStr);
                                            }

                                            String allocStr = st.nextToken();
                                            if (allocStr.startsWith("0x")) {
                                                alloc = jinwooDecode(allocStr);
                                            } else {
                                                alloc = jinwooDecode("0x" + allocStr);
                                            }

                                            st.nextToken();
                                            st.nextToken();
                                            String reservedStr = st.nextToken();
                                            if (reservedStr.startsWith("0x")) {
                                                reserved = jinwooDecode(reservedStr);
                                            } else {
                                                reserved = jinwooDecode("0x" + reservedStr);
                                            }

                                            ++countIM;
                                            totalUsed += alloc - base;
                                            totalReserved += reserved;
                                        }

                                        if (line.startsWith("1STSEGLIMIT")) {
                                            line = in.readLine();
                                        }

                                        if (SHOW_MEMORY_SEGMENT_ANALYSIS) {
                                            td.summary = td.summary + "<tr><td>JIT Code Cache (Virtual)</td><td align=right>" + numberFormatter.format(countIM) + "</td><td align=right>" + numberFormatter.format(totalUsed) + "</td><td align=right>" + numberFormatter.format((double) (100.0F * (float) totalUsed / (float) totalReserved)) + "</td><td align=right>" + numberFormatter.format(totalReserved - totalUsed) + "</td><td align=right>" + numberFormatter.format((double) (100.0F * (float) (totalReserved - totalUsed) / (float) totalReserved)) + "</td><td align=right>" + numberFormatter.format(totalReserved) + "</td></tr>";
                                        }

                                        td.memorySegment.dataList.add(Arrays.asList("JIT Code Cache (Virtual)", numberFormatter.format(countIM), numberFormatter.format(totalUsed), numberFormatter.format((double) (100.0F * (float) totalUsed / (float) totalReserved)), numberFormatter.format(totalReserved - totalUsed), numberFormatter.format((double) (100.0F * (float) (totalReserved - totalUsed) / (float) totalReserved)), numberFormatter.format(totalReserved)));
                                        sumCount += countIM;
                                        sumUsed += totalUsed;
                                        sumReserved += totalReserved;
                                        if (line.indexOf(nullSignature) < 0) {
                                            continue;
                                        }

                                        line = in.readLine();
                                        if (line.indexOf(memoryJDSignature) < 0) {
                                            continue;
                                        }

                                        line = in.readLine();
                                        if (line.indexOf(nullSignature) < 0) {
                                            continue;
                                        }

                                        line = in.readLine();
                                        if (line.indexOf(memorySegmentSignature) < 0) {
                                            continue;
                                        }

                                        totalUsed = 0L;
                                        totalReserved = 0L;

                                        for (countIM = 0L; line.indexOf(memorySegmentSignature) >= 0; line = in.readLine()) {
                                            StringTokenizer st = new StringTokenizer(line);
                                            if (st.countTokens() != 7) {
                                                break;
                                            }

                                            st.nextToken();
                                            st.nextToken();
                                            String baseStr = st.nextToken();
                                            long base;
                                            if (baseStr.startsWith("0x")) {
                                                base = jinwooDecode(baseStr);
                                            } else {
                                                base = jinwooDecode("0x" + baseStr);
                                            }

                                            String allocStr = st.nextToken();
                                            if (allocStr.startsWith("0x")) {
                                                alloc = jinwooDecode(allocStr);
                                            } else {
                                                alloc = jinwooDecode("0x" + allocStr);
                                            }

                                            st.nextToken();
                                            st.nextToken();
                                            String reservedStr = st.nextToken();
                                            if (reservedStr.startsWith("0x")) {
                                                reserved = jinwooDecode(reservedStr);
                                            } else {
                                                reserved = jinwooDecode("0x" + reservedStr);
                                            }

                                            ++countIM;
                                            totalUsed += alloc - base;
                                            totalReserved += reserved;
                                        }

                                        if (line.startsWith("1STSEGLIMIT")) {
                                            line = in.readLine();
                                        }

                                        if (SHOW_MEMORY_SEGMENT_ANALYSIS) {
                                            td.summary = td.summary + "<tr><td>JIT Data Cache (Virtual)</td><td align=right>" + numberFormatter.format(countIM) + "</td><td align=right>" + numberFormatter.format(totalUsed) + "</td><td align=right>" + numberFormatter.format((double) (100.0F * (float) totalUsed / (float) totalReserved)) + "</td><td align=right>" + numberFormatter.format(totalReserved - totalUsed) + "</td><td align=right>" + numberFormatter.format((double) (100.0F * (float) (totalReserved - totalUsed) / (float) totalReserved)) + "</td><td align=right>" + numberFormatter.format(totalReserved) + "</td></tr>";
                                        }

                                        td.memorySegment.dataList.add(Arrays.asList("JIT Data Cache (Virtual)", numberFormatter.format(countIM), numberFormatter.format(totalUsed), numberFormatter.format((double) (100.0F * (float) totalUsed / (float) totalReserved)), numberFormatter.format(totalReserved - totalUsed), numberFormatter.format((double) (100.0F * (float) (totalReserved - totalUsed) / (float) totalReserved)), numberFormatter.format(totalReserved)));
                                        sumCount += countIM;
                                        sumUsed += totalUsed;
                                        sumReserved += totalReserved;
                                        if (SHOW_MEMORY_SEGMENT_ANALYSIS) {
                                            td.summary = td.summary + "<tr><td>Overall (Virtual)</td><td align=right>" + numberFormatter.format(sumCount) + "</td><td align=right>" + numberFormatter.format(sumUsed) + "</td><td align=right>" + numberFormatter.format((double) (100.0F * (float) sumUsed / (float) sumReserved)) + "</td><td align=right>" + numberFormatter.format(sumReserved - sumUsed) + "</td><td align=right>" + numberFormatter.format((double) (100.0F * (float) (sumReserved - sumUsed) / (float) sumReserved)) + "</td><td align=right>" + numberFormatter.format(sumReserved) + "</td></tr></table><BR><BR>";
                                        }

                                        td.memorySegment.dataList.add(Arrays.asList("Overall (Virtual)", numberFormatter.format(sumCount), numberFormatter.format(sumUsed), numberFormatter.format((double) (100.0F * (float) sumUsed / (float) sumReserved)), numberFormatter.format(sumReserved - sumUsed), numberFormatter.format((double) (100.0F * (float) (sumReserved - sumUsed) / (float) sumReserved)), numberFormatter.format(sumReserved)));
                                        break;
                                    }

                                    if (line.startsWith(commandLineSignature)) {
                                        String converted = line.replace('<', ' ');
                                        line = converted.replace('>', ' ');
                                        commandLine = "<LI>Command line :<BR>" + line.substring(commandLineSignature.length()).trim() + "<BR><BR>";
                                        tokens = line.split("\\s+");
                                        String xmx = "";
                                        String xms = "";
                                        String xmso = "";
                                        String Xscmx = "";
                                        String Xmt = "";
                                        String Xss = "";
                                        String Xscmaxaot = "";
                                        String Xscmaxjitdata = "";
                                        String Xscminaot = "";
                                        String Xscminjitdata = "";
                                        for (int l = 0; l < tokens.length; ++l) {
                                            if (tokens[l].startsWith("-Xscmaxaot")) {
                                                Xscmaxaot = "<LI>-Xscmaxaot (Maximum number of bytes in the cache that can be used for AOT data) : " + tokens[l].substring(10) + "<BR>";
                                            } else if (tokens[l].startsWith("-Xscmaxjitdata")) {
                                                Xscmaxjitdata = "<LI>-Xscmaxjitdata (Maximum number of bytes in the class cache that can be used for JIT data) : " + tokens[l].substring(14) + "<BR>";
                                            } else if (tokens[l].startsWith("-Xscminaot")) {
                                                Xscminaot = "<LI>-Xscminaot(Minimum number of bytes in the cache to reserve for AOT data) : " + tokens[l].substring(10) + "<BR>";
                                            } else if (tokens[l].startsWith("-Xscminjitdata")) {
                                                Xscminjitdata = "<LI>-Xscminjitdata (Minimum number of bytes in the class cache to reserve for JIT data) : " + tokens[l].substring(14) + "<BR>";
                                            } else if (!tokens[l].startsWith("-Xssi")) {
                                                if (tokens[l].startsWith("-Xss")) {
                                                    Xss = "<LI>-Xss (Maximum stack size for Java threads) : " + tokens[l].substring(4) + "<BR>";
                                                } else if (tokens[l].startsWith("-Xoss")) {
                                                    Xss = "<LI>-Xoss (Maximum stack size for Java threads) : " + tokens[l].substring(5) + "<BR>";
                                                } else if (tokens[l].startsWith("-Xmt")) {
                                                    Xmt = "<LI>-Xmt (Multitenant JVM process model) : Enabled<BR>";
                                                } else if (tokens[l].startsWith("-Xscmx")) {
                                                    Xscmx = "<LI>-Xscmx (Java class data sharing cache size) : " + tokens[l].substring(6) + "<BR>";
                                                } else if (!tokens[l].startsWith("-Xmxcl")) {
                                                    if (tokens[l].startsWith("-Xmx")) {
                                                        xmx = "<LI>Java Heap Information<BR><UL><LI>-Xmx (Maximum Java heap size) : " + tokens[l].substring(4) + "<BR>";
                                                    } else if (tokens[l].startsWith("-Xmso")) {
                                                        xmso = "<LI>-Xmso (Initial Java stack size for operating system threads) : " + tokens[l].substring(5) + "<BR>";
                                                    } else if (tokens[l].startsWith("-Xms")) {
                                                        xms = "<LI>-Xms (Initial Java heap size) : " + tokens[l].substring(4) + "<BR>";
                                                    }
                                                }
                                            }
                                        }

                                        if (xmx.length() != 0) {
                                            td.summary = td.summary + converted;
                                        }

                                        if (xms.length() != 0) {
                                            td.summary = td.summary + xms;
                                        }

                                        if (xmso.length() != 0) {
                                            td.summary = td.summary + xmso;
                                        }

                                        if (Xscmx.length() != 0) {
                                            td.summary = td.summary + Xscmx;
                                        }

                                        if (Xmt.length() != 0) {
                                            td.summary = td.summary + Xmt;
                                        }

                                        if (Xss.length() != 0) {
                                            td.summary = td.summary + Xss;
                                        }

                                        if (Xscmaxaot.length() != 0) {
                                            td.summary = td.summary + Xscmaxaot;
                                        }

                                        if (Xscmaxjitdata.length() != 0) {
                                            td.summary = td.summary + Xscmaxjitdata;
                                        }

                                        if (Xscminaot.length() != 0) {
                                            td.summary = td.summary + Xscminaot;
                                        }

                                        if (Xscminjitdata.length() != 0) {
                                            td.summary = td.summary + Xscminjitdata;
                                        }

                                        int i = line.lastIndexOf(" -Xmxcl");
                                        if (i != -1) {
                                            td.summary = td.summary + "<LI>Maximum class loader size : " + line.substring(i + 7, line.indexOf(" ", i + 7)) + "<BR>";
                                        }

                                        if (converted.length() != 0) {
                                            td.summary = td.summary + "</UL><BR>";
                                        }
                                        break;
                                    }

                                    if (line.startsWith(javaVersionSignature)) {
                                        if (line.indexOf(IBMJ9) >= 0) {
                                            td.isJ9 = true;
                                        }

                                        td.javaVersion = line.substring(javaVersionSignature.length()).trim();
                                        td.summary = td.summary + "<LI>Java version : " + td.javaVersion + "<BR><BR>";
                                        break;
                                    }

                                    if (line.startsWith(vmVersionSignature)) {
                                        td.summary = td.summary + "<LI>Virtual machine version : " + line.substring(vmVersionSignature.length()).trim() + "<BR><BR>";
                                        break;
                                    }

                                    if (line.startsWith(jitVersionSignature)) {
                                        td.summary = td.summary + "<LI>Just-In-Time(JIT) compiler switch, Ahead-Of-Time (AOT) compiler switch, Compiler version : " + line.substring(jitVersionSignature.length()).trim() + "<BR><BR>";
                                        break;
                                    }

                                    if (line.startsWith(gcVersionSignature)) {
                                        td.summary = td.summary + "<LI>Garbage collector version : " + line.substring(gcVersionSignature.length()).trim() + "<BR><BR>";
                                        break;
                                    }

                                    if (line.startsWith(signalSignature)) {
                                        if (line.contains(OOME)) {
                                            td.oome = true;
                                            td.summary = "<LI style=\"color:red\"><b>***WARNING*** " + line.substring(signalSignature.length()).trim() + "</b><BR><BR>" + td.summary;
                                        } else {
                                            td.summary = td.summary + "<LI>Cause of thread dump : " + line.substring(signalSignature.length()).trim() + "<BR><BR>";
                                        }
                                        break;
                                    }

                                    if (line.startsWith(signatureFileName)) {
                                        td.pid = this.getPid(line);
                                        if (td.pid != -1L) {
                                            td.summary = td.summary + "<LI>Process ID : " + td.pid + " (from file name)<BR><BR>";
                                        }
                                        break;
                                    }

                                    if (line.startsWith(deadlockThreadSignature)) {
                                        while (true) {
                                            if (line == null || line.startsWith(javacoreSignature) || line.startsWith(javacoreSignature2)) {
                                                break label2545;
                                            }

                                            if (line.startsWith(deadlockThreadSignature)) {
                                                int i = line.lastIndexOf("(");
                                                int j = line.lastIndexOf(")");
                                                if (i != -1 && j != -1) {
                                                    long systhread;
                                                    try {
                                                        systhread = jinwooDecode(line.substring(i + 1, j));
                                                    } catch (NumberFormatException var121) {
                                                        systhread = jinwooDecode("0x" + line.substring(i + 1, j));
                                                    }

                                                    Long newSysThread = new Long(systhread);
                                                    if (td.deadlock == null) {
                                                        td.deadlock = new ArrayList();
                                                    }

                                                    if (!td.deadlock.contains(newSysThread)) {
                                                        td.deadlock.add(newSysThread);
                                                    }
                                                }
                                            } else if (line.startsWith(nullSignature)) {
                                                if (td.deadlock == null) {
                                                    td.deadlock = new ArrayList();
                                                }

                                                td.deadlock.add(new Long(-1L));
                                            } else if (line.startsWith(errorSignature)) {
                                                td.warning = td.warning + "<LI style=\"color:red\"><b>***WARNING*** Thread dump error : </b>" + line.substring(errorSignature.length() + 1).trim() + "<BR><BR>";
                                            }

                                            line = in.readLine();
                                        }
                                    }

                                    if (line.startsWith(errorSignature)) {
                                        td.warning = td.warning + "<LI style=\"color:red\"><b>***WARNING*** Thread dump error : </b>" + line.substring(errorSignature.length() + 1).trim() + "<BR><BR>";
                                        break;
                                    }

//                                    byte k;
//                                    MonitorDump mon;
                                    if (line.startsWith(signatureHeapLock)) {
                                        int i = line.lastIndexOf(": owner \"");
                                        if (i == -1) {
                                            break;
                                        }

                                        if (this.debug) {
                                            System.out.println("Heap lock " + line);
                                        }

                                        int j = line.substring(0, i).lastIndexOf(" ");
                                        String name = "Heap lock";
                                        i = line.lastIndexOf(threadIDMonIDSignature);
                                        int k = 5;
                                        if (i == -1) {
                                            i = line.lastIndexOf("(");
                                            k = 1;
                                        }

                                        j = line.lastIndexOf(")");
                                        if (i == -1 || j == -1) {
                                            break;
                                        }

                                        long value = jinwooDecode("0x" + line.substring(i + k, j));
                                        MonitorDump mon = new MonitorDump(name, value, true);
                                        this.monList.add(this.monIndex++, mon);
                                        line = in.readLine();
                                        if (line.startsWith(signatureHeapLockWaiting)) {
                                            mon.waiting = new ArrayList();
                                            int waitIndex = 0;
                                            line = in.readLine();

                                            while (true) {
                                                if (line == null || !line.startsWith(waiterSignature)) {
                                                    break label2545;
                                                }

                                                i = line.lastIndexOf("(0x");
                                                k = 3;
                                                j = line.lastIndexOf(")");
                                                if (i == -1) {
                                                    i = line.lastIndexOf("(");
                                                    k = 1;
                                                }

                                                if (i != -1 && j != -1) {
                                                    mon.waiting.add(waitIndex++, new Long(jinwooDecode("0x" + line.substring(i + k, j))));
                                                }

                                                line = in.readLine();
                                            }
                                        }

                                        --this.monIndex;
                                        if (this.monIndex >= 0) {
                                            this.monList.remove(this.monIndex);
                                        } else {
                                            System.out.println("Error: invalid monIndex," + this.monIndex);
                                        }
                                        break;
                                    }

                                    if (!line.startsWith(monSignature) && !line.startsWith(monSignatureSystem)) {
                                        if (line.startsWith(threadHeader1)) {
                                            int i = line.indexOf(threadNativeIDSignatureIBM6);
                                            if (i >= 0) {
                                                int end = line.indexOf(",", i);
                                                if (end >= 0 && oldLine != null && !oldLine.contains(threadHeaderAnonymousNativeThread)) {
                                                    try {
                                                        td.nid[totalThread - 1] = jinwooDecode(line.substring(i + threadNativeIDSignatureIBM6.length(), end));
                                                    } catch (NumberFormatException var120) {
                                                        td.nid[totalThread - 1] = jinwooDecode("0x" + line.substring(i + threadNativeIDSignatureIBM6.length(), end));
                                                    }
                                                }
                                            }
                                            break;
                                        }

                                        if (line.startsWith(threadHeaderAlone) && !line.contains(threadHeaderAnonymousNativeThread) && !threadLine.contains(line) && this.isValidThread(line, td)) {
                                            threadLine.add(line);
                                            inAnonymous = false;
                                            int i = line.indexOf(threadStateSignature);
                                            if (i != -1) {
                                                int index_nid = line.indexOf(threadNativeIDSignature, i);
                                                String stateString = null;
                                                if (index_nid >= 0) {
                                                    stateString = line.substring(i + threadStateSignature.length(), index_nid);
                                                    int end = line.indexOf(")", index_nid);
                                                    if (end >= 0) {
                                                        try {
                                                            td.nid[totalThread] = jinwooDecode(line.substring(index_nid + threadNativeIDSignature.length(), end));
                                                        } catch (NumberFormatException var119) {
                                                            td.nid[totalThread] = jinwooDecode("0x" + line.substring(index_nid + threadNativeIDSignature.length(), end));
                                                        }
                                                    }
                                                } else {
                                                    stateString = line.substring(i + threadStateSignature.length(), line.indexOf(", prio=", i));
                                                }

                                                int stateInt = 4;
                                                if (stateString.compareTo("R") != 0 && stateString.compareTo("<21>") != 0) {
                                                    if (stateString.compareTo("CW") != 0 && stateString.compareTo("<24>") != 0 && stateString.compareTo("<23>") != 0) {
                                                        if (stateString.compareTo("MW") == 0) {
                                                            stateInt = 2;
                                                        } else if (stateString.compareTo("S") == 0) {
                                                            stateInt = 3;
                                                        } else if (stateString.compareTo("P") == 0) {
                                                            stateInt = 6;
                                                        } else {
                                                            stateInt = 5;
                                                        }
                                                    } else {
                                                        stateInt = 1;
                                                    }
                                                } else {
                                                    ++td.numberOfRunnable;
                                                    stateInt = 0;
                                                }

                                                td.state[totalThread] = stateInt;
                                                if (this.debug) {
                                                    System.out.println("Setting state = " + stateInt + " for ID " + totalThread + " for line " + line);
                                                }

                                                int j = line.indexOf(sys_threadSignature);
                                                if (j != -1) {
                                                    if (line.substring(j + sys_threadSignature.length(), i).startsWith("0x")) {
                                                        td.sys_thread[totalThread] = jinwooDecode(line.substring(j + sys_threadSignature.length(), i));
                                                    } else {
                                                        td.sys_thread[totalThread] = jinwooDecode("0x" + line.substring(j + sys_threadSignature.length(), i));
                                                    }

                                                    if (this.debug) {
                                                        System.out.println("sys_thread[" + totalThread + "] = " + td.sys_thread[totalThread] + " (1)");
                                                    }
                                                } else {
                                                    j = line.indexOf(sys_threadSignature60);
                                                    if (j >= 0) {
                                                        String checkNewFormat = line.substring(j + sys_threadSignature60.length(), i);
                                                        int ci = checkNewFormat.indexOf(",");
                                                        if (ci >= 0) {
                                                            checkNewFormat = checkNewFormat.substring(0, ci);
                                                        }

                                                        if (checkNewFormat.startsWith("0x")) {
                                                            td.sys_thread[totalThread] = jinwooDecode(checkNewFormat);
                                                        } else {
                                                            td.sys_thread[totalThread] = jinwooDecode("0x" + checkNewFormat);
                                                        }

                                                        if (this.debug) {
                                                            System.out.println("sys_thread[" + totalThread + "] = " + td.sys_thread[totalThread] + " (2)");
                                                        }
                                                    }
                                                }
                                            }

                                            i = line.indexOf(threadIDSignature);
                                            if (i != -1) {
                                                td.name[totalThread] = line.substring(line.indexOf("\"") + 1, i);

                                                try {
                                                    td.tid[totalThread] = jinwooDecode(line.substring(i + threadIDSignature.length(), line.indexOf(",", i)));
                                                } catch (NumberFormatException var118) {
                                                    td.tid[totalThread] = jinwooDecode("0x" + line.substring(i + threadIDSignature.length(), line.indexOf(",", i)));
                                                }

                                                if (this.debug) {
                                                    System.out.println("Adding thread1 " + totalThread + ": " + td.name[totalThread] + "; " + td.tid[totalThread]);
                                                }

                                                ++totalThread;
                                            } else {
                                                i = line.indexOf(threadIDSignature60New);
                                                if (i >= 0) {
                                                    td.name[totalThread] = line.substring(line.indexOf("\"") + 1, i);

                                                    try {
                                                        td.tid[totalThread] = jinwooDecode(line.substring(i + threadIDSignature60New.length(), line.indexOf(",", i)));
                                                    } catch (NumberFormatException var117) {
                                                        td.tid[totalThread] = jinwooDecode("0x" + line.substring(i + threadIDSignature60New.length(), line.indexOf(",", i)));
                                                    }

                                                    if (this.debug) {
                                                        System.out.println("Adding thread2 " + totalThread + ": " + td.name[totalThread] + "; " + td.tid[totalThread]);
                                                    }

                                                    ++totalThread;
                                                } else {
                                                    i = line.indexOf(threadIDSignature60Old);
                                                    if (i >= 0) {
                                                        td.name[totalThread] = line.substring(line.indexOf("\"") + 1, i);

                                                        try {
                                                            td.tid[totalThread] = jinwooDecode(line.substring(i + threadIDSignature60Old.length(), line.indexOf(",", i)));
                                                        } catch (NumberFormatException var116) {
                                                            td.tid[totalThread] = jinwooDecode("0x" + line.substring(i + threadIDSignature60Old.length(), line.indexOf(",", i)));
                                                        }

                                                        if (this.debug) {
                                                            System.out.println("Adding thread3 " + totalThread + ": " + td.name[totalThread] + "; " + td.tid[totalThread]);
                                                        }

                                                        ++totalThread;
                                                    }
                                                }
                                            }

                                            if (td.sys_thread[totalThread - 1] == 0L) {
                                                td.sys_thread[totalThread - 1] = td.tid[totalThread - 1];
                                            }

                                            if (currentThread) {
                                                if (totalThread > 0) {
                                                    td.currentTid = td.tid[totalThread - 1];
                                                    td.currentThreadIndex = totalThread - 1;
                                                }

                                                currentThread = false;
                                            }
                                            break;
                                        }

                                        if (line.startsWith(threadHeaderAlone) && line.contains(threadHeaderAnonymousNativeThread)) {
                                            inAnonymous = true;
                                        }
                                        break;
                                    }

                                    int i = line.indexOf(monSignature2);
                                    if (i != -1) {
                                        int j = line.lastIndexOf(",");
                                        if (j == -1) {
                                            break;
                                        }

                                        Long valueLong = (Long) this.threadIdent.get(new Long(jinwooDecode("0x" + line.substring(i + monSignature2.length(), j))));
                                        i = line.indexOf(": Flat");
                                        if (i == -1 || valueLong == null) {
                                            break;
                                        }

                                        if (this.debug) {
                                            System.out.println("Flat lock: " + line);
                                        }

                                        j = line.substring(0, i).lastIndexOf(" ");
                                        String name = line.substring(j + 1, i);
                                        MonitorDump mon = new MonitorDump(name, valueLong);
                                        this.monList.add(this.monIndex++, mon);
                                        line = in.readLine();
                                        if (!line.startsWith(notifySignature0)) {
                                            break;
                                        }

                                        mon.waiting = new ArrayList();
                                        int waitIndex = 0;
                                        line = in.readLine();

                                        while (true) {
                                            if (line == null || !line.startsWith(notifySignature)) {
                                                break label2545;
                                            }

                                            i = line.lastIndexOf("(0x");
                                            int k = 3;
                                            j = line.lastIndexOf(")");
                                            if (i == -1) {
                                                i = line.lastIndexOf("(");
                                                k = 1;
                                            }

                                            if (i != -1 && j != -1) {
                                                mon.waiting.add(waitIndex++, new Long(jinwooDecode("0x" + line.substring(i + k, j))));
                                            }

                                            line = in.readLine();
                                        }
                                    }

                                    i = line.lastIndexOf(": owner \"");
                                    if (i != -1) {
                                        if (this.debug) {
                                            System.out.println("Monitor with owner: " + line);
                                        }

                                        if (td.isJ9) {
                                            if (this.debug) {
                                                System.out.println("J9");
                                            }

                                            td.isNewFormat = true;
                                        }

                                        int j = line.substring(0, i).lastIndexOf(" ");
                                        String name = null;
                                        if (line.startsWith(monSignatureSystem)) {
                                            String sysMon = line.substring(monSignatureSystem.length());
                                            sysMon = sysMon.trim();
                                            name = sysMon.substring(0, sysMon.lastIndexOf(": owner \""));
                                        } else {
                                            name = line.substring(j + 1, i);
                                        }

                                        if (this.debug) {
                                            System.out.println("Monitor Name: " + name);
                                        }

                                        int x = i;
                                        i = line.substring(i).lastIndexOf("(0x");
                                        int k = 3;
                                        if (i == -1) {
                                            i = line.lastIndexOf("(J9VMThread:0x");
                                            if (i == -1) {
                                                i = line.lastIndexOf("(");
                                                k = 1;
                                            } else {
                                                k = "(J9VMThread:0x".length();
                                            }
                                        } else {
                                            i += x;
                                        }

                                        j = line.lastIndexOf(")");
                                        if (i == -1 || j == -1) {
                                            break;
                                        }

                                        long value = jinwooDecode("0x" + line.substring(i + k, j));
                                        if (this.debug) {
                                            System.out.println("Monitor Owner: " + value);
                                        }

                                        MonitorDump mon = new MonitorDump(name, value);
                                        this.monList.add(this.monIndex++, mon);
                                        line = in.readLine();
                                        if (this.debug) {
                                            System.out.println("Next Line: " + line);
                                        }

                                        if (line.startsWith(notifySignature0)) {
                                            mon.waiting = new ArrayList();
                                            int waitIndex = 0;
                                            line = in.readLine();

                                            while (true) {
                                                if (line == null || !line.startsWith(notifySignature)) {
                                                    break label2545;
                                                }

                                                i = line.lastIndexOf("(0x");
                                                k = 3;
                                                j = line.lastIndexOf(")");
                                                if (i == -1) {
                                                    i = line.lastIndexOf("(J9VMThread:0x");
                                                    if (i == -1) {
                                                        i = line.lastIndexOf("(");
                                                        k = 1;
                                                    } else {
                                                        k = "(J9VMThread:0x".length();
                                                    }
                                                }

                                                if (i != -1 && j != -1) {
                                                    if (this.debug) {
                                                        System.out.println("Add monitor waiter: " + jinwooDecode("0x" + line.substring(i + k, j)));
                                                    }

                                                    mon.waiting.add(waitIndex++, new Long(jinwooDecode("0x" + line.substring(i + k, j))));
                                                }

                                                line = in.readLine();
                                            }
                                        }

                                        if (line.startsWith(waiterqSignature)) {
                                            mon.waiting = new ArrayList();
                                            int waitIndex = 0;
                                            line = in.readLine();
                                            if (this.debug) {
                                                System.out.println("Next Line: " + line);
                                            }

                                            while (true) {
                                                if (line == null || !line.startsWith(waiterSignature)) {
                                                    break label2545;
                                                }

                                                i = line.lastIndexOf("(0x");
                                                k = 3;
                                                j = line.lastIndexOf(")");
                                                if (i == -1) {
                                                    i = line.lastIndexOf("(J9VMThread:0x");
                                                    if (i == -1) {
                                                        i = line.lastIndexOf("(");
                                                        k = 1;
                                                    } else {
                                                        k = "(J9VMThread:0x".length();
                                                    }
                                                }

                                                if (this.debug) {
                                                    System.out.println("Monitor Waiter: " + new Long(jinwooDecode("0x" + line.substring(i + k, j))));
                                                }

                                                if (i != -1 && j != -1) {
                                                    mon.waiting.add(waitIndex++, new Long(jinwooDecode("0x" + line.substring(i + k, j))));
                                                    if (this.debug) {
                                                        System.out.println("Adding monitor " + jinwooDecode("0x" + line.substring(i + k, j)));
                                                    }
                                                }

                                                line = in.readLine();
                                            }
                                        }

                                        --this.monIndex;
                                        if (this.monIndex >= 0) {
                                            this.monList.remove(this.monIndex);
                                        } else {
                                            System.out.println("Error: invalid monIndex," + this.monIndex);
                                        }
                                        break;
                                    }

                                    i = line.indexOf(monSignatureJ9);
                                    if (i < 0) {
                                        break;
                                    }

                                    if (this.debug) {
                                        System.out.println("Flat lock: " + line);
                                    }

                                    td.isNewFormat = true;
                                    i = line.lastIndexOf("(J9VMThread:");
                                    if (i == -1) {
                                        i = line.lastIndexOf("(");
                                    } else {
                                        i += "(J9VMThread:".length() - 1;
                                    }

                                    int j = line.lastIndexOf(")");
                                    if (i <= -1 || j <= -1 || j <= i) {
                                        break;
                                    }

                                    long value = jinwooDecode(line.substring(i + 1, j));
                                    i = line.indexOf(": Flat");
                                    if (i == -1) {
                                        break;
                                    }

                                    String name = null;
                                    if (line.startsWith(monSignatureSystem)) {
                                        String sysMon = line.substring(monSignatureSystem.length());
                                        sysMon = sysMon.trim();
                                        name = sysMon.substring(0, sysMon.indexOf(": Flat"));
                                    } else {
                                        j = line.substring(0, i).lastIndexOf(" ");
                                        name = line.substring(j + 1, i);
                                    }

                                    MonitorDump mon = new MonitorDump(name, value);
                                    this.monList.add(this.monIndex++, mon);
                                    line = in.readLine();
                                    if (this.debug) {
                                        System.out.println("Next line: " + line);
                                    }

                                    if (line.startsWith(waiterqSignature)) {
                                        mon.waiting = new ArrayList();
                                        int waitIndex = 0;
                                        line = in.readLine();

                                        while (true) {
                                            if (line == null || !line.startsWith(waiterSignature)) {
                                                break label2545;
                                            }

                                            i = line.lastIndexOf("(0x");
                                            int k = 3;
                                            j = line.lastIndexOf(")");
                                            if (i == -1) {
                                                i = line.lastIndexOf("(J9VMThread:0x");
                                                if (i == -1) {
                                                    i = line.lastIndexOf("(");
                                                    k = 1;
                                                } else {
                                                    k = "(J9VMThread:0x".length();
                                                }
                                            }

                                            if (i != -1 && j != -1) {
                                                mon.waiting.add(waitIndex++, new Long(jinwooDecode("0x" + line.substring(i + k, j))));
                                            }

                                            line = in.readLine();
                                        }
                                    }

                                    --this.monIndex;
                                    if (this.monIndex >= 0) {
                                        this.monList.remove(this.monIndex);
                                    } else {
                                        System.out.println("Error: invalid monIndex," + this.monIndex);
                                    }
                                    break;
                                }
                            }

                            if (line.startsWith(CPUTIME)) {
                                String[] sp = line.split(",");

                                for (int l = 0; l < sp.length && l <= 2; ++l) {
                                    int col = sp[l].lastIndexOf(':');
                                    if (col >= 0 && sp[l].length() > 5 && col + 2 < sp[l].length()) {
                                        String v = sp[l].substring(col + 2, sp[l].length() - 5);
                                        if (td.cpu == null) {
                                            td.cpu = new float[td.javaStack.length][3];
                                        }

                                        td.cpu[totalThread - 1][l] = Float.parseFloat(v);
                                    }
                                }
                            } else if (line.startsWith(stackTraceHeader)) {
                                int i = line.indexOf("at ");
                                if (i != -1 && totalThread != 0) {
                                    if (td.javaStack[totalThread - 1] == null) {
                                        td.javaStack[totalThread - 1] = new String();
                                    }

                                    td.javaStack[totalThread - 1] = td.javaStack[totalThread - 1] + line.substring(i) + "<BR>";
                                    td.javaStackDepth[totalThread - 1]++;
                                }
                            } else if (line.startsWith(signatureMore)) {
                                if (totalThread != 0) {
                                    if (td.javaStack[totalThread - 1] == null) {
                                        td.javaStack[totalThread - 1] = new String();
                                    }

                                    td.javaStack[totalThread - 1] = td.javaStack[totalThread - 1] + "... (more frames not shown in thread dump)<BR>";
                                    td.javaStackDepth[totalThread - 1]++;
                                }
                            } else if (line.startsWith(stackLineHeader)) {
                                int i = line.indexOf("at ");
                                if (i != -1 && totalThread != 0) {
                                    if (td.nativeStack[totalThread - 1] == null) {
                                        td.nativeStack[totalThread - 1] = new String();
                                    }

                                    td.nativeStack[totalThread - 1] = td.nativeStack[totalThread - 1] + line.substring(i) + "<BR>";
                                }
                            } else if (line.startsWith(stackLineHeader2)) {
                                int i = this.indexOfContent(line);
                                if (i != -1 && totalThread != 0) {
                                    if (td.nativeStack[totalThread - 1] == null) {
                                        td.nativeStack[totalThread - 1] = new String();
                                    }

                                    td.nativeStack[totalThread - 1] = td.nativeStack[totalThread - 1] + line.substring(i) + "<BR>";
                                }
                            } else if (line.startsWith(stackLineHeaderIBM6) && !inAnonymous) {
                                int i = this.indexOfContent(line);
                                if (i != -1 && totalThread != 0) {
                                    if (td.nativeStack[totalThread - 1] == null) {
                                        td.nativeStack[totalThread - 1] = new String();
                                    }

                                    td.nativeStack[totalThread - 1] = td.nativeStack[totalThread - 1] + line.substring(i) + "<BR>";
                                }
                            } else if (line.startsWith("3XMTHREADBLOCK") && line.contains("Parked on") && !line.contains("Owned by: <unknown>") && !line.contains("Owned by: <unowned>")) {
                                if (this.debug) {
                                    System.out.println("Thread parked: " + line);
                                }

                                String monitorName = line.substring(line.indexOf("Parked on: ") + "Parked on: ".length());
                                monitorName = monitorName.substring(0, monitorName.indexOf(' '));
                                if (this.debug) {
                                    System.out.println("Monitor name: \"" + monitorName + "\"");
                                }

                                String owningThreadName = line.substring(line.indexOf("Owned by: \"") + "Owned by: \"".length());

                                try {
                                    owningThreadName = owningThreadName.substring(0, owningThreadName.indexOf("\" (J9VMThread:"));
                                    if (this.debug) {
                                        System.out.println("Owning thread name: \"" + owningThreadName + "\"");
                                    }

                                    String owningThreadIdString = line.substring(line.indexOf("J9VMThread:") + "J9VMThread:".length());
                                    owningThreadIdString = owningThreadIdString.substring(0, owningThreadIdString.indexOf(44)).trim();
                                    if (!owningThreadIdString.equals("<null>")) {
                                        long owningThreadId = jinwooDecode(owningThreadIdString);
                                        if (this.debug) {
                                            System.out.println("Owning thread ID: \"" + owningThreadId + "\"");
                                        }

                                        MonitorDump mon = null;
                                        for (Iterator iterator = monList.iterator(); iterator.hasNext(); ) {
                                            Object obj = iterator.next();
                                            MonitorDump existingMon = (MonitorDump) obj;
                                            if (existingMon.objectName.equals(monitorName))
                                                mon = existingMon;
                                        }

                                        if (mon == null) {
                                            mon = new MonitorDump(owningThreadIdString, owningThreadId, false);
                                            this.monList.add(mon);
                                            mon.waiting = new ArrayList();
                                        }

                                        Long waitingThreadId = td.tid[totalThread - 1];
                                        boolean alreadyHasLock = false;

                                        for (Iterator iterator1 = mon.waiting.iterator(); iterator1.hasNext(); ) {
                                            Object obj = iterator1.next();
                                            if (obj instanceof Long) {
                                                Long existingWaitingThreadId = (Long) obj;
                                                if (debug)
                                                    System.out.println((new StringBuilder("Existing waiting thread ID: ")).append(existingWaitingThreadId).toString());
                                                if (existingWaitingThreadId == waitingThreadId)
                                                    alreadyHasLock = true;
                                            }
                                        }

                                        if (!alreadyHasLock) {
                                            mon.waiting.add(waitingThreadId);
                                        }
                                    }
                                } catch (Throwable var133) {
                                    td.warning = td.warning + "<LI style=\"color:red\"><b>***WARNING*** " + in.getLastLineExceptionDiagnostics(var133) + "<BR><BR>";
                                }
                            }

                            oldLine = line;
                            line = in.readLine();
                            if (line != null) {
                                fileLocation += (long) line.length();
                            }

                            this.current = (int) (100.0F * (float) in.getLinesRead() / (float) totalLine);
                            this.overall = 30 + (int) (70.0F * (float) fileLocation / (float) fileSize);
                        }
                    } else {
                        for (; line != null; this.overall = 30 + (int) (70.0F * (float) fileLocation / (float) fileSize)) {
                            if (line.indexOf(threadDumpSignature) >= 0) {
                                if (td != null) {
                                    threadInfo.threadDumps.add(td);

                                    td = null;
                                }

                                int threadSize = (Integer) list.get(dumpIndex++);
                                td = new ThreadDump();
                                td.xmx = -1L;
                                td.javaHeap = -1;
                                td.warning = "";
                                td.name = new String[threadSize];
                                td.nid = new long[threadSize];
                                td.state = new int[threadSize];
                                td.isDeadlock = new boolean[threadSize];
                                td.priority = new int[threadSize];
                                td.javaStack = new String[threadSize];
                                td.javaStackDepth = new int[threadSize];
                                td.macro = new int[threadSize];
                                td.pattern = new String[threadSize];
                                td.nativeStack = new String[threadSize];
                                td.sys_thread = new long[threadSize];
                                td.tid = new long[threadSize];
                                td.summary = "<LI>File name : " + file[z].getAbsolutePath() + "<BR><BR>";
                                totalThread = 0;
                                td.fileName = file[z].getName() + "_" + dumpIndex;
                                td.pid = -1L;
                                td.free = -1L;
                                td.allocated = -1L;
                                td.af = -1L;
                                td.gc = -1L;
                                td.timeStamp = -1L;
                                Date d = null;
                                if (oldLine != null) {
                                    oldLine = oldLine.trim();
                                    if (oldLine.length() > 0 && oldLine.charAt(0) >= '0' && oldLine.charAt(0) <= '9') {
                                        try {
                                            d = formatterSolaris.parse(oldLine);
                                        } catch (Exception e) {
                                            e.printStackTrace();
                                        }

                                        if (d != null) {
                                            td.timeStamp = d.getTime();
                                            td.summary = td.summary + "<LI>Timestamp : " + formatterSolaris.format(d) + "<BR><BR>";
                                        }
                                    }
                                }

                                if (d == null) {
                                    int start = line.indexOf('[');
                                    int end = line.indexOf(']');
                                    if (start + 1 > threadDumpSignature.length() && end > threadDumpSignature.length() && end > start + 1) {
                                        String org = line.substring(start + 1, end);
                                        tokens = org.split(" ");
                                        org = tokens[0] + " " + tokens[1] + " " + tokens[2] + " " + tokens[3] + " " + tokens[5];
                                        if (org != null) {
                                            try {
                                                d = formatterHP.parse(org);
                                            } catch (Exception e) {
                                                e.printStackTrace();
                                            }

                                            if (d != null) {
                                                td.timeStamp = d.getTime();
                                                td.summary = td.summary + "<LI>Timestamp : " + formatterHP.format(d) + "<BR><BR>";
                                            }
                                        }
                                    }
                                }
                            }

                            if (td != null) {
                                if (line.startsWith("\"") && line.indexOf(threadSignature) >= 0) {
                                    int i = line.indexOf(threadIDSignatureSolaris);
                                    if (i != -1) {
                                        td.name[totalThread] = line.substring(1, line.indexOf("\"", 1));

                                        try {
                                            td.tid[totalThread] = jinwooDecode(line.substring(i + threadIDSignatureSolaris.length(), line.indexOf(" n", i)));
                                            td.sys_thread[totalThread] = td.tid[totalThread];
                                        } catch (NumberFormatException var112) {
                                            td.tid[totalThread] = jinwooDecode("0x" + line.substring(i + threadIDSignatureSolaris.length(), line.indexOf(" n", i)));
                                            td.sys_thread[totalThread] = td.tid[totalThread];
                                        }

                                        if (this.debug) {
                                            System.out.println("sys_thread[" + totalThread + "] = " + td.sys_thread[totalThread] + " 31)");
                                        }
                                    }

                                    i = line.indexOf(threadNativeIDSignatureSolaris);
                                    if (i != -1) {
                                        td.nid[totalThread] = (long) Integer.decode(line.substring(i + threadNativeIDSignatureSolaris.length(), line.indexOf(" ", i + 1)));
                                        String stateString = line.substring(line.indexOf(" ", i + 1) + 1);
                                        if (stateString.startsWith(lwp_id)) {
                                            stateString = stateString.substring(stateString.indexOf(" ") + 1);
                                        }

                                        int stateInt = 4;
                                        if (stateString.startsWith("runnable")) {
                                            ++td.numberOfRunnable;
                                            stateInt = 0;
                                        } else if (stateString.startsWith("waiting on condition")) {
                                            stateInt = 1;
                                        } else if (!stateString.startsWith("waiting on monitor") && !stateString.startsWith("waiting for monitor")) {
                                            if (stateString.startsWith("suspended")) {
                                                stateInt = 3;
                                            } else if (stateString.startsWith("in Object.wait")) {
                                                stateInt = 4;
                                            } else {
                                                stateInt = 5;
                                            }
                                        } else {
                                            stateInt = 2;
                                        }

                                        td.state[totalThread] = stateInt;
                                    }

                                    if (this.debug) {
                                        System.out.println("Adding thread4 " + totalThread + ": " + td.name[totalThread] + "; " + td.tid[totalThread]);
                                    }

                                    ++totalThread;
                                } else if (line.startsWith("\"") && line.indexOf(threadSignature2) >= 0) {
                                    td.name[totalThread] = line.substring(1, line.indexOf("\"", 1));
                                    int t = line.indexOf(threadSignature2);
                                    td.nid[totalThread] = (long) Integer.decode(line.substring(t + threadSignature2.length()));
                                    ++totalThread;
                                }

                                if (!line.startsWith(stackTraceHeaderSolaris) && !line.startsWith(stackTraceHeaderSolaris2)) {
                                    if (!line.startsWith(stackTraceHeaderHPlocked) && !line.startsWith(stackTraceHeaderHPlocked2) && !line.startsWith(stackTraceHeaderHPwaiting1) && !line.startsWith(stackTraceHeaderHPwaiting1b) && !line.startsWith(stackTraceHeaderHPwaiting2) && !line.startsWith(stackTraceHeaderHPwaiting2b)) {
                                        if (line.startsWith(deadlockSignatureSolaris131) || line.startsWith(deadlockSignatureSolaris142)) {
                                            for (; line != null && (!line.startsWith("Found ") || !line.endsWith(" deadlock.")) && (!line.startsWith("Found ") || !line.endsWith(" deadlocks.")); line = in.readLine()) {
                                                if (line.startsWith("\"")) {
                                                    int i = line.indexOf("\":");
                                                    if (i != -1) {
                                                        if (td.deadlock == null) {
                                                            td.deadlock = new ArrayList();
                                                        }

                                                        String tempS = line.substring(1, i);
                                                        if (!td.deadlock.contains(tempS)) {
                                                            td.deadlock.add(tempS);
                                                        }
                                                    }
                                                } else if (!line.startsWith(signatureWaiting) && !line.startsWith(signatureLocked)) {
                                                    line.startsWith(signatureHeld);
                                                }
                                            }

                                            if (td.deadlock != null && td.deadlock.size() != 0) {
                                                td.warning = td.warning + "<LI style=\"color:red\"><b>***WARNING*** Deadlock detected in </b><br>";

                                                for (int q = 0; q < td.deadlock.size(); ++q) {
                                                    td.warning = td.warning + "  [" + td.deadlock.get(q) + "]";
                                                }

                                                td.warning = td.warning + "<BR><BR>";
                                            }
                                        }
                                    } else {
                                        int i = line.indexOf("- ");
                                        if (i != -1 && totalThread != 0) {
                                            if (td.javaStack[totalThread - 1] == null) {
                                                td.javaStack[totalThread - 1] = new String();
                                            }

                                            String newStr = line.substring(i);
                                            newStr = newStr.replace('<', '[');
                                            newStr = newStr.replace('>', ']');
                                            td.javaStack[totalThread - 1] = td.javaStack[totalThread - 1] + newStr + "<BR>";
                                        }
                                    }
                                } else {
                                    int i = line.indexOf("at ");
                                    if (i != -1 && totalThread != 0) {
                                        if (td.javaStack[totalThread - 1] == null) {
                                            td.javaStack[totalThread - 1] = new String();
                                        }

                                        td.javaStack[totalThread - 1] = td.javaStack[totalThread - 1] + line.substring(i) + "<BR>";
                                        td.javaStackDepth[totalThread - 1]++;
                                    }
                                }
                            }

                            oldLine = line;
                            line = in.readLine();
                            if (line != null) {
                                fileLocation += (long) line.length();
                            }

                            this.current = (int) (100.0F * (float) in.getLinesRead() / (float) totalLine);
                        }

                        if (td != null) {
                            threadInfo.threadDumps.add(td);
                        }
                    }

                    in.close();
                    if (this.monList.size() == 0) {
                        td.mdump = null;
                    } else {
                        td.mdump = (MonitorDump[]) this.monList.toArray(new MonitorDump[this.monList.size()]);
                        if (td.isNewFormat) {
                            for (int i = 0; i < td.mdump.length; ++i) {
                                if (td.mdump[i].waiting != null) {
                                    long monitorOwner = td.getSys_ThreadFromTID(td.mdump[i].owner);
                                    if (this.debug) {
                                        System.out.println("Monitor owner = " + monitorOwner + " tid = " + td.mdump[i].owner + " waiting = " + td.mdump[i].waiting);
                                    }

                                    if (monitorOwner != -1L) {
                                        td.mdump[i].owner = monitorOwner;
                                        List newList = new ArrayList();

                                        for (int j = 0; j < td.mdump[i].waiting.size(); ++j) {
                                            newList.add(new Long(td.getSys_ThreadFromTID((Long) td.mdump[i].waiting.get(j))));
                                            if (this.debug) {
                                                System.out.println("Converted ID: " + newList.get(newList.size() - 1));
                                            }
                                        }

                                        td.mdump[i].waiting = newList;
                                    }
                                }
                            }
                        }
                    }
                } catch (Exception var134) {
                    System.out.println(in.getLastLineExceptionDiagnostics(var134));
                    continue;
                }

                this.monList = null;
                this.threadIdent.clear();
                this.threadIdent = null;
            }
        }

        int size;
        size = threadInfo.threadDumps.size();

        for (int i = 0; i < size; ++i) {
            ThreadDump td;
            td = (ThreadDump) threadInfo.threadDumps.get(i);

//            int x;
            if (!td.isIBM) {
                this.monIndex = 0;
                int nameIndex = 0;
                List nameList = new ArrayList();
                this.monList = new ArrayList();
                int j = 0;

                while (true) {
//                    String tempString;
//                    String name;
                    if (j >= td.javaStack.length) {
                        for (j = 0; j < td.javaStack.length; ++j) {
                            if (td.javaStack[j] != null) {
                                int k = td.javaStack[j].indexOf(signatureSolarisLockAfter);

                                String tempString = td.javaStack[j];
                                for (; k != -1; k = tempString.indexOf(signatureSolarisLockAfter)) {
                                    tempString = tempString.substring(k + signatureSolarisLock.length());
                                    int k2 = tempString.indexOf("] ");
                                    if (k2 == -1) {
                                        break;
                                    }

                                    String name = tempString.substring(0, k2);
                                    if (nameList.contains(name)) {
                                        int k3 = nameList.indexOf(name);
                                        MonitorDump mon = (MonitorDump) this.monList.get(k3);
                                        mon.owner = td.tid[j];
                                        if (td.deadlock != null && td.deadlock.contains(td.name[j])) {
                                            td.isDeadlock[j] = true;
                                        }
                                    }
                                }
                            }
                        }

                        if (this.monList.size() == 0) {
                            td.mdump = null;
                        } else {
                            td.mdump = (MonitorDump[]) this.monList.toArray(new MonitorDump[this.monList.size()]);
                        }
                        break;
                    }

                    if (td.javaStack[j] != null) {
                        int k = td.javaStack[j].indexOf(signatureSolarisWaitAfter);

                        String tempString = td.javaStack[j];
                        for (; k != -1; k = tempString.indexOf(signatureSolarisWaitAfter)) {
                            tempString = tempString.substring(k + signatureSolarisWait.length());
                            int k2 = tempString.indexOf("] ");
                            if (k2 == -1) {
                                break;
                            }

                            String name = tempString.substring(0, k2);
                            if (!nameList.contains(name)) {
                                nameList.add(nameIndex++, name);
                                MonitorDump mon = new MonitorDump(name, -1L);
                                mon.waiting = new ArrayList();
                                this.monList.add(this.monIndex++, mon);
                                mon.waiting.add(new Long(td.tid[j]));
                            } else {
                                int k3 = nameList.indexOf(name);
                                MonitorDump mon = (MonitorDump) this.monList.get(k3);
                                if (mon.waiting == null) {
                                    mon.waiting = new ArrayList();
                                }

                                mon.waiting.add(new Long(td.tid[j]));
                            }
                        }
                    }

                    ++j;
                }
            }

            for (int x = 0; x < td.javaStack.length; ++x) {
                this.checkIdle(td.javaStack[x], x, td);
            }

        }

        this.done = true;

        //后置处理，解析出threadDump内的thread列表详细数据
        //合并threadDetail
        for (int i = 0; i < size; ++i) {
            ThreadDump td = (ThreadDump) threadInfo.threadDumps.get(i);
            td.threadDetailList = new ArrayList<>(totalThread);
            long totalThread1 = td.getTotalThread();
            for (int j = 0; j < totalThread1; j++) {
                ThreadDetail threadDetail = new ThreadDetail();
                threadDetail.name = td.name[j];
                threadDetail.pattern = td.pattern[j];
                threadDetail.isDeadlock = td.isDeadlock[j];
                threadDetail.nid = td.nid[j];
                threadDetail.state = td.state[j];
                threadDetail.stateValue = td.getState(j);
                threadDetail.priority = td.priority[j];
                threadDetail.javaStack = td.javaStack[j];
                if (td.javaStack[j] != null) {
                    threadDetail.javaStackList = Arrays.asList(td.javaStack[j].split("<BR>"));
                }
                threadDetail.currentMethod = td.getCurrentMethod(j);
                threadDetail.javaStackDepth = td.javaStackDepth[j];
                threadDetail.macro = td.macro[j];
                threadDetail.nativeStack = td.nativeStack[j];
                threadDetail.sys_thread = td.sys_thread[j];
                threadDetail.tid = td.tid[j];
                td.threadDetailList.add(threadDetail);
            }

            //计算各状态分布
            ThreadStateDistribute threadStateDistribute = new ThreadStateDistribute(td);
            td.threadStateDistribute = threadStateDistribute;

            //计算方法分布
            ThreadMethodDistribute threadMethodDistribute = new ThreadMethodDistribute(td);
            td.threadMethodDistribute = threadMethodDistribute;

            //计算聚合数据分布
            ThreadAggregationDistribute threadAggregationDistribute = new ThreadAggregationDistribute(td);
            td.threadAggregationDistribute = threadAggregationDistribute;
        }

//            return  this.generateReport(threadInfo);
        return null;
    }

    private String replaceEntities(String line) {
        line = line.replaceAll(Pattern.quote("&"), "&amp;");
        line = line.replaceAll(Pattern.quote("<"), "&lt;");
        line = line.replaceAll(Pattern.quote(">"), "&gt;");
        return line;
    }

    protected String splitPath(String vvalue, String javaVersion) {
        StringBuilder sb = new StringBuilder();
        sb.append("<html><b>");
        String del;
        if (javaVersion.toLowerCase().contains("win")) {
            del = ";";
        } else {
            del = ":";
        }

        String[] splits = vvalue.split(del);
        if (splits.length > 0) {
            for (int m = 0; m < splits.length; ++m) {
                String t = splits[m].trim();
                if (t.length() != 0) {
                    sb.append(t).append(del).append("<br>");
                }
            }
        }

        return sb.append("</html>").toString();
    }

    public void stop() {
        this.canceled = true;
        this.statMessage = null;
    }

    public String threadDetailTable(ThreadDump threadDump) {
        int selectedRow = threadDump.currentThreadIndex;
        String stackTrace = "<table border=\"1\"><tr><th><B>Thread Name</B></th><th>" + threadDump.getName(selectedRow) + "<tr><td><B>State</B></td><td>" + threadDump.getState(selectedRow);
        String m1 = threadDump.getOwningMonitors(selectedRow);
        String monString = "";
        if (m1 != null) {
            monString = "Owns Monitor Lock on " + m1;
        }

        m1 = threadDump.getWaitingMonitors(selectedRow);
        if (m1 != null) {
            if (monString.length() == 0) {
                monString = monString + "Waiting for Monitor Lock on " + m1;
            } else {
                monString = monString + "<BR>Waiting for Monitor Lock on " + m1;
            }
        }

        if (monString.length() == 0) {
            stackTrace = stackTrace + "</td></tr>";
        } else {
            stackTrace = stackTrace + "<tr><td><B>Monitor</B></td><td>" + monString + "</td></tr>";
        }

        if (threadDump.javaStack[selectedRow] != null) {
            stackTrace = stackTrace + "<tr><td><B>Java Stack</B></td><td>" + threadDump.javaStack[selectedRow] + "</td></tr>";
        } else {
            stackTrace = stackTrace + "<tr><td><B>Java Stack</B></td>No Java stack trace available</td></tr>";
        }

        if (threadDump.nativeStack[selectedRow] != null) {
            stackTrace = stackTrace + "<tr><td><B>Native Stack</B></td><td>" + threadDump.nativeStack[selectedRow] + "</td></tr>";
        } else {
            stackTrace = stackTrace + "<tr><td><B>Native Stack</B></td>No Native stack trace available</td></tr>";
        }

        if (stackTrace.length() == 0) {
            stackTrace = "<tr><td><B>Stack Trace</B></td>No stack trace available</td></tr>";
        }

        return ThreadFrame.getCopyFriendlyString(stackTrace + "</table>");
    }

    class BufferedReaderWrapper {
        private BufferedReader in;
        private long linesRead;
        private long totalBytesRead;
        private String lastLine;
        private int lastBytesRead;
        private File file;
        private int newlineBytes = Integer.getInteger("NEWLINE_BYTES", 1);

        BufferedReaderWrapper(File file) throws IOException {
            this.file = file;
            this.in = new BufferedReader(new InputStreamReader(Analyzer.getInputStream(this.file)));
        }

        public String readLine() throws IOException {
            this.lastLine = this.in.readLine();
            if (this.lastLine != null) {
                if (this.lastLine.startsWith(threadHeaderAll) && this.lastLine.contains("\"") && !this.lastLine.contains("state:") && FileTask.this.isValidThread(this.lastLine, (ThreadDump) null)) {
                    System.err.println("Note: Thread name has newlines: " + this.lastLine);

                    String additionalLine;
                    do {
                        additionalLine = this.in.readLine();
                        if (additionalLine == null) {
                            System.err.println("Warning: Reached end-of-file searching for the end of the thread");
                            break;
                        }

                        this.lastLine = this.lastLine + additionalLine;
                    } while (!additionalLine.contains("state:"));
                }

                ++this.linesRead;
                this.lastBytesRead = this.lastLine.getBytes().length + this.newlineBytes;
                this.totalBytesRead += (long) this.lastBytesRead;
            }

            return this.lastLine;
        }

        public void close() throws IOException {
            this.in.close();
        }

        public long getLinesRead() {
            return this.linesRead;
        }

        public long getBytesRead() {
            return this.totalBytesRead;
        }

        public String getLastLineExceptionDiagnostics(Throwable t) {
            t.printStackTrace();
            return "Exception (" + t.getMessage() + ") while processing " + this.getLastLineDiagnostics();
        }

        public String getLastLineDiagnostics() {
            long offset = this.totalBytesRead - (long) this.lastBytesRead;
            return "Last line read (# " + this.linesRead + ", offset " + offset + " or 0x" + Long.toHexString(offset) + ", length " + this.lastBytesRead + ", " + this.file.getName() + ")";
        }
    }
}
