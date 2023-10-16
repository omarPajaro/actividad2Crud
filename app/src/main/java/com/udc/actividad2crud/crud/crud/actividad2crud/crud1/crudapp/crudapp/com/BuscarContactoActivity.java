package com.udc.actividad2crud.crud.crud.actividad2crud.crud1.crudapp.crudapp.com;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;


import com.udc.actividad2crud.crud.crud.actividad2crud.crud1.crudapp.crudapp.com.controladores.ContactosBD;
import com.udc.actividad2crud.crud.crud.actividad2crud.crud1.crudapp.crudapp.com.modelos.contactos;

public class BuscarContactoActivity extends AppCompatActivity implements View.OnClickListener {


    Context context;
    EditText txtnombre;
    Button btnbuscar;

    ContactosBD contactosBD;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_buscar_contacto);
        init();
    }

    private void init() {
        context = getApplicationContext();
        txtnombre = findViewById(R.id.bus_txtnombre);
        btnbuscar = findViewById(R.id.bus_btnbuscar);
    }


    @Override
    public void onClick(View view) {
        if (view.getId() == R.id.bus_btnbuscar) {  // Verifica si se hizo clic en el botón de búsqueda
            String nombre = txtnombre.getText().toString(); // Obtiene el nombre ingresado en el EditText
            contactos Contactos = buscarnombre(nombre); // Llama a un método para buscar el contacto por nombre
            if (Contactos != null) { // Si se encontró el contacto
                Bundle bolsa = new Bundle();
                bolsa.putInt("id", Contactos.getId());
                bolsa.putInt("telefono", Contactos.getTelefono());
                bolsa.putString("nombre", Contactos.getnombre());
                bolsa.putString("correo", Contactos.getCorreo());

                Intent i = new Intent(context, GestionarContactosActivity.class);
                i.putExtras(bolsa); // Pasa datos del contacto a la siguiente actividad
                startActivity(i); // Inicia la siguiente actividad
            } else {

                Toast.makeText(context, "no existe el contacto con este nombre", Toast.LENGTH_LONG).show(); // Muestra que no existe
            }

        }
    }

    private contactos buscarnombre(String nombre) {
        ContactosBD contactosBD = new ContactosBD(context, "ContactosBD.db", null, 1);
        contactos Contactos = contactosBD.elemento(nombre); // Llama a un método para buscar un contacto en la base de datos por nombre
        return Contactos; // Devuelve el contacto encontrado o null si no se encuentra
    }
}