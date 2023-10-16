package com.udc.actividad2crud.crud.crud.actividad2crud.crud1.crudapp.crudapp.com.controladores;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.DatabaseErrorHandler;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.udc.actividad2crud.crud.crud.actividad2crud.crud1.crudapp.crudapp.com.modelos.contactos;

import java.util.ArrayList;
import java.util.List;

public class ContactosBD extends SQLiteOpenHelper implements IcontactosBD {
    Context context;
    private List<contactos> contactosList =new ArrayList<>();

    public ContactosBD(@Nullable Context context, @Nullable String name,
                       @Nullable SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);

    }

    // Método llamado cuando se crea la base de datos por primera vez
    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        String sql="CREATE TABLE contactos("+
                "_id INTEGER PRIMARY KEY AUTOINCREMENT,"+
                "telefono INTEGER,"+
                "nombre TEXT,"+
                "correo TEXT)";

        // Insertar un contacto de ejemplo

        sqLiteDatabase.execSQL(sql);
        String insert = "INSERT INTO contactos (telefono, nombre, correo) VALUES " +
                "('12345678', 'omar', 'omar123@gmail.com')";
        sqLiteDatabase.execSQL(insert);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }

    // Obtiene un contacto por su ID
    @Override
    public contactos elemento(int id) {
        SQLiteDatabase database = getReadableDatabase();
        String sql = "SELECT * FROM contactos WHERE _id=" + id;
        Cursor cursor = database.rawQuery(sql,null);
        try {
            if (cursor.moveToNext())
                return extraercontactos(cursor);
            else
                return null;
        } catch (Exception e){
            Log.d("TAG","Error elemento(id) ContactosBD"+ e.getMessage());
            throw e;
        }finally{
            if(cursor !=null) cursor.close();
        }
    }

    // Método auxiliar para extraer un contacto de un cursor
    private contactos extraercontactos(Cursor cursor) {
        contactos contactos =new contactos();
        contactos.setId(cursor.getInt(0));
        contactos.setTelefono(cursor.getInt(1));
        contactos.setnombre(cursor.getString(2));
        contactos.setCorreo(cursor.getString(3));

        return contactos;

    }


    // Obtiene un contacto por su nombre
    @Override
    public contactos elemento(String nombre) {
        SQLiteDatabase database = getReadableDatabase();
        String sql = "SELECT * FROM contactos WHERE nombre='" + nombre+"'";
        Cursor cursor = database.rawQuery(sql,null);
        try {
            if (cursor.moveToNext())
                return extraercontactos(cursor);
            else
                return null;
        } catch (Exception e){
            Log.d("TAG","Error elemento(nombre) ContactosBD"+ e.getMessage());
            throw e;
        }finally{
            if(cursor !=null) cursor.close();
        }

    }

    // Obtiene una lista de todos los contactos ordenados por nombre
    @Override
    public List<contactos> Lista() {
        SQLiteDatabase database= getReadableDatabase();
        String sql="SELECT* FROM contactos ORDER BY nombre ASC";
        Cursor cursor = database.rawQuery(sql,null);
        if ( cursor.moveToFirst()){
            do{
                contactosList.add(
                        new contactos( cursor.getInt(0),
                                cursor.getInt(1),
                                cursor.getString(2),
                                cursor.getString(3) )
                );
            }while(cursor.moveToNext());
        }
        cursor.close();
        return contactosList;

    }
    // Agrega un nuevo contacto a la base de datos
    @Override
    public void agregar(contactos contact) {
        SQLiteDatabase database=getWritableDatabase();
        ContentValues values=new ContentValues();
        values.put("telefono",contact.getTelefono());
        values.put("nombre",contact.getnombre());
        values.put("correo",contact.getCorreo());
        database.insert("contactos",null,values);


    }


    // Actualiza un contacto existente
    @Override
    public void actualizar(int id, contactos contact) {
        SQLiteDatabase database=getWritableDatabase();
        String[]parametros= {String.valueOf(id)};
        ContentValues values=new ContentValues();
        values.put("telefono",contact.getTelefono());
        values.put("nombre",contact.getnombre());
        values.put("correo",contact.getCorreo());
        database.update("contactos",values,"_id=?",parametros);

    }
    // Borra un contacto
    @Override
    public void borrar(int id) {
        SQLiteDatabase database=getWritableDatabase();
        String[]parametros= {String.valueOf(id)};
        database.delete("contactos","_id=?",parametros);

    }
}