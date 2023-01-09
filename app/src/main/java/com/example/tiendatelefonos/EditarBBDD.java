package com.example.tiendatelefonos;

import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class EditarBBDD extends AppCompatActivity {

    static TextView tv1, tv2, tv3, tv4, tv5;
    String[] array_modificar = new String[5];

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
                cambiarTitulos("cod_cliente", "nombre_cliente", "direccion", "email", "telefono");
                break;
            case "rbEmpleado":
                cambiarTitulos("cod_empleado", "nombre_empleado", "direccion", "salario", "telefono");
                break;
            case "rbProducto":
                cambiarTitulos("cod_producto", "tipo_producto", "precio_producto", "stock", "udEncargadas");
                break;
            case "rbServicio":
                cambiarTitulos("cod_servicio", "nombre_servicio", "precio_servicio", "tiempo_restante", "cod_cliente");
                break;
            case "rbFactura":
                cambiarTitulos("cod_factura", "cod_cliente", "cod_empleado", "precio_factura", "Fecha");
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
        UsuariosSQLiteHelper conn = new UsuariosSQLiteHelper(this, "DBContactos", null, 1);
        SQLiteDatabase bbdd = conn.getWritableDatabase();
        ContentValues nuevoRegistro = new ContentValues();
        nuevoRegistro.put(tv1.getText().toString(), datos1);
        nuevoRegistro.put(tv2.getText().toString(), datos2);
        nuevoRegistro.put(tv3.getText().toString(), datos3);
        nuevoRegistro.put(tv4.getText().toString(), datos4);
        nuevoRegistro.put(tv5.getText().toString(), datos5);
        boolean modificar_cargado = false;
        String mensaje_error = "Error en la edición, comprueba los datos introducidos";

        for (String registro : array_modificar) {
            if (registro != null) {
                modificar_cargado = true;
            }
        }

        switch (id_consulta) {
            case 0:
                long resultado_insert = bbdd.insert(string_tabla, null, nuevoRegistro);
                if (resultado_insert == -1) {
                    errorEdicion(mensaje_error);
                } else {
                    mensaje_toast = "Registro insertado con éxito";
                    terminarEdicion(mensaje_toast);
                }
                break;
            case 2:
                int resultado_delete = bbdd.delete(string_tabla, tv1.getText().toString() + "= '" + datos1 + "' or " + tv2.getText().toString() + "= '" + datos2 + "' or " + tv3.getText().toString() + "= '" + datos3 + "' or " + tv4.getText().toString() + "= '" + datos4 + "' or " + tv5.getText().toString() + "= '" + datos5 + "'", null);
                if (resultado_delete == 0) {
                    errorEdicion(mensaje_error);
                } else {
                    mensaje_toast = "Registro eliminado con éxito";
                    terminarEdicion(mensaje_toast);
                }
                break;
            case 1:
                if (!modificar_cargado) {
                    array_modificar = guardarModificado(array_modificar, nuevoRegistro, string_tabla, bbdd);
                } else {
                    bbdd.update(string_tabla, nuevoRegistro, tv1.getText().toString() + "= " + array_modificar[0] + " or " + tv2.getText().toString() + "= " + array_modificar[1] + " or " + tv3.getText().toString() + "= " + array_modificar[2] + " or " + tv4.getText().toString() + "= " + array_modificar[3] + " or " + tv5.getText().toString() + "= " + array_modificar[4] + "", null);
                    modificar_cargado = false;
                    mensaje_toast = "Registro modificado con éxito";
                    terminarEdicion(mensaje_toast);
                }
        }
    }

    private void terminarEdicion(String mensaje_toast) {
        Intent i = new Intent(this, MainActivity.class);
        Toast.makeText(this, mensaje_toast, Toast.LENGTH_SHORT).show();
        startActivity(i);
    }

    private void errorEdicion(String mensaje_toast) {
        Intent i = new Intent(this, MainActivity.class);
        Toast.makeText(this, mensaje_toast, Toast.LENGTH_SHORT).show();
    }

    private String[] guardarModificado(String[] array_modificar, ContentValues nuevoRegistro, String string_tabla, SQLiteDatabase bbdd) {
        boolean registroEncontrado = true;
        for (String key : nuevoRegistro.keySet()) {
            String comprobarRegistro = nuevoRegistro.getAsString(key);
            if (!comprobarRegistro.equals("")) {
                Cursor c = bbdd.rawQuery("SELECT * from " + string_tabla + " where " + key + " = " + comprobarRegistro + ";", null);
                if (!c.moveToFirst()) {
                    Toast.makeText(this, "El registro a modificar no se encuentra en la base de datos", Toast.LENGTH_SHORT).show();
                    registroEncontrado = false;
                    break;
                }
            }
        }

        if (registroEncontrado) {
            array_modificar[0] = nuevoRegistro.getAsString(tv1.getText().toString());
            array_modificar[1] = nuevoRegistro.getAsString(tv2.getText().toString());
            array_modificar[2] = nuevoRegistro.getAsString(tv3.getText().toString());
            array_modificar[3] = nuevoRegistro.getAsString(tv4.getText().toString());
            array_modificar[4] = nuevoRegistro.getAsString(tv5.getText().toString());
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

        return array_modificar;
    }

    private void cambiarTitulos(String titulo1, String titulo2, String titulo3, String titulo4, String titulo5) {
        tv1.setText(titulo1);
        tv2.setText(titulo2);
        tv3.setText(titulo3);
        tv4.setText(titulo4);
        tv5.setText(titulo5);
    }
}