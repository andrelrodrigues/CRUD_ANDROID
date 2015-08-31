package com.todolist;

import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;

import com.todolist.dao.UsuarioDAO;
import com.todolist.util.Mensagem;


public class LoginActivity extends ActionBarActivity {
    private EditText edtUsuario, edtSenha;
    UsuarioDAO helper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        edtUsuario = (EditText) findViewById(R.id.login_edtUsuario);
        edtSenha = (EditText) findViewById(R.id.login_edtSenha);
        helper = new UsuarioDAO(this);

    }

    public void logar(View view) {
        String usuario = edtUsuario.getText().toString();
        String senha = edtSenha.getText().toString();
        Boolean validacao = true;
        if (usuario == null || usuario.equals("")) {
            validacao = false;
            edtUsuario.setError(getString(R.string.Login_valUsuario));
        }
        if (senha == null || usuario.equals("")) {
            validacao = false;
            edtSenha.setError(getString(R.string.Login_valSenha));
        }
        if (validacao) {
            //logar
            if (helper.logar(usuario, senha)) {
                startActivity(new Intent(this, MainActivity.class));
                finish();
            } else {
                //erro
                Mensagem.Msg(this, String.valueOf(R.string.Login_valErro));
            }
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_login, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
