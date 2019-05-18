package com.example.base.View;

import android.os.Bundle;
import android.util.Log;

import com.example.base.Base.BaseActivity;
import com.example.base.CostoumUtils.ActivityUtils;
import com.example.base.CostoumUtils.SPUtil;
import com.example.base.R;

public class SplashActivity extends BaseActivity {

    public static String SHARE_IS_FIRST = "share_is_first";
    public static Boolean IS_FIRST = true;
    private static final String TAG = "SplashActivity";



    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.d(TAG, "onCreate: ");
        initView();

    }
    @Override
    public void requestPermissionsSuccess() {
        initView();

    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_main;
    }

    @Override
    public void initView() {
        super.initView();
        getToolbarTitle().setText("SplashActivity");

        IS_FIRST = (boolean) SPUtil.getInstance().get(SHARE_IS_FIRST, IS_FIRST);


       if (IS_FIRST) {
           ActivityUtils.toNextActivity(this, WelcomActivity.class);
           finish();

        } else {

            ActivityUtils.toNextActivity(this, FirstActivity.class);
            finish();

        }
    }

    @Override
    public void initData() {
        super.initData();


    }
}




