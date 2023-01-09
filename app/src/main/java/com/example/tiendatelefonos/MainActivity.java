package com.example.tiendatelefonos;

import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;

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
            //Abrimos la base de datos 'DBContactos' en modo escritura
            UsuariosSQLiteHelper usdbh = new UsuariosSQLiteHelper(this, "DBContactos", null, 1);
            SQLiteDatabase db = usdbh.getWritableDatabase();
            //Si hemos abierto correctamente la base de datos
            if (db != null) {
                //Insertamos 5 usuarios de ejemplo
                for (int i = 1; i <= 5; i++) {
                    //Generamos los datos
                    int cod_cliente = (int) (Math.random() * 9999 + 1000);
                    int cod_empleado = (int) (Math.random() * 9999 + 1000);
                    int cod_producto = (int) (Math.random() * 9999 + 1000);
                    int cod_servicio = (int) (Math.random() * 9999 + 1000);
                    int cod_factura = (int) (Math.random() * 9999 + 1000);
                    int telefono = (int) (Math.random() * 99999999 + 10000000);
                    String nombre_cliente = "Cliente" + i;
                    String direccion = "Direccion" + i;
                    String email = "email" + i;
                    String nombre_empleado = "Empleado" + i;
                    int salario = (int) (Math.random() * 9999 + 1000);
                    String tipo_producto = "Telefono" + i;
                    int precio_producto = (int) (Math.random() * 999 + 100);
                    int stock_producto = (int) (Math.random() * 100 + 1);
                    int encargo_producto = (int) (Math.random() * 100 + 1);
                    String nombre_servicio = "Servicio" + i;
                    int precio_servicio = (int) (Math.random() * 99 + 10);
                    int tiempo_restante = (int) (Math.random() * 99 + 1);
                    int precio_factura = (int) (Math.random() * 9999 + 1);
                    String fecha = "10-01-2023";

                    //Insertamos los datos en la tabla Usuarios
//                    db.execSQL("INSERT INTO Contactos (nombre, telefono) VALUES ('" + nombre + "', " + telefono + " )");
                    db.execSQL("INSERT INTO Cliente VALUES (" + cod_cliente + ", '" + nombre_cliente + "', '" + direccion + "', '" + email + "', " + telefono + ")");
                    db.execSQL("INSERT INTO Empleado VALUES (" + cod_empleado + ", '" + nombre_empleado + "', '" + direccion + "', " + salario + ", " + telefono + ")");
                    db.execSQL("INSERT INTO Producto VALUES (" + cod_producto + ", '" + tipo_producto + "', " + precio_producto + ", " + stock_producto + ", " + encargo_producto + ")");
                    db.execSQL("INSERT INTO Servicio VALUES (" + cod_servicio + ", '" + nombre_servicio + "', " + precio_servicio + ", " + tiempo_restante + ", " + cod_cliente + ")");
                    db.execSQL("INSERT INTO Factura VALUES (" + cod_factura + ", " + cod_cliente + ", " + cod_empleado + ", " + precio_factura + ", '" + fecha + "')");
                }
                //Cerramos la base de datos
                db.close();
            }

        }
    }


    public void onClickInfo(View v) {
        Intent i = new Intent(this, VerBBDD.class);
        i.putExtra("titulo", "Información general");
        startActivity(i);
    }

    public void onClickFacturas(View v) {
        Intent i = new Intent(this, VerBBDD.class);
        i.putExtra("titulo", "Facturas");
        startActivity(i);
    }

    public void onClickEmpleados(View v) {
        Intent i = new Intent(this, VerBBDD.class);
        i.putExtra("titulo", "Empleados");
        startActivity(i);
    }

    public void onClickProductos(View v) {
        Intent i = new Intent(this, VerBBDD.class);
        i.putExtra("titulo", "Productos");
        startActivity(i);
    }

    public void onClickGestionar(View v) {
        Intent i = new Intent(this, EditarBBDD.class);
        i.putExtra("titulo", "Facturas");
        startActivity(i);
    }

    private void reiniciarBBDD() {
        this.deleteDatabase("DBContactos");
    }

}