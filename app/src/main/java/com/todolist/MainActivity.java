package com.todolist;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Switch;

import com.todolist.util.Mensagem;


public class MainActivity extends ActionBarActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
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
                Mensagem.addMsgConfirm(this, "Sair", "Deseja realmente sair?", R.drawable.abc_btn_radio_to_on_mtrl_015, new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        finish();
                    }
                });
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
