package com.example.tiendatelefonos;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class UsuariosSQLiteHelper extends SQLiteOpenHelper {
    //Sentencia SQL para crear la tabla de Usuarios
    String sqlCreateCliente = "CREATE TABLE Cliente (cod_cliente TEXT, nombre_cliente TEXT, direccion TEXT, email TEXT, telefono NUMERIC)";
    String sqlCreateEmpleado = "CREATE TABLE Empleado (cod_empleado TEXT, nombre_empleado TEXT, direccion TEXT, salario NUMERIC, telefono NUMERIC)";
    String sqlCreateProducto = "CREATE TABLE Producto (cod_producto TEXT, tipo_producto TEXT, precio_producto NUMERIC, stock NUMERIC, udEncargadas NUMERIC)";
    String sqlCreateServicio = "CREATE TABLE Servicio (cod_servicio TEXT, nombre_servicio TEXT, precio_servicio NUMERIC, tiempo_restante NUMERIC, cod_cliente TEXT)";
    String sqlCreateFactura = "CREATE TABLE Factura (cod_factura TEXT, cod_cliente TEXT, cod_empleado TEXT, precio_factura NUMERIC, fecha TEXT)";


    public UsuariosSQLiteHelper(@Nullable Context context, @Nullable String name, @Nullable SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        //Se ejecuta la sentencia SQL de creación de la tabla
        sqLiteDatabase.execSQL(sqlCreateCliente);
        sqLiteDatabase.execSQL(sqlCreateEmpleado);
        sqLiteDatabase.execSQL(sqlCreateProducto);
        sqLiteDatabase.execSQL(sqlCreateServicio);
        sqLiteDatabase.execSQL(sqlCreateFactura);

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
        sqLiteDatabase.execSQL(sqlCreateCliente);
        sqLiteDatabase.execSQL(sqlCreateEmpleado);
    }

}
