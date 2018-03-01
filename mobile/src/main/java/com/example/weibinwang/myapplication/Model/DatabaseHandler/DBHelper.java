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
    public static final String USER_ID = "_idU";
    public static final String COLNUM_UFIRSTNAME = "uFirstName";
    public static final String COLNUM_USECONDNAME = "uSecondName";
    public static final String COLNUM_UUSERNAME = "uUserName";
    public static final String COLNUM_UPASSWORD = "uPassword";
    public static final String COLNUM_UEMAIL = "uEmail";

    private static final String CREATE_TABLE_USER =
            String.format("CREATE TABLE %s("+USER_ID+" integer primary key autoincrement, " +
                    "%s varchar(20)," +
                    "%s varchar(20)," +
                    "%s varchar(20) not null," +
                    "%s varchar(20) not null," +
                    "%s varchar(50) not null);",
                    TABLE_USER_NAME,
                    COLNUM_UFIRSTNAME, COLNUM_USECONDNAME, COLNUM_UUSERNAME,COLNUM_UPASSWORD,COLNUM_UEMAIL);

    /*
    * Preferences for setting table Restaurant
    * */
    public static final String TABLE_RESTAURANT_NAME = "restaurants";
    public static final String RESTAURANT_ID = "_idR";
    public static final String COLNUM_RNAME = "rName";
    public static final String COLNUM_RADDRESS = "rAddress";
    public static final String COLNUM_RDESCRIPTION = "rDescription";
    public static final String COLNUM_RCONTACT = "rContact";
    public static final String COLNUM_RTYPE = "rType";// limit with Chinese, Japanese, Italien, etc

    private static final String CREATE_TABLE_RES =
            String.format("create table %s("+RESTAURANT_ID+" integer primary key autoincrement," +
                    "%s text not null," +
                    "%s text not null," +
                    "%s text," +
                    "%s integer not null," +
                    "%s varchar(20) not null);",
                    TABLE_RESTAURANT_NAME,
                    COLNUM_RNAME,COLNUM_RADDRESS,COLNUM_RDESCRIPTION,COLNUM_RCONTACT,COLNUM_RTYPE);
    /*
    * Preferences for setting table Group
    * */
    public static final String TABLE_GROUP_NAME = "groups";
    public static final String GROUP_ID = "_idG";
    public static final String COLNUM_GNAME = "gname";
    public static final String COLNUM_GTYPE = "gType";
    public static final String COLNUM_GADMIN = "gAdmin";

    private static final String CREATE_TABLE_GROUP =
            "create table " +TABLE_GROUP_NAME+ "("+GROUP_ID+" integer primary key autoincrement," +
                    COLNUM_GNAME + " varchar(20) not null," +
                    COLNUM_GTYPE+ " varchar(20) not null," +
                    COLNUM_GADMIN+ " integer not null," +
                    "foreign key ("+COLNUM_GADMIN+") references "+TABLE_USER_NAME+"("+USER_ID+"));";
    /*
    * Preferences for setting table VoteDej
    * */
    public static final String TABLE_VOTEDEJ_NAME = "votedejs";
    public static final String VOTEDEJ_ID = "_idVD";
    public static final String COLNUM_VDOWNER = "vdOwner";
    public static final String COLNUM_VDPURPOSE = "vdRestaurant";
    public static final String COLNUM_VDTYPE = "vdType";

    private static final String CREATE_TABLE_VOTEDEJ =
            "create table "+TABLE_VOTEDEJ_NAME+"("+VOTEDEJ_ID+" integer primary key autoincrement," +
                    COLNUM_VDOWNER+" integer not null," +
                    COLNUM_VDPURPOSE+" integer not null," +
                    COLNUM_VDTYPE+" varchar(4) not null," +
                    "foreign key ("+COLNUM_VDOWNER+") references "+TABLE_USER_NAME+"("+USER_ID+")," +
                    "foreign key ("+COLNUM_VDPURPOSE+") references "+TABLE_VOTEDEJ_NAME+"("+RESTAURANT_ID+"));";
    /*
    * Preferences for setting table VoteNote
    * */
    public static String TABLE_VOTENOTE_NAME = "votenotes";
    public static String VOTENOTE_ID = "_idVN";
    public static String COLNUM_VNOWNER = "vnOwner";
    public static String COLNUM_VNPURPOSE = "vnRestaurant";
    public static String COLNUM_VNNOTE = "vnNote";

    private static final String CREATE_TABLE_VOTENOTE =
            "create table "+TABLE_VOTENOTE_NAME+" ("+VOTENOTE_ID+" integer primary key autoincrement," +
                    COLNUM_VNOWNER+" integer not null," +
                    COLNUM_VNPURPOSE+" integer not null," +
                    COLNUM_VNNOTE+" intteger not null," +
                    "foreign key ("+COLNUM_VNOWNER+") references "+TABLE_USER_NAME+"("+USER_ID+")," +
                    "foreign key ("+COLNUM_VNPURPOSE+") references "+TABLE_RESTAURANT_NAME+"("+RESTAURANT_ID+"));";


    public DBHelper(Context context) {
        super(context, DBNAME, null, VERSION);
    }




    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL(CREATE_TABLE_USER);
        sqLiteDatabase.execSQL(CREATE_TABLE_RES);
        sqLiteDatabase.execSQL((CREATE_TABLE_GROUP));
        sqLiteDatabase.execSQL(CREATE_TABLE_VOTEDEJ);
        sqLiteDatabase.execSQL(CREATE_TABLE_VOTENOTE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }
}
