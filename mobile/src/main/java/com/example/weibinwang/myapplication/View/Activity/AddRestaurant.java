package com.example.weibinwang.myapplication.View.Activity;

import android.app.LoaderManager;
import android.content.CursorLoader;
import android.content.Loader;
import android.database.Cursor;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.example.weibinwang.myapplication.Model.Provider.DataProvider;
import com.example.weibinwang.myapplication.R;

public class AddRestaurant extends AppCompatActivity implements LoaderManager.LoaderCallbacks<Cursor>{

    private final int LOADER_ID = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_restaurant);

        getSupportLoaderManager().initLoader(LOADER_ID,null, (android.support.v4.app.LoaderManager.LoaderCallbacks<Object>) this);
    }

    @Override
    public Loader<Cursor> onCreateLoader(int i, Bundle bundle) {
        if(i==LOADER_ID){
            return new CursorLoader(this, DataProvider.RES_MESSAGE_URI,null,null,null,DataProvider.SCHEME);
        }
        return null;
    }

    @Override
    public void onLoadFinished(Loader<Cursor> loader, Cursor cursor) {

    }

    @Override
    public void onLoaderReset(Loader<Cursor> loader) {

    }

    @Override
    protected void onDestroy(){
        getSupportLoaderManager().destroyLoader(LOADER_ID);
        super.onDestroy();
    }
}
