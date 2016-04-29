package com.joel.assistant.Views.Fragmants;


import android.graphics.Bitmap;
import android.os.Bundle;
import android.app.Fragment;
import android.util.Log;
import android.view.GestureDetector;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.ScrollView;
import android.widget.TextView;

import com.joel.assistant.R;
import com.joel.assistant.utils.NewsHandler.Feeds;
import com.joel.assistant.utils.NewsHandler.NewsFeedsHandler;
import com.joel.assistant.utils.NewsHandler.NewsFragmentGestureListener;
//import com.joel.assistant.utils.NewsHandler.customListener;
import com.joel.assistant.utils.StateProvider;
import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;
import com.nostra13.universalimageloader.core.assist.LoadedFrom;
import com.nostra13.universalimageloader.core.display.BitmapDisplayer;
import com.nostra13.universalimageloader.core.imageaware.ImageAware;

import butterknife.Bind;
import butterknife.ButterKnife;


public class Fragment_News extends Fragment implements NewsFeedsHandler.NewsFeedsListener {

    View root;
    GestureDetector detector;


    @Bind(R.id.newsImage)
    ImageView newsImage;

    @Bind(R.id.newsTitle)
    TextView newsTitle;

    @Bind(R.id.newsContent)
    TextView newsContent;

    @Bind(R.id.BaseScrollView_News)
    ScrollView base_sv;

    static String Tag = "News Fragment";

    public static Fragment_News newInstance() {
        Log.i(Tag, "in newInstance()");
        Fragment_News fragment = new Fragment_News();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    public Fragment_News() {
        // Required empty public constructor
        Log.i(Tag, "News Fragment Constructor..");
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        Log.i(Tag, "On Create().");
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
        }

    }

    @Override
    public void onResume() {

        setHeight();
        Log.i(Tag, "onResume()..");
        super.onResume();
        try {
            initializeFeeds();
        } catch (Exception e) {
            e.printStackTrace();
        }

        NewsFeedsHandler.setListener(this);
        NewsFeedsHandler.start();

        showHeights();
    }

    private void setHeight() {
        final ViewTreeObserver vto = base_sv.getViewTreeObserver();
        vto.addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
            int count = 0;

            @Override
            public void onGlobalLayout() {
                Log.i("VTO in NewsFrag", "Listener Callback" + count);
//                vto.removeOnGlobalLayoutListener(this);

                base_sv.getViewTreeObserver().removeOnGlobalLayoutListener(this);

                RelativeLayout.LayoutParams lp = (RelativeLayout.LayoutParams) base_sv.getLayoutParams();
                lp.height = StateProvider.ResponseFragmentHeight;
                base_sv.setLayoutParams(lp);

                System.out.println("Frag Height(after mod) :  " + base_sv.getHeight());
            }
        });

    }

    private void showHeights() {

        LinearLayout base = (LinearLayout) getActivity().findViewById(R.id.BaseActivity);
        int base_height = base.getHeight();
        LinearLayout ip = (LinearLayout) getActivity().findViewById(R.id.Request_Fragment_main);
        int ip_height = ip.getHeight();
        LinearLayout op = (LinearLayout) getActivity().findViewById(R.id.Response_Fragment_main);
        int op_height = op.getHeight();

        System.out.println("Fragments Heights :  " + base_height + "  " + op.getHeight() + "  " + ip_height);
    }

    @Override
    public void onPause() {
/*        ScrollView base_sv = (ScrollView) this.getActivity().findViewById(R.id.BaseScrollView_News);
        base_sv.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                return false;
            }
        });
*/
        NewsFeedsHandler.removeListener();

        super.onPause();
    }

    private void initializeFeeds() {
        Log.i(Tag, "InitializeFeeds()..");
        detector = new GestureDetector(StateProvider.getContext(), NewsFragmentGestureListener.getInstance());

//        ScrollView base_sv = (ScrollView) StateProvider.getActivity().findViewById(R.id.BaseScrollView_News);

        if (base_sv == null)
            System.out.println("Null Reference");
        else
            System.out.println("Found Object...");

        base_sv.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
//                Log.i(Tag,"onTouch() callback...");
//                Log.i(Tag,"Passing Touch Event to detector");
                detector.onTouchEvent(event);
                return false;
            }
        });

        NewsFeedsHandler.setListener(this);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        Log.i(Tag, "onCreateView()...");
        root = inflater.inflate(R.layout.fragment__news, container, false);
        ButterKnife.bind(this, root);
        initUIL();

        newsTitle.setText("NewsFragment.....");
        return root;
    }

    private void initUIL() {
        Log.i(Tag, "initUIL()...");
        BitmapDisplayer bd = new BitmapDisplayer() {
            @Override
            public void display(Bitmap bitmap, ImageAware imageAware, LoadedFrom loadedFrom) {
                bitmap.setWidth(1000);
            }
        };
        DisplayImageOptions options = new DisplayImageOptions.Builder()
                .cacheInMemory(true)
                .cacheOnDisk(true)
//                .displayer(bd)
                .build();
        ImageLoaderConfiguration config = new ImageLoaderConfiguration.Builder(StateProvider.getActivity())
                .defaultDisplayImageOptions(options)
                .build();
        ImageLoader.getInstance().init(config);
    }

    public void showFeed(Feeds f) {
        Log.i(Tag, "showFeed()..");
        Log.i(Tag, "Feed.title : " + f.Title);
        newsTitle.setText(f.Title);
        newsContent.setText(f.Text);

        String URL = f.ImageURL;

        if (URL.isEmpty() == true) {
        }

        System.out.println("Image URL :  " + URL);

        ImageLoader imageLoader = ImageLoader.getInstance();
        imageLoader.displayImage(URL, newsImage);

    }

    @Override
    public void onStop() {
        super.onStop();
        Log.i(Tag, "OnStop().....");
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        Log.i(Tag, "onDetach()....");
    }
}
