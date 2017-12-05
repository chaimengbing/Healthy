package com.health.infrared.activity.quarantine;

import android.view.View;
import android.widget.TextView;

import com.health.infrared.R;
import com.health.infrared.activity.BaseActivity;
import com.health.infrared.commconfig.CommEventEntry;

import butterknife.BindView;

/**
 * 病例处置
 * Created by 123 on 2017/12/5.
 */

public class CaseHandleActivity extends BaseActivity {
    //title toolbar
    @BindView(R.id.back_textview)
    TextView backTextView;
    @BindView(R.id.title_textview)
    TextView titleTextView;
    @BindView(R.id.right_textview)
    TextView rightTextView;
    private String homeName = "";

    @Override
    public int getLayoutView() {
        return R.layout.activity_registration;
    }

    @Override
    public void initData() {

    }

    @Override
    public void initComponentViews() {
        if (getIntent() != null) {
            homeName =  getIntent().getStringExtra(CommEventEntry.HOME_NAME);
        }
        initToolbar();
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
