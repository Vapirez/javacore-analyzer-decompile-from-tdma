package com.ibm.jinwoo;

import java.util.List;

public class ThreadDetail {
    public String name;
    public String pattern;
    public Boolean isDeadlock;
    public Long nid;
    public Integer state;
    public String stateValue;
    public Integer priority;
    public String currentMethod;
    public String javaStack;
    public List<String> javaStackList;
    public Integer javaStackDepth;
    public Integer macro;
    public String nativeStack;
    public Long sys_thread;
    public Long tid;
}
