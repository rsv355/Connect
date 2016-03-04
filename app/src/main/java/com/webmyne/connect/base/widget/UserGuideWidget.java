package com.webmyne.connect.base.widget;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.webmyne.connect.R;
import com.webmyne.connect.customUI.viewPager.DotsView;
import com.webmyne.connect.customUI.viewPager.SCViewPager;

/**
 * Created by priyasindkar on 04-03-2016.
 */
public class UserGuideWidget extends RelativeLayout {

    private Context mContext;
    private View view;
    private LayoutInflater mLayoutInflater;
    private TextView guideText1, guideText2, guideText3;
    private ImageView guideImage1, guideImage2, guideImage3;

    public UserGuideWidget(Context context) {
        super(context);
        init(context);
    }

    public UserGuideWidget(Context context, AttributeSet attrs, Context mContext) {
        super(context, attrs);
        this.mContext = mContext;
    }

    private void init(Context ctx) {
        View.inflate(ctx, R.layout.user_guide_widget, this);
        guideText1 = (TextView) findViewById(R.id.guideText1);
        guideText2 = (TextView) findViewById(R.id.guideText2);
        guideText3 = (TextView) findViewById(R.id.guideText3);

        guideImage1 = (ImageView) findViewById(R.id.guideImage1);
        guideImage2 = (ImageView) findViewById(R.id.guideImage2);
        guideImage3 = (ImageView) findViewById(R.id.guideImage3);
    }

}
