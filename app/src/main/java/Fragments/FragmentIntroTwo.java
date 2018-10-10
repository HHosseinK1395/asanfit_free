package Fragments;

import android.os.Bundle;
import android.support.annotation.ColorInt;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.github.paolorotolo.appintro.ISlideBackgroundColorHolder;

import ir.andishehlab.asanfit.R;

/**
 * Created by N550J on 12/2/2017.
 */
public class FragmentIntroTwo extends Fragment  implements ISlideBackgroundColorHolder {

    private View mainView;
    private AppCompatActivity activity;

    @Override
    public int getDefaultBackgroundColor() {
        // Return the default background color of the slide.
        return ContextCompat.getColor(getActivity(), R.color.intro_background_2);
    }

    @Override
    public void setBackgroundColor(@ColorInt int backgroundColor) {
        // Set the background color of the view within your slide to which the transition should be applied.
        if (mainView != null) {
            mainView.setBackgroundColor(backgroundColor);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
    {
        mainView = inflater.inflate(R.layout.fragment_intro_2, null);
        return mainView;
    }

    @Override
    public void onResume()
    {
        super.onResume();

    }
}
