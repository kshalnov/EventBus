package ru.gb.course1.myapplication;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import java.util.concurrent.Executor;

import io.reactivex.Observable;
import io.reactivex.Scheduler;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.internal.schedulers.SchedulerWhen;
import io.reactivex.schedulers.Schedulers;

public class SecondFragment extends Fragment {
    private CompositeDisposable disposable = new CompositeDisposable();
    private TextView counterTv;

    private int count = 0;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_second, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        counterTv = view.findViewById(R.id.counter_text_view);

        disposable.add(
                AppKt.getApp(this).getCounterBus().get()
                        .doOnNext(event -> {
                            Log.d("@@@", "doOnNext 1 " + Thread.currentThread().getName());
                        })
                        .doOnNext(event -> {
                            Log.d("@@@", "doOnNext 2 " + Thread.currentThread().getName());
                        })
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribe(event -> {
                            Log.d("@@@", "subscribe " + Thread.currentThread().getName());
                            Log.d("@@@", " ");
                            if (event instanceof PlusEvent) {
                                count++;
                            } else if (event instanceof MinusEvent) {
                                count--;
                            }
                            counterTv.setText(String.valueOf(count));
                        }));
    }

    @Override
    public void onDestroyView() {
        disposable.dispose();
        super.onDestroyView();
    }
}