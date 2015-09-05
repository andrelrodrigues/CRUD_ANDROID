package com.todolist;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Switch;
import android.widget.TextView;

import com.todolist.adapter.UsuarioAdapter;
import com.todolist.dao.UsuarioDAO;
import com.todolist.model.Usuario;
import com.todolist.util.Mensagem;

import java.util.List;


public class MainActivity extends ActionBarActivity {

    private ListView lista;
    private List<Usuario> usuarioList;
    private UsuarioAdapter usuarioAdapter;
    private UsuarioDAO usuarioDAO;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        LoginActivity lo = new LoginActivity();
       // TextView txtuser = (TextView) findViewById(R.id.txtNome);
        usuarioDAO = new UsuarioDAO(this);
        usuarioList = usuarioDAO.listarUsuarios();
        usuarioAdapter = new UsuarioAdapter(this, usuarioList);
        lista = (ListView) findViewById(R.id.lvUsuario);
        lista.setAdapter(usuarioAdapter);
        //lista.setOnItemClickListener(this);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        switch (item.getItemId()) {
            case R.id.cadastroUsuarios:
                startActivity(new Intent(this, CadUsuarioActivity.class));
                //return true;
                break;
            case R.id.ListaUsuarios:
                startActivity(new Intent(this, ListUsuariosActivity.class));
                //return true;
                break;
            case R.id.ListaSair:
                AlertDialog.Builder alert = new AlertDialog.Builder(this);
                alert.setTitle("Sair");
                alert.setMessage("Voce deseja realmente Sair?");
                alert.setPositiveButton("sim", new OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        finish();
                    }
                });
                alert.setNegativeButton("não", null);
                alert.show();

                break;
            case R.id.ListaLogout:
                SharedPreferences preferences = getSharedPreferences("LoginActivityPreferences", Context.MODE_PRIVATE);
                SharedPreferences.Editor editor = preferences.edit();
                editor.clear();
                editor.commit();
                startActivity(new Intent(this, LoginActivity.class));
                break;
        }


        /*noinspection SimplifiableIfStatement
        if (id == R.id.cadastroUsuarios) {
            startActivity(new Intent(this, CadUsuarioActivity.class));
            return true;
        }

        if (id == R.id.ListaUsuarios) {
            startActivity(new Intent(this, ListUsuariosActivity.class));
            return true;
        }*/

        return super.onOptionsItemSelected(item);
    }

}
