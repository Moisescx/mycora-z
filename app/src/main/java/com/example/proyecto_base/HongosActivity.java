package com.example.proyecto_base;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import Models.Hongo;


public class HongosActivity extends AppCompatActivity {

    // Crear Variables
    public EditText Nombre, Descripcion, Tipo;
    public Button Agregarbtn;
    private DatabaseReference mDatabase;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_hongos);

        // Inicializar variables
        Nombre = findViewById(R.id.etNombre1);
        Descripcion = findViewById(R.id.etcDesc1);
        Tipo = findViewById(R.id.etTipo1);

        Agregarbtn = findViewById(R.id.btnAgre1);
        mDatabase = FirebaseDatabase.getInstance().getReference("hongos");


        Agregarbtn.setOnClickListener(view -> {

            // Obtener los datos ingresados por el usuario
            String nombre = Nombre.getText().toString().trim();
            String descripcion = Descripcion.getText().toString().trim();
            String tipo = Tipo.getText().toString().trim();

            // Llamar método para agregar datos a Firebase
            if (!nombre.isEmpty() && !descripcion.isEmpty() && !tipo.isEmpty()) {
                agregarHongo(nombre, descripcion, tipo);
            } else {
                Toast.makeText(HongosActivity.this, "Por favor, complete todos los campos", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void agregarHongo(String nombre, String descripcion, String tipo) {

        // Crear un ID único para cada hongo (puedes usar push() para hacerlo automáticamente)
        String hongoId = mDatabase.push().getKey();

        // Crear un objeto Hongo con los datos
        Hongo hongo = new Hongo(nombre, descripcion, tipo);

        // Guardar los datos en Firebase bajo el ID generado
        if (hongoId != null) {
            mDatabase.child(hongoId).setValue(hongo)
                    .addOnSuccessListener(aVoid -> {
                        Toast.makeText(HongosActivity.this, "Hongo agregado correctamente", Toast.LENGTH_SHORT).show();
                    })
                    .addOnFailureListener(e -> {
                        Toast.makeText(HongosActivity.this, "Error al agregar hongo: " + e.getMessage(), Toast.LENGTH_LONG).show();
                    });
        }
    }

}
