package Fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import FontComponents.MyButton;
import FontComponents.MyEditText;
import FontComponents.TextViewFont;
import Others.Globals;
import ir.andishehlab.asanfit.ActivityMain;
import ir.andishehlab.asanfit.R;

/**
 * Created by N550J on 12/1/2017.
 */
public class FragmentChangePassword extends Fragment {

    MyEditText edtCurrentPass, edtNewPass, edtRepeatNewPass;
    Globals globals;
    TextViewFont txtHeader;
    MyButton btnChangeOk;

    private static FragmentChangePassword instance;
    public FragmentChangePassword() {
        // Required empty public constructor
    }

    public static FragmentChangePassword getInstance()
    {
        if (instance == null)
            instance = new FragmentChangePassword();
        return instance;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_change_password, container, false);

        edtCurrentPass = view.findViewById(R.id.edt_change_pass_current_password);
        edtNewPass = view.findViewById(R.id.edt_change_pass_new_password);
        edtRepeatNewPass = view.findViewById(R.id.edt_change_pass_repeat_new_password);

        txtHeader = view.findViewById(R.id.txt_change_password_header);
        btnChangeOk = view.findViewById(R.id.btn_change_password_ok);

        //Init
        globals = new Globals(getContext());
        Globals.toolbarBack.setVisibility(View.VISIBLE);
        ActivityMain.imgHome.setColorFilter(getResources().getColor(R.color.black));
        ActivityMain.txtHome.setTextColor(getResources().getColor(R.color.black));
        ActivityMain.lnrBotomMenu.setVisibility(View.GONE);

        edtCurrentPass.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View view, boolean b) {
                if(!b)
                {
                    view.setBackgroundDrawable(getResources().getDrawable(R.drawable.shape_edittext));
                }
                else
                {
                    view.setBackgroundDrawable(getResources().getDrawable(R.drawable.shape_edittext_focused));
                }
            }
        });

        edtNewPass.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View view, boolean b) {
                if(!b)
                {
                    view.setBackgroundDrawable(getResources().getDrawable(R.drawable.shape_edittext));
                }
                else
                {
                    view.setBackgroundDrawable(getResources().getDrawable(R.drawable.shape_edittext_focused));
                }
            }
        });

        edtRepeatNewPass.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View view, boolean b) {
                if(!b)
                {
                    edtRepeatNewPass.setBackgroundDrawable(getResources().getDrawable(R.drawable.shape_edittext));
                }
                else
                {
                    edtRepeatNewPass.setBackgroundDrawable(getResources().getDrawable(R.drawable.shape_edittext_focused));
                }
            }
        });

        btnChangeOk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if(edtCurrentPass.getVisibility() == View.VISIBLE && edtCurrentPass.getText().toString().equals("")) {
                    Snackbar.make(getActivity().findViewById(android.R.id.content), "لطفا رمز فعلی را وارد کنید.", Snackbar.LENGTH_LONG).show();
                }

                if(edtNewPass.getVisibility() == View.VISIBLE && edtNewPass.getText().toString().equals("")){
                    Snackbar.make(getActivity().findViewById(android.R.id.content), "لطفا رمز جدید را وارد کنید.", Snackbar.LENGTH_LONG).show();
                }

                if(edtRepeatNewPass.getVisibility() == View.VISIBLE && edtRepeatNewPass.getText().toString().equals(""))
                {

                    Snackbar.make(getActivity().findViewById(android.R.id.content), "لطفا تکرار رمز جدید را وارد کنید.", Snackbar.LENGTH_LONG).show();
                    return;
                }
                else
                {

                }
            }
        });

        return view;
    }
}

