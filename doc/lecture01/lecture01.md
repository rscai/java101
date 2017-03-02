class: center, middle

## The Road to Java

### Lecture 01: Class and Object

> by **Ray Cai**

> Feb 25th, 2017

---


## Agenda

* Code Organization
* Code Reuse
* Four Principles 

---


## Code Organization

Procedure Oriented approach:
> Record and Procedure

```
struct Bird {
    char* wow;
}
void birdYell(Bird* bird){
    printf("yell %s",bird->wow);
}
void birdFly(Bird* bird){
    printf("I'm flying");
}

Bird* bird = allocate(sizeof(Bird));
// initialize ...
birdYell(bird);
birdFly(bird);
```

---

## Code Organization

Object Oriented approach:
> Field and Method of Class

```
class Bird {
    protected String wow;    
    public void yell(){
        System.out.println(String.format("yell %s",wow));
    }
    public void fly(){
        System.out.println("I'm flying");
    }
}
Bird bird = new Bird();
// initialize ...
bird.yell();
bird.fly();
```

---

## Code Reuse

```c
struct Chicken {
    char* wow;
    float weight;
    float unitPrice;
}
void chickenYell(Chicken* chicken){
    printf("yell %s",chicken->wow);
}

void chickenFly(Chicken* chicken){
    printf("I'm inable to fly")
}

float chickenPricing(Chicken* chicken){
    return chicken->weight * chicken->unitPrice;
}

Bird* chicken = allocate(sizeof(Bird));
// initialize ...
chickenYell(chicken);
chickenFly(chicken);
chickenPricing(chicken);
```

---

## Code Reuse

```java
class Chicken extends Bird {
    protected float weight;
    protected float unitPrice;
    
    @Override
    public void fly(){
        System.out.println("I'm inable to fly");
    }
    
    public float pricing(){
        return weight * unitPrice;
    }
}

Chicken chicken = new Chicken();
// initializing ...
chicken.yell();
chicken.fly();
chicken.pricing();
```

---

## Four Principles

* Encapsulation
* Inheritance
* Abstraction
* Polymorphism

---

## Encapsulation

> Reducing developers' mistake

```c
struct Chicken {
    char* wow;
    float weight;
    float unitPrice;
}

Bird* chicken = allocate(sizeof(Bird));
// initialize 
chicken->unitPrice = 14.5F;
...

chicken->unitPrice = 0.5F;
...

chickenPricing(chicken);

```

---

# Encapsulation

```java
class Chicken extends Bird {
    protected float weight;
    protected float unitPrice;
    
    public void setUnitPrice(float unitPrice){
        if(unitPrice < 10.0F){
            return;
        }
        
        this.unitPrice = unitPrice;
    }
    
    @Override
    public void fly(){
        System.out.println("I'm inable to fly");
    }
    
    public float pricing(){
        return weight * unitPrice;
    }
}

Chicken chicken = new Chicken();
chicken.setUnitPrice(14.5F);
// initialize ...
chicken.unitPrice = 0.5F; // compilation error

chicken.setUnitPrice(0.5F); // no effect

chicken.pricing();
```

---

## Inheritance

> Inheritance will make you rich!

![Alt text](https://www.planttext.com/plantuml/svg/SoWkIImgAStDiUPApaaiBbPmoYnAKQZcKW02vVBySYk5u9AYpBnqi81sPMfEZeOcX9sMKmbaraArEMTa9cUdvi5rfcQcPvHOAQJbv2UMG8HAyp9B0eeoarEHHQ6cW9a5HSb0Yy1cWXrXKwEh2pENoo4rBmNeIW00)

---

## Abstraction

TBD

---

## Polymorphism

TBD

---

## Reference



