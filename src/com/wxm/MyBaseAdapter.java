package com.wxm;

import java.util.List;

import android.content.Context;
import android.widget.BaseAdapter;

	public abstract class MyBaseAdapter<T, Q> extends BaseAdapter {
		public Context context;
		public List<T> list;//
		public Q view; // 这里不一定是ListView,比如GridView,CustomListView

		public MyBaseAdapter(Context context, List<T> list, Q view) {
			this.context = context;
			this.list = list;
			this.view = view;
			/**
			 * 
			private DisplayImageOptions optionshead;
			
			// Init-------------------------
			optionshead = new DisplayImageOptions.Builder().// builder
			showStubImage(R.drawable.expert_headportrait)// 设置图片在下载期间显示的图片
			.showImageForEmptyUri(R.drawable.expert_headportrait)// 设置图片Uri为空或是错误的时候显示的图片
			.showImageOnFail(R.drawable.expert_headportrait)// 设置图片加载/解码过程中错误时候显示的图片
			.cacheInMemory(true).// 设置下载的图片是否缓存在内存中
			cacheOnDisc(true). // 设置下载的图片是否缓存在SD卡中
			bitmapConfig(android.graphics.Bitmap.Config.RGB_565).build();
			
			//Use:------------------
			ImageLoader.getInstance().displayImage(URL,
						holder1.img, optionshead, null);
			 */
		}

		public MyBaseAdapter(Context context, List<T> list) {
			this.context = context;
			this.list = list;

		}

		@Override
		public int getCount() {
			return list.size();
		}

		@Override
		public Object getItem(int position) {
			return list.get(position);
		}

		@Override
		public long getItemId(int position) {
			return position;
		}

}
