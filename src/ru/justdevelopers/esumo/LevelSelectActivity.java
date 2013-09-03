package ru.justdevelopers.esumo;

import java.util.ArrayList;
import java.util.List;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.Toast;

public class LevelSelectActivity extends Activity implements OnClickListener{

	private static List<Button> lvlButtons = new ArrayList<Button>();
	private final int catpad00 =R.drawable.catpad00;
	private final int catpad0 = R.drawable.catpad0;
	private final int catpad1 = R.drawable.catpad1;
	private final int catpad2 = R.drawable.catpad2;
	private final int catpad3 = R.drawable.catpad3;
//	private final int catpad4 = R.drawable.catpad4;
	private static int[] state = new int[21]; // ArrayList<Integer>(20);// = {; //{-2, 0,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1}; // OMG, what's this??? ah, ok I see...
	
	private static SharedPreferences mSettings; 
	private static final String LEVEL_STATE = "levelState"; // òèï String
	private static final String APP_PREFERENCES = "KSSettings"; // ýòî áóäåò èìåíåì ôàéëà íàñòðîåê
	
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

//		SoundControl.stop();
//		SoundControl.playSound(this, R.raw.menu_music, "menu music", true);
//		SoundControl.start();
        
	System.out.println("GAME MENU ACT");
		
		
		mSettings = getSharedPreferences(APP_PREFERENCES, Context.MODE_PRIVATE);
		for (int i = 1; i<=20; i++)
				state[i] = mSettings.getInt(LEVEL_STATE+Integer.toString(i),0);
		
		state[0] =-2;
		if (state[1] == -1) state[1] = 0;

        
        setContentView(R.layout.selectlevel);
        
        lvlButtons.clear();
        
        lvlButtons.add((Button) findViewById(R.id.backtoboxbutton));        
        lvlButtons.add((Button) findViewById(R.id.buttonlvl1));
        lvlButtons.add((Button) findViewById(R.id.buttonlvl2));
        lvlButtons.add((Button) findViewById(R.id.buttonlvl3));
        lvlButtons.add((Button) findViewById(R.id.buttonlvl4));
        lvlButtons.add((Button) findViewById(R.id.buttonlvl5));
        lvlButtons.add((Button) findViewById(R.id.buttonlvl6));
        lvlButtons.add((Button) findViewById(R.id.buttonlvl7));
        lvlButtons.add((Button) findViewById(R.id.buttonlvl8));
        lvlButtons.add((Button) findViewById(R.id.buttonlvl9));
        lvlButtons.add((Button) findViewById(R.id.buttonlvl10));
        lvlButtons.add((Button) findViewById(R.id.buttonlvl11));
        lvlButtons.add((Button) findViewById(R.id.buttonlvl12));
        lvlButtons.add((Button) findViewById(R.id.buttonlvl13));
        lvlButtons.add((Button) findViewById(R.id.buttonlvl14));
        lvlButtons.add((Button) findViewById(R.id.buttonlvl15));
        lvlButtons.add((Button) findViewById(R.id.buttonlvl16));
        lvlButtons.add((Button) findViewById(R.id.buttonlvl17));
        lvlButtons.add((Button) findViewById(R.id.buttonlvl18));
        lvlButtons.add((Button) findViewById(R.id.buttonlvl19));
        lvlButtons.add((Button) findViewById(R.id.buttonlvl20));
        
        for (int i = 0; i<=20; i++){
        	lvlButtons.get(i).setOnClickListener(this);
        	
        	if (state[i] == -1) lvlButtons.get(i).setBackgroundResource(catpad00);
        	else if (state[i] == 0) lvlButtons.get(i).setBackgroundResource(catpad0);
        	else if (state[i] == 1) lvlButtons.get(i).setBackgroundResource(catpad1);
        	else if (state[i] == 2) lvlButtons.get(i).setBackgroundResource(catpad2);
        	else if (state[i] == 3) lvlButtons.get(i).setBackgroundResource(catpad3);
        	else if (state[i] == 4) lvlButtons.get(i).setBackgroundResource(catpad3); // ATTENTION WTF?!?!?
        }
        
    }

 // need to be optimized
public static void setState(int percent, int lvl) {
	state[lvl+1] = 0;
	int score=-1;
	if (percent<50) score = 0; 
	else if (percent<70) score = 1; 
	else if (percent<80) score = 2; 
	else if (percent<90) score = 3;
	else if (percent>90) score = 4; 

	
	Editor editor = mSettings.edit();
	if (score > state[lvl]) 
		editor.putInt(LEVEL_STATE+Integer.toString(lvl), score);
	editor.putInt(LEVEL_STATE+Integer.toString(lvl+1), 0);
	editor.commit();
}


public void onClick(View v) {
	for (int i = 0; i<=20; i++){
		if (lvlButtons.get(i).getId() == v.getId())
			if (state[i] == -2) {
				Intent intent = new Intent(LevelSelectActivity.this, ChapterSelectActivity.class);
        		startActivity(intent);
        		finish();
			}
			else if (state[i] != -1){
				GameActivity.CURRENT_LEVEL = i;
            	Intent intent = new Intent(LevelSelectActivity.this, GameActivity.class);
        		startActivity(intent);
        		finish();
			}
			else Toast.makeText(this, "This level isn't available yet", Toast.LENGTH_LONG).show();
	}

}

@Override
public void onDestroy(){
	super.onDestroy();
//	if (this.hasWindowFocus()) SoundControl.stop();
}

}