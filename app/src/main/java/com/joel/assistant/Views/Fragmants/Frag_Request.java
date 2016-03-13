package com.joel.assistant.Views.Fragmants;

import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.os.Bundle;
import android.app.Fragment;
import android.speech.RecognizerIntent;
import android.speech.tts.TextToSpeech;
import android.support.design.widget.FloatingActionButton;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.EditorInfo;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

//import com.joel.assistant.``;
import com.joel.assistant.utils.TTS;
import com.joel.assistant.R;


import java.util.ArrayList;
import java.util.Locale;

import butterknife.ButterKnife;
import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class Frag_Request extends Fragment {

    View root;
    Communicator parentActivity;

    @Bind(R.id.Request_ip)
    EditText Request_ip;

    @OnClick(R.id.Request_ip)
    void Req_Onclick() {
        TTS.stopTTS();
    }

    @Bind(R.id.speech)
    FloatingActionButton fab;

    public static Frag_Request newInstance(String param1, String param2) {
        Frag_Request fragment = new Frag_Request();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    public Frag_Request() {
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
        root = inflater.inflate(R.layout.fragment_frag__request, container, false);
        ButterKnife.bind(this, root);


        Request_ip.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                if (actionId == EditorInfo.IME_ACTION_DONE) {
                    parentActivity.Process(Request_ip.getText().toString());
                }
                return false;
            }
        });


        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                TTS.stopTTS();
                PromptSpeechInput();
            }
        });


        return root;
    }


    private void PromptSpeechInput() {

        Intent i = new Intent(RecognizerIntent.ACTION_RECOGNIZE_SPEECH);

        i.putExtra(RecognizerIntent.EXTRA_LANGUAGE_MODEL, RecognizerIntent.LANGUAGE_MODEL_FREE_FORM);

        i.putExtra(RecognizerIntent.EXTRA_LANGUAGE, Locale.getDefault());
        i.putExtra(RecognizerIntent.EXTRA_PROMPT, "Tell your Query..");
        try {
            startActivityForResult(i, 1001);
        } catch (ActivityNotFoundException a) {
            Toast.makeText(root.getContext(),
                    "Error with Recognition",
                    Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void onActivityResult(int reqC, int resC, Intent data) {

        super.onActivityResult(reqC, resC, data);

        if (data == null)
            return;

        final ArrayList<String> result = data
                .getStringArrayListExtra(RecognizerIntent.EXTRA_RESULTS);

        if (result.isEmpty() == true)
            return;

        if (result.get(0).isEmpty() == true)
            return;
        parentActivity.Process(result.get(0));
        Request_ip.setText(result.get(0));
        System.out.println("----------------------Recogniosed Text :    " + result.get(0));
    }


    public void setCommunicator(Communicator c) {
        parentActivity = c;
    }

    public interface Communicator {
        public void Process(String s);
//        public void stopTTS();
    }

}
