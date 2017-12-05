package com.health.infrared.activity.infraredpic;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.health.infrared.R;
import com.health.infrared.activity.BaseActivity;
import com.health.infrared.commconfig.CommEventEntry;
import com.health.infrared.utils.DateUtil;

import butterknife.BindView;

/**
 * Created by 123 on 2017/12/5.
 */

public class RapidRegistrationDetailActivity extends BaseActivity {
    //title toolbar
    @BindView(R.id.back_textview)
    TextView backTextView;
    @BindView(R.id.title_textview)
    TextView titleTextView;
    @BindView(R.id.right_textview)
    TextView rightTextView;
    private String homeName = "";
    private int countImg;
    @BindView(R.id.time_textview)
    TextView timeTextView;

    @BindView(R.id.rapid_detal_img)
    ImageView rapidDetailImageView;


    @Override
    public int getLayoutView() {
        return R.layout.activity_raoid_registration_detail;
    }

    @Override
    public void initData() {
        if (getIntent() != null) {
            homeName = getIntent().getStringExtra(CommEventEntry.HOME_NAME);
            countImg = getIntent().getIntExtra(CommEventEntry.IMAGE_POSITION, 1);
        }
    }


    @Override
    public void initComponentViews() {
        initToolbar();
        if (countImg == 1) {
            rapidDetailImageView.setImageResource(R.mipmap.rapid_1);
        } else if (countImg == 2) {
            rapidDetailImageView.setImageResource(R.mipmap.rapid_2);
        } else if (countImg == 3) {
            rapidDetailImageView.setImageResource(R.mipmap.rapid_3);
        } else if (countImg == 4) {
            rapidDetailImageView.setImageResource(R.mipmap.rapid_4);
        }
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
