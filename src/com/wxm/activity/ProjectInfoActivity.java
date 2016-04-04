package com.wxm.activity;

import java.util.ArrayList;
import java.util.List;

import android.view.LayoutInflater;
import android.view.View;
import android.widget.CheckBox;
import android.widget.LinearLayout;
import android.widget.TextView;
import cn.bmob.v3.BmobQuery;
import cn.bmob.v3.listener.FindListener;

import com.lidroid.xutils.ViewUtils;
import com.lidroid.xutils.view.annotation.ViewInject;
import com.wxm.BaseActivty;
import com.wxm.R;
import com.wxm.model.Project;

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

	CheckBox[] checkboxes;
	Integer[] ints = { 1, 0, 1 };

	String oidString;
	
	@ViewInject(R.id.lin_ls)
	LinearLayout lin_ls;
	@ViewInject(R.id.lin_stu)
	LinearLayout lin_stu;
	
	LayoutInflater layoutInflater;

	@Override
	protected void initView() {
		setContentView(R.layout.activity_projectinfo);
		ViewUtils.inject(this);
	}

	@Override
	protected void initData() {
		setTitleName("项目详情");
		btn_left.setOnClickListener(this);
		
		
		oidString = getIntent().getStringExtra("oid");
		checkboxes = new CheckBox[] { check1, check2, check3, check4, check5, check6 };

		BmobQuery<Project> projectBmobQuery = new BmobQuery<Project>();
		projectBmobQuery.addWhereEqualTo("objectId", oidString);
		projectBmobQuery.findObjects(ct, new FindListener<Project>() {

			@Override
			public void onSuccess(List<Project> arg0) {
				txt_name.setText(arg0.get(0).getName());
				txt_xmjj.setText(arg0.get(0).getDesc());
				txt_sjmb.setText(arg0.get(0).getTarget());
				txt_xmyq.setText(arg0.get(0).getRequest());
				txt_zdjs.setText(arg0.get(0).getTeacher());
				
				ArrayList<Integer> ints = arg0.get(0).getRate();
				ArrayList<String> stus = arg0.get(0).getStunames();
				for (int i = 0; i < ints.size(); i++) {
					if (ints.get(i) == 1) {
						checkboxes[i].setChecked(true);
					}
				}
				for (int i = 0; i < ints.size(); i++) {
					View view = getLayoutInflater().inflate(R.layout.item_checkbox, null);
					CheckBox checkBox = (CheckBox) view.findViewById(R.id.check);
					checkBox.setText(stus.get(i));
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
		// TODO Auto-generated method stub

	}

}
