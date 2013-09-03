package ru.justdevelopers.esumo.characters;

import java.util.Random;

import ru.justdevelopers.esumo.Circle;
import ru.justdevelopers.esumo.GameActivity;

import ru.justdevelopers.esumo.R;

import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;

public class Protagonist extends Circle {
	private static Bitmap idExtremitiesBitmap = BitmapFactory.decodeResource(GameActivity.res,R.drawable.players_extremities);
	private static Bitmap idHeadBitmap = BitmapFactory.decodeResource(GameActivity.res,R.drawable.players_head);
	private static Bitmap idPaunchBitmap = BitmapFactory.decodeResource(GameActivity.res,R.drawable.players_paunch);
	
	private final int wC =10; //weakness coeficient
	private boolean poisoned = false;
	private long now;
	private Random rnd = new Random();
	private int pCounter=0;
	private int poisonDurability;
	private final int TYPE = 0;
	
	public Protagonist(int x, int y, int r) {
		setBunnable();
		int[] protCoord = { x, y, r, 10, Color.BLUE, wC, TYPE}; 	// ������ ���������:
														// {X,Y,R,DMG, COLOR}
		setData(protCoord);
		createSprite(idExtremitiesBitmap, idHeadBitmap, idPaunchBitmap);
	}
	
	public Protagonist(Resources res, int x, int y, int r) { // i dont really drive resources well...
		idExtremitiesBitmap = BitmapFactory.decodeResource(res,R.drawable.players_extremities);
		idHeadBitmap = BitmapFactory.decodeResource(res,R.drawable.players_head);
		idPaunchBitmap = BitmapFactory.decodeResource(res,R.drawable.players_paunch);
		setBunnable();
		int[] protCoord = { x, y, r, 10, Color.BLUE, wC, TYPE}; 	// ������ ���������:
														// {X,Y,R,DMG, COLOR}
		setData(protCoord);
		createSprite(idExtremitiesBitmap, idHeadBitmap, idPaunchBitmap);
	}
	
	protected void setPoisoned(){
		poisoned = true;
		poisonDurability = rnd.nextInt(5)+5;
//		System.out.println("Poisoned");
	}
	
	/** FatCat eats from time to time some sausages and grows up */
	@Override 
	protected void bun(){

//		if (poisoned) System.out.println("bun "+pCounter+" "+poisonDurability);
		int r = getR();
		if (poisoned && System.currentTimeMillis()-now>1000 && r>40){
			pCounter++;
			System.out.println("poison hits");
			setR(r-5);
			now = System.currentTimeMillis();
		}
		if (pCounter >= poisonDurability) {
//			System.out.println("FALSE");
			poisoned = false;
			pCounter = 0;
		}
	}

}
