package com.example.weibinwang.myapplication.Model.DatabaseService;


import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.weibinwang.myapplication.Bean.User;
import com.example.weibinwang.myapplication.Model.DatabaseHandler.DBHelper;
import com.example.weibinwang.myapplication.Model.DatabaseOutils.UserOpe;

import java.util.ArrayList;
import java.util.List;


/**
 * Created by weibinwang on 2018/2/22.
 */

public class UserService implements UserOpe{
    private DBHelper dbHelper;
    private SQLiteDatabase database;
    private ContentValues values;


    public UserService(DBHelper dbHelper){
        this.dbHelper = dbHelper;
    }

    /*
    * Add a new user
    * @param user : information for new users
    *
    * @return long : id for user
    * */
    @Override
    public long addUser(User user) {
        database = dbHelper.getWritableDatabase();
        values = new ContentValues();
        values.put(DBHelper.COLNUM_UFIRSTNAME, user.getFirstname());
        values.put(DBHelper.COLNUM_USECONDNAME, user.getSecondname());
        values.put(DBHelper.COLNUM_UUSERNAME, user.getUsername());
        values.put(DBHelper.COLNUM_UPASSWORD, user.getPassword());
        values.put(DBHelper.COLNUM_UEMAIL, user.getMail());
        long id = database.insert(DBHelper.TABLE_USER_NAME, null, values);
        database.close();
        return id;
    }
    /*
    * Delete user by username information
    * @param username
    * */
    @Override
    public void deleteUserByUsername(String username) {
        database = dbHelper.getWritableDatabase();
        database.delete(DBHelper.TABLE_USER_NAME, DBHelper.COLNUM_UUSERNAME + " = ?", new String[]{username});
        database.close();
    }
    /*
    * Update user's information
    * @param user
    * */
    @Override
    public void updateUser(User user) {
        database = dbHelper.getWritableDatabase();
        values = new ContentValues();
        values.put(DBHelper.COLNUM_UFIRSTNAME, user.getFirstname());
        values.put(DBHelper.COLNUM_USECONDNAME, user.getSecondname());
        values.put(DBHelper.COLNUM_UUSERNAME, user.getUsername());
        values.put(DBHelper.COLNUM_UPASSWORD, user.getPassword());
        values.put(DBHelper.COLNUM_UEMAIL, user.getMail());
        database.update(DBHelper.TABLE_USER_NAME,values,DBHelper.USER_ID + "= ?", new String[]{String.valueOf(user.getId())});
        database.close();
    }
    /*
    * Search user by clause of email
    * @param email
    *
    * @return User
    * */
    @Override
    public User queryUserByEmail(String email) {
        database = dbHelper.getWritableDatabase();

        String sql = "select * from "+DBHelper.TABLE_USER_NAME+" where " + DBHelper.COLNUM_UEMAIL+ " = ?;";
        Cursor cursor = database.rawQuery(sql, new String[]{email});
        User user = null;

       while(cursor.moveToNext()) {
           user = new User();
           user.setId(cursor.getInt(cursor.getColumnIndex(DBHelper.USER_ID)));
           user.setUsername(cursor.getString(cursor.getColumnIndex(DBHelper.COLNUM_UUSERNAME)));
           user.setFirstname(cursor.getString(cursor.getColumnIndex(DBHelper.COLNUM_UFIRSTNAME)));
           user.setSecondname(cursor.getString(cursor.getColumnIndex(DBHelper.COLNUM_USECONDNAME)));
           user.setEmail(cursor.getString(cursor.getColumnIndex(DBHelper.COLNUM_UEMAIL)));
           user.setPassword(cursor.getString(cursor.getColumnIndex(DBHelper.COLNUM_UPASSWORD)));
       }

        cursor.close();
        database.close();
        return user;
    }
    /*
    * Judge if the username have existed already.
    * @param username
    *
    * @return boolean
    * */
    @Override
    public boolean isExistedUsername(String username) {
        database = dbHelper.getReadableDatabase();
        boolean res = false;
        String sql = "select * from "+DBHelper.TABLE_USER_NAME
                + " where " + DBHelper.COLNUM_UUSERNAME+ " = ?;";
        Cursor cursor = database.rawQuery(sql, new String[]{username});
        if(cursor.moveToFirst()){
            res=true;
        }
        cursor.close();
        database.close();
        return res;
    }

    /*
    * Judge if email exists
    * @param email
    *
    * @return boolean
    * */
    @Override
    public boolean isExistedEmail(String email) {
        database = dbHelper.getReadableDatabase();
        boolean res = false;
        String sql = "select * from "+DBHelper.TABLE_USER_NAME
                + " where " + DBHelper.COLNUM_UEMAIL+" = ?;";
        Cursor cursor = database.rawQuery(sql,new String[]{email});

        if(cursor.moveToFirst()){
            res = true;
        }
        cursor.close();
        database.close();
        return res;
    }

    /*
    * Judge if user login successfully
    * @param user
    * */
    @Override
    public boolean isLoginSuccess(User user) {
        boolean res = false;

        database = dbHelper.getReadableDatabase();
        String sql1 = "select * from "+DBHelper.TABLE_USER_NAME+" where "+DBHelper.COLNUM_UEMAIL+" = ? and "+ DBHelper.COLNUM_UPASSWORD+" = ?";
        String sql2 = "select * from "+DBHelper.TABLE_USER_NAME+" where "+DBHelper.COLNUM_UUSERNAME+" = ? and " + DBHelper.COLNUM_UPASSWORD+" = ?";
        Cursor cursor = null;

        if(user.getUsername()!=null && (user.getMail() == null)){
            cursor = database.rawQuery(sql2, new String[]{user.getUsername(),user.getPassword()});
        }else{
            cursor = database.rawQuery(sql1, new String[]{user.getMail(),user.getPassword()});
        }

        if(cursor.moveToFirst()){
            res = true;
        }

        cursor.close();
        database.close();
        return res;

    }
    @Override
    public User queryUserByUserId(long id){

        database = dbHelper.getWritableDatabase();

        String sql = "select * from "+DBHelper.TABLE_USER_NAME+" where " + DBHelper.USER_ID+ " = ?;";
        Cursor cursor = database.rawQuery(sql, new String[]{String.valueOf(id)});

        User user = null;

        while(cursor.moveToNext()) {
            user = new User();
            user.setId(cursor.getInt(cursor.getColumnIndex(DBHelper.USER_ID)));
            user.setUsername(cursor.getString(cursor.getColumnIndex(DBHelper.COLNUM_UUSERNAME)));
            user.setFirstname(cursor.getString(cursor.getColumnIndex(DBHelper.COLNUM_UFIRSTNAME)));
            user.setSecondname(cursor.getString(cursor.getColumnIndex(DBHelper.COLNUM_USECONDNAME)));
            user.setEmail(cursor.getString(cursor.getColumnIndex(DBHelper.COLNUM_UEMAIL)));
            user.setPassword(cursor.getString(cursor.getColumnIndex(DBHelper.COLNUM_UPASSWORD)));
        }

        cursor.close();
        database.close();
        return user;
    }

    public List<User> queryUsersByListId(List<Long> lstID){
        List<User> lstUsers = new ArrayList<>();
        for( Long id : lstID) {
            lstUsers.add(queryUserByUserId(id));
        }
        return lstUsers;
    }

}
