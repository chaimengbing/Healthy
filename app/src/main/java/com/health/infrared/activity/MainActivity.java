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
        for (int i = 0; i < 9; i++) {
            HomeItem homeItem = new HomeItem();
            homeItem.setImgId(R.mipmap.ic_launcher);
            homeItem.setName("红外图片");
            homeList.add(homeItem);
        }
    }

    @Override
    public void initComponentViews() {
        initToolbar();
        homeGriAdapter = new HomeGriAdapter(getApplicationContext(), homeList);
        homeGridView.setAdapter(homeGriAdapter);
        homeGridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
//                Intent intent = new Intent(getApplicationContext(), TestAct.class);
//                startActivity(intent);
                Intent intent = new Intent(getApplicationContext(), FunctionActivity.class);
                HomeItem homeItem = (HomeItem) homeGriAdapter.getItem(position);
                intent.putExtra(CommEventEntry.HOME_NAME, homeItem.getName());
                startActivity(intent);
            }
        });
    }

    private void initToolbar() {
        backTextView.setVisibility(View.GONE);
        titleTextView.setText(R.string.main_home);
        rightTextView.setVisibility(View.GONE);
    }

}
