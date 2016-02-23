package com.webmyne.connect.revenuePayment;

import android.animation.Animator;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.InputFilter;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.andexert.library.RippleView;
import com.webmyne.connect.R;
import com.webmyne.connect.Utils.Constants;
import com.webmyne.connect.Utils.Functions;

/**
 * Created by priyasindkar on 17-02-2016.
 */
public class RedeemMoneyActivity extends AppCompatActivity implements RippleView.OnRippleCompleteListener {

    private Toolbar toolbar;
    private FloatingActionButton fab;
    private LinearLayout linear1row, linear2row, linear3row, linear4row;
    private TextView txtAmount,txtDollar,txtButtonRedeem;
    private StringBuilder AMOUNT = new StringBuilder();
    private RippleView viewAddBankRipple, viewRedeemRipple;

    private int maxLengthofAMOUNT = 6;
    private int maxLengthofAfterDecimal = 2;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_redeem_money);

        initToolbar();
        init();

    }

    private void init() {

        fab = (FloatingActionButton) findViewById(R.id.fab);
        linear1row = (LinearLayout) findViewById(R.id.linear1row);
        linear2row = (LinearLayout) findViewById(R.id.linear2row);
        linear3row = (LinearLayout) findViewById(R.id.linear3row);
        linear4row = (LinearLayout) findViewById(R.id.linear4row);
        txtAmount = (TextView) findViewById(R.id.txtAmount);
        txtDollar= (TextView) findViewById(R.id.txtAmount);
        txtButtonRedeem = (TextView) findViewById(R.id.txtButtonRedeem);
        viewAddBankRipple = (RippleView) findViewById(R.id.viewAddBankRipple);
        viewAddBankRipple.setOnRippleCompleteListener(this);
        viewRedeemRipple = (RippleView) findViewById(R.id.viewRedeemRipple);
        viewRedeemRipple.setOnRippleCompleteListener(this);

        setFirstCellData();
        setSecondCellData();
        setThirdCellData();
        setFourthCellData();
    }

    private void setFirstCellData() {

        LinearLayout layout1 = (LinearLayout) linear1row.findViewById(R.id.layout1);
        TextView txtItem1 = (TextView) layout1.findViewById(R.id.txtItem);
        txtItem1.setText("1");
        txtItem1.setTypeface(Functions.getTypeFace(RedeemMoneyActivity.this));
        txtItem1.setOnClickListener(cellItemClickListener);

        LinearLayout layout2 = (LinearLayout) linear1row.findViewById(R.id.layout2);
        TextView txtItem2 = (TextView) layout2.findViewById(R.id.txtItem);
        txtItem2.setText("2");
        txtItem2.setTypeface(Functions.getTypeFace(RedeemMoneyActivity.this));
        txtItem2.setOnClickListener(cellItemClickListener);

        LinearLayout layout3 = (LinearLayout) linear1row.findViewById(R.id.layout3);
        TextView txtItem3 = (TextView) layout3.findViewById(R.id.txtItem);
        txtItem3.setText("3");
        txtItem3.setTypeface(Functions.getTypeFace(RedeemMoneyActivity.this));
        txtItem3.setOnClickListener(cellItemClickListener);

        animateCellItem(txtItem1,txtItem2,txtItem3);
    }

    private void setSecondCellData() {

        LinearLayout layout4 = (LinearLayout) linear2row.findViewById(R.id.layout4);
        TextView txtItem1 = (TextView) layout4.findViewById(R.id.txtItem);
        txtItem1.setText("4");
        txtItem1.setTypeface(Functions.getTypeFace(RedeemMoneyActivity.this));
        txtItem1.setOnClickListener(cellItemClickListener);

        LinearLayout layout5 = (LinearLayout) linear2row.findViewById(R.id.layout5);
        TextView txtItem2 = (TextView) layout5.findViewById(R.id.txtItem);
        txtItem2.setText("5");
        txtItem2.setTypeface(Functions.getTypeFace(RedeemMoneyActivity.this));
        txtItem2.setOnClickListener(cellItemClickListener);

        LinearLayout layout6 = (LinearLayout) linear2row.findViewById(R.id.layout6);
        TextView txtItem3 = (TextView) layout6.findViewById(R.id.txtItem);
        txtItem3.setText("6");
        txtItem3.setTypeface(Functions.getTypeFace(RedeemMoneyActivity.this));
        txtItem3.setOnClickListener(cellItemClickListener);

        animateCellItem(txtItem1,txtItem2,txtItem3);

    }

    private void setThirdCellData() {

        LinearLayout layout7 = (LinearLayout) linear3row.findViewById(R.id.layout7);
        TextView txtItem1 = (TextView) layout7.findViewById(R.id.txtItem);
        txtItem1.setText("7");
        txtItem1.setTypeface(Functions.getTypeFace(RedeemMoneyActivity.this));
        txtItem1.setOnClickListener(cellItemClickListener);

        LinearLayout layout8 = (LinearLayout) linear3row.findViewById(R.id.layout8);
        TextView txtItem2 = (TextView) layout8.findViewById(R.id.txtItem);
        txtItem2.setText("8");
        txtItem2.setTypeface(Functions.getTypeFace(RedeemMoneyActivity.this));
        txtItem2.setOnClickListener(cellItemClickListener);

        LinearLayout layout9 = (LinearLayout) linear3row.findViewById(R.id.layout9);
        TextView txtItem3 = (TextView) layout9.findViewById(R.id.txtItem);
        txtItem3.setText("9");
        txtItem3.setTypeface(Functions.getTypeFace(RedeemMoneyActivity.this));
        txtItem3.setOnClickListener(cellItemClickListener);

        animateCellItem(txtItem1,txtItem2,txtItem3);
    }

    private void setFourthCellData() {

        LinearLayout layout10 = (LinearLayout) linear4row.findViewById(R.id.layout10);
        TextView txtItem1 = (TextView) layout10.findViewById(R.id.txtItem);
        txtItem1.setText(".");
        txtItem1.setTypeface(Functions.getTypeFace(RedeemMoneyActivity.this),Typeface.BOLD);
        txtItem1.setOnClickListener(cellItemClickListener);

        LinearLayout layout11 = (LinearLayout) linear4row.findViewById(R.id.layout11);
        TextView txtItem2 = (TextView) layout11.findViewById(R.id.txtItem);
        txtItem2.setText("0");
        txtItem2.setTypeface(Functions.getTypeFace(RedeemMoneyActivity.this));
        txtItem2.setOnClickListener(cellItemClickListener);

        LinearLayout layout12 = (LinearLayout) linear4row.findViewById(R.id.layout12);
        TextView txtItem3 = (TextView) layout12.findViewById(R.id.txtItem);
        txtItem3.setText("Del");
        txtItem3.setTypeface(Functions.getTypeFace(RedeemMoneyActivity.this),Typeface.BOLD);
        txtItem3.setOnClickListener(cellItemClickListener);
        txtItem3.setOnLongClickListener(deleteItemLongClickListener);

        animateCellItem(txtItem1,txtItem2,txtItem3);
    }


    private void animateCellItem(TextView textView1,TextView textView2,TextView textView3){
        //Left to right animation
        Animator AIanim1 = ObjectAnimator.ofFloat(textView1, "translationY",200,0);
        Animator AIanim2 = ObjectAnimator.ofFloat(textView2, "translationY",200,0);
        Animator AIanim3 = ObjectAnimator.ofFloat(textView3, "translationY",200,0);

        AIanim1.setDuration(Constants.KEYBOARD_CELL_ITEM_ANIMATION_DURATION);
        AIanim2.setDuration(Constants.KEYBOARD_CELL_ITEM_ANIMATION_DURATION);
        AIanim3.setDuration(Constants.KEYBOARD_CELL_ITEM_ANIMATION_DURATION);
        AnimatorSet animatorSetTopRow = new AnimatorSet();
        animatorSetTopRow.playTogether(AIanim1,AIanim2,AIanim3);
        animatorSetTopRow.start();
    }

    View.OnLongClickListener deleteItemLongClickListener = new View.OnLongClickListener(){

        @Override
        public boolean onLongClick(View v) {
            AMOUNT = new StringBuilder();
            changeAmount(1,maxLengthofAMOUNT);
            return false;
        }
    };

    View.OnClickListener cellItemClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            TextView txt = (TextView)v;
            if(txt.getText().toString().equalsIgnoreCase(".")){
                //append dot only if there is no dot
                if(!AMOUNT.toString().contains(".")) {
                    AMOUNT.append(txt.getText().toString());
                    //if dot at first place
                    if(AMOUNT.toString().startsWith(".")) {
                        AMOUNT = new StringBuilder();
                        AMOUNT.append("0.");
                    }

                    if(AMOUNT.toString().contains(".")) {
                        String decimalBeforeText = AMOUNT.substring(0, AMOUNT.indexOf("."));
                        changeAmount(0,(decimalBeforeText.length() + maxLengthofAfterDecimal + 1));
                    }
                }


            }else if(txt.getText().toString().equalsIgnoreCase("Del")){

                if(AMOUNT.length()>1) {
                    AMOUNT.deleteCharAt(AMOUNT.length() - 1);

                    if(AMOUNT.toString().contains(".")) {
                        String decimalBeforeText = AMOUNT.substring(0, AMOUNT.indexOf("."));
                        changeAmount(2,(decimalBeforeText.length() + maxLengthofAfterDecimal + 1));
                    }else changeAmount(2,maxLengthofAMOUNT);

                }else{
                    AMOUNT =  new StringBuilder();
                    txtAmount.setText("0");
                    txtAmount.setTextSize(48);
                    txtAmount.setSingleLine();
                    txtAmount.setTextColor(getResources().getColor(R.color.textColor));
                }
            }else{
                AMOUNT.append(txt.getText().toString());

                if(AMOUNT.toString().contains(".")) {
                    String decimalBeforeText = AMOUNT.substring(0, AMOUNT.indexOf("."));
                    changeAmount(0,(decimalBeforeText.length() + maxLengthofAfterDecimal + 1));
                }else changeAmount(0,maxLengthofAMOUNT);
            }

        }
    };

    //0 - For enter new number
    //1 - For Clear all
    //2 - For Delete
    private void changeAmount(int operation,int maxLength){
      /*  if(AMOUNT.toString().contains(".")){
            String decimalBeforeText = AMOUNT.substring(0,AMOUNT.indexOf("."));
            txtAmount.setFilters(new InputFilter[] {new InputFilter.LengthFilter(decimalBeforeText.length()+maxLengthofAfterDecimal+1)});
        }else{
            txtAmount.setFilters(new InputFilter[] {new InputFilter.LengthFilter(maxLengthofAMOUNT)});
        }
*/
        txtAmount.setFilters(new InputFilter[] {new InputFilter.LengthFilter(maxLength)});
        if(AMOUNT.toString().length()>maxLength){
            String org = AMOUNT.toString();
            String temp = AMOUNT.toString();
            AMOUNT = new StringBuilder();
            AMOUNT.append(org.substring(0,maxLength));
           //txtAmount.setText(org.substring(maxLength,temp.length()));
        }

        switch (operation){
            case 0:
                txtAmount.setText(AMOUNT);
                txtAmount.setTextSize(32);
                txtAmount.setSingleLine();
                txtAmount.setTextColor(getResources().getColor(R.color.flatGreenColor));
                break;
            case 1:
                txtAmount.setText("0");
                txtAmount.setTextSize(48);
                txtAmount.setSingleLine();
                txtAmount.setTextColor(getResources().getColor(R.color.textColor));
                break;
            case 2:
                txtAmount.setText(AMOUNT);
                txtAmount.setTextSize(32);
                txtAmount.setSingleLine();
                txtAmount.setTextColor(getResources().getColor(R.color.flatGreenColor));
                break;
        }

    }

    private void initToolbar() {
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        // setSupportActionBar(toolbar);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });


    }

    @Override
    public void onComplete(RippleView rippleView) {
        switch (rippleView.getId()) {
            case R.id.viewAddBankRipple:
                Intent intent = new Intent(RedeemMoneyActivity.this, AddBankActivity.class);
                startActivity(intent);
                overridePendingTransition(R.anim.push_down_in, R.anim.push_up_out);
                break;
            case R.id.viewRedeemRipple:
                AlertDialog.Builder dialog = Functions.getSimpleOkAlterDialog(RedeemMoneyActivity.this, "Redeem Successful!", "Ok");
                dialog.show();
                break;
        }
    }
}
