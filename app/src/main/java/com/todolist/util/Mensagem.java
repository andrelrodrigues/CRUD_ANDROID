package com.todolist.util;

import android.app.Activity;
import android.widget.Toast;

/**
 * Created by renancunha on 31/08/15.
 */
public class Mensagem {
    public static void Msg(Activity activity, String msg){
        Toast.makeText(activity,msg,Toast.LENGTH_LONG).show();
    }
}
