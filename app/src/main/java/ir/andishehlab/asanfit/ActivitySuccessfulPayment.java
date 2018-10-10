package ir.andishehlab.asanfit;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

/**
 * Created by N550J on 12/1/2017.
 */
public class ActivitySuccessfulPayment extends AppCompatActivity{

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_successful_payment);

        ActivityMain.imgHome.setColorFilter(getResources().getColor(R.color.black));
        ActivityMain.txtHome.setTextColor(getResources().getColor(R.color.black));
    }
}

