package search;

/**
 * 二分查找
 * 二分查找适用于已经排序好的序列中找到元素
 *
 * @author Administrator
 * @since 2016-09-28 21:17
 */

public class BinarySearch {
    public static int binarySearch(int[] array, int x) {
        int i = 0;
        int j = array.length - 1;

        while (i < j) {
            if (array[(i + j) / 2] < x)
                i = (i + j) / 2 + 1;
            else
                j = (i + j) / 2;
        }

        if (array[i] == x)
            return i + 1;
        else
            return 0;
    }

    public static void main(String[] args) {
        int[] testArr = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10};
        for (int i = 0; i < testArr.length; i++) {
            System.out.println(i + ":" + binarySearch(testArr, i + 1));
        }
    }
}
