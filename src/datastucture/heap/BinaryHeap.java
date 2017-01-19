package datastucture.heap;

import java.util.Arrays;

import datastucture.tree.UnderflowException;

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
        buildHeap();
    }

    public BinaryHeap(int capacity) {
        array = (AnyType[]) new Object[capacity];
        currentSize = 0;
    }

    public BinaryHeap(AnyType[] items) {
        array = (AnyType[]) new Object[items.length * 2];

        for (int i = 0; i < items.length; i++) {
            // 假设items的0处存有值
            array[i + 1] = items[i];
        }
        currentSize = items.length;
    }

    /**
     * 插入到有限队列（堆），同时保持堆序
     * 允许重复
     *
     * @param x 要插入的元素
     */
    public void insert(AnyType x) {
        // 因为下标0处是不存储元素的，所以currentSize最大为array.length - 1
        if (currentSize == array.length - 1) {
            enlargeArray(array.length * 2 + 1);
        }

        // 找到下一个可用的空穴
        int hole = ++currentSize;

        // 在穴坐标小于等于1或者x比他的父节点小时，停止循环。如果继续循环，则执行上滤，即其父节点下沉到当前穴
        for (; hole > 1 && x.compareTo(array[hole / 2]) < 0; hole /= 2) {
            array[hole] = array[hole / 2];
        }

        array[hole] = x;
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

    /**
     * 删除最小元素
     *
     * @return
     * @throws UnderflowException
     */
    public AnyType deleteMin() throws UnderflowException {
        if (isEmpty()) {
            throw new UnderflowException();
        }

        AnyType minItem = findMin();
        array[1] = array[currentSize--];
        percolateDown(1);
        return minItem;
    }

    public boolean isEmpty() {
        return currentSize == 0;
    }

    public void makeEmpty() {
        currentSize = 0;
        Arrays.fill(array, null);
    }

    private void percolateDown(int hole) {
        int child;
        AnyType tmp = array[hole];

        for (; hole * 2 <= currentSize; hole = child) {
            child = hole * 2;
            // 判断右儿子是不是比左儿子小，如果是，则取左儿子下标
            // 下面这种跟currentSize的比较方法，既避免了偶数个节点（有一个节点只有一个儿子）的情况的判断，又可以判断是否到了最后一层
            if (child != currentSize && array[child + 1].compareTo(array[child]) < 0) {
                child++;
            }

            if (array[child].compareTo(tmp) < 0) {
                array[hole] = array[child];
            } else {
                break;
            }
        }

        array[hole] = tmp;
    }

    private void buildHeap() {
        array = (AnyType[]) new Object[DEFAULT_CAPACITY];
        currentSize = 0;
    }

    private void enlargeArray(int newSize) {
        AnyType[] newArray = (AnyType[]) new Object[newSize];
        for (int i = 0; i < array.length; i++) {
            newArray[i] = array[i];
        }

        array = newArray;
    }

    /**
     * 返回最小值
     *
     * @return
     */
    private AnyType findMin() {
        return array[1];
    }
}
