package com.joel.assistant.Views.Activities;

import android.app.FragmentManager;
import android.content.Context;
import android.graphics.Bitmap;
import android.speech.tts.TextToSpeech;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.AttributeSet;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.ViewTreeObserver;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.joel.assistant.R;

import com.joel.assistant.Views.Fragmants.Frag_Request;
import com.joel.assistant.utils.Handler_AI;
import com.joel.assistant.utils.ResponseHandler_AI;
import com.joel.assistant.utils.StateProvider;
import com.joel.assistant.utils.TTS;
import com.joel.assistant.utils.ContactsHandler.contactsHandler;
import com.joel.assistant.utils.MediaHandler.mediaTest;
import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;
import com.nostra13.universalimageloader.core.assist.ImageScaleType;
import com.nostra13.universalimageloader.core.assist.LoadedFrom;
import com.nostra13.universalimageloader.core.display.BitmapDisplayer;
import com.nostra13.universalimageloader.core.imageaware.ImageAware;

public class MainActivity extends AppCompatActivity implements Frag_Request.Communicator {

    TextToSpeech tts;
    FragmentManager m;

    public static String Tag = "Main Activity";

    @Override
    protected void onPause() {
        super.onPause();
        TTS.stopTTS();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        TTS.stopTTS();
    }

    @Override
    protected void onStart() {
        super.onStart();

//        ResponseHandler_AI.TextResponse("Hello! What can I do for you.");
//        TTS.speak("Hello! What can I do for you.");

        final LinearLayout base = (LinearLayout) findViewById(R.id.BaseActivity);

        final ViewTreeObserver vto = base.getViewTreeObserver();
        vto.addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
            int count = 0;

            @Override
            public void onGlobalLayout() {
                try {
                    System.out.println("Vto status  " + vto.isAlive());
                    base.getViewTreeObserver().removeOnGlobalLayoutListener(this);
                    vto.removeOnGlobalLayoutListener(this);
                } catch (Exception e) {
                    e.printStackTrace();
                }
                if (count > 0) {
                    return;
                }
                LinearLayout base = (LinearLayout) findViewById(R.id.BaseActivity);
                int base_height = base.getHeight();
                LinearLayout ip = (LinearLayout) findViewById(R.id.Request_Fragment_main);
                int ip_height = ip.getHeight();
                int op_height = base_height - ip_height;
                LinearLayout op = (LinearLayout) findViewById(R.id.Response_Fragment_main);
                System.out.println("Op Height Before :  " + op.getHeight());
///                op.setMinimumHeight(op_height - 20);
                LinearLayout.LayoutParams lp = (LinearLayout.LayoutParams) op.getLayoutParams();
                lp.height = op_height;
                op.setLayoutParams(lp);
                StateProvider.ResponseFragmentHeight = op_height;
                System.out.println("Base   " + base_height + " ip :  " + ip_height + " op :  " + op_height);
                System.out.println("Op Height After :  " + op.getHeight());
            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
        initUIL();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

//        getActionBar().hide();
        StateProvider.setContext(getApplicationContext());
        StateProvider.setActivity(this);

        initTTS();
        TTS.initTTS(getApplicationContext());

        Handler_AI.setContext(getApplicationContext());

        Response_Fragments.setActivity(this);
        try {
            Response_Fragments.initFragments();
        } catch (Exception e) {
            e.printStackTrace();
        }

        Handler_AI.updateSession("Call me Joel");

        //       ResponseHandler_AI.TextResponse("What can I do for you");


    }

    private void initUIL() {
        Log.i(Tag, "initUIL()...");
        BitmapDisplayer bd = new BitmapDisplayer() {
            @Override
            public void display(Bitmap bitmap, ImageAware imageAware, LoadedFrom loadedFrom) {
                DisplayMetrics dMetrics = new DisplayMetrics();
                StateProvider.getActivity().getWindowManager().getDefaultDisplay().getMetrics(dMetrics);
//                bitmap.setWidth(dMetrics.widthPixels);
            }
        };
        DisplayImageOptions options = new DisplayImageOptions.Builder()
                .cacheInMemory(true)
                .cacheOnDisk(true)
                .imageScaleType(ImageScaleType.EXACTLY_STRETCHED)
//                .displayer(bd)
                .build();
        ImageLoaderConfiguration config = new ImageLoaderConfiguration.Builder(StateProvider.getActivity())
                .defaultDisplayImageOptions(options)
                .build();
        ImageLoader.getInstance().init(config);
    }

    private void initTTS() {


    }


    @Override
    public void Process(String s) {
        System.out.println("Processing Query.....  " + s);
        ResponseHandler_AI.TextResponse("Processing...", "");
        Handler_AI.process_update(s);
    }


}
