package com.example.proyecto_base;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ViewFlipper;

import androidx.appcompat.app.AppCompatActivity;

public class MenuActivity extends AppCompatActivity {

    private ViewFlipper viewFlipper;
    private int[] images = {R.drawable.image1, R.drawable.image2, R.drawable.image3};
    private Button btnAgregar, btnLeer, btnInfom;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);

        btnAgregar = findViewById(R.id.btnInfo);
        btnLeer = findViewById(R.id.btnLeer);
        btnInfom = findViewById(R.id.btnInfom);

        btnAgregar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MenuActivity.this, HongosActivity.class);
                startActivity(intent);
            }
        });

        btnLeer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MenuActivity.this, ListViewActivity.class);
                startActivity(intent);
            }
        });
        btnInfom.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MenuActivity.this, FormularioActivity.class);
                startActivity(intent);
            }
        });
        viewFlipper = findViewById(R.id.slider);

        // Iteramos las imágenes
        for (int image : images) {
            flipperImages(image);
        }
    }

    // Función para agregar imágenes al ViewFlipper
    private void flipperImages(int image) {
        ImageView imageView = new ImageView(this);
        imageView.setBackgroundResource(image); // Colocamos la imagen en el ImageView

        viewFlipper.addView(imageView); // Añadimos el ImageView al ViewFlipper
        viewFlipper.setFlipInterval(2000); // Tiempo de intervalo entre imágenes (en milisegundos)
        viewFlipper.setAutoStart(true); // Inicia automáticamente el slider

        // Opcional: Configurar la animación de entrada y salida
        viewFlipper.setInAnimation(this, android.R.anim.slide_in_left);
        viewFlipper.setOutAnimation(this, android.R.anim.slide_out_right);
    }
}
