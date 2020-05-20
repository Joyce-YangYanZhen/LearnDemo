package com.example.kotlindemo

/**
 * 拓展函数，DSL
 * 同时继承类和接口
 * 抽象类
 */
fun main(args: Array<String>) {
    //同时继承类和接口
    //Square().draw()

    //抽象类
    //Demo().draw()


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

