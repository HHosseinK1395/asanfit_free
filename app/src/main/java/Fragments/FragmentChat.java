package Fragments;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import FontComponents.MyEditText;
import Others.Globals;
import ir.andishehlab.asanfit.ActivityMain;
import ir.andishehlab.asanfit.R;

/**
 * Created by N550J on 12/1/2017.
 */
public class FragmentChat extends Fragment {

    MyEditText edtChat;
    ImageView imgAttachFile;

    private static FragmentChat instance;
    public FragmentChat() {
        // Required empty public constructor
    }

    public static FragmentChat getInstance()
    {
        if (instance == null)
            instance = new FragmentChat();
        return instance;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_chat, container, false);

        edtChat = view.findViewById(R.id.edt_chat);
        imgAttachFile = view.findViewById(R.id.img_attach_file);

        //Init
        Globals.toolbarBack.setVisibility(View.VISIBLE);
        ActivityMain.lnrBotomMenu.setVisibility(View.VISIBLE);

        edtChat.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View view, boolean b) {
                if(b)
                {
                    view.setBackgroundResource(R.drawable.shape_edittext_chat_focused);
                }
                else
                {
                    view.setBackgroundResource(R.drawable.shape_edittext_chat);
                }
            }
        });

        imgAttachFile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Intent.ACTION_GET_CONTENT);
                intent.setType("file/*");
                startActivityForResult(intent, Globals.PICKFILE_RESULT_CODE);
            }
        });

        return view;
    }
}

