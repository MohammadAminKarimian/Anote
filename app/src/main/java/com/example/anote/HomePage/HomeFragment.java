package com.example.anote.HomePage;

import android.content.Context;
import android.content.Intent;
import android.graphics.Typeface;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import androidx.annotation.NonNull;
import com.google.android.material.snackbar.Snackbar;
import androidx.fragment.app.Fragment;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.appcompat.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.anote.Adapters.HomeAdapter;
import com.example.anote.HandNotePageActivity;
import com.example.anote.HomePage.AddPage.AddPage;
import com.example.anote.MainActivity;
import com.example.anote.Objects.HandNote;
import com.example.anote.R;


public class HomeFragment extends Fragment {
    public HomeFragment() {
    }

    Typeface typeface,typefaceMedium;
    TextView inReading, lastHandNotes, public_lessons,inReading_more, lastHandNotes_more,
            public_lessons_more;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_home, container, false);

        typeface = Typeface.createFromAsset(getContext().getAssets(),
                "fonts/IRANSansMobile(FaNum).ttf");
        typefaceMedium = Typeface.createFromAsset(getContext().getAssets(),
                "fonts/IRANSansMobile(FaNum)_Medium.ttf");
        inReading=view.findViewById(R.id.in_reading);
        inReading.setTypeface(typeface);
        lastHandNotes=view.findViewById(R.id.last_handnotes);
        lastHandNotes.setTypeface(typeface);
        public_lessons=view.findViewById(R.id.public_lessons);
        public_lessons.setTypeface(typeface);

        inReading_more=view.findViewById(R.id.in_reading_more);
        inReading_more.setTypeface(typefaceMedium);
        lastHandNotes_more=view.findViewById(R.id.last_handnotes_more);
        lastHandNotes_more.setTypeface(typefaceMedium);
        public_lessons_more=view.findViewById(R.id.public_lessons_more);
        public_lessons_more.setTypeface(typefaceMedium);


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
                Snackbar.make(parent_view, "FAB Add clicked", Snackbar.LENGTH_LONG).show();
                return false;
            }
        });

//        initToolbar(view);

        return view;
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
