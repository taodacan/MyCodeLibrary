package com.example.greendaotest;

import java.util.ArrayList;
import java.util.List;

import com.cn.speedchat.greendao.DaoSession;

import de.greenrobot.dao.query.QueryBuilder;
import android.content.Context;
import android.util.Log;

public class DBHelper {
	 private static final String TAG = DBHelper.class.getSimpleName();  
	    private static DBHelper instance;  
	    private static Context appContext;
	    private DaoSession mDaoSession;
	    
	    private DBHelper() {
	    } 
	    //单例模式，DBHelper只初始化一次
	    public static  DBHelper getInstance(Context context) {  
	        if (instance == null) {
	                   instance = new DBHelper();  
	                   if (appContext == null){  
	                       appContext = context.getApplicationContext();  
	                   }  
	                   instance.mDaoSession = MyApplication.getDaoSession(context);
	               } 
	        return instance;  
	    }
}
