package datastucture.tree;

/**
 * 实现懒惰删除的树
 *
 * @author zhaoyan
 * @since 2017.01.10 10:23
 */
public class LazyTree<AnyType extends Comparable<? super AnyType>> {

    Node<AnyType> root;
    int modCount = 0;

    public void makeEmpty() {
        root = null;
        modCount++;
    }

    public boolean isEmpty() {
        return root == null;
    }

    /**
     * 依照中序遍历(左儿子→父节点→右儿子)，找到第一个非空且isDeleted标志位为false的元素
     *
     * @param t 需要被便利的子树
     * @return 最小元素
     */
    private Node<AnyType> findMin(Node<AnyType> t) {

        Node<AnyType> st = null;

        if (t != null) {
            if (t.left != null) {
                st = findMin(t.left);
            }

            if (st == null && !t.isDeleted) {
                st = t;
            }

            if (st == null && t.right != null) {
                st = findMin(t.right);
            }
        }

        return st;
    }


    public AnyType findMin() {
        return findMin(root).element;
    }

    /**
     * 依照中序遍历(右儿子→父节点→左儿子)，找到第一个非空且isDeleted标志位为false的元素
     *
     * @param t 需要被便利的子树
     * @return 最小元素
     */
    private Node<AnyType> findMax(Node<AnyType> t) {
        Node<AnyType> st = null;
        if (t != null) {
            if (t.right != null) {
                st = findMax(t.right);
            }

            if (st == null && !t.isDeleted) {
                st = t;
            }

            if (st == null && t.left != null) {
                st = findMax(t.left);
            }
        }

        return st;
    }

    public boolean contains(AnyType x) {
        return contains(x, root);
    }

    private boolean contains(AnyType x, Node<AnyType> t) {
        if (t == null) {
            return false;
        }

        int compareResult = x.compareTo(t.element);

        if (compareResult < 0) {
            return contains(x, t.left);
        } else if (compareResult > 0) {
            return contains(x, t.right);
        } else if (t.isDeleted) {
            return false;
        } else {
            return true;
        }
    }

    public void insert(AnyType x) {
        root = insert(x, root);
    }

    private Node<AnyType> insert(AnyType x, Node<AnyType> t) {
        if (t == null) {
            modCount++;
            return new Node<AnyType>(x);
        }

        int compareResult = x.compareTo(t.element);

        if (t.isDeleted && (t.left == null || x.compareTo(t.left.element) > 0) && (t.right == null || x
                .compareTo(t.right.element) < 0)) {
            t.element = x;
            t.isDeleted = false;
            modCount++;
        } else if (compareResult < 0) {
            t.left = insert(x, t.left);
        } else if (compareResult > 0) {
            t.right = insert(x, t.right);
        } else if (t.isDeleted) {
            t.isDeleted = false;
            modCount++;
        }

        return t;
    }

    public void printTree() {
        if (isEmpty()) {
            System.out.println("Empty Tree");
        } else {
            printTree(root);
        }

    }

    private void printTree(Node<AnyType> t) {
        if (t != null) {
            printTree(t.left);
            if (!t.isDeleted)
                System.out.println(t.element);
            printTree(t.right);
        }
    }

    public void remove(AnyType x) {
        remove(x, root);
    }

    private void remove(AnyType x, Node<AnyType> t) {

        if (t == null) {
            return;
        }

        int compareResult = x.compareTo(t.element);

        if (compareResult < 0) {
            remove(x, t.left);
        } else if (compareResult > 0) {
            remove(x, t.right);
        } else if (!t.isDeleted) {
            t.isDeleted = true;
            modCount++;
        }
    }


    private static class Node<AnyType> {
        AnyType element;
        Node<AnyType> left;
        Node<AnyType> right;
        boolean isDeleted;

        public Node(AnyType element) {
            this(element, null, null, false);
        }

        public Node(AnyType element, Node<AnyType> left, Node<AnyType> right, boolean isDeleted) {
            this.element = element;
            this.left = left;
            this.right = right;
            this.isDeleted = isDeleted;
        }
    }
}
