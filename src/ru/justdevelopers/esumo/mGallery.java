package ru.justdevelopers.esumo;

import android.content.Context;
import android.graphics.Rect;
import android.view.MotionEvent;
import android.widget.Gallery;


@SuppressWarnings("deprecation")
public class mGallery extends Gallery {// implements OnGestureListener{
	
	private static final int SWIPE_MIN_DISTANCE = 100;
//	private static final int SWIPE_THRESHOLD_VELOCITY = 10;
//	private static final int SWIPE_PAGE_ON_FACTOR = 10;
	
//	private GestureDetector gestureDetector;
	private int scrollTo = 0;
	private int maxItem = 0;
	private int activeItem = 0;
//	private float prevScrollX = 0;
//	private boolean start = true;
	private int itemWidth = 0;
//	private float currentScrollX;
//	private boolean flingDisable = true;
	private static ImageAdapter ia;
	private boolean scrollingVertically = false;

	public mGallery(Context context){
		super(context);
//		System.out.println("mGallery has been created! "+maxItem+" "+itemWidth);
//		this.setSpacing(100);
		 //=true;
		ia = new ImageAdapter(context);
		this.setAdapter(ia);
		maxItem = ia.getCount();
		itemWidth = 400; //ia.getWidth(1);
	}
	
	
//    @Override
//    public boolean onInterceptTouchEvent(MotionEvent ev) {
//        super.onInterceptTouchEvent(ev);
//        return scrollingVertically;
//    }
//
//    @Override
//    public boolean onScroll(MotionEvent e1, MotionEvent e2, float distanceX, float distanceY) {
//        scrollingVertically = true;
//        return super.onScroll(e1, e2, distanceX, distanceY);
//    }

//    @Override
//    public boolean onTouchEvent(MotionEvent event) {
//        switch(event.getAction()) {
//        case MotionEvent.ACTION_UP:
//        case MotionEvent.ACTION_CANCEL:
//            scrollingVertically = false;
//        }
//        return super.onTouchEvent(event);
//    }
//
//	@Override
//	public void onFocusChanged(boolean gainFocus, int direction, Rect previouslyFocusedRect){
//		System.out.println("Focus has been changed");
//		
//	}

	@Override
		public boolean onFling(MotionEvent e1, MotionEvent e2, float velocityX, float velocityY) {
//		Sys001tem.out.println(". ");	
//		System.out.println("e1 "+e1);
//			System.out.println("e2 "+e2);
//			System.out.println("velocityX "+velocityX);
//			System.out.println("velocityY "+velocityY);
////			if (flingDisable)
////				return false;
////			System.out.println("flingAnable");
			boolean returnValue = false;
			float ptx1 = 0, ptx2 = 0;
//			if (e1 == null || e2 == null)
//				return false;
////			System.out.println("e != null");
			ptx1 = e1.getX();
			ptx2 = e2.getX();
//			// right to left
//			System.out.println("ptx1 "+ptx1);
//			System.out.println("ptx1-ptx2 "+Math.abs(ptx1-ptx2));
//		
			if (ptx1 - ptx2 > SWIPE_MIN_DISTANCE
//					&& Math.abs(velocityX) > SWIPE_THRESHOLD_VELOCITY
					){
				if (activeItem < maxItem - 1)
					activeItem = activeItem + 1;
//				
//				System.out.println("activeItem + 1");
				returnValue = true;
//
			} else if (ptx2 - ptx1 > SWIPE_MIN_DISTANCE
//					&& Math.abs(velocityX) > SWIPE_THRESHOLD_VELOCITY
					) {
				if (activeItem > 0)
					activeItem = activeItem - 1;
//
				
				returnValue = true;
			}
			//System.out.println("activeItem "+ activeItem);
//			
//			System.out.println("activeItem "+activeItem);
//			scrollTo = activeItem * itemWidth;
//			System.out.println("currX "+this.currentScrollX);
			
//			System.out.println("scrollTo "+scrollTo);
//			this.scrollTo(scrollTo, 0);
//			System.out.println("RV "+returnValue);
			return returnValue;
			
//
		}	
}
