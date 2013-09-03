package ru.justdevelopers.esumo;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class GameMenuActivity extends Activity {

	private Button resumeButton;
	private Button restartButton;
	private Button nextLevelButton;
	// private Button mainMenuButton;
	private Button selectLevelButton;
	private TextView text;
	private TextView percentageText;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		System.out.println("GAME MENU ACT");

		/** This settings should to be set in styles or manifest! **/

		setContentView(R.layout.gamemenu);
		
		text = new TextView(this);
		text = (TextView) findViewById(R.id.gamemenutextview);
		text.setText("DEFEAT");

		percentageText = new TextView(this);
		percentageText = (TextView) findViewById(R.id.percentagetextview);
		percentageText.setText("You didn't pass this level yet");
		// percentageText.setText("Your previous record is");

		resumeButton = (Button) findViewById(R.id.resume);
		if (GameActivity.gameState == GameActivity.PAUSE) {
			text.setText("Pause");
		} else {
			View b = findViewById(R.id.resume);
			b.setVisibility(View.GONE);
		} // set invisible

		nextLevelButton = (Button) findViewById(R.id.nextlevel);
		if (GameActivity.gameState == GameActivity.VICTORY) {
			text.setText("Victory");
			int percent = Stars.getPercent();
			percentageText.setText("You've collected "+ Integer.toString(percent) + "% of stars!");
			LevelSelectActivity.setState(percent, GameActivity.CURRENT_LEVEL);
		} else {
			View b = findViewById(R.id.nextlevel);
			b.setVisibility(View.GONE);
		} // set invisible
		restartButton = (Button) findViewById(R.id.restart);
		selectLevelButton = (Button) findViewById(R.id.selectlevel);
		// mainMenuButton = (Button) findViewById(R.id.menu);

		menuButtonClick();
	}

	public void menuButtonClick() {

		resumeButton.setOnClickListener(new View.OnClickListener() {
			public void onClick(View v) {
				GameActivity.gameState = GameActivity.PLAYING;
				finish();
			}
		});

		restartButton.setOnClickListener(new View.OnClickListener() {
			public void onClick(View v) {
				Intent intent = new Intent(GameMenuActivity.this,GameActivity.class);
				startActivity(intent);
				finish();
			}
		});

		nextLevelButton.setOnClickListener(new View.OnClickListener() {
			public void onClick(View v) {
				if (GameActivity.CURRENT_LEVEL == 10)
					GameActivity.CURRENT_LEVEL = 1;
				else
					GameActivity.CURRENT_LEVEL++;
				Intent intent = new Intent(GameMenuActivity.this,
						GameActivity.class);
				startActivity(intent);
				finish();
				System.out.println("next level button pressed");
			}
		});

		selectLevelButton.setOnClickListener(new View.OnClickListener() {
			public void onClick(View v) {
				Intent intent = new Intent(GameMenuActivity.this,
						LevelSelectActivity.class);
				startActivity(intent);
				finish();
			}
		});
	}
	
	@Override
	public void onDestroy(){
		super.onDestroy();
	}
	

}

