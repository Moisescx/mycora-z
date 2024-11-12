package com.example.proyecto_base;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import com.google.firebase.auth.FirebaseAuth;

public class RegistroActivity extends AppCompatActivity {

    // Crear Variables
    public EditText Nombre, Correo, Contrasena;
    public Button RegistrarUsuarioBTN, IrAlLoginBTN;
    private FirebaseAuth mAuth;  // Instancia de FirebaseAuth


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_registro);

        // Inicializar variables
        Nombre = findViewById(R.id.etNombre1);
        Correo = findViewById(R.id.etcDesc1);
        Contrasena = findViewById(R.id.etPass1);

        RegistrarUsuarioBTN = findViewById(R.id.btnAgre1);
        IrAlLoginBTN = findViewById(R.id.btnIrAlLogin);
        mAuth = FirebaseAuth.getInstance();


        IrAlLoginBTN.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(RegistroActivity.this, Login_act.class);
                startActivity(intent);
                finish(); // Finaliza la actividad de registro
            }
        });

        // Acción del botón de registrar
        RegistrarUsuarioBTN.setOnClickListener(view -> {

            // Obtener los datos ingresados por el usuario
            String nombre = Nombre.getText().toString().trim();
            String correo = Correo.getText().toString().trim();
            String password = Contrasena.getText().toString().trim();

            // Verificar que los campos no estén vacíos
            if (!nombre.isEmpty() && !correo.isEmpty() && !password.isEmpty()) {
                // Llamar al método de registro
                registrarUsuario(correo, password);
            } else {
                Toast.makeText(RegistroActivity.this, "Por favor, complete todos los campos", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void registrarUsuario(final String correo, final String password) {
        // Registrar usuario con Firebase
        mAuth.createUserWithEmailAndPassword(correo, password)
                .addOnCompleteListener(this, task -> {
                    if (task.isSuccessful()) {
                        // Si el registro es exitoso, se muestra un mensaje y se redirige al login
                        Toast.makeText(RegistroActivity.this, "Usuario registrado exitosamente", Toast.LENGTH_SHORT).show();

                        // Redirigir al login
                        Intent intent = new Intent(RegistroActivity.this, Login_act.class);
                        startActivity(intent);
                        finish(); // Finalizar la actividad de registro
                    } else {
                        // Si el registro falla, se muestra el mensaje de error
                        Toast.makeText(RegistroActivity.this, "Error en el registro: " + task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                    }
                });
    }

}



