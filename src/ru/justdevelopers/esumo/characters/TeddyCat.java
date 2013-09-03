package ru.justdevelopers.esumo.characters;

import ru.justdevelopers.esumo.Circle;
import ru.justdevelopers.esumo.GameActivity;

import ru.justdevelopers.esumo.R;

import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;

public class TeddyCat extends Circle implements Enemy{
	private static Bitmap bmpIdExtremities = BitmapFactory.decodeResource(GameActivity.res,R.drawable.teddy_extremities);
	private static Bitmap bmpIdHead = BitmapFactory.decodeResource(GameActivity.res,R.drawable.teddy_head);
	private static Bitmap bmpIdPaunch = BitmapFactory.decodeResource(GameActivity.res,R.drawable.teddy_paunch);
	private final int dmg = 0;
	private final int color =Color.GRAY;
	private final int wC = 0;
	private final int TYPE = 3;
	
	public TeddyCat(int x, int y, int r) {
		int[] enData = {x,y,r,dmg,color,wC,TYPE};   //{startX, startY, startR, DMG}
		setData(enData);
		createSprite(bmpIdExtremities, bmpIdHead, bmpIdPaunch);

	}

	public TeddyCat(Resources res, int x, int y, int r) {
		bmpIdExtremities = BitmapFactory.decodeResource(res,R.drawable.teddy_extremities);
		bmpIdHead = BitmapFactory.decodeResource(res,R.drawable.teddy_head);
		bmpIdPaunch = BitmapFactory.decodeResource(res,R.drawable.teddy_paunch);
		int[] enData = {x,y,r,dmg,color,wC,TYPE};   //{startX, startY, startR, DMG}
		setData(enData);
		createSprite(bmpIdExtremities, bmpIdHead, bmpIdPaunch);

	}
	
}
