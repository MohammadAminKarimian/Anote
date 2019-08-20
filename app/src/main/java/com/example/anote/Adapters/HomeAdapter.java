package com.example.anote.Adapters;

import android.content.Context;
import android.graphics.Typeface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.anote.FontUtils;
import com.example.anote.Objects.HandNote;
import com.example.anote.R;

import java.util.ArrayList;
import java.util.Random;

public class HomeAdapter extends RecyclerView.Adapter<HomeAdapter.myViewHolder> {
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
        int[] androidColors = context.getResources().getIntArray(R.array.androidcolors);
        int randomAndroidColor = androidColors[new Random().nextInt(androidColors.length)];
        myViewHolder.name.setText(object.getName());
        myViewHolder.name.setTypeface(FontUtils.getIranSans(context));
        myViewHolder.teacher_name.setText(object.getTeacher_name());
        myViewHolder.teacher_name.setTypeface(FontUtils.getIranSansLight(context));
        myViewHolder.image.setImageResource(object.getDrawable());
        myViewHolder.image.setColorFilter(randomAndroidColor);
        myViewHolder.cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (mOnItemClickListener != null)
                    mOnItemClickListener.onItemClick(view, object, i);
            }
        });
    }


    @Override
    public int getItemCount() {
        return handNotes.size();
    }

    static class myViewHolder extends RecyclerView.ViewHolder {

        Typeface nameTypeFace;
        View cardView;
        View view;
        //        Chip university;
        TextView name, teacher_name;
        ImageView image;

        myViewHolder(View itemView) {
            super(itemView);

            this.name = itemView.findViewById(R.id.home_list_item_name);
            this.teacher_name = itemView.findViewById(R.id.home_list_item_teacher_name);
            this.image = itemView.findViewById(R.id.home_list_item_Image);
            this.view = itemView.findViewById(R.id.home_list_item);
            this.cardView = itemView.findViewById(R.id.parent_view);
        }

    }
}
