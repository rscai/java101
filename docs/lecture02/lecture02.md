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

```java
a + b * (c + d) * e + f
```

## Reference

1. [The Java Virtual Machine Instruction Set](https://docs.oracle.com/javase/specs/jvms/se8/html/jvms-6.html)
