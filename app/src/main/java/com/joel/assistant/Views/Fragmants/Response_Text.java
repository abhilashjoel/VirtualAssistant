package com.joel.assistant.Views.Fragmants;

import android.app.Activity;
import android.net.Uri;
import android.os.Bundle;
import android.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.joel.assistant.R;
import com.joel.assistant.utils.FragmentWithType;

import butterknife.Bind;
import butterknife.ButterKnife;

public class Response_Text extends FragmentWithType {

    View root;

    @Bind(R.id.Response_Text_Content)
    TextView Response_Content;

    public static Response_Text newInstance(String param1, String param2) {
        Response_Text fragment = new Response_Text();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    public Response_Text() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        root = inflater.inflate(R.layout.fragment_response__text, container, false);
        ButterKnife.bind(this, root);
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
}
