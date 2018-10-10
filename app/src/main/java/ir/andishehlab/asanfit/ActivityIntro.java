package ir.andishehlab.asanfit;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;

import Adapters.AdapterIntro;
import FontComponents.MyButton;
import Others.PreferencesManager;

/**
 * Created by N550J on 12/2/2017.
 */
public class ActivityIntro extends AppCompatActivity implements View.OnClickListener {

    private ViewPager viewPager;
    private int[] layouts = {R.layout.fragment_intro_1, R.layout.fragment_intro_2, R.layout.fragment_intro_3};
    private AdapterIntro adapterIntro;
    private LinearLayout dotslayout;
    private ImageView[] dots;
    private LinearLayout imgNext, imgSkip, imgPrev;
    private MyButton btnRegisterIntro;
    private Button btnIntroRegister;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        PreferencesManager.getInstance().getSharedPreferences(this);
        if(PreferencesManager.getInstance().checkPreferences(this))
        {
            loadHome();
        }

//        if(Build.VERSION.SDK_INT > 19)
//        {
//            getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
//        }
//        else
//        {
//            getWindow().clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
//        }

        setContentView(R.layout.activity_intro);

        viewPager = findViewById(R.id.pager_intro);
        dotslayout = findViewById(R.id.dotslayout);
        imgNext = findViewById(R.id.btnNext);
        imgSkip = findViewById(R.id.btnSkip);
        imgPrev = findViewById(R.id.btnPrev);
        btnIntroRegister = findViewById(R.id.btn_intro_register);
        //btnRegisterIntro = findViewById(R.id.btn_intro_register);

        adapterIntro = new AdapterIntro(layouts, this);
        viewPager.setAdapter(adapterIntro);

        imgSkip.setOnClickListener(this);
        imgNext.setOnClickListener(this);
        imgPrev.setOnClickListener(this);
        btnIntroRegister.setOnClickListener(this);

        createDots(0);


        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                createDots(position);
                if(position == layouts.length - 1)
                {
                    imgSkip.setVisibility(View.GONE);
                    imgNext.setVisibility(View.GONE);
                    dotslayout.setVisibility(View.GONE);
                    imgPrev.setVisibility(View.VISIBLE);
                    btnIntroRegister.setVisibility(View.VISIBLE);

                }
                else
                {
                    imgSkip.setVisibility(View.VISIBLE);
                    imgNext.setVisibility(View.VISIBLE);
                    dotslayout.setVisibility(View.VISIBLE);
                    imgPrev.setVisibility(View.GONE);
                    btnIntroRegister.setVisibility(View.GONE);
                }
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
    }

    private void createDots(int currentPosition)
    {
        if(dotslayout != null)
        {
            dotslayout.removeAllViews();
        }

        dots = new ImageView[layouts.length];
        for(int i=0; i<layouts.length; i++)
        {
            dots[i] = new ImageView(this);
            if(i == currentPosition)
            {
                dots[i].setImageDrawable(ContextCompat.getDrawable(this, R.drawable.indicator_gradient));
            }
            else
            {
                dots[i].setImageDrawable(ContextCompat.getDrawable(this, R.drawable.round_indicator));
            }

            LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT,
                    ViewGroup.LayoutParams.WRAP_CONTENT);
            params.setMargins(4,0,4,0);

            dotslayout.addView(dots[i], params);
        }
    }

    @Override
    public void onClick(View view) {
        switch (view.getId())
        {
            case R.id.btnNext:
            {
                loadNextSlid();
            }
            break;
            case R.id.btnSkip:
            {
                loadHome();
                PreferencesManager.getInstance().checkPreferences(this);
                PreferencesManager.getInstance().writeSHaredPreferences(this);
            }
            break;
            case R.id.btnPrev:
            {
                loadPrevSlid();
            }
            break;
            case R.id.btn_intro_register:
            {
                loadHome();
                PreferencesManager.getInstance().checkPreferences(this);
                PreferencesManager.getInstance().writeSHaredPreferences(this);
            }
            break;
            default:
            break;
        }
    }

    private void loadHome()
    {
        startActivity(new Intent(this, ActivityRegister.class));
        finish();
    }

    private void loadNextSlid()
    {
        int nextSlide = viewPager.getCurrentItem() + 1;
        if(nextSlide < layouts.length)
        {
            viewPager.setCurrentItem(nextSlide);
        }
        else
        {
            loadHome();
            PreferencesManager.getInstance().checkPreferences(this);
            PreferencesManager.getInstance().writeSHaredPreferences(this);
        }
    }

    private void loadPrevSlid() {
        int nextSlide = viewPager.getCurrentItem() - 1;
        if(nextSlide > 0)
        {
            viewPager.setCurrentItem(nextSlide);
        }
        else
        {
            loadHome();
            PreferencesManager.getInstance().checkPreferences(this);
            PreferencesManager.getInstance().writeSHaredPreferences(this);
        }
    }

}
