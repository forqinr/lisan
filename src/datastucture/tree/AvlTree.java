package datastucture.tree;

import java.util.Comparator;

/**
 * AVL树的实现
 *
 * @author zhaoyan
 * @since 2016.12.12 11:04
 */
public class AvlTree<AnyType> {

    private AvlNode<AnyType> root;
    private Comparator<AnyType> comparator;

    public AvlTree() {
        this.root = null;
        this.comparator = new Comparator<AnyType>() {
            @Override
            public int compare(AnyType o1, AnyType o2) {
                Integer x = (Integer) o1;
                Integer y = (Integer) o2;
                return x - y;
            }

            @Override
            public boolean equals(Object obj) {
                return false;
            }
        };
    }

    /**
     * 当向左儿子的左子树中插入数据造成AVL不平衡时，使用此旋转
     * 这是单旋转的一种
     * 最后在更新完节点高度后，返回新的子树根
     *
     * @param k2 旋转前的子树的根
     *
     * @return 旋转后的字数的根
     */
    private AvlNode<AnyType> rotateWithLeftChild(AvlNode<AnyType> k2) {
        AvlNode<AnyType> k1 = k2.left;

        k2.left = k1.right;
        k1.right = k2;

        k2.height = Math.max(height(k2.left), height(k2.right)) + 1;
        k1.height = Math.max(height(k1.left), height(k1.right)) + 1;

        return k1;
    }

    /**
     * 当向右儿子的右子树中插入数据造成AVL不平衡时，使用此旋转
     * 这是单旋转的一种
     * 最后在更新完节点高度后，返回新的子树根
     *
     * @param k2 旋转前的子树的根
     *
     * @return 旋转后的字数的根
     */
    private AvlNode<AnyType> rotateWithRightChild(AvlNode<AnyType> k2) {
        AvlNode<AnyType> k1 = k2.right;

        k2.right = k1.left;
        k1.left = k2;

        k2.height = Math.max(height(k2.left), height(k2.right)) + 1;
        k1.height = Math.max(height(k1.left), height(k1.right)) + 1;

        return k1;
    }

    /**
     * 双旋转
     * 双旋转，就是先是该节点的儿子节点和孙子节点的一次单旋转，然后是该节点和新儿子的一次单旋转组成
     * 该方法是子树的左子树进行双旋转
     *
     * @param k3 需要双旋转的子树
     *
     * @return 新子树的根节点
     */
    private AvlNode<AnyType> doubleWithLeftChild(AvlNode<AnyType> k3) {
        k3.left = rotateWithRightChild(k3.left);
        return rotateWithLeftChild(k3);
    }

    /**
     * 双旋转
     * 双旋转，就是先是该节点的儿子节点和孙子节点的一次单旋转，然后是该节点和新儿子的一次单旋转组成
     * 该方法是子树的右子树进行双旋转
     *
     * @param k3 需要双旋转的子树
     *
     * @return 新子树的根节点
     */
    private AvlNode<AnyType> doubleWithRightChild(AvlNode<AnyType> k3) {
        k3.right = rotateWithLeftChild(k3.right);
        return rotateWithRightChild(k3);
    }

    private int height(AvlNode<AnyType> t) {
        return t == null ? -1 : t.height;
    }

    public AvlNode<AnyType> insert(AnyType x) {
        root = insert(x, root);
        return root;
    }

    public AvlNode<AnyType> insert(AnyType x, AvlNode<AnyType> t) {
        if (t == null) {
            return new AvlNode<AnyType>(x);
        }

        int compareResult = compare(x, t.element);

        if (compareResult < 0) {
            t.left = insert(x, t.left);
            if (height(t.left) - height(t.right) == 2) {
                if (compare(x, t.left.element) < 0) {
                    t = rotateWithLeftChild(t);
                } else {
                    t = doubleWithLeftChild(t);
                }
            }
        } else if (compareResult > 0) {
            t.right = insert(x, t.right);
            if (height(t.right) - height(t.left) == 2) {
                if (compare(x, t.right.element) > 0) {
                    t = rotateWithRightChild(t);
                } else {
                    t = doubleWithRightChild(t);
                }
            }
        } else {
            ;//Duplicate;do nothing;
        }


        t.height = Math.max(height(t.left), height(t.right)) + 1;
        return t;
    }

    private int compare(AnyType x, AnyType anyType) {
        return comparator.compare(x, anyType);
    }

    /**
     * AVL树节点
     *
     * @param <AnyType> 泛型
     */
    private static class AvlNode<AnyType> {
        public AnyType element;
        public AvlNode<AnyType> left;
        public AvlNode<AnyType> right;
        public int height;

        public AvlNode(AnyType data) {
            this.element = data;
            left = null;
            right = null;
            height = 0;
        }

        public AvlNode(AnyType data, AvlNode<AnyType> lt, AvlNode<AnyType> rt) {
            this.element = data;
            this.left = lt;
            this.right = rt;
            this.height = 0;
        }


    }
}
