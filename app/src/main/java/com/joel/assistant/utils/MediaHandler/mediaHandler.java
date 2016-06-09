package com.joel.assistant.utils.MediaHandler;

import android.app.SearchManager;
import android.content.Intent;
import android.provider.MediaStore;
import android.util.Log;

import com.joel.assistant.utils.ActionHandlerFactory.ActionHandler;
import com.joel.assistant.utils.ActionHandlerFactory.ActionHandlerFactory;
import com.joel.assistant.utils.ResponseHandler_AI;
import com.joel.assistant.utils.StateProvider;

import android.widget.Toast;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by Joel on 18-03-2016.
 */
public class mediaHandler implements ActionHandler {
    public static void playSong(String song) {
        ResponseHandler_AI.TextResponse("Playing " + song);

        Intent intent = new Intent(MediaStore.INTENT_ACTION_MEDIA_PLAY_FROM_SEARCH);
        intent.putExtra(MediaStore.Audio.Playlists.ENTRY_CONTENT_TYPE,
                "android.intent.extra.playlist");
        intent.putExtra(MediaStore.EXTRA_MEDIA_FOCUS, "vnd.android.cursor.item/playlist");
        intent.putExtra(SearchManager.QUERY, song);


        if (intent.resolveActivity(StateProvider.getActivity().getPackageManager()) != null) {
            StateProvider.getActivity().startActivity(intent);
        } else {
            Toast.makeText(StateProvider.getContext(), "Unable to perform the requested action", Toast.LENGTH_LONG).show();
        }

    }

    @Override
    public void performAction(JSONObject res) {
        try {
            String song = res.getJSONObject("parameters").getString("song_name");
            //----------------------------------------------------------------------------

            String song2;
            if(song.isEmpty() == true)
                song2 = mediaTest.getRandSong();
            else
                song2 = mediaTest.listMedia2(song);

            if (song2.isEmpty() == true) {
                ResponseHandler_AI.TextResponse(song + " was not found");
                Intent intent = new Intent(Intent.ACTION_SEARCH);
                intent.setPackage("com.google.android.youtube");
                intent.putExtra("query", song);
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                StateProvider.getActivity().startActivity(intent);
                return;
            }
            song = song2;
            //----------------------------------------------------------------------------
            playSong(song);
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }
}
