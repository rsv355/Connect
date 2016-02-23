package com.webmyne.connect.revenuePayment;

/**
 * Created by priyasindkar on 12-02-2016.
 */


import android.content.Context;
import android.graphics.Color;
import android.graphics.Typeface;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.webmyne.connect.R;
import com.webmyne.connect.Utils.AdvancedSpannableString;
import com.webmyne.connect.Utils.Functions;

import java.util.ArrayList;

public class PaymentHistoryListAdapter extends RecyclerView.Adapter<PaymentHistoryListAdapter.DataObjectHolder> {
    private static String LOG_TAG = "PaymentHistoryListAdapter";
    private ArrayList<PaymentHistoryDataObject> mDataset;
    private static MyClickListener myClickListener;
    private Context mContext;



    public static class DataObjectHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        public TextView txtLeadId, txtDateTime, txtCustomerName, txtRedeemAmount, txtAmount;

        public DataObjectHolder(View itemView) {
            super(itemView);
            txtLeadId = (TextView) itemView.findViewById(R.id.txtLeadId);
            txtDateTime = (TextView) itemView.findViewById(R.id.txtDateTime);
            //labelStatus = (LabelView) itemView.findViewById(R.id.labelStatus);
            txtCustomerName = (TextView) itemView.findViewById(R.id.txtCustomerName);
            txtRedeemAmount =(TextView) itemView.findViewById(R.id.txtRedeemAmount);
            txtAmount = (TextView) itemView .findViewById(R.id.txtAmount);
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

    public PaymentHistoryListAdapter(Context _context) {
        mContext = _context;
        mDataset = new ArrayList<>();
    }

    public PaymentHistoryListAdapter(Context _context, ArrayList<PaymentHistoryDataObject> myDataset) {
        mContext = _context;
        mDataset = myDataset;
    }

    @Override
    public DataObjectHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater mInflater = LayoutInflater.from (parent.getContext());
        ViewGroup viewgroup1 = ( ViewGroup ) mInflater.inflate ( R.layout.payment_history_item, parent, false );
        DataObjectHolder listHolder = new DataObjectHolder (viewgroup1);
        return listHolder;
    }


    @Override
    public void onBindViewHolder(DataObjectHolder holder, int position) {
        int color =  mDataset.get(position).getColor();
        holder.txtLeadId.setTypeface(Functions.getTypeFace(mContext), Typeface.BOLD);
        holder.txtDateTime.setTypeface(Functions.getTypeFace(mContext));
        holder.txtCustomerName.setTypeface(Functions.getTypeFace(mContext));
        holder.txtRedeemAmount.setTypeface(Functions.getTypeFace(mContext), Typeface.BOLD);
        holder.txtAmount.setTypeface(Functions.getTypeFace(mContext), Typeface.BOLD);


        holder.txtLeadId.setText(mDataset.get(position).getLeadName());
        holder.txtLeadId.setTextColor(color);
        holder.txtCustomerName.setText(mDataset.get(position).getCustomerName());
        holder.txtDateTime.setText(mDataset.get(position).getDateTime());
        holder.txtAmount.setText("$ "+String.valueOf(mDataset.get(position).getAmount()));
        holder.txtAmount.setBackgroundColor(color);
        AdvancedSpannableString sp = new AdvancedSpannableString("Encashable Amount  $ "+String.valueOf(mDataset.get(position).getRedeeemAmount()));
        sp.setColor(mContext.getResources().getColor(R.color.colorPrimary), "Encashable Amount");
        sp.setColor(mContext.getResources().getColor(R.color.flatGreenColor), "$ "+String.valueOf(mDataset.get(position).getRedeeemAmount()));
        holder.txtRedeemAmount.setText(sp);
        holder.txtDateTime.setTextColor(Color.parseColor("#494949"));
    }

    public void addItem(PaymentHistoryDataObject dataObj, int index) {
        mDataset.add(dataObj);
        notifyItemInserted(index);
    }

    public void deleteItem(int index) {
        mDataset.remove(index);
        notifyItemRemoved(index);
    }

    @Override
    public int getItemCount() {
        return mDataset.size();
    }

    public interface MyClickListener {
        public void onItemClick(int position, View v);
    }
}