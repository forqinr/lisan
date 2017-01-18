package datastucture.heap;

/**
 * 堆的数组实现
 * <p/>
 * 堆的两个性质：结构性质（完全二叉树）、堆序性质（最小元素永远在根节点）
 * <p/>
 * 根元素在数组的下标为1的位置
 * 一个元素的下标为i，他的左儿子的下标为2i，右儿子为2i+1
 * 一个元素的父节点的下标为j/2向下取整
 * <p/>
 * 插入操作涉及到一个操作叫做上滤（percolate up）
 *
 * @author zhaoyan
 * @since 2017.01.18 9:44
 */
public class BinaryHeap<AnyType extends Comparable<? super AnyType>> {
    private static final int DEFAULT_CAPACITY = 10;
    private int currentSize;
    private AnyType[] array;

    public BinaryHeap() {
    }

    public BinaryHeap(int capacity) {
        this.currentSize = capacity;
    }

    public BinaryHeap(AnyType[] items) {
        this.array = items;
    }

    public void insertByRecursion(AnyType x) {
        if (array.length - 1 == currentSize) {
            enlargeArray(array.length * 2 + 1);
        }

        insert(x, array.length);
    }

    /**
     * 递归方法插入元素
     *
     * @param x
     * @param index
     */
    private void insert(AnyType x, int index) {
        if (index == 1 || index == 0) {
            array[1] = x;
            currentSize++;
        }

        int compareResult = x.compareTo(array[index / 2]);

        if (compareResult < 0) {
            // 如果某元素比她的父节点小，则把他的父节点复制到该穴的位置，该插入值上滤
            array[index] = array[index / 2];
            insert(x, index / 2);
        } else if (compareResult > 0) {
            array[index] = x;
            currentSize++;
        } else {
            ;// do nothing
        }
    }

    public AnyType deleteMin() {
        return null;
    }

    public boolean isEmpty() {
        return currentSize == 0;
    }

    public void makeEmpty() {
    }

    private void percolateDown(int hole) {
    }

    private void buildHeap() {
    }

    private void enlargeArray(int newSize) {
    }
}
