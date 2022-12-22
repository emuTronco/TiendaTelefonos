package com.example.tiendatelefonos;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TableLayout;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class VerBBDD extends AppCompatActivity {

    private TableLayout tlBBDD;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ver_bbdd);

        tlBBDD = findViewById(R.id.tablaBBDD);
        llenarTabla();
    }

    private void llenarTabla() {
        UsuariosSQLiteHelper conn = new UsuariosSQLiteHelper(this, "DBContactos", null, 1);
        SQLiteDatabase bbdd = conn.getWritableDatabase();
        Cursor fila = bbdd.rawQuery("select nombre, telefono from contactos", null);
        fila.moveToFirst();
        do {
            View registro = LayoutInflater.from(this).inflate(R.layout.item_table_layout, null, false);
            TextView tvNombre = registro.findViewById(R.id.tv1);
            TextView tvTlf = registro.findViewById(R.id.tv2);
            tvNombre.setText(fila.getString(0));
            tvTlf.setText(fila.getString(1));
            tlBBDD.addView(registro);
        } while (fila.moveToNext());
    }
}