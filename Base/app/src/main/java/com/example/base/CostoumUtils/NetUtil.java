
package com.example.base.CostoumUtils;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

//import com.liyi.sutils.constant.NetworkType;
//import com.liyi.sutils.utils.log.LogUtil;

/**
 * 网络相关工具类
 * <p>
 * 需添加的权限：
 * {@code <uses-permission android:name="android.permission.INTERNET"/>}
 * {@code <uses-permission android:name="android.permission.CHANGE_WIFI_STATE"/>}
 * {@code <uses-permission android:name="android.permission.MODIFY_PHONE_STATE"/>}
 * {@code <uses-permission android:name="android.permission.ACCESS_NETWORK_STATE"/>}
 * {@code <uses-permission android:name="android.permission.ACCESS_NETWORK_STATE"/>}
 * </p>
 */

public final class NetUtil {
    private static final String TAG = NetUtil.class.getClass().getSimpleName();
    private static final int NETWORK_TYPE_GSM = 16;
    private static final int NETWORK_TYPE_TD_SCDMA = 17;
    private static final int NETWORK_TYPE_IWLAN = 18;

    private NetUtil() {
        throw new UnsupportedOperationException("cannot be instantiated");
    }


    /**
     * 检测网络是否可用
     */
    public static boolean isNetworkConnected(Context context) {
        ConnectivityManager cm = (ConnectivityManager) context.getApplicationContext().getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo ni = null;
        if (cm != null) {

            ni = cm.getActiveNetworkInfo();
        }
        return ni != null && ni.isConnectedOrConnecting();
    }
}


