package com.example.weibinwang.myapplication.Model.DatabaseService;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.weibinwang.myapplication.Bean.Group;
import com.example.weibinwang.myapplication.Common.Classify.TypeGroup;
import com.example.weibinwang.myapplication.Model.DatabaseHandler.DBHelper;
import com.example.weibinwang.myapplication.Model.DatabaseOutils.GroupOpe;

import java.util.ArrayList;
import java.util.List;


/**
 * Created by Eliass on 28/02/2018.
 */

public class GroupService implements GroupOpe {

    private DBHelper dbHelper;
    private SQLiteDatabase database;
    private ContentValues values;

    public GroupService(DBHelper dbHelper){this.dbHelper = dbHelper;
    }

    /*
     * add group into database
     * @param group
     *
     * @return key_id
     * */

    @Override
    public long addGroup(Group group) {
        database = dbHelper.getWritableDatabase();
        values = new ContentValues();
        values.put(DBHelper.COLNUM_GNAME, group.getName());
        values.put(DBHelper.COLNUM_GTYPE, String.valueOf(group.getType()));
        values.put(DBHelper.COLNUM_GADMIN, String.valueOf(group.getAdministrator()));

        long id = database.insert(DBHelper.TABLE_GROUP_NAME, null, values);
        database.close();
        return 0;
    }
    /*
     * update infomation of group
     * @param group
     * */

    @Override
    public void updateGroup(Group group) {
        database = dbHelper.getWritableDatabase();
        values = new ContentValues();
        values.put(DBHelper.COLNUM_GNAME, group.getName());
        values.put(DBHelper.COLNUM_GTYPE, String.valueOf(group.getType()));
        database.update(DBHelper.TABLE_GROUP_NAME,values,DBHelper.GROUP_ID + "= ?", new String[]{String.valueOf(group.getId())});
        database.close();

    }

    @Override
    public void deleteGroupByName(String name) {
        database = dbHelper.getWritableDatabase();
        database.delete(DBHelper.TABLE_GROUP_NAME, DBHelper.COLNUM_GNAME + " = ?", new String[]{name});
        database.close();

    }
    /*
    * request of searching group by name
    * @param name of group
    *
    * @return group
    * */

    @Override
    public Group queryGroupByName(String name) {
        database = dbHelper.getWritableDatabase();
        String sql = "select * from "+DBHelper.TABLE_GROUP_NAME+" where "+DBHelper.COLNUM_GNAME+" = ?";

        UserService us = new UserService(dbHelper);

        Cursor cursor = database.rawQuery(sql, new String[]{name});

        Group group = null;

        while(cursor.moveToNext()){
            group = new Group();
            group.setId(cursor.getInt(cursor.getColumnIndex(DBHelper.GROUP_ID)));
            group.setName(cursor.getString(cursor.getColumnIndex(DBHelper.COLNUM_GNAME)));
            group.setType(tranformStringToType(cursor.getString(cursor.getColumnIndex(DBHelper.COLNUM_GTYPE))));
            group.setAdministrator(us.queryUserByUserId(cursor.getInt(cursor.getColumnIndex(DBHelper.COLNUM_GADMIN))));

        }
        cursor.close();
        database.close();

        return group;
    }
    /*
    * private method to transform the type of restaurant from String to TypeGroup
    * @param typeGroup : String
    *
    * @return typeGroup : TypeGroup
    * */
    private TypeGroup tranformStringToType(String type){
        for( TypeGroup t : TypeGroup.values() ){
            if(t.toString().toUpperCase().equals(type.toUpperCase())){
                return t;
            }
        }
        return TypeGroup.NONE;

    }

    /*
    * request of searching a list of group with same type
    * @param type : TypeRestaurant
    *
    * @return List<Restaurant>
    * */
    public List<Group> queryGroupByType(TypeGroup type){
        List<Group> groupList = new ArrayList<Group>();

        database = dbHelper.getWritableDatabase();
        String sql = "select * from "+DBHelper.TABLE_GROUP_NAME+" where "+DBHelper.COLNUM_GTYPE+" = ?";
        Cursor cursor = database.rawQuery(sql, new String[]{type.toString()});

        while(cursor.moveToNext()){
            Group g = new Group();
            g.setId(cursor.getInt(cursor.getColumnIndex(DBHelper.GROUP_ID)));
            g.setName(cursor.getString(cursor.getColumnIndex(DBHelper.COLNUM_GNAME)));
            g.setType(tranformStringToType(cursor.getString(cursor.getColumnIndex(DBHelper.COLNUM_GTYPE))));
            g.setAdministrator(new UserService(dbHelper).queryUserByUserId(cursor.getInt(cursor.getColumnIndex(DBHelper.COLNUM_GADMIN))) );
            groupList.add(g);
        }

        cursor.close();
        database.close();

        return groupList;
    }

    @Override
    public boolean isExisted(String name) {
        Group judge = queryGroupByName(name);
        return judge == null ? false : true;
    }
}