package com.health.infrared.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Gravity;
import android.view.View.OnClickListener;
import android.widget.Toast;

import com.orhanobut.logger.Logger;

import butterknife.ButterKnife;


/**
 * 说明：父类的Activity
 */
public abstract class BaseActivity extends AppCompatActivity {
    private static final String TAG = BaseActivity.class.getName();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        try {
            super.onCreate(savedInstanceState);
            setContentView(getLayoutView());
            ButterKnife.bind(this);
            initData();
            initComponentViews();
        } catch (Exception e) {
            Logger.e(e.toString());
        }
    }

    /**
     * 方法说明 : 初始化页面的布局
     */
    public abstract int getLayoutView();

    /**
     * 方法说明 : 初始化页面显示数据
     */
    public abstract void initData();

    /**
     * 方法说明 : 初始化页面控件的布局
     */
    public abstract void initComponentViews();

    /**
     * 显示用户提示
     */
    public void showToast(String message) {
        Toast toast = Toast.makeText(getApplicationContext(), message, Toast.LENGTH_SHORT);
        toast.setGravity(Gravity.CENTER, 0, 0);
        toast.setText(message);
        toast.show();
    }

    @Override
    protected void onResume() {
        super.onResume();
    }

    @Override
    public void onBackPressed() {
        return;
    }


}
