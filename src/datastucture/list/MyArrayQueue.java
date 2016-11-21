package datastucture.list;

import java.util.Arrays;
import java.util.NoSuchElementException;

/**
 * 使用循环数组的方式来实现队列（FIFO）
 * 支持泛型
 *
 * @author Administrator
 * @since 2016-11-21 20:06
 */

public class MyArrayQueue<AnyType> implements MyQueue<AnyType> {

    // 队列的大小
    private int theSize;

    // 队列中持有的数组
    private AnyType[] theItems;

    // 队列头的标志
    private int front;

    // 队列尾的标志
    private int back;

    // 队列的最大存储量（其实是保持的数组的初始大小。此次练习不支持扩充。所以这是队列的最大容量）
    private static final int DEFAULT_SIZE = 10;

    public MyArrayQueue() {
        clear();
    }

    private void clear() {
        if (theItems == null) {
            theItems = (AnyType[]) new Object[DEFAULT_SIZE];
        } else {
            Arrays.fill(theItems, null);
        }

        front = 0;
        // 其实在很多判断里面，可以通过尾节点等于头结点减一来判断该队列为空。
        // 但是那有一种情形就是队列是满的时候，会出错，所以这里保有theSize这个变量
        back = (front - 1 + DEFAULT_SIZE) % DEFAULT_SIZE;
        theSize = 0;
    }

    @Override
    public void enqueue(AnyType element) throws IllegalStateException, NullPointerException {
        if (theSize >= DEFAULT_SIZE) {
            throw new IllegalStateException();
        }

        if (null == element) {
            throw new NullPointerException();
        }

        // 在尾部入队
        theItems[++back % DEFAULT_SIZE] = element;
        theSize++;
    }

    @Override
    public AnyType dequeue() {
        if (0 == theSize) {
            throw new NoSuchElementException();
        }

        AnyType element = theItems[front];
        front = (front + 1) % DEFAULT_SIZE;
        theSize--;

        return element;
    }

    public static void main(String[] args) {
        System.out.println((-1 + 10) % 10);
    }

    @Override
    public AnyType peek() {
        if (theSize == 0) {
            return null;
        }
        return theItems[front];
    }
}
