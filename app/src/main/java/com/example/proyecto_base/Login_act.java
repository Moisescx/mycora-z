package com.example.proyecto_base;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;


public class Login_act extends AppCompatActivity {

    private TextView usuario, contrasena;
    private Button btn_login, irAlRegistroBtn;
    private FirebaseAuth mAuth;  // Para manejar la autenticación


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_login);

        mAuth = FirebaseAuth.getInstance();


        usuario = findViewById(R.id.et_User);
        contrasena = findViewById(R.id.et_Pass);
        btn_login = findViewById(R.id.btn_Login);
        irAlRegistroBtn = findViewById(R.id.btnIrAlRegistro);


        btn_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String email = usuario.getText().toString();  // Cambié a email
                String password = contrasena.getText().toString();

                // Validación de campos vacíos
                if (TextUtils.isEmpty(email) || TextUtils.isEmpty(password)) {
                    Toast.makeText(Login_act.this, "Por favor rellene los campos.", Toast.LENGTH_SHORT).show();
                    return;
                }

                // Intentamos iniciar sesión con Firebase Authentication
                mAuth.signInWithEmailAndPassword(email, password)
                        .addOnCompleteListener(Login_act.this, task -> {
                            if (task.isSuccessful()) {
                                // Si la autenticación es exitosa, obtenemos al usuario actual
                                FirebaseUser user = mAuth.getCurrentUser();
                                Toast.makeText(Login_act.this, "Bienvenido, " + user.getEmail(), Toast.LENGTH_SHORT).show();

                                // Redirigimos a la actividad principal (por ejemplo, MenuActivity)
                                Intent intent = new Intent(Login_act.this, MenuActivity.class);
                                startActivity(intent);
                                finish(); // Cerrar la actividad actual para no permitir volver a ella
                            } else {
                                // Si la autenticación falla, mostramos el error
                                Toast.makeText(Login_act.this, "Error de autenticación: " + task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                            }
                        });
            }
        });

        // Acción para ir a la actividad de registro
        irAlRegistroBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Crear un Intent para ir a RegistroActivity
                Intent intent = new Intent(Login_act.this, RegistroActivity.class);
                startActivity(intent);  // Iniciar la actividad de registro
            }
        });
    }

}

