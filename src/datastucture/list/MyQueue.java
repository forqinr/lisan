package datastucture.list;

/**
 * 队列的数据模型
 * Created by Administrator on 2016/11/21.
 */
public interface MyQueue<AnyType> {
    /**
     * insert an element at the queue head
     *
     * @param element
     */
    public void enqueue(AnyType element) throws IllegalStateException, NullPointerException;

    /**
     * delete an element at the queue head
     */
    public AnyType dequeue();

    /**
     * return the first element in the queue
     *
     * @return
     */
    public AnyType peek();
}
