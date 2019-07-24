package com.example.anote.Adapters;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.view.menu.MenuView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.anote.R;

import java.util.ArrayList;
import java.util.Locale;

public class CategoryAdapter extends RecyclerView.Adapter<CategoryAdapter.myViewHolder> {


    private ArrayList<String> dataUsage, dataFeed;
    private Context context;

    public CategoryAdapter(Context context, ArrayList<String> dataFeed){
        this.dataFeed = dataFeed;
        this.context = context;
//        this.dataUsage = new ArrayList<String>();
        this.dataUsage = dataFeed;
    }

    private OnItemClickListener mOnItemClickListener;

    public interface OnItemClickListener {
        void onItemClick(View view, String str, int position);
    }

    public void setOnItemClickListener(final OnItemClickListener mItemClickListener) {
        this.mOnItemClickListener = mItemClickListener;
    }

    @NonNull
    @Override
    public myViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(context).inflate(R.layout.category_list_item, viewGroup, false);
        return new myViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final myViewHolder myViewHolder, int i) {
        final String str = dataUsage.get(i);
        myViewHolder.name.setText(str);
        myViewHolder.drawable.setImageResource(R.color.colorSecondary);

        myViewHolder.lyt_parent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(mOnItemClickListener != null)
                    mOnItemClickListener.onItemClick(view, str, myViewHolder.getAdapterPosition());
            }
        });
    }

    @Override
    public int getItemCount() {
        return dataUsage.size();
    }

    public void filter(String charText, LinearLayout listOfSuggestions) {
        charText = charText.toLowerCase(Locale.getDefault());
        dataUsage.clear();
        if (charText.length() != 0) {
            listOfSuggestions.setVisibility(View.VISIBLE);
            for (String str: dataFeed) {
                if (str.toLowerCase(Locale.getDefault()).contains(charText)) {
                    dataUsage.add(str);
                }
            }
        }
        notifyDataSetChanged();
    }

    static class myViewHolder extends RecyclerView.ViewHolder{

        TextView name;
        ImageView drawable;
        View lyt_parent;

        myViewHolder(View itemView){
            super(itemView);
            name = itemView.findViewById(R.id.category_list_item_TV);
            drawable = itemView.findViewById(R.id.category_list_item_IV);
            lyt_parent = itemView.findViewById(R.id.category_list_item_parentView);
        }

    }
}