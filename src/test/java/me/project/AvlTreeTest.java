package me.project;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class AvlTreeTest {
    private static final int[] arr = {10, 20, 22, 15};

    @Test
    public void preOrder() throws Exception {
        //given
        AvlTree<Integer> avlTree = getAvlTree();

        //when
        avlTree.preOrder();

        //then
    }

    private AvlTree<Integer> getAvlTree() {
        AvlTree<Integer> avlTree = new AvlTree<>();
        for (int num: arr) {
            avlTree.insert(num);
        }

        return avlTree;
    }
}
