package com.example.proyecto_base;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class Login_act extends AppCompatActivity {

    private EditText usuario, contrasena;
    private Button btn_login, irAlRegistroBtn;
    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);


        mAuth = FirebaseAuth.getInstance();

        usuario = findViewById(R.id.et_User);
        contrasena = findViewById(R.id.et_Pass);
        btn_login = findViewById(R.id.btn_Login);
        irAlRegistroBtn = findViewById(R.id.btnIrAlRegistro);

        btn_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String email = usuario.getText().toString().trim();
                String password = contrasena.getText().toString().trim();

                if (TextUtils.isEmpty(email) || TextUtils.isEmpty(password)) {
                    Toast.makeText(Login_act.this, "Por favor rellene los campos.", Toast.LENGTH_SHORT).show();
                    return;
                }

                mAuth.signInWithEmailAndPassword(email, password)
                        .addOnCompleteListener(Login_act.this, task -> {
                            if (task.isSuccessful()) {
                                FirebaseUser user = mAuth.getCurrentUser();
                                Toast.makeText(Login_act.this, "Bienvenido, " + user.getEmail(), Toast.LENGTH_SHORT).show();

                                Intent intent = new Intent(Login_act.this, MenuActivity.class);
                                startActivity(intent);
                                finish();
                            } else {
                                Toast.makeText(Login_act.this, "Error de autenticación: " + task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                            }
                        });
            }
        });

        irAlRegistroBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Login_act.this, RegistroActivity.class);
                startActivity(intent);  // Iniciar la actividad de registro
            }
        });
    }

    @Override
    protected void onStart() {
        super.onStart();
        FirebaseUser currentUser = mAuth.getCurrentUser();
        if (currentUser != null) {
            Intent intent = new Intent(Login_act.this, MenuActivity.class);
            startActivity(intent);
            finish();
        }
    }
}
