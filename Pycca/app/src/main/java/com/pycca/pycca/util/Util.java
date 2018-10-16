package com.pycca.pycca.util;

import android.support.design.widget.Snackbar;
import android.text.TextUtils;
import android.view.View;

import com.pycca.pycca.pojo.Division;

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
        catch (Exception e){
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
        } else {
            return android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches();
        }
    }

}
