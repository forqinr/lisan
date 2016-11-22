package datastucture.list.practice;

import java.util.Iterator;

import org.junit.Test;

import datastucture.list.MyLinkedCircle;

/**
 * Josephus问题
 * <p/>
 * 该问题要求有N个人做成一个圈，传递热土豆，每次传递M下，M下之后拿着土豆的人离场（剔除元素），
 * 由离场的人下一个人接手土豆，继续游戏。问每次离场的人的顺序和最后剩下谁。
 * <p/>
 * 要求：尽可能的提高效率。要确保能够移除各个单元。
 * <p/>
 * 分析：
 * 1.使用环模型最佳
 * 2.这个问题里寻址和元素之间指向的操作兼而有之，从编码角度考虑，使用链（离散存储）模型。
 *
 * @author Administrator
 * @since 2016-11-22 6:36
 */

public class JosephusProblem<AnyType> {
    public void showJoInfo(Object[] anyObjectTypes, int m) {
        MyLinkedCircle<AnyType> circle = new MyLinkedCircle<AnyType>();
        AnyType[] anyTypes = (AnyType[]) anyObjectTypes;
        for (int i = 0; i < anyTypes.length; i++) {
            circle.add(anyTypes[i]);
        }


        Iterator<AnyType> iterator = circle.itrator();
        while (circle.size() > 1) {

            for (int i = 0; i < m; i++) {
                iterator.next();
            }

            System.out.println("Removed Item : " + iterator.next());
            iterator.remove();
        }

        System.out.println("The winner is : " + circle.get(1));
    }

    @Test
    public void test() {
        Integer[] array = new Integer[5];
        for (int i = 0; i < array.length; i++) {
            array[i] = i + 1;
        }
        int m = 2;
        showJoInfo(array, m);
    }
}
