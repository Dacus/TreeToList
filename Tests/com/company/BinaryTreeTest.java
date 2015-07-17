package com.company;

import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.hamcrest.Matchers.contains;
import static org.junit.Assert.assertThat;

public class BinaryTreeTest {
    private BinaryTree<Integer> binaryTree;

    @Before
    public void setup() {
        binaryTree = createBinaryTree(20, 25, 30, 23, 21, 24, 22, 10, 15, 5, 13, 17, 16, 18, 11, 14, 3, 7, 6, 8);
    }

    @Test
    public void testToList() throws Exception {
        Integer[] testList = new Integer[]{20, 25, 30, 23, 21, 24, 22, 10, 15, 5, 13, 17, 16, 18, 11, 14, 3, 7, 6, 8};
        binaryTree = createBinaryTree(testList);
        binaryTree.toList();
        Arrays.sort(testList);
        assertThat(binaryTreeToList(binaryTree), contains(testList));
    }

    private static <T extends Comparable<T>> BinaryTree<T> createBinaryTree(T... elements) {
        BinaryTree<T> binaryTree = new BinaryTree<>();
        for (T element : elements) {
            binaryTree.add(element);
        }
        return binaryTree;
    }

    private static <T extends Comparable<T>> List<T> binaryTreeToList(BinaryTree<T> tree) {
        List<T> result = new ArrayList<>();
        for (T element : tree) {
            result.add(element);
        }
        return result;
    }
}