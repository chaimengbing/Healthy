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
import com.health.infrared.fragment.quarantine.CaseIdeaFragment;
import com.health.infrared.fragment.quarantine.ClinicalFragment;
import com.health.infrared.fragment.quarantine.EpidemiologicalFragment;
import com.health.infrared.utils.DateUtil;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

/**
 * 流行病学调查
 * Created by 123 on 2017/12/5.
 */

public class InquireActivity extends BaseActivity {
    //title toolbar
    @BindView(R.id.back_textview)
    TextView backTextView;
    @BindView(R.id.title_textview)
    TextView titleTextView;
    @BindView(R.id.right_textview)
    TextView rightTextView;
    private String homeName = "";
    @BindView(R.id.time_textview)
    TextView timeTextView;

    @BindView(R.id.inquire_tablayout)
    TabLayout tableLayout;
    @BindView(R.id.inquire_viewpager)
    ViewPager viewPager;

    private String[] functionArray = null;
    private List<Fragment> functionFragment = new ArrayList<>();

    @Override
    public int getLayoutView() {
        return R.layout.activity_inquire;
    }

    @Override
    public void initData() {
        functionArray = new String[]{getString(R.string.clinical), getString(R.string.epidemiological), getString(R.string.case_idea)};
        functionFragment.add(new ClinicalFragment());
        functionFragment.add(new EpidemiologicalFragment());
        functionFragment.add(new CaseIdeaFragment());
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

        if (timeTextView != null) {
            timeTextView.setText(DateUtil.getCurrentTime());
        }
    }
}
