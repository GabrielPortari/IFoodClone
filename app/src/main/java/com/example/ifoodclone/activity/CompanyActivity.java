package com.example.ifoodclone.activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;


import com.example.ifoodclone.R;
import com.example.ifoodclone.helper.FirebaseConfiguration;
import com.google.firebase.auth.FirebaseAuth;

public class CompanyActivity extends AppCompatActivity {
    private Toolbar toolbar;
    private RecyclerView recyclerProducts;
    private FirebaseAuth firebaseAuth;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_company);

        firebaseAuth = FirebaseConfiguration.getFirebaseAuth();

        findViewsById();
        toolbarConfig();
    }
    private void findViewsById(){
        toolbar = findViewById(R.id.toolbar);
        recyclerProducts = findViewById(R.id.recyclerProducts);
    }
    private void toolbarConfig(){
        toolbar.setTitle("iFood - Company");
        setSupportActionBar(toolbar);
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_company, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if(item.getItemId() == R.id.menuAddProduct){
            addProduct();
        }
        if(item.getItemId() == R.id.menuConfigurations){
            configurations();
        }
        if(item.getItemId() == R.id.menuLogout){
            logout();
        }
        return super.onOptionsItemSelected(item);
    }

    private void configurations(){
        startActivity(new Intent(CompanyActivity.this, CompanyConfigurationsActivity.class));
    }
    private void addProduct(){
        startActivity(new Intent(CompanyActivity.this, NewProductActivity.class));
    }
    private void logout(){
        try {
            firebaseAuth.signOut();
            finish();
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}