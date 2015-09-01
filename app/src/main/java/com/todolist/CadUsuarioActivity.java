package com.todolist;

import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.EditText;

import com.todolist.dao.UsuarioDAO;
import com.todolist.model.Usuario;
import com.todolist.util.Mensagem;


public class CadUsuarioActivity extends ActionBarActivity {
    private EditText edtnome, edtlogin, edtsenha;
    private UsuarioDAO usuarioDAO;
    private Usuario usuario;
    private int idusuario;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cad_usuario);

        usuarioDAO = new UsuarioDAO(this);

        edtnome = (EditText) findViewById(R.id.usuario_edtNome);
        edtlogin = (EditText) findViewById(R.id.usuario_edtLogin);
        edtsenha = (EditText) findViewById(R.id.usuario_edtSenha);

    }

    public void onDestroy() {
        usuarioDAO.fechar();
        super.onDestroy();
    }

    public void cadastrar() {
        boolean validacao = true;
        String nome = edtnome.getText().toString();
        String login = edtlogin.getText().toString();
        String senha = edtsenha.getText().toString();

        if (nome == null || nome.equals("")) {
            validacao = false;
            edtnome.setError("Campo Obrigatório!");
        }

        if (login == null || login.equals("")) {
            validacao = false;
            edtlogin.setError("Campo Obrigatório!");
        }

        if (senha == null || senha.equals("")) {
            validacao = false;
            edtsenha.setError("Campo Obrigatório!");
        }
        if (validacao) {
            usuario = new Usuario();
            usuario.setNome(nome);
            usuario.setLogin(login);
            usuario.setSenha(senha);

            //atualizacao
            if (idusuario > 0) {
                usuario.set_id(idusuario);
            }
            long resultado = usuarioDAO.SalvarUsuario(usuario);
            if (resultado != -1) {
                if (idusuario > 0) {
                    Mensagem.Msg(this, "Atualizado com sucesso!");

                } else {
                    Mensagem.Msg(this, "Salvo com sucesso!");
                }
                finish();
                startActivity(new Intent(this, MainActivity.class));
            } else {
                Mensagem.Msg(this, "ERRO ao salvar!");
            }
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.cadastros, menu);

        if (idusuario > 0) {
            menu.findItem(R.id.action_Menu_Excluir).setVisible(true);
        }

        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        switch (id) {
            case R.id.action_Menu_Salvar:
                this.cadastrar();
                break;
            case R.id.action_Menu_Sair:
                finish();
                startActivity(new Intent(this, MainActivity.class));
                break;


        }

        return super.onOptionsItemSelected(item);
    }
}
