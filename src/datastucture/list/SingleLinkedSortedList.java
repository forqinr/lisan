package datastucture.list;

import org.junit.*;

/**
 * 始终排序的单链表
 *
 * @author Administrator
 * @since 2016-11-22 22:15:59
 */
public class SingleLinkedSortedList<AnyType extends Comparable<AnyType>> {
    // 列表长度
    private int theSize;
    // 头节点的指示节点
    private Node<AnyType> head;

    public SingleLinkedSortedList() {
        init();
    }

    public int size() {
        return theSize;
    }

    /**
     * 打印该单链表
     */
    public void print() {
        Node<AnyType> p = head.next;
        while (p != null) {
            System.out.print(p.data + " ");
            p = p.next;
        }
        System.out.println();
    }

    /**
     * 测试对象是否包含在链表中
     *
     * @param x 被测试对象
     *
     * @return 是否包含
     */
    public boolean contains(AnyType x) {
        Node<AnyType> p = head.next;

        while (p != null) {
            if (x.compareTo(p.data) == 0) {
                return true;
            }
            p = p.next;
        }

        return false;
    }

    /**
     * 如果值不在链表中，添加值到链表中
     *
     * @param x 被添加的对象
     */
    public boolean add(AnyType x) {
        if (contains(x)) {
            return false;
        }

        Node<AnyType> p = head.next;

        // 批注1：由于是排序的，所以只判断值就行，因为理论上，找到比当前迭代指针大的元素的话，就插在起前面即可
        //        // 如果比第一个比头节点还小，则直接插入到头节点前
        //        if (x.compareTo(p.data) < 0) {
        //            Node<AnyType> node = new Node<AnyType>(x, p);
        //            head.next = node;
        //        } else {
        //            Node<AnyType> trailer = head;
        //
        //            // 找中间节点
        //            while (!(p.data.compareTo(x) > 0 && trailer.data.compareTo(x) < 0) && p != null) {
        //                trailer = p;
        //                p = p.next;
        //            }
        //
        //            Node<AnyType> addedItem = new Node<AnyType>(x, p);
        //
        //            trailer.next = addedItem;
        //        }

        // 批注1修正如下：
        Node<AnyType> trailer = head;
        while (p != null && p.data.compareTo(x) < 0) {
            trailer = p;
            p = p.next;
        }
        Node<AnyType> addedItem = new Node<AnyType>(x, p);
        trailer.next = addedItem;

        theSize++;
        return true;
    }

    /**
     * 由于类的add方法不会添加重复的元素，所以remove的时候，不需要考虑表中有相同的元素
     *
     * @param x
     */
    public boolean remove(AnyType x) {
        if (!contains(x)) {
            return false;

        } else {
            Node<AnyType> p = head.next;
            // trailer 追踪者
            Node<AnyType> trailer = head;

            while (x.compareTo(p.data) != 0) {
                trailer = p;
                p = p.next;
            }

            trailer.next = p.next;

            theSize--;
            return true;
        }
    }

    /**
     * 初始化一个单链表对象
     */
    private void init() {
        head = new Node<AnyType>();
        theSize = 0;
    }

    /**
     * 节点，用于封装数据，并携带指针，保持链表连续
     *
     * @param <AnyType>
     */
    private static class Node<AnyType> {
        public AnyType data;
        public Node<AnyType> next;

        public Node(AnyType data, Node<AnyType> next) {
            this.data = data;
            this.next = next;
        }

        // 增加了以下2个常用的构造器
        public Node() {
            this.data = null;
            this.next = null;
        }

        public Node(AnyType data) {
            this.data = data;
            this.next = null;
        }
    }

}
