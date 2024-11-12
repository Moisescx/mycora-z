package com.example.proyecto_base;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class ListViewActivity extends AppCompatActivity {

    private ListView listView;
    private ArrayList<String> dataList;
    private ArrayAdapter<String> adapter;
    private DatabaseReference databaseReference;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_view);

        listView = findViewById(R.id.listaHongos);
        dataList = new ArrayList<>();

        // Adapter para mostrar los datos en el ListView
        adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, dataList);
        listView.setAdapter(adapter);

        databaseReference = FirebaseDatabase.getInstance().getReference("hongos");  // "hongos" es el nombre de la rama en Firebase

        // Escuchar cambios en la base de datos
        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                // Limpiar la lista para evitar duplicados
                dataList.clear();

                // Iterar sobre los datos obtenidos de Firebase
                for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                    String nombre = snapshot.child("nombre").getValue(String.class);
                    String descripcion = snapshot.child("descripcion").getValue(String.class);
                    String tipo = snapshot.child("tipo").getValue(String.class);

                    // Agregar los datos al ArrayList
                    dataList.add(nombre + " - " + descripcion + " - " + tipo);
                }

                // Notificar al Adapter que los datos han cambiado
                adapter.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                // Mostrar error en caso de problemas con la base de datos
                Toast.makeText(ListViewActivity.this, "Error al obtener datos: " + databaseError.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }
}
