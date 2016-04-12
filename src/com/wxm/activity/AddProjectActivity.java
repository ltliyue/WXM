package com.wxm.activity;

import java.util.ArrayList;

import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import cn.bmob.v3.listener.SaveListener;

import com.lidroid.xutils.ViewUtils;
import com.lidroid.xutils.view.annotation.ViewInject;
import com.wxm.BaseActivty;
import com.wxm.R;
import com.wxm.model.Project;

/**
 * 创建项目界面
 * 
 * @author MaryLee
 * 
 */
public class AddProjectActivity extends BaseActivty {

	@ViewInject(R.id.et_xmm)
	private EditText et_xmm;
	@ViewInject(R.id.et_xmjj)
	private EditText et_xmjj;
	@ViewInject(R.id.et_sjmb)
	private EditText et_sjmb;
	@ViewInject(R.id.et_xmyq)
	private EditText et_xmyq;
	@ViewInject(R.id.et_zdjs)
	private EditText et_zdjs;

	@ViewInject(R.id.btn_lr_now)
	private Button btn_lr_now;

	@Override
	protected void initView() {
		setContentView(R.layout.activity_addproject);
		ViewUtils.inject(this);
	}

	@Override
	protected void initData() {
		setTitleName("新建微项目");
		btn_left.setOnClickListener(this);// 返回键
		btn_lr_now.setOnClickListener(this);// 保存按钮点击事件
	}

	@Override
	protected void processClick(View v) {
		switch (v.getId()) {
		// 保存按钮点击事件
		case R.id.btn_lr_now:

			if (TextUtils.isEmpty(et_xmm.getText().toString())) {
				showToast("信息不完整！");
				return;
			}
			if (TextUtils.isEmpty(et_xmjj.getText().toString())) {
				showToast("信息不完整！");
				return;
			}
			if (TextUtils.isEmpty(et_sjmb.getText().toString())) {
				showToast("信息不完整！");
				return;
			}
			if (TextUtils.isEmpty(et_xmyq.getText().toString())) {
				showToast("信息不完整！");
				return;
			}
			if (TextUtils.isEmpty(et_zdjs.getText().toString())) {
				showToast("信息不完整！");
				return;
			}

			Project project = new Project();
			project.setName(et_xmm.getText().toString());
			project.setDesc(et_xmjj.getText().toString());
			project.setTarget(et_sjmb.getText().toString());
			project.setRequest(et_xmyq.getText().toString());
			project.setTeacher(et_zdjs.getText().toString());
			project.setState("未开始");
			ArrayList<Integer> ints = new ArrayList<Integer>();
			for (int i = 0; i < 6; i++) {
				ints.add(0);
			}
			project.setRate(ints);
			project.setStunames(new ArrayList<String>());
			project.save(ct, new SaveListener() {

				@Override
				public void onSuccess() {
					showToast("创建项目成功~");
					finish();
				}

				@Override
				public void onFailure(int arg0, String arg1) {
					showToast("创建项目失败，请重试");
				}
			});

			break;

		default:
			break;
		}
	}
}
