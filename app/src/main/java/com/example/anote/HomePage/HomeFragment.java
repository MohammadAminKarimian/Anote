package com.example.anote.HomePage;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.anote.Adapters.HomeAdapter;
import com.example.anote.HandNotePageActivity;
import com.example.anote.HomePage.AddPage.AddPage;
import com.example.anote.MainActivity;
import com.example.anote.Objects.HandNote;
import com.example.anote.R;


public class HomeFragment extends Fragment {
    public HomeFragment() {}


    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_home, container, false);

        RecyclerView inReadingRV = view.findViewById(R.id.list_in_reading);
        RecyclerView latestHandnotesRV = view.findViewById(R.id.list_last_handnotes);
        RecyclerView publicLessonsRV = view.findViewById(R.id.list_public_lessons);

        HomeAdapter homeAdapter = new HomeAdapter(container.getContext(), MainActivity.getHandNotes());

        homeAdapter.setOnItemClickListener(new HomeAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(View view, HandNote obj, int position) {
//                Toast.makeText(getContext(), "item clicked", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(getActivity(), HandNotePageActivity.class);
                intent.putExtra("name", obj.getName());
                intent.putExtra("teacher_name", obj.getTeacher_name());
                intent.putExtra("university", obj.getUniversity());
                intent.putExtra("drawable", obj.getDrawable());
                startActivity(intent);
            }
        });

        inReadingRV.setHasFixedSize(true);
        inReadingRV.setLayoutManager(new LinearLayoutManager(container.getContext(), LinearLayoutManager.HORIZONTAL, false));
        inReadingRV.setAdapter(homeAdapter);

        latestHandnotesRV.setHasFixedSize(true);
        latestHandnotesRV.setLayoutManager(new LinearLayoutManager(container.getContext(), LinearLayoutManager.HORIZONTAL, false));
        latestHandnotesRV.setAdapter(homeAdapter);

        publicLessonsRV.setHasFixedSize(true);
        publicLessonsRV.setLayoutManager(new LinearLayoutManager(container.getContext(), LinearLayoutManager.HORIZONTAL, false));
        publicLessonsRV.setAdapter(homeAdapter);

        View floatButton = view.findViewById(R.id.home_float_button);
        floatButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent mIntent = new Intent(v.getContext(), AddPage.class);
                v.getContext().startActivity(mIntent);
            }
        });

        initToolbar(view);

        return view;
    }


    private void initToolbar(View view){
        AppCompatActivity parent = (AppCompatActivity) getActivity();
        Toolbar toolbar = view.findViewById(R.id.home_toolbar);
        parent.setSupportActionBar(toolbar);
        parent.getSupportActionBar().setTitle("ANOTE");

    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        ColorDrawable colorDrawable=new ColorDrawable();
    }
}
