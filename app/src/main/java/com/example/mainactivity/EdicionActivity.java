package com.example.mainactivity;

import android.os.Bundle;
import android.view.View;
import android.widget.*;
import androidx.appcompat.app.AppCompatActivity;

public class EdicionActivity extends AppCompatActivity {

    EditText edtNroIdE, edtNombreE, edtNacionalidadE;
    Spinner spnGeneroE;
    Button btnActualizar, btnEliminarE;
    ArtistaController artistaController;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edicion);

        edtNroIdE = findViewById(R.id.edtNroIdE);
        edtNombreE = findViewById(R.id.edtNombreE);
        edtNacionalidadE = findViewById(R.id.edtNacionalidadE);
        spnGeneroE = findViewById(R.id.spnGeneroE);
        btnActualizar = findViewById(R.id.btnActualizar);
        btnEliminarE = findViewById(R.id.btnEliminarE);

        artistaController = new ArtistaController(this);

        String[] generos = {"Terror", "Comedia", "Accion", "Drama"};
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, generos);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spnGeneroE.setAdapter(adapter);

        edtNroIdE.setText(getIntent().getStringExtra("nroId"));
        edtNombreE.setText(getIntent().getStringExtra("nombre"));
        edtNacionalidadE.setText(getIntent().getStringExtra("nacionalidad"));
        String genero = getIntent().getStringExtra("genero");
        spnGeneroE.setSelection(adapter.getPosition(genero));

        btnActualizar.setOnClickListener(v -> actualizar());
        btnEliminarE.setOnClickListener(v -> eliminar());
    }

    private void actualizar() {
        Artista a = new Artista(
                edtNroIdE.getText().toString(),
                edtNombreE.getText().toString(),
                edtNacionalidadE.getText().toString(),
                spnGeneroE.getSelectedItem().toString()
        );
        artistaController.actualizarArtista(a);
        Toast.makeText(this, "Artista actualizado", Toast.LENGTH_LONG).show();
        finish();
    }

    private void eliminar() {
        artistaController.eliminarArtista(edtNroIdE.getText().toString());
        Toast.makeText(this, "Artista eliminado", Toast.LENGTH_LONG).show();
        finish();
    }
}