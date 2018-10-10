package Fragments;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;

import Adapters.AdapterSelectAdvisor;
import FontComponents.MyButton;
import Structures.StructureSelectAdvisor;
import de.hdodenhof.circleimageview.CircleImageView;
import ir.andishehlab.asanfit.ActivityMain;
import ir.andishehlab.asanfit.R;

/**
 * Created by N550J on 1/4/2018.
 */

public class FragmentSelectAdvisor extends Fragment {

    private static String strTestBitmapUrl = "https://homepages.cae.wisc.edu/~ece533/images/tulips.png";
    private CircleImageView moshaverProfileImage;
    private ArrayList<StructureSelectAdvisor> userCommentsList = new ArrayList<StructureSelectAdvisor>();
    private AdapterSelectAdvisor adapter;
    private RecyclerView recyclerView;
    private MyButton btnContinue;

    private static FragmentSelectAdvisor instance;
    public FragmentSelectAdvisor() {
        // Required empty public constructor
    }

    public static FragmentSelectAdvisor getInstance()
    {
        if (instance == null)
            instance = new FragmentSelectAdvisor();

        return instance;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_select_advisor, container, false);

        moshaverProfileImage = view.findViewById(R.id.moshaver_profile_image);
        recyclerView = view.findViewById(R.id.recycle_view_user_comments);
        btnContinue = view.findViewById(R.id.btn_select_advisor_continue);

        //Init
        InitUserComments();
        ActivityMain.lnrBotomMenu.setVisibility(View.GONE);

        adapter = new AdapterSelectAdvisor(getActivity(), userCommentsList);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));

        btnContinue.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });

        return view;
    }

    private void InitUserComments() {
        userCommentsList.add(new StructureSelectAdvisor("محمدرضا عارف", 2, "24/1", loadBitmap(strTestBitmapUrl)));
        userCommentsList.add(new StructureSelectAdvisor("محمدرضا عارف", 1, "24/1", loadBitmap(strTestBitmapUrl)));
        userCommentsList.add(new StructureSelectAdvisor("محمدرضا عارف", 4, "24/1", loadBitmap(strTestBitmapUrl)));
        userCommentsList.add(new StructureSelectAdvisor("محمدرضا عارف", 5, "24/1", loadBitmap(strTestBitmapUrl)));
        userCommentsList.add(new StructureSelectAdvisor("محمدرضا عارف", 3, "24/1", loadBitmap(strTestBitmapUrl)));
        userCommentsList.add(new StructureSelectAdvisor("محمدرضا عارف", 1, "24/1", loadBitmap(strTestBitmapUrl)));
        userCommentsList.add(new StructureSelectAdvisor("محمدرضا عارف", 5, "24/1", loadBitmap(strTestBitmapUrl)));
        userCommentsList.add(new StructureSelectAdvisor("محمدرضا عارف", 4, "24/1", loadBitmap(strTestBitmapUrl)));

    }

    public Bitmap loadBitmap(String url)
    {
        Bitmap bm = null;
        InputStream is = null;
        BufferedInputStream bis = null;
        try
        {
            URLConnection conn = new URL(url).openConnection();
            conn.connect();
            is = conn.getInputStream();
            bis = new BufferedInputStream(is, 8192);
            bm = BitmapFactory.decodeStream(bis);
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
        finally {
            if (bis != null)
            {
                try
                {
                    bis.close();
                }
                catch (IOException e)
                {
                    e.printStackTrace();
                }
            }
            if (is != null)
            {
                try
                {
                    is.close();
                }
                catch (IOException e)
                {
                    e.printStackTrace();
                }
            }
        }
        return bm;
    }
}
