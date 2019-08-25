package com.example.anote.HomePage.AddPage;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.example.anote.FontUtils;
import com.example.anote.HomePage.AddPage.SelectLists.AddPageSearchList;
import com.example.anote.R;

public class AddPage extends AppCompatActivity {

    View lyt_field, lyt_lesson, lyt_teacher, lyt_university;
    TextView selected_field, selected_lesson, selected_teacher, selected_university,
            uploadFile, university, field, lessen, teacher, type_title;
    RadioGroup radioGroup;
    RadioButton general_lesson, private_lesson;
    Button uploadFileBtn, sendBtn;
    EditText name_input;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_page);

        initToolbar();
        initialization();

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent intent) {
        if (resultCode != 0) {
            switch (requestCode) {
                case 1:
                    selected_field.setText(intent.getStringExtra("name"));
                    break;
                case 2:
                    selected_lesson.setText(intent.getStringExtra("name"));
                    break;
                case 3:
                    selected_teacher.setText(intent.getStringExtra("name"));
                    break;
                case 4:
                    selected_university.setText(intent.getStringExtra("name"));
                    break;
                case Activity.RESULT_CANCELED:

            }
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

    private void initialization() {
        uploadFile = findViewById(R.id.add_page_upload_file_tv);
        uploadFile.setTypeface(FontUtils.getIranSans(getApplication()));
        uploadFileBtn = findViewById(R.id.add_page_upload_file_btn);
        uploadFileBtn.setTypeface(FontUtils.getIranSansMedium(getApplication()));
        name_input = findViewById(R.id.add_page_name_input_et);
        name_input.setTypeface(FontUtils.getIranSans(getApplication()));
        university = findViewById(R.id.add_page_university_tv);
        university.setTypeface(FontUtils.getIranSans(getApplication()));
        selected_university = findViewById(R.id.add_page_selected_university);
        selected_university.setTypeface(FontUtils.getIranSansLight(getApplication()));
        lessen = findViewById(R.id.add_page_lesson_tv);
        lessen.setTypeface(FontUtils.getIranSans(getApplication()));
        selected_lesson = findViewById(R.id.add_page_selected_lesson);
        selected_lesson.setTypeface(FontUtils.getIranSansLight(getApplication()));
        teacher = findViewById(R.id.add_page_teacher_tv);
        teacher.setTypeface(FontUtils.getIranSans(getApplication()));
        selected_teacher = findViewById(R.id.add_page_selected_teacher);
        selected_teacher.setTypeface(FontUtils.getIranSansLight(getApplication()));
//        type_title.findViewById(R.id.add_page_type_title);
//        type_title.setTypeface(FontUtils.getIranSansMedium(getApplication()));
        radioGroup=findViewById(R.id.add_page_type_radio_group);
        general_lesson=findViewById(R.id.add_page_general_lesson_rg);
        general_lesson.setTypeface(FontUtils.getIranSans(getApplication()));
        private_lesson=findViewById(R.id.add_page_private_lesson_rg);
        private_lesson.setTypeface(FontUtils.getIranSans(getApplication()));
        field = findViewById(R.id.add_page_field_tv);
        field.setTypeface(FontUtils.getIranSans(getApplication()));
        selected_field = findViewById(R.id.add_page_selected_field);
        selected_field.setTypeface(FontUtils.getIranSansLight(getApplication()));
        sendBtn = findViewById(R.id.add_page_send_btn);
        sendBtn.setTypeface(FontUtils.getIranSansMedium(getApplication()));
        lyt_field = findViewById(R.id.add_page_lyt_field);
        lyt_lesson = findViewById(R.id.add_page_lyt_lesson);
        lyt_teacher = findViewById(R.id.add_page_lyt_teacher);
        lyt_university = findViewById(R.id.add_page_lyt_university);
        lyt_field.setOnClickListener(new View.OnClickListener() {
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

    private void initToolbar() {
        Toolbar toolbar = findViewById(R.id.add_page_toolbar);
//        toolbar.setNavigationIcon(R.drawable.ic_round_more_vert_24px);
//        toolbar.setBackgroundColor(getResources().getColor(R.color.colorPrimary));
        setSupportActionBar(toolbar);
//        parent.getSupportActionBar().setTitle(R.string.app_name_second);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setElevation(4);
    }
}
