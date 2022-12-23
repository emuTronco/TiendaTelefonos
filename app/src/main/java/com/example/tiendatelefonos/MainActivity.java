package com.example.tiendatelefonos;

import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Abrimos la base de datos 'DBContactos' en modo escritura
        UsuariosSQLiteHelper usdbh = new UsuariosSQLiteHelper(this, "DBContactos", null, 1);
        SQLiteDatabase db = usdbh.getWritableDatabase();
        db.execSQL("DROP TABLE IF EXISTS Contactos");
        usdbh.onCreate(db);
        //Si hemos abierto correctamente la base de datos
        if (db != null) {
            //Insertamos 5 usuarios de ejemplo
            for (int i = 1; i <= 5; i++) {
                //Generamos los datos
                int telefono = 11111111 + i;
                String nombre = "Usuario" + i;
                //Insertamos los datos en la tabla Usuarios
                db.execSQL("INSERT INTO Contactos (nombre, telefono) VALUES ('" + nombre + "', " + telefono + " )");
            }
            //Cerramos la base de datos
            db.close();
        }
    }

    public void onClickInfo(View v) {
        Intent i = new Intent(this, VerBBDD.class);
        i.putExtra("titulo", "Información productos");
        startActivity(i);
    }

    public void onClickFacturas(View v) {
        Intent i = new Intent(this, EditarBBDD.class);
        i.putExtra("titulo", "Facturas");
        startActivity(i);
    }

//    @Override
//    public boolean onCreateOptionsMenu(Menu menu) {
//        // Inflate the menu; this adds items to the action bar if it is present.
//        getMenuInflater().inflate(R.menu.main_crear_bd, menu);
//        return true;
//    }
}