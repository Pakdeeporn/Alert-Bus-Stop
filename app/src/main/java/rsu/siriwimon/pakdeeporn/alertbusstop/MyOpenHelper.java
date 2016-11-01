package rsu.siriwimon.pakdeeporn.alertbusstop;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by USER on 1/11/2559.
 */

public class MyOpenHelper extends SQLiteOpenHelper {

    //Explicit
    private Context context; //Context context การเชื่อมต่อกัน
    public static final String database_name = "BusStop.db";
    private static final int database_version = 1;
    private static final String create_bus_table = "create table busTABLE (" +
            "_id integer primary key," +
            "NameBusStop text," +
            "PathBusStop text," +
            "Lat text," +
            "Lng text);"; //การตั้งชื่อ table มี 5 คอลัมน์ จะให้ค่า Lat,Lng เป็นสตริงก่อนแล้วค่อยโยนเป็นfloat




    public MyOpenHelper(Context context) {
        super(context,database_name,null,database_version); //การใส่ให้กับ constructor
        this.context = context;
    } // Constructor

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {

        sqLiteDatabase.execSQL(create_bus_table); //สร้างฐานข้อมูลตามคอลัมน์ที่กำหนดไว้


    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }
} // Main Class
