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

    private Node<AnyType> root;
    private int theSize;

    public MyTreeSet() {
        root = null;
        theSize = 0;
    }

    @Override
    public int size() {
        return theSize;
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
        if (x == null) {
            return false;
        }

        if (t == null) {
            return false;
        } else if (x.compareTo(t.element) < 0) {
            return contains(x, t.left);
        } else if (x.compareTo(t.element) > 0) {
            return contains(x, t.right);
        } else {
            return true;
        }
    }

    @Override
    public Iterator<AnyType> iterator() {
        return null;
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

    private boolean add(AnyType x, Node<AnyType> t) {
        if (x == null) {
            return false;
        }

        if (t == null) {
            t = new Node<AnyType>(x);
            theSize++;
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
        return false;
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
        return false;
    }

    @Override
    public void clear() {
        root = null;
        theSize = 0;
    }

    private static class Node<AnyType> {
        Node<AnyType> left;
        Node<AnyType> right;
        AnyType element;

        public Node(AnyType element) {
            this.element = element;
            left = null;
            right = null;
        }

        public Node(Node<AnyType> left, Node<AnyType> right, AnyType element) {
            this.left = left;
            this.right = right;
            this.element = element;
        }
    }
}
