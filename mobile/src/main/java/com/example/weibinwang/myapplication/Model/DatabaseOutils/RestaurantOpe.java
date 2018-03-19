package com.example.weibinwang.myapplication.Model.DatabaseOutils;

import com.example.weibinwang.myapplication.Bean.Restaurant;

/**
 * Created by weibinwang on 2018/2/25.
 */

public interface RestaurantOpe {

    long addRestaurant(Restaurant restaurant);

    void updateRestaurant(Restaurant restaurant);

    void deleteRestaurantByName(String name);

    Restaurant queryRestaurantByName(String name);

    Restaurant queryRestaurantById(long id);

    boolean isExisted(String name);
}
