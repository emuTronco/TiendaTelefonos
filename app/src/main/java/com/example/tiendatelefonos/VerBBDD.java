package com.example.tiendatelefonos;

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
    private String query1 = "select nombre, telefono from contactos";
    private String query2 = "select nombre, direccion from empleado";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ver_bbdd);

        tlBBDD = findViewById(R.id.tablaBBDD);
        String titulo = getIntent().getStringExtra("titulo");
        tvTitulo = findViewById(R.id.titulo);
        tvTitulo.setText(titulo);
        verTabla();
    }


    private void verTabla() {
        View registro = LayoutInflater.from(this).inflate(R.layout.item_table_layout, null, false);
        encontrarColumnas(registro);
        UsuariosSQLiteHelper conn = new UsuariosSQLiteHelper(this, "DBContactos", null, 1);
        SQLiteDatabase bbdd = conn.getWritableDatabase();

        escribirIndices(registro, "Registro 1", "Registro 2", "Registro 3", "Registro 4", "Registro 5");
        dibujarTabla(bbdd, query1);

        registro = LayoutInflater.from(this).inflate(R.layout.item_table_layout, null, false);
        escribirIndices(registro, "Registro 1", "Registro 2", "Registro 3", "Registro 4", "Registro 5");
        dibujarTabla(bbdd, query2);

    }

    private void dibujarTabla(SQLiteDatabase bbdd, String query) {
        Cursor fila = bbdd.rawQuery(query, null);
        fila.moveToFirst();
        do {
            View registro = LayoutInflater.from(this).inflate(R.layout.item_table_layout, null, false);
            encontrarColumnas(registro);
            tv1.setText(fila.getString(0));
            tv2.setText(fila.getString(1));
            tlBBDD.addView(registro);
        } while (fila.moveToNext());
        espacioTabla();
    }

    private void escribirIndices(View registro, String i1, String i2, String i3, String i4, String i5) {
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

    private void espacioTabla() {
        View registro = LayoutInflater.from(this).inflate(R.layout.item_table_layout, null, false);
        encontrarColumnas(registro);
        tv1.setBackground(null);
        tv2.setBackground(null);
        tv3.setBackground(null);
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

}