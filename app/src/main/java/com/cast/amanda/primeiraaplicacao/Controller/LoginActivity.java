package com.cast.amanda.primeiraaplicacao.Controller;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.cast.amanda.primeiraaplicacao.Model.Entities.Login;
import com.cast.amanda.primeiraaplicacao.Model.Persistence.SQLiteLoginRepository;
import com.cast.amanda.primeiraaplicacao.R;

public class LoginActivity  extends AppCompatActivity {

    private EditText editTextUserName;
    private EditText editTextPassord;
    private SQLiteLoginRepository sql;
    private Login login;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        login = new Login();
        sql = (SQLiteLoginRepository) SQLiteLoginRepository.getInstance();
        editTextUserName = (EditText) findViewById(R.id.editTextUserName);
        editTextPassord = (EditText) findViewById(R.id.editTextPassord);


        bindLoginButton();
    }

    private void bindLoginButton() {
        Button buttonLogin = (Button) findViewById(R.id.buttonLogin);
        buttonLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                login.setUsername(editTextUserName.getText().toString());
                login.setPassword(editTextPassord.getText().toString());
                if (validateLogin(login)) {
                    Intent goToMainActivity = new Intent(LoginActivity.this, HerbsListActivity.class);
                    startActivity(goToMainActivity);
                }  else{
                    Toast.makeText(LoginActivity.this, getString(R.string.message_error_login), Toast.LENGTH_LONG).show();
                }
            }
        });
    }

    private boolean validateLogin(Login login) {
        Login loginValido = sql.verifyLogin(login);
        return loginValido != null;
    }

}
