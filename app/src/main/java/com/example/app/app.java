package com.example.app;

import android.app.Application;

import com.example.db.SqlHelp;

/**
 * Created by admin on 2016/9/29.
 */
public class app extends Application {
    private SqlHelp sqlHelp;
    private static  app  mApplication;

    @Override
    public void onCreate() {
        super.onCreate();
        mApplication=this;
    }

    public SqlHelp getSqlHelp() {
        if(sqlHelp==null){
           sqlHelp=new SqlHelp(mApplication);
        }
        return sqlHelp;
    }

    public static app getmApplication() {
        return mApplication;
    }
}
