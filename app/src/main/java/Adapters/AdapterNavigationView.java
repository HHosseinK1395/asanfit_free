package Adapters;

import android.content.Context;
import android.graphics.Color;
import android.support.v4.app.FragmentManager;
import android.support.v7.widget.RecyclerView;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import FontComponents.TextViewFont;
import Fragments.FragmentAboutUs;
import Fragments.FragmentDiscountCode;
import Fragments.FragmentExtendedSubscription;
import Fragments.FragmentInputWeightInfoAndDiagram;
import Fragments.FragmentParvandehPezeshki;
import Fragments.FragmentProfile;
import Others.Globals;
import Structures.StructureNavigation;
import ir.andishehlab.asanfit.R;

public class AdapterNavigationView extends RecyclerView.Adapter<AdapterNavigationView.ViewHolder>
{
    private List<StructureNavigation> mContacts;
    private Context mContext;
    private int frameContainer;
    private FragmentManager fragmentManager;
    private List<RelativeLayout> items;
    private List<TextViewFont> txtList;
    private List<ImageView> imgList;

    public AdapterNavigationView(Context context, List<StructureNavigation> contacts, int frameLayout, FragmentManager fragmentManager)
    {
        this.mContacts = contacts;
        this.mContext = context;
        this.frameContainer = frameLayout;
        this.fragmentManager = fragmentManager;
        this.items = new ArrayList<>();
        this.txtList = new ArrayList<>();
        this.imgList = new ArrayList<>();
    }

    private Context getContext()
    {
        return mContext;
    }

    @Override
    public AdapterNavigationView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType)
    {
            Context context = parent.getContext();
            LayoutInflater inflater = LayoutInflater.from(context);
            View contactView = inflater.inflate(R.layout.layout_navigation_list_item, parent, false);
            ViewHolder viewHolder = new ViewHolder(contactView);
            return viewHolder;
    }

    @Override
    public void onBindViewHolder(AdapterNavigationView.ViewHolder viewHolder, int position) {
        StructureNavigation contact = mContacts.get(position);

        items.add(viewHolder.list_row);
        txtList.add(viewHolder.nameTextView);
        imgList.add(viewHolder.icon);

        TextView textView = viewHolder.nameTextView;
        textView.setText(contact.getText());

        ImageView imgIcon = viewHolder.icon;
        imgIcon.setImageResource(contact.getImageView());

        TextView textViewCount = viewHolder.txtMessageNumber;
        if(position == 1) {
            viewHolder.frameLayout.setBackgroundResource(R.drawable.background_circle);
            textViewCount.setTextColor(mContext.getResources().getColor(R.color.white));
            textViewCount.setText(String.valueOf(contact.getiMessageCount()));
        }
    }

    @Override
    public int getItemCount()
    {
        return mContacts.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder
    {
        public TextViewFont nameTextView, txtMessageNumber;
        public ImageView icon;
        public FrameLayout frameLayout;
        RelativeLayout list_row;

        public ViewHolder(View itemView)
        {
            super(itemView);
            nameTextView = itemView.findViewById(R.id.item_string);
            icon = itemView.findViewById(R.id.item_image);
            txtMessageNumber = itemView.findViewById(R.id.item_string_message_count);
            frameLayout = itemView.findViewById(R.id.frame_message_count);
            list_row = itemView.findViewById(R.id.relative_row);

            list_row.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    int itemPosition = Globals.recyclerView.getChildLayoutPosition(view);
                    makeAllWhite();
                    switch (itemPosition)
                    {
                        case 0: {
                            fragmentManager.beginTransaction().replace(
                                    frameContainer, FragmentProfile.getInstance(), "PROFILE_TAG").addToBackStack("PROFILE_TAG").commit();
                            Globals.drawerLayout.closeDrawer(Gravity.END);

                            items.get(0).setBackground(mContext.getResources().getDrawable(R.drawable.shape_navigation_item_background));
                            nameTextView.setTextColor(mContext.getResources().getColor(R.color.white));
                            imgList.get(0).setImageResource(R.drawable.ic_myprofile_white);
                        }
                        break;
                        case 1: {
                            fragmentManager.beginTransaction().replace(
                                    frameContainer, FragmentInputWeightInfoAndDiagram.getInstance(), "REGISTER_WEIGHT_TAG").addToBackStack("REGISTER_WEIGHT_TAG").commit();
                            Globals.drawerLayout.closeDrawer(Gravity.END);

                            items.get(1).setBackground(mContext.getResources().getDrawable(R.drawable.shape_navigation_item_background));
                            nameTextView.setTextColor(mContext.getResources().getColor(R.color.white));
                            imgList.get(1).setImageResource(R.drawable.ic_weight_white);
                        }
                        break;
                        case 2: {
                            fragmentManager.beginTransaction().replace(
                                    frameContainer, FragmentParvandehPezeshki.getInstance()).addToBackStack("PARVANDEH_TAG").commit();
                            Globals.drawerLayout.closeDrawer(Gravity.END);

                            items.get(2).setBackground(mContext.getResources().getDrawable(R.drawable.shape_navigation_item_background));
                            nameTextView.setTextColor(mContext.getResources().getColor(R.color.white));
                            imgList.get(2).setImageResource(R.drawable.ic_medicalprofile_white);
                        }
                        break;
                        case 3: {
                            fragmentManager.beginTransaction().replace(
                                    frameContainer, FragmentDiscountCode.getInstance()).addToBackStack("DISCOUNT_CODE_TAG").commit();
                            Globals.drawerLayout.closeDrawer(Gravity.END);

                            items.get(3).setBackground(mContext.getResources().getDrawable(R.drawable.shape_navigation_item_background));
                            nameTextView.setTextColor(mContext.getResources().getColor(R.color.white));
                            imgList.get(3).setImageResource(R.drawable.ic_tag_white);
                        }
                        break;
                        case 4: {
                            fragmentManager.beginTransaction().replace(
                                    frameContainer, FragmentExtendedSubscription.getInstance()).addToBackStack("EXTEND_SUBSCRIPTION_TAG").commit();
                            Globals.drawerLayout.closeDrawer(Gravity.END);

                            items.get(4).setBackground(mContext.getResources().getDrawable(R.drawable.shape_navigation_item_background));
                            nameTextView.setTextColor(mContext.getResources().getColor(R.color.white));
                            imgList.get(4).setImageResource(R.drawable.ic_coins_white);
                        }
                        break;
                        case 5: {
                            fragmentManager.beginTransaction().replace(
                                    frameContainer, FragmentAboutUs.getInstance(), "ABOUT_TAG").addToBackStack("ABOUT_TAG").commit();
                            Globals.drawerLayout.closeDrawer(Gravity.END);

                            items.get(5).setBackground(mContext.getResources().getDrawable(R.drawable.shape_navigation_item_background));
                            nameTextView.setTextColor(mContext.getResources().getColor(R.color.white));
                            imgList.get(5).setImageResource(R.drawable.ic_aboutus_white);
                        }
                        break;
                        default:
                        break;
                    }
                }
            });
        }
    }

    private void makeAllWhite() {
        for(RelativeLayout item : items) {
            item.setBackgroundColor(Color.parseColor("#ffffff"));
        }

        for(TextViewFont txtView : txtList)
        {
            txtView.setTextColor(mContext.getResources().getColor(R.color.black));
        }

        imgList.get(0).setImageResource(R.drawable.ic_myprofile);
        imgList.get(1).setImageResource(R.drawable.ic_weight_menu);
        imgList.get(2).setImageResource(R.drawable.ic_medicalprofile);
        imgList.get(3).setImageResource(R.drawable.ic_tag);
        imgList.get(4).setImageResource(R.drawable.ic_coins);
        imgList.get(5).setImageResource(R.drawable.ic_aboutus);
    }
}
