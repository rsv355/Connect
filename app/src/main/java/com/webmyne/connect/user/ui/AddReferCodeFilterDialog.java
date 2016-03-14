package com.webmyne.connect.user.ui;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.AppCompatDialog;
import android.support.v7.widget.AppCompatButton;
import android.view.View;
import android.widget.Toast;

import com.jungly.gridpasswordview.GridPasswordView;
import com.jungly.gridpasswordview.PasswordType;
import com.webmyne.connect.R;
import com.webmyne.connect.Utils.Functions;
import com.webmyne.connect.user.presenter.EditProfileView;

/**
 * Created by priyasindkar on 16-02-2016.
 */
public class AddReferCodeFilterDialog extends AppCompatDialog implements View.OnClickListener {
    private AppCompatButton btnCancel, btnReferCode;
    private GridPasswordView edtReferCode;
    private Context mContext;
    private EditProfileView editProfileView;
    private AddReferCodePresenter presenter;

    public AddReferCodeFilterDialog(Context context, EditProfileView editProfileView) {
        super(context);
        mContext = context;
        this.editProfileView = editProfileView;
    }

    public AddReferCodeFilterDialog(Context context, EditProfileView editProfileView, int themeResId) {
        super(context, themeResId);
        mContext = context;
        this.editProfileView = editProfileView;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.dialog_add_refer_code);
        setTitle(mContext.getResources().getString(R.string.add_refer_code));
        btnCancel = (AppCompatButton) findViewById(R.id.btnCancel);
        btnCancel.setOnClickListener(this);
        btnReferCode = (AppCompatButton) findViewById(R.id.btnReferCode);
        btnReferCode.setOnClickListener(this);
        edtReferCode = (GridPasswordView) findViewById(R.id.edtReferCode);
        edtReferCode.setPasswordVisibility(true);
        edtReferCode.setPasswordType(PasswordType.TEXTVISIBLE);

        presenter = new AddReferCodePresenterImpl();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btnReferCode:
                if (presenter.validatePassword(edtReferCode.getPassWord())) {
                    editProfileView.onReferCodeAdd(true, edtReferCode.getPassWord());
                } else {
                    Toast.makeText(mContext, "Refer Code Must Be 6 Characters.", Toast.LENGTH_SHORT).show();
                }
               /* presenter = null;
                dismiss();*/
                break;
            case R.id.btnCancel:
                editProfileView.onReferCodeAdd(false, "");
                presenter = null;
                dismiss();
                break;
        }
    }
}
