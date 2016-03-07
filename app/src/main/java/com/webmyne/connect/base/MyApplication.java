package com.webmyne.connect.base;

import android.app.Application;

import com.webmyne.connect.Utils.APIConstants;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by priyasindkar on 07-03-2016.
 */
public class MyApplication extends Application {
    private static MyApplication sInstance;
    public static Retrofit retrofit;

    @Override
    public void onCreate() {
        super.onCreate();
        // initialize the singleton
        sInstance = this;
        retrofit = new Retrofit.Builder()
                .baseUrl(APIConstants.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
    }

    public static synchronized MyApplication getInstance() {
        return sInstance;
    }
}
