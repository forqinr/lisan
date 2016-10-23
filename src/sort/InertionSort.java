package sort;

import java.util.Arrays;

/**
 * 插入排序
 *
 * @author Administrator
 * @since 2016-09-29 6:40
 */

public class InertionSort {
    public static void main(String[] args) {
        Integer[] testArr = {2, 4, 9, 0, 3, 1, 7, 8, 6, 5};
        insertionSort(testArr);
        System.out.println(Arrays.deepToString(testArr));
    }

    public static void insertionSort(Integer[] array) {
        int temp = 0;
        // 从第二个元素开始拿出来作比较，所以i从下标1开始计算
        for (int i = 1; i < array.length; i++) {
            // 内部循环做比较
            int j = 0;
            //            while (j < i) {
            //                if (array[j] < array[i]) {
            //                    j++;
            //                } else {
            //                    break;
            //                }
            //            }

            while (array[j] < array[i])
                j++;

            temp = array[i];

            // 获取正确的元素下标之后，开始进行插入操作以及对应的元素顺序挪动操作
            for (int k = i - 1; k >= j; k--) {
                array[k + 1] = array[k];
            }

            array[j] = temp;

        }
    }
}
