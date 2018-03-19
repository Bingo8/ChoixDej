package com.example.weibinwang.myapplication.Model.Provider;

import android.net.Uri;

import com.example.weibinwang.myapplication.Model.DatabaseHandler.DBHelper;

/**
 * Created by weibinwang on 2018/3/11.
 */

public final class DataProvider {

    public static final String RES_AUTHORITY = "com.example.weibinwang.myapplication.Model.Provider.RestaurantContentProvider";

    public static final String GROUP_AUTHORITY = "com.example.weibinwang.myapplication.Model.Provider.GroupContentProvider";

    public static final String SCHEME = "content";

    public static final Uri RES_CONTENT_URI = Uri.parse(SCHEME+"://"+RES_AUTHORITY);

    public static final Uri GROUP_CONTENT_URI = Uri.parse(SCHEME+"//"+GROUP_AUTHORITY);

    public static final Uri RES_MESSAGE_URI = Uri.withAppendedPath(RES_CONTENT_URI, DBHelper.TABLE_RESTAURANT_NAME);

    public static final Uri GROUP_MESSAGE_URI = Uri.withAppendedPath(GROUP_CONTENT_URI,DBHelper.TABLE_GROUP_NAME);
}
