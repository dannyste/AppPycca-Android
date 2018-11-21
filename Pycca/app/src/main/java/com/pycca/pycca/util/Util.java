package com.pycca.pycca.util;

import android.support.annotation.NonNull;
import android.support.design.widget.Snackbar;
import android.text.TextUtils;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.CycleInterpolator;
import android.view.animation.Transformation;
import android.view.animation.TranslateAnimation;

import com.google.firebase.messaging.FirebaseMessaging;
import com.pycca.pycca.pojo.CouponImageResource;
import com.pycca.pycca.pojo.DivisionImageResource;
import com.pycca.pycca.pojo.ImageResource;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.regex.Pattern;

public class Util {

    public static void showMessage(View view, String text){
        Snackbar.make(view, text, Snackbar.LENGTH_LONG).show();
    }

//    public static boolean checkValidEmail(String email){
//        if (TextUtils.isEmpty(email)) {
//            return false;
//        }
//        else {
//            return android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches();
//        }
//    }

    public static boolean checkValidEmail(String email)
    {
        String emailRegex = "^[a-zA-Z0-9_+&*-]+(?:\\."+
                "[a-zA-Z0-9_+&*-]+)*@" +
                "(?:[a-zA-Z0-9-]+\\.)+[a-z" +
                "A-Z]{2,7}$";

        Pattern pat = Pattern.compile(emailRegex);
        if (email == null)
            return false;
        return pat.matcher(email).matches();
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

    public static boolean containCouponResourceInList(ArrayList<CouponImageResource> list, CouponImageResource imageResource){
        for (CouponImageResource imgRsc: list) {
            if(imageResource.getDescription().equals(imgRsc.getDescription()) &&
                    imageResource.getName().equals(imgRsc.getName()) &&
                    imageResource.getPath().equals(imgRsc.getPath()) &&
                    imageResource.getUrl().equals(imgRsc.getUrl()) &&
                    imageResource.getCode().equals(imgRsc.getCode())){
                return true;
            }
        }
        return false;
    }

    public static boolean containDivisionResourceInList(ArrayList<DivisionImageResource> list, DivisionImageResource imageResource){
        for (DivisionImageResource imgRsc: list) {
            if(imageResource.getDescription().equals(imgRsc.getDescription()) &&
                    imageResource.getName().equals(imgRsc.getName()) &&
                    imageResource.getPath().equals(imgRsc.getPath()) &&
                    imageResource.getUrl().equals(imgRsc.getUrl()) &&
                    imageResource.getOrder().equals(imgRsc.getOrder())){
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

    public static TranslateAnimation getTranslateAnimation() {
        TranslateAnimation translateAnimation = new TranslateAnimation(0, 10, 0, 0);
        translateAnimation.setDuration(Constants.ANIMATION_DURATION);
        translateAnimation.setInterpolator(new CycleInterpolator(7));
        return translateAnimation;
    }

    @NonNull
    public static String maskClubPyccaCardNumber(String cardNumber){
        String subStr1 = cardNumber.substring(0,6);
        String subStr2 = cardNumber.substring(6,14);
        String subStr3 = cardNumber.substring(14);
        return subStr1.concat("********").concat(subStr3);
    }

    public static String twoDigits(int n) {
        return (n<=9) ? ("0"+n) : String.valueOf(n);
    }

    public static void subscribeToTopicFCM(String topic){
        FirebaseMessaging.getInstance().subscribeToTopic(topic);
    }

    public static void unSubscribeToTopicFCM(String topic){
        FirebaseMessaging.getInstance().unsubscribeFromTopic(topic);
    }
}
