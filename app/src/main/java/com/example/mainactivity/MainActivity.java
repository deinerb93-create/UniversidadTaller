package com.example.mainactivity;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.*;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    EditText edtNroId, edtNombre, edtNacionalidad;
    Spinner spnGenero;
    Button btnRegistrar, btnListado, btnEliminar, btnCancelar;
    ArtistaController artistaController;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        edtNroId = findViewById(R.id.edtNroId);
        edtNombre = findViewById(R.id.edtNombre);
        edtNacionalidad = findViewById(R.id.edtNacionalidad);
        spnGenero = findViewById(R.id.spnGenero);
        btnRegistrar = findViewById(R.id.btnRegistrar);
        btnListado = findViewById(R.id.btnListado);
        btnEliminar = findViewById(R.id.btnEliminar);
        btnCancelar = findViewById(R.id.btnCancelar);

        artistaController = new ArtistaController(this);

        String[] generos = {"Terror", "Comedia", "Accion", "Drama"};
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, generos);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spnGenero.setAdapter(adapter);

        btnRegistrar.setOnClickListener(this);
        btnListado.setOnClickListener(this);
        btnEliminar.setOnClickListener(this);
        btnCancelar.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.btnRegistrar) {
            registrarArtista();
        } else if (v.getId() == R.id.btnListado) {
            Intent i = new Intent(this, ListadoActivity.class);
            startActivity(i);
        } else if (v.getId() == R.id.btnEliminar) {
            eliminarArtista();
        } else if (v.getId() == R.id.btnCancelar) {
            limpiarCampos();
        }
    }

    private void registrarArtista() {
        String nroId = edtNroId.getText().toString();
        String nombre = edtNombre.getText().toString();
        String nacionalidad = edtNacionalidad.getText().toString();
        String genero = spnGenero.getSelectedItem().toString();

        if (TextUtils.isEmpty(nroId) || TextUtils.isEmpty(nombre) || TextUtils.isEmpty(nacionalidad)) {
            Toast.makeText(this, "Ningun campo puede estar vacio", Toast.LENGTH_LONG).show();
        } else {
            Artista artista = new Artista(nroId, nombre, nacionalidad, genero);
            long id = artistaController.agregarArtista(artista);
            if (id > 0) {
                Toast.makeText(this, "Artista registrado correctamente", Toast.LENGTH_LONG).show();
                limpiarCampos();
            } else {
                Toast.makeText(this, "Error al registrar el artista", Toast.LENGTH_LONG).show();
            }
        }
    }

    private void eliminarArtista() {
        String nroId = edtNroId.getText().toString();
        if (TextUtils.isEmpty(nroId)) {
            Toast.makeText(this, "Ingrese el Nro ID del artista a eliminar", Toast.LENGTH_LONG).show();
            return;
        }
        int filas = artistaController.eliminarArtista(nroId);
        if (filas > 0) {
            Toast.makeText(this, "Artista eliminado correctamente", Toast.LENGTH_LONG).show();
            limpiarCampos();
        } else {
            Toast.makeText(this, "No se encontro un artista con ese ID", Toast.LENGTH_LONG).show();
        }
    }

    private void limpiarCampos() {
        edtNroId.setText("");
        edtNombre.setText("");
        edtNacionalidad.setText("");
        spnGenero.setSelection(0);
    }
}
