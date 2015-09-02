package com.todolist;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;

import com.todolist.dao.UsuarioDAO;
import com.todolist.util.Mensagem;


public class LoginActivity extends ActionBarActivity {
    private EditText edtUsuario, edtSenha;
    UsuarioDAO helper;
    private CheckBox ckbConectado;
    private static final String MANTER_CONECTADO = "manter conectado";
    private static final String PREFERENCE_NAME = "LoginActivityPreferences";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        edtUsuario = (EditText) findViewById(R.id.login_edtUsuario);
        edtSenha = (EditText) findViewById(R.id.login_edtSenha);
        ckbConectado = (CheckBox) findViewById(R.id.login_ckbConectado);
        helper = new UsuarioDAO(this);
        SharedPreferences preferences = getSharedPreferences(PREFERENCE_NAME, MODE_PRIVATE);
        boolean conectado = preferences.getBoolean(MANTER_CONECTADO, false);
        if (conectado) {
            chamarMainActivity();
        }
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
                //manter conectado
                if ((ckbConectado.isChecked())) {
                    SharedPreferences sharedPreferences = getSharedPreferences(PREFERENCE_NAME, MODE_PRIVATE);
                    SharedPreferences.Editor editor = sharedPreferences.edit();

                    editor.putBoolean(MANTER_CONECTADO, true);
                    editor.commit();
                }

                chamarMainActivity();
            } else {
                //erro
                Mensagem.Msg(this, getString(R.string.Login_valErro));
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
            Mensagem.addMsgConfirm(this, "Sair", "Deseja realmente sair?", R.drawable.abc_btn_radio_to_on_mtrl_015, new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    finish();
                }
            });

            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    private void chamarMainActivity() {
        startActivity(new Intent(this, MainActivity.class));
        finish();
    }
}
