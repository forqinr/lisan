package expansion;

/**
 * 求最大公约数
 * ① 列出所有公约数，求最大
 * ② 欧几里得算法
 *
 * @author Administrator
 * @since 2016-10-04 23:54
 */

public class GCD {
    /**
     * 欧几里得算法求最大公约数
     * 这里假定a > b
     * <p/>
     * 此算法的原理是整除的性质：
     * 假定d|a,d|b,则d|am+bn
     * <p/>
     * 辗转求余，最后一个不为0的余数即为最大公约数
     *
     * @param a
     * @param b
     *
     * @return
     */
    public static int gcd(int a, int b) {
        int x = a;
        int y = b;
        while (y != 0) {
            int r = x % y;
            x = y;
            y = r;
        }
        return x;
    }

    public static void main(String[] args) {
        System.out.println(gcd(662, 414));
    }
}
