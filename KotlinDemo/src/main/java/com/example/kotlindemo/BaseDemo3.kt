package com.example.kotlindemo

import android.os.Build
import androidx.annotation.RequiresApi

/**
 * 集合的类型
 * Set集合
 * MutableSet
 * Map
 * MutableMap
 * /**
 * 集合的操作：
 * （聚合系：）
 * any:只要其中一个元素满足判定条件
 * all:返回true,如果所有元素满足条件
 * count:返回匹配条件的元素数目
 * forEach:对每个元素执行指定的操作
 * （映射系或称变形系：）
 * flatMap:迭代集合所有的元素，为每一个元素生成一个新的集合，最后把所有集合摊平合并到一个集合里
 * groupBy:分组，返回一个原集合按条件判断函数分组后的map
 * map:返回一个对每个元素变换后的新集合
 * （元素操作：）
 * elementAt:
 * elementAtOrElse:
 * indexOf:
 * indexOfFirst:
 * (生成系：)
 * partition:
 * zip:
 * (排序系：)
 * reversed:
 * */
 */
@RequiresApi(Build.VERSION_CODES.N)
fun main(args: Array<String>) {
    /**
     * 集合的类型
     * 有序可重复-Array(数组)
     * 有序不重复-Set(数学中的集合)
     * 无序可重复-Map(字典)
     */
//    //数组排序
//    val numbers = arrayOf(0, -999, 100, 33, -2, 3000, 3000)
//    println(numbers.sortedArray().joinToString())//升序
//    println(numbers.sortedArrayDescending().joinToString())//降序
//    val numbers1 = arrayOf(
//        "电视剧看的", "电视剧看的",
//        "电视剧看的电视剧看的", "电视电视剧看的", "电视剧看的电视剧看的电视剧看的"
//    )
//    //根据字符串长度排序
//    println(numbers1.sortedBy { s -> s.length })//s代表数组元素的常量
//    println(numbers1.sortedByDescending { s -> s.length })
//    //把数字类型转换成字符串类型并加前缀
//    println(numbers.map { it -> "数字：" + it })
//    //返回一个删除了重复元素的新数组
//    println(numbers.distinct())
//    //只返回长度不同的字符串
//    println(numbers1.distinctBy { s -> s.length })
//    //all:检查全体元素是否满足，吐过满足则返回true
//    println(numbers.all { it > 0 })
//    //any:只要有一个元素满足条件，返回true
//    println(numbers.any() { it < 0 })
//    //nong:所有元素都不满足条件返回true
//    println(numbers.none { it == 0 })
    /**Set集合*/
//    val mainLine = setOf("迪士尼", "徐家汇", "桃浦新村", "南翔", "马陆", "嘉定新城")
//    val sublinejd = setOf("嘉定新城", "嘉定西", "嘉定北")
//    val sublineks = setOf("嘉定新城", "上海赛车场", "安亭", "花桥")
//    //判断sublinejd是否是mainLine的子集
//    println(mainLine.containsAll(sublinejd))
//    //+号可以融合两个Set集合
//    println((mainLine + sublinejd).containsAll(sublinejd))
//    println((mainLine + sublinejd).containsAll(mainLine))
//    //两个集合中都包含的元素(交集)
//    println(mainLine.intersect(sublinejd))
//    //从一个集合中排除与另一个集合交集的部分(差集)
//    println(mainLine.subtract(sublinejd))
//    //两个集合会合并成一个新集合，重复的元素只会留下一份（并集）
//    println(mainLine.union(sublinejd).union(sublineks))
//    //并集除去交集后的部分
//    println(mainLine.union(sublinejd) - mainLine.intersect(sublinejd))
    /**MutableSet*/
//    val mainLine = setOf("迪士尼", "徐家汇", "桃浦新村", "南翔", "马陆", "嘉定新城")
//    val sublinejd = setOf("嘉定新城", "嘉定西", "嘉定北")
//    val sublineks = setOf("嘉定新城", "上海赛车场", "安亭", "花桥")
//    //Set可以很方便的转变为MutableSet
//    val mutableMainline = mainLine.union(sublinejd).union(sublineks).toMutableList()
//    mutableMainline.add("光明路")//添加元素
//    println(mutableMainline)
//    mutableMainline.addAll(sublinejd)//添加另一个集合
//    println(mutableMainline)
//    mutableMainline.remove("光明路")
//    println(mutableMainline)
//    mutableMainline.removeAll(sublinejd)
//    println(mutableMainline)
    /**Map*/
//    val airports = mapOf<String, String>(Pair("PVG", "上海浦东国际机场"), Pair("DXB", "哈哈机场"))
//    println(airports.get("PVG"))
//    println(airports["PVG"] ?: "上海浦东国际机场")//获取值
//    println(airports["PVG"])
//    println(airports.keys)//获取所有的key
//    println(airports.values)//获取所有的value属性
//    println(airports.entries)//获取所有的条目
//    println(airports.containsKey("PVG"))//检查key是否存在
//    println(airports.containsValue("上海浦东国际机场"))//检查value是否存在
//    println(airports.filter { it.value.contains("浦东") })//筛选机场名中含有"浦东"的
//    println(airports.filterKeys { it.contains("P") })//筛选key中是否含有P
//    println(airports.filterValues { it.contains("浦东") })//筛选value中是否含有浦东
//    println(airports.filterNot { it.value.contains("浦") })//把不符合条件的筛选出来
//    //字典的变形
//    val new_airports = airports.map { "机场代码：" + it.key + ",机场全称：" + it.value }
//    println(new_airports)
//    //只对key变形
//    val newKeyAirports = airports.mapKeys { "机场代码：" + it.key }
//    println(newKeyAirports)
//    //只对value变形
//    val newValueAirports = airports.mapValues { "机场全称：" + it.value }
//    println(newValueAirports)
//    //求机场全称最长的
//    println(airports.maxBy { it.value.length })
//    //往后新增一个map列表
//    val new_map = airports + mapOf<String, String>(Pair("M", "月球静海传送站"), Pair("P", "冥王星"))
//    println(new_map.minBy { it.key.length })
//    //默认按key来排序
//    println(new_map.toSortedMap())
//    //转化成其他类型的集合
//    println(new_map.toList())
//    //转化成可变map
//    println(new_map.toMutableMap())
    /**MutableMap*/
//    val airports =
//        mapOf<String, String>(Pair("PVG", "上海浦东国际机场"), Pair("DXB", "哈哈机场")).toMutableMap()
//    airports["PVG"] = "上海浦东国际机场1"//设置元素
//    println(airports)
//    airports.putIfAbsent("PVG1", "上海浦东国际机场2")//如果不存在的话就添加
//    println(airports)
//    airports.put("KKK", "阿肯")//直接添加
//    println(airports)
//    //添加其他map
//    val airports_new = mapOf<String, String>(Pair("PVG111", "上海浦东国际机场22"), Pair("DXB22", "哈哈机场ff"))
//    airports += airports_new //putAll和+=操作是等价的
//    println(airports)


}