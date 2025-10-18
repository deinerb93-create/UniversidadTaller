package com.example.mainactivity;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

public class ArtistaController {

    private BaseDatos baseDatos;

    public ArtistaController(Context context) {
        baseDatos = new BaseDatos(context, 1);
    }

    public long agregarArtista(Artista artista) {
        SQLiteDatabase db = baseDatos.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(DefBD.col_nroid, artista.getNroId());
        values.put(DefBD.col_nombre, artista.getNombre());
        values.put(DefBD.col_nacionalidad, artista.getNacionalidad());
        values.put(DefBD.col_genero, artista.getGenero());
        long id = db.insert(DefBD.tabla_artista, null, values);
        db.close();
        return id;
    }
    public Cursor listarArtistas() {
        SQLiteDatabase db = baseDatos.getReadableDatabase();
        String[] columnas = {
                DefBD.col_nroid,
                DefBD.col_nombre,
                DefBD.col_nacionalidad,
                DefBD.col_genero
        };
        return db.query(DefBD.tabla_artista, columnas, null, null, null, null, null);
    }

    public Cursor allArtistas() {
        SQLiteDatabase db = baseDatos.getReadableDatabase();
        return db.rawQuery("SELECT NroId AS _id, NroId, Nombre, Nacionalidad, Genero FROM " + DefBD.tabla_artista, null);

    }
    public int eliminarArtista(String nroId) {
        SQLiteDatabase db = baseDatos.getWritableDatabase();
        String whereClause = DefBD.col_nroid + "=?";
        String[] whereArgs = { nroId };
        int filas = db.delete(DefBD.tabla_artista, whereClause, whereArgs);
        db.close();
        return filas;
    }

    public int actualizarArtista(Artista artista) {
        SQLiteDatabase db = baseDatos.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(DefBD.col_nombre, artista.getNombre());
        values.put(DefBD.col_nacionalidad, artista.getNacionalidad());
        values.put(DefBD.col_genero, artista.getGenero());
        String whereClause = DefBD.col_nroid + "=?";
        String[] whereArgs = { artista.getNroId() };
        int filas = db.update(DefBD.tabla_artista, values, whereClause, whereArgs);
        db.close();
        return filas;
    }
}




