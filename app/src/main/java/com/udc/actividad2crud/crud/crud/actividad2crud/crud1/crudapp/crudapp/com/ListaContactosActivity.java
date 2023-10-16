package com.udc.actividad2crud.crud.crud.actividad2crud.crud1.crudapp.crudapp.com;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.udc.actividad2crud.crud.crud.actividad2crud.crud1.crudapp.crudapp.com.controladores.ContactosBD;
import com.udc.actividad2crud.crud.crud.actividad2crud.crud1.crudapp.crudapp.com.controladores.SelectListener;
import com.udc.actividad2crud.crud.crud.actividad2crud.crud1.crudapp.crudapp.com.modelos.contactos;

import java.util.ArrayList;
import java.util.List;

public class ListaContactosActivity extends AppCompatActivity implements SelectListener {

    ListView listView;
    ArrayList<String> nombrescontactos;
    ArrayList<Integer> idcontactos;
    ContactosBD contactosBD;
    Context context;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lista_contactos);
        init();
    }

    private void init() {
        context = this.getApplicationContext();
        contactosBD = new ContactosBD(context, "ContactosBD.db", null, 1);
        listView = findViewById(R.id.listaContactos);
        llenarListView();
    }

    private void llenarListView() {
        nombrescontactos = new ArrayList<String>();
        idcontactos = new ArrayList<Integer>();

        List<contactos> contactosList = contactosBD.Lista(); // Obtiene la lista de contactos de la base de datos
        for (int i = 0; i < contactosList.size(); i++) {
            contactos c = contactosList.get(i);
            nombrescontactos.add(c.getnombre());
            idcontactos.add(c.getId());
        }

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(context,
                android.R.layout.simple_list_item_1,
                nombrescontactos
        );

        listView.setAdapter(adapter); // Asigna el adaptador al ListView para mostrar los nombres de los contactos

        // Configura un listener para manejar los clics en los elementos de la lista
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long id) {
                contactos Contactos = contactosBD.elemento(idcontactos.get(i));
                Bundle bolsa = new Bundle();
                bolsa.putInt("id", Contactos.getId());
                bolsa.putString("nombre", Contactos.getnombre());
                bolsa.putInt("telefono", Contactos.getTelefono());
                bolsa.putString("correo", Contactos.getCorreo());

                Intent intent = new Intent(context, GestionarContactosActivity.class);
                intent.putExtras(bolsa);
                startActivity(intent);
            }
        });
    }
    @Override
    public void onItenClick(String nombre) {

    }
}