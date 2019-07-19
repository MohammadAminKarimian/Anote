package com.example.anote.SearchPage;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.anote.Objects.HandNote;
import com.example.anote.R;

import java.util.ArrayList;
import java.util.Locale;

public class SearchAdapter extends RecyclerView.Adapter<SearchAdapter.myViewHolder>  {

    private ArrayList<HandNote> dataUsage, dataFeed;
    private Context context;

    SearchAdapter(Context context, ArrayList<HandNote> dataUsage){
        this.dataFeed = dataUsage;
        this.context = context;
        this.dataUsage = new ArrayList<HandNote>();
//        this.dataUsage.addAll(dataFeed);
    }

    @NonNull
    @Override
    public myViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(context).inflate(R.layout.search_suggestion_list_item, viewGroup, false);
        return new myViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull myViewHolder myViewHolder, int i) {
        HandNote object = dataUsage.get(i);
        myViewHolder.name.setText(object.getName());
        myViewHolder.teacher_name.setText(object.getTeacher_name());
    }

    @Override
    public int getItemCount() {
        return dataUsage.size();
    }

    void filter(String charText, LinearLayout listOfSuggestions) {
        charText = charText.toLowerCase(Locale.getDefault());
        dataUsage.clear();
        if (charText.length() != 0) {
            listOfSuggestions.setVisibility(View.VISIBLE);
            for (HandNote handnote : dataFeed) {
                if (handnote.getName().toLowerCase(Locale.getDefault()).contains(charText)) {
                    dataUsage.add(handnote);
                }
            }
        }
        notifyDataSetChanged();
    }

    static class myViewHolder extends RecyclerView.ViewHolder {

        TextView name, teacher_name;

        myViewHolder(View itemView){
            super(itemView);
            this.name = itemView.findViewById(R.id.search_list_item_name_txt);
            this.teacher_name = itemView.findViewById(R.id.search_list_item_type_txt);
        }

    }
}
