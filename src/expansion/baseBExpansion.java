package expansion;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;

/**
 * 以b为基数的进制转换
 *
 * @author Administrator
 * @since 2016-10-04 15:38
 */

public class baseBExpansion {
    /**
     * 求10进制数n的b进制表示
     *
     * @param n 原始10进制数
     * @param b 新的进制
     */
    public static void basebexpansion(int n, int b) {
        ArrayList<Integer> arrayList = new ArrayList<Integer>(10);

        int p = n;

        while (p != 0) {
            arrayList.add(p % b);
            p = p / b;
        }

        // -----以上为核心算法-----------
        Collections.reverse(arrayList);
        arrayList.trimToSize();
        for (int i = 0; i < arrayList.size(); i++) {
            System.out.print(arrayList.get(i));
        }

    }

    public static void main(String[] args) {
        basebexpansion(644, 2);
    }
}
