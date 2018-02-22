package com.example.weibinwang.myapplication.Model.DatabaseHandler;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by weibinwang on 2018/2/22.
 */

public class DBHelper extends SQLiteOpenHelper{

    //name of database
    public static final String DBNAME = "choixdej.db";

    //version
    public static final int VERSION = 1;

    /*
    * Preferences for setting table User
    * */
    public static final String TABLE_USER_NAME = "users";
    public static final String COLNUM_FIRSTNAME = "uFirstName";
    public static final String COLNUM_SECONDNAME = "uSecondName";
    public static final String COLNUM_USERNAME = "uUserName";
    public static final String COLNUM_PASSWORD = "uPassword";
    public static final String COLNUM_EMAIL = "uEmail";

    /*
    * Preferences for setting table Restaurant
    * */
    public static final String TABLE_RESTAURANT_NAME = "restaurants";
    public static final String COLNUM_ADDRESS = "rAddress";
    public static final String COLNUM_DESCRIPTION = "rDescription";
    public static final String COLNUM_CONTACT = "rContact";
    public static final String COLNUM_TYPE = "rType";// limit with Chinese, Japanese, Italien, etc

    /*
    * Preferences for setting table Group
    * */



    public DBHelper(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }




    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {

    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }
}
