package rsu.siriwimon.pakdeeporn.alertbusstop;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

/**
 * Created by USER on 1/11/2559.
 */

public class Mymanage {

    //Explicit
    private Context context;
    private MyOpenHelper myOpenHelper;
    private SQLiteDatabase sqLiteDatabase;


    public Mymanage(Context context) {
        this.context = context;
        myOpenHelper = new MyOpenHelper(context);
        sqLiteDatabase = myOpenHelper.getWritableDatabase();

    }
} // Main Class
