package com.example.base.CostoumUtils;

import android.content.Context;
import android.content.DialogInterface;
import android.support.annotation.Nullable;
import android.support.v7.app.AlertDialog;

import com.example.base.R;

/**
 * 系统弹出对话框工具类
 */
@SuppressWarnings({"ALL", "AlibabaLowerCamelCaseVariableNaming"})
public class AlertDialogUtil {
    private static AlertDialogUtil mAlertDialogUtil;
    public static AlertDialog.Builder builder;

    @SuppressWarnings("AlibabaLowerCamelCaseVariableNaming")
    public void showAlertDialog(@Nullable int setTitle,int setMessage, int setNegativeButton, int setPositiveButton,  DialogInterface.OnClickListener ngeativeListener, DialogInterface.OnClickListener  positiveListener){
        builder.setTitle(setTitle);
        builder.setMessage(setMessage);
        builder.setNegativeButton(setNegativeButton, ngeativeListener);
        builder.setPositiveButton(setPositiveButton,positiveListener );
        builder.show();
    }

    public static AlertDialogUtil getInstance(Context mContent){
        if (mAlertDialogUtil==null){
            synchronized(AlertDialogUtil.class){
                if (mAlertDialogUtil==null) {
                    mAlertDialogUtil=new AlertDialogUtil();
                }
                builder = new AlertDialog.Builder(mContent, R.style.Theme_AppCompat_Light_Dialog_Alert);
            }
        }
        return mAlertDialogUtil;
    }


    public void showAlertDialog(int i, int i1, int i2, DialogInterface.OnClickListener onClickListener) {
    }
}
