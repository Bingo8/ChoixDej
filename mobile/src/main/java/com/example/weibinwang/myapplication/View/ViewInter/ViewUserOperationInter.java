package com.example.weibinwang.myapplication.View.ViewInter;

import com.example.weibinwang.myapplication.Bean.User;

/**
 * Created by weibinwang on 2018/2/28.
 */

public interface ViewUserOperationInter {
    // To get username
    String getUsername();
    // To get email address
    String getEmail();
    //clear input username or email inside the widget textview
    void clearUsername();
    //clear input password inside the widget textview
    void clearUserPass();
    //hide the bar of loading
    void hideLoading();

    /*
    * Notice the successful state of login or registry
    * @param user info of user
    * @param tag info of notice
    * */
    void successHint(User user, String tag);
    /*
    * Notice the fail state of login or registry
    * @param user info of user
    * @param tag info of notice
    * */
    void failHint(User user, String tag);

}
