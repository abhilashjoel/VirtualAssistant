package com.joel.assistant.Views.Fragmants;


import android.graphics.Bitmap;
import android.graphics.Point;
import android.os.Bundle;
import android.app.Fragment;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.joel.assistant.R;
import com.joel.assistant.utils.StateProvider;
import com.joel.assistant.utils.TTS;
import com.joel.assistant.utils.WolframAPI.ResultPod;
import com.joel.assistant.utils.WolframAPI.ResultPodList;
import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;
import com.nostra13.universalimageloader.core.assist.ImageScaleType;
import com.nostra13.universalimageloader.core.assist.LoadedFrom;
import com.nostra13.universalimageloader.core.display.BitmapDisplayer;
import com.nostra13.universalimageloader.core.imageaware.ImageAware;

import java.util.Iterator;

import butterknife.Bind;
import butterknife.ButterKnife;


/**
 * A simple {@link Fragment} subclass.
 */
public class NullFragment extends Fragment {
    public static String Tag = "Null Fragmengt";


    @Bind(R.id.BaseView_NullFragment)
    LinearLayout BaseView;

    ResultPodList res;

    View root;

    public NullFragment() {
        // Required empty public constructor
    }

    public static NullFragment newInstance(ResultPodList res) {
        NullFragment nf = new NullFragment();
        nf.res = res;
        return nf;
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        root = inflater.inflate(R.layout.fragment_null, container, false);
        ButterKnife.bind(this, root);
        initUIL();
        return root;
    }

    public void showResultPods(ResultPodList res) {

        Iterator<ResultPod> i = res.iterator();

        while (i.hasNext()) {
            ResultPod pod = i.next();
            if (pod.getTitle().equalsIgnoreCase("Result") == true) {
                TTS.speak(pod.getText());
            }
            LinearLayout ll = new LinearLayout(StateProvider.getActivity());
            ll.setOrientation(LinearLayout.VERTICAL);
            ll.setPadding(0, 10, 0, 10);
            ll.addView(getTextView(pod.getTitle()));
            ll.addView(getImageView(pod.getImageURL()));
            BaseView.addView(ll);
        }
    }

    public TextView getTextView(String text) {
        TextView tv = new TextView(StateProvider.getActivity());
//        LinearLayout.LayoutParams lp = tv.getLayoutParams();
        tv.setText(text);
        return tv;
    }

    public ImageView getImageView(String URL) {
        ImageView iv = new ImageView(StateProvider.getActivity());
        DisplayMetrics dMetrics = new DisplayMetrics();
        StateProvider.getActivity().getWindowManager().getDefaultDisplay().getMetrics(dMetrics);
//        iv.setMinimumWidth(dMetrics.widthPixels);

        ImageLoader imageLoader = ImageLoader.getInstance();
        imageLoader.displayImage(URL, iv);
        return iv;
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


}
