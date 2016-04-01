package com.wxm.activity;

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
		// TODO Auto-generated method stub
		setContentView(R.layout.activity_addproject);
		ViewUtils.inject(this);
	}

	@Override
	protected void initData() {
		setTitleName("新建微项目");
		btn_left.setOnClickListener(this);
		btn_lr_now.setOnClickListener(this);
	}

	@SuppressWarnings("deprecation")
	@Override
	protected void processClick(View v) {
		// TODO Auto-generated method stub
		switch (v.getId()) {
		// case R.id.et_wxrq:
		// DatePickerDialog dateDlg = new DatePickerDialog(ct, d,
		// dateAndTime.get(Calendar.YEAR),
		// dateAndTime.get(Calendar.MONTH),
		// dateAndTime.get(Calendar.DAY_OF_MONTH));
		// dateDlg.show();
		//
		// break;
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
			project.save(ct, new SaveListener() {

				@Override
				public void onSuccess() {
					// TODO Auto-generated method stub
					showToast("创建项目成功~");
					finish();
				}

				@Override
				public void onFailure(int arg0, String arg1) {
					// TODO Auto-generated method stub
					showToast("创建项目失败，请重试");
				}
			});

			break;

		default:
			break;
		}
	}

	// private void upDateDate() {
	// et_wxrq.setText(fmtDate.format(dateAndTime.getTime()));
	// }
	//
	// private void upDateTime() {
	// et_wxsj.setText(fmtTime.format(dateAndTime.getTime()));
	// }

	// // 获取日期格式器对象
	// DateFormat fmtDate = new java.text.SimpleDateFormat("yyyy-MM-dd");
	//
	// DateFormat fmtDateall = new
	// java.text.SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	//
	// DateFormat fmtTime = new java.text.SimpleDateFormat("HH:mm:ss");

	// 获取一个日历对象
	// Calendar dateAndTime = Calendar.getInstance(Locale.CHINA);
	// // 当点击DatePickerDialog控件的设置按钮时，调用该方法
	// DatePickerDialog.OnDateSetListener d = new
	// DatePickerDialog.OnDateSetListener() {
	// @Override
	// public void onDateSet(DatePicker view, int year, int monthOfYear, int
	// dayOfMonth) {
	// // 修改日历控件的年，月，日
	// // 这里的year,monthOfYear,dayOfMonth的值与DatePickerDialog控件设置的最新值一致
	// dateAndTime.set(Calendar.YEAR, year);
	// dateAndTime.set(Calendar.MONTH, monthOfYear);
	// dateAndTime.set(Calendar.DAY_OF_MONTH, dayOfMonth);
	// // 将页面TextView的显示更新为最新时间
	// upDateDate();
	//
	// }
	// };

	// TimePickerDialog.OnTimeSetListener t = new
	// TimePickerDialog.OnTimeSetListener() {
	//
	// // 同DatePickerDialog控件
	// @Override
	// public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
	// dateAndTime.set(Calendar.HOUR_OF_DAY, hourOfDay);
	// dateAndTime.set(Calendar.MINUTE, minute);
	// upDateTime();
	// }
	// };
}
