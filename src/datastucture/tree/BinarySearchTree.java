package datastucture.tree;

/**
 * 二叉查找树
 * <p/>
 * 1.二叉树成为二叉查找树的性质：对于树中的每个节点X，他的左子树上的所有项的值小于节点X的值，右子树上的所有项的值大于X的值
 * 2.由于涉及到数据的比较，所以提供一个标记接口Compareble或者一个函数对象Comparetor
 * 3.由于树是递归定义的，所以树中的方法好多都是递归写的。因为递归的树的栈空间只是O(logN)，所以不需要担心栈空间被耗尽
 * 4.如果删除的次数少，树一般使用懒惰删除（lazy deletion）。因为这样的时间开销要小非常多。因为树的删除也是递归的。（这里的初步入门类不使用懒惰删除）
 * <p/>
 * **树节点用嵌套类表示
 *
 * @author zhaoyan
 * @since 2016.11.30 9:58
 */
public class BinarySearchTree<AnyType extends Comparable<? super AnyType>> {

    // 树的数据结构仅保存对根节点的引用
    private BinaryNode<AnyType> root;

    public BinarySearchTree() {
        this.root = null;
    }

    public void makeEmpty() {
        root = null;
    }

    public boolean isEmpty() {
        return root == null;
    }

    public boolean contains(AnyType x) {
        return contains(x, root);
    }

    public AnyType findMin() {
        if (isEmpty()) {
            throw new UnderflowException();
        }

        return findMin(root).element;
    }

    private BinaryNode<AnyType> findMin(BinaryNode<AnyType> root) {
        return null;
    }

    public AnyType findMax() {
        if (isEmpty()) {
            throw new UnderflowException();
        }

        return findMax(root).element;
    }

    private BinaryNode<AnyType> findMax(BinaryNode<AnyType> root) {
        return null;
    }

    /**
     * 判断被搜索的数据是否在一颗子树上
     *
     * @param x 目标数据
     * @param t 子树
     * @return 是否有一个节点匹配搜索的数据
     */
    private boolean contains(AnyType x, BinaryNode<AnyType> t) {
        // 优先考虑基准情形；也可以认为这是对异常的判断，否则会报空指针异常
        if (t == null) {
            return false;
        }

        int compareResult = x.compareTo(t.element);

        if (compareResult < 0) {
            return contains(x, t.left);
        } else if (compareResult > 0) {
            return contains(x, t.right);
        } else {
            // 最不可能的情况放在if判断的最后
            return true;
        }
    }

    public void insert(AnyType x) {
        root = insert(x, root);
    }

    private BinaryNode<AnyType> insert(AnyType x, BinaryNode<AnyType> root) {
        return null;
    }

    public void remove(AnyType x) {
        root = remove(x, root);
    }

    private BinaryNode<AnyType> remove(AnyType x, BinaryNode<AnyType> root) {
        return null;
    }

    public void printTree() {
    }

    /**
     * 节点类
     *
     * @param <AnyType> 泛型设计
     */
    private static class BinaryNode<AnyType> {

        AnyType element;
        BinaryNode<AnyType> left;
        BinaryNode<AnyType> right;

        public BinaryNode(AnyType element) {
            this.element = element;
        }

        public BinaryNode(AnyType element, BinaryNode<AnyType> left, BinaryNode<AnyType> right) {
            this.element = element;
            this.left = left;
            this.right = right;
        }
    }
}
