package datastucture.tree;

/**
 * 每个节点保留一个通向想一个最小节点和上一个最大节点的链
 *
 * @author zhaoyan
 * @since 2016.12.30 15:31
 */
public class MyTreeSet2<AnyType extends Comparable<? super AnyType>> {

    private int modCount = 0;
    private Node<AnyType> root;

    public MyTreeSet2() {
        root = null;
        modCount = 0;
    }

    public void makeEmpty() {
        modCount++;
        root = null;
    }

    public boolean isEmpty() {
        return null == root;
    }

    public boolean contains(AnyType x) {
        return contains(root, x);
    }

    private boolean contains(Node<AnyType> t, AnyType x) {
        if (t == null)
            return false;

        if (x == null)
            return false;

        int compareResult = x.compareTo(t.element);

        if (compareResult < 0)
            return contains(t.left, x);
        else if (compareResult > 0)
            return contains(t.right, x);
        else
            return true;
    }

    ///**
    // * 插入操作
    // *
    // * @param x 要插入的值
    // */
    //public void insert(AnyType x) {
    //    root = insert(root, x, null, null);
    //}

    ///**
    // * 插入操作：
    // * 1.肯定是作为叶子节点插入，如果是作为左儿子插入，他的next是其父节点。如果作为右儿子插入，他的prev是其父节点
    // * 2.插入完成后，递归的更新其父节点链上的链，next应该是右子树上的最小值，prev应该是左子树上的最大值，不同时更新
    // *
    // * @param t  子树
    // * @param x  插入的值
    // * @param nt 下一个值
    // * @param pv 上一个值
    // * @return 新插入节点所在的子树的索引，用于更新沿途节点上的pv和nt节点
    // */
    //private Node<AnyType> insert(Node<AnyType> t, AnyType x, Node<AnyType> nt, Node<AnyType> pv) {
    //    if (x == null) {
    //        return null;
    //    }
    //
    //    if (t == null) {
    //        return new Node<AnyType>(x, null, null, nt, pv);
    //    }
    //
    //    int compareResult = x.compareTo(t.element);
    //
    //    if (compareResult < 0) {
    //        t.pv = findMax(insert(t.left, x, t, null));
    //    } else if (compareResult > 0) {
    //        t.nt = findMin(insert(t.right, x, null, t));
    //    } else {
    //        ;//当有该元素的时候，什么也不做
    //    }
    //
    //    return t;
    //}


    public void insert(AnyType x) {
        root = insert(x, root, null, null);
    }

    /**
     * 索引链应该是个双向链表
     * @param x
     * @param t
     * @param nt
     * @param pv
     * @return
     */
    private Node<AnyType> insert(AnyType x, Node<AnyType> t, Node<AnyType> nt, Node<AnyType> pv) {
        if (t == null) {
            modCount++;
            Node<AnyType> newNode = new Node<AnyType>(x, null, null, nt, pv);

            // 索引链应该是个双向链表
            if (nt != null) {
                nt.pv = newNode;
            }

            if (pv != null) {
                pv.nt = newNode;
            }

            return newNode;
        }

        int compareResult = x.compareTo(t.element);
        if (compareResult < 0)
            t.left = insert(x, t.left, t, pv);
        else if (compareResult > 0) {
            t.right = insert(x, t.right, nt, t);
        } else
            ; // duplicate
        return t;
    }

    public void delete(AnyType x) {
    }

    private Node<AnyType> delete(Node<AnyType> t, AnyType x) {
        if (x == null) {
            return null;
        }

        if (t == null) {
            return t;
        }

        int compareResult = x.compareTo(t.element);

        if (compareResult < 0) {
            t.left = delete(t.left, x);
        } else if (compareResult > 0) {
            t.right = delete(t.right, x);
        } else if (t.left != null && t.right != null) {
            t.element = findMin(t.right).element;
            t.right = delete(t.right, t.element);
        } else {
            t = (t.left != null) ? t.left : t.right;
        }

        return t;
    }

    /**
     * 根据查找二叉树的性质，寻找最小元素
     *
     * @param t 元素所在的子树
     * @return
     */
    private Node<AnyType> findMin(Node<AnyType> t) {
        if (t == null) {
            return null;
        } else if (t.left == null) {
            return t;
        }

        return findMin(t.left);
    }


    /**
     * 根据查找二叉树的性质，寻找最大元素
     *
     * @param t 元素所在的子树
     * @return
     */
    private Node<AnyType> findMax(Node<AnyType> t) {
        if (t == null) {
            return null;
        } else if (t.right == null) {
            return t;
        }

        return findMax(t.right);
    }

    /**
     * 包含通向想一个最小节点和上一个最大节点的链的节点
     *
     * @param <AnyType>
     */
    private static class Node<AnyType> {
        // 元素
        AnyType element;
        // 左子树的链
        Node<AnyType> left;
        // 右子树
        Node<AnyType> right;
        // 下一个比该节点大的链
        Node<AnyType> nt;
        //上一个比该节点小的链
        Node<AnyType> pv;

        public Node(AnyType element) {
            this(element, null, null, null, null);
        }

        public Node(AnyType element, Node<AnyType> left, Node<AnyType> right, Node<AnyType> nt, Node<AnyType> pv) {
            this.element = element;
            this.left = left;
            this.right = right;
            this.nt = nt;
            this.pv = pv;
        }
    }
}
