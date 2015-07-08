package com.company;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 * Created by intern on 7/8/15.
 */
public class BinaryTree<T> {
    private BinaryTree left;
    private BinaryTree right;
    T value;

    public BinaryTree convertToList(BinaryTree node) {
        if(left == null && right == null) {
            return this;
        } else if(left != null) {
            return convertToList(left);
        } else if(right != null) {
            return convertToList(right);
        } else {

        } 

        return null;
    }
}
