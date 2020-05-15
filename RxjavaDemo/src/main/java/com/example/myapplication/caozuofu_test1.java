package com.example.myapplication;

import android.util.Log;


import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import rx.Observable;
import rx.Subscriber;
import rx.functions.Action1;
import rx.functions.Func0;
import rx.functions.Func1;
import rx.functions.Func2;
import rx.observables.GroupedObservable;
import rx.schedulers.Schedulers;

/**
 * 《创建Observable的操作符》
 * range:
 * defer:
 * just:
 * from:
 * inteval: 在MainActivity中有代码
 * repeat和timer: 在MainActivity中有代码
 * <p>
 * 《转化Observable的操作符》
 * buffer:在MainActivity中有代码
 * flatMap:
 * groupBy:
 * map:
 * cast:
 * scan:
 * window:
 */
public class caozuofu_test1 {
    private static String TAG = "caozuofu_test1:";

    public static void main(String args[]) {
        //range();
        //defer_just();
        //from();
        //inteval();
        //flatmap();
        //groupBy();
        //map();
        //cast();
        //scan();
        //window();
    }

    /**
     * 类似buffer,不同之处是buffer将收集的数据打包作为一个整体发送出去，如list
     * 而window发送的是一些小的Observable（可再订阅）来实现数据分组，
     * 注意：可通过数据和时间来分组
     */
    private static void window() {
        Observable.just(1, 2, 3, 4, 5, 6, 7, 8, 9)
                .window(3)
                .subscribe(new Action1<Observable<Integer>>() {
                    @Override
                    public void call(Observable<Integer> integerObservable) {
                        System.out.println(integerObservable.getClass().getName());
                        integerObservable.subscribe(new Action1<Integer>() {
                            @Override
                            public void call(Integer integer) {
                                System.out.println("window:" + integer);
                            }
                        });
                    }
                });
        //需要放在activity中实现
        Observable.interval(1000, TimeUnit.MILLISECONDS)//每隔3秒发出2个或3个数据
                .window(3000, TimeUnit.MILLISECONDS)
                .subscribe(new Action1<Observable<Long>>() {
                    @Override
                    public void call(Observable<Long> longObservable) {
                        System.out.println("" + System.currentTimeMillis() / 1000);
                        longObservable.subscribe(new Action1<Long>() {
                            @Override
                            public void call(Long aLong) {
                                System.out.println("windowTime:" + aLong);
                            }
                        });
                    }
                });

    }

    /**
     * scan:对一个序列的数据应用同一个函数进行计算，并将这个函数的结果发送出去，
     * 作为下一个数据应用这个函数时的第一个参数使用。也就是说任何一个要发送出来的数据
     * 都是以上次发送出来的"数据结果"作为输入，并应用同一个函数计算后的结果
     * （类似递归操作符）
     */
    private static void scan() {
        ArrayList<Integer> list = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            list.add(2);
        }
        Observable.from(list)
                .scan(new Func2<Integer, Integer, Integer>() {
                    @Override
                    public Integer call(Integer x, Integer y) {//计算2的10次方
                        return x * y;
                    }
                })
                .subscribe(new Action1<Integer>() {
                    @Override
                    public void call(Integer i) {
                        System.out.println("scan:" + i);
                    }
                });
    }

    /**
     * cast:将Observable发送的数据强制转化为另一种类型
     */
    private static void cast() {
        Observable.just(getAnimal())
                .cast(Dog.class)
                .subscribe(new Action1<Dog>() {
                    @Override
                    public void call(Dog dog) {
                        System.out.println("cast:" + dog.getName());
                    }
                });
    }

    static class Animal {
        protected String name = "Animal";

        Animal() {
            System.out.println("create:" + name);

        }

        String getName() {
            return name;
        }
    }

    static class Dog extends Animal {
        Dog() {
            name = getClass().getSimpleName();
            System.out.println("create:" + name);
        }
    }

    static Animal getAnimal() {
        return new Dog();
    }

    /**
     * map:将Observable发送的每个数据都按照给定的函数进行转化，
     * 并将转化后的数据发送出来
     */
    private static void map() {
        Observable.just(1, 2, 3)
                .map(new Func1<Integer, Integer>() {
                    @Override
                    public Integer call(Integer integer) {
                        return integer * 10;
                    }
                })
                .subscribe(new Action1<Integer>() {
                    @Override
                    public void call(Integer integer) {
                        System.out.println("map:" + integer);
                    }
                });
    }

    /**
     * groupBy:将源Observable发送的数据按照key来拆分成一些小的Observable，
     * 然后这些小的Observable分别发送其所包含的数据，
     * 需要提供一个生成key的规则，所有key相同的数据会包含在同一个小的Observable中
     */
    private static void groupBy() {
        Observable.just(1, 2, 3, 4, 5, 6, 7, 8, 9)
                .groupBy(new Func1<Integer, Integer>() {
                    @Override
                    public Integer call(Integer integer) {//实现奇数偶数分组
                        return integer % 2;
                    }
                })
                .subscribe(new Action1<GroupedObservable<Integer, Integer>>() {
                    @Override
                    public void call(final GroupedObservable<Integer, Integer> integerIntegerGroupedObservable) {
                        integerIntegerGroupedObservable.count().subscribe(new Action1<Integer>() {
                            @Override
                            public void call(Integer integer) {
                                if (integerIntegerGroupedObservable.getKey() == 0) {
                                    System.out.println("key" + integerIntegerGroupedObservable.getKey()
                                            + "\n" + "contains:" + integer + " numbers");
                                }
                            }
                        });
                    }
                });
        Observable.just(1, 2, 3, 4, 5, 6, 7, 8, 9)
                .groupBy(new Func1<Integer, Integer>() {
                    @Override
                    public Integer call(Integer integer) {
                        return integer % 2;
                    }
                }, new Func1<Integer, String>() {
                    @Override
                    public String call(Integer integer) {//实现奇数偶数分组，并添加一个字符串前缀
                        return "groupbyStringObservable:" + integer;
                    }
                })
                .subscribe(new Action1<GroupedObservable<Integer, String>>() {
                    @Override
                    public void call(GroupedObservable<Integer, String> integerStringGroupedObservable) {
                        if (integerStringGroupedObservable.getKey() == 0) {
                            integerStringGroupedObservable.subscribe(new Action1<String>() {
                                @Override
                                public void call(String s) {
                                    System.out.println(s);
                                }
                            });
                        }
                    }
                });

    }

    /**
     * flatmap:将数据用我们自己定义的规则转化后再发送出去
     * 原理：将这个Observable转化为多个以源Observab发送的数据作为源数据的Observable,
     * 然后将这多个Observable发送的数据整合并发送出来
     * 注意：顺序可能会有交错，要有序的话用concatmap
     * flatMapIterable转化的多个Observable是使用Iterable作为源数据的
     */
    private static void flatmap() {
        Observable.just(1, 2, 3)
                .flatMap(new Func1<Integer, Observable<String>>() {
                    @Override
                    public Observable<String> call(Integer integer) {
                        return Observable.just("flat map:" + integer);
                    }
                })
                .subscribe(new Action1<String>() {
                    @Override
                    public void call(String s) {
                        System.out.println(TAG + ":" + s);

                    }
                });
        Observable.just(1, 2, 3)
                .flatMapIterable(new Func1<Integer, Iterable<String>>() {
                    @Override
                    public Iterable<String> call(Integer integer) {
                        ArrayList<String> strings = new ArrayList<>();
                        //strings.add("flatMapIterable:" + integer);
                        for (int i = 0; i < 3; i++) {
                            strings.add("flatMapIterable:" + integer);
                        }
                        return strings;
                    }
                })
                .subscribe(new Action1<String>() {
                    @Override
                    public void call(String s) {
                        System.out.println(TAG + ":" + s);
                    }
                });
    }

    private static void inteval() {
        Observable.interval(1, TimeUnit.SECONDS)
                .observeOn(Schedulers.test())
                .subscribe(new Subscriber<Long>() {
                    @Override
                    public void onCompleted() {
                        System.out.println(TAG + "onCompleted");

                    }

                    @Override
                    public void onError(Throwable e) {
                        System.out.println(TAG + e.getMessage());

                    }

                    @Override
                    public void onNext(Long aLong) {
                        if (aLong == 10) {
                            unsubscribe();
                        }
                        System.out.println(TAG + "onNext:" + aLong);

                    }
                });
    }

    /**
     * from:接受一个对象(Itrable,Callable,Future和数组)作为参数来创建Observable
     * 与just不同的是，数据会依次发出，发送五次，而just是一次性发出
     */
    private static void from() {
        Integer[] arrays = {0, 1, 2, 3, 4, 5};
        List<Integer> list = new ArrayList<>();
        for (int i = 0; i < 5; i++) {
            list.add(i);
        }

        Observable.from(arrays)
                .subscribe(new Action1<Integer>() {
                    @Override
                    public void call(Integer integer) {
                        System.out.println(TAG + "FromArray:" + integer);

                    }
                });
        Observable.from(list)
                .subscribe(new Action1<Integer>() {
                    @Override
                    public void call(Integer integer) {
                        System.out.println(TAG + "FromItrable:" + integer);
                    }
                });

        Observable.just(arrays)
                .subscribe(new Action1<Integer[]>() {
                    @Override
                    public void call(Integer[] integers) {
                        System.out.println(TAG + "jsutArray:" + integers.length);
                    }
                });

    }

    /**
     * range
     * 发送一个范围内的数据
     */
    private static void range() {
        Observable.range(10, 5).subscribe(new Action1<Integer>() {
            @Override
            public void call(Integer integer) {
                System.out.println(TAG + integer);
            }
        });
    }

    /**
     * defer:每次订阅都会得到一个刚创建的最新的Observable对象，这样可以保证Observable对象里的数据是最新的
     * just:接受某个对象(数字、一个字符串、数组、Iterate对象等)作为输入，然后会创建一个发送该对象的Observable
     */
    private static void defer_just() {
        Observable<Long> deferObservable = Observable.defer(new Func0<Observable<Long>>() {
            @Override
            public Observable<Long> call() {
                return Observable.just(System.currentTimeMillis());
            }
        });
        Observable<Long> justObservable = Observable.just(System.currentTimeMillis());

        for (int i = 0; i < 5; i++) {
            deferObservable.subscribe(new Action1<Long>() {
                @Override
                public void call(Long aLong) {
                    /**
                     * 每次订阅都会得到Observable发送的一个全新的发送时间
                     */
                    System.out.println(TAG + "deferObservable:" + aLong);
                }
            });
            justObservable.subscribe(new Action1<Long>() {
                @Override
                public void call(Long aLong) {
                    /**
                     * 即使订阅多次也都会发送出和首次订阅一样的数据
                     */
                    System.out.println(TAG + "justObservable:" + aLong);

                }
            });
        }

    }


}
