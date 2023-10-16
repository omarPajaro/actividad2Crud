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

public class GestionarContactosActivity extends AppCompatActivity implements View.OnClickListener {

    Context context;
    EditText txtnombre, txttelefono, txtcorreo;
    int id;
    ContactosBD contactosBD;
    Button btnguardar,btnactualizar,btnborrar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gestionar_contactos);
        init();
    }

    private void init() {
        context = getApplicationContext();
        txtnombre = findViewById(R.id.ges_txtnombre);
        txttelefono = findViewById(R.id.ges_txttelefono);
        txtcorreo = findViewById(R.id.ges_txtcorreo);

        btnactualizar = findViewById(R.id.ges_btnactualizar);
        btnborrar = findViewById(R.id.ges_btnborrar);
        btnguardar = findViewById(R.id.ges_btnguardar);

        Intent i = getIntent();
        Bundle bolsa = i.getExtras();
        id = bolsa.getInt("id");
        if (id != 0 && bolsa.containsKey("telefono")) {
            txtnombre.setText(bolsa.getString("nombre"));

            // Comprobar si el valor 'telefono' es nulo antes de obtenerlo
            int telefono = bolsa.getInt("telefono", 0);
            if (telefono != 0) {
                txttelefono.setText(String.valueOf(telefono));

                // Ocultar el botón "Guardar" al editar un contacto existente
                btnguardar.setVisibility(View.GONE);

                // Mostrar los botones "Actualizar" y "Borrar"
                btnactualizar.setVisibility(View.VISIBLE);
                btnborrar.setVisibility(View.VISIBLE);
            } else {
                // Ocultar los botones "Actualizar" y "Borrar" al agregar un nuevo contacto
                btnactualizar.setVisibility(View.GONE);
                btnborrar.setVisibility(View.GONE);
            }


            txtcorreo.setText(bolsa.getString("correo"));
        }
    }

    private void limpiarCampos() {
        id = 0;
        txtnombre.setText("");
        txttelefono.setText("");
        txtcorreo.setText("");

    }

    private contactos llenardatoscontactos() {
        contactos contactos = new contactos();
        String n = txtnombre.getText().toString();
        String t = txttelefono.getText().toString();
        String c = txtcorreo.getText().toString();

        contactos.setId(id);
        contactos.setnombre(n);
        contactos.setTelefono(Integer.parseInt(t));
        contactos.setCorreo(c);
        return contactos;
    }

    private void guardar() {
        ContactosBD contactosBD = new ContactosBD(context, "ContactosBD.db", null, 1);
        contactos contactos = llenardatoscontactos();

        if (id == 0) {
            contactosBD.agregar(contactos);
            Toast.makeText(context, "Guardado nuevo ok", Toast.LENGTH_LONG).show();
        } else {
            contactosBD.actualizar(id, contactos);
            btnactualizar.setEnabled(false);
            btnborrar.setEnabled(false);
            Toast.makeText(context, "Actualizado existente ok", Toast.LENGTH_LONG).show();
        }
    }


    private void borrar() {
        ContactosBD contactosBD = new ContactosBD(context, "ContactosBD.db", null, 1);
        contactos contactos = llenardatoscontactos();

        if (id == 0) {

            Toast.makeText(context, "No es posible borrar", Toast.LENGTH_LONG).show();
        } else {
            contactosBD.borrar(id);
            limpiarCampos();
            btnguardar.setEnabled(true);
            btnactualizar.setEnabled(false);
            btnborrar.setEnabled(false);
            Toast.makeText(context, "Se borro el registro", Toast.LENGTH_LONG).show();

        }
    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.ges_btnguardar) {
            guardar();
        } else if (v.getId() == R.id.ges_btnactualizar) {
            guardar();
        } else if (v.getId() == R.id.ges_btnborrar) {
            borrar();
        }

    }
}