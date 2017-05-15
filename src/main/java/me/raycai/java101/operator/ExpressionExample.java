package me.raycai.java101.operator;

public class ExpressionExample {
    public int infixExpression(int a, int b, int c, int d, int e, int f) {

        int result = a + b * (c + d) * e + f;
        return result;
    }
}
