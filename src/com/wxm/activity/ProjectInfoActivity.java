package com.wxm.activity;

import java.util.ArrayList;
import java.util.List;

import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.CompoundButton.OnCheckedChangeListener;
import android.widget.LinearLayout;
import android.widget.TextView;
import cn.bmob.v3.BmobQuery;
import cn.bmob.v3.listener.FindListener;
import cn.bmob.v3.listener.UpdateListener;

import com.lidroid.xutils.ViewUtils;
import com.lidroid.xutils.view.annotation.ViewInject;
import com.wxm.BaseActivty;
import com.wxm.R;
import com.wxm.model.Project;

/**
 * 项目详情界面
 * 
 * @author MaryLee
 * 
 */
public class ProjectInfoActivity extends BaseActivty {

	@ViewInject(R.id.txt_name)
	TextView txt_name;
	@ViewInject(R.id.txt_sjmb)
	TextView txt_sjmb;
	@ViewInject(R.id.txt_xmjj)
	TextView txt_xmjj;
	@ViewInject(R.id.txt_xmyq)
	TextView txt_xmyq;
	@ViewInject(R.id.txt_zdjs)
	TextView txt_zdjs;

	// ----六个进度控件
	@ViewInject(R.id.check1)
	CheckBox check1;
	@ViewInject(R.id.check2)
	CheckBox check2;
	@ViewInject(R.id.check3)
	CheckBox check3;
	@ViewInject(R.id.check4)
	CheckBox check4;
	@ViewInject(R.id.check5)
	CheckBox check5;
	@ViewInject(R.id.check6)
	CheckBox check6;

	// ----老师选择
	@ViewInject(R.id.bmry)
	TextView bmry;
	@ViewInject(R.id.lin_ls)
	LinearLayout lin_ls;
	@ViewInject(R.id.lin_stu)
	LinearLayout lin_stu;
	@ViewInject(R.id.btn_sure)
	Button btn_sure;
	// ----学生报名
	@ViewInject(R.id.btn_stu_sure)
	Button btn_stu_sure;

	// ----进度提交
	@ViewInject(R.id.lin_stu_add)
	LinearLayout lin_stu_add;
	@ViewInject(R.id.edit_txt)
	EditText edit_txt;
	@ViewInject(R.id.btn_submit)
	Button btn_submit;

	CheckBox[] checkboxes;

	String oidString;

	Project thisproject;

	LayoutInflater layoutInflater;

	ArrayList<String> stuSure = new ArrayList<String>();

	@Override
	protected void initView() {
		setContentView(R.layout.activity_projectinfo);
		ViewUtils.inject(this);
	}

	@Override
	protected void initData() {
		setTitleName("项目详情");
		btn_left.setOnClickListener(this);
		btn_right.setOnClickListener(this);
		btn_sure.setOnClickListener(this);
		btn_stu_sure.setOnClickListener(this);
		btn_submit.setOnClickListener(this);

		oidString = getIntent().getStringExtra("oid");// 项目id
		// 进度控件 6个进度
		checkboxes = new CheckBox[] { check1, check2, check3, check4, check5, check6 };

		findAndRefresh();// 获得项目详细信息
	}

	// 根据id查找项目
	private void findAndRefresh() {
		// 根据id查找
		BmobQuery<Project> projectBmobQuery = new BmobQuery<Project>();
		projectBmobQuery.addWhereEqualTo("objectId", oidString);
		projectBmobQuery.findObjects(ct, new FindListener<Project>() {

			@Override
			public void onSuccess(List<Project> arg0) {
				thisproject = arg0.get(0);
				// 显示老师选择按钮
				if (thisproject.getState().equals("未开始") && getType() == 1) {
					btn_sure.setVisibility(View.VISIBLE);
				}
				// 显示学生报名按钮
				if (thisproject.getState().equals("未开始") && getType() == 0) {
					btn_stu_sure.setVisibility(View.VISIBLE);
				}
				// 如果已报名
				if (thisproject.getStunames().contains(getUserName())) {
					btn_stu_sure.setText("已报名");
					btn_stu_sure.setEnabled(false);
				}
				// 显示学生提交进度控件
				if (thisproject.getState().equals("进行中") && getType() == 0) {
					lin_stu_add.setVisibility(View.VISIBLE);
				}
				// 显示讨论区
				if (thisproject.getState().equals("进行中")) {
					btn_right.setVisibility(View.VISIBLE);
				}

				txt_name.setText(arg0.get(0).getName());
				txt_xmjj.setText(arg0.get(0).getDesc());
				txt_sjmb.setText(arg0.get(0).getTarget());
				txt_xmyq.setText(arg0.get(0).getRequest());
				txt_zdjs.setText(arg0.get(0).getTeacher());

				ArrayList<Integer> ints = arg0.get(0).getRate();
				ArrayList<String> stus = arg0.get(0).getStunames();

				// 项目进度对号标识
				for (int i = 0; i < ints.size(); i++) {
					if (ints.get(i) == 1) {
						checkboxes[i].setChecked(true);
					}
				}

				// 如果没人报名
				if (stus.size() == 0) {
					bmry.setText("报名人员：无");
					btn_sure.setVisibility(View.GONE);
					return;
				}
				// 显示已经报名的同学
				for (int i = 0; i < ints.size(); i++) {
					View view = getLayoutInflater().inflate(R.layout.item_checkbox, null);
					final CheckBox checkBox = (CheckBox) view.findViewById(R.id.check);
					checkBox.setText(stus.get(i));
					if (thisproject.getState().equals("未开始") && getType() == 1) {
						checkBox.setEnabled(true);
					}
					// 选择框事件监听
					checkBox.setOnCheckedChangeListener(new OnCheckedChangeListener() {

						@Override
						public void onCheckedChanged(CompoundButton arg0, boolean arg1) {
							// TODO Auto-generated method stub
							if (arg1) {
								stuSure.add(checkBox.getText().toString());
							} else {
								stuSure.remove(checkBox.getText().toString());
							}
						}
					});
					lin_stu.addView(view);
				}
			}

			@Override
			public void onError(int arg0, String arg1) {
				// TODO Auto-generated method stub

			}
		});

	}

	@Override
	protected void processClick(View v) {
		switch (v.getId()) {
		// 进入讨论区
		case R.id.btn_right:

			Intent mIntent = new Intent(ct, ChatActivity.class);
			mIntent.putExtra("oid", oidString);
			startActivity(mIntent);
			break;
		// 老师选择按钮事件
		case R.id.btn_sure:

			if (stuSure.size() == 0) {
				showToast("请至少选择一个学生");
				return;
			}
			thisproject.setStunames(stuSure);
			thisproject.setState("进行中");
			thisproject.update(ct, new UpdateListener() {

				@Override
				public void onSuccess() {
					showToast("选择成功！");
					finish();
				}

				@Override
				public void onFailure(int arg0, String arg1) {
					showToast("保存失败");
				}
			});
			break;
		// 学生报名事件
		case R.id.btn_stu_sure:

			thisproject.getStunames().add(getUserName());
			thisproject.update(ct, new UpdateListener() {

				@Override
				public void onSuccess() {
					showToast("报名成功！");
					finish();
				}

				@Override
				public void onFailure(int arg0, String arg1) {
					// TODO Auto-generated method stub
					showToast("报名失败");
				}
			});
			break;
		// 学生提交进度事件
		case R.id.btn_submit:
			if (edit_txt.getText().toString().equals("")) {
				showToast("请提交进度内容");
				return;
			}
			if (thisproject.getRate().get(5) == 0 && thisproject.getRate().get(4) == 1) {
				thisproject.setState("已完成");
			}
			thisproject.getRate().remove(5);
			thisproject.getRate().add(0, 1);
			thisproject.update(ct, new UpdateListener() {

				@Override
				public void onSuccess() {
					showToast("提交成功！");
					finish();
				}

				@Override
				public void onFailure(int arg0, String arg1) {
					// TODO Auto-generated method stub
					showToast("提交失败");
				}
			});
			break;
		default:
			break;
		}
	}
}
