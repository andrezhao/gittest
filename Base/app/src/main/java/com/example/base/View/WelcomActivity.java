package com.example.base.View;

import android.content.DialogInterface;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.example.base.Base.BaseActivity;
import com.example.base.CostoumUtils.AlertDialogUtil;
import com.example.base.R;

public class WelcomActivity extends BaseActivity implements View.OnClickListener {

    private static final String TAG = "WelcomActivity";

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initView();
    }

    @Override
    public void initView() {
        super.initView();
        getToolbarTitle().setText("WelcomActivity");
        Log.d(TAG, "initView: ");
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_welcom;
    }
    @Override
    public void onClick(View v) {
        super.onClick(v);
    }


    @Override
    public void onNetChange(boolean netWorkState) {
        super.onNetChange(netWorkState);


            AlertDialogUtil.getInstance(WelcomActivity.this).showAlertDialog(R.string.dialog_title, R.string.dialog_message, R.string.dialog_positive_button, R.string.dialog_negative_button, new DialogInterface.OnClickListener(){


                @Override
                public void onClick(DialogInterface dialog, int which) {

                }
            }, new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {

                }
            });

        }
    }
