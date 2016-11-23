package datastucture.list;

import java.util.ConcurrentModificationException;
import java.util.Iterator;
import java.util.ListIterator;
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
            Node<AnyType> p = beginMarker.next;
            for (int i = 0; i < idx; i++) {
                p = p.next;
            }

            node = p;
        } else {
            // 因为索引元素是0到size()-1，所以if中的元素的指针直接就是beginMarker.next，此处是endMarker，而不是endMarker.prev
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

    public java.util.ListIterator<AnyType> listIterator() {
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

    private class LinkedListIterator implements java.util.ListIterator<AnyType> {

        private Node<AnyType> current = beginMarker.next;
        private int expectedModCount = modCount;
        private boolean okToRemove = false;

        private boolean isNextOption = false;

        @Override
        public boolean hasNext() {
            return current != endMarker;
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

            isNextOption = true;

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

        @Override
        public boolean hasPrevious() {
            return current.prev != beginMarker;
        }

        @Override
        public AnyType previous() {
            if (expectedModCount != modCount) {
                throw new ConcurrentModificationException();
            }

            if (!hasPrevious()) {
                throw new NoSuchElementException();
            }

            current = current.prev;
            AnyType previousItem = current.data;
            okToRemove = true;

            isNextOption = true;

            return previousItem;
        }

        @Override
        public int nextIndex() {
            throw new UnsupportedOperationException();
        }

        @Override
        public int previousIndex() {
            throw new UnsupportedOperationException();
        }

        @Override
        public void set(AnyType anyType) {
            if (isNextOption) {
                current.prev.data = anyType;
            } else {
                current.data = anyType;
            }
        }

        @Override
        public void add(AnyType anyType) {
            if (expectedModCount != modCount) {
                throw new ConcurrentModificationException();
            }
            //
            //if (!okToRemove) {
            //    throw new IllegalStateException();
            //}

            Node<AnyType> p = new Node<AnyType>(anyType, current.prev, current);
            //            Node<AnyType> p = new Node<AnyType>(anyType, null, null);
            //            if (isNextOption) {
            //                // 正向遍历的add操作是放在最后一次看到的元素的后面，同时下一次next迭代可以看到
            //                p.next = current;
            //                p.prev = current.prev;
            //                current.prev.next = p;
            //                current.prev = p;
            //            } else {
            //                // 逆向遍历的add操作是放在最后一次看到的元素的前面，同时下一次previous迭代可以看到
            //                p.next = current;
            //                p.prev = current.prev;
            //                current.prev.next = p;
            //                current.prev = p;
            //            }
            //很明显上述if分支里的代码是一样的，可以合并，同时p初始化时，指针可以从null直接变为current对应的变量
            //            p.next = current;
            //            p.prev = current.prev;
            current.prev.next = p;
            current.prev = p;

            modCount++;
            expectedModCount++;

            theSize++;
        }
    }

    @org.junit.Test
    public void test() {
        MyLinkedList<Integer> list = new MyLinkedList<Integer>();
        for (int i = 0; i < 10; i++) {
            list.add(i);
        }

        ListIterator<Integer> ltr = list.listIterator();
        // 测试迭代器初始化时直接插入数据
        ltr.add(-1);
        for (int i = 0; i < list.size(); i++) {
            System.out.printf(list.get(i) + " ");
        }
        System.out.println();
        // 测试游标指向结尾时插入数据
        while (ltr.hasNext()) {
            ltr.next();
        }
        ltr.add(99);

        for (int i = 0; i < list.size(); i++) {
            System.out.printf(list.get(i) + " ");
        }
        System.out.println();

        //        测试游标在最后一个元素后面，然后执行一次previous()操作，看是否可以正确的按照逆序的情况插入数据
        System.out.println("previous:" + ltr.previous());
        ltr.add(98);
        for (int i = 0; i < list.size(); i++) {
            System.out.printf(list.get(i) + " ");
        }
        System.out.println();

        while (ltr.hasPrevious()) {
            ltr.previous();
        }

        // 查看是否可以在中间插入数据
        while (ltr.hasNext()) {
            if (ltr.next() == 8) {
                ltr.add(888);
                break;
            }
        }


        for (int i = 0; i < list.size(); i++) {
            System.out.printf(list.get(i) + " ");
        }
        System.out.println();
    }
}
