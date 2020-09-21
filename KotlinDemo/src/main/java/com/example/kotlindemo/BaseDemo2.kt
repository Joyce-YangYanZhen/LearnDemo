package com.example.kotlindemo

import android.os.Build
import androidx.annotation.RequiresApi
import java.nio.file.Files
import java.nio.file.Paths

/**
 * 元祖
 * 同时继承类和接口
 * 抽象类
 * "==="代表引用相等，"=="代表结构相等
 * 区间range
 * drop字符：与之相反的是take字符
 * vararg修饰符
 * 函数的作用范围：成员函数、本地函数、顶层函数
 * in和contains是等价的
 * 拓展函数
 * 中缀函数infix
 * 函数字面量
 * 尾递归函数
 * 标准函数库
 * Lambda表达式
 * 高阶函数
 * 顶层函数引用
 */
@RequiresApi(Build.VERSION_CODES.O)
fun main(args: Array<String>) {
    /**元祖*/
    //yuanzu()
    /**同时继承类和接口*/
    //Square().draw()
    /**抽象类*/
    //Demo().draw()
    /**区间range*/
    //range_test()
    /**drop字符：与之相反的是take字符*/
    //drop_method()
    /**vararg修饰符*/
    //vararg_method1()
    /**函数的作用范围：
     * 成员函数、
     * 本地函数、
     * 顶层函数：即这些函数不属于任何源码文件的小集团（class、对象、inteface）而是直接定义在源码文件中的，它们在所有小集团的顶层之上*/
//    val rect = Rect()
//    rect.showArea(3, 4)
//    val printarea = printArea(3, 4)
//    println(printarea)
//    checkNumber(1, 15)
    /**in和contains是等价的*/
    //in_contains()
    /**拓展函数*/
//    val number2 = MyNumber(8)
//    number2.addFactor(3)
//    println(number2.k)
    //拓展函数在子类中的重载,总是会调用Particle的拓展，是因为接受者类型的编译时就决定的，也就是静态的
//    val al = Element("铝")
//    al.react(Particle())
//    al.react(Electron())
//    val neon = NobleGas("氩")
//    neon.react(Particle())
//    neon.react(Electron())
    /**中缀函数infix*/
//    val train = "北京".到("上海")
//    println(train)
    /**函数字面量*/
    /**尾递归函数*/
    /**标准函数库
     * apply:调用的时候接受一个Lambda表达式，可以任意调用该对象的任意方法，然后返回该对象
     * let:默认当前这个对象作为闭包的it参数，返回值是函数里面最后一行，或者指定return
     * with:执行一个对象的方法，省略对象名
     * run:使用最后一行的返回，apply返回当前自己的对象
     * lazy:可以把非常消耗资源的操作延迟到第一次调用时再加载
     * Use:和try类似，会安全的调用函数，在函数完成后关闭占用的资源，不管是否出现异常
     * repeat:重复执行一个闭包指定次数，简单函数可以避免使用for循环
     * require/assert/check的使用
     * */
    //apply
//    val task = Runnable { println("运行中1") }
//    Thread(task).apply { isDaemon = true }.start()
    //除去apply另一种写法
//    val task1 = Runnable { println("运行中2") }
//    val thread = Thread(task1)
//    thread.isDaemon = true
//    thread.start()

    //let
//    println(myLet())
    //with和let结合
//    testMyWith()
    //run
//    ArrayList<String>().run {
//        add("testMyWith 测试1")
//        add("testMyWith 测试2")
//        add("testMyWith 测试3")
//        println(this.joinToString())
//    }
    //Use
//    val input = Files.newInputStream(Paths.get("input.tex"))
//    val byte = input.use { input.read() }

    //repeat
    //repeat(8,{ println("Kotlin重复执行")})
    /**Lambda表达式*/
    //println(comboTwoValue(3, { x, y -> x + y }, 4))
    /**高阶函数*/
//    val cc = arrayOf(5,4,3)
//    val dd = cc.filter { it > 3 }
//    println(dd)
    /**顶层函数引用*/
//    val dd = arrayOf(5, 4, 3).filter { isEven(it) }
//    val gg = arrayOf(5, 4, 3).filter(::isEven)//"::"一元操作符要写在函数名之前，花括号要改成圆括号
//    println(dd)
//    println(gg)
//
//    val allOddNumbers = arrayOf(5, 4, 3).filter(Int::isOdd)//可以像引用成员函数那样引用拓展函数
//    println(allOddNumbers)
//
//    val p = arrayOf("cool", "kotlin", "tutorial")
//    val x = p.filter("kotlin"::equals)
//    println(x)
}

fun Int.isOdd() = this % 1 == 0//Int类拓展了一个奇数判断函数


fun isEven(a: Int) = a % 2 == 0


fun comboTwoValue(x: Int, method: (a: Int, b: Int) -> Int, y: Int): Int {
    return method(x, y)
}

fun testMyWith() {
    with(ArrayList<String>()) {
        add("testMyWith 测试1")
        add("testMyWith 测试2")
        add("testMyWith 测试3")
        println("this = " + this)
    }.let { println(it) }
}

fun myLet(): Int {
    "myLet".let {
        println(it)
        return 999
    }
}


infix fun String.到(other: String): String {
    return this + "到" + other + "的距离"
}


open class Particle()
class Electron : Particle()
open class Element(val name: String) {
    open fun Particle.react(name: String): Unit {
        println("$name 与粒子发生反应")
    }

    open fun Electron.react(name: String): Unit {
        println("$name 与电子发生反应生成同位素")
    }

    fun react(paticle: Particle): Unit {
        paticle.react(name)
    }
}

class NobleGas(name: String) : Element(name) {
    override fun Particle.react(name: String) {
        println("$name 是稀有气体，不与与粒子发生反应")
    }

    override fun Electron.react(name: String) {
        println("$name 是稀有气体，不与与电子发生反应")
    }

    fun react(electron: Electron): Unit {
        electron.react(name)
    }
}


class MyNumber(var k: Int) {
    private fun Int.triple() = this * this * this
    fun addFactor(p: Int) {
        k += p.triple()
    }
}

fun in_contains() {
    val numbers = arrayOf(5, 6, 7, 8)
    val a = 6 in numbers
    val b = numbers.contains(6)
    println("$a | $b")
    val c = 7 !in numbers
    val d = !numbers.contains(7)
    println("$c | $d")

}

fun checkNumber(start: Int, end: Int): Unit {
    for (k in start..end) {
        fun isThrees() = (k % 3 == 0)
        fun isFive() = (k % 5 == 0)

//        if (isThrees()) {
//            println("$k 被3整除")
//        } else if (isFive()) {
//            println("$k 被5整除")
//        } else if (isThrees() && isFive()) {
//            println("$k 既能被3整除，也能被5整除")
//        }else{
//            println(k)
//        }
        //效果同上
        when {
            isThrees() -> println("$k 被3整除")
            isFive() -> println("$k 被5整除")
            isThrees() && isFive() -> println("$k 既能被3整除，也能被5整除")
            else -> println(k)
        }

    }


}


/**
 * 成员函数
 */
class Rect {
    fun area(x: Int, y: Int): Int {
        return x * y
    }

    fun showArea(x: Int, y: Int): Unit {
        val area = area(x, y)//调用的是成员函数
        println("面积 = $area")
    }
}

/**
 * 本地函数
 */
fun printArea(x: Int, y: Int): Unit {
    fun area(x: Int, y: Int): Int {
        return x * y
    }

    fun area1() = x * y//另一种写法

    val area = area(x, y)
    println("面积 = $area")

}


fun vararg_method1() {
    val sum1 = sum(1, 2, 3, 5, 100)
    println(sum1)

    val a = intArrayOf(1, 2, 3, 4, 100)
    println(sum(*a)) //*是展开操作符，是紧跟在变量名之前，是一元操作符，这里指把数组a全部展开
}

fun sum(vararg x: Int): Int {
    var total = 0
    for (i in x) {
        total += i
    }
    return total
}

fun drop_method() {
    val title = "   前面有空格的文本 嘎嘎"
    println(title.drop(6))//舍弃前6个字符
    println(title.dropLast(3))//舍弃后3个字符
    println(title.dropWhile { it.isWhitespace() })//去掉字符串前的所有空格
    println(title.dropWhile { it.isWhitespace() }
        .dropLastWhile { it == '嘎' || it.isWhitespace() })//可以往后追加条件
}

fun range_test() {
    val a到z = "a".."z"
    val d在其中 = "d" in a到z
    val 一到一百 = 1..100
    val 三十八在其中 = 38 in 一到一百
    println("${d在其中}|${三十八在其中}")

    val 倒数的奇数 = (1..100).step(2).reversed()//reversed代表反转数组
    for (i in 倒数的奇数) {
        print("${i},")
    }
}


fun yuanzu() {
    /**
     * 元祖只分二元（Pair）和三元（Triple）最多容纳三个值
     */
    val lesson = Triple(3, "学会", "kotlin")
    val money = Pair("学费", 0)
    print("${lesson.first}天${lesson.second}${lesson.third},${money.first}${money.second}元")
}

open class Polygon1 {
    open fun draw() {
        System.out.println("Polygon1:draw")
    }
}

abstract class Rectangle1 : Polygon1() {
    override fun draw() {
        super.draw()
        System.out.println("Rectangle1:draw")
    }
}

class Demo : Rectangle1() {
    override fun draw() {
        super.draw()
        System.out.println("demo:draw")

    }
}


class Square : Rectangle(), Polygon {
    override fun draw() {
        super<Rectangle>.draw()

    }

}

open class Rectangle {
    open fun draw() {
        System.out.println("Rectangle:draw")
    }
}

interface Polygon {
    fun draw()
}

