package com.kwz.longpicdemo;

import android.Manifest;
import android.app.ActivityManager;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;

import java.util.List;

public class MainActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        requestPermissions();

    }

    private static final String TAG = "BaseActivity";
    //用来控制应用前后台切换的逻辑
    private boolean isCurrentRunningForeground = true;

    @Override
    protected void onStart() {
        super.onStart();
        if (!isCurrentRunningForeground) {
            isCurrentRunningForeground = true;
            //处理跳转到广告页逻辑
            Intent intent = new Intent(this, Main2Activity.class);
            startActivity(intent);
            Log.e(TAG, ">>>>>>>>>>>>>>>>>>>切回前台 activity process");
        }
    }

    @Override
    protected void onStop() {
        super.onStop();
        isCurrentRunningForeground = isRunningForeground();
        if (!isCurrentRunningForeground) {
            Log.e(TAG, ">>>>>>>>>>>>>>>>>>>切到后台 activity process");
        }
    }


    public boolean isRunningForeground() {
        ActivityManager activityManager = (ActivityManager) this.getSystemService(Context.ACTIVITY_SERVICE);
        List<ActivityManager.RunningAppProcessInfo> appProcessInfos = activityManager.getRunningAppProcesses();
        // 枚举进程,查看该应用是否在运行
        for (ActivityManager.RunningAppProcessInfo appProcessInfo : appProcessInfos) {
            if (appProcessInfo.importance == ActivityManager.RunningAppProcessInfo.IMPORTANCE_FOREGROUND) {
                if (appProcessInfo.processName.equals(this.getApplicationInfo().processName)) {
                    Log.e(TAG, "EntryActivity isRunningForeGround");
                    return true;
                }
            }
        }
        Log.e(TAG, "EntryActivity isRunningBackGround");
        return false;
    }

    public void register(View view) {
        startActivity(new Intent(this, FaceActivity.class));
    }

    public void recognition(View view) {
        startActivity(new Intent(this, BottomSheetActivity.class));
    }

    private void requestPermissions() {
        try {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                int permission = ActivityCompat.checkSelfPermission(this,
                        Manifest.permission.WRITE_EXTERNAL_STORAGE);
                if (permission != PackageManager.PERMISSION_GRANTED) {
                    ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE,
                            Manifest.permission.LOCATION_HARDWARE, Manifest.permission.READ_PHONE_STATE,
                            Manifest.permission.WRITE_SETTINGS, Manifest.permission.READ_EXTERNAL_STORAGE, Manifest.permission.CAMERA}, 1122);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
    }
}
