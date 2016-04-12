package com.wxm.activity;

import java.util.List;

import android.content.Context;
import android.content.Intent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
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
 * 我参加的项目
 * 
 * @author MaryLee
 * 
 */
public class MyProjectActivity extends BaseActivty {

	Intent mIntent;

	@ViewInject(R.id.listview)
	ListView listView;

	List<Project> listData;

	@Override
	protected void initView() {
		setContentView(R.layout.activity_my);
		ViewUtils.inject(this);
	}

	@Override
	protected void initData() {
		setTitleName("我参加的项目");
		btn_left.setOnClickListener(this);
	}

	@Override
	protected void processClick(View v) {
		// TODO Auto-generated method stub

	}

	@Override
	protected void onResume() {
		super.onResume();
		getDatas();
	}

	private void getDatas() {
		BmobQuery<Project> bmobQuery = new BmobQuery<Project>();
		bmobQuery.addWhereContains("stunames", getUserName());
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
						mIntent = new Intent(ct, ProjectInfoActivity.class);
						mIntent.putExtra("oid", listData.get(position).getObjectId());
						startActivity(mIntent);
					}
				});
			}

			@Override
			public void onError(int arg0, String arg1) {
				// TODO Auto-generated method stub

			}
		});
	}

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
