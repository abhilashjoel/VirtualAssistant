package com.joel.assistant.utils;

import android.animation.Animator;
import android.graphics.Color;
import android.util.Log;
import android.view.View;
import android.view.ViewAnimationUtils;
import android.view.animation.AccelerateDecelerateInterpolator;
import android.widget.EditText;

import com.joel.assistant.R;
import com.joel.assistant.Views.Activities.Response_Fragments;

import butterknife.Bind;
import io.codetail.animation.RevealAnimator;
import io.codetail.animation.SupportAnimator;

/**
 * Created by Joel on 08-03-2016.
 */
public class ResponseHandler_AI {

    @Bind(R.id.TextResponseBaseView)
    View base;


    public static void TextResponse(String data, String tts) {
        Log.i("Text Response", data);
        Log.i("TTS Response", tts);
        Response_Fragments.SetTextResponse(data);

/*        View base = StateProvider.getActivity().findViewById(R.id.TextResponseBaseView);

        int cx = (base.getLeft()+base.getRight())/2;
        int cy = (base.getTop()+base.getBottom())/2;

        int dx = Math.max(cx, base.getWidth()-cx);
        int dy = Math.max(cy, base.getHeight()-cy);

        float radius = (float) Math.hypot(dx,dy);

        int colorCode = Color.WHITE;
        switch (RandomGenerator.get(5)){
            case 0: colorCode = Color.BLUE;
                break;
            case 1: colorCode = Color.GREEN;
                break;
            case 2: colorCode = Color.RED;
                break;
            case 3: colorCode = Color.YELLOW;
                break;
            case 4: colorCode = Color.BLACK;
        }

//        base.setBackgroundColor(colorCode);

        Animator animator =  ViewAnimationUtils.createCircularReveal(base, cx, cy, 0, radius);

        animator.setInterpolator(new AccelerateDecelerateInterpolator());
        animator.setDuration(1000);
        animator.addListener(new Animator.AnimatorListener() {
            @Override
            public void onAnimationStart(Animator animation) {
            }

            @Override
            public void onAnimationEnd(Animator animation) {

            }

            @Override
            public void onAnimationCancel(Animator animation) {

            }

            @Override
            public void onAnimationRepeat(Animator animation) {
                animation.end();
//                animation.removeListener(this);

            }
        });
//        animator.start();
*/
        TTS.speak(tts);
    }

    public static void TextResponse(String s) {
        TextResponse(s, s);
    }
}
