package pl.boleck.delask.helper;

import android.app.ProgressDialog;
import android.content.Context;

/**
 * Created by Boleck on 2016-04-22.
 */
public class Utils {
    public static void showLoading(Context cx, String title,String text,boolean cancelable){
        ProgressDialog.show(cx,title,text,true,cancelable);
    }
}
