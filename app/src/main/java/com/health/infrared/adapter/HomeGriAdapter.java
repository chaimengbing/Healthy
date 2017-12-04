package com.health.infrared.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.health.infrared.R;
import com.health.infrared.model.HomeItem;
import com.orhanobut.logger.Logger;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by 123 on 2017/12/4.
 */

public class HomeGriAdapter extends Base<HomeItem> {

    public HomeGriAdapter(Context context, List<HomeItem> list) {
        super(context, list);
    }

    @Override
    public View createView(int position, View convertView, ViewGroup parent) {
        try {
            ViewHolder viewHolder = null;
            if (convertView == null) {
                convertView = inflater.inflate(R.layout.item_home_view, null);
                viewHolder = new ViewHolder();
                ButterKnife.bind(viewHolder, convertView);
                convertView.setTag(viewHolder);
            } else {
                viewHolder = (ViewHolder) convertView.getTag();
            }
            HomeItem homeItem = list.get(position);
            viewHolder.titleImageView.setImageResource(homeItem.getImgId());
            viewHolder.titleTextView.setText(homeItem.getName());
        } catch (Exception e) {
            Logger.e(e.toString());
        }

        return convertView;
    }

    class ViewHolder {
        @BindView(R.id.title_img)
        ImageView titleImageView;
        @BindView(R.id.title_textview)
        TextView titleTextView;
    }
}
