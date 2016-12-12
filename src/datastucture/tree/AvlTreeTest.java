package datastucture.tree;

import org.junit.Test;

public class AvlTreeTest {

    @Test
    public void testInsert() throws Exception {
        AvlTree<Integer> avlTree = new AvlTree<Integer>();
        for (int i = 3; i > 0; i--) {
            avlTree.insert(i);
        }

        for (int i = 4; i < 8; i++) {
            avlTree.insert(i);
        }
    }
}