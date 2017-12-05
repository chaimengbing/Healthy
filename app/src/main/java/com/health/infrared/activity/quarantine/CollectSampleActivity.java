package com.health.infrared.activity.quarantine;

import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.widget.TextView;

import com.health.infrared.R;
import com.health.infrared.activity.BaseActivity;
import com.health.infrared.adapter.TabFragmentPagerAdapter;
import com.health.infrared.commconfig.CommEventEntry;
import com.health.infrared.fragment.quarantine.ClinicalFeatureFragment;
import com.health.infrared.fragment.quarantine.CollectionStandFragment;
import com.health.infrared.fragment.quarantine.FirstVisitHandleFragment;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

/**
 * 采集样品
 * Created by 123 on 2017/12/5.
 */

public class CollectSampleActivity extends BaseActivity {
    //title toolbar
    @BindView(R.id.back_textview)
    TextView backTextView;
    @BindView(R.id.title_textview)
    TextView titleTextView;
    @BindView(R.id.right_textview)
    TextView rightTextView;
    private String homeName = "";

    @BindView(R.id.collect_tablayout)
    TabLayout tableLayout;
    @BindView(R.id.collect_viewpager)
    ViewPager viewPager;

    private String[] functionArray = null;
    private List<Fragment> functionFragment = new ArrayList<>();

    @Override
    public int getLayoutView() {
        return R.layout.activity_collect_sample;
    }

    @Override
    public void initData() {
        functionArray = new String[]{getString(R.string.collection_stand), getString(R.string.clinical_feature), getString(R.string.first_visit_handle)};
        functionFragment.add(new CollectionStandFragment());
        functionFragment.add(new ClinicalFeatureFragment());
        functionFragment.add(new FirstVisitHandleFragment());
    }

    @Override
    public void initComponentViews() {
        if (getIntent() != null) {
            homeName = getIntent().getStringExtra(CommEventEntry.HOME_NAME);
        }
        initToolbar();
        viewPager.setAdapter(new TabFragmentPagerAdapter(tableLayout, viewPager, getSupportFragmentManager(), functionArray, functionFragment));
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
        titleTextView.setText(homeName);
        rightTextView.setVisibility(View.GONE);
    }
}
