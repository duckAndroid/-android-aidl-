package com.pythoncat.ipcorservice.service;

import android.app.Service;
import android.content.Intent;
import android.os.Binder;
import android.os.IBinder;
import android.os.RemoteException;

import com.apkfuns.logutils.LogUtils;
import com.pythoncat.ipcorservice.NormalBinder;
import com.pythoncat.ipcorservice.bean.Student;

import java.util.Random;

public class NormalService extends Service {
    public NormalService() {
    }

    @Override
    public IBinder onBind(Intent intent) {
        LogUtils.w("normal service bind");
//        return new LocalBinder();
        return new RemoteBinder();
    }

    @Override
    public boolean onUnbind(Intent intent) {
        LogUtils.w("normal service unbind");
        return super.onUnbind(intent);
    }

    @Override
    public void onRebind(Intent intent) {
        super.onRebind(intent);

        LogUtils.w("normal service rebind");
    }

    @Override
    public void onCreate() {
        super.onCreate();
        LogUtils.w("normal service create");
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        LogUtils.w("normal service start");
        return super.onStartCommand(intent, flags, startId);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        LogUtils.w("normal service destroy");
    }


    public class LocalBinder extends Binder {
        public NormalService get() {
            return NormalService.this;
        }
    }

    /**
     * public method invoked by caller
     *
     * @return a number of random
     */
    public int getResult() {
        return new Random().nextInt(500);
    }

    public class RemoteBinder extends NormalBinder.Stub {

        private Student stu;

        @Override
        public void basicTypes(int anInt, long aLong, boolean aBoolean, float aFloat, double aDouble, String aString) throws RemoteException {

        }

        @Override
        public int getResult() throws RemoteException {
            return new Random().nextInt(500);
        }

        @Override
        public Student getStudent() throws RemoteException {
            if (this.stu != null) {
                this.stu.name = this.stu.name+" hello pythonCat!";
                return this.stu;
            } else {
                Student stu = new Student();
                stu.id = 10086;
                stu.name = "pythonCat";
                return stu;
            }
        }

        @Override
        public void addStudent(Student s) throws RemoteException {
            this.stu = s;
        }
    }
}
