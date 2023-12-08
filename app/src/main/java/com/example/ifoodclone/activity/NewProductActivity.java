package com.example.ifoodclone.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.os.Bundle;

import com.example.ifoodclone.R;

public class NewProductActivity extends AppCompatActivity {
    private Toolbar toolbar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_product);
        findViewsById();
        toolbarConfig();
    }
    private void findViewsById(){
        toolbar = findViewById(R.id.toolbar);
    }

    private void toolbarConfig(){
        toolbar.setTitle("New Product");
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }
}