package com.chensd.colormatrixtest;

import android.content.Context;
import android.util.DisplayMetrics;
import android.view.Display;
import android.view.WindowManager;


/**
 * 通过手机设备信息，设置和获取数据
 */
public class DeviceUtil
{
	private static final String TAG = "DeviceUtil";

	/**
	 * 获取手机屏幕宽度
	 * 
	 * @return
	 */
	public static int getScreenWidth(Context context)
	{
		Display display = ((WindowManager) context.getSystemService(Context.WINDOW_SERVICE))
				.getDefaultDisplay();
		return display.getWidth();
	}

	/**
	 * 获取手机屏幕高度
	 * 
	 * @param context
	 * @return
	 */
	public static int getScreenHeight(Context context)
	{
		Display display = ((WindowManager) context.getSystemService(Context.WINDOW_SERVICE))
				.getDefaultDisplay();
		return display.getHeight();
	}

	public static int[] getScreenSize(Context context){
		int[] ints = new int[2];
		DisplayMetrics displayMetrics = context.getResources().getDisplayMetrics();
		ints[0] = displayMetrics.widthPixels;
		ints[1] = displayMetrics.heightPixels;
		return ints;
	}

}