package ru.justdevelopers.esumo.characters;


import ru.justdevelopers.esumo.Circle;
import ru.justdevelopers.esumo.GameActivity;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import ru.justdevelopers.esumo.R;

public class JunkCat extends Circle implements Enemy{
	private static Bitmap bmpIdExtremities = BitmapFactory.decodeResource(GameActivity.res,R.drawable.junk_extremities);
	private static Bitmap bmpIdHead = BitmapFactory.decodeResource(GameActivity.res,R.drawable.junk_head);
	private static Bitmap bmpIdPaunch = BitmapFactory.decodeResource(GameActivity.res,R.drawable.junk_paunch);
	private static  final int wC =15; //weakness coeficient	
	private final int TYPE = 4;
	
	public JunkCat(int x, int y, int r) {
		
		int[] enData = {x,y,r,10,Color.GREEN, wC, TYPE};   //������ ���������: {X,Y,R,DMG, COLOR}
		setData(enData);
		createSprite(bmpIdExtremities, bmpIdHead, bmpIdPaunch);
	}
	
	public JunkCat(Resources res, int x, int y, int r) {
		bmpIdExtremities = BitmapFactory.decodeResource(res,R.drawable.junk_extremities);
		bmpIdHead = BitmapFactory.decodeResource(res,R.drawable.junk_head);
		bmpIdPaunch = BitmapFactory.decodeResource(res,R.drawable.junk_paunch);
		int[] enData = {x,y,r,10,Color.GREEN, wC, TYPE};   //������ ���������: {X,Y,R,DMG, COLOR}
		setData(enData);
		createSprite(bmpIdExtremities, bmpIdHead, bmpIdPaunch);
	}
}
