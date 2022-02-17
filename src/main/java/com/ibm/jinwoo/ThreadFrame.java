package com.ibm.jinwoo;

public class ThreadFrame {
    public static String getCopyFriendlyString(String source)
    {
        return source.replace("<BR>", "<BR>&#x0A;");
    }
}
