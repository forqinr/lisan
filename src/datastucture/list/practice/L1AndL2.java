package datastucture.list.practice;

import datastucture.list.MyArrayList;
import org.junit.Test;

/**
 * 使用基本的表操作两个已排序表L1和L2的交集
 *
 * @author Administrator
 * @since 2016-11-21 21:53
 */

public class L1AndL2<AnyType extends Comparable> {
    /**
     * 这个方法主要的任务是遍历，所以采用数组列表
     *
     * @param t1 列表1
     * @param t2 列表2
     *
     * @return 交集结果
     */
    public MyArrayList getIntersection(MyArrayList<AnyType> t1, MyArrayList<AnyType> t2) {
        if (t1 == null || t2 == null | t1.isEmpty() || t2.isEmpty()) {
            return null;
        }

        MyArrayList<AnyType> intersection = new MyArrayList<AnyType>();
        // 最简单的办法就是双层for循环做比较。但是这样的算法复杂度为O(n^2)。而且没有使用已知条件“已排序”
        // 另一个办法就是采用交替上升对比的方法
        int i1 = 0;
        int i2 = 0;

        while (i1 < t1.size() && i2 < t2.size()) {
            // 因为交集为集合，由集合的互异性，需要去除可能重复的元素
            if (!intersection.isEmpty()) {
                if (intersection.get(intersection.size() - 1).compareTo(t1.get(i1)) == 0) {
                    i1++;
                    continue;
                } else if (intersection.get(intersection.size() - 1).compareTo(t2.get(i2)) == 0) {
                    i2++;
                    continue;
                }
            }

            if (t1.get(i1) == t2.get(i2)) {
                intersection.add(t1.get(i1));
                i1++;
                i2++;
            } else {
                if (t1.get(i1).compareTo(t2.get(i2)) < 0) {
                    i1++;
                } else {
                    i2++;
                }
            }
        }

        return intersection;
    }

    @Test
    public void testGetIntersection() {
        MyArrayList<Integer> t1 = new MyArrayList<Integer>();

        for (int i = 0; i < 20; i++) {
            t1.add(i);
            t1.add(i);
        }

        MyArrayList<Integer> t2 = new MyArrayList<Integer>();

        for (int i = 10; i < 25; i++) {
            t2.add(i);
        }

        L1AndL2<Integer> intersection = new L1AndL2<Integer>();
        MyArrayList<Integer> result = intersection.getIntersection(t1, t2);

        for (int i = 0; i < result.size(); i++) {
            System.out.println(result.get(i));
        }
    }
}
