package com.example.weibinwang.myapplication;

import android.content.Context;
import android.test.InstrumentationTestCase;

import com.example.weibinwang.myapplication.Bean.Restaurant;
import com.example.weibinwang.myapplication.Bean.User;
import com.example.weibinwang.myapplication.Common.Classify.TypeRestaurant;
import com.example.weibinwang.myapplication.Model.DatabaseHandler.DBHelper;
import com.example.weibinwang.myapplication.Model.DatabaseOutils.RestaurantOpe;
import com.example.weibinwang.myapplication.Model.DatabaseOutils.UserOpe;
import com.example.weibinwang.myapplication.Model.DatabaseService.RestaurantService;
import com.example.weibinwang.myapplication.Model.DatabaseService.UserService;

import org.junit.Before;
import org.junit.Test;

/**
 * Created by weibinwang on 2018/2/25.
 */



public class DatabaseUnitTest extends InstrumentationTestCase{

    private UserOpe us;
    private RestaurantOpe rs;

    @Before
    protected void setUp() throws Exception{
        Context appContext = getInstrumentation().getTargetContext();

        DBHelper dbHelper = new DBHelper(appContext);
        us = new UserService(dbHelper);
        rs = new RestaurantService(dbHelper);
    }

    @Test
    public void validateUser() throws Exception {
        User user = new User();

        user.setUsername("WANG");
        user.setEmail("weibin.wang.fr@hotmail.com");
        user.setPassword("psd");
        long user_id = us.addUser(user);

        user.setId(user_id);

        User actualuser = us.queryUserByEmail(user.getMail());

        assertEquals(actualuser.getUsername().toUpperCase(), user.getUsername().toUpperCase());
    }

    @Test
    public void validateRestaurant() throws  Exception {
        Restaurant restaurant = new Restaurant();
        restaurant.setName("Sushi");
        restaurant.setType(TypeRestaurant.JAPANESE);
        restaurant.setDescription("Good Food");
        restaurant.setContact("07 67 67 67 67");
        restaurant.setAddress(" dhshjahdjshajkhjskhd ");

        long id =rs.addRestaurant(restaurant);

        Restaurant actuel = rs.queryRestaurantByName("Sushi");

        assertEquals(actuel.getName(),restaurant.getName());

    }


}
