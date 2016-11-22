package datastucture.list;

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

    @Override
    public AnyType getHead() {
        return nowItem.data;
    }

    @Override
    public int size() {
        return theSize;
    }

    @Override
    public AnyType remove() {
        return null;
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
    public AnyType itrator() {
        return null;
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


        Node<AnyType> node = new Node<AnyType>(item, null, null);

        if (isEmpty()) {
            node.next = node;
            node.prev = node;
            head = node;
            nowItem = node;
            theSize++;
            return;
        }

        if (idx == size()) {

        }
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
}
