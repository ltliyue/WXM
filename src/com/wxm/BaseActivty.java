package com.wxm;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageView;
import android.widget.TextView;

import com.wxm.utils.CustomToast;
import com.wxm.utils.PreferencesUtils;

public abstract class BaseActivty extends FragmentActivity implements OnClickListener {

	protected Context ct;
	protected MApplication app;

	public MApplication getApp() {
		return app;
	}

	public void setApp(MApplication app) {
		this.app = app;
	}

	protected View loadingView;
	protected TextView bar_title;
	protected ImageView btn_left, btn_right;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		app = (MApplication) getApplication();
		ct = this;
		initView();
		bar_title = (TextView) findViewById(R.id.bar_title);
		btn_right = (ImageView) findViewById(R.id.btn_right);
		btn_left = (ImageView) findViewById(R.id.btn_left);
		loadingView = (View) findViewById(R.id.loading_view);
		initData();
	}

	@Override
	protected void onResume() {
		super.onResume();
	}

	@Override
	protected void onPause() {
		super.onPause();
	}

	@Override
	protected void onDestroy() {
		super.onDestroy();
	}

	protected void setTitleName(String text) {
		bar_title.setText(text);
	}

	protected int getType() {
		return PreferencesUtils.getInt(ct, "type", 0);
	}
	
	protected String getUserName() {
		return PreferencesUtils.getString(this, "username");
	}

	@Override
	public void onClick(View v) {
		if (v.getId() == R.id.btn_left) {
			finish();
			// overridePendingTransition(R.anim.slide_in_left,
			// R.anim.slide_out_right);
		}
		processClick(v);
	}

	protected void showToast(String msg) {
		CustomToast customToast = new CustomToast(ct, msg);
		customToast.show();
	}

	@Override
	public void onBackPressed() {
		super.onBackPressed();
		// overridePendingTransition(R.anim.slide_in_left,
		// R.anim.slide_out_right);
	}

	public void showLonding() {
		if (loadingView != null)
			loadingView.setVisibility(View.VISIBLE);
	}

	public void missLonding() {
		if (loadingView != null)
			loadingView.setVisibility(View.GONE);
	}

	protected abstract void initView();

	protected abstract void initData();

	protected abstract void processClick(View v);

	@Override
	protected void onActivityResult(int arg0, int arg1, Intent arg2) {
		super.onActivityResult(arg0, arg1, arg2);
	}

	@Override
	public void startActivity(Intent intent) {
		super.startActivity(intent);
		// overridePendingTransition(R.anim.slide_in_right,
		// R.anim.slide_out_left);
	}
}
