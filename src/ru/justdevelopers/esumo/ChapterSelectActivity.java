
package ru.justdevelopers.esumo;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.view.ViewGroup.LayoutParams;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.BaseAdapter;
import android.widget.Gallery;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.Toast;

public class ChapterSelectActivity extends Activity {

	private static mGallery gallery;
	protected static int CURRENT_CHAPTER;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		
		gallery = new mGallery(this);
		gallery.setSpacing(100);


		gallery.setOnItemClickListener(new OnItemClickListener() {
			// @Override
			public void onItemClick(AdapterView<?> arg0, View arg1, int arg2,long arg3) {
				switch (arg2) {
				case 0: {
					CURRENT_CHAPTER = 1;
					Intent intent = new Intent(ChapterSelectActivity.this, LevelSelectActivity.class);
					startActivity(intent);
					finish();
					System.out.println("CURRENT_CHAPTER " + CURRENT_CHAPTER);
					break;
				}
				}
			}

		});

		System.out.println("LevelSelectActivity has been created " + this);
		setContentView(gallery);

	}
	

}