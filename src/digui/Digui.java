package digui;

/**
 * 该类写各种递归算法
 *
 * @author Administrator
 * @since 2016-10-11 20:36
 */

public class Digui {

    public static void main(String[] args) {
        //        System.out.println(factorial(0));
        //        System.out.println(factorial(1));
        //        System.out.println(factorial(2));
        //        System.out.println(factorial(3));

        //        System.out.println(power(2, 0));
        //        System.out.println(power(2, 1));
        //        System.out.println(power(2, 2));
        //        System.out.println(power(2, 3));
        //        System.out.println(power(2, 4));
        //
        //        System.out.println(mpower(2, 1, 4));
        //        System.out.println(mpower(2, 2, 4));
        //        System.out.println(mpower(2, 3, 4));
        //        System.out.println(mpower(2, 4, 4));
        //        System.out.println(gcd(3, 1));
        //        System.out.println(gcd(4, 2));
        //        System.out.println(gcd(5, 3));

        int[] arr = new int[]{0, 1, 2, 3, 4, 5, 6, 7, 8, 9};
        System.out.println(linerSearch(arr, 0, -1));
        System.out.println(linerSearch(arr, 0, 0));
        System.out.println(linerSearch(arr, 0, 6));
        System.out.println(linerSearch(arr, 0, 9));
        System.out.println(linerSearch(arr, 0, 10));
    }


    /**
     * 递归的求阶乘
     *
     * @param n 整数
     *
     * @return
     */
    public static int factorial(int n) {
        // 基础步骤 0! = 1
        if (n == 0) {
            return 1;
        }

        return n * factorial(n - 1);
    }

    /**
     * 求a的n次方
     *
     * @param a 底数
     * @param n 指数
     */
    public static int power(int a, int n) {
        if (n == 0) {
            return 1;
        }

        return a * power(a, n - 1);
    }

    /**
     * 递归模指数
     * 求b的n次方求余m
     * 使用的原理是ab mod m= ((a mod m)(b mod m)) mod m
     *
     * @param b 底数
     * @param n 指数
     * @param m 除数
     *
     * @return 结果
     */
    public static int mpower(int b, int n, int m) {
        // 任何数的0次方 mod m等于1
        if (n == 0) {
            return 1;
        }

        int i = mpower(b, n / 2, m);
        if (n % 2 == 0) {
            return (i * i) % m;
        } else {
            return (((i * i) % m) * (b % m)) % m;
        }
    }

    /**
     * 求最大公约数
     * 递归
     * 欧几里得算法
     * 原理：gcd(a,b)=gcd(b,a mod b) ，其中a>b
     *
     * @param a
     * @param b
     *
     * @return
     */
    public static int gcd(int a, int b) {
        if (b == 0) {
            return a;
        }

        return gcd(b, a % b);
    }

    /**
     * 线性搜索
     * 递归
     *
     * @param arr 代搜索的序列
     * @param i   记录从哪里开始（终点是arr的最后一个）
     * @param j   搜索目标
     *
     * @return 元素j在序列arr中的位置，如果找不到，返回0
     */
    public static int linerSearch(int[] arr, int i, int j) {
        if (i == arr.length) {
            return 0;
        } else if (arr[i] == j) {
            return i + 1;
        } else {
            return linerSearch(arr, i + 1, j);
        }
    }
}
