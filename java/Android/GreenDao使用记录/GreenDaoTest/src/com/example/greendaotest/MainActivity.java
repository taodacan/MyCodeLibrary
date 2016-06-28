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

	private DBManager dBManager; // ����һ��DBHelper���������������ݿ������ɾ�Ĳ�

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		// ��ȡ���ݿ����ʵ��
		dBManager = DBManager.getInstance(this);

		Button mButton = (Button) findViewById(R.id.btn_add);
		mButton.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				//����һ��UserEntityʵ����󣬲���ֵ
				UserEntity userEntity = new UserEntity();
				userEntity.setName("����");
				userEntity.setSex(true);
				userEntity.setTime("2016/06/28");
				userEntity.setAge(21);
				
				//��userEntity��������ݿ�
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
