package com.example.base.CostoumUtils;

import android.annotation.SuppressLint;
import android.app.Application;
import android.content.Intent;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.Signature;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Build;
import android.support.annotation.NonNull;
import android.support.v4.content.FileProvider;

import java.io.File;
import java.util.ArrayList;
import java.util.List;


public class AppUtils {

    private static Application mApplication;

    /**
     * 获取应用对象
     *
     * @return Application
     */
    public static Application getApp() {
        if (mApplication != null) {
            return mApplication;
        }
        throw new NullPointerException("you should initialize first");
    }

    /**
     * 判断 App 是否安装
     *
     * @param packageName 应用程序包名
     * @return {@code true}: 已安装<br>{@code false}: 未安装
     */
    public static boolean isAppInstalled(@NonNull String packageName) {
        PackageManager pm = getApp().getPackageManager();
        // 获取所有已安装程序的包信息
        List<PackageInfo> pInfos = pm.getInstalledPackages(0);
        // 用于存储所有已安装程序的包名
        List<String> pNames = new ArrayList<String>();
        if (pInfos != null) {
            for (int i = 0; i < pInfos.size(); i++) {
                String pn = pInfos.get(i).packageName;
                pNames.add(pn);
            }
        }
        return pNames.contains(packageName);
    }

    /**
     * 安装 App
     *
     * @param filePath  apk 文件的路径
     * @param authority 7.0 及以上安装需要传入清单文件中的{@code <provider>}的 authorities 属性，
     *                  即 AndroidManifest.xml 中的 FileProvider 的路径
     */
    public static void installApp(@NonNull String filePath, String authority) {
        installApp(new File(filePath), authority);
    }

    /**
     * 安装 App
     *
     * @param file      apk 文件
     * @param authority 7.0 及以上安装需要传入清单文件中的{@code <provider>}的 authorities 属性，
     *                  即 AndroidManifest.xml 中的 FileProvider 的路径
     */
    public static void installApp(File file, String authority) {
        if (file == null || !file.exists()) return;
        Intent intent = new Intent(Intent.ACTION_VIEW);
        Uri uri;
        // 在 7.0 之前安装的时候,只需要通过隐式 Intent 来跳转,并且指定安装的文件 Uri 即可
        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.N) {
            uri = Uri.fromFile(file);
        }
        // 在 7.0 以上是不能直接通过 Uri 访问的
        else {
            // 参数1 : 上下文
            // 参数2 : Provider 主机地址，和清单文件中保持一致
            // 参数3 : apk 文件
            uri = FileProvider.getUriForFile(getApp(), authority, file);
            // 对目标应用临时授权该 Uri 所代表的文件
            intent.setFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION);
        }
        intent.setDataAndType(uri, "application/vnd.android.package-archive");
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
    }

    /**
     * 获取 App 信息
     *
     * @param pm 应用程序包的管理类
     * @param pi 应用程序包的信息类
     * @return AppInfo 类
     */
    private static AppInfo getAppInfo(PackageManager pm, PackageInfo pi) {
        if (pm == null || pi == null) {
            return null;
        }
        ApplicationInfo ai = pi.applicationInfo;
        String packageName = pi.packageName;
        String name = ai.loadLabel(pm).toString();
        Drawable icon = ai.loadIcon(pm);
        String packagePath = ai.sourceDir;
        String versionName = pi.versionName;
        int versionCode = pi.versionCode;
        boolean isSystemApp = (ApplicationInfo.FLAG_SYSTEM & ai.flags) != 0;
        return new AppInfo(name, icon, packageName, packagePath, versionName, versionCode, isSystemApp);
    }

    /**
     * 获取 App 的签名信息
     *
     * @param packageName 应用程序包名
     * @return App 签名
     */
    public static Signature[] getAppSignature(@NonNull String packageName) {
        try {
            PackageManager pm = getApp().getPackageManager();
            @SuppressLint("PackageManagerGetSignatures")
            PackageInfo pi = pm.getPackageInfo(packageName, PackageManager.GET_SIGNATURES);
            return pi == null ? null : pi.signatures;
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * App 信息封装类
     */
    public final static class AppInfo {
        // App 的名称
        public String name;
        // App 的图标
        public Drawable icon;
        // App 的包名
        public String packageName;
        // App 的包路径
        public String packagePath;
        // App 的版本名
        public String versionName;
        // App 的版本号
        public int versionCode;
        // 是否系统应用
        public boolean isSystemApp;

        public AppInfo(String name, Drawable icon, String packageName, String packagePath,
                       String versionName, int versionCode, boolean isSystemApp) {
            this.name = name;
            this.icon = icon;
            this.packageName = packageName;
            this.packagePath = packagePath;
            this.versionName = versionName;
            this.versionCode = versionCode;
            this.isSystemApp = isSystemApp;
        }
    }
}
