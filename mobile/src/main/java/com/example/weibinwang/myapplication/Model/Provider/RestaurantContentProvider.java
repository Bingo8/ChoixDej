package com.example.weibinwang.myapplication.Model.Provider;

import android.content.ContentProvider;
import android.content.ContentValues;
import android.content.UriMatcher;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import com.example.weibinwang.myapplication.Model.DatabaseHandler.DBHelper;

/**
 * Created by weibinwang on 2018/3/11.
 */

public class RestaurantContentProvider extends ContentProvider{
    private static final int TABLE_DIR = 1;

    private static UriMatcher uriMatcher;
    private DBHelper dbHelper;

    static {
        uriMatcher = new UriMatcher(UriMatcher.NO_MATCH);
        uriMatcher.addURI(DataProvider.RES_AUTHORITY,DBHelper.TABLE_RESTAURANT_NAME,TABLE_DIR);
    }

    @Override
    public boolean onCreate() {
        dbHelper = new DBHelper(getContext());
        return true;
    }

    @Nullable
    @Override
    public Cursor query(@NonNull Uri uri, @Nullable String[] strings, @Nullable String s, @Nullable String[] strings1, @Nullable String s1) {
        SQLiteDatabase database = dbHelper.getReadableDatabase();
        Cursor cursor = null;
        switch(uriMatcher.match(uri)){
            case TABLE_DIR:
                //String sql = "select * from "+DBHelper.TABLE_RESTAURANT_NAME+" inner join "+
                  //      DBHelper.TABLE_VOTENOTE_NAME+" on "+DBHelper.TABLE_RESTAURANT_NAME+"."+DBHelper.RESTAURANT_ID
                    //    +" = "+DBHelper.TABLE_VOTENOTE_NAME+"."+DBHelper.COLNUM_VNPURPOSE;
                cursor = database.query(DBHelper.TABLE_RESTAURANT_NAME,strings,s,strings1,null,null,s1);
                break;
            default:
                break;
        }
        if(cursor != null){
            cursor.setNotificationUri(getContext().getContentResolver(),uri);
        }
        return cursor;
    }

    @Nullable
    @Override
    public String getType(@NonNull Uri uri) {
        switch (uriMatcher.match(uri)){
            case TABLE_DIR:
                return "vnd.android.cursor.dir/vnd."+DataProvider.RES_AUTHORITY
                        + DBHelper.TABLE_RESTAURANT_NAME;
            default:
                break;
        }
        return null;
    }

    @Nullable
    @Override
    public Uri insert(@NonNull Uri uri, @Nullable ContentValues contentValues) {
        SQLiteDatabase database = dbHelper.getWritableDatabase();
        Uri returnUri = null;
        switch(uriMatcher.match(uri)){
            case TABLE_DIR:
                long rowId = database.insert(DBHelper.TABLE_RESTAURANT_NAME,null,contentValues);
                returnUri = Uri.parse("content://"+DataProvider.RES_AUTHORITY+"/"+DBHelper.TABLE_RESTAURANT_NAME
                + "/"+rowId);
                break;
            default:
                break;
        }
        getContext().getContentResolver().notifyChange(uri,null);
        return returnUri;
    }

    @Override
    public int delete(@NonNull Uri uri, @Nullable String s, @Nullable String[] strings) {
        SQLiteDatabase database = this.dbHelper.getWritableDatabase();
        int deleteRow = 0;
        switch(uriMatcher.match(uri)){
            case TABLE_DIR:
                deleteRow=database.delete(DBHelper.TABLE_RESTAURANT_NAME,s,strings);
                break;
            default:
                break;
        }
        getContext().getContentResolver().notifyChange(uri,null);
        return deleteRow;
    }

    @Override
    public int update(@NonNull Uri uri, @Nullable ContentValues contentValues, @Nullable String s, @Nullable String[] strings) {
        SQLiteDatabase database = this.dbHelper.getWritableDatabase();
        int updateRow = 0;
        switch(uriMatcher.match(uri)){
            case TABLE_DIR:
                updateRow = database.update(DBHelper.TABLE_RESTAURANT_NAME,contentValues,s,strings);
                break;
            default:
                break;
        }
        if(updateRow>0){
            getContext().getContentResolver().notifyChange(uri,null);
        }
        return 0;
    }
}
