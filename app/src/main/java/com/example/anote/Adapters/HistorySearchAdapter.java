package com.example.anote.Adapters;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.anote.R;

import java.util.ArrayList;

public class HistorySearchAdapter extends RecyclerView.Adapter<HistorySearchAdapter.myViewHolder> {

    private ArrayList<String> historyUsage;
    private Context context;

    public HistorySearchAdapter(Context context, ArrayList<String> historyFeed) {
        this.context = context;
        this.historyUsage = new ArrayList<String>();
        this.historyUsage.addAll(historyFeed);
    }

    @NonNull
    @Override
    public myViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(context).inflate(R.layout.search_history_list_item, viewGroup, false);
        return new myViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull myViewHolder myViewHolder, int i) {
        String string = historyUsage.get(i);
        myViewHolder.name.setText(string);
    }

    @Override
    public int getItemCount() {
        return historyUsage.size();
    }

    static class myViewHolder extends RecyclerView.ViewHolder {

        TextView name;

        myViewHolder(View itemView){
            super(itemView);
            this.name = itemView.findViewById(R.id.search_history_list_item_name);
        }

    }

    public void filter(String s){
        historyUsage.add(s);
        notifyDataSetChanged();
    }
}
