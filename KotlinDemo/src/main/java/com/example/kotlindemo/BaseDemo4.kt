package com.example.kotlindemo

import java.util.function.BinaryOperator

/**
 * 类和对象
 * 在类中定义函数属性
 * 自定义get和set
 */
fun main(args: Array<String>) {
//    val friend_first = Friend(name = "xiaoming", age = 20)//主构造函数
//    val friend_second = Friend("DaMing", 45, friend_first)
    /**Person*/
//    val baby = Person("MingHong", 3)
//    val parent1 = Person("DaMing", 50)
//    val parent2 = Person("DaHong", 45)
//    val child1 = Person("XiaoMing", 18, mutableListOf(parent1, parent2), mutableListOf(baby))
//    val child2 = Person("XiaoHong", 20, mutableListOf(parent1, parent2), mutableListOf(baby))
//    child1.showParentsInformation()
//    child1.showChildrenInformation()
//    baby.showParentsInformation()

    /**在类中定义函数属性*/
//    val numComp = NumberCompution(10, 20, { x, y -> x + y })
//    numComp.operation()
//    numComp.operator = { x, y -> x * y }
//    numComp.operation()
//    println(numComp.fixed)
//    println(numComp.notFixed)
    /**自定义get和set*/
    var person = Person("haha", 17)
    println(person.isAdult)
    person.addAge = 10
    println(person.isAdult)
}

class NumberCompution(val num1: Int, val num2: Int, var operator: (Int, Int) -> Int) {
    val fixed = 1
    var notFixed = 1

    fun operation() {
        println("Operation Result:${operator(num1, num2)}")
    }
}

class Person(name: String, age: Int) {
    //主构造函数
    val name: String = name
    var age: Int = age
    var parents = mutableListOf<Person>()
    var children = mutableListOf<Person>()

    //自定义get和set
    val isAdult: Boolean
        get() = age >= 18
    var addAge: Int
        //一个属性自定义set也必须自定义它的get
        get() = 0
        set(value) {
            age += value
        }


    constructor(
        name: String,
        age: Int,
        parents: MutableList<Person>,
        children: MutableList<Person>
    ) : this(name, age) {
        parents.forEach { it.children.add(this) }
        parents.forEach { it.parents.add(this) }
        this.parents.addAll(parents)
        this.children.addAll(children)
    }

    /**
     * 关于个人的名字与年龄信息
     */
    fun showPersonalInformation() = println("name:${name},age:${age}")

    /**
     * 关于孩子的名字与年龄信息
     */
    fun showChildrenInformation() = children.forEach { it.showPersonalInformation() }

    /**
     * 显示关于父母的名字与年龄信息
     */
    fun showParentsInformation() = parents.forEach { it.showPersonalInformation() }

}

class Friend {
    var name: String = "name"
    var age: Int = 0
    var parent: Friend? = null

    init {
        println("You have a Friend name $name")
    }

    constructor(name: String, age: Int) {//次构造函数
        this.name = name
        this.age = age
    }

    constructor(name: String, age: Int, child: Friend) : this(name, age) {//次构造函数
        child.parent = this
    }
}
