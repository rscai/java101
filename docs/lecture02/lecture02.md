---
title: "The Road to Java: Operators"
author: Ray Cai
date: May 15, 2017
output: pdf_document
markdown:
  image_dir: figures
  path: lecture02_output.md
  absolute_image_path: false
---

## The Road to Java

### Operators

> by **Ray Cai**
> May 15, 2017

---

## Content

* Infix, Prefix and Postfix Expression
* Prefix and Postfix Expression Evaluation
* Infix, Prefix and Postfix Expression Conversion

---

## Infix Expression

```java
public class ExpressionExample {
    public int infixExpression(int a, int b, int c, int d, int e, int f) {

        int result = a + b * (c + d) * e + f;
        return result;
    }
}
```

---

## JavaByte

```java
// access flags 0x1
public infixExpression(IIIIII)I
 L0
  LINENUMBER 6 L0
  ILOAD 1
  ILOAD 2
  ILOAD 3
  ILOAD 4
  IADD
  IMUL
  ILOAD 5
  IMUL
  IADD
  ILOAD 6
  IADD
  ISTORE 7
 L1
  LINENUMBER 7 L1
  ILOAD 7
  IRETURN
 L2
  LOCALVARIABLE this Lme/raycai/java101/operator/ExpressionExample; L0 L2 0
  LOCALVARIABLE a I L0 L2 1
  LOCALVARIABLE b I L0 L2 2
  LOCALVARIABLE c I L0 L2 3
  LOCALVARIABLE d I L0 L2 4
  LOCALVARIABLE e I L0 L2 5
  LOCALVARIABLE f I L0 L2 6
  LOCALVARIABLE result I L1 L2 7
  MAXSTACK = 4
  MAXLOCALS = 8
```

---

## Postfix Expression

```
a b c d + * e * + f +

( ( a ( ( b ( c d + ) * ) e * ) + ) f + )
```
```@viz
engine:dot
graph stack {

  stack [shape=record, label="{+|f|+|*|e|*|+|d|c|b|a}"]

}
```

---

## Postfix Expression

```@viz
engine:dot
digraph stack {
  splines=false;
  add1 [shape=circle,label="+"]
  mul1 [shape=circle,label="*"]
  mul2 [shape=circle,label="*"]
  add2 [shape=circle,label="+"]
  add3 [shape=circle,label="+"]

  stack [shape=record, width=6, label="<f>f |<e>e|<d>d|<c>c|<b>b|<a>a"]

  add1 -> stack:c
  add1 -> stack:d

  mul1 -> add1
  mul1 -> stack:b

  mul2 -> mul1
  mul2 -> stack:e

  add2 -> mul2
  add2 -> stack:a

  add3 -> add2
  add3 -> stack:f
}
```
---

## Prefix Expression

**Reverse of Postfix expression**

```
+ f + * e * + d c b a

( + f ( + ( * e ( * ( + d c ) b ) ) a ) )
```

## Infix Expression Evaluation

**We will use two stacks:**[2]
1. Operand stack: to keep values (numbers)  and
2. Operator stack: to keep operators (+, -, *, . and ^).

**Algorithm:**

Until the end of the expression is reached, get one character and perform only one of the steps (a) through (f):
(a) If the character is an operand, push it onto the operand stack.
(b) If the character is an operator, and the operator stack is empty then push it onto the operator stack.
(c) If the character is an operator and the operator stack is not empty, and the character's precedence is greater than the precedence of the stack top of operator stack, then push the character onto the operator stack.
(d) If the character is "(", then push it onto operator stack.
(e) If the character is ")", then "process" as explained above until the corresponding "(" is encountered in operator stack.  At this stage POP the operator stack and ignore "(."
(f) If cases (a), (b), (c), (d) and (e) do not apply, then process as explained above.

 When there are no more input characters, keep processing until the operator stack becomes empty.  The values left in the operand stack is the final result of the expression.

---

## Infix Expression Evaluation

```java
a + b * ( c + d ) * e + f
```

```java
a +
```
```@viz
engine:dot
graph infixEvaluation {
  operand [shape="record",label="{a}"]
  operator [shape="record", label="{+}"]
}
```

```java
a + b *
```
```@viz
engine:dot
graph infixEvaluation {
  operand [shape="record",label="{b|a}"]
  operator [shape="record", label="{*|+}"]
}
```

```java
a + b * ( c + d
```
```@viz
engine:dot
graph infixEvaluation {
  operand [shape="record",label="{d|c|b|a}"]
  operator [shape="record", label="{+|(|*|+}"]
}
```

```java
a + b * ( c + d )
```
```@viz
engine:dot
graph infixEvaluation {
  operand [shape="record",label="{c+d|b|a}"]
  operator [shape="record", label="{*|+}"]
}
```

```java
a + b * ( c + d ) *
```
```@viz
engine:dot
graph infixEvaluation {
  operand [shape="record",label="{b*(c+d)|a}"]
  operator [shape="record", label="{*|+}"]
}
```

```java
a + b * ( c + d ) * e
```
```@viz
engine:dot
graph infixEvaluation {
  operand [shape="record",label="{e|b*(c+d)|a}"]
  operator [shape="record", label="{*|+}"]
}
```

```java
a + b * ( c + d ) * e +
```
```@viz
engine:dot
graph infixEvaluation {
  operand [shape="record",label="{b*(c+d)*e|a}"]
  operator [shape="record", label="{+}"]
}
```
```@viz
engine:dot
graph infixEvaluation {
  operand [shape="record",label="{a+b*(c+d)*e}"]
  operator [shape="record", label="{}"]
}
```

```java
a + b * ( c + d ) * e + f
```
```@viz
engine:dot
graph infixEvaluation {
  operand [shape="record",label="{f|a+b*(c+d)*e}"]
  operator [shape="record", label="{+}"]
}
```
```@viz
engine:dot
graph infixEvaluation {
  operand [shape="record",label="{a+b*(c+d)*e+f}"]
  operator [shape="record", label="{}"]
}
```

---

## Postfix Expression Evaluation

```java
a b c d + * e * + f +
```

```java
a b c d
```
```@viz
engine:dot
graph postfixEvaluation {
  stack [shape=record, label="{d|c|b|a}"]
}
```

```java
a b c d +
```
```@viz
engine:dot
graph postfixEvaluation {
  stack [shape=record, label="{c+d|b|a}"]
}
```

```java
a b c d + *
```
```@viz
engine:dot
graph postfixEvaluation {
  stack [shape=record, label="{b*(c+d)|a}"]
}
```

```java
a b c d + * e
```
```@viz
engine:dot
graph postfixEvaluation {
  stack [shape=record, label="{e|b*(c+d)|a}"]
}
```

```java
a b c d + * e *
```
```@viz
engine:dot
graph postfixEvaluation {
  stack [shape=record, label="{b*(c+d)*e|a}"]
}
```

```java
a b c d + * e * +
```
```@viz
engine:dot
graph postfixEvaluation {
  stack [shape=record, label="{a+b*(c+d)*e}"]
}
```

```java
a b c d + * e * + f
```
```@viz
engine:dot
graph postfixEvaluation {
  stack [shape=record, label="{f|a+b*(c+d)*e}"]
}
```

```java
a b c d + * e * + f +
```
```@viz
engine:dot
graph postfixEvaluation {
  stack [shape=record, label="{a+b*(c+d)*e+f}"]
}
```

---

## Expression in Binary Tree Format

```@viz
engine:dot
graph tree {
  add1 [label="+"]
  add2 [label="+"]

  add1 -- add2
  add1 -- f
  add2 -- a

  mul1 [label="*"]
  mul2 [label="*"]

  add2 -- mul1
  mul1 -- e
  mul1 -- mul2
  mul2 -- b

  add3 [label="+"]
  mul2 -- add3
  add3 -- c
  add3 -- d
}
```

**Infix:**
1. start at certain leaf
2. find deepest leaf
3. do Breadth-First traversal
4. input sequence is different than traversal sequence, need cache a part of tree

**Postfix:**
1. start at deepest leaf
2. do Breadth-First traversal
4. input sequence is same with traversal sequence, need not cache any part of tree

---

## Conversion of Infix and Postfix

TODO

---

## Reference

1. [The Java Virtual Machine Instruction Set](https://docs.oracle.com/javase/specs/jvms/se8/html/jvms-6.html)
2. [Evaluation of infix expressions](http://csis.pace.edu/~murthy/ProgrammingProblems/16_Evaluation_of_infix_expressions)
