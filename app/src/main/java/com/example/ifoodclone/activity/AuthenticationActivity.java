package com.example.ifoodclone.activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SwitchCompat;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.ifoodclone.R;
import com.example.ifoodclone.helper.ConfigurationFirebase;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthInvalidCredentialsException;
import com.google.firebase.auth.FirebaseAuthUserCollisionException;
import com.google.firebase.auth.FirebaseAuthWeakPasswordException;
import com.google.firebase.auth.FirebaseUser;

public class AuthenticationActivity extends AppCompatActivity {
    private EditText editEmail, editPassword;
    private SwitchCompat switchAuth;
    private Button buttonAccess;
    private FirebaseAuth auth;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_authentication);
        getSupportActionBar().hide();
        findViewsById();

        auth = ConfigurationFirebase.getFirebaseAuth();
        verifyCurrentUser(); //Verifica se há um usuário logado

        buttonAccess.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String email = editEmail.getText().toString();
                String password = editPassword.getText().toString();

                if(!email.isEmpty()){
                    if(!password.isEmpty()){
                        /* Verificar o estado do switch
                           Ativado - Fazer Cadastro
                           Desativado - Fazer login */
                        if(switchAuth.isChecked()){ //CADASTRO
                            auth.createUserWithEmailAndPassword(email, password)
                                    .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                                        @Override
                                        public void onComplete(@NonNull Task<AuthResult> task) {
                                            if(task.isSuccessful()){
                                                Toast.makeText(AuthenticationActivity.this, "Registration completed", Toast.LENGTH_SHORT).show();
                                                openMainActivity();
                                            }else{
                                                String exception;
                                                try {
                                                    throw task.getException();
                                                }catch (FirebaseAuthWeakPasswordException e){
                                                    exception = "Enter a stronger password";
                                                }catch (FirebaseAuthInvalidCredentialsException e){
                                                    exception = "Enter a valid email";
                                                }catch (FirebaseAuthUserCollisionException e){
                                                    exception = "Account already registered";
                                                }catch (Exception e){
                                                    exception = "Registration failed: " + e.getMessage();
                                                    e.printStackTrace();
                                                }
                                                Toast.makeText(AuthenticationActivity.this, exception, Toast.LENGTH_SHORT).show();
                                            }
                                        }
                                    });
                        }else{ //LOGIN
                            auth.signInWithEmailAndPassword(email, password)
                                    .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                                        @Override
                                        public void onComplete(@NonNull Task<AuthResult> task) {
                                            if(task.isSuccessful()){
                                                Toast.makeText(AuthenticationActivity.this, "Sign in completed", Toast.LENGTH_SHORT).show();
                                                openMainActivity();
                                            }else{
                                                Toast.makeText(AuthenticationActivity.this, "Sign in failed, try again later", Toast.LENGTH_SHORT).show();
                                            }
                                        }
                                    });
                        }

                    }else{
                        Toast.makeText(AuthenticationActivity.this, "Password field is empty", Toast.LENGTH_SHORT).show();
                    }
                }else{
                    Toast.makeText(AuthenticationActivity.this, "Email field is empty", Toast.LENGTH_SHORT).show();
                }
            }
        });

    }

    private void verifyCurrentUser(){
        FirebaseUser currentUser = auth.getCurrentUser();
        if(currentUser != null){
            openMainActivity();
        }
    }

    private void openMainActivity(){
        startActivity(new Intent(getApplicationContext(), MainActivity.class));
    }
    private void findViewsById(){
        editEmail = findViewById(R.id.editTextEmail);
        editPassword = findViewById(R.id.editTextPassword);
        switchAuth = findViewById(R.id.switchAuth);
        buttonAccess = findViewById(R.id.buttonAccess);
    }
}