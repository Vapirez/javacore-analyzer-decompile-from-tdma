package com.ibm.jinwoo;

import java.util.ArrayList;
import java.util.List;

public class ThreadAggregationDistribute {
    List<CustomerAggregationInfo> aggregationInfoList;

    static final String AGGREGATION[] = {
            "ThreadManager.JobsProcessorThread.InternalThread", "WLMMonitorSleeper", "Deferrable Alarm", "HAManager.thread.pool", "WebContainer", "Servlet.Engine.Transports :", "ServerSocket", "ORB.thread.pool", "SoapConnectorThreadPool", "Alarm",
            "Thread"
    };

    private class CustomerAggregationInfo {
        String MethodName;
        long count;

        public CustomerAggregationInfo(String methodName, long count) {
            MethodName = methodName;
            this.count = count;
        }
    }

    ThreadAggregationDistribute(ThreadDump td) {
        aggregationInfoList = new ArrayList<>();
        //计算排序后方法
        int[] currentMethodArray = new int[td.tid.length];
        String tempString[] = new String[td.name.length];
        for (int j = 0; j < currentMethodArray.length; j++) {
            tempString[j] = td.getCurrentMethod(j);
            currentMethodArray[j] = j;
        }
        Arrays2.sort(tempString, currentMethodArray);

        long emptyMethod = 0L;
        List methodList = new ArrayList();
        String[] name = td.name;
        for (int i = 0; i < name.length; i++) {
            String methodName = td.getName(i);
            if (methodName != null) {
                if (methodName.length() != 0) {
                    int result = td.getCommonThreadName(methodName);
                    if (result >= 0 && !methodList.contains(AGGREGATION[result])) {
                        methodList.add(AGGREGATION[result]);
                    }
                } else if (methodName.length() == 0) {
                    emptyMethod++;
                }
            }
        }

        String methodArray[] = (String[]) methodList.toArray(new String[methodList.size()]);
        long sortedMethod[][] = new long[2][methodArray.length];
        methodList.clear();
        for (int i = 0; i < methodArray.length; i++) {
            sortedMethod[0][i] = 0L;
            sortedMethod[1][i] = i;
        }

        int j = 0;
        int value[] = new int[AGGREGATION.length];
        for (int i = 0; i < value.length; i++) {
            value[i] = 0;
        }

        for (int i = 0; i < name.length; i++) {
            int result = td.getCommonThreadName(td.getName(i));
            if (result >= 0) {
                for (int k = 0; k < methodArray.length; k++) {
                    if (methodArray[k].compareTo(AGGREGATION[result]) == 0) {
                        sortedMethod[0][k]++;
                    }
                }
            }
        }

        Arrays2.sort(sortedMethod);
        for (int i = methodArray.length - 1; i >= 0; i--) {
            aggregationInfoList.add(new CustomerAggregationInfo(methodArray[(int) sortedMethod[1][i]], sortedMethod[0][i]));
        }

        if (emptyMethod != 0L) {
            aggregationInfoList.add(new CustomerAggregationInfo("Unknown method : ", emptyMethod));
        }
    }
}
