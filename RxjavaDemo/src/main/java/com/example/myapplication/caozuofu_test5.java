package com.example.myapplication;


import rx.Observable;
import rx.Subscriber;
import rx.functions.Action1;
import rx.functions.Func1;

/**
 * 《条件操作符》
 * all
 * amb
 * contains
 * isEmpty
 * defaultIfEmpty
 * sequenceEqual
 * skipUntil和skipWhile
 * takeUntil和takeWhile
 * <p>
 * 《聚合操作符》
 * concast
 * count
 * reduce
 * callect
 * <p>
 * 《与Connectable Observable相关的操作符》
 * publish和connect
 * refCount
 * replay
 * <p>
 * 《自定义操作符》
 * lift
 * compose
 */
public class caozuofu_test5 {
    public static void main(String[] args) {
        //lift();
        compose();
    }

    private static void compose() {
        composeObserver().subscribe(new Action1<String>() {
            @Override
            public void call(String s) {
                System.out.println("compose:" + s);
            }
        });
    }

    private static Observable<String> composeObserver() {
        Observable.Transformer<Integer, String> myTransformer =
                new Observable.Transformer<Integer, String>() {
                    @Override
                    public Observable<String> call(Observable<Integer> integerObservable) {
                        return integerObservable
                                .map(new Func1<Integer, String>() {
                                    @Override
                                    public String call(Integer integer) {
                                        return "myTransforer:" + integer;
                                    }
                                })
                                .doOnNext(new Action1<String>() {

                                    @Override
                                    public void call(String o) {
                                        System.out.println("doOnNext:" + o);
                                    }
                                });
                    }
                };
        return Observable.just(1, 2, 3).compose(myTransformer);
    }

    private static void lift() {
        liftObservable().subscribe(new Action1<String>() {
            @Override
            public void call(String s) {
                System.out.println("lift:" + s);
            }
        });
    }

    private static Observable<String> liftObservable() {
        Observable.Operator<String, String> myOperator = new Observable.Operator<String, String>() {
            @Override
            public Subscriber<? super String> call(final Subscriber<? super String> subscriber) {
                return new Subscriber<String>() {
                    @Override
                    public void onCompleted() {
                        if (!subscriber.isUnsubscribed()) {
                            subscriber.onCompleted();
                        }
                    }

                    @Override
                    public void onError(Throwable e) {
                        if (!subscriber.isUnsubscribed()) {
                            subscriber.onError(e);
                        }
                    }

                    @Override
                    public void onNext(String s) {
                        if (!subscriber.isUnsubscribed()) {
                            subscriber.onNext("myOperator:" + s);
                        }
                    }
                };
            }
        };

        return Observable.just(1, 2, 3)
                .map(new Func1<Integer, String>() {
                    @Override
                    public String call(Integer integer) {
                        return "map1:" + integer;
                    }
                })
                .lift(myOperator)
                .map(new Func1<String, String>() {
                    @Override
                    public String call(String s) {
                        return "map2:" + s;
                    }
                });
    }
}
