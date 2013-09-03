package ru.justdevelopers.esumo;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.media.AudioManager;
import android.media.SoundPool;
import android.os.Looper;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

public class GameView extends SurfaceView implements SurfaceHolder.Callback {

	private Bitmap backgroundBitmap = null;
	private GameLoopThread gameLoopThread;

//	private GameView gv; // need to remove it
	// public static int height;
	// //this.getWindowManager().getDefaultDisplay().getWidth();
	// public static int width; //
	//
	private Circle circle = new Circle(); //
	private Stars stars;

	// ------------------------------------THREAD CLASS
	// START--------------------------
	@SuppressLint("WrongCall")
	public class GameLoopThread extends Thread {
		private SurfaceHolder surfaceHolder;
		private final int FPS = 65;

		public GameLoopThread(SurfaceHolder surfaceHolder) {
			this.surfaceHolder = surfaceHolder;

		}

		@Override
		public void run() {
			while (!isInterrupted()) {

				Canvas canvas = null;
				if (GameActivity.gameState == GameActivity.PLAYING) {
					// System.out.println(".");
					try {
						canvas = surfaceHolder.lockCanvas(null);
						synchronized (surfaceHolder) {
							onDraw(canvas);
						}
					}catch (NullPointerException e) {
						break;
					}
					finally {
						// do this in a finally so that if an exception is
						// thrown
						// during the above, we don't leave the Surface in an
						// inconsistent state
						if (canvas != null) {
							surfaceHolder.unlockCanvasAndPost(canvas);
						}
					}
				}

				try {
					sleep(1000 / FPS);
				} catch (InterruptedException e) {
					break;
				}

			}

		}

	}

	// -----------------------------------------THREAD CLASS
	// FINISH--------------------------------

	// private SoundPool sounds;
	// private int sExplosion;

	public GameView(Context context, AttributeSet attrs) {
		super(context);
//		gv = this;
		getHolder().addCallback(this);
		// Sprite.res = this.getResources();
		// sounds = new SoundPool(10, AudioManager.STREAM_MUSIC,0);
		// sExplosion = sounds.load(context, R.raw.backgroundmusic, 1);
	}

	@Override
	public boolean onTouchEvent(MotionEvent e) {
		circle.isTouched(e.getX(), e.getY(), e.getAction());
		stars.isTouched(e.getX(), e.getY());
		return true;
	}

	public void surfaceChanged(SurfaceHolder holder, int format, int width,
			int height) {
		// sounds.autoPause();
	}

	public void surfaceCreated(SurfaceHolder holder) {

		// sounds.play(sExplosion, 1.0f, 1.0f, 0, 0, 1.5f);

		System.out.println("CREATOIN");
		// height = gv.getHeight();
		// width = gv.getWidth();
		// Circle.setScreenParams(width, height);

		LevelParser lp = new LevelParser();
		lp.loadlevel(getContext());
		backgroundBitmap = BitmapFactory.decodeResource(getResources(),
				GameActivity.BG_IMAGE);
		backgroundBitmap.getScaledHeight(Main.height);
		backgroundBitmap.getScaledWidth(Main.width);
		stars = new Stars(getResources());

		gameLoopThread = new GameLoopThread(getHolder());
		gameLoopThread.start();

		// System.out.println("GameView sC "+" height "+gv.getHeight());
	}

	public void surfaceDestroyed(SurfaceHolder holder) {
		System.out.println("DESTRUCTION");
		boolean retry = true;

		gameLoopThread.interrupt();

		Circle.characters.clear();

		backgroundBitmap.recycle();
		backgroundBitmap = null;
		stars.releaseBitmap();
		Stars.clearStars(); // Is it nessesary here?
		// sounds.stop(streamID)

		GameActivity ga = new GameActivity();
		ga.finishGameAct(); // should use finish activity for result!
		System.gc();
	}

	@SuppressLint("WrongCall")
	@Override
	protected void onDraw(Canvas canvas) throws NullPointerException {
		
//		float k = (float) Main.width / backgroundBitmap.getWidth();
		
//		 canvas.scale(k, k, Main.width/2, Main.height/2); //ДОДЕЛАТЬ!
//		 -) Что бы грамотно масштабировалось на
//		 всех экранах!
		 try{
		 canvas.drawBitmap(backgroundBitmap, 0, 0, null);
		 }catch (NullPointerException e){
		 System.out.println("backgroundBitmap "+backgroundBitmap);
		 }
//		if (canvas != null) {
			canvas.scale(1, 1);
			circle.onDraw(canvas);
			stars.onDraw(canvas);
//		} else System.out.println("Canvas == null");
	}
}
