package com.pse.rxandroid.course1;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.pse.rxandroid.R;

import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;

/**
 * Created by 泅渡者
 * Created on 2017/3/3.
 */

public class CourseOneActivity  extends Activity{
    private final static String TAG = CourseOneActivity.class.getName();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_course_1);
        creatObserver();
        creatObserverable();
    }

    /**
     * 创建Observerable
     */
    private Observable<Integer>  creatObserverable() {
        return  Observable.create(new ObservableOnSubscribe<Integer>() {
            @Override
            public void subscribe(ObservableEmitter<Integer> e) throws Exception {
                    e.onNext(1/0);
                    e.onComplete();
            }
        });
    }

    /**
     * 创建Observer
     */
    private  Observer<Integer> creatObserver() {
        return  new Observer<Integer>() {
            @Override
            public void onSubscribe(Disposable d) {
                Log.d("TAG","onSubscribe被执行");
            }

            @Override
            public void onNext(Integer s) {
                Log.d("TAG","onNext被执行:"+s);
            }

            @Override
            public void onError(Throwable e) {
                Log.d("TAG","onError被执行");
            }

            @Override
            public void onComplete() {
                Log.d("TAG","onComplete被执行");
            }
        };

    }

    public void onTest(View view) {
            Observable<Integer> observerable = creatObserverable();
            Observer<Integer> observer = creatObserver();
            observerable.subscribe(observer);
    }
}
