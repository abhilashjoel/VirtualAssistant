package com.joel.assistant.utils.ContactsHandler;

import android.Manifest;
import android.content.ContentResolver;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.net.Uri;
import android.provider.ContactsContract;
import android.util.Log;

import com.joel.assistant.utils.ActionHandlerFactory.ActionHandler;
import com.joel.assistant.utils.ResponseHandler_AI;
import com.joel.assistant.utils.StateProvider;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by Joel on 16-03-2016.
 */
public class contactsHandler implements ActionHandler {
    static String TAG = "Contacts Handler";
    private static contactsHandler c = new contactsHandler();
    ContentResolver contacts;
    Context context;
    Cursor cursor;
    Uri Query_Uri = ContactsContract.Contacts.CONTENT_URI;
    String projection[] = {ContactsContract.Contacts._ID,
            ContactsContract.Contacts.DISPLAY_NAME,
            ContactsContract.Contacts.DISPLAY_NAME_PRIMARY,
            ContactsContract.Contacts.HAS_PHONE_NUMBER};
    //                            ContactsContract.CommonDataKinds.Phone.NUMBER};
    Uri Phone_Content_uUri = ContactsContract.CommonDataKinds.Phone.CONTENT_URI;

    public contactsHandler() {
        context = StateProvider.getContext();
        contacts = context.getContentResolver();
    }

    public static String getContact(String name) {

        Cursor cur = c.contacts.query(c.Query_Uri, c.projection, ContactsContract.Contacts.DISPLAY_NAME + " = \"" + name + "\"", null, null);

        Log.i(TAG, "Count : " + cur.getCount());
        cur.moveToFirst();

        Cursor phoneCursor = c.contacts.query(c.Phone_Content_uUri, null, ContactsContract.CommonDataKinds.Phone.CONTACT_ID + " = ?", new String[]{cur.getString(0)}, null);
        phoneCursor.moveToFirst();
        String phoneNumber = phoneCursor.getString(phoneCursor.getColumnIndex(ContactsContract.CommonDataKinds.Phone.NUMBER));
        Log.i(TAG, "Found Contact : " + phoneNumber);
        return phoneNumber;
    }

    @Override
    public void performAction(JSONObject j) {
        try {
            String name = j.getJSONObject("parameters").getString("name_first");
            String number = getContact(name);

            ResponseHandler_AI.TextResponse("Calling " + name);

            Intent intent = new Intent(Intent.ACTION_CALL, Uri.fromParts("tel", number, null));
            try {
                StateProvider.getActivity().startActivity(intent);
            } catch (Exception e) {
                e.printStackTrace();
            }

        } catch (JSONException e) {
            e.printStackTrace();
        }
    }
}
