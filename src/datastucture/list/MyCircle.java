package datastucture.list;

/**
 * Created by Administrator on 2016/11/22.
 */
public interface MyCircle<AnyType> {
    /**
     * 获取当前环的当前指向元素
     *
     * @return
     */
    AnyType getNow();

    AnyType get(int idx);

    /**
     * 获取环的容量
     *
     * @return
     */
    int size();

    /**
     * 删除当前指向的节点
     */
    AnyType remove();

    /**
     * 删除指定位置的元素
     * @param idx
     * @return
     */
    AnyType remove(int idx);

    /**
     * 清空环
     */
    void clear();

    /**
     * 是否含有0个元素
     *
     * @return
     */
    boolean isEmpty();

    /**
     * 返回一个迭代器
     *
     * @return
     */
    java.util.Iterator<AnyType> itrator();

    /**
     * 向环中增加一个元素，增加到环的最后
     *
     * @param item
     */
    void add(AnyType item);

    /**
     * 在当前节点的后方加入一个元素
     *
     * @param item
     */
    void addNow(AnyType item);

    /**
     * 在指定位置加入元素
     *
     * @param item
     * @param idx
     */
    void add(AnyType item, int idx);
}
