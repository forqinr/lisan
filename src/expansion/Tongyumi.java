package expansion;

/**
 * 同余幂
 * 求b的n次方除以m的余数，其中b,n,m都是大数
 * 如果先求b的n次方效率太低且不现实，则利用同余的某些性质，求解之
 * 利用的性质有：
 * ① 指数n可以分解为2进制表示
 * ② b的0次方为1，所以只需要将分解完的的数计算数位为1的值的积即可
 * ③ 同余定理：ab mod m ≡ ((a mod m)(b mod m)) mod m
 *
 * @author Administrator
 * @since 2016-10-04 23:09
 */

public class Tongyumi {
    /**
     * 计算大数的同余
     * <p/>
     * 该方法用于密码学较多
     *
     * @param b 底数
     * @param n 指数，用int数组表示已经进行二进制分解后的数
     * @param m 除数
     *
     * @return 余数
     */
    public static int tongyu(int b, int[] n, int m) {
        // 需要返回的值，因为一会肯定需要和其他的余数进行同余运算（乘法），故直接赋值为1
        int x = 1;

        // 因为在下面的代码中，只要n[i]为1，就第一时间获得余数，所以在进行到n的i次方之前，就优先计算出来
        int power = b % m;

        for (int i = 0; i < n.length; i++) {
            if (n[i] == 1) {
                x = (x * power) % m;
            }

            power = (power * power) % m;
        }

        return x;
    }

    /**
     * 就拿书上的习题作为练习，求（3）644 mod 645
     *
     * @param args
     */
    public static void main(String[] args) {
        int b = 3;
        int m = 645;
        // n的二进制表示其实是数组中的顺序反过来，不过数组中后面表示高位，所以这么表示
        int[] n = {0, 0, 1, 0, 0, 0, 0, 1, 0, 1};

        System.out.println(tongyu(b, n, m));
    }

}
