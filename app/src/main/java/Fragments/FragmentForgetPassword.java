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
public class FragmentForgetPassword extends Fragment {

    MyEditText edtCurrentPass, edtNewPass, edtRepeatNewPass;
    Globals globals;
    TextViewFont txtHeader;
    MyButton btnChangeOk;

    private static FragmentForgetPassword instance;
    public FragmentForgetPassword() {
        // Required empty public constructor
    }

    public static FragmentForgetPassword getInstance()
    {
        if (instance == null)
            instance = new FragmentForgetPassword();
        return instance;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_forget_password, container, false);

        edtCurrentPass = view.findViewById(R.id.edt_forget_password);
        btnChangeOk = view.findViewById(R.id.btn_forget_password_ok);

        //Init
        globals = new Globals(getContext());
        Globals.toolbarBack.setVisibility(View.VISIBLE);
        ActivityMain.imgHome.setColorFilter(getResources().getColor(R.color.black));
        ActivityMain.txtHome.setTextColor(getResources().getColor(R.color.black));
        ActivityMain.lnrBotomMenu.setVisibility(View.GONE);

        //1
        edtCurrentPass.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View view, boolean b) {
                if(b)
                {
                    view.setBackgroundResource(R.drawable.shape_edittext_focused);
                }
                else
                {
                    view.setBackgroundResource(R.drawable.shape_edittext);
                }
            }
        });

        btnChangeOk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(edtCurrentPass.getVisibility() == View.VISIBLE && edtCurrentPass.getText().toString().equals("")) {
                    Snackbar.make(getActivity().findViewById(android.R.id.content), "لطفا رمز فعلی را وارد کنید.", Snackbar.LENGTH_LONG).show();
                }
                else
                if(edtNewPass.getVisibility() == View.VISIBLE && edtNewPass.getText().toString().equals("")){
                    Snackbar.make(getActivity().findViewById(android.R.id.content), "لطفا رمز جدید را وارد کنید.", Snackbar.LENGTH_LONG).show();
                }
                else
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

