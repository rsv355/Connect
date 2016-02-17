package com.webmyne.connect.revenue;

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

public class RevenueHistoryListAdapter extends RecyclerView.Adapter<RevenueHistoryListAdapter.DataObjectHolder> {
    private static String LOG_TAG = "RevenueHistoryListAdapter";
    private ArrayList<TransactionHistoryDataObject> mDataset;
    private static MyClickListener myClickListener;
    private Context mContext;



    public static class DataObjectHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        public TextView txtLeadId, txtDateTime, txtCustomerName, txtCustomerNo, txtAmount;
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
            txtCustomerNo =(TextView) itemView.findViewById(R.id.txtCustomerNo);
            txtAmount = (TextView) itemView .findViewById(R.id.txtCommissionAmount);
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

    public RevenueHistoryListAdapter(Context _context) {
        Log.e(LOG_TAG, _context.toString());
        mContext = _context;
        mDataset = new ArrayList<>();
    }

    public RevenueHistoryListAdapter(Context _context, ArrayList<TransactionHistoryDataObject> myDataset) {
        mContext = _context;
        mDataset = myDataset;
    }

    @Override
    public DataObjectHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater mInflater = LayoutInflater.from (parent.getContext());
        ViewGroup viewgroup1 = ( ViewGroup ) mInflater.inflate ( R.layout.transaction_history_item, parent, false );
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
        holder.txtAmount.setTypeface(Functions.getTypeFace(mContext), Typeface.BOLD);


        holder.txtLeadId.setText(mDataset.get(position).getLeadName());
        holder.txtLeadId.setTextColor(color);
        holder.txtCustomerName.setText(mDataset.get(position).getCustomerName());
        holder.txtDateTime.setText(mDataset.get(position).getDateTime());
        holder.txtAmount.setText("$ "+String.valueOf(mDataset.get(position).getAmount()));
        //holder.txtAmount.setTextColor(color);
        holder.cardAmount.setCardBackgroundColor(color);
        holder.txtDateTime.setTextColor(Color.parseColor("#494949"));
        holder.labelStatus.setNum(mDataset.get(position).getStatus());
        String status = mDataset.get(position).getStatus();

        if(status.equals("ACTIVE")){
            holder.labelStatus.setBackGroundColor(mContext.getResources().getColor(R.color.primaryBackground));
        } else  if(status.equals("ONGOING")){
            holder.labelStatus.setBackGroundColor(mContext.getResources().getColor(R.color.accent_A200));
        } else  if(status.equals("DEACTIVE")){
            holder.labelStatus.setBackGroundColor(mContext.getResources().getColor(R.color.theme_500));
        } else {
            holder.labelStatus.setBackGroundColor(mContext.getResources().getColor(R.color.primaryBackground));
        }


       /* int dimensions = (int) mContext.getResources().getDimension(R.dimen.normal_text_size);
        TextDrawable drawable2 = TextDrawable.builder().buildRound("$" + String.valueOf(mDataset.get(position).getAmount()),color );
        holder.image.setImageDrawable(drawable2);*/
    }

    public void addItem(TransactionHistoryDataObject dataObj, int index) {
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