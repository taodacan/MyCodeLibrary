package com.example.greendaotest;

import java.io.File;
import java.io.IOException;

import android.content.Context;
import android.content.ContextWrapper;
import android.database.DatabaseErrorHandler;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteDatabase.CursorFactory;
import android.util.Log;

import com.cn.speedchat.greendao.DaoMaster;
import com.cn.speedchat.greendao.UserEntityDao;
import com.cn.speedchat.greendao.DaoMaster.OpenHelper;

public class MySQLiteOpenHelper extends DaoMaster.OpenHelper {
	
	public MySQLiteOpenHelper(Context context, String name,
			CursorFactory factory) {
		super(new ContextWrapper(context){
			/** 
             * ������ݿ�·������������ڣ��򴴽�������� 
             *  
             * @param name 
             * @param mode 
             * @param factory 
             */
        	@Override
        	public File getDatabasePath(String name) {
        		// �ж��Ƿ����sd��  
                boolean sdExist = android.os.Environment.MEDIA_MOUNTED.equals(android.os.Environment.getExternalStorageState());  
                if (!sdExist) {// ���������,  
                    Log.e("SD������", "SD�������ڣ������SD��");  
                    return null;  
                } else {// �������  
                        // ��ȡsd��·��  
                    String dbDir = android.os.Environment.getExternalStorageDirectory().getAbsolutePath() + "/Dao/Database/";  // ���ݿ�����Ŀ¼  
                    
                    String dbPath = dbDir + name;// ���ݿ�·��  
                    
                    // �ж�Ŀ¼�Ƿ���ڣ��������򴴽���Ŀ¼  
                    File dirFile = new File(dbDir);  
                    if (!dirFile.exists())  
                        dirFile.mkdirs();  

                    // ���ݿ��ļ��Ƿ񴴽��ɹ�  
                    boolean isFileCreateSuccess = false;  
                    // �ж��ļ��Ƿ���ڣ��������򴴽����ļ�  
                    File dbFile = new File(dbPath);  
                    if (!dbFile.exists()) {  
                        try {  
                            isFileCreateSuccess = dbFile.createNewFile();// �����ļ�  
                        } catch (IOException e) {  
                            e.printStackTrace();  
                        }  
                    } else  
                        isFileCreateSuccess = true;  
                    // �������ݿ��ļ�����  
                    if (isFileCreateSuccess)  
                        return dbFile;  
                    else  
                        return super.getDatabasePath(name);  
                }  
        	}
        	
        	/** 
             * ���������������������SD���ϵ����ݿ�ģ�android 2.3�����»������������� 
             *  
             * @param name 
             * @param mode 
             * @param factory 
             */  
        	@Override
        	public SQLiteDatabase openOrCreateDatabase(String name,
        			int mode, CursorFactory factory) {
        		return SQLiteDatabase.openOrCreateDatabase(getDatabasePath(name), null);
        	}

        	
        	/** 
             * Android 4.0����ô˷�����ȡ���ݿ⡣ 
             *  
             * @see android.content.ContextWrapper#openOrCreateDatabase(java.lang.String, 
             *      int, 
             *      android.database.sqlite.SQLiteDatabase.CursorFactory, 
             *      android.database.DatabaseErrorHandler) 
             * @param name 
             * @param mode 
             * @param factory 
             * @param errorHandler 
             */ 
        	@Override
        	public SQLiteDatabase openOrCreateDatabase(String name,
        			int mode, CursorFactory factory,
        			DatabaseErrorHandler errorHandler) {
        		// TODO Auto-generated method stub
        		return SQLiteDatabase.openOrCreateDatabase(getDatabasePath(name), null);
        	}
		}, name, factory);
	}
	
	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		//GreenDaoĬ��Ϊɾ�����еı���ȫ�����½��Ĳ���
		
		//�������ݿ⣬����ת�Ƶ������ݿ�
		 MigrationHelper.getInstance().migrate(db, UserEntityDao.class);
	}

}
