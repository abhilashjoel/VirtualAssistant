package com.joel.assistant.utils.WolframAPI;

/**
 * Created by Joel on 28-04-2016.
 */
public class ResultPod {
    String imageURL;
    String Text;
    String Title;

    public void setTitle(String title) {
        Title = title;
    }

    public void setImageURL(String url) {
        imageURL = url;
    }

    public void setText(String text) {
        Text = text;
    }

    public String getTitle() {
        return Title;
    }

    public String getImageURL() {
        return imageURL;
    }

    public String getText() {
        return Text;
    }

    @Override
    public String toString() {
//        return super.toString();

        String s = "";
        s += "Title :  " + Title + "\n";
        s += "Test :  " + Text + "\n";
        s += "imageURL :  " + imageURL;

        return s;
    }
}
