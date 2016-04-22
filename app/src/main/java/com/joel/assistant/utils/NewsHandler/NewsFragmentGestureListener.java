package com.joel.assistant.utils.NewsHandler;

/**
 * Created by Joel on 23-04-2016.
 */
public class NewsFragmentGestureListener extends com.joel.assistant.Listeners.OnSwipeListener {

    public static NewsFragmentGestureListener listener = new NewsFragmentGestureListener();

    private NewsFragmentGestureListener() {

    }

    public static NewsFragmentGestureListener getInstance() {
        return listener;
    }


    @Override
    public boolean onSwipe(Direction direction) {

        if (direction.compareTo(Direction.left) == 0) {
            NewsFeedsHandler.nextFeed();
        } else if (direction.compareTo(Direction.right) == 0) {
            NewsFeedsHandler.previousFeed();
        }

        return super.onSwipe(direction);
    }
}
