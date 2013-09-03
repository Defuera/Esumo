package ru.justdevelopers.esumo;

import java.util.List;
import java.util.Random;
import java.util.concurrent.CopyOnWriteArrayList;

import ru.justdevelopers.esumo.characters.FatCat;
import ru.justdevelopers.esumo.characters.JunkCat;
import ru.justdevelopers.esumo.characters.Protagonist;
import ru.justdevelopers.esumo.characters.SmellyCat;
import ru.justdevelopers.esumo.characters.TeddyCat;


import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.view.Display;

public class Circle {
	public static List<Circle> characters = new CopyOnWriteArrayList<Circle>();
	public static final int rMax = (int) (250*Main.resCoef);
	public int rMin = (int) (50*Main.resCoef);
	
	private final int S = 5; /* ! Speed constant ! */
	private double xSpeed;
	private double ySpeed;
	int x; // PRIVATE!
	int y;
	int r;
	private int dmg;
	private int TYPE;
	private static int screenWidth = Main.width; // should i do that, or just use Main.width locally? 
	private static int screenHeight = Main.height;
	
	private boolean bunnable = false; // if character has any bun
	private double wC; // weakness coefficient
//	private int color;
//	private Paint paint = new Paint();
	private static Circle pressedCircle; // appears it must be static!
	private static long prevTime = 0;
	
	public Sprite sprite; //public only for constructor, remove when constroctor will be removed
	

	/** Constructor */
	public Circle() {
		
//		System.out.println("NEW Circle");
		Random rnd = new Random();
			int sign = -1;
			if (rnd.nextInt(2)== 0) sign = 1;
			this.ySpeed=rnd.nextInt(S)*0.75*sign; 
			this.xSpeed=Math.sqrt((S*S-ySpeed*ySpeed))*sign;
	}

	/** public methods */
	public void onDraw(Canvas canvas) {
		for (Circle circle : characters) {
//			paint.setColor(circle.color); // need to be set only once
//			paint.setAntiAlias(true); //
//			canvas.drawCircle(circle.x, circle.y, circle.r, paint);
			if (circle.sprite != null)
			circle.sprite.onDraw(canvas,circle.x,circle.y, circle.r);
			testCollision();
			circle.updateCircle();			
		}
	}
	
//	public static void setScreenParams(int width, int height){
//		screenWidth = width;
//		screenHeight = height;
//	}
	
	protected void updateCircle() {
		x = x + (int) xSpeed;
		y = y + (int) ySpeed;
		if (bunnable) bun();
	}

	public void isTouched(float touchX, float touchY, int action) {
		for (Circle circle : characters) {
			if (circle instanceof Protagonist) {
				if ((touchX - circle.x) * (touchX - circle.x)+ (touchY - circle.y) * (touchY - circle.y) < circle.r	* circle.r) {
					pressedCircle = circle;
					circle.sprite.isEating = true;
					circle.increaseR();
				}
				else {
					pressedCircle = null;
					circle.sprite.isEating = false;
				}
				if (action == 1){
					pressedCircle = null;
					circle.sprite.isEating = false;
				}
			}
		}
	}

	public int getX() {
		return x;
	}

	public int getY() {
		return y;
	}

	public int getR() {return r;}
	protected void setR(int r){this.r = r;}
	
	/** protected methods */
	protected void increaseR() {
		if (r < rMax && System.currentTimeMillis() - prevTime > 60) {
			r++;
			prevTime = System.currentTimeMillis();
		}
	}

	protected void createSprite(Bitmap idextremitiesbitmap, Bitmap idheadbitmap, Bitmap idpaunchbitmap) {
		sprite = new Sprite(idextremitiesbitmap, idheadbitmap, idpaunchbitmap);
//		System.out.println(idheadbitmap+"~~~~~~~~~inCircle.createSprite");
	}

	protected void setData(int[] data) {
		x = data[0];
		y = data[1];
		r = data[2];
		dmg = data[3];
//		color = data[4];
		wC = data[5] / 10;
		TYPE = data[6];
	}

	protected void setBunnable() {
		bunnable = true;
	}

	// Bun is a method that declares specificity of character 
	protected void bun() {};


	/** private methods */
	private void testCollision() {
		for (int i = 0; i < characters.size(); i++) {
			Circle icircle = characters.get(i);

			wallCollision(icircle);

			if (i < characters.size()-1)
				for (int j = i+1; j < characters.size(); j++) {
					Circle jcircle = characters.get(j);

					double dist = Math.sqrt((icircle.x - jcircle.x)	* (icircle.x - jcircle.x) + 
													(icircle.y - jcircle.y)	* (icircle.y - jcircle.y));

					if (dist <= (icircle.r + jcircle.r)){		
						if (((icircle.TYPE==0)  || (jcircle.TYPE==0))&& (icircle.TYPE != jcircle.TYPE)){

							if (icircle.TYPE == 4) jcircle.setPoisoned();
							if (jcircle.TYPE == 4) icircle.setPoisoned();

							if (icircle.equals(pressedCircle)) {	transform(icircle, jcircle, i); }
							else if (jcircle.equals(pressedCircle)) 	transform(jcircle, icircle, j);

							if (icircle.TYPE !=  jcircle.TYPE) {
								if (icircle.TYPE == 0)	battle(icircle, jcircle, i, j);
								else if (jcircle.TYPE == 0)	battle(jcircle, icircle, j, i);
							}
						}
						colResponse(icircle, jcircle);
					}
				}
		}
	}
	
	private void battle(Circle prot, Circle enemy, int n1, int n2) {
		prot.r -= enemy.dmg * prot.wC;
		System.out.println("BATTLE "); 
		
		if (prot.r < rMin){
			transform(prot, enemy, n1);
		}
		else{
			enemy.r -= prot.dmg * enemy.wC;
			if (enemy.r < rMin){
				transform(enemy, prot, n2);
			}
		}
			

	}

	private void transform(Circle trans, Circle corresp, int n) {
		System.out.println(". "); 
		System.out.println("transform start");
		int x = trans.x;
		int y = trans.y;
		int r = 80;//trans.r - 10; // which radius should be here??
		if (!(corresp instanceof TeddyCat))
			r = 60; // which radius should be here??

		double xSpeed = trans.xSpeed;
		double ySpeed = trans.ySpeed;

		if (characters.size() != 0) { // WTF it's not working without it - ArrayIndexoutOfBoundsException is thrown
			System.out.println(characters.size()+" "+n+" ~~~~~~~~~~~~~~~~~~~~~~");
			System.out.println("transform "+characters.get(n)+" n "+n);
			characters.remove(n);
		trans = getNewChar(corresp, x, y, r);
		
		trans.x = x;
		trans.y = y;
		trans.r = r;
		trans.xSpeed = xSpeed;
		trans.ySpeed = ySpeed;
		characters.add(n, trans);
		}
		isEnd();
		System.out.println("transform end");
		System.out.println(". "); 
	}
	
	private void isEnd() {
		System.out.println("isend");
		int counter = 0;
		int iceCounter = 0;
		for (Circle circle : characters) {
			if (circle.TYPE == 0)	counter++;
			if (circle.TYPE == 3)	iceCounter++;
	}	
		if (counter == characters.size() - iceCounter || counter == 0) {
			if (counter == 0) {
				System.out.println("DEFEAT");
				GameActivity.changeGameState(GameActivity.DEFEAT); 
			}
			else {
				System.out.println("VICTORY");
				GameActivity.changeGameState(GameActivity.VICTORY); 
			}		
			characters.clear();
			System.out.println("isEnd true______ "+GameActivity.gameState);
			for (Circle circle : characters) circle.releaseSprite();	
		}
}
	
// Switch-case ? RTTI ??
	private Circle getNewChar(Circle corresp, int x, int y, int r) {
		Circle c = null;
		
		switch (corresp.TYPE){
			case 0:
			{
				c = new Protagonist(x, y, r);
			}
			break;	
			case 1:
			{
				c = new SmellyCat(x, y, r);
			}
			break;
			case 2:
			{
				c = new FatCat(x, y, r);
			}
			break;
			case 3:
			{
				c = new TeddyCat(x, y, r);
			}
			break;
			case 4:
			{
				c = new JunkCat(x, y, r);
			}
			break;
		}
		return c;
	}
		
	protected void setPoisoned() {}

	private void wallCollision(Circle icircle) {
		if (((icircle.x + icircle.r > screenWidth) && (icircle.xSpeed > 0))|| ((icircle.x - icircle.r < 0) && (icircle.xSpeed < 0))) {
			icircle.xSpeed = -icircle.xSpeed;
		}
		if (((icircle.y + icircle.r > screenHeight) && (icircle.ySpeed > 0))	|| ((icircle.y - icircle.r < 0) & (icircle.ySpeed < 0))) {
			icircle.ySpeed = -icircle.ySpeed;
		}
		
	}

	private void colResponse(Circle icircle, Circle jcircle){
		double t = Math.atan2(icircle.y - jcircle.y, icircle.x - jcircle.x);
		double x1 = icircle.xSpeed;
		double y1 = icircle.ySpeed;
		double x2 = jcircle.xSpeed;
		double y2 = jcircle.ySpeed;
		double px1 = x1 * Math.cos(t) + y1 * Math.sin(t);
		double py1 = y1 * Math.cos(t) - x1 * Math.sin(t);
		double px2 = x2 * Math.cos(t) + y2 * Math.sin(t);
		double py2 = y2 * Math.cos(t) - x2 * Math.sin(t);
		double ppx1 = px1 * Math.cos(t);
		double dif;
		if ((Math.abs(px1 / Math.abs(px1) + px2 / Math.abs(px2)) == 2)) {
			if (Math.abs(px1) > Math.abs(px2)) {
				px2 = px1 + px2;
				py2 = py1 + py2;
				dif = S / Math.sqrt((px2 * px2 + py2 * py2));
				px2 = px2 * dif;
				py2 = py2 * dif;
				px1 = -px1;
				x1 = px1 * Math.cos(t) - py1 * Math.sin(t);
				y1 = py1 * Math.cos(t) + px1 * Math.sin(t);
				x2 = px2 * Math.cos(t) - py2 * Math.sin(t);
				y2 = py2 * Math.cos(t) + px2 * Math.sin(t);
			} else {
				px1 = px1 + px2;
				py1 = py1 + py2;
				dif = S / Math.sqrt((px1 * px1 + py1 * py1));
				px1 = px1 * dif;
				py1 = py1 * dif;
				px2 = -px2;
				x1 = px1 * Math.cos(t) - py1 * Math.sin(t);
				y1 = py1 * Math.cos(t) + px1 * Math.sin(t);
				x2 = px2 * Math.cos(t) - py2 * Math.sin(t);
				y2 = py2 * Math.cos(t) + px2 * Math.sin(t);
			}
		} 
		else 
			if (((icircle.x < jcircle.x) == (ppx1 >= 0))) {
			px1 = -px1;
			px2 = -px2;
			x1 = px1 * Math.cos(t) - py1 * Math.sin(t);
			y1 = py1 * Math.cos(t) + px1 * Math.sin(t);
			x2 = px2 * Math.cos(t) - py2 * Math.sin(t);
			y2 = py2 * Math.cos(t) + px2 * Math.sin(t);
		}
	
		icircle.xSpeed = x1;
		icircle.ySpeed = y1;
		jcircle.xSpeed = x2;
		jcircle.ySpeed = y2;
	}
	
	
	private void releaseSprite(){
		System.out.println("Circle.releaseSprite");
			this.sprite.releaseBitmap();
			this.sprite = null;
	}
}
