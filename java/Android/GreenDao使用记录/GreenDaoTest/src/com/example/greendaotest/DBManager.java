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
 * ����һ��DBManager�Ĺ����࣬���������д���Լ���װ��һЩ��������������
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

	// ����ģʽ��DBHelperֻ��ʼ��һ��
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
	 * ɾ��User��
	 */
	public void dropUserTable() {
		UserEntityDao.dropTable(mDaoSession.getDatabase(), true);
	}

	/**
	 * ɾ�����б�
	 */
	public void dropAllTable() {
		UserEntityDao.dropTable(mDaoSession.getDatabase(), true);
	}

	/**
	 * �������б�
	 */
	public void createAllTable() {
		UserEntityDao.createTable(mDaoSession.getDatabase(), true);
	}

	/**
	 * �������ɾ���û�
	 * 
	 * @param user
	 * @return
	 */
	public long saveUser(UserEntity user) {
		return userEntityDao.insertOrReplace(user);
	}

	/**
	 * �������User��������浽List�б�����
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

	// ɾ��ĳһ��
	public void DeleteNoteBySession(UserEntity entity) {
		QueryBuilder<UserEntity> mqBuilder = userEntityDao.queryBuilder();
		mqBuilder.where(Properties.Id.eq(entity.getId()));
		List<UserEntity> chatEntityList = mqBuilder.build().list();
		userEntityDao.deleteInTx(chatEntityList);
	}

	// ����id�ҵ�ĳһ��
	public UserEntity loadNote(long id) {
		return userEntityDao.load(id);
	}

	// ������е�UserEntity�б�
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
	// ��ѯ����params�������б�
	public List<UserEntity> queryNote(String where, String... params) {
		ArrayList<UserEntity> ad = new ArrayList<UserEntity>();
		return userEntityDao.queryRaw(where, params);
	}

	// ��һһ�����ˣ���ҿ����Լ�д����Щ�Ƚ��ѵĲ�ѯ����ʹ��QueryBuilder����ѯ
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
