package com.pycca.pycca.util;

import android.content.Context;
import android.os.Environment;
import android.support.annotation.NonNull;
import android.support.design.widget.Snackbar;
import android.support.v4.content.ContextCompat;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.CycleInterpolator;
import android.view.animation.Transformation;
import android.view.animation.TranslateAnimation;

import com.pycca.pycca.pojo.CouponImageResource;
import com.pycca.pycca.pojo.DivisionImageResource;
import com.pycca.pycca.pojo.ImageResource;
import com.pycca.pycca.restApi.RestApiConstants;
import com.pycca.pycca.restApi.model.QuotaCalculatorResponse;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.regex.Pattern;

import okhttp3.ResponseBody;

public class Util {

    public static void showMessage(View view, String text){
        Snackbar.make(view, text, Snackbar.LENGTH_LONG).show();
    }

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
        FACEBOOK,
        INSTAGRAM,
        GOOGLE,
        EMAIL
    }

    public static TranslateAnimation getTranslateAnimation() {
        TranslateAnimation translateAnimation = new TranslateAnimation(0, 10, 0, 0);
        translateAnimation.setDuration(Constants.ANIMATION_DURATION);
        translateAnimation.setInterpolator(new CycleInterpolator(7));
        return translateAnimation;
    }

    @NonNull
    public static String maskClubPyccaCardNumber(String cardNumber, boolean clipCardNumber){
        String subStr1 = cardNumber.substring(0,6);
        String subStr2 = cardNumber.substring(6,14);
        String subStr3 = cardNumber.substring(14);
        String maskedCardNumber = subStr1.concat("********").concat(subStr3);
        if (clipCardNumber){
            maskedCardNumber = clipCardNumber(maskedCardNumber);
        }
        return maskedCardNumber;
    }

    public static String clipCardNumber(String cardNumber){
        String subStr1 = cardNumber.substring(0, 4);
        String subStr2 = cardNumber.substring(4, 8);
        String subStr3 = cardNumber.substring(8, 12);
        String subStr4 = cardNumber.substring(12);
        return subStr1.concat(" ").concat(subStr2).concat(" ").concat(subStr3).concat(" ").concat(subStr4);
    }

    public static String twoDigits(int n) {
        return (n<=9) ? ("0"+n) : String.valueOf(n);
    }

    public static String formatStringPayUntil(String payUntil, String quota){
        return "$".concat(quota).concat(" hasta ").concat(payUntil);
    }

    public static ArrayList<String> getStringListQuotas(ArrayList<QuotaCalculatorResponse> list){
        ArrayList<String> stringArrayList = new ArrayList<>();
        for (QuotaCalculatorResponse quota: list) {
            stringArrayList.add(String.valueOf(quota.getNPlazo()));
        }
        return stringArrayList;
    }

    public static boolean writeResponseBodyToDisk(ResponseBody body, String fileName) {
        try {
            File file = new File( Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS) + File.separator + fileName + ".pdf");
            InputStream inputStream = null;
            OutputStream outputStream = null;
            try {
                byte[] fileReader = new byte[4096];
                long fileSize = body.contentLength();
                long fileSizeDownloaded = 0;
                inputStream = body.byteStream();
                outputStream = new FileOutputStream(file);
                while (true) {
                    int read = inputStream.read(fileReader);
                    if (read == -1) {
                        break;
                    }
                    outputStream.write(fileReader, 0, read);
                    fileSizeDownloaded += read;
                }
                outputStream.flush();
                return true;
            }
            catch (IOException e) {
                return false;
            }
            finally {
                if (inputStream != null) {
                    inputStream.close();
                }
                if (outputStream != null) {
                    outputStream.close();
                }
            }
        }
        catch (IOException e) {
            return false;
        }
    }

    public static boolean validateDate(String dateToValidate, String dateFromat){

        if(dateToValidate == null){
            return false;
        }

        SimpleDateFormat sdf = new SimpleDateFormat(dateFromat);
        sdf.setLenient(false);

        try {

            //if not valid, it will throw ParseException
            Date date = sdf.parse(dateToValidate);
            System.out.println(date);

        } catch (ParseException e) {

            e.printStackTrace();
            return false;
        }

        return true;
    }

}
