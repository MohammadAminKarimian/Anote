package com.example.anote.CategoryPage.HandNoteList;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.anote.Adapters.CategoryHandNoteListAdapter;
import com.example.anote.MainActivity;
import com.example.anote.R;

public class HandNoteList extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hand_note_list);

        RecyclerView rv = findViewById(R.id.category_handnote_list_recycler_view);
        rv.setHasFixedSize(true);
        rv.setLayoutManager(new LinearLayoutManager(this));

        rv.setAdapter(new CategoryHandNoteListAdapter(this, MainActivity.getHandNotes()));

    }
}
