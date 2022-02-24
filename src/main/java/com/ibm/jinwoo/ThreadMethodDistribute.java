package com.ibm.jinwoo;

import sun.management.MethodInfo;

import java.util.ArrayList;
import java.util.List;

public class ThreadMethodDistribute {
    List<CustomerMethodInfo> methodInfoList;

    private class CustomerMethodInfo {
        String MethodName;
        long count;

        public CustomerMethodInfo(String methodName, long count) {
            MethodName = methodName;
            this.count = count;
        }
    }

    ThreadMethodDistribute(ThreadDump td) {
        methodInfoList = new ArrayList<>();
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
            String methodName = td.getCurrentMethod(currentMethodArray[i]);
            if (methodName.length() != 0 && !methodList.contains(methodName)) {
                methodList.add(methodName);
            } else if (methodName.length() == 0) {
                emptyMethod++;
            }
        }

        String methodArray[] = (String[]) methodList.toArray(new String[methodList.size()]);
        long sortedMethod[][] = new long[2][methodArray.length];
        methodList.clear();
        for (int i = 0; i < methodArray.length; i++) {
            sortedMethod[0][i] = 1L;
            sortedMethod[1][i] = i;
        }

        int j = 0;
        for (int i = 0; i < name.length; i++) {
            if (i < name.length - 1 && td.getCurrentMethod(currentMethodArray[i]).length() != 0 && td.getCurrentMethod(currentMethodArray[i + 1]).length() != 0) {
                if (td.getCurrentMethod(currentMethodArray[i]).compareTo(td.getCurrentMethod(currentMethodArray[i + 1])) == 0) {
                    sortedMethod[0][j]++;
                } else {
                    j++;
                }
            }
        }

        Arrays2.sort(sortedMethod);
        for (int i = methodArray.length - 1; i >= 0; i--) {
            methodInfoList.add(new CustomerMethodInfo(methodArray[(int)sortedMethod[1][i]], sortedMethod[0][i]));
        }

        if (emptyMethod != 0L) {
            methodInfoList.add(new CustomerMethodInfo("Unknown method : ", emptyMethod));
        }

    }

}
