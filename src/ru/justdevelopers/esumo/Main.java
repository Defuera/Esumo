package ru.justdevelopers.esumo;

import android.app.Activity;
import android.content.Intent;
import android.media.AudioManager;
import android.media.SoundPool;
import android.os.Bundle;
import android.view.Display;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.Toast;

public class Main extends Activity implements OnClickListener{
//	private SoundTrack menuMusic;
//	 private SoundPool sounds;
//     private int sExplosion;
	
	public static int width;
	public static int height;
	public static double resCoef; //Resolution coefficient 
	
    @SuppressWarnings("deprecation")
	@Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        
        Display display = getWindowManager().getDefaultDisplay();
        width = display.getWidth();
        height = display.getHeight();
        resCoef = (double) width/800;
        
        
        
        
        System.out.println("Screen resolution: "+width+"x"+height+"~~~~~~~~~~~~~~~~~~~~~~~~` "+resCoef);
        
//        menuMusic = new SoundTrack(this, R.raw.menu_music, "menu music", true);
//        menuMusic.setPlayable(true);
//        
//        menuMusic.start();
        
//        SoundControl.playSound(this, R.raw.menu_music, "menu music", true);
//        SoundControl.start();
        
        setContentView(R.layout.main);
        
        Button playButton = (Button) findViewById(R.id.play);
        playButton.setOnClickListener(this);
        Button exitButton = (Button) findViewById(R.id.exit);
        exitButton.setOnClickListener(this);
        Button rateButton = (Button) findViewById(R.id.rate);
        rateButton.setOnClickListener(this);
       
//        sounds = new SoundPool(10, AudioManager.STREAM_MUSIC,0);
//        sExplosion = sounds.load(getBaseContext(), R.raw.menu_music, 1);
    }
    
    public void onClick(View v)
    {
    	switch (v.getId())
    	{
    		case R.id.play:
    		{
    			System.out.println("/////////////////////////////");
    			System.out.println("//////////NEW__START/////////");
    			System.out.println("/////////////////////////////");
    			newGame();
//    	    	Intent intent = new Intent(this, Constructor.class);
//    			startActivity(intent);
       	    break;
    		}
    		case R.id.exit:
    		{
//    			Intent intent = new Intent(this, GameMenuActivity.class);
//				startActivity(intent);
//    			SoundControl.stop();
				finish();
    	    break;
    		}
    		case R.id.rate:
    		{
    			setContentView(R.layout.test);
//    	    	Intent intent = new Intent(this, HorScrollView.class);
//    			startActivity(intent);
//    	        sounds.play(sExplosion, 1.0f, 1.0f, 0, 0, 1.5f);
//    			Toast.makeText(this, "we don't need your shit!", Toast.LENGTH_SHORT).show();
    	    break;
    		}
    	}
    }
    
    public void newGame(){
//    	Intent intent = new Intent(this, LevelBoxActivity.class);
    	Intent intent = new Intent(this, ChapterSelectActivity.class);
		startActivity(intent);
//		finish();
    }
    
	@Override
	public void onDestroy(){
		super.onDestroy();
//		if (this.hasWindowFocus())SoundControl.stop();
	}
}

