package datastucture.list;

import java.util.ConcurrentModificationException;
import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * 链表数据结构
 *
 * @author Administrator
 * @since 2016-11-19 10:25
 */

public class MyLinkedList<AnyType> {
    private int theSize = 0;
    private int modCount = 0;
    // 总是保留head和tail节点，可以省去很多编码上的判断，以及去除头尾节点时指针的移动操作。
    private Node<AnyType> beginMarker = null;
    private Node<AnyType> endMarker = null;

    public MyLinkedList() {
        clear();
    }

    public boolean isEmpty() {
        return theSize == 0;
    }

    /**
     * 声明一个空链表
     */
    public void clear() {
        beginMarker = new Node<AnyType>(null, null, null);
        endMarker = new Node<AnyType>(null, beginMarker, null);
        beginMarker.next = endMarker;

        theSize = 0;
        modCount++;
    }

    public int size() {
        return theSize;
    }

    public AnyType get(int idx) {
        return getNode(idx).data;
    }

    public AnyType set(AnyType data, int idx) {
        Node<AnyType> node = getNode(idx);
        AnyType old = node.data;

        node.data = data;

        return old;
    }

    private void addBefore(Node<AnyType> p, AnyType x) {
        Node<AnyType> newNode = new Node<AnyType>(x, p.prev, p);
        p.prev = p.prev.next = newNode;
        theSize++;
        modCount++;
    }

    public void add(AnyType data, int idx) {
        Node<AnyType> prevNode = getNode(idx);
        addBefore(prevNode, data);
    }

    public void add(AnyType data) {
        add(data, size());
    }

    public AnyType remove(int idx) {
        return remove(getNode(idx));
    }

    private AnyType remove(Node<AnyType> node) {

        AnyType data = node.data;

        node.next.prev = node.prev;
        node.prev.next = node.next;

        theSize--;
        modCount++;

        return data;
    }

    public void removeAll(Iterable<? extends AnyType> items) {
        Iterator<? extends AnyType> iterator = items.iterator();
        while (iterator.hasNext()) {

            AnyType outer = iterator.next();

            Iterator iterator1 = this.iterator();
            while (iterator1.hasNext()) {
                AnyType innerNode = (AnyType) iterator1.next();
                if (innerNode.equals(outer)) {
                    iterator1.remove();
                }
            }
        }
    }

    private Node<AnyType> getNode(int idx) {
        if (idx > size() || idx < 0) {
            throw new IndexOutOfBoundsException();
        }

        Node<AnyType> node;

        if (idx < size() / 2) {
            Node<AnyType> p = beginMarker;
            for (int i = 0; i < idx; i++) {
                p = p.next;
            }

            node = p;
        } else {
            Node<AnyType> p = endMarker;
            for (int i = size(); i > idx; i--) {
                p = p.prev;
            }

            node = p;
        }

        return node;
    }

    public java.util.Iterator<AnyType> iterator() {
        return new LinkedListIterator();
    }

    /**
     * 数据节点的模型类
     * 嵌套类
     * 该类不需要操作外部类的任何元素，而且其实例化也不需要任何外部类的引用参与，故声明为static
     *
     * @param <AnyType> 范型
     */
    private static class Node<AnyType> {
        // 因为其他顶级类无法获取该内部类的引用，或者说无法实例化该类，所以该类的实例域都是public的无所谓。
        // 同时省去了编写get和set方法

        // 数据域
        public AnyType data;
        // 前驱节点链
        public Node<AnyType> prev;
        // 后继节点链
        public Node<AnyType> next;

        public Node(AnyType data, Node<AnyType> prev, Node<AnyType> next) {
            this.data = data;
            this.prev = prev;
            this.next = next;
        }
    }

    private class LinkedListIterator implements java.util.Iterator<AnyType> {

        private Node<AnyType> current = beginMarker.next;
        private int expectedModCount = modCount;
        private boolean okToRemove = false;

        @Override
        public boolean hasNext() {
            return current.next != endMarker;
        }

        @Override
        public AnyType next() {
            if (expectedModCount != modCount) {
                throw new ConcurrentModificationException();
            }

            if (!hasNext()) {
                throw new NoSuchElementException();
            }
            AnyType nextItem = current.data;
            current = current.next;
            okToRemove = true;
            return nextItem;
        }

        @Override
        public void remove() {
            if (expectedModCount != modCount) {
                throw new ConcurrentModificationException();
            }

            if (!okToRemove) {
                throw new IllegalStateException();
            }

            MyLinkedList.this.remove(current.prev);
            okToRemove = false;
            expectedModCount++;
        }


    }
}
