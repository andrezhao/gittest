package com.example.base.View;

import android.os.Bundle;
import android.view.View;

import com.example.base.Base.BaseActivity;

public class FirstActivity extends BaseActivity {
    @Override
    public void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);

    }

    @Override
    public void initView()
    {
        setContentView(getLayoutId());
        getToolbarTitle().setText("FirstActivity");

    }

    @Override
    public void onClick(View v) {



    }

    /**
     * 2.申明一个接口对象
     */
    private ShowCallBack mShowCallBack;

    /**
     * 1. 定义一个接口，以便程序员B根据我的定义编写程序实现接口。
     */
    public interface  ShowCallBack{
        void onShown();
    }

    /**
     * 3. 对接口对象赋值，参数为实现了接口的实例
     * 调用实现了接口的实例的方法就完成了回调
     * @param c
     */
    public void setShowCallBack(ShowCallBack c){
        mShowCallBack = c;
    }


}
