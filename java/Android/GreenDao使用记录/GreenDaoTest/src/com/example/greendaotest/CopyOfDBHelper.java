//package com.example.greendaotest;
//
//import java.util.ArrayList;
//import java.util.List;
//
//import com.cn.speedchat.greendao.DaoSession;
//import com.cn.speedchat.greendao.MqttChatEntity;
//import com.cn.speedchat.greendao.MqttChatEntityDao;
//import com.cn.speedchat.greendao.MqttChatEntityDao.Properties;
//import com.cn.speedchat.greendao.ReplayEntityDao;
//import com.cn.speedchat.greendao.SessionEntity;
//import com.cn.speedchat.greendao.SessionEntityDao;
//
//import de.greenrobot.dao.query.QueryBuilder;
//import android.content.Context;
//import android.util.Log;
//
//public class CopyOfDBHelper {
//	 private static final String TAG = CopyOfDBHelper.class.getSimpleName();  
//	    private static CopyOfDBHelper instance;  
//	    private static Context appContext;
//	    private DaoSession mDaoSession;  
//	    private MqttChatEntityDao chatDao;
//	    private SessionEntityDao sessionDao;  
//	    private CopyOfDBHelper() {
//	    } 
//	    //����ģʽ��DBHelperֻ��ʼ��һ��
//	    public static  CopyOfDBHelper getInstance(Context context) {  
//	        if (instance == null) {
//	                   instance = new CopyOfDBHelper();  
//	                   if (appContext == null){  
//	                       appContext = context.getApplicationContext();  
//	                   }  
//	                   instance.mDaoSession = MyApplication.getDaoSession(context);
//	                   instance.chatDao = instance.mDaoSession.getMqttChatEntityDao();
//	                   instance.sessionDao = instance.mDaoSession.getSessionEntityDao();
//	               } 
//	        return instance;  
//	    }
//
//
//	    //ɾ��Session��
//	    public  void dropSessionTable()
//	    {
//	        SessionEntityDao.dropTable(mDaoSession.getDatabase(), true);
//	    }
//	    //ɾ��MqttChatEntity��
//	    public void dropChatTable()
//	    {
//	        MqttChatEntityDao.dropTable(mDaoSession.getDatabase(), true);
//	    }
//	    //ɾ�����б�
//	    public void dropAllTable()
//	    {
//	        MqttChatEntityDao.dropTable(mDaoSession.getDatabase(), true);
//	        SessionEntityDao.dropTable(mDaoSession.getDatabase(), true);
//	     ReplayEntityDao.dropTable(mDaoSession.getDatabase(), true);
//	    }
//	    //�������б�
//	    public void createAllTable()
//	    {
//	        MqttChatEntityDao.createTable(mDaoSession.getDatabase(), true);
//	        SessionEntityDao.createTable(mDaoSession.getDatabase(), true);
//	     ReplayEntityDao.createTable(mDaoSession.getDatabase(), true);
//	    }
//	    /** 
//	     * insert or update note 
//	     * @param note 
//	     * @return insert or update note id 
//	     */  
//	    //�������ɾ��session��
//	    public long saveSession(SessionEntity session){  
//	        return sessionDao.insertOrReplace(session);  
//	    }  
//
//
//	    //������е�Session�����Ŵ浽List�б�����
//	    public List<SessionEntity> loadAllSession() {
//	        List<SessionEntity> sessions = new ArrayList<SessionEntity>();
//	        List<SessionEntity> tmpSessions = sessionDao.loadAll();
//	        int len = tmpSessions.size();
//	        for (int i = len-1; i >=0; i--) {
//	                sessions.add(tmpSessions.get(i));
//	        }
//	        return sessions;
//	    }  
//
//
//	    public void DeleteSession(SessionEntity entity) {
//	        sessionDao.delete(entity);
//	    }  
//	    //ɾ��ĳһ��Session    
//	    public void DeleteNoteBySession(SessionEntity entity) {
//	        QueryBuilder<MqttChatEntity> mqBuilder = chatDao.queryBuilder();
//	        mqBuilder.where(Properties.Sessionid.eq(entity.getId()));
//	        List<MqttChatEntity> chatEntityList = mqBuilder.build().list();
//	        chatDao.deleteInTx(chatEntityList);
//	    }  
//
//
//	    //����id�ҵ�ĳһ��
//	    public MqttChatEntity loadNote(long id) {
//	        return chatDao.load(id);  
//	    }  
//	    //������е�MqttChatEntity�б� 
//	    public List<MqttChatEntity> loadAllNote(){  
//	        return chatDao.loadAll();  
//	    }  
//
//
//	    /** 
//	     * query list with where clause 
//	     * ex: begin_date_time >= ? AND end_date_time <= ? 
//	     * @param where where clause, include 'where' word 
//	     * @param params query parameters 
//	     * @return 
//	     */  
//	      //��ѯ����params�������б�
//	    public List<MqttChatEntity> queryNote(String where, String... params){
//	        ArrayList<MqttChatEntity> ad = new ArrayList<MqttChatEntity>();
//	        return chatDao.queryRaw(where, params); 
//	    }
//
//
//	    //��һһ�����ˣ���ҿ����Լ�д����Щ�Ƚ��ѵĲ�ѯ����ʹ��QueryBuilder����ѯ
//	    public List<MqttChatEntity> loadLastMsgBySessionid(String sessionid){
//	        QueryBuilder<MqttChatEntity> mqBuilder = chatDao.queryBuilder();
//	        mqBuilder.where(Properties.Sessionid.eq(sessionid))
//	        .orderDesc(Properties.Id)
//	        .limit(1);
//	        return mqBuilder.list();
//	    }
//
//
//	    public List<MqttChatEntity> loadMoreMsgById(String sessionid, Long id){
//	        QueryBuilder<MqttChatEntity> mqBuilder = chatDao.queryBuilder();
//	        mqBuilder.where(Properties.Id.lt(id))
//	        .where(Properties.Sessionid.eq(sessionid))
//	        .orderDesc(Properties.Id)
//	        .limit(20);
//	        return mqBuilder.list();
//	    }
//
//
//	    /** 
//	     * delete all note 
//	     */  
//	    public void deleteAllNote(){  
//	        chatDao.deleteAll();  
//	    }  
//
//
//	    /** 
//	     * delete note by id 
//	     * @param id 
//	     */  
//	    public void deleteNote(long id){  
//	        chatDao.deleteByKey(id);  
//	        Log.i(TAG, "delete");  
//	    }  
//
//
//	    public void deleteNote(MqttChatEntity note){  
//	        chatDao.delete(note);  
//	    }  
//}
