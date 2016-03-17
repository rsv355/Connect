package com.webmyne.connect.base;

import android.app.Application;
import android.content.Context;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.mikepenz.iconics.IconicsDrawable;
import com.mikepenz.materialdrawer.util.AbstractDrawerImageLoader;
import com.mikepenz.materialdrawer.util.DrawerImageLoader;
import com.mikepenz.materialdrawer.util.DrawerUIUtils;
import com.webmyne.connect.R;
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




    //initialize and create the image loader logic
    DrawerImageLoader.init(new

    AbstractDrawerImageLoader() {
        @Override
        public void set (ImageView imageView, Uri uri, Drawable placeholder){
            Glide.with(imageView.getContext()).load(uri).placeholder(placeholder).into(imageView);
        }

        @Override
        public void cancel (ImageView imageView){
            Glide.clear(imageView);
        }

        @Override
        public Drawable placeholder (Context ctx, String tag){
            //define different placeholders for different imageView targets
            //default tags are accessible via the DrawerImageLoader.Tags
            //custom ones can be checked via string. see the CustomUrlBasePrimaryDrawerItem LINE 111
            if (DrawerImageLoader.Tags.PROFILE.name().equals(tag)) {
                return DrawerUIUtils.getPlaceHolder(ctx);
            } else if (DrawerImageLoader.Tags.ACCOUNT_HEADER.name().equals(tag)) {
                return new IconicsDrawable(ctx).iconText(" ").backgroundColorRes(com.mikepenz.materialdrawer.R.color.primary).sizeDp(56);
            } else if ("customUrlItem".equals(tag)) {
                return new IconicsDrawable(ctx).iconText(" ").backgroundColorRes(R.color.md_red_500).sizeDp(56);
            }

            //we use the default one for
            //DrawerImageLoader.Tags.PROFILE_DRAWER_ITEM.name()

            return super.placeholder(ctx, tag);
        }
    }

    );


}

    //LOaded

    public static synchronized MyApplication getInstance() {
        return sInstance;
    }
}
