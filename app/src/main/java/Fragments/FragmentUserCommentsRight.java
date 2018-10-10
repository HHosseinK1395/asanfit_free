package Fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import de.hdodenhof.circleimageview.CircleImageView;
import ir.andishehlab.asanfit.R;

/**
 * Created by N550J on 1/4/2018.
 */

public class FragmentUserCommentsRight extends Fragment {


    CircleImageView moshaverProfileImage;

    private static FragmentUserCommentsRight instance;
    public FragmentUserCommentsRight() {
        // Required empty public constructor
    }

    public static FragmentUserCommentsRight getInstance()
    {
        if (instance == null)
            instance = new FragmentUserCommentsRight();

        return instance;
    }


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_user_comments_right, container, false);

        moshaverProfileImage = view.findViewById(R.id.moshaver_profile_image);

        return view;
    }
}
