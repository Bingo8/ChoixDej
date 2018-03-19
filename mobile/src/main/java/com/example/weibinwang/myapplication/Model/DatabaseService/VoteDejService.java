package com.example.weibinwang.myapplication.Model.DatabaseService;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.weibinwang.myapplication.Bean.VoteDej;
import com.example.weibinwang.myapplication.Model.DatabaseHandler.DBHelper;
import com.example.weibinwang.myapplication.Model.DatabaseOutils.VoteDejOpe;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Eliass on 28/02/2018.
 */

public class VoteDejService implements VoteDejOpe {

    private DBHelper dbHelper;
    private SQLiteDatabase database;
    private ContentValues values;

    public VoteDejService (DBHelper dbHelper){
        this.dbHelper = dbHelper;
    }

    @Override
    public long addVoteDej(VoteDej votedej) {

        database = dbHelper.getWritableDatabase();
        values = new ContentValues();
        values.put(DBHelper.COLNUM_VDOWNER, votedej.getOwner().getId());
        values.put(DBHelper.COLNUM_VDPURPOSE,votedej.getPurpose().getId());
        long id = database.insert(DBHelper.TABLE_VOTEDEJ_NAME, null, values);
        database.close();
        return id;
    }

    @Override
    public void updateVoteDej(VoteDej votedej) {
        database = dbHelper.getWritableDatabase();
        values = new ContentValues();
        values.put(DBHelper.COLNUM_VDOWNER, votedej.getOwner().getId());
        values.put(DBHelper.COLNUM_VDPURPOSE,votedej.getPurpose().getId());
        database.update(DBHelper.TABLE_VOTEDEJ_NAME,values,DBHelper.VOTEDEJ_ID + "= ?", new String[]{String.valueOf(votedej.getId())});
        database.close();
    }
    /*
        * delete votedej by name from database
        * @param name of votedej
        * */
    @Override
    public void deleteVoteDej(long id) {
        database = dbHelper.getWritableDatabase();
        database.delete(DBHelper.TABLE_VOTEDEJ_NAME, DBHelper.VOTEDEJ_ID + " = ?", new String[]{String.valueOf(id)});
        database.close();

    }
    /*
        * Update votedej information
        * @param votedej
        * */
    @Override
    public VoteDej queryVoteDejById(long id) {
        database = dbHelper.getWritableDatabase();
        String sql = "select * from "+DBHelper.TABLE_VOTEDEJ_NAME+" where "+DBHelper.VOTEDEJ_ID + " = ?";

        UserService us = new UserService(dbHelper);
        RestaurantService rs = new RestaurantService(dbHelper);

        Cursor cursor = database.rawQuery(sql, new String[]{String.valueOf(id)});

        VoteDej votedej =  null;
        while(cursor.moveToNext()){
            votedej = new VoteDej();
            votedej.setId(cursor.getInt(cursor.getColumnIndex(DBHelper.VOTEDEJ_ID)));
            votedej.setOwner(us.queryUserByUserId( cursor.getInt(cursor.getColumnIndex(DBHelper.COLNUM_VDOWNER))));
            votedej.setPurpose(rs.queryRestaurantById(cursor.getInt(cursor.getColumnIndex(DBHelper.COLNUM_RADDRESS))));
        }
        database.close();
        cursor.close();
        return votedej;
    }

    @Override
    public List<VoteDej> queryVoteDejByPurpose(String restaurantName) {
        List<VoteDej> lstVD = new ArrayList<>();
        database = dbHelper.getReadableDatabase();

        RestaurantService rs = new RestaurantService(dbHelper);
        String sql = "select * from "+ DBHelper.TABLE_VOTEDEJ_NAME+" where "+DBHelper.COLNUM_VDPURPOSE+" = ?";
        Cursor cursor = database.rawQuery(sql, new String[]{String.valueOf(rs.queryRestaurantByName(restaurantName).getId())});

        VoteDej vd = null;
        while(cursor.moveToNext()){
            vd = new VoteDej();
            vd.setId(cursor.getInt(cursor.getColumnIndex(DBHelper.VOTEDEJ_ID)));
            vd.setOwner(new UserService(dbHelper).queryUserByUserId(cursor.getInt(cursor.getColumnIndex(DBHelper.COLNUM_VDOWNER))));
            vd.setPurpose(rs.queryRestaurantById(cursor.getInt(cursor.getColumnIndex(DBHelper.COLNUM_VDPURPOSE))));

            lstVD.add(vd);
        }
        cursor.close();
        database.close();

        return lstVD;
    }
}