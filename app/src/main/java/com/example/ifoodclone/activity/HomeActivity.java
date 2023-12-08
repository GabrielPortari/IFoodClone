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

public class HomeActivity extends AppCompatActivity {
    private Toolbar toolbar;
    private RecyclerView recyclerCompanies;
    private FirebaseAuth firebaseAuth;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        firebaseAuth = FirebaseConfiguration.getFirebaseAuth();

        findViewsById();
        toolbarConfig();
    }

    private void findViewsById(){
        toolbar = findViewById(R.id.toolbar);
        recyclerCompanies = findViewById(R.id.recyclerCompanies);
    }
    private void toolbarConfig(){
        toolbar.setTitle("iFood - User");
        setSupportActionBar(toolbar);
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_user, menu);

        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if(item.getItemId() == R.id.menuSearch){
            search();
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
        startActivity(new Intent(HomeActivity.this, CompanyConfigurationsActivity.class));
    }
    private void search(){
        startActivity(new Intent(HomeActivity.this, SearchActivity.class));
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