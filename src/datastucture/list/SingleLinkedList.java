package datastucture.list;

/**
 * 修正后的MySingleLinkedList单链表
 * <p/>
 * 泛型即是编译器对Object的解释，这里直接使用Object
 *
 * @author Administrator
 * @since 2016-11-22 21:23
 */
public class SingleLinkedList<Object> {
    // 列表长度
    private int theSize;
    // 头节点的指示节点
    private Node<Object> head;

    public SingleLinkedList() {
        init();
    }

    public int size() {
        return theSize;
    }

    /**
     * 打印该单链表
     */
    public void print() {
        Node<Object> p = head.next;
        while (p != null) {
            System.out.println(p.data + " ");
            p = p.next;
        }
        System.out.println();
    }

    /**
     * 测试对象是否包含在链表中
     *
     * @param object 被测试对象
     *
     * @return 是否包含
     */
    public boolean contains(Object object) {
        Node<Object> p = head.next;

        while (p != null) {
            if (object.equals(p.data)) {
                return true;
            }
            p = p.next;
        }

        return false;
    }

    /**
     * 如果值不在链表中，添加值到链表中
     *
     * @param object 被添加的对象
     */
    public boolean add(Object object) {
        if (contains(object)) {
            return false;
        }

        Node<Object> addedItem = new Node<Object>(object);
        addedItem.next = head.next;
        head.next = addedItem;

        theSize++;
        return true;
    }

    /**
     * 由于类的add方法不会添加重复的元素，所以remove的时候，不需要考虑表中有相同的元素
     *
     * @param object
     */
    public boolean remove(Object object) {
        if (contains(object)) {
            Node<Object> p = head.next;
            // trailer 追踪者
            Node<Object> trailer = head;
            // 既然contains返回true，则肯定是包含的，而且contains函数中有判断空，所以不需要判断空，且在循环到结尾前肯定能找到该元素。
            // 所以以下完全可以简写

            // while (p != null) {
            //     if (object.equals(p.data)) {
            //         trailer.next = p.next;
            //         theSize--;
            //         return true;
            //     }
            //     trailer = p;
            //     p = p.next;
            // }

            // 简写为如下(元素将在循环结束时找到)：
            while (!object.equals(p.data)) {
                trailer = p;
                p = p.next;
            }

            trailer.next = p.next;

            theSize--;
            return true;
        }
        return false;
    }

    /**
     * 初始化一个单链表对象
     */
    private void init() {
        head = new Node<Object>();
        theSize = 0;
    }

    /**
     * 节点，用于封装数据，并携带指针，保持链表连续
     *
     * @param <Object>
     */
    private static class Node<Object> {
        public Object data;
        public Node<Object> next;

        public Node(Object data, Node<Object> next) {
            this.data = data;
            this.next = next;
        }

        // 增加了以下2个常用的构造器
        public Node() {
            this.data = null;
            this.next = null;
        }

        public Node(Object data) {
            this.data = data;
            this.next = null;
        }
    }
}
