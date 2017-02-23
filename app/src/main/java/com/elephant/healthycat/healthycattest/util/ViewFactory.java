package com.elephant.healthycat.healthycattest.util;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.widget.ImageView;

import com.elephant.healthycat.healthycattest.R;
import com.nostra13.universalimageloader.core.ImageLoader;

public class ViewFactory {
	/**
	 * 获取ImageView视图的同时加载显示url
	 * 
	 * @paramtext
	 * @return
	 */
	private static String TAG=ViewFactory.class.getSimpleName();

	public static ImageView getImageView(Context context, String url) {
		ImageView imageView = (ImageView)LayoutInflater.from(context).inflate(
				R.layout.activity_home_carousel_view, null);

		Log.d(TAG, "getImageView: url--->"+url);
		ImageLoader.getInstance().displayImage(url, imageView);
		return imageView;
	}
}
