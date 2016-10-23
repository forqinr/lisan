package search;

/**
 * 线性排序
 */
public class LineSearchDemo {

    public static void main(String[] args) {
        int[] testArr = { 2, 4, 9, 0, 3, 1, 7, 8, 6, 5 };
        System.out.println(max(testArr));
    }

    public static int max(int[] arr) {
        int temp = arr[0];

        for (int i = 1; i < arr.length; i++) {
            if (arr[i] > temp) {
                temp = arr[i];
            }
        }

        return temp;
    }

}
