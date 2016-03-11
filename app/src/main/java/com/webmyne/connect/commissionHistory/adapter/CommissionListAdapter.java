package com.webmyne.connect.commissionHistory.adapter;

/**
 * Created by priyasindkar on 12-02-2016.
 */


import android.content.Context;
import android.graphics.Color;
import android.graphics.Typeface;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.webmyne.connect.R;
import com.webmyne.connect.Utils.AdvancedSpannableString;
import com.webmyne.connect.Utils.Constants;
import com.webmyne.connect.Utils.Functions;
import com.webmyne.connect.commissionHistory.model.CommissionDataObject;

import java.util.ArrayList;

public class CommissionListAdapter extends RecyclerView.Adapter<CommissionListAdapter.DataObjectHolder> {
    private static String LOG_TAG = "CommissionListAdapter";
    private ArrayList<CommissionDataObject> mDataset;
    private static MyClickListener myClickListener;
    private Context mContext;


    public static class DataObjectHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        public TextView txtLeadId, txtDateTime, txtCustomerName, txtMobile, txtCommissionAmount, txtBoughtAmount;
        private LinearLayout linearParent;
        private CardView cardCommission;

        public DataObjectHolder(View itemView) {
            super(itemView);
            txtLeadId = (TextView) itemView.findViewById(R.id.txtLeadId);
            txtDateTime = (TextView) itemView.findViewById(R.id.txtDateTime);
            txtCustomerName = (TextView) itemView.findViewById(R.id.txtCustomerName);
            txtMobile = (TextView) itemView.findViewById(R.id.txtMobile);
            txtCommissionAmount = (TextView) itemView.findViewById(R.id.txtCommissionAmount);
            txtBoughtAmount = (TextView) itemView.findViewById(R.id.txtBoughtAmount);
            linearParent = (LinearLayout) itemView.findViewById(R.id.linearParent);
            cardCommission = (CardView) itemView.findViewById(R.id.cardCommission);
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

    public CommissionListAdapter(Context _context) {
        Log.e(LOG_TAG, _context.toString());
        mContext = _context;
        mDataset = new ArrayList<>();
    }

    public CommissionListAdapter(Context _context, ArrayList<CommissionDataObject> myDataset) {
        mContext = _context;
        mDataset = myDataset;
    }

    @Override
    public DataObjectHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater mInflater = LayoutInflater.from(parent.getContext());
        ViewGroup viewgroup1 = (ViewGroup) mInflater.inflate(R.layout.commission_history_item, parent, false);
        DataObjectHolder listHolder = new DataObjectHolder(viewgroup1);
        return listHolder;
    }

    @Override
    public void onBindViewHolder(final DataObjectHolder holder, final int position) {
        int color = mDataset.get(position).getColor();
        holder.txtLeadId.setTypeface(Functions.getTypeFace(mContext), Typeface.BOLD);
        holder.txtDateTime.setTypeface(Functions.getTypeFace(mContext));
        holder.txtCustomerName.setTypeface(Functions.getTypeFace(mContext));
        holder.txtMobile.setTypeface(Functions.getTypeFace(mContext));
        holder.txtCommissionAmount.setTypeface(Functions.getTypeFace(mContext));
        holder.txtBoughtAmount.setTypeface(Functions.getTypeFace(mContext));
        holder.txtCommissionAmount.setBackgroundColor(color);

        holder.txtLeadId.setText(mDataset.get(position).getLeadName());
        holder.txtLeadId.setTextColor(color);
        holder.txtCustomerName.setText(mDataset.get(position).getCustomerName());
        holder.txtDateTime.setText(mDataset.get(position).getDateTime());
        holder.txtDateTime.setTextColor(Color.parseColor("#494949"));
        holder.txtBoughtAmount.setTextColor(color);
        holder.txtCommissionAmount.setText(Constants.DOLLAR_PREFIX + mDataset.get(position).getCommissionEarned());
        AdvancedSpannableString sp = new AdvancedSpannableString(Constants.SP_PREFIX + Constants.DOLLAR_PREFIX + mDataset.get(position).getSoldPrice());
        sp.setBold(Constants.SP_PREFIX + Constants.DOLLAR_PREFIX + mDataset.get(position).getSoldPrice());
        sp.setColor(mContext.getResources().getColor(R.color.colorPrimary), Constants.SP_PREFIX);
        sp.setColor(mContext.getResources().getColor(R.color.flatGreenColor),Constants.DOLLAR_PREFIX + mDataset.get(position).getSoldPrice());
        holder.txtBoughtAmount.setText(sp);

    }


    @Override
    public int getItemCount() {
        return mDataset.size();
    }

    public interface MyClickListener {
        public void onItemClick(int position, View v);
    }
    //end of main class
}