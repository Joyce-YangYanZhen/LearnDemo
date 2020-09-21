package com.example.kotlindemo

import java.util.*
import kotlin.collections.ArrayList

/**
基本类型的运算比较
双冒号::的使用
"""原始字符串
返回函数（Unit）相当于java 中的 Void
vararg参数：使函数可以接受任意数量的参数
${}替换
字符串比较
流程控制语句
@标签的使用
loop和range(循环和区间)
map入门
函数表达式
代理和委托
枚举
印章类
 */

fun main() {
    /**基本类型的运算比较*/
    //function1()

    /**
     * 双冒号::的使用
     * Kotlin 中 双冒号操作符 表示把一个方法当做一个参数，传递到另一个方法中进行使用，通俗的来讲就是引用一个方法
     */
//    println(lock("param1", "param2", ::getResult))
//    //如果我们需要调用其他 Class 中的某一个方法是：
//    var d = Test()
//    println(lock("param1", "param2", d::getResult))
//    //我们在 Class 中的某个方法中使用双冒号调用当前 Class 的内部方法时调动方式为：
//    //为了防止作用域混淆 ， :: 调用的函数如果是类的成员函数或者是扩展函数，必须使用限定符,比如this
//    var e = Test1()
//    println(e.test())

    /**${}替换*/
    //function2()

    /**字符串比较*/
    //function3()

    /**流程控制语句*/
    //when_code(10)
    //if_code()
    //for_code()
    //while_code()
    //break_continue_code()

    /**@标签的使用*/
    //biaoqian()

    /**返回函数（Unit）相当于java 中的 Void*/
    //back_method()

    /**vararg参数：使函数可以接受任意数量的参数*/
    //vararg_method()

    /**loop和range(循环和区间)*/
    //function5()

    /**map入门*/
    //function6()

    /**函数表达式*/
    //function7()

    /**枚举*/
    //function8()

    /**印章类,子类类型是有限的，更在意类型，枚举类更在意数据*/
    //function9()

    /** """原始字符串*/
    //function10()

}


class Test1 {
    fun isOdd(x: Int) = x % 2 != 0

    fun test() {
        var list = listOf(1, 2, 3, 4, 5)
        println(list.filter(this::isOdd))
    }
}

class Test {
    /**
     * @param str1 参数1
     * @param str2 参数2
     */
    fun getResult(str1: String, str2: String): String = "result is {$str1 , $str2}"
}

/**
 * @param p1 参数1
 * @param p2 参数2
 * @param method 方法名称
 */
fun lock(p1: String, p2: String, method: (str1: String, str2: String) -> String): String {
    return method(p1, p2)
}

/**
 * @param str1 参数1
 * @param str2 参数2
 */
fun getResult(str1: String, str2: String): String = "result is {$str1 , $str2}"

fun vararg_method() {
    printsum(1, 2)
    printsum(1, 2, 3)
    printAll("AA", "B", "C")
    printAll2(1, 2, "哈哈1", "哈哈2", "哈哈3")
    printAll3(1, 2, "哈哈1", "哈哈2", "哈哈3")

    val texts = arrayOf(1, 2, "哈哈1", "哈哈2", "哈哈3")
    printAll3(*texts)//效果和printAll3是等价的
    printAll3("A", "B", *texts)
}

fun printAll3(vararg c: Any) {
    val allTexts = c.joinToString(",", "[", "]")
    println(allTexts)

}

fun printAll2(a: Int, b: Int, vararg c: String) {
    val allTexts = c.joinToString(",", "[", "]")
    println("$a and $b and $allTexts")
}

fun printAll(vararg texts: String) {
    val allTexts = texts.joinToString(",", "[", "]", 2)
    println("Text are $allTexts")

}

fun printsum(vararg numbers: Int) {
    val sum = numbers.sum()//多个整数之和
    println(sum)
}

fun back_method() {
    //Unit等价于java中的void
    printSum(1, 2)
    //unit也等价于任意其他对象，可以将其存储于变量中
    val p = printSum(1, 3)
    println(p is Unit)
    println(p == Unit)
    println(p === Unit)

    printSum1(1, 3)

}

fun printSum(a: Int, b: Int): Unit {
    val sum = a + b
    println(sum)
}

fun printSum1(a: Int, b: Int): Int {
    if (a < 0 || b < 0) {
        return 0
    }
    val sum = a + b
    print(sum)
    return sum
}

fun biaoqian() {
    println("START : " + ::biaoqian.name)
    outer@ for (out in 1..5) {//使用标签跳出指定循环
        println("outer $out")
        for (inner in 1..5) {
            if (inner % 2 == 0) {
                break@outer
            }
            println("inner $inner")
        }
    }
    println(" END : " + ::biaoqian.name)
}

fun function10() {
    //原始字符串引号，会保留字符串的格式，包括换行，符号等
    var rawCode = """
    for (item in list) {
        if (item is Son.小小驴) {
            println(item.sayHello())
        }
    }
    """
    print(rawCode)
}

fun function9() {
    var s: Son = Son.小小驴()
    var s1: Son = Son.小骡子()
    var s2: Son = Son.小小驴()
    var list = listOf<Son>(s, s1, s2)
    for (item in list) {
        if (item is Son.小小驴) {
            println(item.sayHello())
        }
    }
}


sealed class Son {
    fun sayHello() {
        println("大家好！")
    }

    class 小小驴() : Son()
    class 小骡子() : Son()

}

fun function8() {
    println(Week.day1.ordinal)//获取第几位
}

enum class Week {
    day1, day2, day3, day4
}

fun function7() {
    var i = { x: Int, y: Int -> x + y }
    var result = i(3, 5)
    println("result:$result")

    println(add_method(3, 5))

    var j: (Int, Int) -> Int = { x, y -> x + y }
    println(j(3, 5))
}

fun add_method(x: Int, y: Int): Int = x + y


fun function6() {
    var map = TreeMap<String, String>()
    map["好"] = "good"
    map["差"] = "bad"
    println(map["好"])
}

fun function5() {
    //从一加到100
    var nums = 1..100//区间 [1,100]
    var nums1 = 1 until 100 //[1,100)
    var result = 0
    for (num in nums) {//in关键字可以把数组里的值取出来
        result = result + num
    }
    println("结果：${result}")

    var nums2 = 1..16
    var nums3 = nums2.reversed()//数组反转
    for (a in nums2 step 2) {//step控制步长，类似for循环的i++操作
        println("step:：${a}")
    }

}

fun diaryGenarator(placeName: String) {//三个引号表示字符串输入
    var diary = """今天天气晴朗，我们去${placeName}游玩，映入眼帘的是${numberChiness(placeName.length)}个鎏金大字"""
    println(diary)
}

fun numberChiness(num: Int): String {
    var chiness = when (num) {
        3 -> "三"
        4 -> "四"
        else -> num.toString()
    }
    return chiness
}

fun when_code(score: Int) {//类似switch
    when (score) {
        10 -> println("棒棒哒")
        9 -> println("哈哈哈")
        else -> println("需要加油哦")
    }
    diaryGenarator("中山公园")

    //条件分支也可以是代码块
    val x = 1
    val validNumbers = arrayOf(1, 2, 3)
    when (x) {
        in 1..10 -> print("x is in the range")
        in validNumbers -> print("x is in the validNumbers")
        !in 10..20 -> print("x is outside the range")
        else -> print("none of the above")
    }

}

fun for_code() {
    var lists: ArrayList<String> = ArrayList()
    lists.add("aa")
    lists.add("bb")
    for (i in lists) {
        println(i)
    }
    for (i in lists.indices) {//遍历下标
        println(i)
    }
    //库函数withIndex
    for ((index, value) in lists.withIndex()) {
        println("the element at $index is $value")
    }
}

fun while_code() {
    var x = 10
    while (x > 0) {
        x--
        print("$x |")
    }
    println()
    var y = 10
    do {
        y = y + 1
        print("$y |")
    } while (y < 20)


}

fun break_continue_code() {
    for (i in 1..10) {
        println(i)
        if (i % 2 == 0) {//跳出整个循环
            break
        }
    }

    for (i in 1..10) {
        if (i % 2 == 0) {//跳过某个循环
            continue
        }
        println(i)
    }
}

fun if_code() {
    max(1, 3)
    max1(1, 3)
    max2(1, 3)
    max3(1, 3)

    val x = if (1 == 1) true else false
    println(x)
}

fun max(a: Int, b: Int): Int {
    val max = if (a > b) a else b
    return max
}

fun max1(a: Int, b: Int): Int {
    var max1 = a
    if (a < b) max1 = b
    return max1
}

fun max2(a: Int, b: Int): Int {
    var max2: Int
    if (a > b) {
        max2 = a
    } else {
        max2 = b
    }
    return max2
}

fun max3(a: Int, b: Int): Int {
    val max = if (a > b) {
        print("Max is a")
        a
    } else {
        print("Max is b")
        b
    }
    return max
}


fun function3() {
    var str1 = "haha"
    var str2 = "Haha"
    println(str1.equals(str2))
    println(str1 == str2)
    println(str1.equals(str2, false))//第二个参数代表是否忽略字母大小写
}

fun function2() {
    var str = "杨国"
    println("打印：${str}的长度为${str.length}")
}

fun function1() {
//    var aByte: Byte = Byte.MAX_VALUE
//    var bByte: Byte = Byte.MIN_VALUE
//    println("最大值" + aByte + "最小值" + bByte)
//
//    var num3 = Math.sqrt(5.0) - Math.sqrt(4.0)//根号
//    var num4 = Math.sqrt(4.0) - Math.sqrt(3.0)
//    println("根号比较:" + (num3 < num4))
//
//    var num5 = Math.pow(5.0, 100.0)//平方
//    var num6 = Math.pow(6.0, 75.0)
//    println("平方比较:" + (num5 < num6))

    val array = arrayOf(1, 2, 3)
    println(array[0])
    println(array is Array)
    println(array::class)
    println(array::class.java)
}




