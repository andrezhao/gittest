package com.example.base;

import android.app.Application;
import android.content.res.Configuration;
import android.util.Log;

import com.android.volley.RequestQueue;
import com.android.volley.toolbox.Volley;
import com.example.base.CostoumUtils.NetUtil;
import com.example.base.CostoumUtils.SPUtil;
import com.example.base.CostoumUtils.SUtils;

import org.xutils.x;

import static com.example.base.View.SplashActivity.SHARE_IS_FIRST;


/* 初始化 应用程序级别 的资源，如全局对象、环境配置变量等
 数据共享、数据缓存，如设置全局共享变量、方法等
 获取应用程序当前的内存使用情况，及时释放资源，从而避免被系统杀死
 监听 应用程序 配置信息的改变，如屏幕旋转等
 监听应用程序内 所有Activity的生命周期*/
public class MyAppcation extends Application {
    private static Application app;
    private static RequestQueue requesqueue;
    public static Boolean mNetWorkState;
    private static final String TAG = "MyAppcation";

    public MyAppcation() {
        super();
    }

    @Override
    public void onTerminate() {
        super.onTerminate();
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
    }

    @Override
    public void onLowMemory() {
        super.onLowMemory();
    }

    @Override
    public void onTrimMemory(int level) {
        super.onTrimMemory(level);
    }

    @Override
    public void onCreate() {

        super.onCreate();
        initData();


    }

    public void initData() {

        app = this;
        SUtils.initialize(app);
        SPUtil.getInstance().put(SHARE_IS_FIRST, true);
        requesqueue = Volley.newRequestQueue(this);



        //初始化 应用程序级别 的资源，如全局对象、环境配置变量、图片资源初始化、推送服务的注册等
        x.Ext.init(this);
        //是否输出Debug
        x.Ext.setDebug(true);
        mNetWorkState = NetUtil.isNetworkConnected(this);
        if (mNetWorkState == false) {

            Log.d(TAG, "initData: ");
        }
    }
}
