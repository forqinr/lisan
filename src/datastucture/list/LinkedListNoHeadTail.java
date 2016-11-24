package datastucture.list;

import java.util.NoSuchElementException;

/**
 * 不使用头节点和尾节点实现链表
 * <p/>
 * 头节点的prev为null，尾节点的next为null
 *
 * @author Administrator
 * @since 2016-11-24 18:30
 */

public class LinkedListNoHeadTail<AnyType> {
    private int theSize;

    // 指向列表的第一个节点，当列表为空时，该也为空。因为题目规定不许使用头节点（列表以外的额外指示节点）
    private Node<AnyType> front;

    public LinkedListNoHeadTail() {
        theSize = 0;
        front = null;
    }

    public int size() {
        return theSize;
    }

    public boolean isEmpty() {
        return theSize == 0;
    }

    public String toString() {
        if (front == null) {
            return "[]";
        } else {
            String a = "[";

            Node<AnyType> node = front;
            while (node != null) {
                a = a + " " + node.data.toString();
                node = node.next;
            }
            a = a + "]";

            return a;
        }
    }

    private void addBefore(Node<AnyType> node, AnyType x) {
        Node<AnyType> newNode = new Node<AnyType>(x, null, node);
        if (front == null) {
            front = newNode;
        } else {
            // 用null来表示尾节点后面的位置,即tail.next
            if (node == null) {
                Node<AnyType> p = front;
                while (p.next != null) {
                    p = p.next;
                }
                newNode.prev = p;
                p.next = newNode;
            } else {
                if (node != front) {
                    newNode.prev = node.prev;
                    node.prev.next = newNode;
                }
                node.prev = newNode;
                front = newNode;
            }
        }

        theSize++;
    }

    public AnyType remove(int index) {
        Node<AnyType> node = getNode(index);
        if (node == null) {
            throw new NoSuchElementException();
        }

        return remove(node);
    }

    private AnyType remove(Node<AnyType> node) {
        AnyType data = node.data;
        if (node == front) {
            front = front.next;

            node.next.prev = null;
        } else if (node.next == null) {
            node.prev.next = null;
        } else {
            node.prev.next = node.next;
            node.next.prev = node.prev;
        }

        theSize--;
        return data;
    }

    public AnyType get(int i) {

        if (i >= size()) {
            throw new IndexOutOfBoundsException();
        }

        return getNode(i).data;
    }

    private Node<AnyType> getNode(int idx) {
        if (idx < 0 || idx > size()) {
            throw new IndexOutOfBoundsException();
        }

        Node<AnyType> node = front;

        for (int i = 0; i < idx; i++) {
            node = node.next;
        }

        return node;
    }

    public void add(AnyType x) {
        addBefore(null, x);
    }

    public void add(AnyType x, int idx) {
        addBefore(getNode(idx), x);
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
}
