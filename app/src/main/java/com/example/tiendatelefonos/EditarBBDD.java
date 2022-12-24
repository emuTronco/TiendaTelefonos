package com.example.tiendatelefonos;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class EditarBBDD extends AppCompatActivity {

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
}