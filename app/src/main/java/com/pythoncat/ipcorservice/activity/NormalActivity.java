package com.pythoncat.ipcorservice.activity;

import android.content.ComponentName;
import android.content.Intent;
import android.content.ServiceConnection;
import android.graphics.PorterDuff;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.os.IBinder;
import android.os.Process;
import android.os.RemoteException;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.TextView;

import com.apkfuns.logutils.LogUtils;
import com.pythoncat.ipcorservice.NormalBinder;
import com.pythoncat.ipcorservice.R;
import com.pythoncat.ipcorservice.bean.Student;
import com.pythoncat.ipcorservice.service.NormalService;
import com.pythoncat.ipcorservice.utils.ToastHelper;

public class NormalActivity extends AppCompatActivity {
    public boolean bind;
    private ServiceConnection mNormalConn;
    private NormalBinder mBinder;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_normal);

        setSupportActionBar((Toolbar) findViewById(R.id.toolbar));
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle("Normal activity");
        final Drawable upArrow = getResources().getDrawable(R.drawable.abc_ic_ab_back_mtrl_am_alpha);
        upArrow.setColorFilter(getResources().getColor(R.color.colorWhite), PorterDuff.Mode.SRC_ATOP);
        getSupportActionBar().setHomeAsUpIndicator(upArrow);
        TextView tvShowResult = (TextView) findViewById(R.id.normal_show_tv);
        TextView tvStudent = (TextView) findViewById(R.id.normal_show_stu_tv);
        tvStudent.setText(getString(R.string.tip_student, ""));
        tvShowResult.setText(getString(R.string.show_about_normal_service, ""));
        Button btnExecute = (Button) findViewById(R.id.normal_ui_btn);
        btnExecute.setOnClickListener(v -> {
            LogUtils.w("execute normal service method...");
            LogUtils.w("execute normal service method...");
            if (bind) {
//                int result = mBinder.get().getResult();
                int result = 0;
                try {
                    Student s = new Student();
                    s.name=this.toString();
                    s.id=Thread.currentThread().getId();
                    mBinder.addStudent(s);
                    result = mBinder.getResult();
                    Student stu = mBinder.getStudent();
                    tvStudent.setText(getString(R.string.tip_student, stu.toString()));
                    LogUtils.e("student========" + stu);
                    LogUtils.e("result=====" + result);
                    tvShowResult.setText(getString(R.string.show_about_normal_service, result));
                } catch (RemoteException e) {
                    e.printStackTrace();
                }
            } else {
                LogUtils.e("bind service fail ,try again......");
                bindService(new Intent(this, NormalService.class), mNormalConn, BIND_AUTO_CREATE);
                ToastHelper.show(getApplication(), "bind service fail ,try again......");
            }
        });
        mNormalConn = new ServiceConnection() {
            @Override
            public void onServiceConnected(ComponentName name, IBinder service) {
                bind = true;
                LogUtils.w("normal activity connected normal service...");
//                mBinder = (NormalService.LocalBinder) service;
                mBinder = NormalBinder.Stub.asInterface(service);
            }

            @Override
            public void onServiceDisconnected(ComponentName name) {
                bind = false;
                mBinder = null;
                LogUtils.w("normal activity disconnected normal service!");
            }
        };

        int pid = Process.myPid();
        bindService(new Intent(this, NormalService.class), mNormalConn, BIND_AUTO_CREATE);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        unbindService(mNormalConn);
        LogUtils.e("$$$ unbind normal service");
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            finish();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
