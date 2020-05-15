package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.TimeUnit;

import rx.Completable;
import rx.Observable;
import rx.Single;
import rx.SingleSubscriber;
import rx.Subscriber;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action1;
import rx.functions.Func1;
import rx.functions.Func2;
import rx.functions.Func3;
import rx.functions.FuncN;


public class MainActivity extends AppCompatActivity {
    private String TAG = "MainActivity：";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


    }

    public void testoneclick13(View view) {
        zip_zipWith();

    }

    public void testoneclick12(View view) {
        switch_switchOnNext();

    }

    public void testoneclick11(View view) {
        merge_mergeDelayError();

    }

    public void testoneclick10(View view) {
        join_groupjoin();

    }

    public void testoneclick8(View view) {
        combineLatest();

    }

    public void testoneclick7(View view) {
        debounce();

    }


    public void testoneclick6(View view) {
        buffer();

    }


    public void testoneclick5(View view) {
        repeat_timer();

    }


    public void testoneclick4(View view) {
        inteval();

    }

    public void testoneclick1(View view) {
        testone();

    }

    public void testoneclick2(View view) {
        testtwo();

    }

    public void testoneclick3(View view) {
        testthree();

    }

    private void zip_zipWith() {
        zipWithObserver().subscribe(new Action1<String>() {
            @Override
            public void call(String s) {
                Log.e("zip:", s);
            }
        });
        zipWithIterableObserver().subscribe(new Action1<String>() {
            @Override
            public void call(String s) {
                Log.e("zipWithIterable:", s);
            }
        });
    }

    private Observable<String> zipWithObserver() {
        return createObservable(2L).zipWith(createObservable(3L),
                new Func2<String, String, String>() {
                    @Override
                    public String call(String s, String s2) {
                        return s + "-" + s2;
                    }
                });
    }

    private Observable<String> zipWithIterableObserver() {
        return Observable.zip(createObservable(2L),
                createObservable(3L), createObservable(4L),
                new Func3<String, String, String, String>() {
                    @Override
                    public String call(String s, String s2, String s3) {
                        return s + "-" + s2 + "-" + s3;
                    }
                });
    }

    private void switch_switchOnNext() {
        switchObserver().subscribe(new Action1<String>() {
            @Override
            public void call(String s) {
                Log.e("switchObserver:", s);
            }
        });
    }

    private Observable<String> createObservable(final Long index) {
        return Observable
                .interval(1000, 1000, TimeUnit.MILLISECONDS)
                .take(5)
                .map(new Func1<Long, String>() {
                    @Override
                    public String call(Long aLong) {
                        return index + ":" + aLong;
                    }
                });
    }

    private Observable<String> switchObserver() {
        return Observable.switchOnNext(Observable
                .interval(3000, TimeUnit.MILLISECONDS)
                .take(3)
                .map(new Func1<Long, Observable<? extends String>>() {
                    @Override
                    public Observable<? extends String> call(Long aLong) {
                        return createObservable(aLong);
                    }
                }));
    }

    private void merge_mergeDelayError() {
//        mergeObservable().subscribe(new Action1<Integer>() {
//            @Override
//            public void call(Integer integer) {
//                Log.e("mergeObservable:", integer + "");
//            }
//        });
        mergeDelayErrorObserver().subscribe(new Subscriber<Integer>() {
            @Override
            public void onCompleted() {
                Log.e("mergeDelayError:", "onCompleted");

            }

            @Override
            public void onError(Throwable e) {
                Log.e("mergeDelayError:", "" + e);

            }

            @Override
            public void onNext(Integer integer) {
                Log.e("mergeDelayError:", "" + integer);

            }
        });
    }

    private Observable<Integer> mergeObservable() {
        return rx.Observable.merge(Observable.just(1, 2, 3), Observable.just(4, 5, 6));
    }

    private Observable<Integer> mergeDelayErrorObserver() {
        return Observable.mergeDelayError(Observable.create(
                new Observable.OnSubscribe<Integer>() {
                    @Override
                    public void call(Subscriber<? super Integer> subscriber) {
                        for (int i = 0; i < 5; i++) {
                            if (i == 3) {
                                subscriber.onError(new Throwable("error"));
                            }
                            subscriber.onNext(i);
                        }
                    }
                }
        ), Observable.create(new Observable.OnSubscribe<Integer>() {
            @Override
            public void call(Subscriber<? super Integer> subscriber) {
                for (int i = 0; i < 5; i++) {
                    subscriber.onNext(5 + i);
                }
                subscriber.onCompleted();
            }
        }));
    }

    /**
     * join:根据时间窗口来组合两个Observable发送的数据，每个Observable都有一个自己的时间窗口，在这个时间窗口内的数据都是有效的，可以拿来组合
     * groupJoin,和join基本相同，只是通过groupjoin操作符组合后，发送出来的是一个个小的observable(里面包含了一轮组合数据)
     */
    private void join_groupjoin() {
        joinObservable().subscribe(new Action1<String>() {
            @Override
            public void call(String s) {
                Log.e(TAG, "joinObservable:" + s);
            }
        });
        groupJoinObservable().first().subscribe(new Action1<Observable<String>>() {
            @Override
            public void call(Observable<String> stringObservable) {
                stringObservable.subscribe(new Action1<String>() {
                    @Override
                    public void call(String s) {
                        Log.e(TAG, "groupJoinObservable:" + s);

                    }
                });
            }
        });
    }

    private Observable<String> getLeftObservable() {
        return Observable.just("a", "b", "c");
    }

    private Observable<Long> getRightObservable() {
        return Observable.just(11L, 21L, 31L);
    }

    private Observable<String> joinObservable() {
        return getLeftObservable().join(
                getRightObservable(),
                new Func1<String, Observable<Long>>() {
                    @Override
                    public Observable<Long> call(String s) {
                        return Observable.timer(1000, TimeUnit.MILLISECONDS);
                    }
                },
                new Func1<Long, Observable<Long>>() {
                    @Override
                    public Observable<Long> call(Long aLong) {
                        return Observable.timer(1000, TimeUnit.MILLISECONDS);
                    }
                },
                new Func2<String, Long, String>() {
                    @Override
                    public String call(String s, Long aLong) {
                        return s + ":" + aLong;
                    }
                });
    }

    private Observable<Observable<String>> groupJoinObservable() {
        return getLeftObservable().groupJoin(
                getRightObservable(),
                new Func1<String, Observable<Long>>() {
                    @Override
                    public Observable<Long> call(String s) {
                        return Observable.timer(1000, TimeUnit.MILLISECONDS);
                    }
                },
                new Func1<Long, Observable<Long>>() {
                    @Override
                    public Observable<Long> call(Long aLong) {
                        return Observable.timer(1000, TimeUnit.MILLISECONDS);
                    }
                },
                new Func2<String, Observable<Long>, Observable<String>>() {
                    @Override
                    public Observable<String> call(final String s, Observable<Long> longObservable) {
                        return longObservable.map(new Func1<Long, String>() {
                            @Override
                            public String call(Long aLong) {
                                return s + ":" + aLong;
                            }
                        });
                    }
                }
        );
    }


    private void combineLatest() {
        combineListObserver().subscribe(new Action1<String>() {
            @Override
            public void call(String s) {
                Log.e(TAG, "combineLatest:" + s);
            }
        });
        combineLatestObserver().subscribe(new Action1<String>() {
            @Override
            public void call(String s) {
                Log.e(TAG, "combineLatestObserver:" + s);
            }
        });

    }

    private Observable<Long> createObserver(int index) {
        return Observable.interval(500 * index, TimeUnit.MILLISECONDS);
    }

    private Observable<String> combineLatestObserver() {
        return Observable.combineLatest(createObserver(1), createObserver(2),
                new Func2<Long, Long, String>() {
                    @Override
                    public String call(Long aLong, Long aLong2) {
                        return "left:" + aLong + "right:" + aLong2;
                    }
                });
    }

    private Observable<String> combineListObserver() {
        ArrayList<Observable<Long>> list = new ArrayList<>();
        for (int i = 0; i < 3; i++) {
            list.add(createObserver(i));
        }

        return Observable.combineLatest(list, new FuncN<String>() {
            @Override
            public String call(Object... args) {
                String temp = "";
                for (Object i : args) {
                    temp = temp + ":" + i;
                }
                return temp;
            }

        });

    }

    /**
     * throttleWithTimeout:通过时间来限流，每次接收到源Observable发送出来的一个数据后就会进行计时，
     * 如果在设定好的时间又接收到源Observable发送的新数据，那么上一个数据就会被丢弃，同时重新开始计时，
     * 如果每次都是在计时结束前接收到新数据，那么这个限流就会走向极端，只发送最后一个数据。
     * debounce：过滤掉的数据会被丢弃掉，也可以依据时间来进行过滤，根据函数来进行限流，这个函数的返回值是
     * 一个临时的Observable,如果源Observable在发送一个新的数据时，上一个数据所生成的临时Observab还没有
     * 结束，那么上一个数据就会被过滤掉。
     */
    private void debounce() {
        /*Observable.create(new Observable.OnSubscribe<Integer>() {
            @Override
            public void call(Subscriber<? super Integer> subscriber) {
                for (int i = 0; i < 10; i++) {
                    if (!subscriber.isUnsubscribed()) {
                        subscriber.onNext(i);
                    }
                    int sleep = 100;
                    if (i % 3 == 0) {//当发送的数据是3的倍数时，下一个数据就延迟到300毫秒后再发送
                        sleep = 300;
                    }
                    try {
                        Thread.sleep(sleep);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                subscriber.onCompleted();

            }
        }).subscribeOn(Schedulers.computation())
                .throttleWithTimeout(200, TimeUnit.MILLISECONDS)//发送时间间隔小于200毫秒的数据会被过滤掉
                .subscribe(new Action1<Integer>() {
                    @Override
                    public void call(Integer integer) {
                        Log.e(TAG, "throttleWithTimeout:" + integer);//只会发出3的倍数
                    }
                });*/
        Observable.interval(1000, TimeUnit.MILLISECONDS)//每隔1000毫秒发送一个数据
                .debounce(new Func1<Long, Observable<Long>>() {
                    @Override
                    public Observable<Long> call(Long aLong) {
                        //对于偶数，time操作符会马上结束，
                        //对于奇书则是1500毫秒后结束，因为1500毫秒大于1000秒，所有的奇数都被过滤掉了
                        return Observable.timer(aLong % 2 * 1500, TimeUnit.MILLISECONDS);
                    }
                })
                .subscribe(new Action1<Long>() {
                    @Override
                    public void call(Long aLong) {
                        Log.e(TAG, "debounce:" + aLong);

                    }
                });

    }


    /**
     * buffer将数据按照规定的大小做一下缓存，当缓存的数据量达到设置的上限后就将缓存的数据作为一个集合发送出去
     * buffer可以设置跳过的数目
     * buffer不仅可以通过数量规则来缓存，也可以通过时间等规则来缓存
     */
    private void buffer() {
        Observable.just(1, 2, 3, 4, 5, 6, 7, 8, 9)
                .buffer(2, 3)//每间隔3个数据发送一个包含2个数据的集合
                .subscribe(new Action1<List<Integer>>() {
                    @Override
                    public void call(List<Integer> integers) {
                        Log.e(TAG, "bufferObserver:" + integers);
                    }
                });

        Observable.interval(1, TimeUnit.SECONDS)
                .buffer(4, TimeUnit.SECONDS)//每3秒钟缓存发送依次
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Action1<List<Long>>() {
                    @Override
                    public void call(List<Long> longs) {
                        Log.e(TAG, "bufferTimeObserver:" + longs);

                    }
                });
    }

    /**
     * repeat:数据重复发送N次
     * timer:会在指定的时间后发送一个数字0
     */
    private void repeat_timer() {
        Observable.just(1, 2, 3)
                .repeat(2)
                .subscribe(new Action1<Integer>() {
                    @Override
                    public void call(Integer integer) {
                        Log.e(TAG, "repeatObservable:" + integer);
                    }
                });

        Observable
                .timer(1, TimeUnit.SECONDS)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Action1<Long>() {
                    @Override
                    public void call(Long aLong) {
                        Log.e(TAG, "timerObservable:" + aLong);

                    }
                });
    }

    /**
     * inteval:从0开始隔固定时间发送一个数字，需要在主线程中订阅，
     * 由于会不停的发送数据，在不需要时，要进行"反订阅"才能停止发送数据
     */
    private void inteval() {
        Observable.interval(1, TimeUnit.SECONDS)//每隔一秒钟发送一次
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<Long>() {
                    @Override
                    public void onCompleted() {
                        Log.e(TAG, "onCompleted");
                    }

                    @Override
                    public void onError(Throwable e) {
                        Log.e(TAG, e.getMessage());
                    }

                    @Override
                    public void onNext(Long aLong) {
                        if (aLong == 10) {
                            onCompleted();
                            unsubscribe();
                        }
                        Log.e(TAG, "onNext:" + aLong);

                    }
                });
    }

    /**
     * Completable:单个事件的生产者
     * 与Single的不同在于只会发送错误和结束的事件，而不会发送数据
     */
    private void testthree() {
        Completable.error(new Throwable("Completable error"))
                .subscribe(new Completable.CompletableSubscriber() {
                    @Override
                    public void onCompleted() {
                        Log.e(TAG, "onCompleted");

                    }

                    @Override
                    public void onError(Throwable e) {
                        Log.e(TAG, "" + e.getMessage());

                    }

                    @Override
                    public void onSubscribe(Subscription d) {

                    }
                });
        Completable.complete()
                .subscribe(new Completable.CompletableSubscriber() {
                    @Override
                    public void onCompleted() {
                        Log.e(TAG, "onCompleted");

                    }

                    @Override
                    public void onError(Throwable e) {
                        Log.e(TAG, "" + e.getMessage());

                    }

                    @Override
                    public void onSubscribe(Subscription d) {

                    }
                });
    }


    /**
     * 单例Single
     * 只能发送一个数据或者一个错误事件
     */
    private void testtwo() {
        Single.create(new Single.OnSubscribe<Integer>() {
            @Override
            public void call(SingleSubscriber<? super Integer> singleSubscriber) {
                if (!singleSubscriber.isUnsubscribed()) {
                    //singleSubscriber.onSuccess(1);
                    singleSubscriber.onError(new Throwable("错了吧"));
                }
            }
        }).subscribe(new SingleSubscriber<Integer>() {
            @Override
            public void onSuccess(Integer value) {
                Log.e(TAG, "" + value);
            }

            @Override
            public void onError(Throwable error) {
                Log.e(TAG, error.getMessage());
            }
        });
    }

    private void testone() {
        createobserver().subscribe(new Subscriber<Integer>() {
            @Override
            public void onCompleted() {
                Log.e(TAG, "onCompleted");
            }

            @Override
            public void onError(Throwable e) {
                Log.e(TAG, "" + e.getMessage());

            }

            @Override
            public void onNext(Integer integer) {
                Log.e(TAG, "onNext:" + integer);

            }
        });
    }


    private Observable<Integer> createobserver() {
        return Observable.create(new Observable.OnSubscribe<Integer>() {
            @Override
            public void call(Subscriber<? super Integer> subscriber) {
                if (!subscriber.isUnsubscribed()) {
                    for (int i = 0; i < 5; i++) {
                        int temp = new Random().nextInt(10);
                        if (temp > 8) {
                            subscriber.onError(new Throwable("value>8"));
                            break;
                        } else {
                            subscriber.onNext(temp);
                        }
                        if (i == 4) {
                            subscriber.onCompleted();
                        }
                    }
                }
            }
        });
    }


}
