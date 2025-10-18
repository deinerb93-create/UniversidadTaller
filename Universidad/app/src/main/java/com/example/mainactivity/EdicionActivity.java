package com.example.mainactivity;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class EdicionActivity extends AppCompatActivity {

    EditText codigo, nombre, programa;
    Button actualizar, eliminar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edicion);
        Intent i = getIntent();
        String cod = i.getStringExtra("codigo");
        String nom = i.getStringExtra("nombre");
        String prg = i.getStringExtra("programa");
        codigo = findViewById(R.id.edtcod);
        nombre = findViewById(R.id.edtnom);
        programa = findViewById(R.id.edtprg);
        actualizar = findViewById(R.id.btnactualizar);
        eliminar = findViewById(R.id.btneliminar);
        codigo.setText(cod);
        codigo.setEnabled(false);
        nombre.setText(nom);
        programa.setText(prg);
        eliminar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                AlertDialog.Builder builder = new AlertDialog.Builder(EdicionActivity.this);
// Add the buttons
                builder.setPositiveButton("SI", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        EstudianteController ec = new EstudianteController(getApplication());
                        ec.eliminarEstudiante(codigo.getText().toString());
                        Intent i = new Intent(getApplicationContext(),ListadoActivity.class);
                        startActivity(i);
                    }
                });
                builder.setNegativeButton("NO", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        // User cancelled the dialog
                    }
                });
                builder.setMessage("Esta seguro de Eliminar el registro?")
                        .setTitle("UNIVERSIDAD");
                builder.show();
            }
        });
        actualizar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Estudiante e = new Estudiante(codigo.getText().toString(),nombre.getText().toString(),programa.getText().toString());
                EstudianteController ec = new EstudianteController(getApplication());
                ec.actualizarEstudiante(e);
                Intent i = new Intent(getApplicationContext(),ListadoActivity.class);
                startActivity(i);
            }
        });
    }
}