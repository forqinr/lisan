package bigO;

import static java.lang.Math.pow;

/**
 * 多项式计算
 * 求解多项式Σanxn（第一个n为下标，第二个为指数）
 *
 * @author Administrator
 * @since 2016-10-03 20:53
 */

public class Duoxiangshi {
    /**
     * 由于pow的复杂度未知，故该算法复杂度未知，应该是n的平方的复杂度
     * 复杂度为f(n2)
     *
     * @param a
     * @param x
     *
     * @return
     */
    public static int mySuanfa1(int[] a, int x) {
        int sum = 0;
        for (int i = 0; i < a.length; i++) {
            sum += a[i] * pow(x, i);
        }
        return sum;
    }


    private static int pow1(int x, int n) {
        if (x == 0) {
            return 0;
        }

        if (n == 0) {
            return 1;
        }

        int result = 1;
        for (int i = 0; i < n; i++) {
            result *= x;
        }
        return result;
    }

    /**
     * n的平方的复杂度，总计为n*(n+1)+n
     * n*(n+1)为乘法的次数和
     * n为加法的次数和
     * 复杂度为f(n2)
     *
     * @param x
     * @param a
     *
     * @return
     */
    public static int mySuanfa2(int[] a, int x) {
        int sum = 0;
        for (int i = 0; i < a.length; i++) {
            sum += a[i] * pow1(x, i);
        }
        return sum;
    }

    /**
     * 该算法的复杂度为2n左右
     * 其中一个n为乘法的次数，另一个为加法的次数
     * 该算法中，乘法每做一次计算（每次乘以一个x），都会有效利用，所以效率很高
     * 复杂度为f(n)
     *
     * @param a
     * @param x
     *
     * @return
     */
    public static int polynomial(int[] a, int x) {
        // 相当于x的0次方
        int xi = 1;

        int sum = 0;

        for (int i = 1; i < a.length; i++) {
            xi *= x;
            sum += a[i] * xi;
        }

        return sum;
    }

    /**
     * 霍纳法
     * 计算多项式效率最高的方法。
     * a3x3+a2x2+a1x1+a0x0=((a3x+a2)x+a1)x+a0
     * 计算次数也是2n左右
     * 复杂度为f(n)
     *
     * @param a
     * @param x
     *
     * @return
     */
    public static int horner(int[] a, int x) {
        int n = a.length - 1;
        // 最后一位
        int sum = a[n];

        for (int i = 1; i < a.length; i++) {
            sum = sum * x + a[n - i];
        }

        return sum;
    }

    public static void main(String[] args) {
        int[] a = {0, 1, 2, 3, 4, 5, 6};
        int x = 10;
        System.out.println(mySuanfa1(a, x));
        // System.out.println(Integer.MAX_VALUE);
        System.out.println(mySuanfa2(a, x));
        System.out.println(polynomial(a, x));
        System.out.println(horner(a, x));

    }
}
