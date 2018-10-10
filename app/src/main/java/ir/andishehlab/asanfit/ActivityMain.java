package ir.andishehlab.asanfit;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.Gravity;
import android.view.View;
import android.view.WindowManager;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.ArrayList;

import Adapters.AdapterNavigationView;
import FontComponents.TextViewFont;
import Fragments.FragmentChat;
import Fragments.FragmentFirstPage;
import Fragments.FragmentFoodPlan;
import Others.Globals;
import Others.Settings;
import Structures.StructureNavigation;

public class ActivityMain extends AppCompatActivity {

    //public
    public static FragmentManager fragmentManager;
    public static FrameLayout globalFrame;
    public static LinearLayout lnrBotomMenu;
    public static ImageView imgProgram, imgChat;
    public static ImageView imgHome;
    public static TextView txtHome;
    public static TextViewFont txtProgram, txtChat;
    public static View activity;
    public static String notification_message;

    //Privates
    public static TextViewFont txtNavUserName;
    private Toolbar toolbar;
    private ArrayList<StructureNavigation> navigationList = new ArrayList<StructureNavigation>();
    private AdapterNavigationView adapter;
    private ActionBarDrawerToggle actionBarDrawerToggle;
    private boolean doubleBackToExitPressedOnce = false;
    private ImageView imgToggleButton;
    private LinearLayout lnrHome, lnrProgram, lnrChat;
    private LinearLayout lnrBackground;

    @SuppressLint("ResourceAsColor")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

//        if(Build.VERSION.SDK_INT > 19)
//        {
//            getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
//        }
//        else
//        {
//            getWindow().clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
//        }

        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);

        setContentView(R.layout.activity_main);

        FragmentFirstPage.getInstance().currentWeight = getIntent().getStringExtra("CURRENT_WEIGHT");

        toolbar = (Toolbar) findViewById(R.id.main_toolbar);
        Globals.recyclerView = (RecyclerView) findViewById(R.id.list_nav);
        Globals.drawerLayout = findViewById(R.id.drawer_layout);
        Globals.toolbarBack = findViewById(R.id.img_toolbar_back);

        lnrHome = findViewById(R.id.lnr_bottom_menu_home);
        lnrProgram = findViewById(R.id.lnr_bottom_menu_program);
        lnrChat = findViewById(R.id.lnr_bottom_menu_chat);;
        imgHome = findViewById(R.id.lnr_bottom_menu_home_img);
        imgProgram = findViewById(R.id.lnr_bottom_menu_program_img);
        imgChat = findViewById(R.id.lnr_bottom_menu_chat_img);
        txtHome = findViewById(R.id.lnr_bottom_menu_home_text);
        txtProgram  = findViewById(R.id.lnr_bottom_menu_program_text);
        txtChat  = findViewById(R.id.lnr_bottom_menu_chat_text);
        imgToggleButton = findViewById(R.id.img_toolbar_button);
        globalFrame = findViewById(R.id.frame_container);
        lnrBotomMenu = findViewById(R.id.layout_bottom_menu);
        txtNavUserName = findViewById(R.id.txt_rightnav_name);

        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("");
        //Inits
        fragmentManager = getSupportFragmentManager();
        fragmentManager.beginTransaction().replace(R.id.frame_container, FragmentFirstPage.getInstance()).commit();
        //Globals.toolbarBack.setVisibility(View.INVISIBLE);
        lnrBotomMenu.setVisibility(View.VISIBLE);
        //toolbar.getBackground().setAlpha(0);
        Settings.getInstance().setbIsRegistered(true);
        Settings.getInstance().saveAll();
        activity = this.findViewById(android.R.id.content);


        String str  = Settings.getInstance().getProfile().profile.getName();

        txtNavUserName.setText(Settings.getInstance().getProfile().profile.getName());
        Globals.drawerLayout.setScrimColor(Color.parseColor("#80FFFFFF"));


        InitFirstPage();

        //actionBarDrawerToggle = new ActionBarDrawerToggle(this, Globals.drawerLayout, toolbar, R.string.drawer_open, R.string.drawer_close);
        //Globals.drawerLayout.setDrawerListener(imgToggleButton);
        imgToggleButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Globals.drawerLayout.openDrawer(Gravity.RIGHT);
            }
        });

        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (Globals.drawerLayout.isDrawerOpen(Gravity.RIGHT)) {
                    Globals.drawerLayout.closeDrawer(Gravity.RIGHT);
                } else {
                    Globals.drawerLayout.openDrawer(Gravity.RIGHT);
                }
            }
        });

        lnrHome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                imgHome.setColorFilter(getResources().getColor(R.color.mycolor_yellow));
                txtHome.setTextColor(getResources().getColor(R.color.mycolor_yellow));
                imgProgram.setColorFilter(getResources().getColor(R.color.black));
                txtProgram.setTextColor(getResources().getColor(R.color.black));
                imgChat.setColorFilter(getResources().getColor(R.color.black));
                txtChat.setTextColor(getResources().getColor(R.color.black));

                if(!FragmentFirstPage.getInstance().isAdded())
                    fragmentManager.beginTransaction().replace(R.id.frame_container, FragmentFirstPage.getInstance()).commit();

            }
        });
        lnrProgram.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                imgHome.setColorFilter(getResources().getColor(R.color.black));
                txtHome.setTextColor(getResources().getColor(R.color.black));
                imgProgram.setColorFilter(getResources().getColor(R.color.mycolor_yellow));
                txtProgram.setTextColor(getResources().getColor(R.color.mycolor_yellow));
                imgChat.setColorFilter(getResources().getColor(R.color.black));
                txtChat.setTextColor(getResources().getColor(R.color.black));

                fragmentManager.beginTransaction().replace(R.id.frame_container, FragmentFoodPlan.getInstance()).addToBackStack("").commit();


            }
        });

        lnrChat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                imgHome.setColorFilter(getResources().getColor(R.color.black));
                txtHome.setTextColor(getResources().getColor(R.color.black));
                imgProgram.setColorFilter(getResources().getColor(R.color.black));
                txtProgram.setTextColor(getResources().getColor(R.color.black));
                imgChat.setColorFilter(getResources().getColor(R.color.mycolor_yellow));
                txtChat.setTextColor(getResources().getColor(R.color.mycolor_yellow));

                fragmentManager.beginTransaction().replace(R.id.frame_container, FragmentChat.getInstance()).addToBackStack("").commit();
            }
        });

        InitNavigationList();

        adapter = new AdapterNavigationView(this, navigationList, R.id.frame_container, fragmentManager);
        Globals.recyclerView.setAdapter(adapter);
        Globals.recyclerView.setLayoutManager(new LinearLayoutManager(this));


        Globals.toolbarBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //fragmentManager.beginTransaction().replace(R.id.frame_container, FragmentFirstPage.getInstance()).commit();
                //Globals.toolbarBack.setVisibility(View.INVISIBLE);
                if(fragmentManager.getBackStackEntryCount() <= 0)
                    finish();


//                int index = getFragmentManager().getBackStackEntryCount() - 1;
//                FragmentManager.BackStackEntry backEntry = (FragmentManager.BackStackEntry) getFragmentManager().getBackStackEntryAt(index);
//                String tag = backEntry.getName();
//                Fragment fragment = getFragmentManager().findFragmentByTag(tag);

                //if(fragment.isAdded()) {
                    for (int i = fragmentManager.getBackStackEntryCount() - 1; i >= 0; i--)//Clean Stack when back clicked
                    {
                        fragmentManager.popBackStack();
                    }
                //}
                lnrHome.callOnClick();
            }
        });


        Bundle extras = getIntent().getExtras();

        if (extras != null) {
            notification_message = extras.getString("message_content");
            if(FragmentFirstPage.getInstance().txtNotification != null)
                FragmentFirstPage.getInstance().txtNotification.setText(notification_message);
        }

        //RunNotificationThrea();
    }

    void RunNotificationThrea()
    {
        final Handler ha=new Handler();
        ha.postDelayed(new Runnable() {

            @Override
            public void run() {
                //call function
                FragmentFirstPage.getInstance().RunNotificationThread();
                ha.postDelayed(this, 300000);
            }
        }, 300000);
    }

    void InitNavigationList()
    {
        navigationList.add(new StructureNavigation("پروفایل من" , R.drawable.ic_myprofile, 0));
        navigationList.add(new StructureNavigation("ثبت وزن" , R.drawable.ic_weight_menu, 2));
        navigationList.add(new StructureNavigation("پرونده پزشکی" , R.drawable.ic_medicalprofile, 0));
        navigationList.add(new StructureNavigation("کد تخفیف هدیه" , R.drawable.ic_tag, 0));
        navigationList.add(new StructureNavigation("تمدید اشتراک" , R.drawable.ic_coins, 0));
        navigationList.add(new StructureNavigation("درباره آسان فیت" , R.drawable.ic_aboutus, 0));
    }

    void InitFirstPage()
    {

    }

    @Override
    protected void onPostCreate(@Nullable Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);
        //actionBarDrawerToggle.syncState();
    }

   /* @Override
    public void onBackPressed() {
        if(Globals.drawerLayout.isDrawerOpen(Gravity.RIGHT)) {
            Globals.drawerLayout.closeDrawer(Gravity.RIGHT);
            return;
        }

        if (doubleBackToExitPressedOnce) {
            super.onBackPressed();
            return;
        }

        this.doubleBackToExitPressedOnce = true;
        View parentLayout = findViewById(android.R.id.content);
        Snackbar.make(parentLayout, "برای خروج دوباره لمس کنید . . .", Snackbar.LENGTH_LONG).show();

        new Handler().postDelayed(new Runnable() {

            @Override
            public void run() {
                doubleBackToExitPressedOnce=false;
            }
        }, 2000);
    }*/

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        // TODO Auto-generated method stub
        switch(requestCode){
            case Globals.PICKFILE_RESULT_CODE:
                if(resultCode == RESULT_OK){
                    String FilePath = data.getData().getPath();
                    //Toast.makeText(this, FilePath, Toast.LENGTH_LONG).show();
                }
                break;

        }
    }
}
