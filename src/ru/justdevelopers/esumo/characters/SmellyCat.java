package ru.justdevelopers.esumo.characters;

import ru.justdevelopers.esumo.Circle;
import ru.justdevelopers.esumo.GameActivity;

import ru.justdevelopers.esumo.R;

import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;

public class SmellyCat extends Circle implements Enemy{
	private static Bitmap bmpIdExtremities = BitmapFactory.decodeResource(GameActivity.res,R.drawable.smelly_extremities);
	private static Bitmap bmpIdHead = BitmapFactory.decodeResource(GameActivity.res,R.drawable.smelly_head);
	private static Bitmap bmpIdPaunch = BitmapFactory.decodeResource(GameActivity.res,R.drawable.smelly_paunch);
	private final int wC =25; //weakness coeficient	
	private final int TYPE = 1;
	
	public SmellyCat(int x, int y, int r) {
		int[] enData = {x,y,r,10,Color.MAGENTA, wC, TYPE};   //������ ���������: {X,Y,R,DMG, COLOR}
		setData(enData);
		createSprite(bmpIdExtremities, bmpIdHead, bmpIdPaunch);
	}
	
	public SmellyCat(Resources res, int x, int y, int r) {
		bmpIdExtremities = BitmapFactory.decodeResource(res,R.drawable.smelly_extremities);
		bmpIdHead = BitmapFactory.decodeResource(res,R.drawable.smelly_head);
		bmpIdPaunch = BitmapFactory.decodeResource(res,R.drawable.smelly_paunch);
		
		int[] enData = {x,y,r,10,Color.MAGENTA, wC, TYPE};   //������ ���������: {X,Y,R,DMG, COLOR}
		setData(enData);
		createSprite(bmpIdExtremities, bmpIdHead, bmpIdPaunch);
	}
	
}
