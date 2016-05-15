package com.joel.assistant.utils;

import android.os.Bundle;
import android.speech.RecognitionListener;
import android.speech.SpeechRecognizer;
import android.widget.Toast;

import com.joel.assistant.Views.Activities.MainActivity;
import com.joel.assistant.Views.Fragmants.Frag_Request;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.Set;

/**
 * Created by Joel on 19-04-2016.
 */
public class SpeechRecognitionListener implements RecognitionListener {

    private Frag_Request.Communicator communicator;

    @Override
    public void onReadyForSpeech(Bundle params) {
        Toast.makeText(StateProvider.getActivity(), "Listening..", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onBeginningOfSpeech() {
    }

    @Override
    public void onRmsChanged(float rmsdB) {
        System.out.println("onRmsChanged()  " + rmsdB);

    }

    @Override
    public void onBufferReceived(byte[] buffer) {

        System.out.println("onBufferReceived()  " + new String(buffer));
    }

    @Override
    public void onEndOfSpeech() {

    }

    @Override
    public void onError(int error) {
        String errorMessage = diagnoseErrorCode(error);
        System.out.println("onError....................................");
        System.out.println(errorMessage);

    }

    @Override
    public void onResults(Bundle results) {
        System.out.println("From Bundle :  " + results);
        Set<String> keys = results.keySet();
        Iterator<String> ki = keys.iterator();
        while (ki.hasNext())
            System.out.println("Key :  " + ki.next());

        ArrayList<String> res = (ArrayList<String>) results.get("results_recognition");
        Iterator<String> i = res.iterator();
        while (i.hasNext())
            System.out.println("Contents :  " + i.next());
        communicator.Process(res.get(0));

    }

    @Override
    public void onPartialResults(Bundle partialResults) {


        ArrayList<String> res = (ArrayList<String>) partialResults.get("RESULTS_RECOGNITION");
        Iterator<String> i = res.iterator();
        while (i.hasNext())
            System.out.println("Contents :  " + i.next());
    }

    @Override
    public void onEvent(int eventType, Bundle params) {

    }

    public static String diagnoseErrorCode(int errorCode) {
        String message;
        switch (errorCode) {
            case SpeechRecognizer.ERROR_AUDIO:
                message = "Audio recording error";
                break;
            case SpeechRecognizer.ERROR_CLIENT:
                message = "Client side error";
                break;
            case SpeechRecognizer.ERROR_INSUFFICIENT_PERMISSIONS:
                message = "Insufficient permissions";
                break;
            case SpeechRecognizer.ERROR_NETWORK:
                message = "Network error";
                break;
            case SpeechRecognizer.ERROR_NETWORK_TIMEOUT:
                message = "Network timeout";
                break;
            case SpeechRecognizer.ERROR_NO_MATCH:
                message = "No match";
                break;
            case SpeechRecognizer.ERROR_RECOGNIZER_BUSY:
                message = "RecognitionService busy";
                break;
            case SpeechRecognizer.ERROR_SERVER:
                message = "error from server";
                break;
            case SpeechRecognizer.ERROR_SPEECH_TIMEOUT:
                message = "No speech input";
                break;
            default:
                message = "Didn't understand, please try again.";
                break;
        }
        return message;
    }


    public SpeechRecognitionListener setCommunicator(Frag_Request.Communicator communicator) {
        this.communicator = communicator;
        return this;
    }
}
