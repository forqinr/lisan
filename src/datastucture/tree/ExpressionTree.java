package datastucture.tree;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Stack;

/**
 * 表达式数
 * <p/>
 * 将后缀表达式转换成表达式树
 *
 * @author Administrator
 * @since 2016-11-29 20:52
 */

public class ExpressionTree {
    private static List<String> oprators = new ArrayList<String>();

    static {
        oprators.add("+");
        oprators.add("-");
        oprators.add("*");
        oprators.add("/");
        oprators.add("^");
    }

    /**
     * 把一个后缀表达式转换成表达式树
     *
     * @param expression 后缀表达式（用空格分隔）
     *
     * @return 表达式树
     */
    public BinaryNode getExpressionTree(String expression) {

        String[] array = expression.split(" ");

        Stack<BinaryNode> stack = new Stack<BinaryNode>();

        for (String s : array) {
            if (!oprators.contains(s)) {
                BinaryNode dataNode = new BinaryNode();
                dataNode.setElement(s);
                stack.push(dataNode);
            } else {
                BinaryNode opratorNode = new BinaryNode();
                opratorNode.setElement(s);
                opratorNode.setRight(stack.pop());
                opratorNode.setLeft(stack.pop());
                stack.push(opratorNode);
            }
        }

        return stack.peek();

    }

}
