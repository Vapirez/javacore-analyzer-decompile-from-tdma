# tdma-decompiler
如果你想要做一个javacore解析工具，那不妨看看这个
---
这个代码来源是IBM的javacoe分析工具TDMA

首先获取IBM Thread and Monitor Dump Analyzer for Java (TMDA)的jca4611.jar

利用了IDEA自带的反编译和JAD反编译

其中，IDEA自带的反编译能够反编译出来带循环的代码，JAD的反编译能够反编译出来更易懂的变量名称

手动结合这两种反编译，并修改很多变量引用，这应该比你直接看反编译后的源码更容易理解

只抽取了部分源码，涉及解析javacore文件的，因为文件源码很多，即便我整理了两天，还是很乱，但希望能帮助到你们

我会在这个工程里面上传原本的jca4611.jar，你如果需要其他功能，可以自己打开反编译工具，找到想要的代码



参考网址
IBM javacore分析工具
https://www.ibm.com/support/pages/ibm-thread-and-monitor-dump-analyzer-java-tmda

JAD工具
https://blog.csdn.net/qq_19714505/article/details/80309968