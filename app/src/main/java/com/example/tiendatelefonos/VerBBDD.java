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
        mostrarTabla();
    }

    private void mostrarTabla() {
        UsuariosSQLiteHelper conn = new UsuariosSQLiteHelper(this, "DBContactos", null, 1);
        View registro2 = LayoutInflater.from(this).inflate(R.layout.item_table_layout, null, false);
        TextView tv1 = registro2.findViewById(R.id.tv1);
        TextView tv2 = registro2.findViewById(R.id.tv2);
        TextView tv3 = registro2.findViewById(R.id.tv3);
        TextView tv4 = registro2.findViewById(R.id.tv4);
        TextView tv5 = registro2.findViewById(R.id.tv5);
        tv1.setText("fgdg");
        tv2.setText("dfgdg");
        tv3.setText("dfgs");
        tv4.setText("sfds");
        tv5.setText("SFGDFG");

        tlBBDD.addView(registro2);
        SQLiteDatabase bbdd = conn.getWritableDatabase();
        Cursor fila = bbdd.rawQuery("select nombre, telefono from contactos", null);
        fila.moveToFirst();
        do {
            View registro = LayoutInflater.from(this).inflate(R.layout.item_table_layout, null, false);
            tv1 = registro.findViewById(R.id.tv1);
            tv2 = registro.findViewById(R.id.tv2);
            tv1.setText(fila.getString(0));
            tv2.setText(fila.getString(1));
            tlBBDD.addView(registro);
        } while (fila.moveToNext());
    }
}