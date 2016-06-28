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

	private DBHelper dBManager;    //定义一个DBHelper对象，用他来对数据库进行增删改查
	
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		//获取数据库管理实例
		dBManager = DBHelper.getInstance(this);
		
		Button mButton = (Button) findViewById(R.id.btn);
		mButton.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
//				SessionEntity entity = new SessionEntity();    //创建一个SessionEntity实体对象，并赋值
//		        entity.setFrom("C");
//		        entity.setGossip("大家好吗？我来了...");
//		        entity.setGossipid(10);
//		        entity.setSessiontype(1);
//		        entity.setTo("D");
//		        //下面这一行就把entity对象存数据库了，然后我们新建一个SessionEntity列表再读一下
//		        dBManager.saveSession(entity);    //保存到数据库
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
