package Adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import ir.andishehlab.asanfit.R;

/**
 * Created by N550J on 1/6/2018.
 */

public class AdapterDiet extends RecyclerView.Adapter<AdapterDiet.ViewHolder> {

    private List<String> strList = new ArrayList<String>();
    private Context context;

    public AdapterDiet(List<String> strList, Context context) {
        this.strList = strList;
        this.context = context;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        ViewHolder viewHolder;
        Context context = parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.layout_diet_list_item, parent, false);
        viewHolder = new ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        String strHolder = strList.get(position);
        holder.textView.setText(strHolder);
    }

    @Override
    public int getItemCount() {
        return strList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{

        TextView textView;
        public ViewHolder(View itemView) {
            super(itemView);

            textView = itemView.findViewById(R.id.txt_diet_text);
        }
    }
}
