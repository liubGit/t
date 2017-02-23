package com.elephant.healthycat.healthycattest.util;


import android.os.Bundle;
import android.os.Handler;
import android.os.HandlerThread;
import android.os.Looper;
import android.app.Fragment;
import android.view.View;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.PopupWindow;
import android.widget.TextView;

import com.elephant.healthycat.healthycattest.R;


public abstract class BaseFragment extends Fragment {
	//[start] 基本变量声明
	private Looper mActivityLooper;
	private ActivityHandler mActivityHandler;
	protected TextView mLeftTitleTv;
	protected ImageView mBackBtn;
	protected TextView mTitleTv;
	protected TextView mRightTitleTv;
	protected ImageView mRightBtn;
	private View mPopView;
	public PopupWindow mPopupWindow;
	private ListView mMenuListView;

	protected static final int PICK_PHOTO_FROM_CAMERA = 10001;
	protected static final int PICK_PHOTO_FROM_CONTENT = 10002;
	protected static final int PICK_PHOTO_FROM_CORP = 10003;
	//[end]
	
	//[start] Handler对象
	private class ActivityHandler extends Handler {
		public ActivityHandler(Looper looper) {
			super(looper);
		}
	};
	//[end]
	
	//[start] 初始化头部工具栏
	protected void setActionBar(final View view, String title) {
		
		mLeftTitleTv = (TextView) view.findViewById(R.id.left_title);
		mBackBtn = (ImageView) view.findViewById(R.id.back);
		mTitleTv = (TextView) view.findViewById(R.id.title);
		mRightBtn = (ImageView) view.findViewById(R.id.right_btn);
		mRightTitleTv = (TextView) view.findViewById(R.id.right_title);
		mTitleTv.setText(title);
		mBackBtn.setVisibility(View.INVISIBLE);
	}
	//[end]

	//[start] onActivityCreated
	@Override
	public void onActivityCreated(Bundle savedInstanceState) {
		super.onActivityCreated(savedInstanceState);
		
		HandlerThread thread = new HandlerThread("BaseActivity");
		thread.start();
		mActivityLooper = thread.getLooper();
		mActivityHandler = new ActivityHandler(mActivityLooper);
	}
	//[end]
	//[start] onDestroy
	@Override
	public void onDestroy() {
		super.onDestroy();
		//结束线程
		mActivityLooper.quit();
	}
	//[end]
	
	
	//[start] 在非ui线程中执行任务action
	public final void runOnThread(Runnable action) {
		mActivityHandler.post(action);
	}
	//[end]
	
	


}
