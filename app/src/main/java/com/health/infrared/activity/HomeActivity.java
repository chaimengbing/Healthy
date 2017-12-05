package com.health.infrared.activity;

import android.content.Intent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.TextView;

import com.health.infrared.R;
import com.health.infrared.activity.infraredpic.RapidRegistrationActivity;
import com.health.infrared.activity.quarantine.CaseHandleActivity;
import com.health.infrared.activity.quarantine.CollectSampleActivity;
import com.health.infrared.activity.quarantine.InquireActivity;
import com.health.infrared.activity.quarantine.MedicalExaminationActivity;
import com.health.infrared.activity.quarantine.RegistrationActivity;
import com.health.infrared.adapter.HomeGriAdapter;
import com.health.infrared.adapter.MainGriAdapter;
import com.health.infrared.commconfig.CommEventEntry;
import com.health.infrared.model.HomeItem;
import com.health.infrared.model.MainItem;

import java.util.ArrayList;
import java.util.IllegalFormatCodePointException;
import java.util.List;

import butterknife.BindView;

public class HomeActivity extends BaseActivity {
    //title toolbar
    private String homeName = "";
    private MainItem mainItem;

    @BindView(R.id.main_gridview)
    GridView mainGridView;

    private HomeGriAdapter homeGriAdapter;
    private List<HomeItem> homeList;

    @Override
    public int getLayoutView() {
        return R.layout.activity_main;
    }

    @Override
    public void initData() {
        homeList = new ArrayList<>();
        if (getIntent() != null) {
            mainItem = (MainItem) getIntent().getSerializableExtra(CommEventEntry.MAIN_TYPE);
            if (mainItem != null) {
                homeName = mainItem.getName();
            }
        }
        if (mainItem != null) {
            if (mainItem.getType() == CommEventEntry.TYPE_INFAREDPIC) {
                initInfaredpic();
            } else if (mainItem.getType() == CommEventEntry.TYPE_QUARANTINE) {
                initQuarantine();
            }
        }
    }

    /**
     * 初始化红外图片首页
     */
    private void initInfaredpic() {
        //快速登记
        HomeItem homeItem = new HomeItem();
        homeItem.setType(CommEventEntry.INFAREDPIC_RAPID_REGISTRATION);
        homeItem.setImgId(R.mipmap.ic_launcher);
        homeItem.setName("快速登记");
        homeList.add(homeItem);

        //漏警处置
        HomeItem homeItem1 = new HomeItem();
        homeItem1.setType(CommEventEntry.INFAREDPIC_DISPOSITION);
        homeItem1.setImgId(R.mipmap.ic_launcher);
        homeItem1.setName("漏警处置");
        homeList.add(homeItem1);

        //警情浏览
        HomeItem homeItem2 = new HomeItem();
        homeItem2.setType(CommEventEntry.INFAREDPIC_ALERT_BROWSE);
        homeItem2.setImgId(R.mipmap.ic_launcher);
        homeItem2.setName("警情浏览");
        homeList.add(homeItem2);

        //追踪登记
        HomeItem homeItem3 = new HomeItem();
        homeItem3.setType(CommEventEntry.INFAREDPIC_REGISTRATION);
        homeItem3.setImgId(R.mipmap.ic_launcher);
        homeItem3.setName("追踪登记");
        homeList.add(homeItem3);
    }

    /**
     * 初始检疫首页
     */
    private void initQuarantine() {
        //查验登记
        HomeItem homeItem = new HomeItem();
        homeItem.setType(CommEventEntry.QUARANTINE_REGISTRATION);
        homeItem.setImgId(R.mipmap.ic_launcher);
        homeItem.setName("查验登记");
        homeList.add(homeItem);

        //流行病学调查
        HomeItem homeItem1 = new HomeItem();
        homeItem1.setType(CommEventEntry.QUARANTINE_INQUIRE);
        homeItem1.setImgId(R.mipmap.ic_launcher);
        homeItem1.setName("流行病学调查");
        homeList.add(homeItem1);

        //医学排查
        HomeItem homeItem2 = new HomeItem();
        homeItem2.setType(CommEventEntry.QUARANTINE_MEDIACAL_EXAMINA);
        homeItem2.setImgId(R.mipmap.ic_launcher);
        homeItem2.setName("医学排查");
        homeList.add(homeItem2);

        //病例处置
        HomeItem homeItem3 = new HomeItem();
        homeItem3.setType(CommEventEntry.QUARANTINE_CASE_HANDLE);
        homeItem3.setImgId(R.mipmap.ic_launcher);
        homeItem3.setName("病例处置");
        homeList.add(homeItem3);

        //采集样品
        HomeItem homeItem4 = new HomeItem();
        homeItem4.setType(CommEventEntry.QUARANTINE_COLLECT_SAMPLE);
        homeItem4.setImgId(R.mipmap.ic_launcher);
        homeItem4.setName("采集样品");
        homeList.add(homeItem4);
    }


    @Override
    public void initComponentViews() {
        initToolbar();
        homeGriAdapter = new HomeGriAdapter(getApplicationContext(), homeList);
        mainGridView.setAdapter(homeGriAdapter);
        mainGridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                HomeItem homeItem = (HomeItem) homeGriAdapter.getItem(position);
                Intent intent = null;
                switch (homeItem.getType()) {
                    case CommEventEntry.QUARANTINE_REGISTRATION:
                        intent = new Intent(getApplicationContext(), RegistrationActivity.class);
                        break;
                    case CommEventEntry.QUARANTINE_INQUIRE:
                        intent = new Intent(getApplicationContext(), InquireActivity.class);
                        break;
                    case CommEventEntry.QUARANTINE_MEDIACAL_EXAMINA:
                        intent = new Intent(getApplicationContext(), MedicalExaminationActivity.class);
                        break;
                    case CommEventEntry.QUARANTINE_CASE_HANDLE:
                        intent = new Intent(getApplicationContext(), CaseHandleActivity.class);
                        break;
                    case CommEventEntry.QUARANTINE_COLLECT_SAMPLE:
                        intent = new Intent(getApplicationContext(), CollectSampleActivity.class);
                        break;
                    case CommEventEntry.INFAREDPIC_RAPID_REGISTRATION:
                        intent = new Intent(getApplicationContext(), RapidRegistrationActivity.class);
                        break;
                    default:
                        break;
                }
                if (intent != null) {
                    intent.putExtra(CommEventEntry.HOME_NAME, homeItem.getName());
                    startActivity(intent);
                }
            }
        });
    }

    private void initToolbar() {
        backTextView.setText(R.string.back);
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
