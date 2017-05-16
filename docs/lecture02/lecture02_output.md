
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
![](figures/14a74c04e7ac28dc768fc91ed19b8973c1da0bac35d084aa26da9f2efc75df8d20cf27547de105e396e31a3466009e5e5d04952cde4fd168b167c44f1653d8c69c01196fbf117e9ecde60b489559b51aea7b770.png?0.6454551999813358)  

---

## Postfix Expression

![](figures/14a74c04e7ac28dc768fc91ed19b8973c1da0bac35d084aa26da9f2efc75df8d20cf27547de105e396e31a3466009e5e5d04952cde4fd168b167c44f1653d8c69c01196fbf117e9ecde60b489559b51aea7b771.png?0.1310494359997243)  
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
![](figures/14a74c04e7ac28dc768fc91ed19b8973c1da0bac35d084aa26da9f2efc75df8d20cf27547de105e396e31a3466009e5e5d04952cde4fd168b167c44f1653d8c69c01196fbf117e9ecde60b489559b51aea7b772.png?0.8882654748929761)  

```java
a + b *
```
![](figures/14a74c04e7ac28dc768fc91ed19b8973c1da0bac35d084aa26da9f2efc75df8d20cf27547de105e396e31a3466009e5e5d04952cde4fd168b167c44f1653d8c69c01196fbf117e9ecde60b489559b51aea7b773.png?0.7176836822302064)  

```java
a + b * ( c + d
```
![](figures/14a74c04e7ac28dc768fc91ed19b8973c1da0bac35d084aa26da9f2efc75df8d20cf27547de105e396e31a3466009e5e5d04952cde4fd168b167c44f1653d8c69c01196fbf117e9ecde60b489559b51aea7b774.png?0.03779387087272168)  

```java
a + b * ( c + d )
```
![](figures/14a74c04e7ac28dc768fc91ed19b8973c1da0bac35d084aa26da9f2efc75df8d20cf27547de105e396e31a3466009e5e5d04952cde4fd168b167c44f1653d8c69c01196fbf117e9ecde60b489559b51aea7b775.png?0.9752463490172947)  

```java
a + b * ( c + d ) *
```
![](figures/14a74c04e7ac28dc768fc91ed19b8973c1da0bac35d084aa26da9f2efc75df8d20cf27547de105e396e31a3466009e5e5d04952cde4fd168b167c44f1653d8c69c01196fbf117e9ecde60b489559b51aea7b776.png?0.9974973917425047)  

```java
a + b * ( c + d ) * e
```
![](figures/14a74c04e7ac28dc768fc91ed19b8973c1da0bac35d084aa26da9f2efc75df8d20cf27547de105e396e31a3466009e5e5d04952cde4fd168b167c44f1653d8c69c01196fbf117e9ecde60b489559b51aea7b777.png?0.7820406265670674)  

```java
a + b * ( c + d ) * e +
```
![](figures/14a74c04e7ac28dc768fc91ed19b8973c1da0bac35d084aa26da9f2efc75df8d20cf27547de105e396e31a3466009e5e5d04952cde4fd168b167c44f1653d8c69c01196fbf117e9ecde60b489559b51aea7b778.png?0.48379370379901876)  
![](figures/14a74c04e7ac28dc768fc91ed19b8973c1da0bac35d084aa26da9f2efc75df8d20cf27547de105e396e31a3466009e5e5d04952cde4fd168b167c44f1653d8c69c01196fbf117e9ecde60b489559b51aea7b779.png?0.46945193357149284)  

```java
a + b * ( c + d ) * e + f
```
![](figures/14a74c04e7ac28dc768fc91ed19b8973c1da0bac35d084aa26da9f2efc75df8d20cf27547de105e396e31a3466009e5e5d04952cde4fd168b167c44f1653d8c69c01196fbf117e9ecde60b489559b51aea7b7710.png?0.8406292003693516)  
![](figures/14a74c04e7ac28dc768fc91ed19b8973c1da0bac35d084aa26da9f2efc75df8d20cf27547de105e396e31a3466009e5e5d04952cde4fd168b167c44f1653d8c69c01196fbf117e9ecde60b489559b51aea7b7711.png?0.7884677862710447)  

---

## Postfix Expression Evaluation

```java
a b c d + * e * + f +
```

```java
a b c d
```
![](figures/14a74c04e7ac28dc768fc91ed19b8973c1da0bac35d084aa26da9f2efc75df8d20cf27547de105e396e31a3466009e5e5d04952cde4fd168b167c44f1653d8c69c01196fbf117e9ecde60b489559b51aea7b7712.png?0.933109488159821)  

```java
a b c d +
```
![](figures/14a74c04e7ac28dc768fc91ed19b8973c1da0bac35d084aa26da9f2efc75df8d20cf27547de105e396e31a3466009e5e5d04952cde4fd168b167c44f1653d8c69c01196fbf117e9ecde60b489559b51aea7b7713.png?0.1608017685184957)  

```java
a b c d + *
```
![](figures/14a74c04e7ac28dc768fc91ed19b8973c1da0bac35d084aa26da9f2efc75df8d20cf27547de105e396e31a3466009e5e5d04952cde4fd168b167c44f1653d8c69c01196fbf117e9ecde60b489559b51aea7b7714.png?0.3080054637439036)  

```java
a b c d + * e
```
![](figures/14a74c04e7ac28dc768fc91ed19b8973c1da0bac35d084aa26da9f2efc75df8d20cf27547de105e396e31a3466009e5e5d04952cde4fd168b167c44f1653d8c69c01196fbf117e9ecde60b489559b51aea7b7715.png?0.32099558320935295)  

```java
a b c d + * e *
```
![](figures/14a74c04e7ac28dc768fc91ed19b8973c1da0bac35d084aa26da9f2efc75df8d20cf27547de105e396e31a3466009e5e5d04952cde4fd168b167c44f1653d8c69c01196fbf117e9ecde60b489559b51aea7b7716.png?0.2269749826230978)  

```java
a b c d + * e * +
```
![](figures/14a74c04e7ac28dc768fc91ed19b8973c1da0bac35d084aa26da9f2efc75df8d20cf27547de105e396e31a3466009e5e5d04952cde4fd168b167c44f1653d8c69c01196fbf117e9ecde60b489559b51aea7b7717.png?0.024085268507429314)  

```java
a b c d + * e * + f
```
![](figures/14a74c04e7ac28dc768fc91ed19b8973c1da0bac35d084aa26da9f2efc75df8d20cf27547de105e396e31a3466009e5e5d04952cde4fd168b167c44f1653d8c69c01196fbf117e9ecde60b489559b51aea7b7718.png?0.09507844550375877)  

```java
a b c d + * e * + f +
```
![](figures/14a74c04e7ac28dc768fc91ed19b8973c1da0bac35d084aa26da9f2efc75df8d20cf27547de105e396e31a3466009e5e5d04952cde4fd168b167c44f1653d8c69c01196fbf117e9ecde60b489559b51aea7b7719.png?0.13538799413041236)  

---

## Expression in Binary Tree Format

![](figures/14a74c04e7ac28dc768fc91ed19b8973c1da0bac35d084aa26da9f2efc75df8d20cf27547de105e396e31a3466009e5e5d04952cde4fd168b167c44f1653d8c69c01196fbf117e9ecde60b489559b51aea7b7720.png?0.0527915936394201)  

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

1. [The Java Virtual Machine Instruction Set](https://docs.oracle.com/javase/specs/jvms/se8/html/jvms-6.html )
2. [Evaluation of infix expressions](http://csis.pace.edu/~murthy/ProgrammingProblems/16_Evaluation_of_infix_expressions )