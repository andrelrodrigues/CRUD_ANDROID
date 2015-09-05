package com.todolist;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ListView;

import com.todolist.adapter.TarefaAdapter;
import com.todolist.dao.TarefaDAO;
import com.todolist.model.Tarefa;

import java.util.List;


public class MainActivity extends ActionBarActivity {

    private ListView lista;
    private List<Tarefa> tarefaList;
    private TarefaAdapter tarefaAdapter;
    private TarefaDAO tarefaDAO;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        android.support.v7.app.ActionBar actionBar = getSupportActionBar();
        actionBar.setBackgroundDrawable(new ColorDrawable(Color.parseColor("#1A237E")));


        LoginActivity lo = new LoginActivity();
        // TextView txtuser = (TextView) findViewById(R.id.txtNome);
        tarefaDAO = new TarefaDAO(this);
        tarefaList = tarefaDAO.listarTarefas();
        tarefaAdapter = new TarefaAdapter(this, tarefaList);
        lista = (ListView) findViewById(R.id.lvTarefa);
        lista.setAdapter(tarefaAdapter);

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
            case R.id.cadastroTarefa:
                startActivity(new Intent(this, CadTarefaActivity.class));
                break;
            case R.id.ListaTarefa:
                startActivity(new Intent(this, ListTarefaActivity.class));
                break;
            case R.id.ListaLogout:
                SharedPreferences preferences = getSharedPreferences("LoginActivityPreferences", Context.MODE_PRIVATE);
                SharedPreferences.Editor editor = preferences.edit();
                editor.clear();
                editor.commit();
                startActivity(new Intent(this, LoginActivity.class));
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
