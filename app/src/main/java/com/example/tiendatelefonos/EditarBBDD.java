package com.example.tiendatelefonos;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
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

    public void onClickAceptar(View v) {
        Intent i = new Intent(this, MainActivity.class);
        Toast toast = Toast.makeText(this, "hola", Toast.LENGTH_SHORT);
        toast.show();
        startActivity(i);
    }

    private void editarRegistros(View v) {
        tv1 = findViewById(R.id.tvEditar1);
        tv2 = findViewById(R.id.tvEditar2);
        tv3 = findViewById(R.id.tvEditar3);
        tv4 = findViewById(R.id.tvEditar4);
        tv5 = findViewById(R.id.tvEditar5);
        String tabla = v.getResources().getResourceEntryName(v.getId());

        switch (tabla) {
            case "rbCliente":
                cambiarTitulos();
                break;
            case "rbEmpleado":

        }
    }

    private void cambiarTitulos(String titulo1, String titulo2, String titulo3, String titulo4, String titulo5) {
        tv1.setText(titulo1);
        tv2.setText(titulo2);
        tv3.setText(titulo3);
        tv4.setText(titulo4);
        tv5.setText(titulo5);
    }
}