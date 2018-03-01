package com.example.weibinwang.myapplication.Model.DatabaseService;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.weibinwang.myapplication.Bean.Restaurant;
import com.example.weibinwang.myapplication.Common.Classify.TypeRestaurant;
import com.example.weibinwang.myapplication.Model.DatabaseHandler.DBHelper;
import com.example.weibinwang.myapplication.Model.DatabaseOutils.RestaurantOpe;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by weibinwang on 2018/2/25.
 */

public class RestaurantService implements RestaurantOpe {

    private DBHelper dbHelper;
    private SQLiteDatabase database;
    private ContentValues values;

    public RestaurantService(DBHelper dbHelper){
        this.dbHelper = dbHelper;
    }
    /*
    * add restaurant into database
    * @param restaurant
    *
    * @return key_id
    * */
    @Override
    public long addRestaurant(Restaurant restaurant) {
        database = dbHelper.getWritableDatabase();
        values = new ContentValues();
        values.put(DBHelper.COLNUM_RNAME, restaurant.getName());
        values.put(DBHelper.COLNUM_RADDRESS, restaurant.getAddress());
        values.put(DBHelper.COLNUM_RDESCRIPTION, restaurant.getDescription());
        values.put(DBHelper.COLNUM_RCONTACT,restaurant.getContact());
        values.put(DBHelper.COLNUM_RTYPE, String.valueOf(restaurant.getType()));
        long id = database.insert(DBHelper.TABLE_RESTAURANT_NAME, null, values);
        database.close();
        return id;
    }
    /*
    * update infomation of restaurant
    * @param restaurant
    * */
    @Override
    public void updateRestaurant(Restaurant restaurant) {
        database = dbHelper.getWritableDatabase();
        values = new ContentValues();
        values.put(DBHelper.COLNUM_RNAME, restaurant.getName());
        values.put(DBHelper.COLNUM_RDESCRIPTION, restaurant.getDescription());
        values.put(DBHelper.COLNUM_RADDRESS, restaurant.getAddress());
        values.put(DBHelper.COLNUM_RCONTACT, restaurant.getContact());
        values.put(DBHelper.COLNUM_RTYPE, String.valueOf(restaurant.getType()));
        database.update(DBHelper.TABLE_RESTAURANT_NAME,values,DBHelper.RESTAURANT_ID + "= ?", new String[]{String.valueOf(restaurant.getId())});
        database.close();
    }
    /*
    * delete restaurant by name from database
    * @param name of restaurant
    * */
    @Override
    public void deleteRestaurantByName(String name) {
        database = dbHelper.getWritableDatabase();
        database.delete(DBHelper.TABLE_RESTAURANT_NAME, DBHelper.COLNUM_RNAME + " = ?", new String[]{name});
        database.close();
    }
    /*
    * request of searching restaurant by name
    * @param name of restaurant
    *
    * @return restaurant
    * */
    @Override
    public Restaurant queryRestaurantByName(String name) {
        database = dbHelper.getWritableDatabase();
        String sql = "select * from "+DBHelper.TABLE_RESTAURANT_NAME+" where "+DBHelper.COLNUM_RNAME+" = ?";
        Cursor cursor = database.rawQuery(sql, new String[]{name});

        Restaurant restaurant = null;

        while(cursor.moveToNext()){
            restaurant = new Restaurant();
            restaurant.setId(cursor.getInt(cursor.getColumnIndex(DBHelper.RESTAURANT_ID)));
            restaurant.setName(cursor.getString(cursor.getColumnIndex(DBHelper.COLNUM_RNAME)));
            restaurant.setAddress(cursor.getString(cursor.getColumnIndex(DBHelper.COLNUM_RADDRESS)));
            restaurant.setDescription(cursor.getString(cursor.getColumnIndex(DBHelper.COLNUM_RDESCRIPTION)));
            restaurant.setContact(cursor.getString(cursor.getColumnIndex(DBHelper.COLNUM_RCONTACT)));
            restaurant.setType(tranformStringToType(cursor.getString(cursor.getColumnIndex(DBHelper.COLNUM_RTYPE))) );
        }
        return restaurant;
    }
    /*
    * private method to transform the type of restaurant from String to TypeRestaurant
    * @param typeRestaurant : String
    *
    * @return typeRestaurant : TypeRestaurant
    * */
    private TypeRestaurant tranformStringToType(String type){
        for( TypeRestaurant t : TypeRestaurant.values() ){
            if(t.toString().toUpperCase().equals(type.toUpperCase())){
                return t;
            }
        }
        return TypeRestaurant.NONE;

    }
    /*
    * request of searching a list of restaurant with same type
    * @param type : TypeRestaurant
    *
    * @return List<Restaurant>
    * */
    public List<Restaurant> queryRestaurantByType(TypeRestaurant type){
        List<Restaurant> restaurantList = new ArrayList<Restaurant>();
        database = dbHelper.getWritableDatabase();
        String sql = "select * from "+DBHelper.TABLE_RESTAURANT_NAME+" where "+DBHelper.COLNUM_RTYPE+" = ?";
        Cursor cursor = database.rawQuery(sql, new String[]{type.toString()});

        while(cursor.moveToNext()){
            Restaurant r = new Restaurant();
            r.setId(cursor.getInt(cursor.getColumnIndex(DBHelper.RESTAURANT_ID)));
            r.setName(cursor.getString(cursor.getColumnIndex(DBHelper.COLNUM_RNAME)));
            r.setAddress(cursor.getString(cursor.getColumnIndex(DBHelper.COLNUM_RADDRESS)));
            r.setDescription(cursor.getString(cursor.getColumnIndex(DBHelper.COLNUM_RDESCRIPTION)));
            r.setContact(cursor.getString(cursor.getColumnIndex(DBHelper.COLNUM_RCONTACT)));
            r.setType(tranformStringToType(cursor.getString(cursor.getColumnIndex(DBHelper.COLNUM_RTYPE))) );

            restaurantList.add(r);
        }
        cursor.close();
        database.close();

        return restaurantList;
    }
    /*
    * Judge if restaurant have existed inside database
    * @param nameRestaurant : String
    *
    * @return boolean
    * */
    @Override
    public boolean isExisted(String name) {
        Restaurant judge = queryRestaurantByName(name);
        return judge == null ? false : true;
    }
}
