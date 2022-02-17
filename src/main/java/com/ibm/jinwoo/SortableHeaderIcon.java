// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   SortableHeaderIcon.java

package com.ibm.jinwoo;

import javax.swing.*;
import java.awt.*;

public class SortableHeaderIcon
    implements Icon
{

    public SortableHeaderIcon()
    {
    }

    public SortableHeaderIcon(boolean direction, int size)
    {
        this.direction = direction;
        this.size = size;
    }

    public int getIconHeight()
    {
        return size;
    }

    public int getIconWidth()
    {
        return size;
    }

    public void paintIcon(Component c, Graphics g, int x, int y)
    {
        Color color;
        if(c == null)
            color = Color.gray;
        else
            color = c.getBackground();
        int dx = (size * 2) / 3;
        int dy = direction ? dx : -dx;
        y = y + (5 * size) / 6 + (direction ? -dy : 0);
        g.translate(x, y);
        if(direction)
        {
            g.setColor(Color.blue);
        } else
        {
            dx++;
            dy--;
            g.setColor(Color.red);
        }
        int xP[] = {
            dx / 2, 0, dx
        };
        int yP[] = {
            dy, 0, 0
        };
        g.fillPolygon(xP, yP, 3);
        g.setColor(color);
        g.translate(-x, -y);
    }

    boolean direction;
    int size;
}
