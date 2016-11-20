package datastucture.list;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.HashMap;

/**
 * 计算器
 *
 * @author Administrator
 * @since 2016-11-20 16:45
 */

public class Calculator {
    // 所有支持的运算符
    private static Collection<String> oprators = new ArrayList<String>(Arrays.asList("+", "-", "*", "/", "^"));
    // 运算符优先级
    private static HashMap<String, Integer> opMap = new HashMap<String, Integer>();

    static {
        opMap.put("+", 1);
        opMap.put("-", 1);
        opMap.put("*", 2);
        opMap.put("/", 2);
        opMap.put("^", 3);
    }

    /**
     * 通过给定的逆波兰式，计算结果，假设支持正负数，加减乘除
     *
     * @param reversePolish a+b表示为ab+
     *
     * @return 计算结果
     */
    private BigDecimal calculate(String[] reversePolish) {
        MyArrayStack<BigDecimal> stack = new MyArrayStack<BigDecimal>();
        //        BigDecimal result = new BigDecimal(0);
        for (int i = 0; i < reversePolish.length; i++) {
            // 如果是操作数，入栈
            if (!oprators.contains(reversePolish[i])) {
                BigDecimal data = new BigDecimal(reversePolish[i]);
                stack.push(data);
            } else {

                String op = reversePolish[i];

                // 如果是操作符，出栈进行计算
                // 先出栈的是右操作数
                BigDecimal rightData = stack.pop();
                // 后出栈的是做操作数
                BigDecimal leftData = stack.pop();

                BigDecimal tempResult = new BigDecimal(0);
                if ("+".equals(op)) {
                    tempResult = leftData.add(rightData);
                } else if ("-".equals(op)) {
                    tempResult = leftData.subtract(rightData);
                } else if ("*".equals(op)) {
                    tempResult = leftData.multiply(rightData);
                } else if ("/".equals(op)) {
                    tempResult = leftData.divide(rightData);
                } else if ("^".equals(op)) {
                    tempResult = leftData.pow(rightData.intValue());
                }

                stack.push(tempResult);
            }
        }

        return stack.peek();
    }

    /**
     * 中缀转后缀表达式
     *
     * @param polish 中缀表达式
     *
     * @return 后缀表达式
     */
    private String[] reverse(String[] polish) {
        MyArrayStack<String> opStack = new MyArrayStack<String>();
        ArrayList<String> arrayList = new ArrayList<String>(polish.length);
        for (int i = 0; i < polish.length; i++) {
            // 如果是操作数，直接返回
            if (!oprators.contains(polish[i]) && !"(".equals(polish[i]) && !")".equals(polish[i])) {
                arrayList.add(polish[i]);
            } else {
                // 空栈或者遇到开括号时，直接入栈
                if (opStack.empty() || "(".equals(polish[i])) {
                    opStack.push(polish[i]);
                } else if (")".equals(polish[i])) {
                    // 遇到闭括号时，所有符号依次出栈，直到遇到对应的开括号为止
                    while (!"(".equals(opStack.peek())) {
                        arrayList.add(opStack.pop());
                    }

                    // 对应的开括号出栈
                    opStack.pop();
                } else {
                    while (!opStack.empty() && !"(".equals(opStack.peek()) && opMap.get(polish[i]).intValue() <= opMap.get(opStack.peek()).intValue()) {
                        arrayList.add(opStack.pop());
                    }
                    opStack.push(polish[i]);
                }
            }
        }

        while (!opStack.empty()) {
            arrayList.add(opStack.pop());
        }

        // arrayList.ensureCapacity(arrayList.size());
        // Java里不允许进行数组的强制类型转换
        // return (String[]) arrayList.toArray();

        String[] result = new String[arrayList.size()];
        for (int i = 0; i < arrayList.size(); i++) {
            result[i] = arrayList.get(i);
        }
        return result;
    }

    /**
     * 将字符串分解为数学表达式，即分割出操作数和操作符
     *
     * @param str 表达式的字符串
     *
     * @return 中缀表达式
     */
    private String[] getPolish(String str) {
        // 去除空格等，此处只去除空格
        String string = str.replace(" ", "");

        ArrayList<String> polishList = new ArrayList<String>();

        char[] chars = string.toCharArray();

        StringBuilder sb = new StringBuilder();

        for (int i = 0; i < chars.length; i++) {
            if (chars[i] != '+' && chars[i] != '-' && chars[i] != '*' && chars[i] != '/' && chars[i] != '(' && chars[i] != ')' && chars[i] != '^') {
                sb.append(chars[i]);
            } else {
                if (sb != null && sb.length() > 0) {
                    polishList.add(sb.toString());
                    sb.delete(0, sb.length());
                }
                polishList.add((new StringBuilder().append(chars[i]).toString()));
            }
        }

        if (sb != null && sb.length() > 0) {
            polishList.add(sb.toString());
            sb.delete(0, sb.length());
        }

        String[] result = new String[polishList.size()];
        for (int i = 0; i < polishList.size(); i++) {
            result[i] = polishList.get(i);
        }

        return result;
    }

    public String caculate(String string) {
        return calculate(reverse(getPolish(string))).toString();
    }

//    public static void main(String[] args) {
//        Calculator calculator = new Calculator();
//        // 测试逆波兰式计算结果是否正确
//        //        String[] test = {"6", "5", "2", "3", "+", "8", "*", "+", "3", "+", "*"};
//        //        System.out.println(calculator.calculate(test));
//
//        // 测试中缀表达式转后缀是否正确
//        //        String[] polish = {"a", "+", "b", "*", "c", "+", "(", "d", "*", "e", "+", "f", ")", "*", "g"};
//        //        System.out.println(Arrays.deepToString(calculator.reverse(polish)));
//
//        // 测试是否可以正确的将字符串分割为中缀表达式（切割出操作数和操作符）
//        //        String string = "10 + 2222 * 3.1 + ( 4 * 5 + 6 ) * 7";
//        //        System.out.println(Arrays.deepToString(calculator.getPolish(string)));
//    }
}
