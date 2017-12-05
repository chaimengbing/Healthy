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
import com.health.infrared.fragment.quarantine.CheckBodyFragment;
import com.health.infrared.fragment.quarantine.CheckLaboratoryFragment;
import com.health.infrared.fragment.quarantine.DisinfectedStandFragment;
import com.health.infrared.fragment.quarantine.FirstVisitCaseHandleFragment;
import com.health.infrared.fragment.quarantine.HospitalStandFragment;
import com.health.infrared.fragment.quarantine.PatientsFragment;
import com.health.infrared.fragment.quarantine.SamplingSituationFragment;
import com.health.infrared.fragment.quarantine.SuperviseStandFragment;
import com.health.infrared.utils.DateUtil;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

/**
 * 医学排查
 * Created by 123 on 2017/12/5.
 */

public class MedicalExaminationActivity extends BaseActivity {
    //title toolbar
    @BindView(R.id.back_textview)
    TextView backTextView;
    @BindView(R.id.title_textview)
    TextView titleTextView;
    @BindView(R.id.right_textview)
    TextView rightTextView;
    @BindView(R.id.time_textview)
    TextView timeTextView;
    private String homeName = "";

    @BindView(R.id.examina_tablayout)
    TabLayout tableLayout;
    @BindView(R.id.examina_viewpager)
    ViewPager viewPager;

    private String[] functionArray = null;
    private List<Fragment> functionFragment = new ArrayList<>();

    @Override
    public int getLayoutView() {
        return R.layout.activity_medical_examination;
    }

    @Override
    public void initData() {

        functionArray = new String[]{getString(R.string.patients), getString(R.string.check_hody), getString(R.string.sampling_situation),
                getString(R.string.check_laboratory), getString(R.string.first_visit_case_handle), getString(R.string.hospital_stand),
                getString(R.string.supervise_stand), getString(R.string.disinfected_stand)};

        functionFragment = new ArrayList<>();
        functionFragment.add(new PatientsFragment());
        functionFragment.add(new CheckBodyFragment());
        functionFragment.add(new SamplingSituationFragment());
        functionFragment.add(new CheckLaboratoryFragment());
        functionFragment.add(new FirstVisitCaseHandleFragment());
        functionFragment.add(new HospitalStandFragment());
        functionFragment.add(new SuperviseStandFragment());
        functionFragment.add(new DisinfectedStandFragment());
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
