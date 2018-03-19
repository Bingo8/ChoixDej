package com.example.weibinwang.myapplication;

import android.app.LoaderManager;
import android.content.Loader;
import android.database.Cursor;
import android.os.Bundle;
import android.support.test.runner.AndroidJUnit4;
import android.support.v7.app.AppCompatActivity;

import org.junit.runner.RunWith;

/**
 * Created by weibinwang on 2018/3/12.
 */
@RunWith(AndroidJUnit4.class)
public class RestaurantInstrumentedTest extends AppCompatActivity implements LoaderManager.LoaderCallbacks<Cursor>{

    @Override
    public Loader<Cursor> onCreateLoader(int i, Bundle bundle) {
        return null;
    }

    @Override
    public void onLoadFinished(Loader<Cursor> loader, Cursor cursor) {

    }

    @Override
    public void onLoaderReset(Loader<Cursor> loader) {

    }
}
