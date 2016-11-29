package datastucture.list;

import java.util.NoSuchElementException;

/**
 * 双端队列
 *
 * @author Administrator
 * @since 2016-11-25 20:35
 */

public class Deque<AnyType> {

    // 总是保留head和tail节点，可以省去很多编码上的判断，以及去除头尾节点时指针的移动操作。
    private Node<AnyType> head = null;
    private Node<AnyType> tail = null;

    public Deque() {
        init();
    }

    private void init() {
        head = new Node<AnyType>();
        tail = new Node<AnyType>(null, head, null);
        head.next = tail;
    }

    public void push(AnyType x) {
        Node p = new Node(x, head, head.next);
        head.next.prev = p;
        head.next = p;
    }

    public AnyType pop() {
        if (head.next == tail) {
            throw new NoSuchElementException();
        }

        Node<AnyType> p = head.next;

        head.next = p.next;
        p.next.prev = head;

        return p.data;
    }

    public void inject(AnyType x) {
        Node<AnyType> p = new Node<AnyType>(x, tail.prev, tail);
        tail.prev.next = p;
        tail.prev = p;
    }

    public AnyType eject() {
        if (tail.prev == head) {
            throw new NoSuchElementException();
        }

        Node<AnyType> p = tail.prev;
        tail.prev = p.prev;
        p.prev.next = tail;
        return p.data;
    }

    public void printFromTail() {
        Node<AnyType> p = tail.prev;
        while (p != head) {
            System.out.printf(" " + p.data);
        }
        System.out.println();
    }

    private static class Node<AnyType> {
        // 因为其他顶级类无法获取该内部类的引用，或者说无法实例化该类，所以该类的实例域都是public的无所谓。
        // 同时省去了编写get和set方法

        // 数据域
        public AnyType data;
        // 前驱节点链
        public Node<AnyType> prev;
        // 后继节点链
        public Node<AnyType> next;

        public Node() {
        }

        public Node(AnyType data) {
            this.data = data;
        }

        public Node(AnyType data, Node<AnyType> prev, Node<AnyType> next) {
            this.data = data;
            this.prev = prev;
            this.next = next;
        }
    }
}
