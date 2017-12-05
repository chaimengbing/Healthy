package com.health.infrared.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.health.infrared.R;
import com.health.infrared.model.RapidRegostration;
import com.orhanobut.logger.Logger;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by 123 on 2017/12/4.
 */

public class RapidGriAdapter extends Base<RapidRegostration> {

    public RapidGriAdapter(Context context, List<RapidRegostration> list) {
        super(context, list);
    }

    @Override
    public View createView(int position, View convertView, ViewGroup parent) {
        try {
            ViewHolder viewHolder = null;
            if (convertView == null) {
                convertView = inflater.inflate(R.layout.item_rapid_view, null);
                viewHolder = new ViewHolder();
                ButterKnife.bind(viewHolder, convertView);
                convertView.setTag(viewHolder);
            } else {
                viewHolder = (ViewHolder) convertView.getTag();
            }
            RapidRegostration rapidRegostration = list.get(position);
            viewHolder.imageView.setImageResource(rapidRegostration.getImgId());
        } catch (Exception e) {
            Logger.e(e.toString());
        }

        return convertView;
    }

    class ViewHolder {
        @BindView(R.id.rapid_img)
        ImageView imageView;
    }
}
