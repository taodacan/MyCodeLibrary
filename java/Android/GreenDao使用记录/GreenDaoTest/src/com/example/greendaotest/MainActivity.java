package com.example.greendaotest;

import java.util.List;

import android.os.Bundle;
import android.app.Activity;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

public class MainActivity extends Activity {

	private DBHelper dBManager;    //����һ��DBHelper���������������ݿ������ɾ�Ĳ�
	
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		//��ȡ���ݿ����ʵ��
		dBManager = DBHelper.getInstance(this);
		
		Button mButton = (Button) findViewById(R.id.btn);
		mButton.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
//				SessionEntity entity = new SessionEntity();    //����һ��SessionEntityʵ����󣬲���ֵ
//		        entity.setFrom("C");
//		        entity.setGossip("��Һ���������...");
//		        entity.setGossipid(10);
//		        entity.setSessiontype(1);
//		        entity.setTo("D");
//		        //������һ�оͰ�entity��������ݿ��ˣ�Ȼ�������½�һ��SessionEntity�б��ٶ�һ��
//		        dBManager.saveSession(entity);    //���浽���ݿ�
//		        
//		        List<SessionEntity> listentity = dBManager.loadAllSession(); 
//				
//				for(int i=0;i<listentity.size();i++)
//		        {
//		            SessionEntity tmpEntity = listentity.get(i);
//		            Log.v("tmpEntity.getFrom()",tmpEntity.getFrom());
//		            Log.v("tmpEntity.getGossip()",tmpEntity.getGossip());
//		            Log.v("tmpEntity.getGossipid()",tmpEntity.getGossipid()+"");
//		            Log.v("tmpEntity.getSessiontype()",tmpEntity.getSessiontype()+"");
//		            Log.v("tmpEntity.getTo()",tmpEntity.getTo());
//		        }
				
			}
		});
		
		
		
	}

}
