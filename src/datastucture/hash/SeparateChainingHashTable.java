package datastucture.hash;

import java.util.LinkedList;
import java.util.List;

/**
 * 使用分离链接法开发哈希表
 * <p/>
 * jdk api（标准库）中的hashtmap和hashset大多是使用此方法实现的
 *
 * @author zhaoyan
 * @since 2017.01.12 15:22
 */
public class SeparateChainingHashTable<AnyType> {
    private static final int DEFAULT_TABLE_SIZE = 101;
    private List<AnyType>[] theLists;
    private int currentSize;

    public SeparateChainingHashTable() {
        this(DEFAULT_TABLE_SIZE);
    }

    public SeparateChainingHashTable(int size) {
        currentSize = 0;
        // 不能声明泛型数组，所以这里的数组不带泛型
        // 散列中，容器的长度最好（几乎是必须）是质数，这样散列度比较好
        theLists = new LinkedList[nextPrime(size)];
        for (int i = 0; i < theLists.length; i++) {
            // 对每个元素进行初始化
            theLists[i] = new LinkedList<AnyType>();
        }
    }

    /**
     * 获取比n大的下一个质数
     *
     * @param n
     * @return
     */
    private static int nextPrime(int n) {
        return 0;
    }

    /**
     * 判断参数n是不是质数
     *
     * @param n
     * @return
     */
    private static boolean isPrime(int n) {
        return false;
    }

    public void insert(AnyType x) {
        int index = myhash(x);
        List<AnyType> whichList = theLists[index];
        if (!whichList.contains(x)) {
            whichList.add(x);

            // 如果装填因子接近1，就需要再散列操作
            if (++currentSize > theLists.length) {
                rehash();
            }
        }

    }

    public void remove(AnyType x) {
        List<AnyType> whichList = theLists[myhash(x)];
        if (!whichList.contains(x)) {
            whichList.remove(x);
            currentSize--;
        }
    }

    public boolean contains(AnyType x) {
        return theLists[myhash(x)].contains(x);
    }

    public void makeEmpty() {
        currentSize = 0;
        for (int i = 0; i < theLists.length; i++) {
            theLists[i].clear();
        }
    }

    private void rehash() {
        // 首先分配更大的空间
        List<AnyType>[] newLists = new LinkedList[nextPrime(2 * theLists.length)];

        // 对新的容器进行初始化
        for (int i = 0; i < newLists.length; i++) {
            newLists[i] = new LinkedList<AnyType>();
        }

        List<AnyType>[] oldList = theLists;
        theLists = newLists;

        // 将旧的数据散列到新的容器内
        for (int i = 0; i < oldList.length; i++) {
            if (oldList[i].size() > 0) {
                for (AnyType x : oldList[i]) {
                    insert(x);
                }
            }
        }
    }

    private int myhash(AnyType x) {
        // 由于使用了泛型，所以这里要求每个类型自行实现合理的哈希算法
        int hashVal = x.hashCode();

        // 映射到对应的数组下标上
        hashVal %= theLists.length;

        // 如果哈希值出现负值，将其求为正值
        if (hashVal < 0) {
            hashVal += theLists.length;
        }

        return hashVal;
    }
}
