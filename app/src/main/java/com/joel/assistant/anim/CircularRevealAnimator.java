package com.joel.assistant.anim;

import android.animation.Animator;
import android.view.View;
import android.view.ViewAnimationUtils;
import android.view.animation.AccelerateDecelerateInterpolator;

import io.codetail.animation.RevealAnimator;
import io.codetail.animation.SupportAnimator;

/**
 * Created by Joel on 15-04-2016.
 */
public class CircularRevealAnimator {

    Animator animator;
    View base;

    public CircularRevealAnimator(View base) {
        this.base = base;


        int cx = (base.getLeft() + base.getRight()) / 2;
        int cy = (base.getTop() + base.getBottom()) / 2;

        int dx = Math.max(cx, base.getWidth() - cx);
        int dy = Math.max(cy, base.getHeight() - cy);

        float radius = (float) Math.hypot(dx, dy);

        animator = (Animator) ViewAnimationUtils.createCircularReveal(base, cx, cy, 0, radius);
        animator.setInterpolator(new AccelerateDecelerateInterpolator());
    }

    public Animator setDuration(int duration) {
        animator.setDuration(duration);
        return animator;
    }

    public Animator start() {
        animator.start();
        return animator;
    }


}
