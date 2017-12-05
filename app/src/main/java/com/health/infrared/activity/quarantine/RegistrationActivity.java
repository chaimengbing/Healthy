package com.health.infrared.activity.quarantine;

import android.Manifest;
import android.annotation.SuppressLint;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.net.Uri;
import android.os.Environment;
import android.provider.MediaStore;
import android.provider.Settings;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.text.TextUtils;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.PopupWindow;
import android.widget.TextView;
import android.widget.Toast;

import com.health.infrared.R;
import com.health.infrared.activity.BaseActivity;
import com.health.infrared.commconfig.CommEventEntry;
import com.health.infrared.utils.DateUtil;
import com.health.infrared.utils.FileUtils;
import com.health.infrared.utils.ToolPicture;
import com.orhanobut.logger.Logger;
import com.yalantis.ucrop.UCrop;

import java.io.File;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * 查验登记
 * Created by 123 on 2017/12/5.
 */

public class RegistrationActivity extends BaseActivity implements View.OnClickListener {

    private static final String TAG = RegistrationActivity.class.getSimpleName();

    private static final int CAMERA_REQUEST_CODE = 1000;
    private String tempImageName = null;
    private Bitmap bitmap = null;

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

    @BindView(R.id.registration_imageview)
    ImageView registrationImageView;
    private PopupWindow popupWindow;

    @Override
    public int getLayoutView() {
        return R.layout.activity_registration;
    }

    @Override
    public void initData() {

    }

    @OnClick(R.id.registration_imageview)
    void regiImageOnclick() {
        initPopupWindow();
    }


    private void takePhoto() {
        //判断是否获取了照相的权限
        int checkCapturePermisson = ContextCompat.checkSelfPermission(getApplicationContext(), Manifest.permission.CAMERA);

        //直接加上获取SD的权限
        int checkSdcardPermisson = ContextCompat.checkSelfPermission(getApplicationContext(), Manifest.permission.WRITE_EXTERNAL_STORAGE);

        if (checkCapturePermisson != PackageManager.PERMISSION_GRANTED || checkSdcardPermisson != PackageManager.PERMISSION_GRANTED) {
            //如果这样的话 就是尽量在点击拍照的时候 把权限都吊起来
            ActivityCompat.requestPermissions(RegistrationActivity.this, new String[]{Manifest.permission.CAMERA, Manifest.permission.WRITE_EXTERNAL_STORAGE}, 1);
        } else {
            takeCamera();
        }
    }

    private void takeCamera() {
        if (FileUtils.hasSDCard()) {
            FileUtils.isExist(Environment.getExternalStorageDirectory() + "/BabyImage");
        } else {
            FileUtils.isExist(Environment.getDataDirectory() + "/BabyImage");
        }
        Logger.i(TAG, "是否有SD卡 =" + FileUtils.hasSDCard() + ",是否有目录=" + FileUtils.isExistPath(Environment.getExternalStorageDirectory() + "/BabyImage"));
        Intent openCameraIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        tempImageName = String.valueOf(System.currentTimeMillis());
        openCameraIntent.putExtra(MediaStore.EXTRA_OUTPUT, FileUtils.getOutputPicUri(tempImageName));
        openCameraIntent.putExtra(MediaStore.Images.Media.ORIENTATION, 0);
        ResolveInfo reInfo = getPackageManager().resolveActivity(openCameraIntent, PackageManager.MATCH_DEFAULT_ONLY);
        if (reInfo == null) {
            Toast.makeText(this, "请先安装相机", Toast.LENGTH_SHORT).show();
            return;
        }
        startActivityForResult(openCameraIntent, CAMERA_REQUEST_CODE);

        if (popupWindow != null) {
            popupWindow.dismiss();
        }
    }

    public void initPopupWindow() {

        View outerView = LayoutInflater.from(getApplicationContext()).inflate(R.layout.user_pic_chose_layer, null);
        if (popupWindow != null) {
            popupWindow.dismiss();
        } else {
            popupWindow = new PopupWindow(outerView, ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT, true);
        }

        //拍照
        TextView tv_take_pic = outerView.findViewById(R.id.tv_take_pic);
        tv_take_pic.setOnClickListener(this);
        //取消按钮
        TextView cacle = outerView.findViewById(R.id.tv_cancle);
        cacle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                popupWindow.dismiss();
            }
        });

        //popupwindow消失将屏幕亮度还原
        popupWindow.setOnDismissListener(new PopupWindow.OnDismissListener() {
            @Override
            public void onDismiss() {
                //设置透明度
                WindowManager.LayoutParams lp = getWindow().getAttributes();
                lp.alpha = 1.0f;
                getWindow().setAttributes(lp);
                //设置黑暗度
                WindowManager.LayoutParams lp2 = getWindow().getAttributes();
                lp2.dimAmount = 1.0f;
                getWindow().setAttributes(lp2);
                getWindow().addFlags(WindowManager.LayoutParams.FLAG_DIM_BEHIND);
            }
        });

        // 使其聚集
        popupWindow.setFocusable(true);
        // 设置允许在外点击消失
        // popupWindow.setOutsideTouchable(true);
        // 这个是为了点击“返回Back”也能使其消失，并且并不会影响你的背景
        popupWindow.setBackgroundDrawable(new BitmapDrawable());
        popupWindow.setAnimationStyle(R.style.main_menu_animstyle);
        popupWindow.showAtLocation(registrationImageView, Gravity.BOTTOM, 0, 0);
    }

    @Override
    public void initComponentViews() {
        if (getIntent() != null) {
            homeName = getIntent().getStringExtra(CommEventEntry.HOME_NAME);
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
        rightTextView.setText(R.string.entry);

        rightTextView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        if (timeTextView != null) {
            timeTextView.setText(DateUtil.getCurrentTime());
        }
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.tv_take_pic:
                takePhoto();
                break;
            default:
                break;
        }
    }


    private void startCropActivity(@NonNull Uri uri) {
        UCrop uCrop = UCrop.of(uri, FileUtils.getOutputPicUri("SampleCropImage")).withAspectRatio(16, 9).withMaxResultSize(500, 500);
        uCrop = advancedConfig(uCrop);
        uCrop.start(RegistrationActivity.this);
    }

    private UCrop advancedConfig(@NonNull UCrop uCrop) {
        UCrop.Options options = new UCrop.Options();
        options.setCompressionFormat(Bitmap.CompressFormat.JPEG);
        options.setCompressionQuality(100);
        options.setHideBottomControls(false);
        options.setFreeStyleCropEnabled(true);

        options.setToolbarColor(ContextCompat.getColor(this, R.color.black));
        return uCrop.withOptions(options);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (resultCode != RESULT_CANCELED) {
            Uri uri = null;
            switch (requestCode) {
                case CAMERA_REQUEST_CODE:
                    if (data != null) {
                        uri = data.getData();
                    } else {
                        uri = FileUtils.getOutputPicUri(tempImageName);
                    }
                    if (uri == null) {
                        return;
                    }
                    startCropActivity(uri);
                    break;
                case UCrop.REQUEST_CROP:
                    try {
                        if (data != null) {
                            if (uri == null) {
                                uri = UCrop.getOutput(data);    //得到剪裁后的图片uri
                            }
                            if (uri == null) {
                                uri = FileUtils.getOutputPicUri(tempImageName);
                            }

                            try {
                                bitmap = ToolPicture.decodeUriAsBitmap(this, uri);
                            } catch (OutOfMemoryError e) {
                                e.printStackTrace();
                            }
                            if (bitmap == null) {
                                // 如果实例化失败 返回默认的Bitmap对象
                                Bitmap mitmap = BitmapFactory.decodeResource(getResources(), R.mipmap.ic_launcher);
                                bitmap = mitmap;
                            }
                            if (bitmap != null) {
                                registrationImageView.setImageBitmap(bitmap);
                            }

                        }
                    } catch (Exception e) {
                        e.printStackTrace();
                    }

                    break;
            }
        }
        super.onActivityResult(requestCode, resultCode, data);
    }

    @SuppressLint("WrongConstant")
    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        switch (requestCode) {
            case 1:
                if (grantResults == null || grantResults.length == 0) {
                    return;
                }
                if (grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    takeCamera();
                } else {
                    // 请求被拒绝，提示用户
                    android.support.v7.app.AlertDialog.Builder builder = new android.support.v7.app.AlertDialog.Builder(this);
                    builder.setTitle("权限申请");
                    builder.setMessage("在设置-应用-" + getString(R.string.app_name) + "-权限中开启相机权限,以正常使用拍照等功能");
                    builder.setPositiveButton("确定", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            Intent intent = new Intent(Settings.ACTION_APPLICATION_SETTINGS);
                            RegistrationActivity.this.startActivityForResult(intent, 0);
                        }
                    });
                    builder.setNegativeButton("取消", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            return;
                        }
                    });
                    builder.show();
                }
                break;
            case 2:
                if (grantResults == null || grantResults.length == 0) {
                    return;
                }
                if (grantResults[0] != PackageManager.PERMISSION_GRANTED) {
                    // 请求被拒绝，提示用户
                    android.support.v7.app.AlertDialog.Builder builder = new android.support.v7.app.AlertDialog.Builder(this);
                    builder.setTitle("权限申请");
                    builder.setMessage("在设置-应用-" + getString(R.string.app_name) + "-权限中开启定位权限,以正常使用定位等功能");
                    builder.setPositiveButton("确定", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            Intent intent = new Intent(Settings.ACTION_APPLICATION_SETTINGS);
                            RegistrationActivity.this.startActivityForResult(intent, 0);
                        }
                    });
                    builder.setNegativeButton("取消", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            return;
                        }
                    });
                    builder.show();
                }
                break;
            default:
                break;

        }
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
    }
}
