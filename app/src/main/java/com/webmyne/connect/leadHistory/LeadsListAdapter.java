package com.webmyne.connect.leadHistory;

/**
 * Created by priyasindkar on 12-02-2016.
 */



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



    public static class DataObjectHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        public TextView txtLeadId, txtDateTime, txtCustomerName, txtCustomerNo;
        public LabelView labelStatus;
        public ImageView image;
        private LinearLayout linearParent;

        public DataObjectHolder(View itemView) {
            super(itemView);
            txtLeadId = (TextView) itemView.findViewById(R.id.txtLeadId);
            txtDateTime = (TextView) itemView.findViewById(R.id.txtDateTime);
            labelStatus = (LabelView) itemView.findViewById(R.id.labelStatus);
            txtCustomerName = (TextView) itemView.findViewById(R.id.txtCustomerName);
            txtCustomerNo =(TextView) itemView.findViewById(R.id.txtRedeemAmount);
            linearParent = (LinearLayout) itemView.findViewById(R.id.linearParent);
            image = (ImageView) itemView.findViewById(R.id.imgLeadItem);
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
    public void onBindViewHolder(DataObjectHolder holder, int position) {
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
    }

    public void addItem(LeadDataObject dataObj, int index) {
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