package com.example.anote.HomePage.AddPage;

import android.app.Activity;
import android.content.Intent;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.example.anote.HomePage.AddPage.SelectLists.AddPageSearchList;
import com.example.anote.R;

public class AddPage extends AppCompatActivity {

    View lyt_field, lyt_lesson, lyt_teacher, lyt_university ;
    TextView selected_field, selected_lesson, selected_teacher, selected_university;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_page);

        initToolbar();
        initialization();

//        if(getIntent().getStringExtra("type") != null){
//            switch (getIntent().getStringExtra("type")){
//                case "Field":
//                    selected_field.setText(getIntent().getStringExtra("field_name"));break;
//                case "University":
//                    selected_university.setText(getIntent().getStringExtra("university_name"));break;
//                case "Lesson":
//                    selected_lesson.setText(getIntent().getStringExtra("lesson_name"));break;
//                case "Teacher":
//                    selected_teacher.setText(getIntent().getStringExtra("teacher_name"));break;
//            }
//        }

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent intent) {
        switch(requestCode){
            case 1:
                selected_field.setText(intent.getStringExtra("name"));break;
            case 2:
                selected_lesson.setText(intent.getStringExtra("name"));break;
            case 3:
                selected_teacher.setText(intent.getStringExtra("name"));break;
            case 4:
                selected_university.setText(intent.getStringExtra("name"));break;
            case Activity.RESULT_CANCELED:

        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.empty_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            finish();
        } else {
            Toast.makeText(getApplicationContext(), item.getTitle(), Toast.LENGTH_SHORT).show();
        }
        return super.onOptionsItemSelected(item);
    }

    private void initialization(){
        lyt_field = findViewById(R.id.add_page_lyt_field);
        lyt_lesson = findViewById(R.id.add_page_lyt_lesson);
        lyt_teacher = findViewById(R.id.add_page_lyt_teacher);
        lyt_university = findViewById(R.id.add_page_lyt_university);
        selected_field = findViewById(R.id.add_page_selected_field);
        selected_lesson = findViewById(R.id.add_page_selected_lesson);
        selected_teacher = findViewById(R.id.add_page_selected_teacher);
        selected_university = findViewById(R.id.add_page_selected_university);

        lyt_field.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(view.getContext(), AddPageSearchList.class);
                intent.putExtra("URL", "https://apianote.000webhostapp.com/select.php");
                startActivityForResult(intent, 1);
            }
        });

        lyt_lesson.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(view.getContext(), AddPageSearchList.class);
                intent.putExtra("URL", "https://apianote.000webhostapp.com/select.php");
                startActivityForResult(intent, 2);
            }
        });

        lyt_teacher.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(view.getContext(), AddPageSearchList.class);
                intent.putExtra("URL", "https://apianote.000webhostapp.com/select.php");
                startActivityForResult(intent, 3);
            }
        });

        lyt_university.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(view.getContext(), AddPageSearchList.class);
                intent.putExtra("URL", "https://apianote.000webhostapp.com/select.php");
                startActivityForResult(intent, 4);
            }
        });
    }

    private void initToolbar(){
        Toolbar toolbar = findViewById(R.id.add_page_toolbar);
//        toolbar.setNavigationIcon(R.drawable.ic_round_more_vert_24px);
//        toolbar.setBackgroundColor(getResources().getColor(R.color.colorPrimary));
        setSupportActionBar(toolbar);
//        parent.getSupportActionBar().setTitle(R.string.app_name_second);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setElevation(4);
    }
}
