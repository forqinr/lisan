package datastucture.list;

/**
 * 单链表实现
 * <p/>
 * 单链表当某个节点的next指向null的时候，表示链表结束。
 *
 * @author zhaoyan
 * @since 2016.11.22 18:45
 */
public class MySingleLinkedList<AnyType> {
    private int theSize;

    private Node<AnyType> beginMarker;

    public MySingleLinkedList() {
        this.theSize = 0;
        // 批注1：此处和下方多处使用这种带有1个null的构造方法，应该考虑到这个构造方法使用频率太低，需要重载其他构造方法
        this.beginMarker = new Node<AnyType>(null, null);
    }

    public int size() {
        return theSize;
    }

    public void printList() {
        // 批注2：此处应该直接指向链表的第一个节点。因为这样虽然在while中直接判断了，不会出错，但是node.next = node.next.next;这种写法可读性很差。
        Node<AnyType> node = beginMarker;
        while (node.next != null) {
            System.out.println("This is :" + node.item);
            node.next = node.next.next;
        }
    }

    public boolean contains(AnyType item) {

        boolean isContained = false;

        Node<AnyType> node = beginMarker;

        while (node.next != null) {

            if (item.equals(node.item))
                return true;

            node.next = node.next.next;
        }

        return isContained;
    }

    public void add(AnyType item) {
        Node<AnyType> addNode = new Node<AnyType>(item, null);

        Node<AnyType> node = beginMarker;
        // 批注3：并没有规定数据一定要加在表的最后。
        // 这种原因是因为做惯了数组列表和双向链表导致的。因为那些实现方法都是放在了最后。但是数组的寻址能力和双向链表的尾指针提升了效率。而单向链表则不行。
        while (node.next != null) {
            node.next = node.next.next;
        }

        node.next = addNode;

        theSize++;
    }

    public void addIfNotContains(AnyType item) {
        // 如果直接调用上方的contains和add方法也可以，但是需要遍历2遍，浪费效率。故此处重写，一边遍历，一边判断
        Node<AnyType> node = beginMarker;

        while (node.next != null) {
            if (item.equals(node.item))
                return;

            node.next = node.next.next;
        }

        Node<AnyType> addNode = new Node<AnyType>(item, null);
        node.next = addNode;

        theSize++;
    }

    public AnyType removeIfContains(AnyType item) {
        int beforeSize = size();
        int afterSize = size();
        Node<AnyType> nodeBefore = beginMarker;
        Node<AnyType> node = beginMarker.next;

        // 批注4：此处的参考答案中没有考虑重复元素的去除，效率很高
        // 不过根据现有的JDK API参考，貌似统一的都是默认只去除或者查询出1处，而不是所有。除非方法有特殊解释。
        while (node != null) {
            if (item.equals(node.item)) {
                if (node.next == null) {
                    nodeBefore.next = null;
                    node = null;
                } else {
                    nodeBefore = nodeBefore.next.next;
                    node = node.next.next;
                }
                theSize--;
                afterSize--;
            }
        }
        return beforeSize == afterSize ? null : item;
    }

    private static class Node<AnyType> {
        public AnyType item;
        public Node<AnyType> next;

        public Node(AnyType item, Node<AnyType> next) {
            this.item = item;
            this.next = next;
        }
    }
}
