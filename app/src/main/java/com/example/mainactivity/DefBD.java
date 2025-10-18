package com.example.mainactivity;

public class DefBD {
    public static final String nameDb = "Artistas";
    public static final String tabla_artista = "Artista";
    public static final String col_nroid = "NroId";
    public static final String col_nombre = "Nombre";
    public static final String col_nacionalidad = "Nacionalidad";
    public static final String col_genero = "Genero";
    public static final String create_tabla_artista =
            "CREATE TABLE IF NOT EXISTS " + DefBD.tabla_artista + " ( " +
                    DefBD.col_nroid + " text primary key," +
                    DefBD.col_nombre + " text," +
                    DefBD.col_nacionalidad + " text," +
                    DefBD.col_genero + " text" +
                    ");";
}
