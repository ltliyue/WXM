package com.wxm.activity;

import java.util.List;

import android.content.Context;
import android.content.Intent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import cn.bmob.v3.BmobQuery;
import cn.bmob.v3.listener.FindListener;
import cn.bmob.v3.listener.SaveListener;

import com.lidroid.xutils.ViewUtils;
import com.lidroid.xutils.view.annotation.ViewInject;
import com.wxm.BaseActivty;
import com.wxm.MyBaseAdapter;
import com.wxm.R;
import com.wxm.model.Chat;
import com.wxm.model.Project;

/**
 * 交流页面
 * 
 * @author MaryLee
 * 
 */
public class ChatActivity extends BaseActivty {

	Intent mIntent;

	@ViewInject(R.id.listview)
	ListView listView;
	@ViewInject(R.id.btn_publish)
	Button btn_publish;
	@ViewInject(R.id.et_content)
	EditText et_content;

	List<Project> listData;
	MyAdapter adapter;
	String oidString;

	@Override
	protected void initView() {
		setContentView(R.layout.activity_chat);
		ViewUtils.inject(this);
	}

	@Override
	protected void initData() {
		oidString = getIntent().getStringExtra("oid");// 项目id
		setTitleName("项目讨论区");
		btn_left.setOnClickListener(this);
		btn_publish.setOnClickListener(this);

		findComments();
	}

	@Override
	protected void processClick(View v) {
		switch (v.getId()) {

		case R.id.btn_publish:
			publishComment(et_content.getText().toString());
			break;
		default:
			break;
		}
	}

	private void findComments() {
		BmobQuery<Chat> query = new BmobQuery<Chat>();
		query.addWhereEqualTo("projectid", oidString);
		query.findObjects(this, new FindListener<Chat>() {

			@Override
			public void onSuccess(List<Chat> object) {
				adapter = new MyAdapter(ct, object);
				listView.setAdapter(adapter);
				adapter.notifyDataSetChanged();
			}

			@Override
			public void onError(int code, String msg) {
				// TODO Auto-generated method stub
			}
		});
	}

	private void publishComment(String content) {
		Chat comment = new Chat();
		comment.setChat(content);
		comment.setUsername(getUserName());
		comment.setProjectid(oidString);
		comment.setType(getType());
		comment.save(this, new SaveListener() {

			@Override
			public void onSuccess() {
				// TODO Auto-generated method stub
				findComments();
				et_content.setText("");
			}

			@Override
			public void onFailure(int code, String msg) {
				// TODO Auto-generated method stub
			}
		});
	}

	private static class MyAdapter extends MyBaseAdapter<Chat, ListView> {

		public MyAdapter(Context context, List<Chat> list) {
			super(context, list);
		}

		class ViewHolder {
			TextView tv_content;
			TextView tv_author;
			TextView tv_time;
		}

		@Override
		public View getView(int position, View convertView, ViewGroup parent) {
			ViewHolder holder = null;
			if (convertView == null) {
				convertView = View.inflate(context, R.layout.list_item_comment, null);

				holder = new ViewHolder();
				holder.tv_content = (TextView) convertView.findViewById(R.id.tv_content);
				holder.tv_author = (TextView) convertView.findViewById(R.id.tv_author);
				holder.tv_time = (TextView) convertView.findViewById(R.id.tv_time);

				convertView.setTag(holder);
			} else {
				holder = (ViewHolder) convertView.getTag();
			}

			if (list.get(position).getType() == 0) {
				holder.tv_author.setText("学生：" + list.get(position).getUsername());
			} else {
				holder.tv_author.setText("教师：" + list.get(position).getUsername());
			}
			holder.tv_content.setText(list.get(position).getChat());
			holder.tv_time.setText(list.get(position).getCreatedAt());

			return convertView;
		}
	}
}
