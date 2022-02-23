package com.ibm.jinwoo;

import java.io.File;

public class JavaCoreTest {
    public static void main(String[] args) {
        File file = new File("D:\\test\\threaddump-1644904093681.tdump");
        File[] files = {file};
        FileTask fileTask = new FileTask(files);
        fileTask.go();
    }

}
