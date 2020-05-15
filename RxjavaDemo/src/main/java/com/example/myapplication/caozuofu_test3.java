package com.example.myapplication;


import android.util.Log;

import java.util.concurrent.TimeUnit;

import rx.Observable;
import rx.Subscriber;
import rx.functions.Action1;
import rx.functions.Func1;

/**
 * 《组合操作符》
 * combineLatest：MainActivity代码
 * join和groupJoin：MainActivity代码
 * merge和mergeDelayError：MainActivity代码
 * startWith
 * switch::MainActivity代码
 * zip和zipWith:MainActivity代码
 */
public class caozuofu_test3 {

    public static void main(String[] args) {
        //startWith();
    }




    private static void startWith() {
        Observable.just(1, 2, 3)
                .startWith(-1, 0)
                .subscribe(new Action1<Integer>() {
                    @Override
                    public void call(Integer integer) {
                        System.out.println("startWith:" + integer);
                    }
                });
    }


}
