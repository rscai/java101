
## The Road to Java

### Lecture 10: Holding Your Objects

> by **Ray Cai**

> Mar 26th, 2017

---

## Agenda

* Architecture of Java Collections Framework
* List
* Map
* Set

---

## Architecture of Java Collections Framework

---

## What is a Collections Framework

A collections framework is a unified architecture for representing and manipulating collections. All collections frameworks contain the following:

* **Interfaces:** These are abstract data types that represent collections. Interfaces allow collections to be manipulated independently of the details of their representation. In object-oriented languages, interfaces generally form a hierarchy.
* **Implementations:** These are the concrete implementations of the collection interfaces. In essence, they are reusable data structures.
* **Algorithms:** These are the methods that perform useful computations, such as searching and sorting, on objects that implement collection interfaces. The algorithms are said to be polymorphic: that is, the same method can be used on many different implementations of the appropriate collection interface. In essence, algorithms are reusable functionality.

---

## Interfaces

![](figures/14a74c04e7ac28dc768fc91ed19b8973c1da0bac35d084aa26da9f2efc75df8d20cf27547de105e396e31a3466009e5e5d04952cde4fd168b167c44f1652dac69c01196fbf117e9fcfe60b489559b51aea7b775.png?0.8553970031956899)  

---

## List

![](figures/14a74c04e7ac28dc768fc91ed19b8973c1da0bac35d084aa26da9f2efc75df8d20cf27547de105e396e31a3466009e5e5d04952cde4fd168b167c44f1652dac69c01196fbf117e9fcfe60b489559b51aea7b776.png?0.9017457769782558)  

---

## Data Structure of ArrayList

![ArrayList Data Structure](figures/ArrayList.datastructure.png )

---

## Complexity of ArrayList

| Method                      | Average               | Worst Case        |
|-----------------------------|------------------------|------------------|
| Space                       | **?**                  |   **?**          |
| get(index: int)             | **?**                  |   **?**          |
| add(index: int, element: E) | **?**                  |   **?**          |
| remove(index: int)          | **?**                  |   **?**          |

---

## get(index: int)

![ArrayList get(index: int)](figures/ArrayList.get.png )

```java
public E get(int index) {
    rangeCheck(index);

    return elementData(index);
}
```

---

## add(index: int, element: E)

```java
public void add(int index, E element) {
    rangeCheckForAdd(index);

    ensureCapacityInternal(size + 1);  // Increments modCount!!
    System.arraycopy(elementData, index, elementData, index + 1,
                     size - index);
    elementData[index] = element;
    size++;
}
```

---

## remove(index)

```java
public E remove(int index) {
    rangeCheck(index);

    modCount++;
    E oldValue = elementData(index);

    int numMoved = size - index - 1;
    if (numMoved > 0)
        System.arraycopy(elementData, index+1, elementData, index,
                         numMoved);
    elementData[--size] = null; // clear to let GC do its work

    return oldValue;
}
```

## Complexity of ArrayList

| Method                      | Average             | Worst Case |
|-----------------------------|---------------------|---------------|
| Space                       | <img src="http://api.gmath.guru/cgi-bin/gmath?%5Cmathcal%7BO%7D%28n%29"/>    | <img src="http://api.gmath.guru/cgi-bin/gmath?%5Cmathcal%7BO%7D%28n%29"/>|
| get(index: int)             | <img src="http://api.gmath.guru/cgi-bin/gmath?%5Cmathcal%7BO%7D%281%29"/>    | <img src="http://api.gmath.guru/cgi-bin/gmath?%5Cmathcal%7BO%7D%281%29"/> |
| add(index: int, element: E) | <img src="http://api.gmath.guru/cgi-bin/gmath?%5Cmathcal%7BO%7D%28n%29"/>    | <img src="http://api.gmath.guru/cgi-bin/gmath?%5Cmathcal%7BO%7D%28n%29"/> |
| remove(index: int)          | <img src="http://api.gmath.guru/cgi-bin/gmath?%5Cmathcal%7BO%7D%28n%29"/>    | <img src="http://api.gmath.guru/cgi-bin/gmath?%5Cmathcal%7BO%7D%28n%29"/> |

---

## Data Structure of LinkedList

![](figures/14a74c04e7ac28dc768fc91ed19b8973c1da0bac35d084aa26da9f2efc75df8d20cf27547de105e396e31a3466009e5e5d04952cde4fd168b167c44f1652dac69c01196fbf117e9fcfe60b489559b51aea7b770.png?0.5795354278561897)  

---

## Complexity of LinkedList

| Method                      | Average                | Worst Case |
|-----------------------------|------------------------|-------------------|
| Space                       | **?**                  |   **?**          |
| get(index: int)             | **?**                  |   **?**          |
| add(index: int, element: E) | **?**                  |   **?**          |
| remove(index: int)          | **?**                  |   **?**          |

---

## get(index: int)

```java
public E get(int index) {
    checkElementIndex(index);
    return node(index).item;
}
Node<E> node(int index) {
    // assert isElementIndex(index);

    if (index < (size >> 1)) {
        Node<E> x = first;
        for (int i = 0; i < index; i++)
            x = x.next;
        return x;
    } else {
        Node<E> x = last;
        for (int i = size - 1; i > index; i--)
            x = x.prev;
        return x;
    }
}
```

---

## add(index: int, element: E)

```java
public void add(int index, E element) {
    checkPositionIndex(index);

    if (index == size)
        linkLast(element);
    else
        linkBefore(element, node(index));
}
void linkBefore(E e, Node<E> succ) {
    // assert succ != null;
    final Node<E> pred = succ.prev;
    final Node<E> newNode = new Node<>(pred, e, succ);
    succ.prev = newNode;
    if (pred == null)
        first = newNode;
    else
        pred.next = newNode;
    size++;
    modCount++;
}
```

## remove(index: int)

```java
public E remove(int index) {
    checkElementIndex(index);
    return unlink(node(index));
}
E unlink(Node<E> x) {
    // assert x != null;
    final E element = x.item;
    final Node<E> next = x.next;
    final Node<E> prev = x.prev;

    if (prev == null) {
        first = next;
    } else {
        prev.next = next;
        x.prev = null;
    }

    if (next == null) {
        last = prev;
    } else {
        next.prev = prev;
        x.next = null;
    }

    x.item = null;
    size--;
    modCount++;
    return element;
}
```

## Complexity of LinkedList

| Method                      | Average          | Worst Case |
|-----------------------------|------------------|-----------------|
| Space                       | <img src="http://api.gmath.guru/cgi-bin/gmath?%5Cmathcal%7BO%7D%28n%29"/> | <img src="http://api.gmath.guru/cgi-bin/gmath?%5Cmathcal%7BO%7D%28n%29"/>|
| get(index: int)             | <img src="http://api.gmath.guru/cgi-bin/gmath?%5Cmathcal%7BO%7D%28n%29"/> | <img src="http://api.gmath.guru/cgi-bin/gmath?%5Cmathcal%7BO%7D%28n%29"/>|
| add(index: int, element: E) | <img src="http://api.gmath.guru/cgi-bin/gmath?%5Cmathcal%7BO%7D%28n%29"/> | <img src="http://api.gmath.guru/cgi-bin/gmath?%5Cmathcal%7BO%7D%28n%29"/>|
| remove(index: int)          | <img src="http://api.gmath.guru/cgi-bin/gmath?%5Cmathcal%7BO%7D%28n%29"/> | <img src="http://api.gmath.guru/cgi-bin/gmath?%5Cmathcal%7BO%7D%28n%29"/>|

---

## Map

![](figures/14a74c04e7ac28dc768fc91ed19b8973c1da0bac35d084aa26da9f2efc75df8d20cf27547de105e396e31a3466009e5e5d04952cde4fd168b167c44f1652dac69c01196fbf117e9fcfe60b489559b51aea7b777.png?0.8616619903862615)  

---

## Data Structure of HashMap

![](figures/14a74c04e7ac28dc768fc91ed19b8973c1da0bac35d084aa26da9f2efc75df8d20cf27547de105e396e31a3466009e5e5d04952cde4fd168b167c44f1652dac69c01196fbf117e9fcfe60b489559b51aea7b771.png?0.9909562700829992)  

## Complexity of HashMap

| Method                      | Average         | Worst Case       |
|-----------------------------|-----------------|------------------|
| Space                       | **?**          |   **?**          |
| put(key: K, value: V)       | **?**          |   **?**          |
| get(key: K)                 | **?**           |   **?**          |

---

## put(key: K, value: V)

```java
final V putVal(int hash, K key, V value, boolean onlyIfAbsent,
               boolean evict) {
    Node<K,V>[] tab; Node<K,V> p; int n, i;
    if ((tab = table) == null || (n = tab.length) == 0)
        n = (tab = resize()).length;
    if ((p = tab[i = (n - 1) & hash]) == null)
        tab[i] = newNode(hash, key, value, null);
    else {
        Node<K,V> e; K k;
        if (p.hash == hash &&
            ((k = p.key) == key || (key != null && key.equals(k))))
            e = p;
        else if (p instanceof TreeNode)
            e = ((TreeNode<K,V>)p).putTreeVal(this, tab, hash, key, value);
        else {
            for (int binCount = 0; ; ++binCount) {
                if ((e = p.next) == null) {
                    p.next = newNode(hash, key, value, null);
                    if (binCount >= TREEIFY_THRESHOLD - 1) // -1 for 1st
                        treeifyBin(tab, hash);
                    break;
                }
                if (e.hash == hash &&
                    ((k = e.key) == key || (key != null && key.equals(k))))
                    break;
                p = e;
            }
        }
        if (e != null) { // existing mapping for key
            V oldValue = e.value;
            if (!onlyIfAbsent || oldValue == null)
                e.value = value;
            afterNodeAccess(e);
            return oldValue;
        }
    }
    ++modCount;
    if (++size > threshold)
        resize();
    afterNodeInsertion(evict);
    return null;
}
```

## get(key: K)

```java
final Node<K,V> getNode(int hash, Object key) {
    Node<K,V>[] tab; Node<K,V> first, e; int n; K k;
    if ((tab = table) != null && (n = tab.length) > 0 &&
        (first = tab[(n - 1) & hash]) != null) {
        if (first.hash == hash && // always check first node
            ((k = first.key) == key || (key != null && key.equals(k))))
            return first;
        if ((e = first.next) != null) {
            if (first instanceof TreeNode)
                return ((TreeNode<K,V>)first).getTreeNode(hash, key);
            do {
                if (e.hash == hash &&
                    ((k = e.key) == key || (key != null && key.equals(k))))
                    return e;
            } while ((e = e.next) != null);
        }
    }
    return null;
}
```

---

## Complexity of HashMap

| Method                      | Average         | Worst Case       |
|-----------------------------|-----------------|------------------|
| Space                       | <img src="http://api.gmath.guru/cgi-bin/gmath?%5Cmathcal%7BO%7D%28n%29"/>| <img src="http://api.gmath.guru/cgi-bin/gmath?%5Cmathcal%7BO%7D%28n%29"/> |
| put(key: K, value: V)       | <img src="http://api.gmath.guru/cgi-bin/gmath?%5Cmathcal%7BO%7D%281%29"/>| <img src="http://api.gmath.guru/cgi-bin/gmath?%5Cmathcal%7BO%7D%28n%29"/> |
| get(key: K)                 | <img src="http://api.gmath.guru/cgi-bin/gmath?%5Cmathcal%7BO%7D%281%29"/>| <img src="http://api.gmath.guru/cgi-bin/gmath?%5Cmathcal%7BO%7D%28n%29"/> |

---

## Binary Search Tree

![](figures/14a74c04e7ac28dc768fc91ed19b8973c1da0bac35d084aa26da9f2efc75df8d20cf27547de105e396e31a3466009e5e5d04952cde4fd168b167c44f1652dac69c01196fbf117e9fcfe60b489559b51aea7b772.png?0.2568659619644871)  

---

## Complexity of Binary Search Tree

| Algorithm | Average                | Worst Case|
|-----------|------------------------|-----------|
| Space     |<img src="http://api.gmath.guru/cgi-bin/gmath?%5Cmathcal%7BO%7D%28n%29"/>        |<img src="http://api.gmath.guru/cgi-bin/gmath?%5Cmathcal%7BO%7D%28n%29"/> |
| Search    |<img src="http://api.gmath.guru/cgi-bin/gmath?%5Cmathcal%7BO%7D%28%5Clog%7B%7Dn%29"/> |<img src="http://api.gmath.guru/cgi-bin/gmath?%5Cmathcal%7BO%7D%28n%29"/> |
| Insert    |<img src="http://api.gmath.guru/cgi-bin/gmath?%5Cmathcal%7BO%7D%28%5Clog%7B%7Dn%29"/> |<img src="http://api.gmath.guru/cgi-bin/gmath?%5Cmathcal%7BO%7D%28n%29"/> |
| Delete    |<img src="http://api.gmath.guru/cgi-bin/gmath?%5Cmathcal%7BO%7D%28%5Clog%7B%7Dn%29"/> |<img src="http://api.gmath.guru/cgi-bin/gmath?%5Cmathcal%7BO%7D%28n%29"/> |

---

## Full Balanced Binary Search Tree

![](figures/14a74c04e7ac28dc768fc91ed19b8973c1da0bac35d084aa26da9f2efc75df8d20cf27547de105e396e31a3466009e5e5d04952cde4fd168b167c44f1652dac69c01196fbf117e9fcfe60b489559b51aea7b773.png?0.719500474869559)  

---

## Tree Rotation

![](figures/Tree_rotation.png )

---

## Complexity of Full Balanced Binary Search Tree

| Algorithm | Average                | Worst Case|
|-----------|------------------------|-----------|
| Space     |<img src="http://api.gmath.guru/cgi-bin/gmath?%5Cmathcal%7BO%7D%28n%29"/>        |<img src="http://api.gmath.guru/cgi-bin/gmath?%5Cmathcal%7BO%7D%28n%29"/> |
| Search    |<img src="http://api.gmath.guru/cgi-bin/gmath?%5Cmathcal%7BO%7D%28%5Clog%7B%7Dn%29"/> |<img src="http://api.gmath.guru/cgi-bin/gmath?%5Cmathcal%7BO%7D%28%5Clog%7B%7Dn%29"/> |
| Insert    |<img src="http://api.gmath.guru/cgi-bin/gmath?%5Cmathcal%7BO%7D%28%5Clog%7B%7Dn%29"/> |<img src="http://api.gmath.guru/cgi-bin/gmath?%5Cmathcal%7BO%7D%28%5Clog%7B%7Dn%29"/> |
| Delete    |<img src="http://api.gmath.guru/cgi-bin/gmath?%5Cmathcal%7BO%7D%28%5Clog%7B%7Dn%29"/> |<img src="http://api.gmath.guru/cgi-bin/gmath?%5Cmathcal%7BO%7D%28%5Clog%7B%7Dn%29"/> |

---

## Red Black Tree

![](figures/14a74c04e7ac28dc768fc91ed19b8973c1da0bac35d084aa26da9f2efc75df8d20cf27547de105e396e31a3466009e5e5d04952cde4fd168b167c44f1652dac69c01196fbf117e9fcfe60b489559b51aea7b774.png?0.4827192916863008)  

---

## Red Black Tree - Properties

1. Each node is either red or black.
2. The root is black. This rule is sometimes omitted. Since the root can always be changed from red to black, but not necessarily vice versa, this rule has little effect on analysis.
3. All leaves (NIL) are black.
4. If a node is red, then both its children are black.
5. Every path from a given node to any of its descendant NIL nodes contains the same number of black nodes. Some definitions: the number of black nodes from the root to a node is the node's black depth; the uniform number of black nodes in all paths from root to the leaves is called the black-height of the red–black tree.

---

## Complexity of Red Black Tree

| Algorithm | Average                | Worst Case|
|-----------|------------------------|-----------|
| Space     |<img src="http://api.gmath.guru/cgi-bin/gmath?%5Cmathcal%7BO%7D%28n%29"/>        |<img src="http://api.gmath.guru/cgi-bin/gmath?%5Cmathcal%7BO%7D%28n%29"/> |
| Search    |<img src="http://api.gmath.guru/cgi-bin/gmath?%5Cmathcal%7BO%7D%28%5Clog%7B%7Dn%29"/> |<img src="http://api.gmath.guru/cgi-bin/gmath?%5Cmathcal%7BO%7D%28%5Clog%7B%7Dn%29"/> |
| Insert    |<img src="http://api.gmath.guru/cgi-bin/gmath?%5Cmathcal%7BO%7D%28%5Clog%7B%7Dn%29"/> |<img src="http://api.gmath.guru/cgi-bin/gmath?%5Cmathcal%7BO%7D%28%5Clog%7B%7Dn%29"/> |
| Delete    |<img src="http://api.gmath.guru/cgi-bin/gmath?%5Cmathcal%7BO%7D%28%5Clog%7B%7Dn%29"/> |<img src="http://api.gmath.guru/cgi-bin/gmath?%5Cmathcal%7BO%7D%28%5Clog%7B%7Dn%29"/> |

-----

## Set

![](figures/14a74c04e7ac28dc768fc91ed19b8973c1da0bac35d084aa26da9f2efc75df8d20cf27547de105e396e31a3466009e5e5d04952cde4fd168b167c44f1652dac69c01196fbf117e9fcfe60b489559b51aea7b778.png?0.8341873707753455)  

## Reference

* [Lesson: Introduction to Collections](http://docs.oracle.com/javase/tutorial/collections/intro/index.html )
* [Red-Black Tree](https://en.wikipedia.org/wiki/Red%E2%80%93black_tree )
* [Binary Search Tree](https://en.wikipedia.org/wiki/Binary_search_tree )
* [Tree Rotation](https://en.wikipedia.org/wiki/Tree_rotation )
* [Self-Balancing Binary Search Tree](https://en.wikipedia.org/wiki/Self-balancing_binary_search_tree )