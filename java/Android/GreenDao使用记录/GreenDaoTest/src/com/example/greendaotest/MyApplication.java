package com.example.greendaotest;

import java.io.File;

import com.cn.speedchat.greendao.DaoMaster;
import com.cn.speedchat.greendao.DaoMaster.OpenHelper;
import com.cn.speedchat.greendao.DaoSession;

import android.app.Application;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

/**
 * �ٷ��Ƽ���ȡ��DaoMaster����ķ����ŵ�Application�����������δ�������Session����
 * ��Applicationʵ�ֵõ�DaoMaster��DaoSession�ķ�����ʵ������
 * 
 * @author Administrator
 * 
 */
public class MyApplication extends Application {
	private static DaoMaster daoMaster;
	private static DaoSession daoSession;
	public static SQLiteDatabase db;

	// ���ݿ������������Զ���������
	public static final String DB_NAME = "dbname.db";

	public static DaoMaster getDaoMaster(Context context) {
		if (daoMaster == null) {
			OpenHelper helper = new DaoMaster.DevOpenHelper(context, DB_NAME,
					null);
			daoMaster = new DaoMaster(helper.getWritableDatabase());
		}
		return daoMaster;
	}

	public static DaoSession getDaoSession(Context context) {
		if (daoSession == null) {
			if (daoMaster == null) {
				daoMaster = getDaoMaster(context);
			}
			daoSession = daoMaster.newSession();
		}
		return daoSession;
	}

	public static SQLiteDatabase getSQLDatebase(Context context) {
		if (daoSession == null) {
			if (daoMaster == null) {
				daoMaster = getDaoMaster(context);
			}
			db = daoMaster.getDatabase();
		}
		return db;
	}

	@Override
	public void onCreate() {

	}
}
