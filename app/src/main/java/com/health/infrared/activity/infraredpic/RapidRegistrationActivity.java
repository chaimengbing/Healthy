package com.health.infrared.activity.infraredpic;

import android.content.Intent;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.health.infrared.R;
import com.health.infrared.activity.BaseActivity;
import com.health.infrared.adapter.RapidGriAdapter;
import com.health.infrared.commconfig.CommEventEntry;
import com.health.infrared.model.RapidRegostration;
import com.health.infrared.utils.DateUtil;

import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * Created by 123 on 2017/12/5.
 */

public class RapidRegistrationActivity extends BaseActivity {
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

    @BindView(R.id.rapid_img_1)
    ImageView rapidImageView1;
    @BindView(R.id.rapid_img_2)
    ImageView rapidImageView2;
    @BindView(R.id.rapid_img_3)
    ImageView rapidImageView3;
    @BindView(R.id.rapid_img_4)
    ImageView rapidImageView4;

    private List<RapidRegostration> rapidRegostrationList;
    private RapidGriAdapter rapidGriAdapter;

    @Override
    public int getLayoutView() {
        return R.layout.activity_raoid_registration;
    }

    @Override
    public void initData() {
        if (getIntent() != null) {
            homeName = getIntent().getStringExtra(CommEventEntry.HOME_NAME);
        }
//        rapidRegostrationList = new ArrayList<>();
//        RapidRegostration rapidRegostration = new RapidRegostration();
//        rapidRegostration.setImgId(R.mipmap.rapid_1);
//        rapidRegostrationList.add(rapidRegostration);
//
//        RapidRegostration rapidRegostration1 = new RapidRegostration();
//        rapidRegostration1.setImgId(R.mipmap.rapid_2);
//        rapidRegostrationList.add(rapidRegostration1);
//
//        RapidRegostration rapidRegostration2 = new RapidRegostration();
//        rapidRegostration2.setImgId(R.mipmap.rapid_3);
//        rapidRegostrationList.add(rapidRegostration2);
//
//        RapidRegostration rapidRegostration3 = new RapidRegostration();
//        rapidRegostration3.setImgId(R.mipmap.rapid_4);
//        rapidRegostrationList.add(rapidRegostration3);

//        RapidRegostration rapidRegostration4 = new RapidRegostration();
//        rapidRegostration4.setImgId(R.mipmap.rapid_5);
//        rapidRegostrationList.add(rapidRegostration4);
    }

    @OnClick(R.id.rapid_img_1)
    void rapidImg1Click() {
        rapidDetail(1);
    }

    @OnClick(R.id.rapid_img_2)
    void rapidImg2Click() {
        rapidDetail(2);
    }

    @OnClick(R.id.rapid_img_3)
    void rapidImg3Click() {
        rapidDetail(3);
    }

    @OnClick(R.id.rapid_img_4)
    void rapidImg4Click() {
        rapidDetail(4);
    }

    private void rapidDetail(int count) {
        Intent intent = new Intent(getApplicationContext(), RapidRegistrationDetailActivity.class);
        intent.putExtra(CommEventEntry.HOME_NAME, homeName);
        intent.putExtra(CommEventEntry.IMAGE_POSITION, count);
        startActivity(intent);
    }

    @Override
    public void initComponentViews() {
        initToolbar();
//        rapidGriAdapter = new RapidGriAdapter(getApplicationContext(), rapidRegostrationList);
//        raoidGridView.setAdapter(rapidGriAdapter);
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
