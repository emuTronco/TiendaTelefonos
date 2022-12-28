package com.example.tiendatelefonos;

import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    public static int versionTabla = 1;
    private static boolean reiniciado = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        if (!reiniciado) {  //se reinicia la bbdd la primera vez que se inicie la aplicacion
            reiniciarBBDD();
            reiniciado = true;
        }
        //Abrimos la base de datos 'DBContactos' en modo escritura
        UsuariosSQLiteHelper usdbh = new UsuariosSQLiteHelper(this, "DBContactos", null, 1);
        SQLiteDatabase db = usdbh.getWritableDatabase();
        //Si hemos abierto correctamente la base de datos
        if (db != null) {
            //Insertamos 5 usuarios de ejemplo
            for (int i = 1; i <= 5; i++) {
                //Generamos los datos
                int telefono = 11111111 + i;
                String nombre = "Usuario" + i;
                //Insertamos los datos en la tabla Usuarios
                db.execSQL("INSERT INTO Contactos (nombre, telefono) VALUES ('" + nombre + "', " + telefono + " )");
                db.execSQL("INSERT INTO Empleado (nombre, direccion) VALUES ('Pepe', 'asdf')");
            }
            db.execSQL("INSERT INTO Empleado (nombre, direccion) VALUES ('Pepito', 'hfthfth')");
            //Cerramos la base de datos
//            db.close();
        }
    }


    public void onClickInfo(View v) {
        Intent i = new Intent(this, VerBBDD.class);
        i.putExtra("titulo", "Información productos");
        startActivity(i);
        finish();
        overridePendingTransition(0, 0);
    }

    public void onClickFacturas(View v) {
        Intent i = new Intent(this, EditarBBDD.class);
        i.putExtra("titulo", "Facturas");
        startActivity(i);
    }

    private void reiniciarBBDD() {
        this.deleteDatabase("DBContactos");
    }

}