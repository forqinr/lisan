package change;

/**
 * 贪心算法
 *
 * @author Administrator
 * @since 2016-09-29 21:05
 */

public class Change {
    /**
     * 场景是从有多种组合的序列中找出n的零钱
     *
     * @param c 零钱的种类，可能是1美分，2美分和5美分（因为100美分=1美元，这样n取值范围大）
     *          零钱的面值是从大到小排列的
     * @param n 需要找的钱的总是
     *
     * @return 找钱的序列
     */
    public static int[] change(int[] c, int n) {
        int[] result = new int[10];
        int j = 0;
        for (int i = 0; i < c.length; i++) {
            while (n >= c[i]) {
                result[j] = c[i];
                j++;
                n -= c[i];
            }
        }
        return result;
    }

    public static void main(String[] args) {
        int[] c = {25, 10, 5, 1};
        int[] result = change(c, 67);
        for (int i = 0; i < result.length; i++) {
            System.out.println(result[i]);
        }
    }
}
