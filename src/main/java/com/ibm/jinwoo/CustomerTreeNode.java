package com.ibm.jinwoo;

import java.util.ArrayList;
import java.util.List;

public class CustomerTreeNode {
    public String label;
    public List<CustomerTreeNode> children;

    public CustomerTreeNode(String label) {
        this.label = label;
    }

    public boolean add(CustomerTreeNode customerTreeNode){
        if(children==null){
            children= new ArrayList<>();
        }
        return children.add(customerTreeNode);
    }
}
