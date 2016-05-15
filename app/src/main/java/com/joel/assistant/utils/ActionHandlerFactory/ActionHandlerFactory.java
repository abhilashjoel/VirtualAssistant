package com.joel.assistant.utils.ActionHandlerFactory;

import com.joel.assistant.utils.ApplicationHandler.launchApp;
import com.joel.assistant.utils.ContactsHandler.contactsHandler;
import com.joel.assistant.utils.DuckDuckGo.DuckDuckGo;
import com.joel.assistant.utils.GooglePlacesAPI.NavigationIntentHandler;
import com.joel.assistant.utils.GooglePlacesAPI.SearchIntentHandler;
import com.joel.assistant.utils.JokesHandler.chuck;
import com.joel.assistant.utils.JokesHandler.jokes;
import com.joel.assistant.utils.JokesHandler.knock;
import com.joel.assistant.utils.JokesHandler.yomama;
import com.joel.assistant.utils.LocationHandler.LocationHandler;
import com.joel.assistant.utils.MediaHandler.mediaHandler;
import com.joel.assistant.utils.NewsHandler.newsHandler;
import com.joel.assistant.utils.WolframAPI.currentWeather;
import com.joel.assistant.utils.WolframAPI.wolframHandler;
import com.joel.assistant.utils.events.alarmHandler;
import com.joel.assistant.utils.events.reminderHandler;

import org.json.JSONException;
import org.json.JSONObject;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;

/**
 * Created by Joel on 13-03-2016.
 */
public class ActionHandlerFactory {


    private static ActionHandlerFactory factory = new ActionHandlerFactory();
    handlerMap actionHandlerSet;

    private ActionHandlerFactory() {
        actionHandlerSet = new handlerMap();
        initHandlers();
    }

    public static ActionHandler getHandler(JSONObject j) {

        if (j.has("action") == false)
            return factory.getHandlerInstance("default");

        String action = "default";
        try {
            action = j.getString("action");
        } catch (JSONException e) {
            e.printStackTrace();
        }

        if (factory.actionHandlerSet.containsKey(action))
            return factory.getHandlerInstance(action);

        return factory.getHandlerInstance(action.split("\\.")[0]);
    }

    public static ActionHandler getActionHandler1(JSONObject j) {

        if (j.has("action") != true) {
            return new DefaultHandler();
        }

        String action = null;
        try {
            action = j.getString("action");
        } catch (JSONException e) {
            e.printStackTrace();
        }
        String parts[] = action.split("\\.");
        System.out.println("Action :  " + action);
        System.out.println("------parts : " + parts.toString());

        if (action.equalsIgnoreCase("apps.open") == true) {
            return new launchApp();
        } else if (parts[0].equalsIgnoreCase("smalltalk") == true) {
            return new SmallTalkHandler();
        }
//        else if(action.equalsIgnoreCase())

        return new DefaultHandler();

    }

    private void initHandlers() {
        registerHandler("apps.open", new launchApp());
        registerHandler("smalltalk", new SmallTalkHandler());
        registerHandler("call", new contactsHandler());
        registerHandler("song.play", new mediaHandler());
        registerHandler("default", new DefaultHandler());
        registerHandler("joke.yomama", new yomama());
        registerHandler("joke.chuck", new chuck());
        registerHandler("knock", new knock());
        registerHandler("joke", new jokes());
        registerHandler("news", new newsHandler());
        registerHandler("knowledge.ddg", new DuckDuckGo());
        registerHandler("wolfram", new wolframHandler());
        registerHandler("knowledge", new wolframHandler());
        registerHandler("maps.userlocation", new LocationHandler());
        registerHandler("maps.search", new SearchIntentHandler());
        registerHandler("maps.navigation", new NavigationIntentHandler());
        registerHandler("weather.current", currentWeather.getInstance());
        registerHandler("alarm", alarmHandler.getInstance());
        registerHandler("reminder", reminderHandler.getInstance());
    }

    private void registerHandler(String action, ActionHandler handler) {
        actionHandlerSet.put(action, handler);
    }

/*
    private ActionHandler getHandlerInstance2(String action) {
        Class h;
        if (actionHandlerSet.containsKey(action))
            h = actionHandlerSet.get(action);
        else {
            String parts[] = action.split("\\.");
            h = actionHandlerSet.get(parts[0], actionHandlerSet.get("default"));
        }
        Constructor handlerConstructor = null;
        try {
            h.getConstructor();
            handlerConstructor = h.getDeclaredConstructor(new Class[]{String.class});
            return (ActionHandler) handlerConstructor.newInstance();

        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }

        return new DefaultHandler();

    }
*/

    private ActionHandler getHandlerInstance(String action) {
        return actionHandlerSet.get(action, actionHandlerSet.get("default"));
    }

    public static ActionHandler getDefaultHandler() {
        return factory.getHandlerInstance("default");
    }

    public static ActionHandler getWolframHandler() {
        return factory.getHandlerInstance("wolfram");
    }
}
