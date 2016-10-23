package sort;

import java.util.Arrays;

/**
 * 冒泡排序
 *
 * @author Administrator
 * @since 2016-09-28 22:02
 */

public class BubbleSort {

    public static void main(String[] args) {
        Integer[] testArr = {2, 4, 9, 0, 3, 1, 7, 8, 6, 5};
        bubbleSort(testArr);
        System.out.println(Arrays.deepToString(testArr));
    }

    public static void bubbleSort(Integer[] array) {
        // 排序遍数
        for (int i = 0; i < array.length; i++) {
            //需要排序的序列
            for (int j = 0; j < array.length - i - 1; j++) {
                if (array[j] > array[j + 1]) {
                    int temp = array[j];
                    array[j] = array[j + 1];
                    array[j + 1] = temp;
                }
            }
            System.out.println(Arrays.deepToString(array));
        }
    }
}
