package datastucture.tree;

/**
 * AVL树的实现
 *
 * @author zhaoyan
 * @since 2016.12.12 11:04
 */
public class AvlTree<AnyType> {

    /**
     * AVL树节点
     *
     * @param <AnyType> 泛型
     */
    private static class AvlNode<AnyType> {
        public AnyType data;
        public AvlNode<AnyType> left;
        public AvlNode<AnyType> right;
        public int height;

        public AvlNode(AnyType data) {
            this.data = data;
            left = null;
            right = null;
            height = 0;
        }

        public AvlNode(AnyType data, AvlNode<AnyType> left, AvlNode<AnyType> right) {
            this.data = data;
            this.left = left;
            this.right = right;
            this.height = 0;
        }

        public int getHeight() {
            return this.height;
        }
    }
}
