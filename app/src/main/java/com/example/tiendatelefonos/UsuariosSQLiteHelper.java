package com.example.tiendatelefonos;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class UsuariosSQLiteHelper extends SQLiteOpenHelper {
    //Sentencia SQL para crear la tabla de Usuarios
    String sqlCreate = "CREATE TABLE Contactos (nombre NUMERIC, telefono TEXT)";
    String sqlCreate2 = "CREATE TABLE Empleado (nombre TEXT, direccion TEXT, id_empleado NUMERIC, email TEXT, telefono NUMERIC)";

    public UsuariosSQLiteHelper(@Nullable Context context, @Nullable String name, @Nullable SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        //Se ejecuta la sentencia SQL de creación de la tabla
        sqLiteDatabase.execSQL(sqlCreate);
        sqLiteDatabase.execSQL(sqlCreate2);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int oldV, int newV) {
        //NOTA: Por simplicidad del ejemplo aquí utilizamos directamente la opción de
        // eliminar la tabla anterior y crearla de nuevo vacía con el nuevo formato.
        // Sin embargo lo normal será que haya que migrar datos de la tabla antigua
        // a la nueva, por lo que este método debería ser más elaborado.

        //Se elimina la versión anterior de la tabla
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS Contactos");
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS Empleado");

        //Se crea la nueva versión de la tabla
        sqLiteDatabase.execSQL(sqlCreate);
        sqLiteDatabase.execSQL(sqlCreate2);
    }

}
