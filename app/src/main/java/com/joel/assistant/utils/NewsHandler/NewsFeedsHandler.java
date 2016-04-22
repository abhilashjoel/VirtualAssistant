package com.joel.assistant.utils.NewsHandler;

import android.util.Log;

import com.joel.assistant.Network.AsyncHTTP;
import com.joel.assistant.Network.JsonResponseHandler;
import com.joel.assistant.Network.param;
import com.joel.assistant.utils.Constants;
import com.joel.assistant.utils.RandomGenerator;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

/**
 * Created by Joel on 22-04-2016.
 */
public class NewsFeedsHandler {

    public static NewsFeedsHandler feedhandler = new NewsFeedsHandler();

    ArrayList<Feeds> feeds;

    static String Tag = "NewsFeedsHandler";

    String[] newsProviders = {"wired.com",
            "sciencealert.com",
            "cnet.com",
            "techcrunch.com",
            "discovery.com",
            "telegraph.co.uk",
            "topgear.com",
            "bbc.co.uk",
            "firstpost.com",
            "indianexpress.com",
            "financialexpress.com",
            "livemint.com",
            "reuters.com"
    };

    NewsFeedsListener listener;
    int CurrentFeedId;

    private NewsFeedsHandler() {
        feeds = new ArrayList<Feeds>();
        initializeFeeds();

    }

    public static NewsFeedsHandler getInstance() {
        return feedhandler;
    }

    public static void start() {
        feedhandler.initializeFeeds();
    }

    void initializeFeeds() {
        String url = newsProviders[RandomGenerator.get(newsProviders.length - 1)];
        System.out.println("Choosen URL :  " + url);

        AsyncHTTP.get(buildParam(url), new JsonResponseHandler() {
            @Override
            public void onSuccess(int statusCode, JSONObject jRes) {
                super.onSuccess(statusCode, jRes);
                try {
//                    System.out.println(jRes.toString(2));
                    JSONArray feeds = jRes.getJSONArray("posts");
                    addFeeds(feeds);
                    showFeed(0);
                } catch (JSONException e) {
                    e.printStackTrace();
                }


            }

        });
    }

    private void showFeed(int i) {
        Log.i(Tag, "CurrentFeedID :  " + i);
        if (listener == null)
            return;
        if (i >= 0 && i < feeds.size()) {
            Log.i(Tag, "Invoking Listeners ShowFeed() with : " + feeds.get(i).Title);
            listener.showFeed(feeds.get(i));
            CurrentFeedId = i;
        }

        if (i > feeds.size() - 3)
            getMoreFeeds();

    }


    public static void nextFeed() {
        feedhandler.showFeed(feedhandler.CurrentFeedId + 1);
    }

    public static void previousFeed() {
        feedhandler.showFeed(feedhandler.CurrentFeedId - 1);
    }

    public void getMoreFeeds() {
        String url = newsProviders[RandomGenerator.get(newsProviders.length - 1)];
        System.out.println("Choosen URL :  " + url);

        AsyncHTTP.get(buildParam(url), new JsonResponseHandler() {
            @Override
            public void onSuccess(int statusCode, JSONObject jRes) {
                super.onSuccess(statusCode, jRes);
                try {
//                    System.out.println(jRes.toString(2));
                    JSONArray feeds = jRes.getJSONArray("posts");
                    addFeeds(feeds);
                } catch (JSONException e) {
                    e.printStackTrace();
                }


            }

        });
    }

    void addFeeds(JSONArray f) {

        int len = f.length();
        int i;

        JSONObject post;
        System.out.println("Size of feed array :  " + len);

        lp:
        for (i = 0; i < len; i++) {
            try {
                if (RandomGenerator.get(3) % 3 != 1) {
//                    System.out.println("Skipped....");
//                    System.out.println("Skipped....");
//                    continue lp;
                }
                Feeds fe = new Feeds();

                post = f.getJSONObject(i);
                fe.Author = post.getString("author");
                fe.Title = post.getString("title");
                fe.Text = post.getString("text");
                fe.Published = post.getString("published");
                fe.ImageURL = post.getJSONObject("thread").getString("main_image");
                fe.URL = post.getString("url");

                Log.i("Adding Feed", fe.Title);

                feeds.add(fe);

            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
    }


    private param buildParam(String URL) {
        System.out.println("URL :   " + URL);
        param p = new param();
        p.setHost(Constants.Webhose_HOST);
        p.setProtocol("https");
        p.setURLSegment("search");
        p.addParam("token", Constants.Webhose_Token);
        p.addParam("format", "json");
        p.addParam("size", "10");
        String q = "language:(english) (site_type:news OR site_type:blogs)";
        q += " site:" + URL;
//        q += " performance_score:>"+ RandomGenerator.get(9);
        p.addParam("q", q);
        return p;

    }


    public static void setListener(NewsFeedsListener l) {
        feedhandler.feeds = new ArrayList<Feeds>();
        feedhandler.listener = l;
    }

    public static void removeListener() {
        feedhandler.feeds = null;
        feedhandler.listener = null;
    }

    public interface NewsFeedsListener {
        public void showFeed(Feeds f);
    }
}
