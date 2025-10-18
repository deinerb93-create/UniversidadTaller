package com.example.mainactivity;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import android.widget.TextView;


public class ListadoActivity extends AppCompatActivity {

    ListView lstArtistas;
    ArtistaController artistaController;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_listado);

        lstArtistas = findViewById(R.id.lstlistado);
        artistaController = new ArtistaController(this);

        Cursor c = artistaController.allArtistas();
        ArtistaCursorAdapter adapter = new ArtistaCursorAdapter(this, c, false);
        lstArtistas.setAdapter(adapter);

        lstArtistas.setOnItemClickListener((AdapterView<?> parent, android.view.View view, int position, long id) -> {
            TextView txtNroId = view.findViewById(R.id.txtNroId);
            TextView txtNombre = view.findViewById(R.id.txtNombre);
            TextView txtNacionalidad = view.findViewById(R.id.txtNacionalidad);
            TextView txtGenero = view.findViewById(R.id.txtGenero);

            Intent i = new Intent(getApplicationContext(), EdicionActivity.class);
            i.putExtra("nroId", txtNroId.getText().toString());
            i.putExtra("nombre", txtNombre.getText().toString());
            i.putExtra("nacionalidad", txtNacionalidad.getText().toString());
            i.putExtra("genero", txtGenero.getText().toString());
            startActivity(i);
        });
    }
}
