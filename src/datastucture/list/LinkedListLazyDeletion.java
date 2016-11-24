package datastucture.list;

/**
 * 实现了懒惰删除的链表
 * <p/>
 * 懒惰删除，就是对被删除的数据进行标记。当被删除根数和不被删除个数一样多时，执行普通的删除操作<br/>
 * 优点：提升效率。可以在某些插入操作时，直接将数据覆盖到原来已经被删除的值上。同时，虽然最终执行指针的次数是一样的，但是不是每一次都操作，所以很多操作很快。
 * 缺点：每个节点占用了空间。因为都需要一个bit（其实是byte）来存储标志位。
 *
 * @author Administrator
 * @since 2016-11-24 22:04
 */

public class LinkedListLazyDeletion<Object> {

    private int theSize;
    private int deletedCount;

    private Node<Object> head;
    private Node<Object> tail;

    public LinkedListLazyDeletion() {
        init();
    }

    public void print(boolean isAll) {
        Node<Object> p = head.next;
        while (p != tail) {
            if (isAll) {
                System.out.printf(p.data + " ");
            } else {
                if (!p.isDeleted) {
                    System.out.printf(p.data + " ");
                }
            }
            p = p.next;
        }
        System.out.println();
    }

    public void print() {
        print(true);
    }

    public int size() {
        return theSize;
    }

    public boolean isEmpty() {
        return theSize == 0;
    }

    private void init() {
        theSize = 0;
        deletedCount = 0;
        head = new Node<Object>();
        tail = new Node<Object>(null, head, null);
        head.next = tail;
    }

    private Node<Object> getNode(int index) {
        if (isOutOfIndex(index)) {
            throw new IndexOutOfBoundsException();
        }

        if (index < size() / 2) {
            Node<Object> p = head.next;
            int i = 0;
            while (i < index) {
                p = p.next;
                if (p.isDeleted) {
                    continue;
                }
                i++;
            }
            return p;
        } else {
            Node<Object> p = tail;
            int i = size();
            while (i > index) {
                p = p.prev;
                if (p.isDeleted) {
                    continue;
                }
                i--;
            }
            return p;
        }
    }

    public Object get(int i) {

        if (i < 0 || i >= size()) {
            throw new IndexOutOfBoundsException();
        }

        return getNode(i).data;
    }

    public void set(Object x, int i) {
        if (i < 0 || i >= size()) {
            throw new IndexOutOfBoundsException();
        }

        getNode(i).data = x;
    }

    private void addBefore(Node<Object> p, Object x) {
        // 如果节点p前面紧邻的节点是一个被安全删除的节点，则激活覆盖就可以
        if (p.prev != head && p.prev.isDeleted) {
            p.prev.data = x;
            p.prev.isDeleted = false;
            deletedCount--;
        } else {
            Node<Object> newNode = new Node<Object>(x, p.prev, p);
            p.prev.next = newNode;
            p.prev = newNode;
        }
        theSize++;
    }

    private Object safeRemove(Node<Object> p) {
        p.isDeleted = true;
        deletedCount++;
        theSize--;
        return p.data;
    }

    private void remove() {
        Node<Object> p = head.next;
        while (p != tail) {
            if (p.isDeleted) {
                p.prev.next = p.next;
                p.next.prev = p.prev;
            }
            p = p.next;
        }

        deletedCount = 0;
    }

    public Object remove(int i) {
        Object data = safeRemove(getNode(i));

        if (deletedCount >= theSize) {
            remove();
        }

        return data;
    }

    public void add(Object x) {
        add(x, size());
    }

    public void add(Object x, int idx) {
        addBefore(getNode(idx), x);
    }

    private boolean isOutOfIndex(int index) {
        return index < 0 || index > size();
    }

    private static class Node<Object> {
        public Object data;
        public Node<Object> prev;
        public Node<Object> next;
        public boolean isDeleted;

        public Node() {
        }

        public Node(Object data) {
            this.data = data;
        }

        public Node(Object data, Node<Object> prev, Node<Object> next) {
            this.data = data;
            this.prev = prev;
            this.next = next;
        }

        public Node(Object data, Node<Object> prev, Node<Object> next, boolean isDeleted) {
            this.data = data;
            this.prev = prev;
            this.next = next;
            this.isDeleted = isDeleted;
        }
    }

}
