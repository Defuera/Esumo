package ru.justdevelopers.esumo.characters;

import java.util.Random;

import ru.justdevelopers.esumo.Circle;
import ru.justdevelopers.esumo.GameActivity;

import ru.justdevelopers.esumo.R;

import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;

public class FatCat extends Circle implements Enemy{
	//Do it final anyway?
	private static Bitmap bmpIdExtremities = BitmapFactory.decodeResource(GameActivity.res,R.drawable.fat_extremities);
	private static Bitmap bmpIdHead = BitmapFactory.decodeResource(GameActivity.res,R.drawable.fat_head);
	private static Bitmap bmpIdPaunch = BitmapFactory.decodeResource(GameActivity.res,R.drawable.fat_paunch);
	
	private final int dmg =20;
	private final int color =Color.BLACK;
	private final int wC =25;
	private long now;
	private boolean increasable;
	private Random rnd = new Random();
	private final int TYPE = 2;
	
	public FatCat(int x, int y, int r) {
		setBunnable();
		int[] enData = {x,y,r,dmg,color,wC,TYPE};   //{startX, startY, startR, DMG}
		setData(enData);
		createSprite(bmpIdExtremities, bmpIdHead, bmpIdPaunch);
		increasable = false;
	}
	
	public FatCat(Resources res, int x, int y, int r) {
		bmpIdExtremities = BitmapFactory.decodeResource(res,R.drawable.fat_extremities);
		bmpIdHead = BitmapFactory.decodeResource(res,R.drawable.fat_head);
		bmpIdPaunch = BitmapFactory.decodeResource(res,R.drawable.fat_paunch);
		
		setBunnable();
		int[] enData = {x,y,r,dmg,color,wC,TYPE};   //{startX, startY, startR, DMG}
		setData(enData);
		createSprite(bmpIdExtremities, bmpIdHead, bmpIdPaunch);
		increasable = false;
	}
	
	/** FatCat eats from time to time some sausages and grows up */
	@Override 
	protected void bun(){
		int r = getR();
		if (!(increasable) && r < 120 && System.currentTimeMillis()-now>(5000+(rnd.nextInt(300)*10))){
			increasable = true;
			this.sprite.isEating = true;
			now = System.currentTimeMillis();
		} 
		else {
			Random rnd = new Random();
			int delta = rnd.nextInt(20)*100; 
			if ((increasable) &&  System.currentTimeMillis()-now > 1000+delta)	{
				increasable = false;
				this.sprite.isEating = false;
				now = System.currentTimeMillis();
			} 
		}
		if((increasable)&& r<130) {
			increaseR();
		}

	}
}
