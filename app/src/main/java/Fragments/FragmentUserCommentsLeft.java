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

import Adapters.AdapterUserComments;
import Structures.StructureUserComments;
import de.hdodenhof.circleimageview.CircleImageView;
import ir.andishehlab.asanfit.R;

/**
 * Created by N550J on 1/4/2018.
 */

public class FragmentUserCommentsLeft extends Fragment {

    private static String strTestBitmapUrl = "https://homepages.cae.wisc.edu/~ece533/images/tulips.png";
    private CircleImageView moshaverProfileImage;
    private ArrayList<StructureUserComments> userCommentsList = new ArrayList<StructureUserComments>();
    private AdapterUserComments adapter;
    private RecyclerView recyclerView;

    private static FragmentUserCommentsLeft instance;
    public FragmentUserCommentsLeft() {
        // Required empty public constructor
    }

    public static FragmentUserCommentsLeft getInstance()
    {
        if (instance == null)
            instance = new FragmentUserCommentsLeft();

        return instance;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_user_comments_left, container, false);

        moshaverProfileImage = view.findViewById(R.id.moshaver_profile_image);
        recyclerView = view.findViewById(R.id.recycle_view_user_comments);

        //Init
        InitUserComments();

        adapter = new AdapterUserComments(getActivity(), userCommentsList);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));

        return view;
    }

    private void InitUserComments() {
        userCommentsList.add(new StructureUserComments("محمدرضا عارف", 2, "خیلی نرم افزار خوبی دارین", loadBitmap(strTestBitmapUrl)));
        userCommentsList.add(new StructureUserComments("محمدرضا عارف", 1, "خیلی نرم افزار خوبی دارین", loadBitmap(strTestBitmapUrl)));
        userCommentsList.add(new StructureUserComments("محمدرضا عارف", 4, "خیلی نرم افزار خوبی دارین", loadBitmap(strTestBitmapUrl)));
        userCommentsList.add(new StructureUserComments("محمدرضا عارف", 5, "خیلی نرم افزار خوبی دارین", loadBitmap(strTestBitmapUrl)));
        userCommentsList.add(new StructureUserComments("محمدرضا عارف", 3, "خیلی نرم افزار خوبی دارین", loadBitmap(strTestBitmapUrl)));
        userCommentsList.add(new StructureUserComments("محمدرضا عارف", 1, "خیلی نرم افزار خوبی دارین", loadBitmap(strTestBitmapUrl)));
        userCommentsList.add(new StructureUserComments("محمدرضا عارف", 5, "خیلی نرم افزار خوبی دارین", loadBitmap(strTestBitmapUrl)));
        userCommentsList.add(new StructureUserComments("محمدرضا عارف", 4, "خیلی نرم افزار خوبی دارین", loadBitmap(strTestBitmapUrl)));

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
