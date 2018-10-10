package Others;

import android.app.Activity;
import android.app.Dialog;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.Button;

import FontComponents.TextViewFont;
import ir.andishehlab.asanfit.R;

public class MyProgressDialog extends Dialog implements
        android.view.View.OnClickListener {

    public Activity c;
    public Dialog d;
    public Button yes, no;
    public String strMessage;

    public MyProgressDialog(Activity a, String message) {
        super(a);
        // TODO Auto-generated constructor stub
        this.c = a;
        this.strMessage = message;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.layout_progress_dialog);

        ((TextViewFont)this.findViewById(R.id.txt_dia)).setText(this.strMessage);
//        yes = (Button) findViewById(R.id.btn_yes);
//        no = (Button) findViewById(R.id.btn_no);
//        yes.setOnClickListener(this);
//        no.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            //case R.id.btn_yes:
                //c.finish();
                //break;
            //case R.id.btn_no:
                //dismiss();
                //break;
            //default:
                //break;
        }
        //dismiss();
    }
}