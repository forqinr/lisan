package datastucture.tree;

import java.util.Collection;
import java.util.Iterator;
import java.util.Set;

/**
 * 实现treeset
 * 使用二叉查找树（不带平衡条件的）
 *
 * @author zhaoyan
 * @since 2016.12.22 8:25
 */
public class MyTreeSet<AnyType extends Comparable<? super AnyType>> implements Set<AnyType> {

    private Node<AnyType> root = null;
    private int modCount = 0;

    public MyTreeSet() {
        root = null;
    }

    public void makeEmpty() {
        modCount++;
        root = null;
    }

    @Override
    public int size() {
        return 0;
    }

    @Override
    public boolean isEmpty() {
        return root == null;
    }

    @Override
    public boolean contains(Object o) {
        AnyType x = (AnyType) o;
        return contains(x, root);
    }


    private boolean contains(AnyType x, Node<AnyType> t) {
        if (t == null) {
            return false;
        }

        if (x == null) {
            return false;
        }

        if (x.compareTo(t.element) < 0) {
            return contains(x, t.left);
        } else if (x.compareTo(t.element) > 0) {
            return contains(x, t.right);
        } else {
            return true;
        }
    }

    public AnyType findMin() throws UnderflowException {
        if (isEmpty()) {
            throw new UnderflowException();
        } else {
            return findMin(root).element;
        }
    }

    public AnyType findMax() throws UnderflowException {
        if (isEmpty()) {
            throw new UnderflowException();
        } else {
            return findMax(root).element;
        }
    }

    private Node<AnyType> findMax(Node<AnyType> t) {
        if (t == null) {
            return null;
        } else if (t.right == null) {
            return t;
        }

        return findMin(t.right);
    }

    @Override
    public Iterator<AnyType> iterator() {
        return new MyTreeSetIterator();
    }

    @Override
    public Object[] toArray() {
        return new Object[0];
    }

    @Override
    public <T> T[] toArray(T[] a) {
        return null;
    }

    @Override
    public boolean add(AnyType anyType) {
        return add(anyType, root);
    }

    public void insert(AnyType x) {
        root = insert(x, root, null);
    }

    private Node<AnyType> insert(AnyType x, Node<AnyType> t, Node<AnyType> pt) {
        if (t == null) {
            modCount++;
            return new Node<AnyType>(null, null, x, pt);
        }

        int compareResult = x.compareTo(t.element);

        if (compareResult < 0) {
            t.left = insert(x, t.left, t);
        } else if (compareResult > 0) {
            t.right = insert(x, t.right, t);
        } else {
            ; // duplicate
        }

        return t;
    }

    private boolean add(AnyType x, Node<AnyType> t) {
        if (x == null) {
            return false;
        }

        if (t == null) {
            t = new Node<AnyType>(x);
            return true;
        } else if (x.compareTo(t.element) < 0) {
            return add(x, t.left);
        } else if (x.compareTo(t.element) > 0) {
            return add(x, t.right);
        } else {
            return false;
        }
    }

    @Override
    public boolean remove(Object o) {
        if (contains((AnyType) o, root)) {
            remove(root, (AnyType) o);
            return true;
        }
        return false;
    }

    /**
     * 假设x一定存在在子树中
     *
     * @param x 要删除的值
     * @param t 子树
     * @return 删除结果
     */
    private Node<AnyType> remove(Node<AnyType> t, AnyType x) {
        if (t == null)
            return t; // not found

        if (x.compareTo(t.element) < 0) {
            t.left = remove(t.left, x);
        } else if (x.compareTo(t.element) > 0) {
            t.right = remove(t.right, x);
        } else if (t.left != null && t.right != null) {
            // 用该树的右子树的最小节点代替该节点
            t.element = findMin(t.right).element;
            // 递归的删除右子树上代替该节点的节点
            t.right = remove(t.right, t.element);
        } else {
            // 只有1个子树的情况或者没有子树的情况，处理就很简单了
            modCount++;
            Node<AnyType> oneChild;

            oneChild = (t.left != null) ? t.left : t.right;
            oneChild.parent = t.parent;// update parent link
            t = oneChild;
        }

        return t;
    }

    /**
     * 寻找一颗子树的最小值
     *
     * @param t
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

    @Override
    public boolean containsAll(Collection<?> c) {
        for (Object o : c) {
            if (!contains(o)) {
                return false;
            }
        }
        return true;
    }

    @Override
    public boolean addAll(Collection<? extends AnyType> c) {
        for (Object o : c) {
            AnyType x = (AnyType) o;

            if (!add(x)) {
                return false;
            }
        }
        return true;
    }

    @Override
    public boolean retainAll(Collection<?> c) {
        return false;
    }

    @Override
    public boolean removeAll(Collection<?> c) {
        if (containsAll(c)) {
            for (Object o : c) {
                remove(root, (AnyType) o);
            }
            return true;
        }
        return false;
    }

    @Override
    public void clear() {
        root = null;
    }


    public void remove(AnyType x) {
        root = remove(root, x);
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
            System.out.println(t.element);
            printTree(t.right);
        }
    }

    private static class Node<AnyType> {
        Node<AnyType> left;
        Node<AnyType> right;
        AnyType element;
        Node<AnyType> parent;

        public Node(AnyType element) {
            this.element = element;
            left = null;
            right = null;
        }

        public Node(Node<AnyType> left, Node<AnyType> right, AnyType element, Node<AnyType> pt) {
            this.left = left;
            this.right = right;
            this.element = element;
            this.parent = pt;
        }
    }

    private class MyTreeSetIterator implements java.util.Iterator<AnyType> {
        // 标记当前指针（迭代器的基本功能）
        private Node<AnyType> current = findMin(root);
        // 上一个指针
        private Node<AnyType> previous;
        // 操作计数器，防止出现主类和内部类操作不一样的情况
        private int expectedModCount = modCount;
        // 是否可以进行删除操作，也是防止出错的
        private boolean okToRemove = false;
        // 是否进行到了最后
        private boolean atEnd = false;

        @Override
        public boolean hasNext() {
            return !atEnd;
        }

        /**
         * 这个迭代器的实现是按照从小到大的（从最左边的子节点到最右边的子节点）顺序进行遍历推进的。
         * 这个实现中，保留了父节点的引用，没有用线索树
         *
         * @return 当前指向的节点对应的值
         */
        @Override
        public AnyType next() {
            if (modCount != expectedModCount)
            // 当方法检测到对象的并发修改，但不允许这种修改时，抛出此异常。
            {
                throw new java.util.ConcurrentModificationException();
            }
            if (!hasNext()) {
                throw new java.util.NoSuchElementException();
            }

            // 要返回的元素
            AnyType nextItem = current.element;

            // 从该方法的开始到这一行的所有内容，是所有实现迭代器的类的共同的地方。即首先验证，然后保留当前指针所指向的元素

            // 记录当前指针
            previous = current;

            // 如果当前节点有右子树，那么下一个遍历的开始就是该右子树上的最小值
            if (current.right != null) {
                current = findMin(current.right);
            } else {
                // 否则，寻找左子树的祖先节点

                // 保留当前节点的位置
                Node<AnyType> child = current;
                // 当前节点依赖指向父节点的指针，进行推进
                current = current.parent;

                // 当前节点不为空，且其刚才标记的位置（child）不是其左儿子，说明是从其右儿子推进上来的，但是其本身已经遍历完毕，故继续推进
                while (current != null && current.left != child) {
                    child = current;
                    current = current.parent;
                }

                // 推进到最后时，标记为已经到最后
                if (current == null) {
                    atEnd = true;
                }
            }

            // 这行以下也是所有的迭代器共有的。首先标记可删除标记为true，然后返回推进前所标记的节点的值
            okToRemove = true;
            return nextItem;
        }

        @Override
        public void remove() {
            if (modCount != expectedModCount) {
                throw new java.util.ConcurrentModificationException();
            }
            if (!okToRemove) {
                throw new IllegalStateException();
            }

            MyTreeSet.this.remove(previous.element);

            okToRemove = false;
        }
    }

}
