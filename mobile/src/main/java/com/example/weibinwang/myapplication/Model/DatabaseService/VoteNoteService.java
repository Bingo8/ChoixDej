package com.example.weibinwang.myapplication.Model.DatabaseService;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Build;
import android.support.annotation.RequiresApi;

import com.example.weibinwang.myapplication.Bean.VoteNote;
import com.example.weibinwang.myapplication.Model.DatabaseHandler.DBHelper;
import com.example.weibinwang.myapplication.Model.DatabaseOutils.VoteNoteOpe;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * Created by weibinwang on 2018/3/11.
 */

public class VoteNoteService implements VoteNoteOpe{

    private DBHelper dbHelper;
    private SQLiteDatabase database;
    private ContentValues values;

    public VoteNoteService(DBHelper dbHelper){
        this.dbHelper = dbHelper;
    }

    @Override
    public long addVoteNote(VoteNote vn) {

        database = dbHelper.getWritableDatabase();

        values = new ContentValues();
        values.put(DBHelper.COLNUM_VNNOTE, vn.getNote());
        values.put(DBHelper.COLNUM_VNOWNER,vn.getOwner().getId());
        values.put(DBHelper.COLNUM_VNPURPOSE,vn.getPurpose().getId());

        long id = database.insert(DBHelper.TABLE_VOTEDEJ_NAME, null, values);
        database.close();
        return id;
    }

    @Override
    public void deleteVoteNote(long id) {
        database = dbHelper.getWritableDatabase();
        database.delete(DBHelper.TABLE_VOTENOTE_NAME, DBHelper.VOTENOTE_ID + " = ?", new String[]{String.valueOf(id)});
        database.close();
    }

    @Override
    public void updateVoteNote(VoteNote vn) {
        database = dbHelper.getWritableDatabase();
        values = new ContentValues();
        values.put(DBHelper.COLNUM_VNOWNER, vn.getOwner().getId());
        values.put(DBHelper.COLNUM_VNPURPOSE,vn.getPurpose().getId());
        values.put(DBHelper.COLNUM_VNNOTE,vn.getNote());
        database.update(DBHelper.TABLE_VOTENOTE_NAME,values,DBHelper.VOTENOTE_ID + "= ?", new String[]{String.valueOf(vn.getId())});
        database.close();
    }

    @Override
    public List<VoteNote> queryListVoteNoteByOwner(long user_id) {
        List<VoteNote> lstVN = new ArrayList<>();
        database = dbHelper.getReadableDatabase();

        String sql = "select * from "+ DBHelper.TABLE_VOTENOTE_NAME+" where "+DBHelper.COLNUM_VNOWNER+" = ?";
        Cursor cursor = database.rawQuery(sql, new String[]{String.valueOf(user_id)});
        VoteNote vn = null;
        while(cursor.moveToNext()){
            vn = new VoteNote();
            vn.setId(cursor.getInt(cursor.getColumnIndex(DBHelper.VOTENOTE_ID)));
            vn.setNote(cursor.getInt(cursor.getColumnIndex(DBHelper.COLNUM_VNNOTE)));
            vn.setOwner(new UserService(dbHelper).queryUserByUserId(cursor.getInt(cursor.getColumnIndex(DBHelper.COLNUM_VNOWNER))));
            vn.setPurpose(new RestaurantService(dbHelper).queryRestaurantById(cursor.getInt(cursor.getColumnIndex(DBHelper.COLNUM_VDPURPOSE))));

            lstVN.add(vn);
        }
        cursor.close();
        database.close();
        return lstVN;
    }

    @Override
    public List<VoteNote> queryListVoteNoteByPurpose(long restaurant_id) {
        List<VoteNote> lstVN = new ArrayList<>();
        database = dbHelper.getReadableDatabase();

        String sql = "select * from "+ DBHelper.TABLE_VOTENOTE_NAME+" where "+DBHelper.COLNUM_VNPURPOSE+" = ?";
        Cursor cursor = database.rawQuery(sql, new String[]{String.valueOf(restaurant_id)});

        VoteNote vn = null;
        while(cursor.moveToNext()){
            vn = new VoteNote();
            vn.setId(cursor.getInt(cursor.getColumnIndex(DBHelper.VOTENOTE_ID)));
            vn.setNote(cursor.getInt(cursor.getColumnIndex(DBHelper.COLNUM_VNNOTE)));
            vn.setOwner(new UserService(dbHelper).queryUserByUserId(cursor.getInt(cursor.getColumnIndex(DBHelper.COLNUM_VNOWNER))));
            vn.setPurpose(new RestaurantService(dbHelper).queryRestaurantById(cursor.getInt(cursor.getColumnIndex(DBHelper.COLNUM_VDPURPOSE))));

            lstVN.add(vn);
        }
        cursor.close();
        database.close();
        return lstVN;
    }

    @Override
    public boolean isExisted(long id) {
        database = dbHelper.getReadableDatabase();
        String sql = "select * from "+DBHelper.TABLE_VOTENOTE_NAME+" where "+ DBHelper.VOTENOTE_ID+" = ?";

        Cursor cursor = database.rawQuery(sql, new String[]{String.valueOf(id)});
        boolean res = false;

        if(cursor.moveToFirst()) {
            res = true;
        }
        cursor.close();
        database.close();
        return res;
    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    @Override
    public Map<Long, Set<Long>> queryTableUser_Res() {

        Map<Long,Set<Long>> userItemsMap = new HashMap<>();

        database = dbHelper.getReadableDatabase();

        String sql = "select * from "+DBHelper.TABLE_VOTENOTE_NAME +" where "+DBHelper.COLNUM_VNNOTE+" > 3 ";


        Cursor cursor = database.rawQuery(sql,null);

        Set<Long> setItems = null;


        while(cursor.moveToNext()){

            long user_id = cursor.getLong(cursor.getColumnIndex(DBHelper.COLNUM_VNOWNER));
            long res_id = cursor.getLong(cursor.getColumnIndex(DBHelper.COLNUM_VDPURPOSE));

            if(userItemsMap.containsKey(user_id)){
                userItemsMap.get(user_id).add(res_id);
            }else{
                setItems = new HashSet<>();
                setItems.add(res_id);
                userItemsMap.put(user_id,setItems);
            }


        }

        cursor.close();
        database.close();

        return userItemsMap;
    }

}
