package com.example.anote.HomePage;

import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.anote.Adapters.HomeAdapter;
import com.example.anote.FontUtils;
import com.example.anote.HandNotePageActivity;
import com.example.anote.HomePage.AddPage.AddPage;
import com.example.anote.MainActivity;
import com.example.anote.Objects.HandNote;
import com.example.anote.R;
import com.google.android.material.snackbar.Snackbar;


public class HomeFragment extends Fragment {
    public HomeFragment() {
    }


    private TextView inReading, lastHandNotes, public_lessons, inReading_more, lastHandNotes_more,
            public_lessons_more;
    private RecyclerView inReadingRV, latestHandnotesRV, publicLessonsRV;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_home, container, false);
        initComponent(view);
        setTypeFace();

        HomeAdapter homeAdapter = new HomeAdapter(container.getContext(), MainActivity.getHandNotes());

        homeAdapter.setOnItemClickListener(new HomeAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(View view, HandNote obj, int position) {
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

        final View parent_view = view.findViewById(R.id.coordinator_lyt);
        View floatButton = view.findViewById(R.id.home_float_button);
        floatButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent mIntent = new Intent(v.getContext(), AddPage.class);
                v.getContext().startActivity(mIntent);
            }
        });

        floatButton.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View view) {
                Snackbar.make(parent_view, "افزودن جزوه جدید به آنوت", Snackbar.LENGTH_LONG).show();
                return false;
            }
        });

//        initToolbar(view);

        return view;
    }

    private void setTypeFace() {
        inReading.setTypeface(FontUtils.getIranSans(getActivity()));
        lastHandNotes.setTypeface(FontUtils.getIranSans(getActivity()));
        public_lessons.setTypeface(FontUtils.getIranSans(getActivity()));
        inReading_more.setTypeface(FontUtils.getIranSans(getActivity()));
        lastHandNotes_more.setTypeface(FontUtils.getIranSans(getActivity()));
        public_lessons_more.setTypeface(FontUtils.getIranSans(getActivity()));
    }

    private void initComponent(View view) {
        inReading = view.findViewById(R.id.in_reading);
        lastHandNotes = view.findViewById(R.id.last_handnotes);
        public_lessons = view.findViewById(R.id.public_lessons);
        inReading_more = view.findViewById(R.id.in_reading_more);
        lastHandNotes_more = view.findViewById(R.id.last_handnotes_more);
        public_lessons_more = view.findViewById(R.id.public_lessons_more);
        inReadingRV = view.findViewById(R.id.list_in_reading);
        latestHandnotesRV = view.findViewById(R.id.list_last_handnotes);
        publicLessonsRV = view.findViewById(R.id.list_public_lessons);
    }


    private void initToolbar(View view) {
        AppCompatActivity parent = (AppCompatActivity) getActivity();
        Toolbar toolbar = view.findViewById(R.id.home_toolbar);
        parent.setSupportActionBar(toolbar);
        parent.getSupportActionBar().setTitle("ANOTE");
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        ColorDrawable colorDrawable = new ColorDrawable();
    }
}
