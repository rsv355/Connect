package com.webmyne.connect.leadHistory.adapter;

/**
 * Created by priyasindkar on 12-02-2016.
 */



import android.animation.Animator;
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

import java.util.ArrayList;

import com.webmyne.connect.base.LabelView;
import com.webmyne.connect.R;
import com.webmyne.connect.Utils.Functions;
import com.webmyne.connect.customUI.textDrawableIcons.TextDrawable;
import com.webmyne.connect.leadHistory.model.LeadDataObject;

public class LeadsListAdapter extends RecyclerView.Adapter<LeadsListAdapter.DataObjectHolder> {
    private static String LOG_TAG = "LeadsListAdapter";
    private ArrayList<LeadDataObject> mDataset;
    private static MyClickListener myClickListener;
    private Context mContext;
    boolean isExpanded = false;


    public static class DataObjectHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        public TextView txtLeadId, txtDateTime, txtCustomerName, txtCustomerNo,txtDescription;
        public LabelView labelStatus;
        public ImageView image,imgArrow;
        private LinearLayout linearParent;

        public DataObjectHolder(View itemView) {
            super(itemView);
            txtDescription = (TextView) itemView.findViewById(R.id.txtDescription);
            txtLeadId = (TextView) itemView.findViewById(R.id.txtLeadId);
            txtDateTime = (TextView) itemView.findViewById(R.id.txtDateTime);
            labelStatus = (LabelView) itemView.findViewById(R.id.labelStatus);
            txtCustomerName = (TextView) itemView.findViewById(R.id.txtCustomerName);
            txtCustomerNo =(TextView) itemView.findViewById(R.id.txtRedeemAmount);
            linearParent = (LinearLayout) itemView.findViewById(R.id.linearParent);
            image = (ImageView) itemView.findViewById(R.id.imgLeadItem);
            imgArrow = (ImageView) itemView.findViewById(R.id.imgArrow);
            Log.e(LOG_TAG, "Adding Listener");
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            myClickListener.onItemClick(getPosition(), v);
        }
    }

    public void setOnItemClickListener(MyClickListener myClickListener) {
        this.myClickListener = myClickListener;
    }

    public LeadsListAdapter(Context _context) {
        Log.e(LOG_TAG, _context.toString());
        mContext = _context;
        mDataset = new ArrayList<>();
    }

    public LeadsListAdapter(Context _context, ArrayList<LeadDataObject> myDataset) {
        mContext = _context;
        mDataset = myDataset;
    }

    @Override
    public DataObjectHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater mInflater = LayoutInflater.from (parent.getContext());
        ViewGroup viewgroup1 = ( ViewGroup ) mInflater.inflate ( R.layout.lead_item, parent, false );
        DataObjectHolder listHolder = new DataObjectHolder (viewgroup1);
        return listHolder;
    }

    @Override
    public void onBindViewHolder(final DataObjectHolder holder, int position) {
        holder.txtLeadId.setTypeface(Functions.getTypeFace(mContext), Typeface.BOLD);
        holder.txtDateTime.setTypeface(Functions.getTypeFace(mContext));
        holder.txtCustomerName.setTypeface(Functions.getTypeFace(mContext));
        holder.txtCustomerNo.setTypeface(Functions.getTypeFace(mContext));

        holder.txtLeadId.setText(mDataset.get(position).getLeadName());
        holder.txtLeadId.setTextColor(mDataset.get(position).getColor());
        holder.txtCustomerName.setText(mDataset.get(position).getCustomerName());
        holder.txtDateTime.setText(mDataset.get(position).getDateTime());
        holder.txtDateTime.setTextColor(Color.parseColor("#494949"));
        holder.labelStatus.setNum(mDataset.get(position).getStatus());
        String status = mDataset.get(position).getStatus();

        if(status.equals("ACTIVE")){
            holder.labelStatus.setBackGroundColor(mContext.getResources().getColor(R.color.primaryTextColor));
        } else  if(status.equals("ONGOING")){
            holder.labelStatus.setBackGroundColor(mContext.getResources().getColor(R.color.accent_A200));
        } else  if(status.equals("DEACTIVE")){
            holder.labelStatus.setBackGroundColor(mContext.getResources().getColor(R.color.theme_500));
        } else {
            holder.labelStatus.setBackGroundColor(mContext.getResources().getColor(R.color.primaryTextColor));
        }

        TextDrawable drawable2 = TextDrawable.builder().buildRound(mDataset.get(position).getVerticalName(), mDataset.get(position).getColor());
        holder.image.setImageDrawable(drawable2);

        holder.imgArrow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                 if(!isExpanded) {
                                    expand( holder.txtDescription);
                                } else {
                                    collapse(holder.txtDescription);
                                }
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

    private void expand(final TextView text) {
        //set Visible
        text.setVisibility(View.VISIBLE);

        final int widthSpec = View.MeasureSpec.makeMeasureSpec(0, View.MeasureSpec.UNSPECIFIED);
        final int heightSpec = View.MeasureSpec.makeMeasureSpec(0, View.MeasureSpec.UNSPECIFIED);
        text.measure(widthSpec, heightSpec);
        ValueAnimator mAnimator = slideAnimator(text,0, text.getMeasuredHeight());
        mAnimator.start();
    }

    private void collapse(final TextView text) {
        int finalHeight = text.getHeight();
        ValueAnimator mAnimator = slideAnimator(text,finalHeight, 0);
        mAnimator.addListener(new Animator.AnimatorListener() {
            @Override
            public void onAnimationStart(Animator animation) {

            }

            @Override
            public void onAnimationEnd(Animator animator) {
                //Height=0, but it set visibility to GONE
                text.setVisibility(View.GONE);
            }

            @Override
            public void onAnimationCancel(Animator animation) {

            }

            @Override
            public void onAnimationRepeat(Animator animation) {

            }
        });
        mAnimator.start();
    }

    private ValueAnimator slideAnimator(final TextView text,int start, int end) {
        ValueAnimator animator = ValueAnimator.ofInt(start, end);
        animator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator valueAnimator) {
                //Update Height
                int value = (Integer) valueAnimator.getAnimatedValue();
                ViewGroup.LayoutParams layoutParams = text.getLayoutParams();
                layoutParams.height = value;
                text.setLayoutParams(layoutParams);
            }
        });
        return animator;
    }

    //end of main class
}