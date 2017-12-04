package com.health.infrared.activity;

import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.widget.TextView;

import com.health.infrared.R;
import com.health.infrared.adapter.TabFragmentPagerAdapter;
import com.health.infrared.commconfig.CommEventEntry;
import com.health.infrared.fragment.QuarantineFragment;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

/**
 * Created by 123 on 2017/12/4.
 */

public class FunctionActivity extends BaseActivity {

    //title toolbar
    @BindView(R.id.back_textview)
    TextView backTextView;
    @BindView(R.id.title_textview)
    TextView titleTextView;
    @BindView(R.id.right_textview)
    TextView rightTextView;
    private String functionName = "";

    @BindView(R.id.function_viewpager)
    ViewPager functionViewPager;
    @BindView(R.id.tablayout)
    android.support.design.widget.TabLayout tableLayout;

    //采集样品
    private String[] functionArray = new String[]{"检查登记", "流行病毒调查", "医学排查", "病例处置","采集样品"};
    private List<Fragment> functionFragment = new ArrayList<>();

    @Override
    public int getLayoutView() {
        return R.layout.activity_function;
    }

    @Override
    public void initData() {
        if (getIntent() != null) {
            functionName = getIntent().getStringExtra(CommEventEntry.HOME_NAME);
        }
        functionFragment.add(new QuarantineFragment());
        functionFragment.add(new QuarantineFragment());
        functionFragment.add(new QuarantineFragment());
        functionFragment.add(new QuarantineFragment());
        functionFragment.add(new QuarantineFragment());
    }

    @Override
    public void initComponentViews() {
        initToolbar();
        TabFragmentPagerAdapter tabFragmentPagerAdapter = new TabFragmentPagerAdapter(tableLayout, functionViewPager, getSupportFragmentManager(), functionArray, functionFragment);
        functionViewPager.setAdapter(tabFragmentPagerAdapter);
    }

    private void initToolbar() {
        backTextView.setText(getString(R.string.back));
        backTextView.setVisibility(View.VISIBLE);
        backTextView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        titleTextView.setText(functionName);
        rightTextView.setVisibility(View.GONE);
    }
}