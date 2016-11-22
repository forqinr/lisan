package datastucture.list;

import java.util.ConcurrentModificationException;
import java.util.NoSuchElementException;

/**
 * 用离散形式实现环
 *
 * @author Administrator
 * @since 2016-11-22 7:08
 */

public class MyLinkedCircle<AnyType> implements MyCircle<AnyType> {

    private int theSize;
    private Node<AnyType> head;
    private Node<AnyType> nowItem;
    private int modCount;

    public MyLinkedCircle() {
        clear();
    }

    @Override
    public AnyType getNow() {
        return nowItem.data;
    }

    @Override
    public AnyType get(int idx) {
        return getNode(idx).data;
    }

    @Override
    public int size() {
        return theSize;
    }

    @Override
    public AnyType remove() {
        if (nowItem == null) {
            throw new NullPointerException();
        }

        Node<AnyType> removedNode = nowItem;
        if (size() > 1) {
            removedNode.prev.next = removedNode.next;
            removedNode.next.prev = removedNode.prev;

            nowItem = nowItem.next;

            if (removedNode == head)
                head = head.next;
        } else {
            nowItem = null;
            head = null;
        }

        modCount++;
        theSize--;

        return removedNode.data;
    }

    @Override
    public AnyType remove(int idx) {
        if (idx < 1 || idx > size()) {
            throw new IndexOutOfBoundsException();
        }

        if (isEmpty()) {
            throw new NullPointerException();
        }

        Node<AnyType> removedNode = getNode(idx);

        if (size() > 1) {
            removedNode.prev.next = removedNode.next;
            removedNode.next.prev = removedNode.prev;

            if (nowItem == removedNode)
                nowItem = nowItem.next;

            if (removedNode == head)
                head = head.next;
        } else {
            nowItem = null;
            head = null;
        }


        modCount++;
        theSize--;

        return removedNode.data;
    }

    @Override
    public void clear() {
        theSize = 0;
        head = null;
        nowItem = null;
    }

    @Override
    public boolean isEmpty() {
        return theSize == 0;
    }

    @Override
    public java.util.Iterator<AnyType> itrator() {
        return new MyLinkedCircleIterator();
    }

    @Override
    public void add(AnyType item) {
        add(item, size());
    }

    @Override
    public void add(AnyType item, int idx) {
        if (item == null) {
            throw new IllegalStateException();
        }


        Node<AnyType> newNode = new Node<AnyType>(item, null, null);

        if (isEmpty()) {
            newNode.next = newNode;
            newNode.prev = newNode;
            head = newNode;
            nowItem = newNode;
            theSize++;
            modCount++;
            return;
        }

        // 此方法经常使用，为了减少遍历，故单独增加了此判断
        if (idx == size()) {
            newNode.prev = head.prev;
            newNode.next = head;
            head.prev.next = newNode;
            head.prev = newNode;
        } else {
            nowItem = getNode(idx);
            newNode.prev = nowItem.prev;
            newNode.next = nowItem;
            nowItem.prev.next = newNode;
            nowItem.prev = newNode;
        }

        theSize++;
        modCount++;
    }

    private Node<AnyType> getNode(int idx) {
        if (idx < 0 || idx > size()) {
            throw new IndexOutOfBoundsException();
        }

        Node<AnyType> node = head;

        if (idx < size() / 2) {
            for (int i = 0; i < idx; i++) {
                node = node.next;
            }
        } else {
            for (int i = size(); i > idx; i--) {
                node = node.prev;
            }
        }

        return node;
    }

    @Override
    public void addNow(AnyType item) {
        if (item == null) {
            throw new IllegalStateException();
        }

        Node<AnyType> newNode = new Node<AnyType>(item, null, null);

        if (isEmpty()) {
            newNode.next = newNode;
            newNode.prev = newNode;
            head = newNode;
        } else {
            newNode.prev = nowItem.prev;
            newNode.next = nowItem;
            nowItem.prev.next = newNode;
            nowItem.prev = newNode;
        }

        theSize++;
        modCount++;
    }

    private void remove(AnyType item) {

    }

    private static class Node<AnyType> {
        public AnyType data;
        public Node<AnyType> prev;
        public Node<AnyType> next;

        public Node(AnyType data, Node<AnyType> prev, Node<AnyType> next) {
            this.prev = prev;
            this.next = next;
            this.data = data;
        }
    }

    private class MyLinkedCircleIterator implements java.util.Iterator<AnyType> {

        private Node<AnyType> current = head;
        private int expectedModCount = modCount;
        private boolean okToRemove = false;

        @Override
        public boolean hasNext() {
            return !isEmpty();
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

            nowItem = current.prev;

            MyLinkedCircle.this.remove();

            okToRemove = false;
            expectedModCount++;
        }

    }
}
