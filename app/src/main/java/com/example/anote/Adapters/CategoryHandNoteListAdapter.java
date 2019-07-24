package com.example.anote.Adapters;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.anote.Objects.HandNote;
import com.example.anote.R;

import java.util.ArrayList;

public class CategoryHandNoteListAdapter extends RecyclerView.Adapter<CategoryHandNoteListAdapter.myViewHolder> {

    private ArrayList<HandNote> dataFeed;
    private Context context;

    public CategoryHandNoteListAdapter(Context context, ArrayList<HandNote> dataFeed){
        this.dataFeed = dataFeed;
        this.context = context;
    }

    @NonNull
    @Override
    public myViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(context).inflate(R.layout.category_handnote_list_item, viewGroup, false);
        return new myViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull myViewHolder myViewHolder, int i) {
        HandNote object = dataFeed.get(i);
        myViewHolder.name.setText(object.getName());
        myViewHolder.teacher_name.setText(object.getTeacher_name());
        myViewHolder.university_name.setText(object.getUniversity());
        myViewHolder.drawable.setImageResource(R.color.colorSecondary);
    }

    @Override
    public int getItemCount() {
        return dataFeed.size();
    }

    static class myViewHolder extends RecyclerView.ViewHolder {

        TextView name, teacher_name, university_name;
        ImageView drawable;

        myViewHolder(View itemView){
            super(itemView);
            this.name = itemView.findViewById(R.id.category_handnote_list_item_name_TV);
            this.teacher_name = itemView.findViewById(R.id.category_handnote_list_item_teacher_name_TV);
            this.university_name = itemView.findViewById(R.id.category_handnote_list_item_university_name_TV);
            this.drawable = itemView.findViewById(R.id.category_handnote_list_item_image_IV);

        }

    }

}
