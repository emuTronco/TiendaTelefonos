package com.example.tiendatelefonos;

import android.content.ContentValues;
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
                cambiarTitulos("RFC", "Nombre", "Dirección", "Telefono", "email");
                break;
            case "rbEmpleado":
                cambiarTitulos("Nombre", "Direccion", "id_empleado", "email", "Telefono");
                break;
            case "rbProducto":
                cambiarTitulos("Código", "Tipo", "Precio", "PH", "PH");
                break;
        }
    }

    public void onClickModificar(View v) {
        String operacion = v.getResources().getResourceEntryName(v.getId());
        TextView tvModificar = findViewById(R.id.tvModificar);
        switch (operacion) {
            case "rbModificar":
                tvModificar.setVisibility(View.VISIBLE);
                break;
            default:
                tvModificar.setVisibility(View.INVISIBLE);
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
        String[] array_modificar = new String[5];
        boolean modificar_cargado = false;
        UsuariosSQLiteHelper conn = new UsuariosSQLiteHelper(this, "DBContactos", null, 1);
        SQLiteDatabase bbdd = conn.getWritableDatabase();
        ContentValues nuevoRegistro = new ContentValues();
        nuevoRegistro.put(tv1.getText().toString(), datos1);
        nuevoRegistro.put(tv2.getText().toString(), datos2);
        nuevoRegistro.put(tv3.getText().toString(), datos3);
        nuevoRegistro.put(tv4.getText().toString(), datos4);
        nuevoRegistro.put(tv5.getText().toString(), datos5);
        String consulta_insertar = "INSERT into " + string_tabla + " values ('" + datos1 + "', '" + datos2 + "', '" + datos3 + "', '" + datos4 + "', '" + datos5 + "');";
        String consulta_borrar = "DELETE from " + string_tabla + " where " + tv1.getText() + " = '" + datos1 + "' or " + tv2.getText() + " = '" + datos2 + "' or " + tv3.getText() + " = '" + datos3 + "' or " + tv4.getText() + " = '" + datos4 + "' or " + tv5.getText() + " = '" + datos5 + "';";
        String consulta_borrar2 = "DELETE from " + string_tabla + " where " + tv1.getText().toString() + " = '" + datos1 + "';";
        switch (id_consulta) {
            case 0:
                bbdd.insert(string_tabla, null, nuevoRegistro);
//                bbdd.execSQL(consulta_insertar);
                mensaje_toast = "Registro insertado con éxito";
                terminarEdicion(mensaje_toast);
                break;
            case 2:
                bbdd.delete(string_tabla, tv1.getText().toString() + "= '" + datos1 + "' or " + tv2.getText().toString() + "= '" + datos2 + "' or " + tv3.getText().toString() + "= '" + datos3 + "' or " + tv4.getText().toString() + "= '" + datos4 + "' or " + tv5.getText().toString() + "= '" + datos5 + "'", null);
//                bbdd.execSQL(consulta_borrar2);
                mensaje_toast = "Registro eliminado con éxito";
                terminarEdicion(mensaje_toast);
                break;
            case 1:
                if (!modificar_cargado) {
                    guardarModificado(array_modificar, datos1, datos2, datos3, datos4, datos5);
                } else {
                    terminarEdicion(mensaje_toast);
                }
        }
    }

    private void terminarEdicion(String mensaje_toast) {
        Intent i = new Intent(this, MainActivity.class);
        Toast.makeText(this, mensaje_toast, Toast.LENGTH_SHORT).show();
        startActivity(i);
    }

    private void guardarModificado(String[] array_modificar, String datos1, String datos2, String datos3, String datos4, String datos5) {
        array_modificar[0] = datos1;
        array_modificar[1] = datos2;
        array_modificar[2] = datos3;
        array_modificar[3] = datos4;
        array_modificar[4] = datos5;
        TextView tvModificar = findViewById(R.id.tvModificar);
        tvModificar.setText("Nuevo registro");
        EditText et1 = findViewById(R.id.editText);
        EditText et2 = findViewById(R.id.editText2);
        EditText et3 = findViewById(R.id.editText3);
        EditText et4 = findViewById(R.id.editText4);
        EditText et5 = findViewById(R.id.editText5);
        et1.setText("");
        et2.setText("");
        et3.setText("");
        et4.setText("");
        et5.setText("");
        Toast.makeText(this, "Introduce los nuevos datos del registro que quieres modificar", Toast.LENGTH_SHORT).show();
    }

    private void cambiarTitulos(String titulo1, String titulo2, String titulo3, String titulo4, String titulo5) {
        tv1.setText(titulo1);
        tv2.setText(titulo2);
        tv3.setText(titulo3);
        tv4.setText(titulo4);
        tv5.setText(titulo5);
    }
}