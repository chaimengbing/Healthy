package com.health.infrared.activity;

import android.content.Intent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.TextView;

import com.health.infrared.R;
import com.health.infrared.adapter.MainGriAdapter;
import com.health.infrared.commconfig.CommEventEntry;
import com.health.infrared.model.MainItem;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

public class MainActivity extends BaseActivity {

    @BindView(R.id.main_gridview)
    GridView mainGridView;

    private MainGriAdapter mainGriAdapter;
    private List<MainItem> mainList;

    @Override
    public int getLayoutView() {
        return R.layout.activity_main;
    }

    @Override
    public void initData() {
        mainList = new ArrayList<>();

        //红外图片
        MainItem mainItem = new MainItem();
        mainItem.setType(CommEventEntry.TYPE_INFAREDPIC);
        mainItem.setImgId(R.mipmap.hongwaitupian);
        mainItem.setName(getString(R.string.Infrared_pic));
        mainList.add(mainItem);

        //申报验放
        MainItem mainItem1 = new MainItem();
        mainItem1.setImgId(R.mipmap.shenbaoyanshou);
        mainItem1.setName(getString(R.string.declare));
        mainList.add(mainItem1);

        //检疫查验
        MainItem mainItem2 = new MainItem();
        mainItem2.setType(CommEventEntry.TYPE_QUARANTINE);
        mainItem2.setImgId(R.mipmap.jianyichayan);
        mainItem2.setName(getString(R.string.quarantine));
        mainList.add(mainItem2);

        //检测结果
        MainItem mainItem3 = new MainItem();
        mainItem3.setImgId(R.mipmap.jianchajeguo);
        mainItem3.setName(getString(R.string.test_result));
        mainList.add(mainItem3);

        //个案溯源
        MainItem mainItem4 = new MainItem();
        mainItem4.setImgId(R.mipmap.geansuyuan);
        mainItem4.setName(getString(R.string.singal_case));
        mainList.add(mainItem4);

        //疫情浏览
        MainItem mainItem5 = new MainItem();
        mainItem5.setImgId(R.mipmap.wenqingliulan);
        mainItem5.setName(getString(R.string.browsing));
        mainList.add(mainItem5);

        //学习园地
        MainItem mainItem6 = new MainItem();
        mainItem6.setImgId(R.mipmap.xuexiyuandi);
        mainItem6.setName(getString(R.string.study_place));
        mainList.add(mainItem6);

        //工作日志
        MainItem mainItem7 = new MainItem();
        mainItem7.setImgId(R.mipmap.gongzuorizhi);
        mainItem7.setName(getString(R.string.work_log));
        mainList.add(mainItem7);

        //查询统计
        MainItem mainItem8 = new MainItem();
        mainItem8.setImgId(R.mipmap.chaxuntongji);
        mainItem8.setName(getString(R.string.query_statistics));
        mainList.add(mainItem8);
    }

    @Override
    public void initComponentViews() {
        initToolbar();
        mainGriAdapter = new MainGriAdapter(getApplicationContext(), mainList);
        mainGridView.setAdapter(mainGriAdapter);
        mainGridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                MainItem mainItem = (MainItem) mainGriAdapter.getItem(position);
                if (position == 2 || position == 0) {
                    Intent intent = new Intent(getApplicationContext(), HomeActivity.class);
                    intent.putExtra(CommEventEntry.MAIN_TYPE, mainItem);
                    startActivity(intent);
                } else {
                    showToast(mainItem.getName() + " 功能尚未开放");
                }
            }
        });
    }

    private void initToolbar() {
        backTextView.setVisibility(View.GONE);
        titleTextView.setText(R.string.system_name);
        rightTextView.setVisibility(View.GONE);
    }

    @Override
    public void onBackPressed() {
        return;
    }

}
