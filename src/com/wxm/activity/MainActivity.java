package com.wxm.activity;

import java.util.List;

import android.content.Context;
import android.content.Intent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import cn.bmob.v3.BmobQuery;
import cn.bmob.v3.listener.FindListener;

import com.lidroid.xutils.ViewUtils;
import com.lidroid.xutils.view.annotation.ViewInject;
import com.wxm.BaseActivty;
import com.wxm.MyBaseAdapter;
import com.wxm.R;
import com.wxm.model.Project;

/**
 * 主页
 * 
 * @author MaryLee
 * 
 */
public class MainActivity extends BaseActivty {

	Intent mIntent;

	@ViewInject(R.id.listview)
	ListView listView;
	@ViewInject(R.id.btn_my)
	ImageView btn_my;

	List<Project> listData;

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
		btn_my.setOnClickListener(this);
		btn_right.setOnClickListener(this);
	}

	@Override
	protected void onResume() {
		super.onResume();
		getDatas();
	}

	@Override
	protected void processClick(View v) {
		switch (v.getId()) {
		case R.id.btn_my:
			mIntent = new Intent(ct, MyProjectActivity.class);
			startActivity(mIntent);
			break;
		case R.id.btn_right:
			mIntent = new Intent(ct, AddProjectActivity.class);
			startActivity(mIntent);
			break;
		default:
			break;
		}
	}

	/**
	 * 获得项目列表
	 */
	private void getDatas() {
		BmobQuery<Project> bmobQuery = new BmobQuery<Project>();
		bmobQuery.findObjects(ct, new FindListener<Project>() {

			@Override
			public void onSuccess(List<Project> arg0) {
				// TODO Auto-generated method stub
				listData = arg0;
				XMListAdapter xmAdapter = new XMListAdapter(ct, arg0);
				listView.setAdapter(xmAdapter);
				listView.setOnItemClickListener(new OnItemClickListener() {

					@Override
					public void onItemClick(AdapterView<?> aaa, View arg1, int position, long arg3) {
						// 老师和报名的学生可以进入
						if (listData.get(position).getStunames().contains(getUserName()) || getType() == 1
								|| listData.get(position).getState().equals("未开始")) {
							mIntent = new Intent(ct, ProjectInfoActivity.class);
							mIntent.putExtra("oid", listData.get(position).getObjectId());
							startActivity(mIntent);
						} else {
							showToast("未参加该项目，不能查看");
						}
					}
				});
			}

			@Override
			public void onError(int arg0, String arg1) {
				// TODO Auto-generated method stub

			}
		});
	}

	/**
	 * 列表样式适配器
	 * 
	 * @author MaryLee
	 * 
	 */
	class XMListAdapter extends MyBaseAdapter<Project, ListView> {

		public XMListAdapter(Context context, List<Project> list) {
			super(context, list);
			// TODO Auto-generated constructor stub
		}

		@Override
		public View getView(int position, View convertView, ViewGroup arg2) {
			// TODO Auto-generated method stub
			ViewHolder holder = null;
			if (convertView == null) {
				holder = new ViewHolder();
				convertView = View.inflate(context, R.layout.item_reply_post, null);
				holder.post_detail_title = (TextView) convertView.findViewById(R.id.post_detail_title);
				holder.name = (TextView) convertView.findViewById(R.id.name);
				holder.gonghuo = (TextView) convertView.findViewById(R.id.gonghuo);
				holder.state = (TextView) convertView.findViewById(R.id.state);
				convertView.setTag(holder);
			} else {
				holder = (ViewHolder) convertView.getTag();
			}
			holder.post_detail_title.setText("项目名称：" + list.get(position).getName());
			holder.name.setText("指导教师：" + list.get(position).getTeacher());
			holder.gonghuo.setText("发布时间：" + list.get(position).getCreatedAt().split(" ")[0]);
			holder.state.setText("项目状态：" + list.get(position).getState());
			return convertView;
		}

		class ViewHolder {
			public TextView name;
			public TextView post_detail_title;
			public TextView gonghuo;
			public TextView state;
		}

	}
}
