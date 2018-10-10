package Adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.SparseBooleanArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.List;

import Structures.StructureSelectAdvisor;
import de.hdodenhof.circleimageview.CircleImageView;
import ir.andishehlab.asanfit.R;

/**
 * Created by N550J on 1/5/2018.
 */

public class AdapterSelectAdvisor extends RecyclerView.Adapter<AdapterSelectAdvisor.ViewHolder>{

    private static int MAXIMUM_RATE_DOTS = 5;
    private List<StructureSelectAdvisor> mList;
    private Context mContext;
    SparseBooleanArray selectedItems = new SparseBooleanArray();
    ImageView imgPreviouseTick = null;
    CircleImageView imgCirclePreviouseProfile = null;

    public AdapterSelectAdvisor(Context mContext, List<StructureSelectAdvisor> mList) {
        this.mList = mList;
        this.mContext = mContext;
    }

    @Override
    public int getItemViewType(int position) {
        if(position == getItemCount() -1)
            return 1;
        else
            return 0;
    }

    @Override
    public AdapterSelectAdvisor.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        ViewHolder innerViewHolder;
        Context context = parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);
        //if(viewType == 0) {
            View view = inflater.inflate(R.layout.layout_select_advisor_list_item, parent, false);
            innerViewHolder = new ViewHolder(view);
        //}
        //else
        //{
            //View view = inflater.inflate(R.layout.layout_select_advisor_list_item, parent, false);
            //innerViewHolder = new ViewHolder(view);
        //}
        return innerViewHolder;
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, final int position) {
        StructureSelectAdvisor structureUserComments = mList.get(position);

        holder.txtHeader.setText(structureUserComments.getStrCommentHeader());
        holder.txtBody.setText(structureUserComments.getStrCommentBody());
        holder.imgProfile.setImageResource(R.drawable.profile_moshaver_test);
        int rate = structureUserComments.getiRate();

        if(rate <= MAXIMUM_RATE_DOTS) {
            for (int i = 0; i < MAXIMUM_RATE_DOTS; i++) {
                if (i < rate)
                {
                    holder.imgRate[i].setImageDrawable(mContext.getResources().getDrawable(R.drawable.dot));
                }
                else
                    holder.imgRate[i].setImageDrawable(mContext.getResources().getDrawable(R.drawable.dot_gray));
            }
        }
    }

    @Override
    public int getItemCount() {
        return mList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{

        CircleImageView imgProfile;
        private ImageView imgRate[] = new ImageView[5];
        ImageView imgTick;
        TextView txtHeader, txtBody;
        LinearLayout lnrParent;

        public ViewHolder(View itemView) {
            super(itemView);

            imgProfile = itemView.findViewById(R.id.img_user_comment_profile);
            imgRate[0] = itemView.findViewById(R.id.img_user_comment_1);
            imgRate[1] = itemView.findViewById(R.id.img_user_comment_2);
            imgRate[2] = itemView.findViewById(R.id.img_user_comment_3);
            imgRate[3] = itemView.findViewById(R.id.img_user_comment_4);
            imgRate[4] = itemView.findViewById(R.id.img_user_comment_5);

            txtHeader = itemView.findViewById(R.id.txt_user_comments_list_item_name);
            txtBody = itemView.findViewById(R.id.txt_user_comments_list_item_body);

            imgTick = itemView.findViewById(R.id.img_select_tick);

            //lnrParent = itemView.findViewById(R.id.lr_select_advisor_parent);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if(imgPreviouseTick != null)
                        imgPreviouseTick.setImageResource(R.drawable.select_advisor_white_black);
                    if(imgCirclePreviouseProfile != null)
                        imgCirclePreviouseProfile.setBorderColor(mContext.getResources().getColor(R.color.black));

                    imgPreviouseTick = imgTick;
                    imgCirclePreviouseProfile = imgProfile;

                    imgTick.setImageResource(R.drawable.select_advisor_white_orange_tik);
                    imgProfile.setBorderColor(mContext.getResources().getColor(R.color.mycolor_yellow));

//                    if (selectedItems.get(getAdapterPosition(), false)) {
//                        selectedItems.delete(getAdapterPosition());
//                        view.setSelected(false);
//                        imgTick.setImageResource(R.drawable.select_advisor_white_black);
//                        imgProfile.setBorderColor(mContext.getResources().getColor(R.color.black));
//                    }
//                    else {
//                        selectedItems.put(getAdapterPosition(), true);
//                        view.setSelected(true);
//                        imgTick.setImageResource(R.drawable.select_advisor_white_orange_tik);
//                        imgProfile.setBorderColor(mContext.getResources().getColor(R.color.mycolor_yellow));
//                    }
                }
            });
        }
    }
}
