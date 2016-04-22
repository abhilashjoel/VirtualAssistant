package com.joel.assistant.Views.Fragmants;

import android.app.Activity;
import android.net.Uri;
import android.os.Bundle;
import android.app.Fragment;
import android.support.design.widget.FloatingActionButton;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.joel.assistant.R;
import com.joel.assistant.utils.FragmentWithType;
import com.joel.assistant.utils.StateProvider;

import butterknife.Bind;
import butterknife.ButterKnife;

public class Response_Text extends FragmentWithType {

    View root;
    public static String Tag = "Text Response";
    public static String TAG_TEXT = "text";

    String text;

    @Bind(R.id.Response_Text_Content)
    TextView Response_Content;

    public static Response_Text newInstance(String text) {
        Response_Text fragment = new Response_Text();
        Bundle args = new Bundle();
        args.putString(TAG_TEXT, text);
        fragment.setArguments(args);
        return fragment;
    }

    public Response_Text() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.i(Tag, "onCreate()...");
        if (getArguments() != null) {
            text = getArguments().getString(TAG_TEXT);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        root = inflater.inflate(R.layout.fragment_response__text, container, false);
        Log.i(Tag, "onCreateVIew()...");
        Log.i(Tag, "Root..  " + root);
        ButterKnife.bind(this, root);
        update(text);
        return root;
    }

    public void update(String s) {
        Response_Content.setText(s);
        Log.i("Response_Text.update()", s);
    }

    @Override
    public Boolean isText() {
        return true;
    }

    @Override
    public Boolean isMap() {
        return false;
    }

    @Override
    public Boolean isWeb() {
        return false;
    }

    @Override
    public void onStart() {
        super.onStart();
        Log.i(Tag, "onStart()...");
    }

}
