package com.wxm.activity;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import cn.bmob.v3.BmobUser;
import cn.bmob.v3.exception.BmobException;
import cn.bmob.v3.listener.LogInListener;

import com.lidroid.xutils.util.LogUtils;
import com.wxm.R;
import com.wxm.model.User;
import com.wxm.utils.PreferencesUtils;
import com.wxm.utils.Util;

/**
 * 登陆界面
 * 
 * @date 2014-4-24
 * @author Stone
 */
public class LoginActivity extends Activity implements OnClickListener {

	@SuppressWarnings("unused")
	private static final String TAG = "LoginActicity";

	private Button btnLogin;
	private Button btnReg;
	private Button btn_register_ls;
	// private Button btnResetPsd;
	private EditText etUsername;
	private EditText etPassword;

	private String username;
	private String password;

	private TextView mUserInfo;
	private ImageView mUserLogo;
	private ImageView mNewLoginButton;

	Handler mHandler = new Handler() {

		@Override
		public void handleMessage(Message msg) {
			if (msg.what == 0) {
				mUserInfo.setVisibility(android.view.View.VISIBLE);
				mUserInfo.setText(msg.getData().getString("nickname"));
			} else if (msg.what == 1) {
				Bitmap bitmap = (Bitmap) msg.obj;
				mUserLogo.setImageBitmap(bitmap);
				mUserLogo.setVisibility(android.view.View.VISIBLE);
			}
		}
	};

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_login);

		btnLogin = (Button) findViewById(R.id.btn_login);
		btnReg = (Button) findViewById(R.id.btn_register);
		btn_register_ls = (Button) findViewById(R.id.btn_register_ls);

		etUsername = (EditText) findViewById(R.id.et_username);
		etPassword = (EditText) findViewById(R.id.et_password);

		btnLogin.setOnClickListener(this);
		btnReg.setOnClickListener(this);
		btn_register_ls.setOnClickListener(this);

		mUserInfo = (TextView) findViewById(R.id.user_nickname);
		mUserLogo = (ImageView) findViewById(R.id.user_logo);
		mNewLoginButton = (ImageView) findViewById(R.id.new_login_btn);
		mNewLoginButton.setOnClickListener(this);

		getUserInfo();

	}

	private void getUserInfo() {
		etUsername.setText(PreferencesUtils.getString(this, "username"));
		etPassword.setText(PreferencesUtils.getString(this, "password"));
	}

	// 保存用户的登陆记录
	private void saveUserInfo(String username, String password) {
		PreferencesUtils.putString(this, "username", username);
		PreferencesUtils.putString(this, "password", password);
	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		// 登陆
		case R.id.btn_login:
			username = etUsername.getText().toString();
			password = etPassword.getText().toString();

			if (!Util.isNetworkConnected(this)) {
				toast("未检查到网络");
			} else if (username.equals("") || password.equals("")) {
				toast("请输入账号和密码~");
				break;
//			} else {
//				User bu2 = new User();
//				bu2.setUsername(username);
//				bu2.setPassword(password);
//				bu2.login(this, new SaveListener() {
//					@Override
//					public void onSuccess() {
//						// 保存用户信息
//						saveUserInfo(username, password);
//						// 跳转到主页
//						Intent toHome = new Intent(LoginActivity.this, MainActivity.class);
//						startActivity(toHome);
//						finish();
//					}
//
//					@Override
//					public void onFailure(int arg0, String msg) {
//						toast("用户名或密码错误");
//						System.out.println(arg0 + ":!:" + msg);
//					}
//				});
			}else{
				BmobUser.loginByAccount(this, username, password, new LogInListener<User>() {

		            @Override
		            public void done(User user, BmobException e) {
		                // TODO Auto-generated method stub
		            	// 保存用户信息
						saveUserInfo(username, password);
						LogUtils.e("type:::"+user.getType());
						PreferencesUtils.putInt(LoginActivity.this, "type", user.getType());
						// 跳转到主页
						Intent toHome = new Intent(LoginActivity.this, MainActivity.class);
						startActivity(toHome);
						finish();
		            }
		        });
			}
			break;

		case R.id.btn_register_ls:
			Intent toResetPsdActivity = new Intent(LoginActivity.this, RegisterActivity.class);
			toResetPsdActivity.putExtra("type", 1);
			startActivity(toResetPsdActivity);
			break;

		case R.id.btn_register:
			Intent toReg = new Intent(LoginActivity.this, RegisterActivity.class);
			toReg.putExtra("type", 0);
			startActivity(toReg);
			break;
		default:
			break;

		}
	}

	public void toast(String toast) {
		Toast.makeText(this, toast, Toast.LENGTH_SHORT).show();
	}

}
