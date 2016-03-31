package com.wxm.activity;

import android.content.Intent;
import android.view.View;

import com.lidroid.xutils.ViewUtils;
import com.wxm.BaseActivty;
import com.wxm.R;

public class MainActivity extends BaseActivty {

	Intent mIntent;

	@Override
	protected void initView() {
		setContentView(R.layout.activity_main);
		ViewUtils.inject(this);
	}

	@Override
	protected void initData() {
		setTitleName("微项目列表");
		if (getType() == 0) {
			btn_right.setVisibility(View.GONE);
		}
		btn_left.setOnClickListener(this);
		btn_right.setOnClickListener(this);
	}

	@Override
	protected void processClick(View v) {
		switch (v.getId()) {
		case R.id.btn_left:

			break;
		case R.id.btn_right:
			mIntent = new Intent(ct, AddProjectActivity.class);
			startActivity(mIntent);
			break;
		default:
			break;
		}
	}

}
