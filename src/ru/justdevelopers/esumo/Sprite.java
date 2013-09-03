package ru.justdevelopers.esumo;

import java.util.Random;

import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Rect;
import android.graphics.RectF;

public class Sprite {

	private final int BMP_COLUMS = 5;
	private static final int BMP_ROWS = 4;
	private int currentFrame=0;
	
	private Bitmap paunchBmp = null;
	private Bitmap headBmp = null;
	private Bitmap extBmp = null;
	
	private float scaleDelta=0.005f;
	private  float scale=1;
	private float maxScaleDelta = 0.025f;
	private boolean increaseScale = false;
	
	private int angle;
	private int maxAngle = 15;
	private boolean increaseAngle = false;
//	public static Resources res;
	
	public boolean isEating = false;
	
	public Sprite(Bitmap idextremitiesbitmap, Bitmap idheadbitmap, Bitmap idpaunchbitmap) {
		extBmp = idextremitiesbitmap;
		headBmp = idheadbitmap;
		paunchBmp = idpaunchbitmap;
		Random rnd = new Random();
		angle = rnd.nextInt(maxAngle*2)-maxAngle;
		if (rnd.nextInt(2)==0) increaseAngle=true;
		
		
	}

	private void updateSprite() {
		currentFrame = ++currentFrame % BMP_COLUMS;
	}
		
	public void onDraw(Canvas canvas, int x, int y, int r) {
		updateSprite();
		int width;
		int height;
		float compWidth;
		float compHeight;
		int srcX;			// Should it be a field for save memory or for faster work?
		int srcY;
		Rect src;
		RectF dst;	
       
		
		if (increaseScale){
			if (scale<1+maxScaleDelta) scale+=scaleDelta;	
			else increaseScale = false;
		}
		else {
			if (scale>1-maxScaleDelta) scale-=scaleDelta;	
			else increaseScale = true;
		}

		if (angle > maxAngle || angle < -maxAngle)  increaseAngle = !increaseAngle;
		if (increaseAngle) 	angle++;//=deltaAngle;	
		else angle--;//=deltaAngle;
		
		canvas.save();
		canvas.rotate(angle,x,y);
		canvas.scale(scale, scale, x, y);
		
		
		
		/** HEAD */
//		if (headBmp == null) System.out.println(headBmp+"~~~~~~~~~2");
		
		width = (headBmp.getWidth()/ BMP_COLUMS);
		height = headBmp.getHeight();
		compWidth = width*r/Circle.rMax;
		compHeight = height*r/Circle.rMax;	
	
		srcX = 0; //currentFrame * width;
		srcY = 0;

		if (this.isEating){ // Repeate for hands -/
			srcX = currentFrame * width;
		}		

		src = new Rect(srcX, srcY, srcX + width, srcY+height);
		dst = new RectF(x-compWidth/2 , y-r-compHeight+4*compHeight/11, x+compWidth/2, y-r+4*compHeight/11);
		canvas.drawBitmap(headBmp, src, dst, null);

		
		/**  
		 * Extremities 
		 * */
		width = (extBmp.getWidth()/ BMP_COLUMS);
		height = extBmp.getHeight()/ BMP_ROWS;
		compWidth = width*r/Circle.rMax;
		compHeight = height*r/Circle.rMax;
		double t=0;
		int centerX =0;
		int centerY =0;
		
		t=40;
		/** Left foot */			
		centerX = (int)( x - r*Math.sin(t*Math.PI/180))-20;
		centerY = (int)( y + r*Math.cos(t*Math.PI/180))-20;
		srcX = 0; //currentFrame * width;
		srcY = 2*height;
		src = new Rect(srcX, srcY, srcX + width, srcY + height);
		dst = new RectF(centerX - compWidth/2, centerY - compHeight/2, centerX + compWidth/2, centerY + compHeight/2);
		canvas.drawBitmap(extBmp, src, dst, null);
		
		
		/** Right foot */			
		centerX = (int)( x + r*Math.sin(t*Math.PI/180))+20;
		centerY = (int)( y + r*Math.cos(t*Math.PI/180))-20;
		srcX = 0; //currentFrame * width;
		srcY = 3*height;
		src = new Rect(srcX, srcY, srcX + width, srcY + height);
		dst = new RectF(centerX - compWidth/2, centerY - compHeight/2, centerX + compWidth/2, centerY + compHeight/2);
		canvas.drawBitmap(extBmp, src, dst, null);
		

		/** Paunch */
		srcX = paunchBmp.getWidth();
		srcY = paunchBmp.getHeight();
		int k =(int) (1.12*r);
		src = new Rect(0, 0, srcX, srcY);
		dst = new RectF(x-k , y-k, x+k, y+k);
		canvas.drawBitmap(paunchBmp, null, dst, null);
		
		srcX = 0;
		if (this.isEating){
			srcX = currentFrame * width;
		}	
		
		/** Left hand */
		t=60;
		centerX = (int)( x - r*Math.sin(t*Math.PI/180))-10;
		centerY = (int)( y - r*Math.cos(t*Math.PI/180))-10;
//		srcX = 0; //currentFrame * width;
		srcY = 0*height;
		src = new Rect(srcX, srcY, srcX + width, srcY + height);
		dst = new RectF(centerX - compWidth/2, centerY - compHeight/2, centerX + compWidth/2, centerY + compHeight/2);
		canvas.drawBitmap(extBmp, src, dst, null);
		
		
		/** Right hand */				
		centerX = (int)( x + r*Math.sin(t*Math.PI/180))+10;
		centerY = (int)( y - r*Math.cos(t*Math.PI/180))-10;
//		srcX = 0; // currentFrame * width;
		srcY = 1*height;
		src = new Rect(srcX, srcY, srcX + width, srcY + height);
		dst = new RectF(centerX - compWidth/2, centerY - compHeight/2, centerX + compWidth/2, centerY + compHeight/2);
		canvas.drawBitmap(extBmp, src, dst, null);		
		
		canvas.restore();
	}

	public void releaseBitmap() {
		System.out.println("Sprite release bitmap");
		if (paunchBmp != null)	paunchBmp.recycle();
		if (headBmp != null) headBmp.recycle();
		if (extBmp != null) extBmp.recycle();
		paunchBmp = null;
		headBmp = null;
		extBmp = null;
		
	}
}
