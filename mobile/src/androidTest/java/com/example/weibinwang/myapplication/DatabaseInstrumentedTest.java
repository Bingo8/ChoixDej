package com.example.weibinwang.myapplication;

import android.content.Context;
import android.support.test.InstrumentationRegistry;
import android.support.test.runner.AndroidJUnit4;
import android.util.Log;

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
import org.junit.runner.RunWith;

import static junit.framework.TestCase.assertTrue;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;

/**
 * Created by weibinwang on 2018/2/25.
 */
@RunWith(AndroidJUnit4.class)
public class DatabaseInstrumentedTest {

    private UserOpe us;
    private RestaurantOpe rs;
    private DBHelper dbHelper;

    @Before
    public void setUp(){

        System.out.print("djskajd");

        Context appContext = InstrumentationRegistry.getTargetContext();

        DBHelper dbHelper = new DBHelper(appContext);
        us = new UserService(dbHelper);
        rs = new RestaurantService(dbHelper);

        System.out.print("Ok setup");
    }

    // Test method UserService.addUser(User user);
    @Test
    public void validateUser() throws Exception {

        Log.d("TAG","START");

        User user = new User();

        user.setUsername("WANG");
        user.setEmail("weibin.wang.fr@hotmail.com");
        user.setPassword("psd");
        long user_id = us.addUser(user);

        user.setId(user_id);

        User actualuser = us.queryUserByEmail(user.getMail());

       assertEquals(actualuser.getUsername(), user.getUsername());
    }
    // Test method RestaurantService.addRestaurant(Restaurant res);
    @Test
    public void validateRestaurant() throws  Exception {

        Restaurant restaurant = new Restaurant();
        restaurant.setName("Sushi");

        restaurant.setType(TypeRestaurant.JAPANESE);
        restaurant.setDescription("Good Food");
        restaurant.setContact("07 67 67 67 67");
        restaurant.setAddress(" dhshjahdjshajkhjskhd ");

        long id = rs.addRestaurant(restaurant);

        Restaurant actuel = rs.queryRestaurantByName("Sushi");

        assertEquals(actuel.getName(),restaurant.getName());
    }
    // Test method UserService.updateUser(User user);
    @Test
    public void validateUpdateUser() throws Exception {

        User user = us.queryUserByEmail("weibin.wang.fr@hotmail.com");
        user.setPassword("new password");
        us.updateUser(user);

        assertEquals("new password",us.queryUserByEmail("weibin.wang.fr@hotmail.com").getPassword());
    }
    // Test method Userservice.deleteUser(User user);
    @Test
    public void validateDeleteUser() throws Exception{
        User os = us.queryUserByEmail("weibin.wang.fr@hotmail.com");
        us.deleteUserByUsername(os.getUsername());

        User user = us.queryUserByEmail(os.getMail());

        assertEquals(null, user);
    }

    // Test method UserService.isLoginSuccess(User user);
    @Test
    public void validateLoginSuccess() throws Exception{
        User user = new User();
        user.setEmail("ghghghg@test.com");
        user.setPassword("password");
        user.setUsername("bingo");

        long id = us.addUser(user);
        user.setId(id);

        assertTrue(us.isLoginSuccess(user));
    }

    // Test method UserService.isExistedUsername(String username)
    @Test
    public void validateDoubleUsernameTrue() throws Exception{
        assertTrue(us.isExistedUsername("bingo"));
    }

    @Test
    public void validateDoubleUsernameFalse() throws Exception{
        assertFalse(us.isExistedUsername("Bingo"));
    }




}
