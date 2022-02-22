//// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
//// Jad home page: http://www.kpdus.com/jad.html
//// Decompiler options: packimports(3)
//// Source File Name:   MonitorModel.java
//
//package com.ibm.jinwoo;
//
//import javax.swing.event.TreeModelListener;
//import javax.swing.tree.TreeModel;
//import javax.swing.tree.TreePath;
//import java.util.*;
//
//// Referenced classes of package com.ibm.jinwoo.thread:
////            ThreadDump, Monitor, MonitorDump, Arrays2
//
//public class MonitorModel
//    implements TreeModel
//{
//
//    public MonitorModel()
//    {
//        nodeCounter = new Hashtable();
//    }
//
//    public MonitorModel(ThreadDump td)
//    {
//        nodeCounter = new Hashtable();
//        tdump = td;
//        mdump = td.mdump;
//        rootNode = new Monitor();
//        rootNode.owner = -1;
//        rootNode.threadName = "Thread Name";
//        rootNode.objectName = "Object Name";
//        Hashtable threadHash = new Hashtable();
//        int index = 0;
//        for(int i = 0; i < mdump.length; i++)
//        {
//            Long newOwner = new Long(mdump[i].owner);
//            if(!threadHash.containsValue(newOwner))
//                threadHash.put(new Integer(index++), newOwner);
//            if(mdump[i].waiting != null && mdump[i].waiting.size() != 0)
//            {
//                for(int j = 0; j < mdump[i].waiting.size(); j++)
//                {
//                    newOwner = (Long)mdump[i].waiting.get(j);
//                    if(!threadHash.containsValue(newOwner))
//                        threadHash.put(new Integer(index++), newOwner);
//                }
//
//            }
//        }
//
//        hasParent = new boolean[threadHash.size()];
//        isPseudoRoot = new boolean[threadHash.size()];
//        counted = new boolean[threadHash.size()];
//        objectName = new int[threadHash.size()];
//        threadArray = new long[threadHash.size()];
//        visited = new boolean[threadHash.size()];
//        total = new int[threadHash.size()];
//        size = new int[threadHash.size()];
//        parent = new int[threadHash.size()];
//        recursiveParent = new int[threadHash.size()];
//        for(int i = 0; i < recursiveParent.length; i++)
//            recursiveParent[i] = -1;
//
//        child = new int[threadHash.size()][];
//        objectArray = new String[mdump.length];
//        owner = new int[threadHash.size()];
//        id = new int[threadHash.size()];
//        isHeapLock = new boolean[threadHash.size()];
//        waitingHeapLock = new boolean[threadHash.size()];
//        owningObject = new int[threadHash.size()][];
//        for(int i = 0; i < owner.length; i++)
//        {
//            owner[i] = -1;
//            objectName[i] = -1;
//            parent[i] = -1;
//        }
//
//        Enumeration enum2 = threadHash.elements();
//        int idx = 0;
//        int idx2 = 0;
//        while(enum2.hasMoreElements())
//            threadArray[idx++] = ((Long)enum2.nextElement()).longValue();
//        Arrays.sort(threadArray);
//        for(int i = 0; i < id.length; i++)
//            id[i] = tdump.getIndexFromSysThread(threadArray[i]);
//
//        threadHash.clear();
//        threadHash = null;
//        for(int i = 0; i < mdump.length; i++)
//        {
//            objectArray[i] = mdump[i].objectName;
//            if(mdump[i].waiting != null && mdump[i].waiting.size() != 0)
//            {
//                idx = Arrays.binarySearch(threadArray, mdump[i].owner);
//                if(idx != -1)
//                {
//                    if(owningObject[idx] == null)
//                    {
//                        owningObject[idx] = new int[1];
//                        owningObject[idx][0] = i;
//                    } else
//                    {
//                        int temp[] = new int[owningObject[idx].length];
//                        System.arraycopy(owningObject[idx], 0, temp, 0, temp.length);
//                        owningObject[idx] = new int[temp.length + 1];
//                        owningObject[idx][0] = i;
//                        System.arraycopy(temp, 0, owningObject[idx], owningObject[idx].length - temp.length, temp.length);
//                    }
//                    if(mdump[i].isHeapLock())
//                        isHeapLock[idx] = true;
//                    size[idx] += mdump[i].waiting.size();
//                    if(child[idx] == null)
//                    {
//                        child[idx] = new int[mdump[i].waiting.size()];
//                    } else
//                    {
//                        int temp[] = new int[child[idx].length];
//                        System.arraycopy(child[idx], 0, temp, 0, temp.length);
//                        child[idx] = new int[size[idx]];
//                        System.arraycopy(temp, 0, child[idx], child[idx].length - temp.length, temp.length);
//                    }
//                    for(int j = 0; j < mdump[i].waiting.size(); j++)
//                    {
//                        Long newOwner = (Long)mdump[i].waiting.get(j);
//                        idx2 = Arrays.binarySearch(threadArray, newOwner.longValue());
//                        if(idx2 != -1)
//                        {
//                            if(mdump[i].isHeapLock())
//                                waitingHeapLock[idx2] = true;
//                            hasParent[idx2] = true;
//                            objectName[idx2] = i;
//                            owner[idx2] = idx;
//                            child[idx][j] = idx2;
//                            recursiveParent[idx2] = idx;
//                        }
//                    }
//
//                }
//            }
//        }
//
//        Vector rv = new Vector();
//        for(int i = 0; i < hasParent.length; i++)
//            if(!hasParent[i])
//            {
//                calculateTotal(i);
//                rv.addElement(new Integer(i));
//            }
//
//        markPseudoRoot();
//        for(int i = 0; i < hasParent.length; i++)
//            if(!visited[i] && child[i] != null && isPseudoRoot[i])
//            {
//                calculateTotal(i);
//                rv.addElement(new Integer(i));
//            }
//
//        enum2 = rv.elements();
//        idx = 0;
//        rootChildren = new int[rv.size()];
//        while(enum2.hasMoreElements())
//            rootChildren[idx++] = ((Integer)enum2.nextElement()).intValue();
//        rv.clear();
//        int tempRoot[][] = new int[2][rootChildren.length];
//        for(int i = 0; i < rootChildren.length; i++)
//        {
//            tempRoot[0][i] = total[rootChildren[i]];
//            tempRoot[1][i] = rootChildren[i];
//        }
//
//        Arrays2.sort(tempRoot);
//        for(int i = 0; i < rootChildren.length; i++)
//            rootChildren[rootChildren.length - 1 - i] = tempRoot[1][i];
//
//        tempRoot = null;
//        for(int i = 0; i < child.length; i++)
//            if(child[i] != null)
//            {
//                int tempChild[][] = new int[2][child[i].length];
//                for(int j = 0; j < child[i].length; j++)
//                {
//                    tempChild[0][j] = total[child[i][j]];
//                    tempChild[1][j] = child[i][j];
//                }
//
//                Arrays2.sort(tempChild);
//                for(int j = 0; j < child[i].length; j++)
//                    child[i][child[i].length - 1 - j] = tempChild[1][j];
//
//            }
//
//    }
//
//    public void addTreeModelListener(TreeModelListener treemodellistener)
//    {
//    }
//
//    public void calculateTotal(int r)
//    {
//        boolean anyChild = false;
//        Stack stack = new Stack();
//        total[r] = size[r];
//        visited[r] = true;
//        stack.push(new Integer(r));
//        while(!stack.empty())
//        {
//            int aNode = ((Integer)stack.peek()).intValue();
//            anyChild = false;
//            if(child[aNode] != null)
//            {
//                for(int i = 0; i < child[aNode].length; i++)
//                {
//                    int index = child[aNode][i];
//                    if(index >= 0)
//                    {
//                        int cNode = index;
//                        if(!visited[cNode])
//                        {
//                            parent[cNode] = aNode;
//                            visited[cNode] = true;
//                            total[cNode] = size[cNode];
//                            stack.push(new Integer(cNode));
//                            anyChild = true;
//                        }
//                    }
//                }
//
//            }
//            if(!anyChild)
//            {
//                aNode = ((Integer)stack.pop()).intValue();
//                if(child[aNode] != null)
//                {
//                    for(int i = 0; i < child[aNode].length; i++)
//                    {
//                        int index = child[aNode][i];
//                        if(index >= 0)
//                        {
//                            int cNode = index;
//                            if(parent[cNode] == aNode && !counted[cNode])
//                            {
//                                total[aNode] += total[cNode];
//                                counted[cNode] = true;
//                            }
//                        }
//                    }
//
//                }
//            }
//        }
//    }
//
//    public Object getChild(Object parent, int index)
//    {
//        Monitor m;
//        if(((Monitor)parent).owner == -1)
//        {
//            m = new Monitor();
//            m.owner = rootChildren[index];
//            m.isHeapLock = isHeapLock[m.owner];
//            m.waitingHeapLock = waitingHeapLock[m.owner];
//            if(id[m.owner] != -1 && tdump.isDeadlock[id[m.owner]])
//                m.isDeadlock = true;
//            return m;
//        }
//        if(child[((Monitor)parent).owner] == null)
//            return null;
//        m = new Monitor();
//        m.owner = child[((Monitor)parent).owner][index];
//        m.isHeapLock = isHeapLock[m.owner];
//        m.waitingHeapLock = waitingHeapLock[m.owner];
//        m.objectName = objectArray[objectName[m.owner]];
//        if(id[m.owner] != -1 && tdump.isDeadlock[id[m.owner]])
//            m.isDeadlock = true;
//        return m;
//    }
//
//    public int getChildCount(Object parent)
//    {
//        if(((Monitor)parent).owner == -1)
//            return rootChildren.length;
//        if(child[((Monitor)parent).owner] != null)
//            return child[((Monitor)parent).owner].length;
//        else
//            return 0;
//    }
//
//    public int getIndexOfChild(Object parent, Object c)
//    {
//        if(((Monitor)parent).owner == -1)
//        {
//            for(int i = 0; i < rootChildren.length; i++)
//                if(((Monitor)c).owner == rootChildren[i])
//                    return i;
//
//            return -1;
//        }
//        if(child[((Monitor)parent).owner] != null)
//        {
//            for(int i = 0; i < child[((Monitor)parent).owner].length; i++)
//                if(((Monitor)c).owner == child[((Monitor)parent).owner][i])
//                    return i;
//
//        }
//        return -1;
//    }
//
//    public String getOwningObjects(int index)
//    {
//        if(index != -1 && owningObject[index] != null && owningObject[index].length != 0)
//        {
//            String objects = new String();
//            boolean isFirst = true;
//            for(int i = 0; i < owningObject[index].length; i++)
//                if(isFirst)
//                {
//                    objects = objectArray[owningObject[index][i]];
//                    isFirst = false;
//                } else
//                {
//                    objects = (new StringBuilder(String.valueOf(objects))).append(" , ").append(objectArray[owningObject[index][i]]).toString();
//                }
//
//            return objects;
//        } else
//        {
//            return null;
//        }
//    }
//
//    public Object getParent(Object parent)
//    {
//        if(((Monitor)parent).owner == -1)
//            return rootNode;
//        Monitor m = new Monitor();
//        m.owner = recursiveParent[((Monitor)parent).owner];
//        if(m.owner == -1)
//            return rootNode;
//        m.isHeapLock = isHeapLock[m.owner];
//        m.waitingHeapLock = waitingHeapLock[m.owner];
//        int obj = objectName[m.owner];
//        if(obj != -1)
//            m.objectName = objectArray[obj];
//        int d = id[m.owner];
//        if(d != -1 && id[m.owner] != -1 && tdump.isDeadlock[d])
//            m.isDeadlock = true;
//        return m;
//    }
//
//    public Object getRoot()
//    {
//        return rootNode;
//    }
//
//    public String getSummary(Monitor node)
//    {
//        if(node == null)
//            return "";
//        if(node.owner == -1)
//            return "";
//        if(id[node.owner] == -1)
//            return "";
//        String stackTrace = (new StringBuilder("Thread Name : ")).append(tdump.name[id[node.owner]]).append("<BR>State : ").append(tdump.getState(id[node.owner])).append("<BR>").toString();
//        if(node.isHeapLock)
//            stackTrace = (new StringBuilder(String.valueOf(stackTrace))).append("Owns Heap Lock<BR>").toString();
//        if(node.waitingHeapLock)
//            stackTrace = (new StringBuilder(String.valueOf(stackTrace))).append("Waiting for Heap Lock<BR>").toString();
//        if(node.objectName != null)
//            stackTrace = (new StringBuilder(String.valueOf(stackTrace))).append("Waiting for Monitor Lock on ").append(node.objectName).append("<BR>").toString();
//        String oo = getOwningObjects(node.owner);
//        if(oo != null)
//            stackTrace = (new StringBuilder(String.valueOf(stackTrace))).append("Owns Monitor Lock on ").append(oo).append("<BR>").toString();
//        if(tdump.javaStack[id[node.owner]] == null)
//            stackTrace = (new StringBuilder(String.valueOf(stackTrace))).append("No Java Stack available<BR>").toString();
//        else
//            stackTrace = (new StringBuilder(String.valueOf(stackTrace))).append("Java Stack<BR>").append(tdump.javaStack[id[node.owner]]).append("<BR>").toString();
//        if(tdump.nativeStack[id[node.owner]] != null)
//            stackTrace = (new StringBuilder(String.valueOf(stackTrace))).append("Native Stack<BR>").append(tdump.nativeStack[id[node.owner]]).toString();
//        return stackTrace;
//    }
//
//    public int getThreadDumpIndex(Monitor node)
//    {
//        if(node == null)
//            return -1;
//        if(node.owner == -1)
//            return -1;
//        else
//            return id[node.owner];
//    }
//
//    public String getThreadName(int index)
//    {
//        long threadID = threadArray[index];
//        for(int i = 0; i < tdump.sys_thread.length; i++)
//            if(threadID == tdump.sys_thread[i])
//                return tdump.name[i];
//
//        return "Unknown";
//    }
//
//    public int getThreadState(int index)
//    {
//        long threadID = threadArray[index];
//        for(int i = 0; i < tdump.sys_thread.length; i++)
//            if(threadID == tdump.sys_thread[i])
//                return tdump.state[i];
//
//        return -1;
//    }
//
//    public boolean isLeaf(Object node)
//    {
//        if(((Monitor)node).owner == -1)
//            return rootChildren.length == 0;
//        return child[((Monitor)node).owner] == null;
//    }
//
//    public boolean isRecursive(Object node)
//    {
//        if(((Monitor)node).owner == -1)
//            return false;
//        HashSet nodeSet = new HashSet();
//        Integer newNode = null;
//        int count = 0;
//        int current = ((Monitor)node).owner;
//        int up;
//        while((up = recursiveParent[current]) != -1)
//        {
//            current = up;
//            newNode = new Integer(up);
//            if(nodeCounter.containsKey(newNode))
//                count = ((Integer)nodeCounter.get(newNode)).intValue();
//            else
//                count = 0;
//            if(up == ((Monitor)node).owner)
//            {
//                count++;
//                nodeCounter.put(newNode, new Integer(count));
//                return count > 3;
//            }
//            if(!nodeSet.isEmpty() && nodeSet.contains(newNode))
//                return false;
//            nodeSet.add(newNode);
//        }
//        return false;
//    }
//
//    public boolean isRootChild(Object node)
//    {
//        return false;
//    }
//
//    public void markPseudoRoot()
//    {
//        Hashtable hash = new Hashtable();
//        boolean checked[] = new boolean[hasParent.length];
//        for(int i = 0; i < hasParent.length; i++)
//            if(!visited[i] && child[i] != null && !checked[i])
//            {
//                hash.clear();
//                int c = i;
//                checked[c] = true;
//                Integer newInt = new Integer(c);
//                hash.put(newInt, "");
//                do
//                {
//                    int p = owner[c];
//                    if(p == -1)
//                        break;
//                    if(checked[p])
//                    {
//                        newInt = new Integer(p);
//                        if(hash.containsKey(newInt))
//                            isPseudoRoot[c] = true;
//                        break;
//                    }
//                    checked[p] = true;
//                    newInt = new Integer(p);
//                    if(hash.containsKey(newInt))
//                    {
//                        isPseudoRoot[c] = true;
//                        break;
//                    }
//                    hash.put(newInt, "");
//                    c = p;
//                } while(true);
//            }
//
//    }
//
//    public void removeTreeModelListener(TreeModelListener treemodellistener)
//    {
//    }
//
//    public void valueForPathChanged(TreePath treepath, Object obj)
//    {
//    }
//
//    ThreadDump tdump;
//    MonitorDump mdump[];
//    Monitor rootNode;
//    int rootChildren[];
//    boolean hasParent[];
//    boolean visited[];
//    boolean isPseudoRoot[];
//    long threadArray[];
//    int owner[];
//    int total[];
//    int size[];
//    int parent[];
//    int recursiveParent[];
//    int child[][];
//    boolean counted[];
//    int objectName[];
//    int id[];
//    String objectArray[];
//    boolean isHeapLock[];
//    boolean waitingHeapLock[];
//    int owningObject[][];
//    Hashtable nodeCounter;
//}
