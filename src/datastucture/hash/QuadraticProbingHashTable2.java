package datastucture.hash;

/**
 * 使用平方探测法实现散列表
 * <p/>
 * 参考了课本上的内容，对代码可读性进行了加强
 *
 * @author zhaoyan
 * @since 2017.01.13 10:59
 */
public class QuadraticProbingHashTable2<AnyType> {
    private static final int DEFAULT_TABLE_SIZE = 11;
    private HashEntry<AnyType>[] array;
    private int currentSize;

    public QuadraticProbingHashTable2() {
        this(DEFAULT_TABLE_SIZE);
    }

    public QuadraticProbingHashTable2(int size) {
        allocateArray(nextPrime(size));
        makeEmpty();
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
     * （逻辑上）清空哈希表
     */
    public void makeEmpty() {
        currentSize = 0;
        for (int i = 0; i < array.length; i++) {
            array[i] = null;
        }
    }

    /**
     * 判断一个元素是否在哈希表中
     *
     * @param x 被搜索的元素
     * @return 匹配结果
     */
    public boolean contains(AnyType x) {
        int currentPos = findPos(x);
        return isActive(currentPos);
    }

    /**
     * 插入数据，如果数据已经存在，则不做任何操作
     *
     * @param x 试图插入的数据
     */
    public void insert(AnyType x) {
        int currentPos = findPos(x);
        if (isActive(currentPos)) {
            return;
        }

        array[currentPos] = new HashEntry<AnyType>(x);

        if (++currentSize > array.length / 2) {
            rehash();
        }
    }

    public void remove(AnyType x) {
        int currentPos = findPos(x);
        if (isActive(currentPos)) {
            array[currentPos].isActive = false;
            currentSize--;
        }
    }

    /**
     * 为数组分配空间
     *
     * @param arraySize
     */
    private void allocateArray(int arraySize) {
        array = new HashEntry[arraySize];
    }

    /**
     * 当参数下标对应的数据存在且不被删除时，返回true
     *
     * @param currentPos
     * @return
     */
    private boolean isActive(int currentPos) {
        return array[currentPos] != null && array[currentPos].isActive;
    }

    /**
     * 二次探测法寻找下标
     *
     * @param x 要被搜索的目标
     * @return 搜索结束时的位置
     */
    private int findPos(AnyType x) {
        int offset = 1;
        int currentPos = myhash(x);

        // while里的顺序定一定是这样的，不能颠倒
        while (array[currentPos] != null && !array[currentPos].element.equals(x)) {
            // 下面利用了二次连续数列的性质，加法的效率是明显高于乘法的
            // 二次函数的性质为f(i+1)=f(i)+2i+1，或者说为f(i)=f(i-1)+2i-1
            currentPos += offset;
            // 连续二次函数数列1,4,9,16···的差为连续的奇数3,5,7，·····，故偏差累计加2
            offset += 2;
            if (currentPos >= array.length)
                currentPos -= array.length;
        }

        return currentPos;
    }

    private void rehash() {
        HashEntry<AnyType>[] newElements = new HashEntry[nextPrime(2 * array.length)];
        for (int i = 0; i < newElements.length; i++) {
            newElements[i] = null;
        }

        HashEntry<AnyType>[] oldElements = array;
        array = newElements;

        for (int i = 0; i < oldElements.length; i++) {
            if (oldElements[i] != null && oldElements[i].isActive) {
                insert(oldElements[i].element);
            }
        }
    }

    private int myhash(AnyType x) {
        int hashValue = x.hashCode();
        hashValue = hashValue % array.length;
        if (hashValue < 0)
            hashValue += array.length;

        return hashValue;
    }

    public static class HashEntry<AnyType> {
        public AnyType element;
        public boolean isActive;

        public HashEntry(AnyType e) {
            this(e, true);
        }

        public HashEntry(AnyType e, boolean i) {
            this.element = e;
            this.isActive = i;
        }
    }
}
