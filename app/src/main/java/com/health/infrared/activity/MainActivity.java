package com.health.infrared.activity;

import android.content.Intent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.TextView;

import com.health.infrared.R;
import com.health.infrared.adapter.HomeGriAdapter;
import com.health.infrared.commconfig.CommEventEntry;
import com.health.infrared.model.HomeItem;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

public class MainActivity extends BaseActivity {
    //title toolbar
    @BindView(R.id.back_textview)
    TextView backTextView;
    @BindView(R.id.title_textview)
    TextView titleTextView;
    @BindView(R.id.right_textview)
    TextView rightTextView;

    @BindView(R.id.home_gridview)
    GridView homeGridView;

    private HomeGriAdapter homeGriAdapter;
    private List<HomeItem> homeList;

    @Override
    public int getLayoutView() {
        return R.layout.activity_main;
    }

    @Override
    public void initData() {
        homeList = new ArrayList<>();

        //红外图片
        HomeItem homeItem = new HomeItem();
        homeItem.setImgId(R.mipmap.ic_launcher);
        homeItem.setName(getString(R.string.Infrared_pic));
        homeList.add(homeItem);

        //申报验放
        HomeItem homeItem1 = new HomeItem();
        homeItem1.setImgId(R.mipmap.ic_launcher);
        homeItem1.setName(getString(R.string.declare));
        homeList.add(homeItem1);

        //检疫查验
        HomeItem homeItem2 = new HomeItem();
        homeItem2.setImgId(R.mipmap.ic_launcher);
        homeItem2.setName(getString(R.string.quarantine));
        homeList.add(homeItem2);

        //检测结果
        HomeItem homeItem3 = new HomeItem();
        homeItem3.setImgId(R.mipmap.ic_launcher);
        homeItem3.setName(getString(R.string.test_result));
        homeList.add(homeItem3);

        //个案溯源
        HomeItem homeItem4 = new HomeItem();
        homeItem4.setImgId(R.mipmap.ic_launcher);
        homeItem4.setName(getString(R.string.singal_case));
        homeList.add(homeItem4);

        //疫情浏览
        HomeItem homeItem5 = new HomeItem();
        homeItem5.setImgId(R.mipmap.ic_launcher);
        homeItem5.setName(getString(R.string.browsing));
        homeList.add(homeItem5);

        //学习园地
        HomeItem homeItem6 = new HomeItem();
        homeItem6.setImgId(R.mipmap.ic_launcher);
        homeItem6.setName(getString(R.string.study_place));
        homeList.add(homeItem6);

        //工作日志
        HomeItem homeItem7 = new HomeItem();
        homeItem7.setImgId(R.mipmap.ic_launcher);
        homeItem7.setName(getString(R.string.work_log));
        homeList.add(homeItem7);

        //查询统计
        HomeItem homeItem8 = new HomeItem();
        homeItem8.setImgId(R.mipmap.ic_launcher);
        homeItem8.setName(getString(R.string.query_statistics));
        homeList.add(homeItem8);
//        for (int i = 0; i < 9; i++) {
//            HomeItem homeItem = new HomeItem();
//            homeItem.setImgId(R.mipmap.ic_launcher);
//            homeItem.setName("红外图片");
//            homeList.add(homeItem);
//        }
    }

    @Override
    public void initComponentViews() {
        initToolbar();
        homeGriAdapter = new HomeGriAdapter(getApplicationContext(), homeList);
        homeGridView.setAdapter(homeGriAdapter);
        homeGridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                HomeItem homeItem = (HomeItem) homeGriAdapter.getItem(position);
                if (position == 2) {
                    Intent intent = new Intent(getApplicationContext(), FunctionActivity.class);
                    intent.putExtra(CommEventEntry.HOME_NAME, homeItem.getName());
                    startActivity(intent);
                } else {
                    showToast(homeItem.getName() + " 功能尚未开放");
                }
            }
        });
    }

    private void initToolbar() {
        backTextView.setVisibility(View.GONE);
        titleTextView.setText(R.string.main_home);
        rightTextView.setVisibility(View.GONE);
    }

}
