package datastucture.list;

import java.util.*;

/**
 * 测试java.util中的List类的各种删除情况
 * 测试调用List的remove方法在删除一个之后正常调用的结果是否正常。
 *
 * @author Administrator
 * @since 2016-11-17 21:29
 */

public class ListRemoveTest {
    public static void main(String[] args) {
        //        List<Integer> lst = new ArrayList<Integer>(5);
        List<Integer> lst = new LinkedList<Integer>();
        lst.add(6);
        lst.add(5);
        lst.add(1);
        lst.add(4);
        lst.add(2);

        //        removeEvenVer1(lst);
//        removeEvenVer2(lst);
        removeEvenVer3(lst);
        System.out.println("----zhaoyan.test---lst:" + Arrays.deepToString(lst.toArray()) + "," + "当前类=ListRemoveTest.main");
        Calendar calendar=Calendar.getInstance();
        calendar.set(Calendar.MONTH,Calendar.FEBRUARY);
        // getActualMaximum返回给定域的最大值。如一个月的最后一天，一年的最后一个月。
        System.out.println(calendar.getActualMaximum(Calendar.DAY_OF_MONTH));
    }

    /**
     * 测试删除list中的偶数--使用基础循环·NG
     * <p/>
     * 结论：通过这个测试发现3个问题：
     * 1、数据被删除时，i会继续增长，而不是像迭代器一样停留在2个数据中间的位置。
     * 2、每次for循环，都会重新计算lst.size()，jre没有对其做优化。这是个低效的写法。在确定其不变时，应该赋值给一个局部变量。
     * 3、就是因为2的重新计算，所以才没有报list越界。
     *
     * @param lst
     */
    public static void removeEvenVer1(List<Integer> lst) {
        for (int i = 0; i < lst.size(); i++) {
            if (lst.get(i) % 2 == 0) {
                lst.remove(i);
            }
        }
    }

    /**
     * 测试删除list中的偶数--使用增强循环·NG
     * <p/>
     * 报异常：java.util.ConcurrentModificationException
     * 异常原因：在迭代器remove方法调用后，迭代器是失效的。应该在下一次next()方法调用之后再遍历其他。
     * <p/>
     * 增强for循环是用的是Collection的基础迭代器，该迭代器在remove时会再次搜索该项，是要消费二次时间的。
     * 另外，在remove完成后，再次推进循环，就会报上面的异常。
     * <p/>
     * 结论：增强for循环间接使用了迭代器，但是还是会报错。也就是说，跟真正的使用迭代器是不一样的。
     *
     * @param lst
     */
    public static void removeEvenVer2(List<Integer> lst) {
        for (Integer i : lst) {
            if (i % 2 == 0) {
                lst.remove(i);
            }
        }
    }

    /**
     * 测试删除list中的偶数--使用ListIterator·OK
     *
     * @param lst
     */
    public static void removeEvenVer3(List<Integer> lst) {
        Iterator<Integer> iterator = lst.iterator();

        // 此处返回了true，说明不是使用的基础迭代器，而是使用的List重写后的迭代器
        System.out.println(iterator instanceof ListIterator);
        while (iterator.hasNext()) {
            if (iterator.next() % 2 == 0) {
                iterator.remove();
            }
        }
    }
}
