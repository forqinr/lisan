package sort;

import java.util.Arrays;

/**
 * 归并排序
 * 递归实现
 *
 * @author Administrator
 * @since 2016-10-17 21:51
 */

public class MergeSort {
    /**
     * 归并排序第一步，将列表无限拆分为平衡二叉树
     */
    public static int[] mergeSort(int[] list) {
        // 都拆成只有1个元素的序列
        if (list.length > 1) {
            int n = list.length / 2;
            // Arrays.copyOfRange 的最后一个参数不包含在内，如取0到5个元素，则不包括第五个
            int[] listOne = Arrays.copyOfRange(list, 0, n);
            int[] listTwo = Arrays.copyOfRange(list, n, list.length);
            list = merge(mergeSort(listOne), mergeSort(listTwo));
        }
        return list;
    }

    // 第二步，排序
    public static int[] merge(int[] l1, int[] l2) {
        if (l1 == null || l1.length == 0) {
            return l2;
        }
        if (l2 == null || l2.length == 0) {
            return l1;
        }

        int[] l3 = new int[l1.length + l2.length];
        // k3下标指针
        int k = 0;
        // k1下标指针
        int i = 0;
        // k2下标指针
        int j = 0;
        while (i < l1.length && j < l2.length) {
            if (l1[i] < l2[j]) {
                l3[k] = l1[i];
                i++;
            } else {
                l3[k] = l2[j];
                j++;
            }
            k++;
        }

        if (i < l1.length) {
            while (i < l1.length) {
                l3[k] = l1[i];
                i++;
                k++;
            }
        }

        if (j < l2.length) {
            while (j < l2.length) {
                l3[k] = l2[j];
                j++;
                k++;
            }
        }


        return l3;
    }

    public static void main(String[] args) {
        int[] testList = {8, 2, 4, 6, 9, 7, 10, 1, 5, 3};
        int[] a = mergeSort(testList);
        Integer[] b = new Integer[a.length];
        for (int i = 0; i < a.length; i++) {
            b[i] = a[i];
        }
        System.out.println(Arrays.deepToString(b));
    }
}
