package com.webmyne.connect.commission;

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
import com.webmyne.connect.Utils.Functions;
import com.webmyne.connect.base.LabelView;

import java.util.ArrayList;

public class CommissionHistoryListAdapter extends RecyclerView.Adapter<CommissionHistoryListAdapter.DataObjectHolder> {
    private static String LOG_TAG = "CommissionHistoryListAdapter";
    private ArrayList<CommissionHistoryDataObject> mDataset;
    private static MyClickListener myClickListener;
    private Context mContext;



    public static class DataObjectHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        public TextView txtLeadId, txtDateTime, txtCustomerName, txtCustomerNo, txtCommissionAmount, txtBoughtAmount;
        public LabelView labelStatus;
        //public ImageView image;
        private CardView cardAmount;
        private LinearLayout linearParent;

        public DataObjectHolder(View itemView) {
            super(itemView);
            txtLeadId = (TextView) itemView.findViewById(R.id.txtLeadId);
            txtDateTime = (TextView) itemView.findViewById(R.id.txtDateTime);
            labelStatus = (LabelView) itemView.findViewById(R.id.labelStatus);
            txtCustomerName = (TextView) itemView.findViewById(R.id.txtCustomerName);
            txtCustomerNo =(TextView) itemView.findViewById(R.id.txtRedeemAmount);
            txtCommissionAmount = (TextView) itemView .findViewById(R.id.txtCommissionAmount);
            txtBoughtAmount = (TextView) itemView .findViewById(R.id.txtBoughtAmount);
            cardAmount = (CardView) itemView.findViewById(R.id.cardAmount);
            linearParent = (LinearLayout) itemView.findViewById(R.id.linearParent);
            //image = (ImageView) itemView.findViewById(R.id.imgLeadItem);
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

    public CommissionHistoryListAdapter(Context _context) {
        Log.e(LOG_TAG, _context.toString());
        mContext = _context;
        mDataset = new ArrayList<>();
    }

    public CommissionHistoryListAdapter(Context _context, ArrayList<CommissionHistoryDataObject> myDataset) {
        mContext = _context;
        mDataset = myDataset;
    }

    @Override
    public DataObjectHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater mInflater = LayoutInflater.from (parent.getContext());
        ViewGroup viewgroup1 = ( ViewGroup ) mInflater.inflate ( R.layout.commission_history_item, parent, false );
        DataObjectHolder listHolder = new DataObjectHolder (viewgroup1);
        return listHolder;
    }


    @Override
    public void onBindViewHolder(DataObjectHolder holder, int position) {
        int color =  mDataset.get(position).getColor();
        holder.txtLeadId.setTypeface(Functions.getTypeFace(mContext), Typeface.BOLD);
        holder.txtDateTime.setTypeface(Functions.getTypeFace(mContext));
        holder.txtCustomerName.setTypeface(Functions.getTypeFace(mContext));
        holder.txtCustomerNo.setTypeface(Functions.getTypeFace(mContext));
        holder.txtCommissionAmount.setTypeface(Functions.getTypeFace(mContext), Typeface.BOLD);
        holder.txtBoughtAmount.setTypeface(Functions.getTypeFace(mContext),Typeface.BOLD );


        holder.txtLeadId.setText(mDataset.get(position).getLeadName());
        holder.txtLeadId.setTextColor(color);
        holder.txtCustomerName.setText(mDataset.get(position).getCustomerName());
        holder.txtDateTime.setText(mDataset.get(position).getDateTime());
        holder.txtCommissionAmount.setText("$ "+String.valueOf(mDataset.get(position).getCommissionAmount()));
        holder.txtCommissionAmount.setBackgroundColor(color);
        holder.txtBoughtAmount.setText("Lead Sold: $ "+String.valueOf(mDataset.get(position).getBoughtAmount()));
        //holder.cardAmount.setCardBackgroundColor(color);
        holder.txtDateTime.setTextColor(Color.parseColor("#494949"));
    }

    public void addItem(CommissionHistoryDataObject dataObj, int index) {
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