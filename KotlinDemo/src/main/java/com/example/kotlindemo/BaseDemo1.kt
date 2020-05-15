package com.example.kotlindemo

import java.util.*

/**
基本类型的运算比较
${}替换
字符串比较
when操作符
loop和range(循环和区间)
map入门
函数表达式
代理和委托
枚举
印章类
 */
fun main() {
    //基本类型的运算比较
    function1()

    //${}替换
    function2()

    //字符串比较
    function3()

    //when操作符
    function4(10)
    diaryGenarator("中山公园")

    //loop和range(循环和区间)
    function5()

    //map入门
    function6()

    //函数表达式
    function7()

    //枚举
    function8()

    //印章类,子类类型是有限的，更在意类型，枚举类更在意数据
    function9()

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

fun function4(score: Int) {//类似switch
    when (score) {
        10 -> println("棒棒哒")
        9 -> println("哈哈哈")
        else -> println("需要加油哦")
    }
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
    var aByte: Byte = Byte.MAX_VALUE
    var bByte: Byte = Byte.MIN_VALUE
    println("最大值" + aByte + "最小值" + bByte)

    var num3 = Math.sqrt(5.0) - Math.sqrt(4.0)//根号
    var num4 = Math.sqrt(4.0) - Math.sqrt(3.0)
    println("根号比较:" + (num3 < num4))

    var num5 = Math.pow(5.0, 100.0)//平方
    var num6 = Math.pow(6.0, 75.0)
    println("平方比较:" + (num5 < num6))
}




