package datastucture.hash;

/**
 * 开放定址法实现散列表
 * 该方法不使用链表等形式的数据结构，而是将数据直接存储在数组中实现快速访问。
 * 但是这种情况下，会有冲突问题，一般的解决办法为通过某种操作，找到一个空单元将其存放。
 * 一般的方法有线性探测法，平方探测法，再散列等方法。
 * <p/>
 * 这种实现方法不能实现真正的删除操作，只能使用懒惰删除。
 * 因为真正删除之后，后面那些因为冲突而找到其他地址的元素将无法被找到。
 * <p/>
 * 这种实现方法的散列数组长度一定是素数
 * <p/>
 * 装填因子λ不能大于0.5
 * <p/>
 * <p/>
 * 该类使用线性探测法实现
 *
 * @author zhaoyan
 * @since 2017.01.13 9:40
 */
public class QuadraticProbingHashTable<AnyType> {
    private static final int DEFAULT_TABLE_SIZE = 101;
    private int theSize;
    private Entry<AnyType>[] elements;

    public QuadraticProbingHashTable() {
        this(DEFAULT_TABLE_SIZE);
    }

    public QuadraticProbingHashTable(int n) {
        elements = new Entry[nextPrime(n)];
        for (int i = 0; i < elements.length; i++) {
            elements[i] = new Entry<AnyType>();
        }
    }

    private static int nextPrime(int n) {
        while (!isPrime(n)) {
            n++;
        }
        return n;
    }

    private static boolean isPrime(int n) {
        return false;
    }

    /**
     * 线性探测法实现插入，线性函数为为f(i)=i
     *
     * @param x 要插入的值
     */
    public void insert(AnyType x) {
        // 如果包含了，则不再重复插入
        if (contains(x)) {
            return;
        }

        int hash = myhash(x);
        int i = 1;
        // 一直找到一个空的位置，或者被删除过的可用位置
        while (elements[hash].element != null && !elements[hash].isDeleted) {
            hash = myhash(hash + (++i));
        }

        elements[hash].element = x;
        elements[hash].isDeleted = false;
        theSize++;

        // 如果装填因子λ大于等于0.5，则需要再散列
        if (elements.length * 0.5 <= theSize)
            rehash();
    }

    public boolean contains(AnyType x) {

        int hashValue = myhash(x);
        int i = 1;
        while (elements[hashValue].element != null) {
            if (elements[hashValue].element.equals(x) && !elements[hashValue].isDeleted) {
                return true;
            } else {
                hashValue = myhash(hashValue + i);
                i++;
            }
        }

        return false;
    }

    public void remove(AnyType x) {
        int hashValue = myhash(x);
        int i = 1;
        while (elements[hashValue].element != null) {
            if (elements[hashValue].element.equals(x) && !elements[hashValue].isDeleted) {
                // 懒惰删除
                elements[hashValue].isDeleted = true;
                theSize--;
            } else {
                // 线性查找
                hashValue = myhash(hashValue + i);
                i++;
            }
        }

    }

    private int myhash(AnyType x) {
        int hashValue = x.hashCode();
        hashValue = hashValue % elements.length;
        if (hashValue < 0)
            hashValue += elements.length;

        return hashValue;
    }

    private int myhash(int hashValue) {
        hashValue = hashValue % elements.length;
        if (hashValue < 0)
            hashValue += elements.length;

        return hashValue;
    }

    private void rehash() {
        Entry<AnyType>[] newElements = new Entry[nextPrime(2 * elements.length)];
        for (int i = 0; i < newElements.length; i++) {
            newElements[i] = new Entry<AnyType>();
        }

        Entry<AnyType>[] oldElements = elements;
        elements = newElements;

        for (Entry<AnyType> oldElement : oldElements) {
            if (oldElement.element != null && !oldElement.isDeleted) {
                insert(oldElement.element);
            }
        }
    }

    private static class Entry<AnyType> {
        AnyType element;
        boolean isDeleted;

        public Entry() {
            this(null);
        }

        public Entry(AnyType x) {
            this.element = x;
            isDeleted = false;
        }
    }
}
