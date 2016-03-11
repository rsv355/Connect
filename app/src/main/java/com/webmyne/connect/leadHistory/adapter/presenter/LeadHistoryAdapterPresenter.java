package com.webmyne.connect.leadHistory.adapter.presenter;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.widget.ImageView;
import android.widget.TextView;

/**
 * Created by priyasindkar on 11-03-2016.
 */
public interface LeadHistoryAdapterPresenter {
    public Drawable expand(final TextView text, final ImageView imgArrow);
    public Drawable collapse(final TextView text, final ImageView imgArrow);
}
