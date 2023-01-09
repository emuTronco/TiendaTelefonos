package com.example.tiendatelefonos;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Typeface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TableLayout;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class VerBBDD extends AppCompatActivity {

    private TableLayout tlBBDD;
    static TextView tv1, tv2, tv3, tv4, tv5, tvTitulo;
    private String queryCliente = "select * from cliente";
    private String queryEmpleado = "select * from empleado";
    private String queryProducto = "select * from producto";
    private String queryServicio = "select * from servicio";
    private String queryFactura = "select * from factura";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ver_bbdd);

        tlBBDD = findViewById(R.id.tablaBBDD);
        String titulo = getIntent().getStringExtra("titulo");
        tvTitulo = findViewById(R.id.titulo);
        tvTitulo.setText(titulo);

        switch (titulo) {
            case "Información general":
                informacionBBDD();
                break;
            case "Facturas":
                informacionTabla(queryFactura, "Código", "Cliente", "Empleado", "Precio", "Fecha");
                break;
            case "Empleados":
                informacionTabla(queryEmpleado, "Código", "Nombre", "Dirección", "Salario", "Teléfono");
                break;
            case "Productos":
                informacionTabla(queryProducto, "Código", "Tipo prod.", "Precio", "Stock", "Ud. encarg.");
        }


    }


    private void informacionBBDD() {
        View registro = LayoutInflater.from(this).inflate(R.layout.item_table_layout, null, false);
        encontrarColumnas(registro);
        UsuariosSQLiteHelper conn = new UsuariosSQLiteHelper(this, "DBContactos", null, 1);
        SQLiteDatabase bbdd = conn.getWritableDatabase();

        escribirIndices(registro, "Código", "Nombre", "Dirección", "e-mail", "Teléfono", "Cliente");
        dibujarTabla(bbdd, queryCliente);

        registro = LayoutInflater.from(this).inflate(R.layout.item_table_layout, null, false);
        escribirIndices(registro, "Código", "Nombre", "Dirección", "Salario", "Teléfono", "Empleado");
        dibujarTabla(bbdd, queryEmpleado);

        registro = LayoutInflater.from(this).inflate(R.layout.item_table_layout, null, false);
        escribirIndices(registro, "Código", "Tipo prod.", "Precio", "Stock", "Ud. encarg.", "Producto");
        dibujarTabla(bbdd, queryProducto);

        registro = LayoutInflater.from(this).inflate(R.layout.item_table_layout, null, false);
        escribirIndices(registro, "Código", "Nombre", "Precio", "T. rest.", "Cliente", "Servicio");
        dibujarTabla(bbdd, queryServicio);

        registro = LayoutInflater.from(this).inflate(R.layout.item_table_layout, null, false);
        escribirIndices(registro, "Código", "Cliente", "Empleado", "Precio", "Fecha", "Factura");
        dibujarTabla(bbdd, queryFactura);

    }

    private void informacionTabla(String query, String i1, String i2, String i3, String i4, String i5) {
        View registro = LayoutInflater.from(this).inflate(R.layout.item_table_layout, null, false);
        encontrarColumnas(registro);
        UsuariosSQLiteHelper conn = new UsuariosSQLiteHelper(this, "DBContactos", null, 1);
        SQLiteDatabase bbdd = conn.getWritableDatabase();

        escribirIndices(registro, i1, i2, i3, i4, i5, "");
        dibujarTabla(bbdd, query);
    }

    private void dibujarTabla(SQLiteDatabase bbdd, String query) {
        Cursor fila = bbdd.rawQuery(query, null);
        fila.moveToFirst();
        do {
            View registro = LayoutInflater.from(this).inflate(R.layout.item_table_layout, null, false);
            encontrarColumnas(registro);
            tv1.setText(fila.getString(0));
            tv2.setText(fila.getString(1));
            tv3.setText(fila.getString(2));
            tv4.setText(fila.getString(3));
            tv5.setText(fila.getString(4));
            tlBBDD.addView(registro);
        } while (fila.moveToNext());
        espacioTabla("");
    }

    private void escribirIndices(View registro, String i1, String i2, String i3, String i4, String i5, String titulo) {
        espacioTabla(titulo);
        encontrarColumnas(registro);
        tv1.setText(i1);
        tv1.setTypeface(null, Typeface.BOLD);
        tv2.setText(i2);
        tv2.setTypeface(null, Typeface.BOLD);
        tv3.setText(i3);
        tv3.setTypeface(null, Typeface.BOLD);
        tv4.setText(i4);
        tv4.setTypeface(null, Typeface.BOLD);
        tv5.setText(i5);
        tv5.setTypeface(null, Typeface.BOLD);
        tlBBDD.addView(registro);
    }

    private void espacioTabla(String titulo) {
        View registro = LayoutInflater.from(this).inflate(R.layout.item_table_layout, null, false);
        encontrarColumnas(registro);
        tv1.setBackground(null);
        tv2.setBackground(null);
        tv3.setBackground(null);
        tv3.setText(titulo);
        tv3.setTypeface(null, Typeface.BOLD);
        tv4.setBackground(null);
        tv5.setBackground(null);
        tlBBDD.addView(registro);
    }

    private void encontrarColumnas(View registro) {
        tv1 = registro.findViewById(R.id.tv1);
        tv2 = registro.findViewById(R.id.tv2);
        tv3 = registro.findViewById(R.id.tv3);
        tv4 = registro.findViewById(R.id.tv4);
        tv5 = registro.findViewById(R.id.tv5);
    }

    public void onClickAtras(View v) {
        Intent i = new Intent(this, MainActivity.class);
        startActivity(i);
    }

}