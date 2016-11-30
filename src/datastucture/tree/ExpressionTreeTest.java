package datastucture.tree;

import org.junit.Test;

import static org.junit.Assert.*;

public class ExpressionTreeTest {

    @Test
    public void testGetExpressionTree() throws Exception {
        String oprator = "a b + c d e + * *";
        ExpressionTree eTree = new ExpressionTree();
        BinaryNode node = eTree.getExpressionTree(oprator);
    }
}