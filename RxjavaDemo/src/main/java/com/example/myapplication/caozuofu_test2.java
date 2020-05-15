package com.example.myapplication;

import java.util.concurrent.TimeUnit;

import rx.Observable;
import rx.functions.Action1;
import rx.functions.Func1;

/**
 * 《过滤操作符》
 * debounce:在MainActivity
 * distinct
 * elementAt
 * filter：
 * first和last：first只会返回第一条或者满足条件的第一条数据，last相反
 * skip和take
 * skipLast和takeLast
 * sample和throttleFirst
 */
public class caozuofu_test2 {

    public static void main(String[] args) {
        //distinct();
        //elementAt();
        //filter();
        //first_last();
        sample_throttleFirst();
    }

    /**
     * sample:指定一段时长，在时长结束后发送源Observe的最新数据，取规定时间段内最后一个数据，其他都会被过滤
     * throttleFirst：取规定时间段内的第一个数据，其他的会被过滤掉（点击事件去重）
     */
    private static void sample_throttleFirst() {
        Observable.interval(200, TimeUnit.MILLISECONDS)
                .sample(1000, TimeUnit.MILLISECONDS)//每隔五个数字发出最后一个数字
                .subscribe(new Action1<Long>() {
                    @Override
                    public void call(Long aLong) {
                        System.out.println("sample:" + aLong);
                    }
                });
        Observable.interval(200, TimeUnit.MILLISECONDS)
                .throttleFirst(1000, TimeUnit.MILLISECONDS)//每隔五个数字发出第一个数字
                .subscribe(new Action1<Long>() {
                    @Override
                    public void call(Long aLong) {
                        System.out.println("throttleFirst:" + aLong);
                    }
                });
    }

    /**
     * skip:过滤掉前N项
     * take:只取前N项
     */
    /**
     * first和last：first只会返回第一条或者满足条件的第一条数据，last相反
     */

    private static void first_last() {
        int first = Observable.just(0, 1, 2, 3, 4, 5)
                .toBlocking()//将Observable对象转化为BlockingObse对象，
                // 并不会对Observable做任何处理，只会阻塞住，
                //只有当满足条件的数据发送出来的时候，才会发送一个BlockingObse对象。
                .last(new Func1<Integer, Boolean>() {
                    @Override
                    public Boolean call(Integer integer) {
                        return integer > 0;
                    }
                });
        System.out.println("last:" + first);

    }

    /**
     * filter:根据函数来进行过滤，将数据发送到函数里，返回为true才发送出去，否则过滤
     */
    private static void filter() {
        Observable.just(0, 1, 2, 3, 4, 5)
                .filter(new Func1<Integer, Boolean>() {
                    @Override
                    public Boolean call(Integer integer) {
                        return integer < 3;
                    }
                })
                .subscribe(new Action1<Integer>() {
                    @Override
                    public void call(Integer integer) {
                        System.out.println("filter:" + integer);
                    }
                });
    }

    /**
     * elementAt:只会过滤出源Observable发送出来的顺序为N的数据（类似数组取出相应数组下标的值）
     */
    private static void elementAt() {
        Observable.just(0, 1, 2, 3, 4)
                .elementAt(2)//只将操作符顺序为2的数据过滤出来
                .subscribe(new Action1<Integer>() {
                    @Override
                    public void call(Integer integer) {
                        System.out.println("elementAt:" + integer);
                    }
                });
    }

    /**
     * distinct:去重，作用范围是全局的，发送出来的数据肯定没有重复的
     * distinctUntilChanged：过滤掉连续的重复数据，作用范围是局部的，
     * 发送出来的数据有可能重复，但重复的数据是不连续的
     */
    private static void distinct() {
        Observable.just(1, 2, 3, 4, 2, 3, 4, 5, 6)
                .distinct()
                .subscribe(new Action1<Integer>() {
                    @Override
                    public void call(Integer integer) {
                        System.out.println("distinct:" + integer);
                    }
                });
        Observable.just(1, 2, 7, 3, 4, 2, 3, 4, 5, 6)
                .distinctUntilChanged()
                .subscribe(new Action1<Integer>() {
                    @Override
                    public void call(Integer integer) {
                        System.out.println("distinctUntilChanged:" + integer);
                    }
                });
    }
}
