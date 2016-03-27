package com.joel.assistant.utils.MediaHandler;

import android.app.SearchManager;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.provider.MediaStore;
import android.util.Log;

import com.joel.assistant.utils.StateProvider;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Joel on 17-03-2016.
 */
public class mediaTest {

    public static void listMedia(Context c) {

        Intent intent = new Intent(MediaStore.INTENT_ACTION_MEDIA_PLAY_FROM_SEARCH);
        intent.putExtra(MediaStore.Audio.Playlists.ENTRY_CONTENT_TYPE,
                "android.intent.extra.playlist");
        intent.putExtra(MediaStore.EXTRA_MEDIA_FOCUS, "vnd.android.cursor.item/playlist");
        intent.putExtra(SearchManager.QUERY, "paareer");


        if (intent.resolveActivity(StateProvider.getActivity().getPackageManager()) != null) {
            StateProvider.getActivity().startActivity(intent);
        } else {
            Log.e("Media", "MEdia not found");
        }
    }

    public static String listMedia2(String name) {
        Context ct = StateProvider.getContext();

        String selection = MediaStore.Audio.Media.IS_MUSIC + " != 0";

        String[] projection = {
                MediaStore.Audio.Media._ID,
                MediaStore.Audio.Media.ARTIST,
                MediaStore.Audio.Media.TITLE,
                MediaStore.Audio.Media.DATA,
                MediaStore.Audio.Media.DISPLAY_NAME,
                MediaStore.Audio.Media.DURATION
        };

        Cursor cursor = StateProvider.getActivity().managedQuery(
                MediaStore.Audio.Media.EXTERNAL_CONTENT_URI,
                projection,
                selection,
                null,
                null);

        String t;
        List<String> songs = new ArrayList<String>();
        while (cursor.moveToNext()) {
            t = (cursor.getString(2) + "|\t|"
                    + cursor.getString(4) + "||");
/*                    + cursor.getString(2) + "||"
                    + cursor.getString(3) + "||"
                    + cursor.getString(4) + "||"
                    + cursor.getString(5));
  */
            Log.wtf("Media ", t);

            String n = cursor.getString(2).toLowerCase();
            if (n.contains(name.toLowerCase()) == true) {
                System.out.println("Found a match 1");
                return n;
            }
            n = cursor.getString(4);
            if (n.contains(name.toLowerCase()) == true) {
                System.out.println("Found a match 2");
                return cursor.getString(2);
            }
        }
        return "";
    }
}
