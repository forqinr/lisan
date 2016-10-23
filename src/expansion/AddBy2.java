package expansion;

import java.util.Arrays;

/**
 * 2进制相加
 *
 * @author Administrator
 * @since 2016-10-04 15:59
 */

public class AddBy2 {
    /**
     * 用a和b存储0和1的串来模拟一个二进制数
     * 假定a和b的位数相同，省去一些非核心代码（验证）
     *
     * @param a
     * @param b
     *
     * @return 二进制表示的a和b的和
     */
    public static int[] add(int[] a, int[] b) {
        int[] result = new int[a.length + 1];
        Arrays.fill(result, 0);

        // 代表要进上去的数
        int c = 0;

        for (int i = 0; i < a.length; i++) {

            // 计算进位值：最终和除以计数进制
            int d = (a[i] + b[i] + c) / 2;

            // 当前位的计算结果等于当前对应位和上一次计算进位的和求进位的余数
            int iWei = a[i] + b[i] + c - 2 * d;
            result[i] = iWei;

            c = d;
        }
        result[result.length - 1] = c;

        return result;
    }

    public static void main(String[] args) {
        int[] a = {1, 0, 1};
        int[] b = {1, 1, 1};

        System.out.print("a:");
        for (int i = a.length - 1; i >= 0; i--) {
            System.out.print(a[i]);
        }
        System.out.println();


        System.out.print("b:");
        for (int i = b.length - 1; i >= 0; i--) {
            System.out.print(b[i]);
        }
        System.out.println();

        int[] result = add(a, b);
        System.out.print("result:");
        for (int i = result.length - 1; i >= 0; i--) {
            System.out.print(result[i]);
        }
    }
}
