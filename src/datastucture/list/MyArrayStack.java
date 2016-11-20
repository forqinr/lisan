package datastucture.list;

import java.util.EmptyStackException;

/**
 * 栈的数据结构
 * <p/>
 * 数组实现
 *
 * @author Administrator
 * @since 2016-11-20 15:59
 */

public class MyArrayStack<T> {
    // 数组的第0个元素表示栈底，存值为null。栈从1开始
    private static final int DEFAUT_SIZE = 101;
    private T[] elements;
    private int topIndex;

    public MyArrayStack() {
        elements = (T[]) new Object[DEFAUT_SIZE];
        topIndex = 0;
    }

    public boolean empty() {
        return topIndex == 0;
    }

    public T peek() {
        if (empty()) {
            throw new EmptyStackException();
        }

        return elements[topIndex];
    }

    public T pop() {
        if (empty()) {
            throw new EmptyStackException();
        }

        T popedElement = elements[topIndex];
        topIndex--;
        return popedElement;
    }

    public T push(T item) {
        // TODO 此处就不判断栈超出数组范围自动扩展了，假设无法超出
        elements[++topIndex] = item;
        return item;
    }
}
