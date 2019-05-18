package com.example.base.Base;

import android.Manifest;
import android.content.DialogInterface;
import android.content.IntentFilter;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.LayoutRes;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.base.CostoumUtils.ActivityUtils;
import com.example.base.CostoumUtils.AlertDialogUtil;
import com.example.base.CostoumUtils.NetUtil;
import com.example.base.CostoumUtils.PermissionHelper;
import com.example.base.CostoumUtils.PermissionInterface;
import com.example.base.R;
import com.example.base.Request.NetBroadcastReceiver;

public class BaseActivity extends AppCompatActivity implements View.OnClickListener, NetBroadcastReceiver.NetChangeListener, PermissionInterface {
   private  LinearLayout parentLinearLayout;
   private TextView mToolbarTitle;
   private TextView mTvRight;
   public PermissionHelper mPermissionHelper;
    private NetBroadcastReceiver mNetBroadcastReceiver;
    private Toolbar mToolbar;
    // 网络状态改变监听事件
    public static NetBroadcastReceiver.NetChangeListener netEvent;
    private static final String TAG = "BaseActivity";


    @Override
   public String[] getPermissions() {

      return new String[]{
              Manifest.permission.WRITE_EXTERNAL_STORAGE,
              Manifest.permission.READ_PHONE_STATE
      };

   }

   @Override
   public void requestPermissionsSuccess() {
      //initView();

   }

   @Override
   public void requestPermissionsFail() {
      finish();

   }

   @Override
   public int getPermissionsRequestCode() {
      return 0;
   }
  kkkkkkkkkkk nnnnnnnnnn
   // 提交到firstBranch
   @Override
   public void onCreate(Bundle savedInstanceState) {
   super.onCreate(savedInstanceState);
      ActivityUtils.getInstance().addActivity(this);

      //初始化并发起权限申请
      mPermissionHelper = new PermissionHelper(this, this);
      mPermissionHelper.requestPermissions();
       netEvent = this;

       if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
           //实例化IntentFilter对象
           IntentFilter filter = new IntentFilter();
           filter.addAction("android.net.conn.CONNECTIVITY_CHANGE");
           mNetBroadcastReceiver = new NetBroadcastReceiver();
           //注册广播接收
           registerReceiver(mNetBroadcastReceiver, filter);
       }


       inspectNet();

      //https://blog.csdn.net/qq_30651537/article/details/51645555
      //https://blog.csdn.net/lijizhi19950123/article/details/79337368
}



   protected int getLayoutId() {
      return 0;
   }

    public boolean inspectNet(){

        return  NetUtil.isNetworkConnected(BaseActivity.this);
    }


    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        mPermissionHelper.requestPermissionsResult(requestCode,permissions,grantResults);

    }

   public  void initView() {
      initContentView(R.layout.activity_base);
      mToolbar = (Toolbar) findViewById(R.id.toolbar);
      mToolbarTitle = (TextView) findViewById(R.id.toolbar_title);
      if (mToolbar != null) {
         //将Toolbar显示到界面
         setSupportActionBar(mToolbar);
      }
      if (mToolbarTitle != null) {
         //getTitle()的值是activity的android:lable属性值
         mToolbarTitle.setText(getTitle());
         //设置默认的标题不显示
         getSupportActionBar().setDisplayShowTitleEnabled(false);
      }

   }
   /**
    * 获取头部标题的TextView
    * @return
    */
   public TextView getToolbarTitle(){
      return mToolbarTitle;
   }


   /**
    * 设置头部标题
    * @param title
    */
   public void setToolBarTitle(CharSequence title) {
      if(mToolbarTitle != null){
         mToolbarTitle.setText(title);
      }else{
         getToolbar().setTitle(title);
         setSupportActionBar(getToolbar());
      }
   }

   /**
    * this Activity of tool bar.
    * 获取头部.
    * @return support.v7.widget.Toolbar.
    */
   public Toolbar getToolbar() {
      return (Toolbar) findViewById(R.id.toolbar);
   }


   public  void initData()
   {

   }

   public void initContentView(@LayoutRes int layoutResID) {
      ViewGroup viewGroup = (ViewGroup) findViewById(android.R.id.content);
      viewGroup.removeAllViews();
      parentLinearLayout = new LinearLayout(this);
      parentLinearLayout.setOrientation(LinearLayout.VERTICAL);
      //  add parentLinearLayout in viewGroup
      viewGroup.addView(parentLinearLayout);
      //  add the layout of BaseActivity in parentLinearLayout
      LayoutInflater.from(this).inflate(layoutResID, parentLinearLayout, true);
   }


   @Override
   public void onClick(View v) {

   }

   // 横竖屏切换，键盘等

   /**
    * 网络状态改变时间监听
    *
    * @param netWorkState true有网络，false无网络
    */

   @Override
   public void onNetChange(boolean netWorkState) {


       Log.d(TAG, "onNetChange: " + netWorkState);
           AlertDialogUtil.getInstance(BaseActivity.this).showAlertDialog(R.string.dialog_title, R.string.dialog_message, R.string.dialog_positive_button, R.string.dialog_negative_button, new DialogInterface.OnClickListener(){


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

