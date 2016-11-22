package datastucture.list;

import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * 使用数组来实现List数据结构
 *
 * @author Administrator
 * @since 2016-11-18 6:02
 */

public class MyArrayList<AnyType> implements Iterable<AnyType> {
    // List对象初始化时，内置数组的容量
    private static final int DEFAULT_LIST_SIZE = 10;

    // 当前容量
    private int theSize;
    // List内置数组的引用
    private AnyType[] theItems;

    public MyArrayList() {
        clear();
    }

    public static void main(String[] args) {
        MyArrayList<Integer> testList = new MyArrayList<Integer>();

        for (int i = 0; i < 15; i++) {
            testList.add(i);
        }

        for (int i = 0; i < testList.size(); i++) {
            System.out.printf(testList.get(i) + ",");
        }

        System.out.println();

        Iterator<Integer> iterator = testList.iterator();
        while (iterator.hasNext()) {
            if (iterator.next() % 2 == 0) {
                iterator.remove();
            }
        }
        for (int i = 0; i < testList.size(); i++) {
            System.out.printf(testList.get(i) + ",");
        }
    }

    public void clear() {
        theSize = 0;
        ensureCapacity(DEFAULT_LIST_SIZE);
    }

    public int size() {
        return theSize;
    }

    public boolean isEmpty() {
        return theSize == 0;
    }

    public void trimToSize() {
        ensureCapacity(size());
    }

    public void ensureCapacity(int newSize) {
        if (theSize >= newSize) {
            return;
        }

        AnyType[] old = theItems;
        // java不允许创建泛型数组，此处只能先创建object，再强制类型转换
        theItems = (AnyType[]) new Object[newSize];
        // 此处只能是theSize，不能是old.length。因为有可能存在已经从list里删除的元素，其实该元素还是保留在数组里的情况
        for (int i = 0; i < size(); i++) {
            theItems[i] = old[i];
        }

    }

    public AnyType get(int idx) {
        if (idx < 0 || idx >= size()) {
            throw new ArrayIndexOutOfBoundsException();
        }

        return theItems[idx];
    }

    public AnyType set(AnyType newItem, int idx) {
        if (idx < 0 || idx >= size()) {
            throw new ArrayIndexOutOfBoundsException();
        }

        AnyType oldItem = theItems[idx];
        theItems[idx] = newItem;
        return oldItem;
    }

    public boolean add(AnyType newItem) {
        add(newItem, size());
        return true;
    }

    public void add(AnyType newItem, int idx) {
        if (size() == theItems.length) {
            // 加1是为了应对size为0的情况
            ensureCapacity(size() * 2 + 1);
        }

        for (int i = size(); i > idx; i--) {
            theItems[i] = theItems[i - 1];
        }

        theItems[idx] = newItem;

        theSize++;
    }

    public AnyType remove(int idx) {
        AnyType oldItem = theItems[idx];

        for (int i = idx; i < size(); i++) {
            theItems[i] = theItems[i + 1];
        }

        theSize--;

        return oldItem;
    }

    @Override
    public Iterator<AnyType> iterator() {
        return new ArrayListIterator();
    }

    public void addAll(Iterable<? extends AnyType> items) {
        Iterator<? extends AnyType> iterator = items.iterator();
        while (iterator.hasNext()) {
            add(iterator.next());
        }
    }

    private class ArrayListIterator implements java.util.Iterator<AnyType> {

        private int current = 0;

        @Override
        public boolean hasNext() {
            return current < size();
        }

        @Override
        public AnyType next() {
            if (!hasNext()) {
                throw new NoSuchElementException();
            }
            return theItems[current++];
        }

        @Override
        public void remove() {
            MyArrayList.this.remove(--current);
        }
    }
}
