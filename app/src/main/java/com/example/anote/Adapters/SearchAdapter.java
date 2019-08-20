package com.example.anote.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.anote.FontUtils;
import com.example.anote.Objects.HandNote;
import com.example.anote.R;

import java.util.ArrayList;
import java.util.Locale;

public class SearchAdapter extends RecyclerView.Adapter<SearchAdapter.myViewHolder> {

    private ArrayList<HandNote> dataUsage, dataFeed;
    private Context context;

    public SearchAdapter(Context context, ArrayList<HandNote> dataFeed) {
        this.dataFeed = dataFeed;
        this.context = context;
        this.dataUsage = new ArrayList<HandNote>();
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
        myViewHolder.name.setTypeface(FontUtils.getIranSans(context));
        myViewHolder.teacher_name.setText(object.getTeacher_name());
        myViewHolder.teacher_name.setTypeface(FontUtils.getIranSansLight(context));
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

        myViewHolder(View itemView) {
            super(itemView);
            this.name = itemView.findViewById(R.id.search_list_item_name_txt);
            this.teacher_name = itemView.findViewById(R.id.search_list_item_type_txt);
        }

    }
}
