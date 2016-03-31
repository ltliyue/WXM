package com.wxm.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import cn.bmob.v3.listener.SaveListener;

import com.wxm.R;
import com.wxm.model.User;
import com.wxm.utils.Util;

/**
 * 注册界面
 */
public class RegisterActivity extends Activity implements OnClickListener {

	@SuppressWarnings("unused")
	private static final String TAG = "RegisterActivity";

	private Button btnReg;
	private EditText etUsername;
	private EditText etPassword;
	private EditText etComfirmPsd;

	private String username = null;
	private String password = null;
	private String comfirmPsd = null;
	// private String phone = null;

	int type = 0;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_reg);

		type = getIntent().getIntExtra("type", 0);

		etUsername = (EditText) findViewById(R.id.et_username);
		etPassword = (EditText) findViewById(R.id.et_password);
		etComfirmPsd = (EditText) findViewById(R.id.et_comfirm_psd);
		// etPhone = (EditText) findViewById(R.id.et_phone);

		btnReg = (Button) findViewById(R.id.btn_reg_now);
		btnReg.setOnClickListener(this);

	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.btn_reg_now:
			username = etUsername.getText().toString();
			password = etPassword.getText().toString();
			comfirmPsd = etComfirmPsd.getText().toString();
			// phone = etPhone.getText().toString();
			if (!Util.isNetworkConnected(this)) {
				toast("木有网络 ( ⊙ o ⊙ ) ");
			} else if (username.equals("") || password.equals("") || comfirmPsd.equals("")) {
				toast("信息不填完整 ");
			} else if (!comfirmPsd.equals(password)) {
				toast("两次密码输入不一致");
				// } else if (!Util.isPhoneNumberValid(phone)) {
				// toast("请输入正确的手机号码");
			} else {
				// 开始提交注册信息
				User bu = new User();
				bu.setUsername(username);
				bu.setPassword(password);
				bu.setType(type);
				bu.signUp(this, new SaveListener() {
					@Override
					public void onSuccess() {
						toast("注册成功");
						Intent backLogin = new Intent(RegisterActivity.this, LoginActivity.class);
						startActivity(backLogin);
						RegisterActivity.this.finish();
					}

					@Override
					public void onFailure(int arg0, String msg) {
						toast("被人注册过了, 换个名字吧.");
					}
				});
			}
			break;

		default:
			break;
		}
	}

	public void toast(String toast) {
		Toast.makeText(this, toast, Toast.LENGTH_SHORT).show();
	};

}
