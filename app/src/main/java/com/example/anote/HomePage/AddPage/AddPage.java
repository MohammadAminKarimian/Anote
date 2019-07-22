package com.example.anote.HomePage.AddPage;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import com.example.anote.HomePage.AddPage.SelectLists.FieldList;
import com.example.anote.R;

public class AddPage extends AppCompatActivity {

    View lyt_field ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_page);
        initToolbar();
        initializtion();
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

    private void initializtion(){
        lyt_field = findViewById(R.id.lyt_field);

        lyt_field.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                startActivity(new Intent(view.getContext(), FieldList.class));
            }
        });
    }

    private void initToolbar(){
        Toolbar toolbar = findViewById(R.id.addpage_toolbar);
//        toolbar.setNavigationIcon(R.drawable.ic_round_more_vert_24px);
//        toolbar.setBackgroundColor(getResources().getColor(R.color.colorPrimary));
        setSupportActionBar(toolbar);
//        parent.getSupportActionBar().setTitle(R.string.app_name_second);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setElevation(4);
    }
}
