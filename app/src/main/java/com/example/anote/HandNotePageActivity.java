package com.example.anote;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.anote.Objects.HandNote;

public class HandNotePageActivity extends AppCompatActivity {

    TextView name, teacher_name, university;
    ImageView drawable;

    HandNote handNote;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hand_note_page);

        Intent intent = getIntent();

        handNote = new HandNote(
                intent.getStringExtra("name"),
                intent.getStringExtra("teacher_name"),
                intent.getStringExtra("university"),
                intent.getIntExtra("drawable", R.color.colorPrimaryLight)
        );

        initComponent();
        initToolbar();

    }

    private void initComponent(){
        name = findViewById(R.id.handnote_page_name_tv);
        teacher_name = findViewById(R.id.handnote_page_teacher_name_tv);
        university = findViewById(R.id.handnote_page_university_tv);
        drawable = findViewById(R.id.handnote_page_drawable_iv);

        name.setText(handNote.getName());
        teacher_name.setText(handNote.getTeacher_name());
        university.setText(handNote.getUniversity());
        drawable.setImageResource(handNote.getDrawable());
    }

    private void initToolbar(){
        Toolbar toolbar = (Toolbar) findViewById(R.id.handnote_page_toolbar);
        this.setSupportActionBar(toolbar);
        getSupportActionBar().setTitle(null);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeAsUpIndicator(R.drawable.ic_sharp_arrow_forward_24px);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.toolbar_menu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch(item.getItemId()){
            case R.id.menu_favorite_button:
                Toast.makeText(this, "favorite", Toast.LENGTH_SHORT).show();
            case R.id.menu_share_button:
                Toast.makeText(this, "share", Toast.LENGTH_SHORT).show();
            default:
                return super.onOptionsItemSelected(item);
        }
    }
}
