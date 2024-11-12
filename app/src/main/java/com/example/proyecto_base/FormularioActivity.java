package com.example.proyecto_base;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import Models.Coleccion;

public class FormularioActivity extends AppCompatActivity {

    private Spinner spnHongos;
    private TextView tvDescripcion, tvLocalizacion, tvEstado;
    private ImageView imgHongo;
    private Coleccion coleccion;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_formulario);

        // Inicializar vistas
        spnHongos = findViewById(R.id.spnHongos);
        tvDescripcion = findViewById(R.id.tvDescripcion);
        tvLocalizacion = findViewById(R.id.tvLocalizacion);
        tvEstado = findViewById(R.id.tvEstado);
        imgHongo = findViewById(R.id.imgHongo);

        // Inicializar la instancia de Coleccion
        coleccion = new Coleccion();

        // Configurar el Spinner de Hongos
        ArrayAdapter<String> adaptadorHongos = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, coleccion.getTipoHongos());
        adaptadorHongos.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spnHongos.setAdapter(adaptadorHongos);

        // Listener para actualizar los TextView y la imagen cuando se selecciona un hongo
        spnHongos.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long l) {
                String hongoSeleccionado = parent.getItemAtPosition(position).toString();

                // Establecer la descripción, localización y estado del hongo en los TextView
                tvDescripcion.setText(coleccion.obtenerDescripcionHongo(hongoSeleccionado));
                tvLocalizacion.setText("Localización: " + coleccion.obtenerLocalizacionHongo(hongoSeleccionado));
                tvEstado.setText("Estado: " + coleccion.obtenerEstadoHongo(hongoSeleccionado));

                int imgResId = obtenerImagenHongo(hongoSeleccionado);
                imgHongo.setImageResource(imgResId);
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {
                // Mensaje por defecto si no se selecciona nada
                tvDescripcion.setText("Seleccione un tipo de hongo.");
                tvLocalizacion.setText("");
                tvEstado.setText("");
                imgHongo.setImageResource(0);
            }
        });
    }

    // Método para obtener la imagen del hongo según el tipo
    private int obtenerImagenHongo(String tipoHongo) {
        switch (tipoHongo) {
            case "Basidiomycota":
                return R.drawable.img1;
            case "Ascomycota":
                return R.drawable.img4;
            case "Zygomycota":
                return R.drawable.img2;
            case "Chytridiomycota":
                return R.drawable.img3;
            default:
                return R.drawable.placeholder;
        }
    }
}
