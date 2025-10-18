package com.example.mainactivity;

import android.content.Context;
import android.database.Cursor;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CursorAdapter;
import android.widget.TextView;

public class ArtistaCursorAdapter extends CursorAdapter {

    public ArtistaCursorAdapter(Context context, Cursor c, boolean autoRequery) {
        super(context, c, autoRequery);
    }

    @Override
    public View newView(Context context, Cursor cursor, ViewGroup parent) {
        return LayoutInflater.from(context).inflate(R.layout.fila_artista, parent, false);
    }

    @Override
    public void bindView(View view, Context context, Cursor cursor) {
        TextView nroId = view.findViewById(R.id.txtNroId);
        TextView nombre = view.findViewById(R.id.txtNombre);
        TextView nacionalidad = view.findViewById(R.id.txtNacionalidad);
        TextView genero = view.findViewById(R.id.txtGenero);

        nroId.setText(cursor.getString(cursor.getColumnIndexOrThrow("NroId")));
        nombre.setText(cursor.getString(cursor.getColumnIndexOrThrow("Nombre")));
        nacionalidad.setText(cursor.getString(cursor.getColumnIndexOrThrow("Nacionalidad")));
        genero.setText(cursor.getString(cursor.getColumnIndexOrThrow("Genero")));
    }
}
