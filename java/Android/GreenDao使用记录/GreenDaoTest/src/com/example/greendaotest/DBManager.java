package com.example.greendaotest;

import java.util.ArrayList;
import java.util.List;

import com.cn.speedchat.greendao.DaoSession;
import com.cn.speedchat.greendao.UserEntity;
import com.cn.speedchat.greendao.UserEntityDao;
import com.cn.speedchat.greendao.UserEntityDao.Properties;

import de.greenrobot.dao.query.QueryBuilder;
import android.content.Context;
import android.util.Log;

/**
 * 创建一个DBManager的管理类，这个管理类写了自己封装的一些方法，内容如下
 * 
 * @author Administrator
 * 
 */
public class DBManager {
	private static final String TAG = DBManager.class.getSimpleName();
	private static DBManager instance;
	private static Context appContext;
	private DaoSession mDaoSession;

	UserEntityDao userEntityDao;

	private DBManager() {
	}

	// 单例模式，DBHelper只初始化一次
	public static DBManager getInstance(Context context) {
		if (instance == null) {
			instance = new DBManager();
			if (appContext == null) {
				appContext = context.getApplicationContext();
			}
			instance.mDaoSession = MyApplication.getDaoSession(context);
			instance.userEntityDao = instance.mDaoSession.getUserEntityDao();
		}
		return instance;
	}

	/**
	 * 删除User表
	 */
	public void dropUserTable() {
		UserEntityDao.dropTable(mDaoSession.getDatabase(), true);
	}

	/**
	 * 删除所有表
	 */
	public void dropAllTable() {
		UserEntityDao.dropTable(mDaoSession.getDatabase(), true);
	}

	/**
	 * 创建所有表
	 */
	public void createAllTable() {
		UserEntityDao.createTable(mDaoSession.getDatabase(), true);
	}

	/**
	 * 插入或者删除用户
	 * 
	 * @param user
	 * @return
	 */
	public long saveUser(UserEntity user) {
		return userEntityDao.insertOrReplace(user);
	}

	/**
	 * 获得所有User倒序排序存到List列表里面
	 * 
	 * @return
	 */
	public List<UserEntity> loadAllUser() {
		List<UserEntity> users = new ArrayList<UserEntity>();
		List<UserEntity> tempUsers = userEntityDao.loadAll();

		int len = tempUsers.size();

		for (int i = len - 1; i >= 0; i--) {
			users.add(tempUsers.get(i));
		}
		return users;
	}

	public void DeleteSession(UserEntity entity) {
		userEntityDao.delete(entity);
	}

	// 删除某一项
	public void DeleteNoteBySession(UserEntity entity) {
		QueryBuilder<UserEntity> mqBuilder = userEntityDao.queryBuilder();
		mqBuilder.where(Properties.Id.eq(entity.getId()));
		List<UserEntity> chatEntityList = mqBuilder.build().list();
		userEntityDao.deleteInTx(chatEntityList);
	}

	// 根据id找到某一项
	public UserEntity loadNote(long id) {
		return userEntityDao.load(id);
	}

	// 获得所有的UserEntity列表
	public List<UserEntity> loadAllNote() {
		return userEntityDao.loadAll();
	}

	/**
	 * query list with where clause ex: begin_date_time >= ? AND end_date_time
	 * <= ?
	 * 
	 * @param where
	 *            where clause, include 'where' word
	 * @param params
	 *            query parameters
	 * @return
	 */
	// 查询满足params条件的列表
	public List<UserEntity> queryNote(String where, String... params) {
		ArrayList<UserEntity> ad = new ArrayList<UserEntity>();
		return userEntityDao.queryRaw(where, params);
	}

	// 不一一介绍了，大家可以自己写，有些比较难的查询可以使用QueryBuilder来查询
	public List<UserEntity> loadLastMsgBySessionid(String userid) {
		QueryBuilder<UserEntity> mqBuilder = userEntityDao.queryBuilder();
		mqBuilder.where(Properties.Id.eq(userid)).orderDesc(Properties.Id)
				.limit(1);
		return mqBuilder.list();
	}

	public List<UserEntity> loadMoreMsgById(String userid, Long id) {
		QueryBuilder<UserEntity> mqBuilder = userEntityDao.queryBuilder();
		mqBuilder.where(Properties.Id.lt(id)).where(Properties.Id.eq(userid))
				.orderDesc(Properties.Id).limit(20);
		return mqBuilder.list();
	}
}
