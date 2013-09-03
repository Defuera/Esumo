package ru.justdevelopers.esumo;

import java.util.List;
import java.util.Random;
import java.util.concurrent.CopyOnWriteArrayList;


import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Rect;

public class Stars {
//	private static final int BMP_BUM_COLUMS = 10;
	private static final int BMP_COLUMS = 3;
	private static final int BMP_ROWS = 1;
	private int x;
	private int y;
	private static Bitmap starBmp; // = BitmapFactory.decodeResource(getResources(), R.drawable.star);
	private static Bitmap starBumBmp; // = BitmapFactory.decodeResource(getResources(), R.drawable.starbum0);;
	private int width;
	private int height;
	private int bumWidth;
	private int bumHeight;
	private static int currentFrame = 0; //it's static here, but why???
	private static int currentBumFrame = 0;
	private long now;
	private long lifetime = 2000;
	private long borntime;
	private static List<Stars> starsList = new CopyOnWriteArrayList<Stars>();
	private Random rnd = new Random();
	private static int starsListCreated=0;
	private static int starsListCatched=0;
	private final int minDist = 100;
	private final int screenWidth  = Main.width;;  // should i do that, or just use Main.width locally? 
	private final int screenHeight = Main.height;
	private static int[] deathStar = new int[3];

	public static int  getPercent(){
		if (starsListCreated==0) return 0;
		return ((starsListCatched*100)/starsListCreated);
	}
	private Stars(int x, int y){
		this.x=x;
		this.y=y;
		borntime = System.currentTimeMillis();
		starsListCreated++;	
	}
	public Stars(Resources res){
//		starsList.clear();
		{
			starBmp = BitmapFactory.decodeResource(res, R.drawable.star);
			starBumBmp = BitmapFactory.decodeResource(res, R.drawable.starbum0);
			
			bumWidth = starBumBmp.getWidth() / 18; // 18 - starbum cols
			bumHeight = starBumBmp.getHeight(); 
//			System.out.println(starBumBmp.getWidth());
			
			width = starBmp.getWidth() / BMP_COLUMS;
			height = starBmp.getHeight() / BMP_ROWS;
		}

//		System.out.println("STAR! "+width+" "+height+" STARBUM ! "+bumWidth+" "+bumHeight);
		now = System.currentTimeMillis();
//		screenWidth = Main.width;
//		screenHeight = Main.height;
	}
	public void onDraw(Canvas canvas) {
		createStar();
		for (Stars star : starsList){
			int srcX = currentFrame * width;
			int srcY = 0*height;
			Rect src = new Rect(srcX, srcY, srcX + width, srcY + height);
			Rect dst = new Rect(star.x - width/2, star.y - height/2, star.x + width/2, star.y + height/2);
			canvas.drawBitmap(starBmp, src, dst, null);
			star.updateStar();

		}
		if (deathStar[0] == 1){
			if (currentBumFrame < 10){
//				System.out.println("Draw BuM!");
				int srcX = currentBumFrame * bumWidth;
				int srcY = 0*bumHeight;
				Rect src = new Rect(srcX, srcY, srcX + bumWidth, srcY + bumHeight);
				Rect dst = new Rect(deathStar[1] - bumWidth/2, deathStar[2] - bumHeight/2, deathStar[1] + bumWidth/2, deathStar[2] + bumHeight/2);
				canvas.drawBitmap(starBumBmp, src, dst, null);
				currentBumFrame++;
		
			}
			else {
				deathStar[0] = 0;
				currentBumFrame =0;
			}
		}
		else{
//			System.out.println("stat "+deathStar[0]);
		if (deathStar[0] == -1){
//			System.out.println("Lost Draw BuM! "+deathStar[1]+" frame "+currentBumFrame );
			if (currentBumFrame < 8){
				int srcX = (bumWidth*10)+currentBumFrame * bumWidth;
				int srcY = 0*bumHeight;
				Rect src = new Rect(srcX, srcY, srcX + bumWidth, srcY + bumHeight);
				Rect dst = new Rect(deathStar[1] - bumWidth/2, deathStar[2] - bumHeight/2, deathStar[1] + bumWidth/2, deathStar[2] + bumHeight/2);
				canvas.drawBitmap(starBumBmp, src, dst, null);
				currentBumFrame++;
			}
			else {
				deathStar[0] = 0;
				currentBumFrame =0;
			}
		}}

	}
	
	private void updateStar() {
		if (System.currentTimeMillis() - this.borntime > this.lifetime){
			deathStar[0] = -1;
			deathStar[1] = this.x;
			deathStar[2] = this.y;
			starsList.remove(this);
			System.out.println("!!!!!!!!!! Star has badaBOOmed "+deathStar[0]);
		}
		currentFrame  = ++currentFrame % BMP_COLUMS;
	}
	
	private void createStar() {
		if (System.currentTimeMillis()-now>(3+rnd.nextInt(5))*1000){
			getCoord();
//			System.out.println("Star created at  "+x+" ; "+y);
			starsList.add(new Stars(x,y));
			now = System.currentTimeMillis();
		} 
		
	}
	
	private void getCoord() {
		boolean goodCoord = false;
		while (!goodCoord){
			
			x = rnd.nextInt((int)(screenWidth*0.87))+ (int)(screenWidth*0.07);
			y = rnd.nextInt((int)(screenHeight*0.8))+(int)(screenHeight*0.1);
			goodCoord = true;
			for (Circle cat : Circle.characters){
				int catX = cat.getX();
				int catY = cat.getY();
				int catR = cat.getR();
				if ( (x>(catX-catR-minDist)) && (x<(catX+catR+minDist)) && (y>(catY-catR-minDist)) && (y<(catY+catR+minDist))){
					goodCoord = false;
				} 
			}
		}
	}

	public void isTouched(float touchX, float touchY) {
		for (int i = 0; i < starsList.size(); i++){
			Stars star = starsList.get(i);
			if (touchX>star.x-width/2 && touchX<star.x+width/2 && touchY>star.y-height/2 && touchY<star.y+height/2){
				catchStar(i);
			}
			
		}
	}
	private void catchStar(int i) {
		deathStar[0] = 1;
		deathStar[1] = starsList.get(i).x;
		deathStar[2] = starsList.get(i).y;
		starsList.remove(i);
		starsListCatched++;
	}
	
	public static void releaseBitmap(){
		for (Stars star:starsList){
			if (star.starBmp !=null){
			star.starBmp.recycle();
			star.starBmp = null;
			}
		}
		
	}
	
	public static void clearStars(){
		starsList.clear();
	}

}
