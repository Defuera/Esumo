package ru.justdevelopers.esumo;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Gallery;
import android.widget.ImageView;

public class ImageAdapter extends BaseAdapter {
	/** The parent context */
	private Context myContext;

	private static int[] myImageIds = { R.drawable.chapter1, R.drawable.chapter2,
			R.drawable.comingsoon, R.drawable.constructorbox,
			R.drawable.chapter1 };

	/** Simple Constructor saving the 'parent' context. */
	public ImageAdapter(Context c) {
//		System.out.println("ImageAdapterConstructor");
		this.myContext = c;
	}

	/** Returns the amount of images we have defined. */
	public int getCount() {
		return this.myImageIds.length;
	}

	/* Use the array-Positions as unique IDs */
	public Object getItem(int position) {
//		System.out.println("getItem " + position);
		return position;
	}

	public long getItemId(int position) {
//		System.out.println("getItemId " + position);
		return position;
	}

	/**
	 * Returns a new ImageView to be displayed, depending on the position
	 * passed.
	 */
	public View getView(int position, View convertView, ViewGroup parent) {
		ImageView i = null;

//		System.out.println("getView");
		if (convertView == null) {

			i = new ImageView(this.myContext);

			i.setBackgroundResource(this.myImageIds[position]);

//			 i.setScaleType(ImageView.ScaleType.FIT_XY);

			i.setLayoutParams(new Gallery.LayoutParams(300, 300));

		} else {

			i = (ImageView) convertView;

		}

//		System.out.println("getView " + i);
		return i;
	}

}
