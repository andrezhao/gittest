package com.example.base.Request;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.widget.Toast;

import com.example.base.Base.BaseActivity;
import com.example.base.CostoumUtils.NetUtil;

public class NetBroadcastReceiver extends BroadcastReceiver {
    public NetChangeListener listener = BaseActivity.netEvent;

    @Override
    public void onReceive(Context context, Intent intent) {
        // 如果相等的话就说明网络状态发生了变化
        if (intent.getAction().equals(ConnectivityManager.CONNECTIVITY_ACTION)) {
            Toast.makeText(context, "network change", Toast.LENGTH_SHORT).show();
            Boolean b = NetUtil.isNetworkConnected(context);
            listener.onNetChange(b);
        }
    }

    // 网络状态变化接口
    public interface NetChangeListener {
        void onNetChange(boolean netWorkState);
    }
}
