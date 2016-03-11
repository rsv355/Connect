package com.webmyne.connect.leadHistory.adapter;

/**
 * Created by priyasindkar on 12-02-2016.
 */

import android.animation.ValueAnimator;
import android.content.Context;
import android.graphics.Color;
import android.graphics.Typeface;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.webmyne.connect.R;
import com.webmyne.connect.Utils.Functions;
import com.webmyne.connect.base.LabelView;
import com.webmyne.connect.customUI.textDrawableIcons.TextDrawable;
import com.webmyne.connect.leadHistory.adapter.presenter.LeadHistoryAdapterPresenter;
import com.webmyne.connect.leadHistory.adapter.presenter.LeadHistoryAdapterPresenterImpl;
import com.webmyne.connect.leadHistory.model.LeadDataObject;
import com.webmyne.connect.leadHistory.model.LeadStatuses;

import java.util.ArrayList;

public class LeadsListAdapter extends RecyclerView.Adapter<LeadsListAdapter.DataObjectHolder> {
    private static String LOG_TAG = "LeadsListAdapter";
    private ArrayList<LeadDataObject> mDataset;
    private static MyClickListener myClickListener;
    private Context mContext;
    private LeadHistoryAdapterPresenter leadHistoryAdapterPresenter;

    public static class DataObjectHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        public TextView txtLeadId, txtDateTime, txtCustomerName, txtCustomerNo, txtDescription;
        public LabelView labelStatus;
        public ImageView image, imgArrow;
        private LinearLayout linearParent;

        public DataObjectHolder(View itemView) {
            super(itemView);
            txtDescription = (TextView) itemView.findViewById(R.id.txtDescription);
            txtLeadId = (TextView) itemView.findViewById(R.id.txtLeadId);
            txtDateTime = (TextView) itemView.findViewById(R.id.txtDateTime);
            labelStatus = (LabelView) itemView.findViewById(R.id.labelStatus);
            txtCustomerName = (TextView) itemView.findViewById(R.id.txtCustomerName);
            txtCustomerNo = (TextView) itemView.findViewById(R.id.txtRedeemAmount);
            linearParent = (LinearLayout) itemView.findViewById(R.id.linearParent);
            image = (ImageView) itemView.findViewById(R.id.imgLeadItem);
            imgArrow = (ImageView) itemView.findViewById(R.id.imgArrow);
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            //myClickListener.onItemClick(getPosition(), v);
        }
    }

    public void setOnItemClickListener(MyClickListener myClickListener) {
        this.myClickListener = myClickListener;
    }

    public LeadsListAdapter(Context _context) {
        Log.e(LOG_TAG, _context.toString());
        mContext = _context;
        mDataset = new ArrayList<>();
        leadHistoryAdapterPresenter = new LeadHistoryAdapterPresenterImpl(_context);
    }

    public LeadsListAdapter(Context _context, ArrayList<LeadDataObject> myDataset) {
        mContext = _context;
        mDataset = myDataset;
        leadHistoryAdapterPresenter = new LeadHistoryAdapterPresenterImpl(_context);
    }

    @Override
    public DataObjectHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater mInflater = LayoutInflater.from(parent.getContext());
        ViewGroup viewgroup1 = (ViewGroup) mInflater.inflate(R.layout.lead_item, parent, false);
        DataObjectHolder listHolder = new DataObjectHolder(viewgroup1);
        return listHolder;
    }

    @Override
    public void onBindViewHolder(final DataObjectHolder holder, final int position) {
        holder.txtLeadId.setTypeface(Functions.getTypeFace(mContext), Typeface.BOLD);
        holder.txtDateTime.setTypeface(Functions.getTypeFace(mContext));
        holder.txtCustomerName.setTypeface(Functions.getTypeFace(mContext));
        holder.txtCustomerNo.setTypeface(Functions.getTypeFace(mContext));

        holder.txtLeadId.setText(mDataset.get(position).getLeadName());
        holder.txtLeadId.setTextColor(mDataset.get(position).getColor());
        holder.txtCustomerName.setText(mDataset.get(position).getCustomerName());
        holder.txtDateTime.setText(mDataset.get(position).getDateTime());
        holder.txtDateTime.setTextColor(Color.parseColor("#494949"));
        holder.labelStatus.setNum(LeadStatuses.getStatusMSG(Integer.parseInt(mDataset.get(position).getStatus())));
        holder.labelStatus.setBackGroundColor(LeadStatuses.getStatusColor(Integer.parseInt(mDataset.get(position).getStatus())));

       /* if (status.equals("ACTIVE")) {
            holder.labelStatus.setBackGroundColor(mContext.getResources().getColor(R.color.primaryTextColor));
        } else if (status.equals("ONGOING")) {
            holder.labelStatus.setBackGroundColor(mContext.getResources().getColor(R.color.accent_A200));
        } else if (status.equals("DEACTIVE")) {
            holder.labelStatus.setBackGroundColor(mContext.getResources().getColor(R.color.theme_500));
        } else {
            holder.labelStatus.setBackGroundColor(mContext.getResources().getColor(R.color.primaryTextColor));
        }*/

        TextDrawable drawable2 = TextDrawable.builder().buildRound(mDataset.get(position).getVerticalName(), mDataset.get(position).getColor());
        holder.image.setImageDrawable(drawable2);

        holder.imgArrow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!mDataset.get(position).isExpanded()) {
                    expand(holder.txtDescription, holder.imgArrow);
                } else {
                    collapse(holder.txtDescription, holder.imgArrow);
                }
                mDataset.get(position).setExpanded(!mDataset.get(position).isExpanded());
            }
        });
    }


    @Override
    public int getItemCount() {
        return mDataset.size();
    }

    public interface MyClickListener {
        public void onItemClick(int position, View v);
    }

    private void expand(final TextView text, final ImageView imgArrow) {
        //set Visible
        /*text.setVisibility(View.VISIBLE);
        text.setAnimation(AnimationUtils.loadAnimation(mContext, android.R.anim.fade_in));
        final int widthSpec = View.MeasureSpec.makeMeasureSpec(0, View.MeasureSpec.UNSPECIFIED);
        final int heightSpec = View.MeasureSpec.makeMeasureSpec(0, View.MeasureSpec.UNSPECIFIED);
        text.measure(widthSpec, heightSpec);
        ValueAnimator mAnimator = slideAnimator(text, 0, text.getMeasuredHeight());
      //  mAnimator.setDuration(2000);
        Drawable drawable = mContext.getResources().getDrawable(R.drawable.ic_arrow_up);
        drawable.setColorFilter(mContext.getResources().getColor(R.color.walletDividerLine), PorterDuff.Mode.SRC_ATOP);
        imgArrow.setImageDrawable(drawable);
        mAnimator.start();*/
        imgArrow.setImageDrawable(leadHistoryAdapterPresenter.expand(text, imgArrow));
    }

    private void collapse(final TextView text, final ImageView imgArrow) {
        imgArrow.setImageDrawable(leadHistoryAdapterPresenter.collapse(text, imgArrow));
    }

    private ValueAnimator slideAnimator(final TextView text, final int start, int end) {
        ValueAnimator animator = ValueAnimator.ofInt(start, end);
        animator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator valueAnimator) {
                //Update Height
                int value = (Integer) valueAnimator.getAnimatedValue();
                ViewGroup.LayoutParams layoutParams = text.getLayoutParams();
                layoutParams.height = 50;//value;
                text.setLayoutParams(layoutParams);
            }
        });
        return animator;
    }

    //end of main class
}