package com.health.infrared.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;


import com.orhanobut.logger.Logger;

import java.util.List;

/**
 */
public abstract class BaseFragment extends Fragment {
    private String TAG = BaseFragment.class.getName();

    protected View view;


    public BaseFragment() {
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Logger.d("onCreate");
        initData();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        Logger.d("onCreateView");
        view = inflater.inflate(getLayoutResId(), container, false);
        return view;
    }

    protected abstract int getLayoutResId();

    protected abstract int initData();


    @Override
    public void onDestroy() {
        super.onDestroy();
    }

    @Override
    public void onResume() {
        super.onResume();
    }


}
