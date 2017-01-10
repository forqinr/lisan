package datastucture.tree;

import org.junit.Test;

/**
 * Created by zhaoyan on 2017-01-10.
 */
public class LazyTreeTest {

    @Test
    public void testPrintTree() throws Exception {
        LazyTree<Integer> tree = new LazyTree<Integer>();
        tree.insert(3);
        tree.insert(1);
        tree.insert(4);
        tree.insert(6);
        tree.insert(9);
        tree.insert(2);
        tree.insert(5);
        tree.insert(7);
        tree.printTree();
        tree.remove(3);
        //tree.remove(1);
        tree.remove(2);
        tree.remove(4);
        tree.remove(5);
        tree.remove(6);
        //tree.remove(7);
        System.out.println("the Min is :" + tree.findMin());

    }
}