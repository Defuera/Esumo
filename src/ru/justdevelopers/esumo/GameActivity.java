package ru.justdevelopers.esumo;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class GameActivity extends Activity {
	public final static int MENU_NEW_GAME = 0;
	public final static int MENU = 1;
	public static int CURRENT_LEVEL = 0;
	public static int BG_IMAGE;
	public static int gameState = -1;

	public static final int VICTORY = 0;
	public static final int DEFEAT = 1;
	public static final int PAUSE = 2;
	public static final int PLAYING = 3;
//	public static final int CONSTRUCTOR = 4;
	public static int[][] levelData;
	
	private Button pauseButton; 
//	private SoundTrack menuMusic;
	
	private static Context ctx;
	
	public static Resources res;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		ctx = this;
		res = ctx.getResources();
//        menuMusic = new SoundTrack(this, R.raw.backgroundmusic, "game music", true);
//        menuMusic.setPlayable(true);
//        menuMusic.start();
		
//		SoundControl.stop();
//		SoundControl.playSound(this, R.raw.backgroundmusic, "menu music", true);
//		SoundControl.start();
		
		System.out.println("GAME ACT ~~~!!!~~~!!!~~~   lvl: " + CURRENT_LEVEL);

		setContentView(R.layout.game);

		pauseButton = (Button) findViewById(R.id.pauseButton);

		buttonClick();
		gameState = PLAYING;
	}
	
	public static void changeGameState(int gameState2){
		System.out.println("CHANGE GAME STATE");
		gameState = gameState2;
		if (gameState == VICTORY){
//			System.out.println("Percent of catched stars: "+Stars.getPercent());
			Intent intent = new Intent(ctx, GameMenuActivity.class);
			ctx.startActivity(intent);
			Stars.clearStars();
		}
		if (gameState == DEFEAT){
			Intent intent = new Intent(ctx, GameMenuActivity.class);
			ctx.startActivity(intent);
			Stars.clearStars();
		}
	}

	public void finishGameAct() {
		this.finish();
	}

	private void buttonClick() {

		pauseButton.setOnClickListener(new View.OnClickListener() {
			public void onClick(View v) {
				gameState = PAUSE;
				startActivity(new Intent(GameActivity.this,	GameMenuActivity.class));
			}
		});
	}
	
	@Override
	public void onDestroy(){
		super.onDestroy();
//		if (this.hasWindowFocus())SoundControl.stop();
		System.out.println("GameActivity has been destroyed!");
	}

	@Override
	public void onResume(){
		super.onResume();
//		SoundControl.stop();
//		SoundControl.playSound(this, R.raw.backgroundmusic, "menu music", true);
//        SoundControl.start();
	}
	
}
