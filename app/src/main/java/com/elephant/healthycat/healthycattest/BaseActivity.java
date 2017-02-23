package com.elephant.healthycat.healthycattest;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.os.Handler;
import android.os.HandlerThread;
import android.os.Looper;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.PopupWindow;
import android.widget.TextView;

import java.lang.ref.WeakReference;


public abstract class BaseActivity extends AppCompatActivity {
    // [start] 基本变量声明
    protected int imgSize;
    protected Bitmap mPhoto;
    public MyHandler mHandler;// handler对象
    private Looper mActivityLooper;
    private ActivityHandler mActivityHandler;
    protected TextView mLeftTitleTv;
    protected ImageView mBackBtn;
    protected TextView mTitleTv;
    protected TextView mRightTitleTv;
    protected ImageView mRightBtn;
    private View mPopView;
    private PopupWindow mPopupWindow;
    private ListView mMenuListView;
    protected boolean isUploadHeadImg = true;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        HandlerThread thread = new HandlerThread("BaseActivity");
        thread.start();
        mActivityLooper = thread.getLooper();
        mActivityHandler = new ActivityHandler(mActivityLooper);
        mHandler = new MyHandler(this);
    }

    // [start] 初始化头部工具栏
    protected void setActionBar(final Activity context, String title) {
        mLeftTitleTv = (TextView) context.findViewById(R.id.left_title);
        mBackBtn = (ImageView) context.findViewById(R.id.back);
        mTitleTv = (TextView) context.findViewById(R.id.title);
        mRightBtn = (ImageView) context.findViewById(R.id.right_btn);
        mRightTitleTv = (TextView) context.findViewById(R.id.right_title);
        mTitleTv.setText(title);
        mBackBtn.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                context.finish();
            }
        });
    }

    // [end]
    // [start] Handler对象
    static class MyHandler extends Handler {
        WeakReference<BaseActivity> mActivity;

        public MyHandler(BaseActivity activity) {
            mActivity = new WeakReference<BaseActivity>(activity);
        }

        @Override
        public void handleMessage(Message message) {
            BaseActivity activity = mActivity.get();
            if (activity == null) {
                return;
            }
            activity.handleMessage(message);
        }
    }

    // [end]

    /**
     * 处理消息
     *
     * @param message
     */
    protected abstract void handleMessage(Message message);

    private class ActivityHandler extends Handler {
        public ActivityHandler(Looper looper) {
            super(looper);
        }
    }

    ;

    /**
     * 在非ui线程中执行任务action
     *
     * @param action
     */
    public final void runOnThread(Runnable action) {
        mActivityHandler.post(action);
    }


    public final void removeOnThread(Runnable action) {
        mActivityHandler.removeCallbacks(action);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        // 结束线程
        mActivityLooper.quit();
        mHandler.removeCallbacksAndMessages(null);
    }


    @Override
    public void startActivity(Intent intent) {
        intent.putExtra("from", this.getClass().toString());
        super.startActivity(intent);
    }
}
