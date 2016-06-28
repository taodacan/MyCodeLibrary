package com.example.greendaotest;

import java.util.List;

import com.cn.speedchat.greendao.UserEntity;

import android.os.Bundle;
import android.app.Activity;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

public class MainActivity extends Activity {

	private DBManager dBManager; // 定义一个DBHelper对象，用他来对数据库进行增删改查

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		// 获取数据库管理实例
		dBManager = DBManager.getInstance(this);

		Button mButton = (Button) findViewById(R.id.btn_add);
		mButton.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				//创建一个UserEntity实体对象，并赋值
				UserEntity userEntity = new UserEntity();
				userEntity.setName("测试");
				userEntity.setSex(true);
				userEntity.setTime("2016/06/28");
				userEntity.setAge(21);
				
				//把userEntity对象存数据库
				dBManager.saveUser(userEntity);
				

			}
		});

		Button mButton_search = (Button) findViewById(R.id.btn_search);
		mButton_search.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {

				 List<UserEntity> listentity = dBManager.loadAllUser();
				
				 for(int i=0;i<listentity.size();i++)
				 {
					 UserEntity tmpEntity = listentity.get(i);
					 Log.e("name", tmpEntity.toString());
				 }
			}
		});
	}

}
