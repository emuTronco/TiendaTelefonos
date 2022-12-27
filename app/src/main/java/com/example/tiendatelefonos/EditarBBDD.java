package com.example.tiendatelefonos;

import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class EditarBBDD extends AppCompatActivity {

    static TextView tv1, tv2, tv3, tv4, tv5;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_editar_bbdd);
    }

    public void onClickEditarRegistros(View v) {
        tv1 = findViewById(R.id.tvEditar1);
        tv2 = findViewById(R.id.tvEditar2);
        tv3 = findViewById(R.id.tvEditar3);
        tv4 = findViewById(R.id.tvEditar4);
        tv5 = findViewById(R.id.tvEditar5);
        String tabla = v.getResources().getResourceEntryName(v.getId());

        switch (tabla) {
            case "rbCliente":
                cambiarTitulos("RFC", "Nombre", "Dirección", "Teléfono", "e-mail");
                break;
            case "rbEmpleado":
                cambiarTitulos("Nombre", "Direccion", "id_empleado", "e-mail", "Teléfono");
                break;
            case "rbProducto":
                cambiarTitulos("Código", "Tipo", "Precio", "PH", "PH");
                break;
        }
    }

    public void onClickAceptar(View v) {
        RadioGroup rg_tabla = findViewById(R.id.rgTablasBBDD);
        int id_tabla = rg_tabla.indexOfChild(findViewById(rg_tabla.getCheckedRadioButtonId()));
        RadioGroup rg_consulta = findViewById(R.id.rgOperacionesBBDD);
        int id_consulta = rg_consulta.indexOfChild(findViewById(rg_consulta.getCheckedRadioButtonId()));
        EditText et1 = findViewById(R.id.editText);
        String datos1 = et1.getText().toString();
        EditText et2 = findViewById(R.id.editText2);
        String datos2 = et2.getText().toString();
        EditText et3 = findViewById(R.id.editText3);
        String datos3 = et3.getText().toString();
        EditText et4 = findViewById(R.id.editText4);
        String datos4 = et4.getText().toString();
        EditText et5 = findViewById(R.id.editText5);
        String datos5 = et5.getText().toString();

        String string_tabla = "";
        switch (id_tabla) {
            case 0:
                string_tabla = "cliente";
                break;
            case 1:
                string_tabla = "empleado";
                break;
            case 2:
                string_tabla = "producto";
                break;
            case 3:
                string_tabla = "telefono";
                break;
            case 4:
                string_tabla = "nose";
                break;
        }

        String mensaje_toast = "";
        UsuariosSQLiteHelper conn = new UsuariosSQLiteHelper(this, "DBContactos", null, 1);
        SQLiteDatabase bbdd = conn.getWritableDatabase();
        String consulta_insertar = "INSERT into " + string_tabla + " values ('" + datos1 + "', '" + datos2 + "', '" + datos3 + "', '" + datos4 + "', '" + datos5 + "');";
        String consulta_borrar = "DELETE from " + string_tabla + "where " + tv1.getText() + " = '" + datos1 + "' or " + tv2.getText() + " = '" + datos2 + "' or " + tv3.getText() + " = '" + datos3 + "' or " + tv4.getText() + " = '" + datos4 + "' or " + tv5.getText() + " = '" + datos5 + "';";
        switch (id_consulta) {
            case 0:
                bbdd.execSQL(consulta_insertar);
                mensaje_toast = "Registro insertado con éxito";
                break;
            case 1:
                bbdd.execSQL(consulta_borrar);
                mensaje_toast = "Registro eliminado con éxito";
                break;
        }

        Intent i = new Intent(this, MainActivity.class);
        Toast toast = Toast.makeText(this, mensaje_toast, Toast.LENGTH_SHORT);
        toast.show();
        startActivity(i);
    }

    private void cambiarTitulos(String titulo1, String titulo2, String titulo3, String titulo4, String titulo5) {
        tv1.setText(titulo1);
        tv2.setText(titulo2);
        tv3.setText(titulo3);
        tv4.setText(titulo4);
        tv5.setText(titulo5);
    }
}