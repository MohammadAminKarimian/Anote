package com.example.anote.CategoryPage;

import android.content.Intent;
import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.appcompat.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.anote.Adapters.CategoryAdapter;
import com.example.anote.CategoryPage.HandNoteList.HandNoteList;
import com.example.anote.R;

import java.util.ArrayList;


public class CategoryFragment extends Fragment {

    ArrayList<String> lessons = new ArrayList<>();

    public CategoryFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_category, container, false);

        fetchData();
        CategoryAdapter adapter = initComponent(view);
        return view;
    }


    private CategoryAdapter initComponent(View view){
        AppCompatActivity parent = (AppCompatActivity) getActivity();
        RecyclerView rv = view.findViewById(R.id.category_recycler_view);
        CategoryAdapter adapter = new CategoryAdapter(getContext(), lessons);
        rv.setHasFixedSize(true);
        rv.setLayoutManager(new LinearLayoutManager(getContext()));
        rv.setAdapter(adapter);

        adapter.setOnItemClickListener(new CategoryAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(View view, String str, int position) {
                Intent intent = new Intent(getContext(), HandNoteList.class);
                startActivity(intent);
            }
        });
        return adapter;
    }

    private void fetchData(){
        lessons.add("معلومات به درد نخور");
        lessons.add("ریاضی 2");
        lessons.add("علوم ریاضی");
        lessons.add("فیزیک مسخره 3");
    }

}
