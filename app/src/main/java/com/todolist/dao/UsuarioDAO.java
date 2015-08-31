package com.todolist.dao;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.todolist.model.Usuario;

/**
 * Created by renancunha on 30/08/15.
 */
public class UsuarioDAO {
    private DatabaseHelper databaseHelper;
    private SQLiteDatabase database;

    public UsuarioDAO(Context context){
       databaseHelper = new DatabaseHelper(context);
    }
    private SQLiteDatabase getDatabase(){
        if(database == null){
            database = databaseHelper.getWritableDatabase();
        }
        return database;
    }
    public void fechar(){
        databaseHelper.close();
        database=null;
    }
    private Usuario CriarUsuario(Cursor cursor){
        Usuario model = new Usuario(
                cursor.getInt(cursor.getColumnIndex(DatabaseHelper.Usuarios._ID)),
                cursor.getString(cursor.getColumnIndex(DatabaseHelper.Usuarios.NOME)),
                cursor.getString(cursor.getColumnIndex(DatabaseHelper.Usuarios.LOGIN)),
                cursor.getString(cursor.getColumnIndex(DatabaseHelper.Usuarios.SENHA))
        );
        return model;
    }
}
