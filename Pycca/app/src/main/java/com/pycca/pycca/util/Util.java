package com.pycca.pycca.util;

import android.support.design.widget.Snackbar;
import android.text.TextUtils;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.Transformation;

import com.pycca.pycca.pojo.Division;
import com.pycca.pycca.pojo.ImageResource;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

public class Util {

    public static void showMessage(View view, String text){
        Snackbar.make(view, text, Snackbar.LENGTH_LONG).show();
    }

    public static int getOrder(String filename){
        try {
            String orderString = filename.substring(0,filename.lastIndexOf('.'));
            return Integer.valueOf(orderString);
        }
        catch (Exception exception){
            return 0;
        }
    }

    public static ArrayList<Division> orderDivisionList(ArrayList<Division> list){
        Collections.sort(list, new Comparator<Division>() {
            @Override public int compare(Division d1, Division d2) {
                return d1.getOrder() - d2.getOrder(); // Ascending
            }

        });
        return list;
    }

    public static boolean checkValidEmail(String email){
        if (TextUtils.isEmpty(email)) {
            return false;
        }
        else {
            return android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches();
        }
    }

    public static void expand(final View v) {
        v.measure(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        final int targtetHeight = v.getMeasuredHeight();
        if (v.isShown()) {
            collapse(v);
        } else {
            v.getLayoutParams().height = 0;
            v.setVisibility(View.VISIBLE);
            Animation a = new Animation() {
                @Override
                protected void applyTransformation(float interpolatedTime,
                                                   Transformation t) {
                    v.getLayoutParams().height = interpolatedTime == 1 ? ViewGroup.LayoutParams.WRAP_CONTENT
                            : (int) (targtetHeight * interpolatedTime);
                    v.requestLayout();
                }

                @Override
                public boolean willChangeBounds() {
                    return true;
                }
            };
            a.setDuration((int) (targtetHeight + 500));
            v.startAnimation(a);
        }

    }

    public static void collapse(final View v) {
        final int initialHeight = v.getMeasuredHeight();
        Animation a = new Animation() {
            @Override
            protected void applyTransformation(float interpolatedTime,
                                               Transformation t) {
                if (interpolatedTime == 1) {
                    v.setVisibility(View.GONE);
                } else {
                    v.getLayoutParams().height = initialHeight
                            - (int) (initialHeight * interpolatedTime);
                    v.requestLayout();
                }
            }

            @Override
            public boolean willChangeBounds() {
                return true;
            }
        };

        a.setDuration((int) (v.getLayoutParams().height + 500));
        v.startAnimation(a);
    }

    public static boolean containImageResourceInList(ArrayList<ImageResource> list, ImageResource imageResource){
        for (ImageResource imgRsc: list) {
            if(imageResource.getDescription().equals(imgRsc.getDescription()) &&
                    imageResource.getName().equals(imgRsc.getName()) &&
                    imageResource.getPath().equals(imgRsc.getPath()) &&
                    imageResource.getUrl().equals(imgRsc.getUrl())){
                return true;
            }
        }
        return false;
    }

    public enum RegistrationProvider {
        EMAIL,
        FACEBOOK,
        GOOGLE,
        INSTAGRAM
    }

}
