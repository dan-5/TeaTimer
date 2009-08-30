package com.goo.TeaTimer.Animation;

import com.goo.TeaTimer.Animation.TimerAnimation.TimerDrawing;

import goo.TeaTimer.R;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;

class TrashCupAnimation implements TimerAnimation.TimerDrawing
{
	Resources mResources = null;
	
	// buffer 
	private final int TOP_BUFFER = 3;
	private final int BOTTOM_BUFFER = 5;
	
	public TrashCupAnimation(Resources r)
	{
		mResources = r;
	}
	
	/**
	 * Updates the image to be in sync with the current time
	 * @param time in milliseconds
	 * @param max the original time set in milliseconds
	 */
	public Bitmap updateImage(int time,int max)
	{	
		
		// Load the bitmap
		Bitmap cup  = BitmapFactory.decodeResource(mResources, R.drawable.cup);
		int w = cup.getWidth();
		int h = cup.getHeight();
		
		Bitmap bitmap = Bitmap.createBitmap(w,h,Bitmap.Config.RGB_565);
		
		Paint paint = new Paint();
		
		float p = (max == 0) ? 0 : (time/(float)max);
		
		// Define the drawing rects
		RectF teaRect = new RectF(0,(h-TOP_BUFFER)*p+BOTTOM_BUFFER,w,h+BOTTOM_BUFFER);
		RectF fillRect = new RectF(0,0,w,h);
		
		Canvas canvas = new Canvas(bitmap);
		
		// Fill the entire bg the correct color
		canvas.drawColor(Color.rgb(24,24,24));
		
		// Unused part of the cup
		paint.setColor(R.color.tea_bg);
		canvas.drawRect(fillRect, paint);
		
		// The filled part of the cup
		paint.setColor(mResources.getColor(R.color.tea_fill));
		canvas.drawRect(teaRect,paint);
		canvas.drawBitmap(cup, 0, 0, paint);
		
		// Switch out the bitmap
		return bitmap;	
	}
}