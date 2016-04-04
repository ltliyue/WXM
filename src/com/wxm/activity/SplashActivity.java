package com.wxm.activity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import cn.bmob.v3.Bmob;

import com.wxm.BaseActivty;
import com.wxm.R;

/**
 * 欢迎界面
 * 
 * @author MaryLee
 * 
 */
public class SplashActivity extends BaseActivty {
	private static final int GO_HOME = 100;
	private static final int GO_LOGIN = 200;

	@SuppressLint("HandlerLeak")
	private Handler mHandler = new Handler() {
		@Override
		public void handleMessage(Message msg) {
			switch (msg.what) {
			case GO_HOME:
				break;
			case GO_LOGIN:
				Intent intent = new Intent(SplashActivity.this, LoginActivity.class);
				startActivity(intent);
				finish();
				break;
			}
		}
	};

	@Override
	protected void initView() {
		setContentView(R.layout.activity_splash);
	}

	@Override
	protected void initData() {
        Bmob.initialize(this, "b1961e287dcad6285f2c7782cea01e5b");
		mHandler.sendEmptyMessageDelayed(GO_LOGIN, 0);
	}

	@Override
	protected void processClick(View v) {
		// TODO Auto-generated method stub

	}
}
