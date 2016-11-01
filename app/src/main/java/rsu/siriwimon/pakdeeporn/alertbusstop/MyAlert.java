package rsu.siriwimon.pakdeeporn.alertbusstop;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;

/**
 * Created by USER on 1/11/2559.
 */

public class MyAlert {

    private Context context;
    private int anInt;
    private String titleString,messageSting; // แล้วกด alt+Insert

    public MyAlert(Context context,
                   int anInt,
                   String titleString,
                   String messageSting) {
        this.context = context;
        this.anInt = anInt;
        this.titleString = titleString;
        this.messageSting = messageSting;

    }

    public void myDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(context);  //ทำการสร้าง pop up
        builder.setCancelable(false); //ก็คือไม่สามารถกดปุ่มendo ไม่ทำงานต้องกด ok เท่านั้น
        builder.setIcon(anInt);
        builder.setTitle(titleString);
        builder.setMessage(messageSting);
        builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                dialogInterface.dismiss(); //เมื่อทำการกดปุ่มok ให้ pop up หายไป

            }
        });
        builder.show();


    }

} // Main Class
