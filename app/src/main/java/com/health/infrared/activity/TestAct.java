package com.health.infrared.activity;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.PopupWindow;
import android.widget.Spinner;
import android.widget.TextView;

import com.health.infrared.R;

/**
 * Created by 123 on 2017/12/4.
 */

public class TestAct extends BaseActivity {

    Spinner spinner;
    Button button;
    Button dialogButton;

    @Override
    public int getLayoutView() {
        return R.layout.activity_test;
    }

    @Override
    public void initData() {

    }

    @Override
    public void initComponentViews() {
        dialogButton = findViewById(R.id.dialog_button);
        dialogButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder alertDialog = new AlertDialog.Builder(TestAct.this);
                alertDialog.setTitle("标题").setMessage("确定么？").setPositiveButton("确定", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        showToast("点击确定");
                        dialog.dismiss();
                    }
                }).setNegativeButton("取消", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                });
                alertDialog.show();
            }
        });


        button = findViewById(R.id.popwindow_button);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final PopupWindow popupWindow = new PopupWindow(getApplicationContext());
                View view = LayoutInflater.from(getApplicationContext()).inflate(R.layout.item_popwindow, null);
                popupWindow.setContentView(view);
                popupWindow.setWidth(ViewGroup.LayoutParams.MATCH_PARENT);
                popupWindow.setHeight(ViewGroup.LayoutParams.WRAP_CONTENT);
                popupWindow.setOutsideTouchable(true);
                popupWindow.showAsDropDown(button);

                TextView close = view.findViewById(R.id.close);
                close.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        if (popupWindow != null) {
                            popupWindow.dismiss();
                        }
                    }
                });

            }
        });


        spinner = findViewById(R.id.spinner);
//        String[] test = getResources().getStringArray(R.array.test);
        final String[] test = new String[]{"qqq", "000", "111", "333", "888", "999", "qqq"};
        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(getApplicationContext(), R.layout.simple_spinner_item_test, test);
//        SpinnerAdapter

        spinner.setAdapter(arrayAdapter);
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                showToast(test[position]);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
    }
}
