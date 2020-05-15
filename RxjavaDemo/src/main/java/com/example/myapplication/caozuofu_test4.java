package com.example.myapplication;

import rx.Notification;
import rx.Observable;
import rx.Subscriber;
import rx.functions.Action1;
import rx.functions.Func1;

/**
 * 《错误处理操作符》
 * onErrorReturn
 * onErrorResumeNext
 * onExceptionResumeNext
 * retry
 * 《辅助操作符》
 * delay
 * do
 * materialize和dematerialize
 * subscribeOn和observeOn
 * timeInterval和timeStamp
 * timeout
 * using
 */
public class caozuofu_test4 {
    public static void main(String[] args) {
        //onErrorReturn();
        //onErrorResumeNext();
        //materialize_dematerialize();
    }

    private static void materialize_dematerialize() {
        materializeObservable().subscribe(new Action1<Notification<Integer>>() {
            @Override
            public void call(Notification<Integer> integerNotification) {
                System.out.println("materializeObservable:"+
                        integerNotification.getValue()
                        +"type:"+integerNotification.getKind());
            }
        });
        deMaterializeObservable().subscribe(new Action1<Integer>() {
            @Override
            public void call(Integer integer) {
                System.out.println("deMaterializeObservable:"+integer);
            }
        });

    }
    private static Observable<Notification<Integer>> materializeObservable(){
        return Observable.just(1,2,3).materialize();
    }
    private static Observable<Integer> deMaterializeObservable(){
        return materializeObservable().dematerialize();
    }




    private static Observable<String> onErrorResumeNextObserver() {
        return createObservable().onErrorResumeNext(Observable.just("7", "8", "9"));
    }

    /**
     * onErrorResumeNext:与onErrorReturn不同的是，如果Exception，就会使用另一个Observable
     * 代替原Observable继续发送数据，否则会将错误分给Subsctiber
     */
    private static void onErrorResumeNext() {
        onErrorResumeNextObserver().subscribe(new Subscriber<String>() {
            @Override
            public void onCompleted() {
                System.out.println("onCompleted");
            }

            @Override
            public void onError(Throwable e) {
                System.out.println("onError:" + e.getMessage());
            }

            @Override
            public void onNext(String s) {
                System.out.println("onNext:" + s);
            }
        });
    }


    private static Observable<String> createObservable() {
        return Observable.create(new Observable.OnSubscribe<String>() {
            @Override
            public void call(Subscriber<? super String> subscriber) {
                for (int i = 1; i < 6; i++) {
                    if (i < 3) {
                        subscriber.onNext("onNext:" + i);
                    } else {
                        subscriber.onError(new Throwable("Throw Error"));
                    }
                }
            }
        });
    }

    private static Observable<String> onErrorReturnObservable() {
        return createObservable().onErrorReturn(new Func1<Throwable, String>() {
            @Override
            public String call(Throwable throwable) {
                return "onErrorReturn";
            }
        });
    }

    /**
     * onErrorReturn:可以在发生错误时，让Observable发送一个预先定义好的数据并停止继续发送数据，正常结束整个过程
     */
    private static void onErrorReturn() {
        onErrorReturnObservable().subscribe(new Subscriber<String>() {
            @Override
            public void onCompleted() {
                System.out.println("onCompleted");
            }

            @Override
            public void onError(Throwable e) {
                System.out.println("onError:" + e.getMessage());

            }

            @Override
            public void onNext(String s) {
                System.out.println("onNext:" + s);

            }
        });
    }


}
