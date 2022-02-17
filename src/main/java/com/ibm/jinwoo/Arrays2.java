// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   Arrays2.java

package com.ibm.jinwoo;


public class Arrays2
{

    public Arrays2()
    {
    }

    private static int med3(int x[], int a, int b, int c)
    {
        return x[a] >= x[b] ? x[b] <= x[c] ? x[a] <= x[c] ? a : c : b : x[b] >= x[c] ? x[a] >= x[c] ? a : c : b;
    }

    private static int med3(long x[], int a, int b, int c)
    {
        return x[a] >= x[b] ? x[b] <= x[c] ? x[a] <= x[c] ? a : c : b : x[b] >= x[c] ? x[a] >= x[c] ? a : c : b;
    }

    private static int med3(String x[], int a, int b, int c)
    {
        return x[a].compareTo(x[b]) >= 0 ? x[b].compareTo(x[c]) <= 0 ? x[a].compareTo(x[c]) <= 0 ? a : c : b : x[b].compareTo(x[c]) >= 0 ? x[a].compareTo(x[c]) >= 0 ? a : c : b;
    }

    public static void sort(int a[][])
    {
        sort1(a, 0, a[0].length);
    }

    public static void sort(long a[][])
    {
        sort1(a, 0, a[0].length);
    }

    public static void sort(String a[], int s[])
    {
        sort1(a, 0, a.length, s);
    }

    private static void sort1(int x[][], int off, int len)
    {
        if(len < 7)
        {
            for(int i = off; i < len + off; i++)
            {
                for(int j = i; j > off && x[0][j - 1] > x[0][j]; j--)
                {
                    swap(x[0], j, j - 1);
                    swap(x[1], j, j - 1);
                }

            }

            return;
        }
        int m = off + (len >> 1);
        if(len > 7)
        {
            int l = off;
            int n = (off + len) - 1;
            if(len > 40)
            {
                int s = len / 8;
                l = med3(x[0], l, l + s, l + 2 * s);
                m = med3(x[0], m - s, m, m + s);
                n = med3(x[0], n - 2 * s, n - s, n);
            }
            m = med3(x[0], l, m, n);
        }
        int v = x[0][m];
        int a = off;
        int b = a;
        int c = (off + len) - 1;
        int d = c;
        do
        {
            while(b <= c && x[0][b] <= v) 
            {
                if(x[0][b] == v)
                {
                    swap(x[0], a, b);
                    swap(x[1], a, b);
                    a++;
                }
                b++;
            }
            for(; c >= b && x[0][c] >= v; c--)
                if(x[0][c] == v)
                {
                    swap(x[0], c, d);
                    swap(x[1], c, d);
                    d--;
                }

            if(b > c)
                break;
            swap(x[0], b, c);
            swap(x[1], b, c);
            b++;
            c--;
        } while(true);
        int n = off + len;
        int s = Math.min(a - off, b - a);
        vecswap(x[0], off, b - s, s);
        vecswap(x[1], off, b - s, s);
        s = Math.min(d - c, n - d - 1);
        vecswap(x[0], b, n - s, s);
        vecswap(x[1], b, n - s, s);
        if((s = b - a) > 1)
            sort1(x, off, s);
        if((s = d - c) > 1)
            sort1(x, n - s, s);
    }

    private static void sort1(long x[][], int off, int len)
    {
        if(len < 7)
        {
            for(int i = off; i < len + off; i++)
            {
                for(int j = i; j > off && x[0][j - 1] > x[0][j]; j--)
                {
                    swap(x[0], j, j - 1);
                    swap(x[1], j, j - 1);
                }

            }

            return;
        }
        int m = off + (len >> 1);
        if(len > 7)
        {
            int l = off;
            int n = (off + len) - 1;
            if(len > 40)
            {
                int s = len / 8;
                l = med3(x[0], l, l + s, l + 2 * s);
                m = med3(x[0], m - s, m, m + s);
                n = med3(x[0], n - 2 * s, n - s, n);
            }
            m = med3(x[0], l, m, n);
        }
        long v = x[0][m];
        int a = off;
        int b = a;
        int c = (off + len) - 1;
        int d = c;
        do
        {
            while(b <= c && x[0][b] <= v) 
            {
                if(x[0][b] == v)
                {
                    swap(x[0], a, b);
                    swap(x[1], a, b);
                    a++;
                }
                b++;
            }
            for(; c >= b && x[0][c] >= v; c--)
                if(x[0][c] == v)
                {
                    swap(x[0], c, d);
                    swap(x[1], c, d);
                    d--;
                }

            if(b > c)
                break;
            swap(x[0], b, c);
            swap(x[1], b, c);
            b++;
            c--;
        } while(true);
        int n = off + len;
        int s = Math.min(a - off, b - a);
        vecswap(x[0], off, b - s, s);
        vecswap(x[1], off, b - s, s);
        s = Math.min(d - c, n - d - 1);
        vecswap(x[0], b, n - s, s);
        vecswap(x[1], b, n - s, s);
        if((s = b - a) > 1)
            sort1(x, off, s);
        if((s = d - c) > 1)
            sort1(x, n - s, s);
    }

    private static void sort1(String x[], int off, int len, int x1[])
    {
        if(len < 7)
        {
            for(int i = off; i < len + off; i++)
            {
                for(int j = i; j > off && x[j - 1].compareTo(x[j]) > 0; j--)
                {
                    swap(x, j, j - 1);
                    swap(x1, j, j - 1);
                }

            }

            return;
        }
        int m = off + (len >> 1);
        if(len > 7)
        {
            int l = off;
            int n = (off + len) - 1;
            if(len > 40)
            {
                int s = len / 8;
                l = med3(x, l, l + s, l + 2 * s);
                m = med3(x, m - s, m, m + s);
                n = med3(x, n - 2 * s, n - s, n);
            }
            m = med3(x, l, m, n);
        }
        String v = x[m];
        int a = off;
        int b = a;
        int c = (off + len) - 1;
        int d = c;
        do
        {
            while(b <= c && x[b].compareTo(v) <= 0) 
            {
                if(x[b].compareTo(v) == 0)
                {
                    swap(x, a, b);
                    swap(x1, a, b);
                    a++;
                }
                b++;
            }
            for(; c >= b && x[c].compareTo(v) >= 0; c--)
                if(x[c].compareTo(v) == 0)
                {
                    swap(x, c, d);
                    swap(x1, c, d);
                    d--;
                }

            if(b > c)
                break;
            swap(x, b, c);
            swap(x1, b, c);
            b++;
            c--;
        } while(true);
        int n = off + len;
        int s = Math.min(a - off, b - a);
        vecswap(x, off, b - s, s);
        vecswap(x1, off, b - s, s);
        s = Math.min(d - c, n - d - 1);
        vecswap(x, b, n - s, s);
        vecswap(x1, b, n - s, s);
        if((s = b - a) > 1)
            sort1(x, off, s, x1);
        if((s = d - c) > 1)
            sort1(x, n - s, s, x1);
    }

    private static void swap(int x[], int a, int b)
    {
        int t = x[a];
        x[a] = x[b];
        x[b] = t;
    }

    private static void swap(long x[], int a, int b)
    {
        long t = x[a];
        x[a] = x[b];
        x[b] = t;
    }

    private static void swap(String x[], int a, int b)
    {
        String t = x[a];
        x[a] = x[b];
        x[b] = t;
    }

    private static void vecswap(int x[], int a, int b, int n)
    {
        for(int i = 0; i < n;)
        {
            swap(x, a, b);
            i++;
            a++;
            b++;
        }

    }

    private static void vecswap(long x[], int a, int b, int n)
    {
        for(int i = 0; i < n;)
        {
            swap(x, a, b);
            i++;
            a++;
            b++;
        }

    }

    private static void vecswap(String x[], int a, int b, int n)
    {
        for(int i = 0; i < n;)
        {
            swap(x, a, b);
            i++;
            a++;
            b++;
        }

    }
}
