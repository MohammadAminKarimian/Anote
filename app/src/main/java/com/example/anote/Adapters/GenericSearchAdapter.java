package com.example.anote.Adapters;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;
import com.example.anote.R;

import java.util.ArrayList;
import java.util.Locale;

public class GenericSearchAdapter extends RecyclerView.Adapter<GenericSearchAdapter.myViewHolder> {

    private ArrayList<String> dataFeed, dataUsage;
    private Context context;

    public GenericSearchAdapter(Context context, ArrayList<String> dataFeed){
        this.dataFeed = dataFeed;
        this.context= context;
        this.dataUsage = new ArrayList<>();
    }

    @NonNull
    @Override
    public myViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(context).inflate(R.layout.search_suggestion_list_item, viewGroup, false);
        return new GenericSearchAdapter.myViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull myViewHolder myViewHolder, int i) {
        String str = dataUsage.get(i);
        myViewHolder.name.setText(str);
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
            for (String str: dataFeed) {
                if (str.toLowerCase(Locale.getDefault()).contains(charText)) {
                    dataUsage.add(str);
                }
            }
        }
        notifyDataSetChanged();
    }

    static class myViewHolder extends RecyclerView.ViewHolder {

        TextView name;

        myViewHolder(View itemView){
            super(itemView);
            this.name = itemView.findViewById(R.id.search_generic_list_item_name);
        }

    }

}
