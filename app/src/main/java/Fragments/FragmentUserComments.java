package Fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import Adapters.AdapterViewPager;
import Others.Globals;
import de.hdodenhof.circleimageview.CircleImageView;
import ir.andishehlab.asanfit.ActivityMain;
import ir.andishehlab.asanfit.R;

/**
 * Created by N550J on 1/4/2018.
 */

public class FragmentUserComments extends Fragment {


    CircleImageView moshaverProfileImage;
    TabLayout tabLayout;
    ViewPager viewPager;
    AdapterViewPager adapterViewPager;


    private static FragmentUserComments instance;
    public FragmentUserComments() {
        // Required empty public constructor
    }

    public static FragmentUserComments getInstance()
    {
        if (instance == null)
            instance = new FragmentUserComments();

        return instance;
    }


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_user_comments, container, false);
        moshaverProfileImage = view.findViewById(R.id.moshaver_profile_image);


        //Init
        Globals.toolbarBack.setVisibility(View.VISIBLE);
        ActivityMain.lnrBotomMenu.setVisibility(View.GONE);

        adapterViewPager = new AdapterViewPager(getChildFragmentManager());
        adapterViewPager.addFragment(new FragmentUserCommentsLeft(), "نظرات کاربران");
        adapterViewPager.addFragment(new FragmentUserCommentsRight(), "اطلاعات");

        viewPager = view.findViewById(R.id.user_comments_view_pager);
        viewPager.setBackgroundColor(getResources().getColor(R.color.transparent));
        viewPager.setAdapter(adapterViewPager);
        viewPager.setOffscreenPageLimit(0);
        tabLayout = view.findViewById(R.id.tab_layout_user_comments);

        tabLayout.setupWithViewPager(viewPager);

        TabLayout.Tab tab = tabLayout.getTabAt(tabLayout.getTabCount() - 1);
        tab.select();

        tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                int pos = tab.getPosition();
                //Toast.makeText(getContext(), "Position : " + pos, Toast.LENGTH_LONG).show();
                switch (pos)
                {
                    case 0:

                        break;
                    case 1:

                        break;
                    case 2:

                        break;
                }
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });

        return view;
    }
}
