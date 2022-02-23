package com.ibm.jinwoo;

import java.util.ArrayList;
import java.util.List;

public class CustomerTree {
    public List<CustomerTreeNode> customerTreeNode;

    public CustomerTree() {
    }

    public CustomerTree(CustomerTreeNode customerTreeNode) {
        this.customerTreeNode = new ArrayList<>();
        this.customerTreeNode.add(customerTreeNode);
    }
}
