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

public class HomeAdapter  extends RecyclerView.Adapter<HomeAdapter.myViewHolder>{

    private Context context;
    private ArrayList<HandNote> handNotes;

    private OnItemClickListener mOnItemClickListener;

    public interface OnItemClickListener {
        void onItemClick(View view, HandNote obj, int position);
    }

    public void setOnItemClickListener(final OnItemClickListener mItemClickListener) {
        this.mOnItemClickListener = mItemClickListener;
    }

    public HomeAdapter(Context context, ArrayList<HandNote> handNotes) {
        this.context = context;
        this.handNotes = handNotes;
    }

    @NonNull
    @Override
    public myViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(context).inflate(R.layout.home_list_item, viewGroup, false);
        return new myViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final myViewHolder myViewHolder, final int i) {
        final HandNote object = handNotes.get(i);
        myViewHolder.name.setText(object.getName());
        myViewHolder.teacher_name.setText(object.getTeacher_name());
        myViewHolder.image.setImageResource(object.getDrawable());
        myViewHolder.university.setText(object.getUniversity());

        myViewHolder.view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(mOnItemClickListener != null)
                    mOnItemClickListener.onItemClick(view, object, i);
            }
        });
    }



    @Override
    public int getItemCount() {
        return handNotes.size();
    }

    static class myViewHolder extends RecyclerView.ViewHolder {

        View view;

        TextView name, teacher_name, university;
        ImageView image;

        myViewHolder(View itemView){
            super(itemView);
            this.view = itemView.findViewById(R.id.parent_view);
            this.name = itemView.findViewById(R.id.home_list_item_name);
            this.teacher_name = itemView.findViewById(R.id.home_list_item_teacher_name);
            this.university = itemView.findViewById(R.id.home_list_item_university);
            this.image = itemView.findViewById(R.id.home_list_item_Image);
        }

    }
}
